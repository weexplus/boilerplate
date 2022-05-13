package com.weexplus.module;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.os.Build;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.weexplus.update.ApkDownloadService;
import com.weexplus.update.JsDownloader;
import com.weexplus.update.UpdateService;
import com.weexplus.util.Callback;
import com.weexplus.util.Config;
import com.weexplus.util.Const;
import com.weexplus.util.IFullHttp;
import com.weexplus.util.Permission;
import com.weexplus.util.PerssionCallback;



import java.util.HashMap;


/**
 * Created by zhengjiangrong on 2018/3/22.
 */

public class WXUpdateModule extends WXModule {



    @JSMethod
    public void doCheck(JSONObject  param,final JSCallback callback)
    {
        String appid=param.get("appid")+"";
        boolean installNextOpen=Boolean.valueOf( param.get("installNextOpen")+"");
        String vcurl=param.get("url")+"";
        String theme=param.containsKey("theme")?param.get("theme")+"":null;
        boolean failtoast=param.getBoolean("failtoast");
        boolean showprogress=param.getBoolean("showprogress");
        UpdateService updateService= new UpdateService();
        updateService.init(appid,vcurl,theme,installNextOpen);

        updateService.doCheck(failtoast, showprogress, new Callback() {
            @Override
            public void onInvoke(Object o) {
                callback.invoke(new HashMap());
            }
        });
    }


    @JSMethod
    public void checkDownloadApk(JSCallback callback)
    {
//         UpdateService up=new  UpdateService();
//
//        if(!up.checkExistApk(mWXSDKInstance.getContext()))
//        {
//            callback.invoke(new HashMap());
//        }

    }

    @JSMethod
    public void doCheckJs(JSONObject param)
    {
        String appid=param.get("appid")+"";
        String vcurl=param.get("url")+"";
        String module= Const.MAIN;
        if(param.containsKey("module")){
            module=param.get("module")+"";
        }
        String theme=param.containsKey("theme")?param.get("theme")+"":null;
        boolean failtoast=param.getBoolean("failtoast");
        boolean showprogress=param.getBoolean("showprogress");
        UpdateService updateService=new UpdateService();
        updateService.init(appid,vcurl,theme,false);
        updateService.doCheckJs(Config.jsVersion(mWXSDKInstance.getContext())+"",module,showprogress);
    }


    @JSMethod
    public void hotUpdate(HashMap param , final JSCallback start, final JSCallback progress, final JSCallback compelete, final JSCallback exception)
    {

        String url=param.get("url")+"";
        String module=Const.MAIN;
        if(param.containsKey("module")){
            module=param.get("module")+"";
        }
        int version=0;
        if(param.containsKey("version")){
            version=Integer.parseInt( param.get("version")+"");
        }
        int mode=0;
        if(param.containsKey("mode")){
            mode=Integer.parseInt( param.get("mode")+"");
        }
        new JsDownloader().start(url,version,mode,module, mWXSDKInstance.getContext(), new IFullHttp() {
            @Override
            public void OnPostProcess(float newProgress,float current,float total) {
                if(progress!=null)
                    progress.invokeAndKeepAlive(newProgress);
            }

            @Override
            public void OnPostStart(Object o) {
                if(start!=null)
                    start.invoke(null);
            }

            @Override
            public void OnPostCompelete(Object o) {
                if(compelete!=null)
                    compelete.invoke(null);
            }

            @Override
            public void OnException(Object o) {
                if(exception!=null)
                    exception.invoke(null);
            }
        });
    }




    @JSMethod
    public void download(final String url)
    {


        Permission.check((Activity) mWXSDKInstance.getContext(), Manifest.permission.REQUEST_INSTALL_PACKAGES, new PerssionCallback() {
            @Override
            public void onGranted() {
                _download(url);
            }
        });


    }


    private void _download(String url){
        ApkDownloadService.Builder.create(url)
                .setStoreDir(null)
                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                .build(mWXSDKInstance.getContext());
    }




}
