package com.farwolf.weex.module;

import android.app.Notification;

import com.farwolf.interfac.IFullHttp;
import com.farwolf.update.JsDownloader;
import com.farwolf.update.UpdateService;
import com.farwolf.update.UpdateService_;
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
    public void doCheck(HashMap param)
    {
        String appid=param.get("appid")+"";
        String vcurl=param.get("url")+"";
        String theme=param.containsKey("theme")?param.get("theme")+"":null;
        boolean failtoast=param.containsKey("failtoast")?(boolean)param.get("failtoast"):false;
        boolean showprogress=param.containsKey("showprogress")?(boolean)param.get("showprogress"):false;
        UpdateService updateService= UpdateService_.getInstance_(mWXSDKInstance.getContext());
        updateService.init(appid,vcurl,theme);
        updateService.doCheck(failtoast,showprogress);
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
        updateService.init(appid,vcurl,theme);
        updateService.doCheckJs(Config.jsVersion(mWXSDKInstance.getContext())+"",failtoast,showprogress);
    }


    @JSMethod
    public void hotUpdate(String url,final JSCallback start,final JSCallback progress,final JSCallback compelete,final JSCallback exception)
    {
        new JsDownloader().start(url, mWXSDKInstance.getContext(), new IFullHttp() {
            @Override
            public void OnPostProcess(int newProgress) {
                if(progress!=null)
                    progress.invoke(newProgress);
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
    public void download(String url)
    {
        com.farwolf.update.download.UpdateService.Builder.create(url)
                .setStoreDir(null)
                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                .build(mWXSDKInstance.getContext());

    }


}
