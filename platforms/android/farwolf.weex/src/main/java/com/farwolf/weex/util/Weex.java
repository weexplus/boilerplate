package com.farwolf.weex.util;

import android.app.AppOpsManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.farwolf.base.ServiceBase;
import com.farwolf.util.FileTool;
import com.farwolf.util.Md5;
import com.farwolf.weex.adapter.DrawableLoader;
import com.farwolf.weex.adapter.ExceptionAdapter;
import com.farwolf.weex.adapter.PicassoImageAdapter;
import com.farwolf.weex.adapter.UriAdapter;
import com.farwolf.weex.adapter.WXHttpAdapter;
import com.farwolf.weex.adapter.display.DefaultWebSocketAdapterFactory;
import com.farwolf.weex.bean.Config;
import com.farwolf.weex.component.WXArc;
import com.farwolf.weex.component.WXChild;
import com.farwolf.weex.component.WXDrawerLayout;
import com.farwolf.weex.component.WXFEmbed;
import com.farwolf.weex.component.WXFImage;
import com.farwolf.weex.component.WXFInput;
import com.farwolf.weex.component.WXFListComponent;
import com.farwolf.weex.component.WXFScrollView;
import com.farwolf.weex.component.WXFVideo;
import com.farwolf.weex.component.WXFWeb;
import com.farwolf.weex.component.WXHost;
import com.farwolf.weex.component.WXItem;
import com.farwolf.weex.component.WXLoading;
import com.farwolf.weex.component.WXLooperText;
import com.farwolf.weex.component.WXPage;
import com.farwolf.weex.component.WXPreRender;
import com.farwolf.weex.component.WXSlidComponent;
import com.farwolf.weex.component.WXWheelView;
import com.farwolf.weex.core.PluginManager;
import com.farwolf.weex.core.local.Local;
import com.farwolf.weex.floatwindow.FloatWindow;
import com.farwolf.weex.floatwindow.Screen;
import com.farwolf.weex.module.WXAddressBookModule;
import com.farwolf.weex.module.WXCenterPopModule;
import com.farwolf.weex.module.WXDeviceModule;
import com.farwolf.weex.module.WXEnvModule;
import com.farwolf.weex.module.WXEventModule;
import com.farwolf.weex.module.WXFLogModule;
import com.farwolf.weex.module.WXFPicker;
import com.farwolf.weex.module.WXFarwolfModule;
import com.farwolf.weex.module.WXFileModule;
import com.farwolf.weex.module.WXFontModule;
import com.farwolf.weex.module.WXKeyboardModule;
import com.farwolf.weex.module.WXLocationModule;
import com.farwolf.weex.module.WXNavBarModule;
import com.farwolf.weex.module.WXNavgationModule;
import com.farwolf.weex.module.WXNetModule;
import com.farwolf.weex.module.WXNotifyModule;
import com.farwolf.weex.module.WXPageModule;
import com.farwolf.weex.module.WXPhotoModule;
import com.farwolf.weex.module.WXPrefModule;
import com.farwolf.weex.module.WXProgressModule;
import com.farwolf.weex.module.WXQRModule;
import com.farwolf.weex.module.WXRsaModule;
import com.farwolf.weex.module.WXSlidpopModule;
import com.farwolf.weex.module.WXStaticModule;
import com.farwolf.weex.module.WXUpdateModule;
import com.farwolf.weex.pref.WeexPref_;
import com.farwolf.weex.view.CustomInsetsLinearLayout;
import com.farwolf.weex.view.ToolView;
import com.farwolf.weex.view.ToolView_;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXException;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXFileUtils;
import com.taobao.weex.utils.WXViewUtils;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

import static com.taobao.weex.WXSDKEngine.registerComponent;

/**
 * Created by zhengjiangrong on 2017/5/8.
 */
@EBean
public class Weex extends ServiceBase{



    @Pref
    WeexPref_ pref;

    public static String basedir;


    public static Map navDic;
    public static String router;

//    public static String baseurl;


    public   void startDebug(final String ip) {



        HotRefreshManager.getInstance().send("opendebug");

        final Handler achandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);


            }
        };
