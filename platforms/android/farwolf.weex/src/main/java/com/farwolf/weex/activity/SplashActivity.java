package com.farwolf.weex.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.farwolf.util.FileTool;
import com.farwolf.util.StringUtil;
import com.farwolf.weex.R;
import com.farwolf.weex.bean.Config;
import com.farwolf.weex.core.Page;
import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.event.Event;
import com.taobao.weex.WXSDKInstance;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjiangrong on 2017/12/8.
 */
@EActivity
@Fullscreen
public class SplashActivity extends WeexActivity {


    @Bean
    public WeexFactory weexFactory;

    @Bean
    public FileTool tool;

    @ViewById
    public ImageView img;



    @Override
    public int getViewId() {
        return R.layout.api_activity_splash;
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
        this.mWXSDKInstance=new WXSDKInstance(this);

        List l= Config.preload(this);
        String entry=getEntryUrl();
        l.add(entry);
        if(!Config.debug(this))
        {

            this.mWXSDKInstance.setBundleUrl(entry);
            List temp=new ArrayList();
            for(Object q:l)
            {
                temp.add((q+"").replace("root:","app/"));
            }
            weexFactory.preRender(temp,new WeexFactory.OnMultiRenderFinishListener(){

                @Override
                public void onRenderFinish() {

                    gotoMain();


                }

                @Override
                public void onRenderFailed() {

                }
            });
        }
        else
        {
            String url=pref.url().get();
            if(StringUtil.isNullOrEmpty(url))
            {
                url=Config.entry(this);
            }
            boolean isPotrait=  Config.isPortrait(this);
            Intent in=   new Intent(SplashActivity.this, EntryActivity_.class);
            this.mWXSDKInstance.setBundleUrl(url);
            in.putExtra("url",url);
            in.putExtra("isPortrait",isPotrait);
            startActivity(in);
            finish();
            releaseImageViewResouce(img);
        }

    }


    public String getEntryUrl()
    {
        SharedPreferences sharedPreferences = this.mWXSDKInstance.getContext().getSharedPreferences("farwolf_weex", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        String url=sharedPreferences.getString("mainurl","");
        if(StringUtil.isNullOrEmpty(url))
        {
            url=Config.entry(this);
        }
        return url;
    }

    public void gotoMain()
    {


        weexFactory.preRender(getEntryUrl(), new WeexFactory.OnRenderFinishListener() {
            @Override
            public void onRenderFinish(Page p) {
//                Intent in=   new Intent(SplashActivity.this, EntryActivity_.class);
//                in.putExtra("url",getEntryUrl());
//                boolean isPotrait=  Config.isPortrait(SplashActivity.this);
//                in.putExtra("isPortrait",isPotrait);
//                startActivity(in);
//                finish();

                p.instance.fireGlobalEventCallback("onPageInit",null);
                p.instance.onActivityCreate();
                releaseImageViewResouce(img);
//                finish();
            }

            @Override
            public void onRenderFailed(Page p) {

            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event e)
    {
           if("closeSplash".equals(e.key))
           {
               finish();
           }
    }


    @AfterViews
    @Override
    public void init() {


//        Bitmap bmx= FileTool.loadAssetImage(Config.splash(this),this);
//        this.img.setImageBitmap(bmx);
        this.bindDelay(1000);
        EventBus.getDefault().register(this);

    }


}
