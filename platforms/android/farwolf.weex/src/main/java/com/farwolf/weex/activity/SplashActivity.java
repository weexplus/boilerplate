package com.farwolf.weex.activity;

import android.content.Intent;
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
import com.farwolf.weex.core.WeexFactory;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjiangrong on 2017/12/8.
 */
@EActivity
@Fullscreen
public class SplashActivity extends WeexActivity {


    @Bean
    WeexFactory weexFactory;

    @Bean
    FileTool tool;

    @ViewById
    ImageView img;



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
        List l= Config.preload(this);
        l.add(Config.entry(this));
        if(!Config.debug(this))
        {
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
                    gotoMain();
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
            Intent in=   new Intent(SplashActivity.this, EntryActivity_.class);
            in.putExtra("url",url);
            startActivity(in);
            finish();
            releaseImageViewResouce(img);
        }

    }

    void gotoMain()
    {
        Intent in=   new Intent(SplashActivity.this, EntryActivity_.class);
        in.putExtra("url",Config.entry(SplashActivity.this));
        startActivity(in);
        finish();
        releaseImageViewResouce(img);
    }



    @AfterViews
    @Override
    public void init() {


//        Bitmap bmx= FileTool.loadAssetImage(Config.splash(this),this);
//        this.img.setImageBitmap(bmx);
        this.bindDelay(1000);

    }


}
