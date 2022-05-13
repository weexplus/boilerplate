package com.weexplus.update;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.JsonReader;
import android.widget.Toast;


import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.weexplus.util.AppMainfest;
import com.weexplus.util.Callback;
import com.weexplus.util.Pref;
import com.weexplus.util.SDCard;
import com.weexplus.view.FreeDialog;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by zhengjiangrong on 2017/4/12.
 */

public class UpdateService {


    AppMainfest appMainfest;




    Context context;

    String appid;

    String vcurl;

    String theme;
    boolean installNextOpen;

    public void init(String appid,String vcurl,String theme,boolean installNextOpen)
    {
        this.appid=appid;
        this.vcurl=vcurl;
        this.theme=theme;
        this.installNextOpen=installNextOpen;

    }

    public void doCheck(final boolean failtoast, final boolean showprogress, final Callback callback)
    {
        Request req = OkGo.<String>post(vcurl);
        req.params("appid",appid);
        req.params("systype",1);
        req.params("vcode",appMainfest.getVersionCode());

        req.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                String hs = response.body();
                JSONObject jb=com.alibaba.fastjson.JSONObject.parseObject(hs);
                UpdateDialog a= new UpdateDialog(context);
                FreeDialog f=new FreeDialog(context,a);
                String version= Pref.getString(context,"version","");
                long time= Pref.getInt(context,"time",0);
                JSONObject ver=jb.getJSONObject("version");
                Version v= new Version();
                v.level=ver.getInteger("level");
                v.version_name=ver.getString("version_name");
                a.f=f;
                f.setCanceledOnTouchOutside(false);
                f.setCancelable(v.level!=2);
                a.setSilent(installNextOpen);
                a.init(v,theme);
                if(v.level==0)
                {
                    if(v.version_name!=null&&v.version_name.equals(version))
                    {
                        long delt= System.currentTimeMillis()-time;
                        long week=1000*60*60*24*7;
                        long min=1000*20;
                        if(delt<min)
                        {
                            return;
                        }
                        else
                        {
                            Pref.setString(context,"version","");
                            Pref.setLong(context,"time",0);
                            f.show();
                        }
                    }
                    else
                    {
                        f.show();
                    }
                }
                else
                {
                    f.show();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });





    }



    public void doCheckJs(String jsversion,final String module,final boolean showprogress)
    {



        Request req = OkGo.<String>post(vcurl);
        req.params("appid",appid);
        req.params("systype",1);
        req.params("jsversion",jsversion);
        req.params("nativeversion",appMainfest.getVersionCode());

        req.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                String hs = response.body();
                JSONObject jb=com.alibaba.fastjson.JSONObject.parseObject(hs);
                JSONObject version=jb.getJSONObject("version");
                JsVersion v=  new JsVersion();
                v.mode=version.getInteger("mode");
                v.url=version.getString("url");
                v.size=version.getString("size");
                v.js_version=version.getInteger("js_version");

                if(v.mode==1) {
                    UpdateJsDialog a = new UpdateJsDialog(context);
                    FreeDialog f = new FreeDialog(context, a);
                    a.f = f;
                    f.setCanceledOnTouchOutside(false);
                    f.setCancelable(false);
                    a.url = v.url;
                    a.size = v.size;
                    a.version = v.js_version;
                    a.path = SDCard.getBasePath(context) + "zip/app.zip";
                    f.show();
                    a.start();
                    return;
                }


                new JsDownloader().start(v.url, v.js_version, v.mode,module,context, null);


            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });



    }




    public  File getFileFromServer(String path, ProgressDialog pd) throws Exception{
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的

        URL url = new URL(path);
        HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        //获取到文件的大小
        pd.setMax(conn.getContentLength());
        InputStream is = conn.getInputStream();
        File file = new File(SDCard.getBasePath(context), "updata.apk");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] buffer = new byte[1024];
        int len ;
        int total=0;
        while((len =bis.read(buffer))!=-1){
            fos.write(buffer, 0, len);
            total+= len;
            //获取当前下载量
            pd.setProgress(total);
        }
        fos.close();
        bis.close();
        is.close();
        return file;

    }






}
