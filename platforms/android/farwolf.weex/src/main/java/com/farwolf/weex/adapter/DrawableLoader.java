package com.farwolf.weex.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import com.farwolf.weex.activity.WeexActivity;
import com.farwolf.weex.app.WeexApplication;
import com.farwolf.weex.core.local.Local;
import com.farwolf.weex.util.Weex;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.adapter.DrawableStrategy;
import com.taobao.weex.adapter.IDrawableLoader;

public class DrawableLoader implements IDrawableLoader {


    @Override
    public void setDrawable(String url,final DrawableTarget drawableTarget,final DrawableStrategy drawableStrategy) {
        WeexActivity ac= (WeexActivity)drawableStrategy.context;
       final String  temp=Weex.getRelativeUrl(url,ac.mWXSDKInstance);
        ac.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                 load(temp,drawableTarget,drawableStrategy);
            }
        });




    }

    void load(String url,final DrawableTarget drawableTarget,final DrawableStrategy drawableStrategy){
        if(url.startsWith("http")){
            if(url.toLowerCase().contains(".gif"))
            {
                Glide
                        .with(WXEnvironment.getApplication())

                        .asGif()
//                  .placeholder(pladrawable)
                        .load(url)
//                  .asGif()
//                  .placeholder(pladrawable)
//                  .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(new com.bumptech.glide.request.target.Target<GifDrawable>() {
                            @Override
                            public void onLoadStarted(@Nullable Drawable placeholder) {

                            }

                            @Override
                            public void onLoadFailed(@Nullable Drawable errorDrawable) {

                            }

                            @Override
                            public void onResourceReady(GifDrawable resource, Transition<? super GifDrawable> transition) {
                                drawableTarget.setDrawable(resource,true);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {

                            }

                            @Override
                            public void getSize(SizeReadyCallback cb) {

                            }

                            @Override
                            public void removeCallback(SizeReadyCallback cb) {

                            }

                            @Override
                            public void setRequest(@Nullable Request request) {

                            }

                            @Nullable
                            @Override
                            public Request getRequest() {
                                return null;
                            }

                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onStop() {

                            }

                            @Override
                            public void onDestroy() {

                            }
                        });

                return;
            }else{
                final  String temp=url;
                Picasso.with(WXEnvironment.getApplication())
                        .load(temp)
                        .into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                BitmapDrawable bd = new BitmapDrawable(bitmap);
                                drawableTarget.setDrawable(bd,true);
                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });

            }

        }else{
            this.loadLocal(url,drawableTarget,drawableStrategy);
        }

    }

    void loadLocal(String url,final DrawableTarget drawableTarget,DrawableStrategy drawableStrategy){
        if(url.toLowerCase().contains(".gif"))
        {
//          Glide.with((Activity)view.getContext()).load()
            Bitmap bm= Local.getBitmap((WXEnvironment.getApplication()).getApplicationContext(),url);


            boolean exist= Local.isDiskExist(WXEnvironment.getApplication());
            if(exist)
            {
                url= "file:///"+Local.getDiskBasePath(WXEnvironment.getApplication())+url;
            }
            else{

                url= "file:///android_asset/"+url;
            }
            Glide
                    .with(WeexApplication.getInstance())
                    .asGif()
                    .load( url)
                    .into(new com.bumptech.glide.request.target.Target<GifDrawable>() {
                        @Override
                        public void onLoadStarted(@Nullable Drawable placeholder) {

                        }

                        @Override
                        public void onLoadFailed(@Nullable Drawable errorDrawable) {

                        }

                        @Override
                        public void onResourceReady(GifDrawable resource, Transition<? super GifDrawable> transition) {
                            drawableTarget.setDrawable(resource,true);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }

                        @Override
                        public void getSize(SizeReadyCallback cb) {

                        }

                        @Override
                        public void removeCallback(SizeReadyCallback cb) {

                        }

                        @Override
                        public void setRequest(@Nullable Request request) {

                        }

                        @Nullable
                        @Override
                        public Request getRequest() {
                            return null;
                        }

                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onStop() {

                        }

                        @Override
                        public void onDestroy() {

                        }
                    });

            return;
        }

        Bitmap bm=PicassoImageAdapter.getLocalBitmap(url);
        if(bm!=null){
            BitmapDrawable bd = new BitmapDrawable(bm);
            drawableTarget.setDrawable(bd,true);
        }


    }
}
