package com.farwolf.weex.util;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.farwolf.base.ServiceBase;
import com.farwolf.util.FileTool;
import com.farwolf.weex.adapter.ExceptionAdapter;
import com.farwolf.weex.adapter.PicassoImageAdapter;
import com.farwolf.weex.adapter.display.DefaultWebSocketAdapterFactory;
import com.farwolf.weex.bean.Config;
import com.farwolf.weex.component.WXArc;
import com.farwolf.weex.component.WXDrawerLayout;
import com.farwolf.weex.component.WXFEmbed;
import com.farwolf.weex.component.WXFImage;
import com.farwolf.weex.component.WXFInput;
import com.farwolf.weex.component.WXFListComponent;
import com.farwolf.weex.component.WXFScrollView;
import com.farwolf.weex.component.WXFWeb;
import com.farwolf.weex.component.WXHost;
import com.farwolf.weex.component.WXItem;
import com.farwolf.weex.component.WXLoading;
import com.farwolf.weex.component.WXLooperText;
import com.farwolf.weex.component.WXPage;
import com.farwolf.weex.component.WXPreRender;
import com.farwolf.weex.component.WXWheelView;
import com.farwolf.weex.core.local.Local;
import com.farwolf.weex.module.WXAddressBookModule;
import com.farwolf.weex.module.WXCenterPopModule;
import com.farwolf.weex.module.WXEnvModule;
import com.farwolf.weex.module.WXEventModule;
import com.farwolf.weex.module.WXFPicker;
import com.farwolf.weex.module.WXFarwolfModule;
import com.farwolf.weex.module.WXFontModule;
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
import com.farwolf.weex.module.WXSlidpopModule;
import com.farwolf.weex.module.WXStaticModule;
import com.farwolf.weex.module.WXTimePicker;
import com.farwolf.weex.module.WXUpdateModule;
import com.farwolf.weex.pref.WeexPref_;
import com.farwolf.weex.view.CustomInsetsLinearLayout;
import com.lzy.okgo.OkGo;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXException;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.taobao.weex.utils.WXFileUtils;
import com.taobao.weex.utils.WXViewUtils;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.json.JSONObject;

import static com.taobao.weex.WXSDKEngine.registerComponent;

/**
 * Created by zhengjiangrong on 2017/5/8.
 */
@EBean
public class Weex extends ServiceBase{



    @Pref
    WeexPref_ pref;

    public static String basedir;
//    public static String baseurl;


    public   void startDebug(final String ip) {

        final Handler handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                startDebug(ip);
                return false;
            }
        });
        final DebugManager debugManager= DebugManager.getInstance();

        debugManager.setDebugListener(new DebugManager.DebugListener() {
            @Override
            public void onSuccess(String channelId) {

                WXEnvironment.sDebugServerConnectable = true;
//                WXEnvironment.sRemoteDebugMode = true;
                WXEnvironment.sRemoteDebugProxyUrl = "ws://" + ip + ":8088/debugProxy/native/"+channelId;
                WXSDKEngine.reload();
                HotRefreshManager.getInstance().send("open="+channelId);
//                DebugManager.getInstance().destory();
            }

            @Override
            public void onFail() {

//                debugManager.disConnect();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0);
                    }
                },2000);


            }
        });
        debugManager.connect(ip);



    }
    public   void startDebug(Context c) {
        startDebug(Config.debugIp(c));
    }

    public   String getDebugUrl()
    {
        return  "ws://" + pref.ip().get() + ":8088/debugProxy/native";
    }


    public static JSONObject config(Context c) {
        String s = WXFileUtils.loadAsset("app/config.json", c);

        try {
            JSONObject j = new JSONObject(s);
            return j;
        } catch (Exception var3) {
            return null;
        }
    }

    public void init(Application application, String name, String groupname,String basedir)
    {

        OkGo.init(application);
        Weex.basedir=basedir;
        WXSDKEngine.addCustomOptions("appName", name);
        WXSDKEngine.addCustomOptions("appGroup", groupname);
        WXBridgeManager.updateGlobalConfig("wson_on");
        WXSDKEngine.initialize(application,
                new InitConfig.Builder()
                        .setImgAdapter(new PicassoImageAdapter())
                        .setJSExceptionAdapter(new ExceptionAdapter())
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
            WXSDKEngine.registerModule("timepicker", WXTimePicker.class);
            WXSDKEngine.registerModule("location", WXLocationModule.class);
            WXSDKEngine.registerModule("env", WXEnvModule.class);

            registerComponent("image",WXFImage.class);
            registerComponent("web",WXFWeb.class);
            registerComponent(WXFListComponent.class, false, WXBasicComponentType.LIST,WXBasicComponentType.VLIST,WXBasicComponentType.RECYCLER,WXBasicComponentType.WATERFALL);
//            registerComponent("viewpager",WXViewPager.class);
            registerComponent("prerender",WXPreRender.class);
            registerComponent("page",WXPage.class);
            registerComponent("item",WXItem.class);
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



        } catch (WXException e) {
            e.printStackTrace();
        }
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
        String s= Local.getString(c,path);
        if(!s.startsWith("// { \"framework\": \"Vue\"}"))
            s="// { \"framework\": \"Vue\"}\n"+s;
        return s;
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
          return  getBaseUrl(instance.getBundleUrl());
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
                String res= x[0]+"//"+x[2]+"/"+Weex.basedir;
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






}
