package com.farwolf.movie;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;

import com.farwolf.util.RegexBase;
import com.farwolf.util.StringUtil;
import com.farwolf.weex.activity.ActivityManager;
import com.farwolf.weex.bean.Config;
import com.farwolf.weex.event.RefreshEvent;
import com.farwolf.weex.pref.WeexPref_;
import com.farwolf.weex.util.Constants;
import com.farwolf.weex.util.HotRefreshManager;
import com.farwolf.weex.util.Weex;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.ypy.eventbus.EventBus;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by zhengjiangrong on 2017/5/18.
 */
@EApplication
public class MVApplication extends Application {

    private Handler mWXHandler;

    @Bean
    Weex weex;


    @Pref
    WeexPref_ pref;

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


        mWXHandler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case Constants.HOT_REFRESH_CONNECT:
                        HotRefreshManager.getInstance().connect(msg.obj.toString());
                        break;
                    case Constants.HOT_REFRESH_DISCONNECT:
                        reconnect(1000);
                        break;
                    case Constants.HOT_REFRESH_REFRESH:
//                        render(WeexActivity.this.url);
                        EventBus.getDefault().post(new RefreshEvent("refresh"));
                        break;
                    case Constants.HOT_REFRESH_CONNECT_ERROR:
                        reconnect(1000);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        registLifecycle();

        EventBus.getDefault().register(this);
         if(Config.debug(this))
        connect();
    }


    public void onEventMainThread(RefreshEvent event) {

        if("reconnect".equals(event.type))
        {
            connect();
        }



    }


    private void reconnect(final long time) {
        final Handler achandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                connect();

            }
        };
        achandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                achandler.sendEmptyMessage(0);
            }
        }, time);
    }

    void registLifecycle(){
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                ActivityManager.getInstance().setCurrentActivity(activity);

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    public void connect()
    {
        HotRefreshManager.getInstance().disConnect();
        HotRefreshManager.getInstance().setHandler(mWXHandler);
        String wsUrl = "ws://" + getIp()+ ":"+Config.socketPort(this);
//        mWXHandler.obtainMessage(Constants.HOT_REFRESH_CONNECT, 0, 0, wsUrl).sendToTarget();
        HotRefreshManager.getInstance().connect(wsUrl);
    }


    public String getIp()
    {
        String url= pref.url().get();
        String ipx= RegexBase.regexOne(url,"http://",":");
        if(!StringUtil.isNullOrEmpty(ipx))
        {
             return ipx;
        }
        else
        {
            return  Config.debugIp(this);
        }
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
