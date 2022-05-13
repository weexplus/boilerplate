package com.weexplus.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.android.bindingx.plugin.weex.BindingX;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXException;
import com.taobao.weex.event.ErrorEvent;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.taobao.weex.ui.component.WXLoading;
import com.taobao.weex.utils.WXViewUtils;
import com.weexplus.adapter.ExceptionAdapter;
import com.weexplus.adapter.ImageAdapter;
import com.weexplus.adapter.WXHttpAdapter;
import com.weexplus.app.WxpApplication;
import com.weexplus.component.WXImage;
import com.weexplus.core.plugin.PluginManager;
import com.weexplus.debug.DefaultWebSocketAdapterFactory;
import com.weexplus.debug.HotRefreshManager;
import com.weexplus.event.RefreshEvent;
import com.weexplus.module.WXAddressBookModule;
import com.weexplus.module.WXDeviceModule;
import com.weexplus.module.WXFLogModule;
import com.weexplus.module.WXFontModule;
import com.weexplus.module.WXKeyboardModule;
import com.weexplus.module.WXLocationModule;
import com.weexplus.module.WXNavigatorModule;
import com.weexplus.module.WXNetModule;
import com.weexplus.module.WXNotifyModule;
import com.weexplus.module.WXPageModule;
import com.weexplus.module.WXPrefModule;
import com.weexplus.module.WXProgressModule;
import com.weexplus.module.WXQRModule;
import com.weexplus.module.WXStaticModule;
import com.weexplus.module.WXUpdateModule;
import com.weexplus.util.ActivityManager;
import com.weexplus.util.Config;
import com.weexplus.util.Const;
import com.weexplus.util.DateTool;
import com.weexplus.util.Pref;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

import static com.taobao.weex.WXSDKEngine.registerComponent;

public class WeexPlus {

   static  WeexPlus instance;
    public Handler mWXHandler;

    public  final static String BRAND="weexplus";
    long lastrefresh=0;

    public static void init(Application application){

        if(instance==null){
            instance=new WeexPlus();
        }
        initOkGo(application);
        WXSDKEngine.addCustomOptions("appName", "weexplus");
        WXSDKEngine.addCustomOptions("appGroup", "weexplus");
        WXBridgeManager.updateGlobalConfig("wson_on");
        WXSDKEngine.initialize(application,
                new InitConfig.Builder()
                        .setImgAdapter(new ImageAdapter())
                        .setJSExceptionAdapter(new ExceptionAdapter())
                        .setHttpAdapter(new WXHttpAdapter())
                        .setWebSocketAdapterFactory(new DefaultWebSocketAdapterFactory())
                        .build());
        registLifecycle(application);
        if(Config.debug(application)){
            instance.registRefresh(application);
            instance.connect();
        }

        EventBus.getDefault().register(instance);
        try {
            BindingX.register();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        regist();
        PluginManager.init(application);
    }

    public static void initOkGo(Application app){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
//log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
//log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
//全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
//全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(app)));
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        OkGo.getInstance().init(app)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0);                      //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0



    }

    public static void registLifecycle(Application app){
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                  Log.e("","");
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




    public    void connect( )
    {
        HotRefreshManager.getInstance().disConnect();
        HotRefreshManager.getInstance().setHandler(mWXHandler);
        String ip= Pref.getString( WxpApplication.getApplication(),"ip","");
        String port= Pref.getString( WxpApplication.getApplication(),"socketPort","9897");
        String wsUrl = "ws://" + ip+ ":"+port;
        HotRefreshManager.getInstance().connect(wsUrl);
    }

    public   void registRefresh(Application app){

       this.mWXHandler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case Const.HOT_REFRESH_CONNECT:
                        HotRefreshManager.getInstance().connect(msg.obj.toString());
                        break;
                    case Const.HOT_REFRESH_DISCONNECT:
                        reconnect(1000);
                        break;
                    case Const.HOT_REFRESH_REFRESH:
                        long now=new Date().getTime();
                        if(now-lastrefresh>300)
                        {
                            Log.i("refresh",now-lastrefresh+"");
                            EventBus.getDefault().post(new RefreshEvent("refresh"));
                            lastrefresh=now;
                        }
                        break;
                    case Const.HOT_REFRESH_CONNECT_ERROR:
                        reconnect(1000);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });




    }


    public static float toNative(float length)
    {
        return  WXViewUtils.getRealPxByWidth(length);
    }

    public static float toWeex(float length)
    {
        return  WXViewUtils.getWeexPxByReal(length);
    }


    public static void regist(){
        try {
            WXSDKEngine.registerModule("log", WXFLogModule.class);
            WXSDKEngine.registerModule("font", WXFontModule.class);
            WXSDKEngine.registerModule("net", WXNetModule.class);
            WXSDKEngine.registerModule("notify", WXNotifyModule.class);
            WXSDKEngine.registerModule("qr", WXQRModule.class);
            WXSDKEngine.registerModule("pref", WXPrefModule.class);
            WXSDKEngine.registerModule("static", WXStaticModule.class);
            WXSDKEngine.registerModule("addressBook", WXAddressBookModule.class);
            WXSDKEngine.registerModule("device", WXDeviceModule.class);
            WXSDKEngine.registerModule("navigator", WXNavigatorModule.class);
            WXSDKEngine.registerModule("location", WXLocationModule.class);
            WXSDKEngine.registerModule("keyboard", WXKeyboardModule.class);
            WXSDKEngine.registerModule("page", WXPageModule.class);
            WXSDKEngine.registerModule("progress", WXProgressModule.class);
            WXSDKEngine.registerModule("update", WXUpdateModule.class);
            WXSDKEngine.registerComponent("image", WXImage.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(RefreshEvent event) {

        if("reconnect".equals(event.type))
        {
            connect();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ErrorEvent event) {

        if("log".equals(event.type))
        {
            String msg=DateTool.Now()+"     "+event.msg;
            String level=event.level;
            HotRefreshManager.getInstance().send("log:"+level+"level:"+msg);
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



}
