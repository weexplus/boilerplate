package com.weexplus.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.StrictMode;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.weexplus.core.WeexPlus;
import com.weexplus.core.render.Local;
import com.weexplus.util.Config;
import com.weexplus.util.Const;

public class WxpApplication extends MultiDexApplication {




    static WxpApplication application;

    Handler handler;


    public static WxpApplication getApplication() {
        return application;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApp(this);
    }

    public void initApp(Application instance){
        application=this;
        Local.copyAssetToDisk(this, Const.MAIN);
        WeexPlus.init(this);
        WeexPlus.registLifecycle(this);
    }



}
