package com.weexplus.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;
import com.weex.weexplus.R;
import com.weexplus.app.WxpApplication;
import com.weexplus.component.WXImage;
import com.weexplus.core.CacheManager;
import com.weexplus.core.WxpInstance;
import com.weexplus.core.render.Local;
import com.weexplus.util.Const;
import com.weexplus.util.Path;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class ImageAdapter implements IWXImgLoaderAdapter {


    static HashMap<String,Bitmap> placeholders=new HashMap<String,Bitmap>();

    @Override
    public void setImage(String url, ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        if(view==null||view.getLayoutParams()==null){
            return;
        }
        if (TextUtils.isEmpty(url)) {
            view.setImageBitmap(null);
            if(strategy==null||strategy.placeHolder==null)
                return;
        }
        WxpInstance instance=(WxpInstance)view.getTag(R.id.imageTag);
        if(instance!=null)
            loadPlaceHodler(strategy,view,instance);
        String bundleUrl=instance.getBundleUrl();
        String module =instance.module.name;

        String furl= Path.getUrl(url,bundleUrl,instance.module.name);
        if(furl!=null){
            loadNormal(furl,strategy,view,module);
        }else{
            loadBase64(furl,strategy,view);
        }


    }

    void loadPlaceHodler(WXImageStrategy strategy,ImageView view,WxpInstance instance){
        String placeHolder= Path.getUrl(strategy.placeHolder,instance.getBundleUrl(),instance.module.name);
        if(placeHolder==null){
            return;
        }
        Bitmap bm= placeholders.get(placeHolder);
        if(bm==null){
            bm= Local.getBitmap(((Activity)view.getContext()).getApplicationContext(),placeHolder);
        }
        view.setImageBitmap(bm);
    }

    void loadNormal(final String url, WXImageStrategy strategy, final ImageView view, final String module){
        String ab_path= Path.urlToLocal(url,module,view.getContext());
        String furl=url;
        if(!CacheManager.needDownload(url,module,view.getContext())){
            furl="file://"+ab_path;
        }
        RequestManager requestManager= Glide
                .with(view.getContext().getApplicationContext());
        if(url.contains(".gif")){
            RequestBuilder<GifDrawable> gif= requestManager
                    .asGif()
                    .load( furl);
            if(strategy.blurRadius>0){
                gif.apply(RequestOptions.bitmapTransform(new RoundedCorners(strategy.blurRadius)))
                        .into(view);
            }else{
                gif.into(view);
            }

        }else{
            Glide
                .with(view.getContext().getApplicationContext())
                .load(furl)
                .into(view);
        }

    }


    void loadBase64(String url,WXImageStrategy strategy,ImageView view){
        try
        {
            byte[] bytes = Base64.decode(url,   Base64.DEFAULT);
            Bitmap bm=  BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            if(bm!=null){
                view.setImageBitmap(bm);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
