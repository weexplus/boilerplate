package com.farwolf.weex.module;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.os.Build;

import com.farwolf.interfac.IFullHttp;
import com.farwolf.perssion.Perssion;
import com.farwolf.perssion.PerssionCallback;
import com.farwolf.update.JsDownloader;
import com.farwolf.update.UpdateService;
import com.farwolf.update.UpdateService_;
import com.farwolf.util.Callback;
import com.farwolf.weex.bean.Config;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;


/**
 * Created by zhengjiangrong on 2018/3/22.
 */

public class WXUpdateModule extends WXModule {



    @JSMethod
    public void doCheck(HashMap param,final JSCallback callback)
    {
        String appid=param.get("appid")+"";
        boolean installNextOpen=Boolean.valueOf( param.get("installNextOpen")+"");
        String vcurl=param.get("url")+"";
        String theme=param.containsKey("theme")?param.get("theme")+"":null;
        boolean failtoast=param.containsKey("failtoast")?(boolean)param.get("failtoast"):false;
        boolean showprogress=param.containsKey("showprogress")?(boolean)param.get("showprogress"):false;
        UpdateService updateService= UpdateService_.getInstance_(mWXSDKInstance.getContext());
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
        com.farwolf.update.download.UpdateService up=new com.farwolf.update.download.UpdateService();

        if(!up.checkExistApk(mWXSDKInstance.getContext()))
        {
            callback.invoke(new HashMap());
        }

    }

    @JSMethod
    public void doCheckJs(HashMap param)
    {
        String appid=param.get("appid")+"";
        String vcurl=param.get("url")+"";
        String theme=param.containsKey("theme")?param.get("theme")+"":null;
        boolean failtoast=param.containsKey("failtoast")?(boolean)param.get("failtoast"):false;
        boolean showprogress=param.containsKey("showprogress")?(boolean)param.get("showprogress"):false;
        UpdateService updateService= UpdateService_.getInstance_(mWXSDKInstance.getContext());
        updateService.init(appid,vcurl,theme,false);
        updateService.doCheckJs(Config.jsVersion(mWXSDKInstance.getContext())+"",failtoast,showprogress);
    }


    @JSMethod
    public void hotUpdate(HashMap param ,final JSCallback start,final JSCallback progress,final JSCallback compelete,final JSCallback exception)
    {

        String url=param.get("url")+"";
        int version=0;
        if(param.containsKey("version")){
            version=Integer.parseInt( param.get("version")+"");
        }
        int mode=0;
        if(param.containsKey("mode")){
            mode=Integer.parseInt( param.get("mode")+"");
        }
        new JsDownloader().start(url,version,mode, mWXSDKInstance.getContext(), new IFullHttp() {
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


        if (Build.VERSION.SDK_INT >= 26) {
            Perssion.check((Activity) mWXSDKInstance.getContext(), Manifest.permission.REQUEST_INSTALL_PACKAGES,new PerssionCallback(){


                @Override
                public void onGranted() {

                    _download(url);



                }
            });
            return;
        }
        _download(url);


    }


    private void _download(String url){
        com.farwolf.update.download.UpdateService.Builder.create(url)
                .setStoreDir(null)
                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                .build(mWXSDKInstance.getContext());
    }




}
