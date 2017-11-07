package com.farwolf.vshop;

import android.app.Application;
import android.os.StrictMode;

import com.farwolf.movie.R;
import com.farwolf.vshop.module.TabModule;
import com.farwolf.weex.module.WXStaticModule;
import com.farwolf.weex.util.Weex;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;

/**
 * Created by zhengjiangrong on 2017/5/18.
 */
@EApplication
public class VSApplication extends Application {


    @Bean
    Weex weex;

    @Override
    public void onCreate() {
        super.onCreate();
        initUnivsalImageloader();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
//        Weex.basedir="mv";
        String schema=Weex.config(this).optString("schema");
        if(schema==null)
            schema="";
        weex.init(this,"网络设计平台","farwolf",schema);
        try {
            WXSDKEngine.registerModule("tabbar", TabModule.class);

        } catch (WXException e) {
            e.printStackTrace();
        }
//        weex.startDebug("192.168.253.22");
//        TabModule

    }


    public static boolean isLogin()
    {
        WXStaticModule s=new WXStaticModule();
        return  s.get("user")!=null;

    }


    void initUnivsalImageloader()
    {
        DisplayImageOptions defaultOptions = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.drawable.bg_gf_crop_texture)
                .showImageOnFail(R.drawable.bg_gf_crop_texture)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .discCacheSize(50 * 1024 * 1024)//

                .writeDebugLogs()
                .build();
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config);
    }
}
