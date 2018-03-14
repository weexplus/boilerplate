package com.farwolf.module;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/12/14.
 */

public class WXOSSModule extends WXModule {


    @JSMethod
    public void upload(HashMap params, final JSCallback progress, final JSCallback compelete)
    {

        String url=params.get("url")+"";
        JSONObject param=(JSONObject)params.get("param");
        String objectkey=params.get("objectkey")+"";
        String endpoint =param.get("Endpoint")+"";
        String AccessKeyId =param.get("AccessKeyId")+"";
        String AccessKeySecret =param.get("AccessKeySecret")+"";
        String SecurityToken = param.get("SecurityToken")+"";
        String BucketName =param.get("BucketName")+"";

        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(AccessKeyId, AccessKeySecret,SecurityToken);
        //该配置类如果不设置，会有默认配置，具体可看该类
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        OSS oss = new OSSClient(this.mWXSDKInstance.getContext(), endpoint, credentialProvider);

//        String timestamp=DateTool.Date().replace("-","/")+"/"+DateTool.TimeTampMili();
        url=url.replace("sdcard:","");
        PutObjectRequest put = new PutObjectRequest(BucketName, objectkey, url);
// 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                HashMap m=new HashMap();
                m.put("send",currentSize);
                m.put("total",totalSize);
                progress.invokeAndKeepAlive(m);
            }
        });
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                HashMap m=new HashMap();
                m.put("err",0);
                m.put("id",result.getETag());

                compelete.invokeAndKeepAlive(m);
            }
            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                HashMap m=new HashMap();
                if (serviceException != null) {
                    // 服务异常
                    m.put("ErrorCode", serviceException.getErrorCode());
                    m.put("RequestId", serviceException.getRequestId());
                    m.put("HostId", serviceException.getHostId());
                    m.put("RawMessage", serviceException.getRawMessage());
                }



                m.put("err",1);
                compelete.invokeAndKeepAlive(m);
            }
        });

    }

}
