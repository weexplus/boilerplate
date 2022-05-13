package com.weexplus.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;

import com.weex.weexplus.R;
import com.weexplus.app.WxpApplication;
import com.weexplus.core.WxpInstance;
import com.weexplus.core.render.WeexRender;
import com.weexplus.util.Callback;
import com.weexplus.util.Config;

import com.weexplus.util.Const;
import com.weexplus.util.Path;
import com.weexplus.util.StringUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends WxpActivity {




    public ImageView img;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_splash_activity);
        img=findViewById(R.id.img);
        init();
    }

    @Override
    public int getViewId() {
        return R.layout.api_splash_activity;
    }

    @Override
    public void load(Bundle bundle) {

    }

    private void bindDelay(final long time) {
        final Handler achandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                jump();

            }
        };
        achandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                achandler.sendEmptyMessage(0);
            }
        }, time);
    }

    public static void releaseImageViewResouce(ImageView imageView) {
        if (imageView == null) return;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }
    public  void jump()
    {
        if(Config.debug(this))
        {
            SharedPreferences sharedPreferences = WxpApplication.getApplication().getSharedPreferences("farwolf_weex", Context.MODE_PRIVATE); //私有数据
            String url=sharedPreferences.getString("url","");
            if(StringUtil.isNullOrEmpty(url))
            {
                url=Config.debugEntry(this);
            }
            String module=getModuleName(url);
            WeexRender.jump(url,null,new JSONObject(),true,Config.isPortrait(this),false,true,this, module,false,null,null);
            finish();
            releaseImageViewResouce(img);

        }
        else
        {
            gotoMain();
        }

    }

    public String getModuleName(String url){
        String module=Const.MAIN;
        if(url.startsWith(Const.HTTP)){
            if(!url.contains(Const.DEBUG_URL_FLAG)){
                module=Path.getPossiableModuleName(url);
            }
        }
        return module;
    }


    public String getEntryUrl()
    {
        return Config.releaseEntry(this);
    }

    public void gotoMain()
    {
       final String url=  getEntryUrl().replace(Const.ROOT,"");
        String module=getModuleName(url);
        WeexRender.renderByUrl(url, module, new JSONObject(), new JSONObject(), this,false,new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {
                WxpInstance wxpInstance=(WxpInstance)instance;
                wxpInstance.setBundleUrl(url);
                wxpInstance.hasInit=true;
                wxpInstance.onActivityCreate();
                wxpInstance.firePageInit();
                releaseImageViewResouce(img);
//                finish();
            }

            @Override
            public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
                Log.e("",width+"");

            }

            @Override
            public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
                Log.e("",width+"");
            }

            @Override
            public void onException(WXSDKInstance instance, String errCode, String msg) {
                Log.e("",msg);
            }
        });
    }



    public void init() {


        this.bindDelay(1000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }


}
