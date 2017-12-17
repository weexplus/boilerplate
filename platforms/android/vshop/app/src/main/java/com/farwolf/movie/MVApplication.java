package com.farwolf.movie;

import android.app.Application;
import android.os.StrictMode;

import com.farwolf.weex.bean.Config;
import com.farwolf.weex.util.Weex;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;

/**
 * Created by zhengjiangrong on 2017/5/18.
 */
@EApplication
public class MVApplication extends Application {


    @Bean
    Weex weex;

    @Override
    public void onCreate() {
        super.onCreate();
        initUnivsalImageloader();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        String schema= Config.schema(this);
        if(schema==null)
            schema="";
        weex.init(this,"网络设计平台","farwolf",schema);



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
