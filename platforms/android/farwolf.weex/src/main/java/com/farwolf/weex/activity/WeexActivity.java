package com.farwolf.weex.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.farwolf.base.TitleActivityBase;
import com.farwolf.util.AppTool;
import com.farwolf.util.ScreenTool;
import com.farwolf.util.StringUtil;
import com.farwolf.view.FreeDialog;
import com.farwolf.weex.R;
import com.farwolf.weex.bean.Config;
import com.farwolf.weex.core.Page;
import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.event.RefreshEvent;
import com.farwolf.weex.module.WXNavgationModule;
import com.farwolf.weex.module.WXStaticModule;
import com.farwolf.weex.pref.WeexPref_;
import com.farwolf.weex.util.Weex;
import com.farwolf.weex.view.ToolPop;
import com.farwolf.weex.view.ToolPop_;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.IWXDebugProxy;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;
import com.taobao.weex.utils.WXUtils;
import com.ypy.eventbus.EventBus;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
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


    public String url;


    @ViewById
    public  View   rootContainer;

    @ViewById
    public ViewGroup container;





    @ViewById
    public ViewGroup root;

    @Pref
    public WeexPref_ pref;

    @Bean
    public WeexFactory weexFactory;

    @Bean
    public Weex weex;


    public  boolean exitEnable;


    public HashMap param;

//    public String navbarVisibility;

    public String pageid;

    public String rootid;

    @Bean
    public AppTool apptool;



    @Override
    public int getViewId() {
        return R.layout.api_weex_activity;
    }


//    public void startHotRefresh() {
//
//        String wsUrl = "ws://" + pref.ip().get() + ":8082";
//        mWXHandler.obtainMessage(Constants.HOT_REFRESH_CONNECT, 0, 0, wsUrl).sendToTarget();
//
//    }
//
//    public void stopHotRefresh() {
//
//        String wsUrl = "ws://" + pref.ip().get() + ":8082";
//        mWXHandler.obtainMessage(Constants.HOT_REFRESH_DISCONNECT, 0, 0, wsUrl).sendToTarget();
//
//    }


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

    }

    public void resetFrame()
    {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
//        if(AppTool.OSVersion()>=19)
//        {
//
//            lp.setMargins(0, screenTool.toDip(60), 0, 0);
//
//        }
//        else
//        {
//            lp.setMargins(0, screenTool.toDip(52), 0, 0);
//        }
        rootContainer.setLayoutParams(lp);

//        if("transparent".equals(navbarVisibility))
//        {
//            makeTransparent();
//        }
//        else if("visiable".equals(navbarVisibility))
//        {
//
//
//        }
//        else
//        {
            makeHidden();

//        }
    }

    @AfterViews
    public void initWeexActivity()
    {
        if(hasInit)
            return;
        if(lodingimg==null)
            return;
        Glide
                .with(this)
                .load(R.drawable.load)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(lodingimg);

        hasInit=true;
        String url=getIntent().getStringExtra("url");

        this.isRoot = getIntent().getBooleanExtra("isRoot",false);
        this.param=(HashMap) getIntent().getSerializableExtra("param");
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

    public void onEventMainThread(RefreshEvent event) {

        if( ActivityManager.getInstance().getCurrentActivity()==this&&"refresh".equals(event.type))
        {
            this.render(this.url,false);
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

        Page page= weexFactory.getPage(url);
        if(page!=null)
        {

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
            mWXSDKInstance.fireGlobalEventCallback("onPageInit",null);
            ViewGroup.LayoutParams lp= this.root.getLayoutParams();
            mWXSDKInstance.setSize(lp.width,lp.height);
            page=null;
            return;

        }


        try
        {
            if(StringUtil.isNullOrEmpty(url))
                return;
            if(showProgress)
                showLoading();
            fail_layout.setVisibility(View.GONE);
            if(mWXSDKInstance!=null)
            {
                mWXSDKInstance.destroy();
            }
            this.url=url;
            Log.i("url",url);
            mWXSDKInstance=null;
            mWXSDKInstance=new WXSDKInstance(this);
            mWXSDKInstance.setSize(screenTool.getScreenWidth(),screenTool.getScreenHeight());
            mWXSDKInstance.registerRenderListener(this);

            mWXSDKInstance.setBundleUrl(url);
            Weex.setBaseUrl(mWXSDKInstance);
            if(url.startsWith("http"))
            {
                mWXSDKInstance.renderByUrl("farwolf", url, null, null, WXRenderStrategy.APPEND_ASYNC);
            }
            else
            {
                String s= WXFileUtils.loadAsset(url, this);
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
        if(Config.debug(this))
        {
            fail_layout.setVisibility(View.VISIBLE);
            this.err.setText(err+"");
            this.err_layout.setVisibility(View.VISIBLE);
        }

    }




    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {

        container.removeAllViews();
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);

        container.addView(view);
        container.requestLayout();
        mWXSDKInstance.fireGlobalEventCallback("onPageInit",null);
        mWXSDKInstance.onActivityCreate();
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
        filter.addAction(IWXDebugProxy.ACTION_DEBUG_INSTANCE_REFRESH);
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
            if (IWXDebugProxy.ACTION_DEBUG_INSTANCE_REFRESH.equals(intent.getAction())) {
                render(url);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }

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
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
        if(mReceiver!=null)
            unregisterReceiver(mReceiver);


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
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
            this.url=url;
            pref.edit().url().put(url).apply();
            String sp=getSocketPortByUrl(url);
            pref.edit().socketPort().put(sp).apply();
            render(url);
            EventBus.getDefault().post(new RefreshEvent("connect"));

//            startHotRefresh();
//            weex.startDebug();
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

        return super.dispatchKeyEvent(event);
    }


    private boolean exit() {

        long n = Calendar.getInstance().getTime().getTime();

        if (n - lasttime < 2000) {
            System.exit(0);
        }
        else {
            Toast.makeText(this, "在按一次退出应用", Toast.LENGTH_SHORT).show();
            lasttime = n;
        }
        return true;
    }



    @Override
    public void finish() {
        super.finish();
        WXNavgationModule.pop(this.rootid);
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