//        achandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                WXEnvironment.sDebugServerConnectable = true;
//                WXEnvironment.sRemoteDebugMode = true;
//                WXEnvironment.sRemoteDebugProxyUrl = "ws://" + ip + ":8089/debugProxy/native/123456";
//                WXSDKEngine.reload();
//            }
//        }, 8000);


    }
    public   void startDebug(Context c) {
        startDebug(Config.debugIp(c));
    }

    public   String getDebugUrl()
    {
        return  "ws://" + pref.ip().get() + ":8088/debugProxy/native";
    }


    public static JSONObject config(Context c) {
        String s = WXFileUtils.loadAsset("app/weexplus.json", c);

        try {
            JSONObject j = new JSONObject(s);
            return j;
        } catch (Exception var3) {
            return null;
        }
    }

    public void init(Application application, String name, String groupname,String basedir)
    {

//        OkGo.init(application);
        initOkGo(application);
        Weex.basedir=basedir;
        WXSDKEngine.addCustomOptions("appName", name);
        WXSDKEngine.addCustomOptions("appGroup", groupname);
        WXBridgeManager.updateGlobalConfig("wson_on");
        WXSDKEngine.initialize(application,
                new InitConfig.Builder()
                        .setImgAdapter(new PicassoImageAdapter())
                        .setDrawableLoader(new DrawableLoader())
                        .setJSExceptionAdapter(new ExceptionAdapter())
                        .setHttpAdapter(new WXHttpAdapter())
                        .setURIAdapter(new UriAdapter())
                        .setWebSocketAdapterFactory(new DefaultWebSocketAdapterFactory())
                        .build());
        try {
            WXSDKEngine.registerModule("event", WXEventModule.class);
            WXSDKEngine.registerModule("navbar", WXNavBarModule.class);
            WXSDKEngine.registerModule("navigator", WXNavgationModule.class);
            WXSDKEngine.registerModule("notify", WXNotifyModule.class);
            WXSDKEngine.registerModule("net", WXNetModule.class);
            WXSDKEngine.registerModule("photo", WXPhotoModule.class);
            WXSDKEngine.registerModule("static", WXStaticModule.class);
            WXSDKEngine.registerModule("farwolf", WXFarwolfModule.class);
            WXSDKEngine.registerModule("pref", WXPrefModule.class);
            WXSDKEngine.registerModule("fpicker", WXFPicker.class);
            WXSDKEngine.registerModule("progress", WXProgressModule.class);
            WXSDKEngine.registerModule("qr", WXQRModule.class);
            WXSDKEngine.registerModule("addressBook", WXAddressBookModule.class);
            WXSDKEngine.registerModule("slidpop", WXSlidpopModule.class);
            WXSDKEngine.registerModule("centerpop", WXCenterPopModule.class);
            WXSDKEngine.registerModule("page", WXPageModule.class);
            WXSDKEngine.registerModule("font", WXFontModule.class);
            WXSDKEngine.registerModule("updater", WXUpdateModule.class);
//            WXSDKEngine.registerModule("timepicker", WXTimePicker.class);
            WXSDKEngine.registerModule("location", WXLocationModule.class);
            WXSDKEngine.registerModule("env", WXEnvModule.class);
            WXSDKEngine.registerModule("file", WXFileModule.class);
            WXSDKEngine.registerModule("device", WXDeviceModule.class);
            WXSDKEngine.registerModule("rsa", WXRsaModule.class);
            WXSDKEngine.registerModule("keyboard", WXKeyboardModule.class);
            WXSDKEngine.registerModule("log", WXFLogModule.class);


            registerComponent("image",WXFImage.class);
            registerComponent("web",WXFWeb.class);
            registerComponent(WXFListComponent.class, false, WXBasicComponentType.LIST,WXBasicComponentType.VLIST,WXBasicComponentType.RECYCLER,WXBasicComponentType.WATERFALL);
//            registerComponent("viewpager",WXViewPager.class);
            registerComponent("prerender",WXPreRender.class);
            registerComponent("page",WXPage.class);
            registerComponent("item",WXItem.class);
            registerComponent("slid",WXSlidComponent.class);
            registerComponent("child",WXChild.class);
            registerComponent("floading",WXLoading.class);
            registerComponent("fscroller",WXFScrollView.class);
            registerComponent("embed",WXFEmbed.class);
            registerComponent("a",com.farwolf.weex.component.WXA.class);
            registerComponent("wheel",WXWheelView.class);
            registerComponent("host",WXHost.class);
            registerComponent("looper",WXLooperText.class);
            registerComponent("drawerlayout",WXDrawerLayout.class);
            registerComponent("input",WXFInput.class);
            registerComponent("arc",WXArc.class);
            registerComponent("video",WXFVideo.class);

            PluginManager.init(application);
//            initDebugger(application);

        } catch (WXException e) {
            e.printStackTrace();
        }
    }
    public void initDebugger(final Application application){

        if(!Config.debug(application)){
            return;
        }
        if(!isPermissionGranted(application,"24")){
            Toast.makeText(application,"请同意 显示悬浮窗 权限",Toast.LENGTH_LONG).show();
        }
        ToolView a= ToolView_.build(application);
        FloatWindow
                .with(application)
                .setView(a)
                .setX(0)                                   //设置控件初始位置
                .setY(Screen.height/2)
                .setDesktopShow(false)                        //桌面显示
                .build();
        FloatWindow.get().show();

    }



    private   boolean isPermissionGranted(Application app,String permissionCode) {
        try {

            Object object = app.getSystemService(Context.APP_OPS_SERVICE);
            if (object == null) {
                return false;
            }
            Class localClass = object.getClass();
            Class[] arrayOfClass = new Class[3];
            arrayOfClass[0] = Integer.TYPE;
            arrayOfClass[1] = Integer.TYPE;
            arrayOfClass[2] = String.class;
            Method method = localClass.getMethod("checkOp", arrayOfClass);

            if (method == null) {
                return false;
            }
            Object[] arrayOfObject = new Object[3];
            arrayOfObject[0] = Integer.valueOf(permissionCode);
            arrayOfObject[1] = Integer.valueOf(Binder.getCallingUid());
            arrayOfObject[2] = app.getPackageName();
            int m = ((Integer) method.invoke(object, arrayOfObject)).intValue();
            return m == AppOpsManager.MODE_ALLOWED;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void downloadImg(String url, ImageView img, Context context)
    {

        if(url==null)
            return;
        if(url.startsWith("http"))
        {
            Glide.with(context).load(url).into(img);
        }
        else
        {
            if(url.startsWith("/"))
            {
                url=url.substring(1);
            }
            Bitmap bm= FileTool.loadAssetImage(url,context);
            img.setImageBitmap(bm);
        }
    }


    public static String loadLocal(String path, Context c)
    {
//        String px= SDCard.getBasePath(c);
//        String s= WXFileUtils.loadAsset(path, c);
        String appboard=loadAppboard(c);
        String s= Local.getString(c,path);
        if(!s.startsWith("// { \"framework\": \"Vue\"}"))
            s="// { \"framework\": \"Vue\"}\n"+s;
        return appboard+s;
    }

    public static String loadAppboard(Context c)
    {
        String path=Config.appBoard(c);
        path=getLocalRootPath(path);
        String s= Local.getString(c,path);
        return s;
    }


    public static String getLocalRootPath(String path)
    {
        path=path.replace("root:","app/");
        return path;
    }



    public static boolean hasLoad(View v)
    {
        if(v==null)
            return  false;
        ViewParent parent=v.getParent();
        while(parent!=null)
        {
            if((parent instanceof CustomInsetsLinearLayout))
            {
                return true;
            }
            parent=parent.getParent();
        }
        return false;

    }


    public static String getBaseUrl(WXSDKInstance  instance)
    {
        if(instance==null)
            return "";
        return  getBaseUrl(instance.getBundleUrl()).trim();
    }


    public static String getBaseUrl(String  url)
    {

        String baseurl="";
        String s= url;

        if(s.startsWith("http"))
        {
            String x[]=url.split("\\/");

            if(x.length>3)
            {
                String port=x[1];
                if("".equals(port))
                port=x[2];
                String res= x[0]+"//"+port+"/"+Weex.basedir;
                if(!res.endsWith("/"))
                    res+="/";
                baseurl=res;
            }
        }
        else
        {
            baseurl="app/";

        }
        return baseurl;
    }

    public static float length(float length)
    {
        return  WXViewUtils.getRealPxByWidth(length);
    }

    public static float deLength(float length)
    {
        return  WXViewUtils.getWeexPxByReal(length);
    }


//    public static String getUrl(String url)
//    {
//        if(url.startsWith("root:"))
//            return   url.replace("root:",Weex.baseurl);
//
//    }


//    public static String getRelativeUrl(String url)
//    {
//        String temp="";
//        if(url.contains("root:"))
//        {
//            String q[]=url.split("root:");
//            temp= Weex.baseurl+q[1];
//        }
//        else
//        {
//            temp=Weex.getSingleRealUrl(url);
//        }
//        return temp;
//    }

    public static String getSingleRealUrl(String url)
    {

        if(url.startsWith("./"))
        {
            url=url.substring(2);
        }
        if(url.startsWith("/"))
        {
            url=url.substring(1);
        }
        if(url.contains("/./"))
        {
            url=url.replace("/./","/");
        }

        String q[]=url.split("\\.\\.\\/");
        String x[]= q[0].split("\\/");
        if(q.length==1)
            return q[0];
        String p="";
        if(x.length>=q.length-1)
        {
            for(int i=0;i<x.length-q.length+1;i++)
            {
                p+=x[i]+"/";
            }
        }
        p+=q[q.length-1];
        return p;
    }


    public static String getRelativeUrl(String url, WXSDKInstance  instance)
    {

        if(url.startsWith("sdcard:"))
        {
            return url;
        }
        if(url.startsWith("http"))
        {
            return url;
        }
        if(url.startsWith("root:"))
        {
            return url.replace("root:", getBaseUrl(instance));
        }

        if(url.startsWith("./"))
        {
            url=url.substring(2);
        }
        String base= instance.getBundleUrl();
        String q[]=url.split("\\.\\.\\/");
        String x[]= base.split("\\/");

        String p="";

        for(int i=0;i<x.length-q.length;i++)
        {
            p+=x[i]+"/";
        }

        p+=q[q.length-1];
        return p;
    }

    public static String getRootUrl(String url, WXSDKInstance  instance)
    {

        if(url.contains("root:"))
        {
            String q[]=url.split("root:");

            if(instance.getBundleUrl().startsWith("http"))
            {
                String x[]=instance.getBundleUrl().split("\\/");

                if(x.length>3)
                {
                    String res= x[0]+"//"+x[2]+"/"+Weex.basedir;
                    if(!res.endsWith("/"))
                        res+="/";
                    url=res+url.replace("root:","");

                }
            }
            else
            {
                url="app/"+q[1];
            }
            return url;
        }


        return url;


    }

    void initOkGo(Application app){

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






    public static String  getSdcardPath(String path)
    {
        return Const.PREFIX_SDCARD+path;
    }





    public static void fireChildEvent(WXComponent c, String name, Map param){
        c.fireEvent(name,param);
        if(c instanceof  WXVContainer) {
            WXVContainer cv=(WXVContainer)c;
            for(int i=0;i<cv.getChildCount();i++){
                WXComponent cx= cv.getChild(i);
                Weex.fireChildEvent(cx,name,param);
            }
        }

    }

    public static String getRootPath(String path,WXSDKInstance instance){
        path=getRootUrl(path,instance);
        if(path.startsWith("http")){
            return path;
        }
        else{
            String p= instance.getContext().getExternalFilesDir("Caches")+"/"+path;
            return p;
        }
    }


    public  static String appBoardContent="";
    public static String webAppboardMd5()
    {
//        String px= SDCard.getBasePath(c);
//        String s= WXFileUtils.loadAsset(path, c);
        String appboard=appBoardContent+"";
        return Md5.toMd5(appboard);
    }

    public static void setWebAppboard(String s)
    {
        appBoardContent=s;
    }


    public static  Map getNavDic(Context c){
        if(appBoardContent.contains("weexplus_split_router_weexplus")){
            String appaboard=appBoardContent.split("\\/\\*\\*\\*\\*\\*\\*\\*weexplus_split_router_weexplus\\*\\*\\*\\*\\*\\*\\/")[0];
            if(navDic==null ||  !(router+"").equals(appaboard)){
                router=appaboard;
                String q[]= appaboard.split("\n");
                String router= q[q.length-1];
                router=router.substring(3,router.length());
                navDic= com.alibaba.fastjson.JSONObject.parseObject(router);
            }
            return navDic;
        }else{
            if(navDic==null  ){
                navDic= Config.routerTranlater(c);
            }
            return navDic;
        }

    }

    public static String getTranslatePath(String url,Context c){
        Map  m=getNavDic(c);
        if(m.containsKey(url)){
            return m.get(url)+"";
        }
        return url;
    }


}
