package com.farwolf.weex.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.farwolf.base.TitleActivityBase;
import com.farwolf.util.ActivityManager;
import com.farwolf.util.AppTool;
import com.farwolf.util.DateTool;
import com.farwolf.util.KeyBoardTool;
import com.farwolf.util.ScreenTool;
import com.farwolf.util.StatusBar;
import com.farwolf.util.StringUtil;
import com.farwolf.view.FreeDialog;
import com.farwolf.weex.R;
import com.farwolf.weex.bean.Config;
import com.farwolf.weex.core.Page;
import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.event.KeyboardEvent;
import com.farwolf.weex.event.PermissionEvent;
import com.farwolf.weex.event.RefreshEvent;
import com.farwolf.weex.listener.RenderListener;
import com.farwolf.weex.module.WXNavgationModule;
import com.farwolf.weex.module.WXStaticModule;
import com.farwolf.weex.pref.WeexPref_;
import com.farwolf.weex.util.HotRefreshManager;
import com.farwolf.weex.util.Weex;
import com.farwolf.weex.view.ToolPop;
import com.farwolf.weex.view.ToolPop_;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.event.ErrorEvent;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengjiangrong on 2017/5/8.
 */
@EActivity
public class WeexActivity extends TitleActivityBase implements IWXRenderListener {




    public WXSDKInstance mWXSDKInstance;
    @ViewById
    public  RelativeLayout mask;
    @ViewById
    public TextView err;
    @ViewById
    public ViewGroup err_layout;
    @ViewById
    public ImageView lodingimg;
    @ViewById
    public RelativeLayout fail_layout;

    private BroadcastReceiver mReceiver;

    public boolean isRoot =false;
    @Bean
    public ScreenTool screenTool;


    public JSCallback backkeyCallback;


    public String url;
    public static String statuBarColor;


    @ViewById
    public  View   rootContainer;

    @ViewById
    public ViewGroup container;


    public boolean isFinish;


    @ViewById
    public ViewGroup root;

    @Pref
    public WeexPref_ pref;

    @Bean
    public WeexFactory weexFactory;

    private BroadcastReceiver mReloadReceiver;

    @Bean
    public Weex weex;


    public  boolean exitEnable;
    public  boolean backKeyEnable=true;


    public Map param;


    public String pageid;

    public String rootid;


    @Bean
    public AppTool apptool;


    public boolean isPageInit=false;



    public List<RenderListener> renderListeners=new ArrayList<>();



    @Override
    public int getViewId() {
        return R.layout.api_weex_activity;
    }





    boolean hasInit=false;


    @Override
    protected void onCreate(Bundle arg0) {
        if(arg0!=null)
        {
            String url= arg0.getString("url");
            if(url!=null)
            {
                this.url=url;
            }
            HashMap m=(HashMap) arg0.getSerializable("static");
            if(m!=null)
            {
                WXStaticModule.m=m;
            }
        }

        super.onCreate(arg0);
        if(statuBarColor!=null){
          StatusBar.setStatusBarStyle(statuBarColor,this);
        }
//        mReloadReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                reload();
//            }
//        };
//        LocalBroadcastManager.getInstance(this).registerReceiver(mReloadReceiver, new IntentFilter(WXSDKEngine.JS_FRAMEWORK_RELOAD));

    }




