package com.weexplus.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;

import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.event.ErrorEvent;
import com.weex.weexplus.R;
import com.weexplus.bean.Page;
import com.weexplus.core.Module;
import com.weexplus.core.WxpInstance;
import com.weexplus.core.render.WeexRender;
import com.weexplus.debug.HotRefreshManager;
import com.weexplus.event.RefreshEvent;
import com.weexplus.module.WXNavigatorModule;
import com.weexplus.module.WXStaticModule;
import com.weexplus.util.ActivityManager;
import com.weexplus.util.AppTool;
import com.weexplus.util.Config;
import com.weexplus.util.DateTool;
import com.weexplus.util.Path;
import com.weexplus.util.Pref;
import com.weexplus.util.RegexBase;
import com.weexplus.util.StringUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxpActivity extends Activity {

    public ViewGroup root;

    public ViewGroup err_layout;

    public ImageView lodingimg;

    public RelativeLayout fail_layout;

    public TextView err;

    public View rootContainer;


    public ViewGroup container;

    public WxpInstance instance;


    public String rootId;

    public String pageId;

    public String url;
    public String module;

    public boolean isRoot;

    public boolean isFinish;

    public boolean exitEnable;

    public boolean backKeyEnable=true;

    public JSCallback backkeyCallback;

    public JSONObject param;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
        load(savedInstanceState);
    }


    public int getViewId(){
      return R.layout.api_weexplus_activity;
    }

    void initLayout(){
        this.err_layout=findViewById(R.id.err_layout);
        this.lodingimg=findViewById(R.id.lodingimg);
        this.fail_layout=findViewById(R.id.fail_layout);
        this.err=findViewById(R.id.err);
        this.root=findViewById(R.id.root);
        this.rootContainer=findViewById(R.id.rootContainer);
        this.container=findViewById(R.id.container);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        findViewById(R.id.close_err).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                err_layout.setVisibility(View.GONE);
            }
        });
        findViewById(R.id.fail_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               refresh();
            }
        });
        this.isRoot = getIntent().getBooleanExtra("isRoot",false);
        Glide.with(this)
                .asGif()
                .load(R.drawable.load)
                .into(lodingimg);
    }


    public void load(Bundle bundle){
        initLayout();
        EventBus.getDefault().register(this);
        if(bundle!=null)
        {
            url= bundle.getString("url");
            rootId= bundle.getString("rootId");
            pageId= bundle.getString("pageId");
            module= bundle.getString("module");
            if(url!=null)
            {
                this.url=url;
            }
            HashMap m=(HashMap) bundle.getSerializable("static");
//            HashMap callback=(HashMap) bundle.getSerializable("callback");
            if(m!=null)
            {
                WXStaticModule.m=m;
            }
//            if(callback!=null){
//                WXNavigatorModule.callbacks=callback;
//            }
        }else{
            url=getIntent().getStringExtra("url");
            this.rootId=getIntent().getStringExtra("rootId");
            this.pageId=getIntent().getStringExtra("pageId");
            this.module=getIntent().getStringExtra("module");
        }
        WXNavigatorModule.addActivity(this.rootId,module,this);
        this.url=url;
        Page page= WeexRender.getPage(url);
        if(page!=null){
            loadCache(page);
        }else{
            render(url,module);
        }
    }

    void loadCache(Page page){
        if(page!=null)
        {
            if(page.view==null)
                return;
            RelativeLayout parent=  (RelativeLayout)page.view.getParent();
            if(parent!=null)
                parent.removeAllViews();
            container.addView(page.view);
            url=page.instance.getBundleUrl();
            this.instance=  page.instance;
            this.instance.setContext(this);
            page.instance.firePageInit();
            instance.onActivityCreate();
            ViewGroup.LayoutParams lp= this.root.getLayoutParams();
            page.instance.setSize(lp.width,lp.height);
        }
    }


    public void refresh(){
        this.render(this.url,this.module);
    }

   public void render(String url, String module){

        HashMap param= (HashMap) getIntent().getSerializableExtra("param");
        this.param=new JSONObject(param);
        Map option= (JSONObject) getIntent().getSerializableExtra("option");
        this.showLoading();
       fail_layout.setVisibility(View.GONE);
        if(instance!=null){
            instance.registerRenderListener(null);
            instance.destroy();
        }
        AppTool tool=new AppTool(this);
        WeexRender.renderByUrl(url, module,  this.param, option,this,!tool.isDebugMode(), new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {
                WxpInstance wxpInstance=(WxpInstance)instance;
                container.removeAllViews();
                ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lp);
                container.addView(view);
                container.requestLayout();
                wxpInstance.hasInit=true;
                wxpInstance.firePageInit();
                WxpActivity.this.instance=wxpInstance;
                instance.onActivityCreate();
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
                if(Config.debug(WxpActivity.this))
                {
                    fail_layout.setVisibility(View.VISIBLE);
                    AppTool appTool=new AppTool(WxpActivity.this);
                    if(appTool.isDebugMode())
                    {
                        if(!StringUtil.isNullOrEmpty(msg))
                            showError(msg);
                    }
                }
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ErrorEvent event) {
        if( ActivityManager.getInstance().getCurrentActivity()==this)
        {
            if("log".equals(event.type))
                this.showError(event.msg);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(RefreshEvent event) {

        if( ActivityManager.getInstance().getCurrentActivity()==this&&"refresh".equals(event.type))
        {
            this.refresh();
        }
    }

    public void showError(String err)
    {
        if(Config.showError(this))
        {
            this.err.setText(err+"");
            this.err_layout.setVisibility(View.VISIBLE);
            String level="info";
            err= DateTool.Now()+" "+err;
            HotRefreshManager.getInstance().send("log:"+level+"level:"+err);
        }
        hideLoading();

    }
    public void showLoading()
    {
        lodingimg.setVisibility(View.VISIBLE);
    }
    public void hideLoading()
    {

        lodingimg.setVisibility(View.GONE);
    }

    @Override
    public void finish() {
        this.isFinish=true;
        WXNavigatorModule.pop(this.rootId,module);
        if (instance != null) {
            instance.fireGlobalEventCallback("onDestory",null);}


//        static_callbacks.remove(id);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                WxpActivity.super.finish();
            }
        }, 200);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            onQRRes(data);
        }else{
            if(instance!=null){
                instance.onActivityResult(requestCode,resultCode,data);
            }
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState!=null)
        {
            outState.putString("url",this.url);
            outState.putString("module",this.module);
            outState.putString("pageId",this.pageId);
            outState.putString("rootId",this.rootId);
            outState.putSerializable("static", WXStaticModule.m);
//            outState.putSerializable("callback", WXNavigatorModule.callbacks);
        }
    }

    @Override
    protected void onDestroy() {

        if (instance != null) {
            instance.onActivityDestroy();
        }
        WXNavigatorModule.removeCallback(module,pageId);
        super.onDestroy();
    }



    @Override
    protected void onResume() {

        super.onResume();
        if (instance != null) {
            instance.onActivityResume();
            instance.fireGlobalEventCallback("onResume",null);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(instance!=null){
            instance.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
    }
    @Override
    protected void onPause() {

        super.onPause();
        if (instance != null) {
            if(!this.isFinish){
                instance.fireGlobalEventCallback("onPause",null);
            }
            instance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (instance != null) {
            instance.fireGlobalEventCallback("onStop",null);

            instance.onActivityStop();

        }

    }





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
            module= Path.getPossiableModuleName(url);
            Module md= WeexRender.getModule(module);
            if(md!=null){
                md.hash=null;
                md.navDic=null;
                md.router=null;
                md.appboard=null;
            }
            render(url,module);
            this.url=url;
            String sp=getSocketPortByUrl(url);
            String ip= RegexBase.regexOne(url,"http://",":");
            Pref.setString(this,"url",url);
            Pref.setString(this,"ip",ip);
            Pref.setString(this,"socketPort",sp);

            EventBus.getDefault().post(new RefreshEvent("connect"));


        }

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
//            System.exit(0);
        }
        else {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            lasttime = n;
        }
        return true;
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
}