    public void setKeyboardListener(){



        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){

            //当键盘弹出隐藏的时候会 调用此方法。
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //获取当前界面可视部分
                WeexActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight =  WeexActivity.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = KeyBoardTool.getKeyboardHeight(WeexActivity.this);
                heightDifference=(int) WXViewUtils.getWeexPxByReal(heightDifference);
                EventBus.getDefault().post(new KeyboardEvent(heightDifference));
                Log.d("Keyboard Size", "Size: " + heightDifference);
            }

        });

    }





    public void addRenderListener(RenderListener l)
    {

        renderListeners.add(l);
    }

    public void invokeRenderListener()
    {
        HashMap m=(HashMap) getIntent().getSerializableExtra("param");
        for(RenderListener r:renderListeners)
        {
            r.onRenderSuccess(m);
        }
    }

    public void resetFrame()
    {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);

        rootContainer.setLayoutParams(lp);


        makeHidden();

    }

    @AfterViews
    public void initWeexActivity()
    {
        if(hasInit)
            return;

        this.setKeyboardListener();
        if(lodingimg==null)
            return;
        Glide.with(this)
                .asGif()
                .load(R.drawable.load)
                .into(lodingimg);

        hasInit=true;
        String url=getIntent().getStringExtra("url");

        this.isRoot = getIntent().getBooleanExtra("isRoot",false);
        this.param=(Map) getIntent().getSerializableExtra("param");
        this.rootid= getIntent().getStringExtra("rootid");
        WXNavgationModule.addActivity(this.rootid,this);
//        AndroidBug5497Workaround.assistActivity(root);
        this.resetFrame();
        this.render(url);

        if(mReceiver!=null)
        {
            unregisterReceiver(mReceiver);
        }


        EventBus.getDefault().register(this);

        init();

    }


    public void init(){

    }





    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState!=null)
        {
            outState.putString("url",this.url);
            outState.putSerializable("static", WXStaticModule.m);
        }

    }

    public void showLoading()
    {


        lodingimg.setVisibility(View.VISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(RefreshEvent event) {

        if( ActivityManager.getInstance().getCurrentActivity()==this&&"refresh".equals(event.type))
        {
            this.render(this.url,false);
        }



    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ErrorEvent event) {

        if( ActivityManager.getInstance().getCurrentActivity()==this)
        {

            if("log".equals(event.type))
            this.showError(event.msg);
        }



    }


    public void hideLoading()
    {

        lodingimg.setVisibility(View.GONE);
    }


    public void makeTransparent()
    {
        getTitleBar().layout.setBackgroundColor(Color.TRANSPARENT);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(0, 0, 0, 0);
        this.rootContainer.setLayoutParams(lp);
    }



    void makeHidden()
    {
        getTitleBar().setVisibility(View.GONE);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(0, 0, 0, 0);
        this.rootContainer.setLayoutParams(lp);
        ViewGroup.LayoutParams lpx= this.rootContainer.getLayoutParams();
        if(mWXSDKInstance!=null)
            mWXSDKInstance.setSize(lpx.width,lpx.height);
    }

    public void render(String url,boolean showProgress)
    {

        if(url==null)
            return;
        this.isPageInit=false;

        Page page= weexFactory.getPage(url);
        if(page!=null)
        {
            if(page.v==null)
                return;
            RelativeLayout parent=  (RelativeLayout)page.v.getParent();
            if(parent!=null)
                parent.removeAllViews();
            container.addView(page.v);
            url=page.instance.getBundleUrl();
            mWXSDKInstance= page.instance;
            pageid=page.id;
            this.url=url;

            mWXSDKInstance.setContext(this);
            mWXSDKInstance.registerRenderListener(this);
            mWXSDKInstance.onActivityCreate();
            mWXSDKInstance.param=(Map) getIntent().getSerializableExtra("param");
//            mWXSDKInstance.fireGlobalEventCallback("onPageInit",mWXSDKInstance.param);
            mWXSDKInstance.firePageInit();
            this.isPageInit=true;
            this.invokeRenderListener();
            ViewGroup.LayoutParams lp= this.root.getLayoutParams();
            mWXSDKInstance.setSize(lp.width,lp.height);
            page=null;
            return;

        }


        try
        {
            boolean preload=getIntent().getBooleanExtra("preload",true);
            if(StringUtil.isNullOrEmpty(url))
                return;
            if(showProgress&&preload!=false)
                showLoading();
            fail_layout.setVisibility(View.GONE);
            if(mWXSDKInstance!=null)
            {
                mWXSDKInstance.registerRenderListener(null);
                mWXSDKInstance.destroy();
            }
            this.url=url;
            Log.i("url",url);
            mWXSDKInstance=null;
            mWXSDKInstance=new WXSDKInstance(this);
            mWXSDKInstance.setSize(screenTool.getScreenWidth(),screenTool.getScreenHeight());
            mWXSDKInstance.registerRenderListener(this);
            mWXSDKInstance.param=(Map) getIntent().getSerializableExtra("param");
            mWXSDKInstance.setBundleUrl(url);
//            Weex.setBaseUrl(mWXSDKInstance);
            if(url.startsWith("http"))
            {
//                if(!url.contains("?"))
//                    url+="?";
//                url+="p="+new Random(100000).nextInt();
//                mWXSDKInstance.renderByUrl("farwolf", url, null, null, WXRenderStrategy.APPEND_ASYNC);
                WeexFactory.downloadJs(url,mWXSDKInstance);
            }
            else
            {
                String s= Weex.loadLocal(url, this);
//                mWXSDKInstance.render("farwolf",s, null, null, WXRenderStrategy.APPEND_ASYNC);
                this.renderPage(s,url);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            if(apptool.isDebugMode())
            {
                showError(e);
            }

        }
    }
    public void render(String url)
    {


        render(url,true);


    }



    protected void renderPage(String template,String source){
        renderPage(template,source,null);
    }

    protected void renderPage(String template,String source,String jsonInitData){
//        AssertUtil.throwIfNull(mContainer,new RuntimeException("Can't render page, container is null"));
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, source);
        // Set options.bundleDigest
        try {
            String banner = WXUtils.getBundleBanner(template);
            JSONObject jsonObj = JSONObject.parseObject(banner);
            String digest = null;
            if (jsonObj != null) {
                digest = jsonObj.getString(com.taobao.weex.common.Constants.CodeCache.BANNER_DIGEST);
            }
            if (digest != null) {
                options.put(com.taobao.weex.common.Constants.CodeCache.DIGEST, digest);
            }
        } catch (Throwable t) {}
        //Set options.codeCachePath
        String path = WXEnvironment.getFilesDir(getApplicationContext());
        path += File.separator;
        path += com.taobao.weex.common.Constants.CodeCache.SAVE_PATH;
        path += File.separator;
        options.put(com.taobao.weex.common.Constants.CodeCache.PATH, path);

        mWXSDKInstance.setTrackComponent(true);
        mWXSDKInstance.render(
                "farwolf",
                template,
                options,
                jsonInitData,
                screenTool.getScreenWidth(),
                screenTool.getScreenHeight(),
                WXRenderStrategy.APPEND_ASYNC);
    }


    public void reload()
    {
        this.render(url,true);
    }
    public void showError(Exception e)
    {
        StackTraceElement[] s= e.getStackTrace();

        String msg=e+""+"\n";
        for(StackTraceElement q:s)
        {
            msg+=q.toString()+"\n";
        }

        showError( msg);
    }
    public void showError(String err)
    {
        if(Config.showError(this))
        {
//            fail_layout.setVisibility(View.VISIBLE);
            this.err.setText(err+"");
            this.err_layout.setVisibility(View.VISIBLE);
            String level="info";
            err= DateTool.Now()+" "+err;
            HotRefreshManager.getInstance().send("log:"+level+"level:"+err);
        }
        hideLoading();

    }




    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {

        container.removeAllViews();
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);

        container.addView(view);
        container.requestLayout();
        mWXSDKInstance.hasInit=true;
//        mWXSDKInstance.fireGlobalEventCallback("onPageInit",mWXSDKInstance.param);
        mWXSDKInstance.firePageInit();
        mWXSDKInstance.onActivityCreate();
        this.invokeRenderListener();
        this.isPageInit=true;
        hideLoading();
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        hideLoading();
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
        hideLoading();

    }


    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        hideLoading();
        if(Config.debug(this))
        {
            fail_layout.setVisibility(View.VISIBLE);
            if(apptool.isDebugMode())
            {
                if(!StringUtil.isNullOrEmpty(msg))
                    showError(msg);
            }
        }


    }

    private void registerBroadcastReceiver() {
        mReceiver = new RefreshBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WXSDKInstance.ACTION_DEBUG_INSTANCE_REFRESH);
        registerReceiver(mReceiver, filter);
    }

    @Click
    public void close_errClicked() {

        this.err_layout.setVisibility(View.GONE);

    }

    @Click
    public void fail_layoutClicked() {

        render(url);

    }

    public class RefreshBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (WXSDKInstance.ACTION_DEBUG_INSTANCE_REFRESH.equals(intent.getAction())) {
                render(url);
            }
        }
    }

    @Override
    protected void onDestroy() {

        if (mWXSDKInstance != null) {
//            mWXSDKInstance.fireGlobalEventCallback("onDestory",null);
            mWXSDKInstance.onActivityDestroy();
        }
        super.onDestroy();
    }





    @Override
    protected void onResume() {

        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
            mWXSDKInstance.fireGlobalEventCallback("onResume",null);
        }
        registerBroadcastReceiver();

        Log.e("stack",WXNavgationModule.stacks.get(rootid)+"");
    }






    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        switch (requestCode) {
            //就像onActivityResult一样这个地方就是判断你是从哪来的。
            case 222:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    EventBus.getDefault().post(new PermissionEvent(PermissionEvent.CAMREA));
                } else {
                    // Permission Denied
                    Toast.makeText(this, "很遗憾你把相机权限禁用了。请务必开启相机权限享受我们提供的服务吧。", Toast.LENGTH_SHORT)
                            .show();
//                    openCamera();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityResult(requestCode,resultCode,data);
        }
    }



    @Override
    protected void onPause() {

        super.onPause();
        if (mWXSDKInstance != null&&!this.isFinish) {
            mWXSDKInstance.fireGlobalEventCallback("onPause",null);
        }
        mWXSDKInstance.onActivityPause();
        if(mReceiver!=null)
            unregisterReceiver(mReceiver);


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.fireGlobalEventCallback("onStop",null);

            mWXSDKInstance.onActivityStop();

        }

    }


    @OnActivityResult(1)
    public void onQRRes(Intent in)
    {

        if(in==null)
            return;
        String url=in.getStringExtra("url");
        if(!StringUtil.isNullOrEmpty(url))
        {
            if(url.contains("_wx_devtool"))
            {
                String debugurl=url.split("_wx_devtool=")[1];
                WXEnvironment.sDebugServerConnectable = true;
                WXEnvironment.sRemoteDebugMode = true;
                WXEnvironment.sRemoteDebugProxyUrl = debugurl;
                WXSDKEngine.reload();
                return;
            }


            String temp=url;
            if(url.contains("_wx_tpl=")){
                url=url.split("_wx_tpl=")[1];
            }
            try {
                url= URLDecoder.decode(url,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            render(url);
            this.url=url;
            pref.edit().url().put(url).apply();
            String sp=getSocketPortByUrl(url);
            pref.edit().socketPort().put(sp).apply();
            EventBus.getDefault().post(new RefreshEvent("connect"));


        }

    }


    public String getSocketPortByUrl(String url)
    {
        if(url.contains("?"))
            return null;
        String q[]= url.split("\\?");
        if(q.length!=2)
            return null;
        String p[]=q[1].split("&");
        for(String px:p)
        {
            String qx[]=px.split("=");
            if(qx.length==2)
            {
                if("socketport".equals(qx[0]))
                {
                    return qx[1];
                }
            }
        }
        return null;


    }

    long lasttime = 0;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {


        if(exitEnable)
        {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

                    return exit();
                }
            }
        }

        if(!backKeyEnable)
        {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    if(backkeyCallback!=null)
                        backkeyCallback.invokeAndKeepAlive(null);
                    return true;
                }
            }
        }

        return super.dispatchKeyEvent(event);
    }


    private boolean exit() {

        long n = Calendar.getInstance().getTime().getTime();

        if (n - lasttime < 2000) {
            List<Activity> l= ActivityManager.getActivitiesByApplication(getApplication());
            for(Activity a:l)
            {
                a.finish();
            }
            System.exit(0);
        }
        else {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            lasttime = n;
        }
        return true;
    }



    @Override
    public void finish() {
         this.isFinish=true;
        WXNavgationModule.pop(this.rootid);
        if (mWXSDKInstance != null) {
            mWXSDKInstance.fireGlobalEventCallback("onDestory",null);}
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              WeexActivity.super.finish();
            }
        }, 200);

    }

    public void showTool()
    {

        ToolPop tool= ToolPop_.build(this);
        FreeDialog f=new FreeDialog(this,tool);
        f.setCanceledOnTouchOutside(false);
        tool.f=f;
        f.show();
    }



}
