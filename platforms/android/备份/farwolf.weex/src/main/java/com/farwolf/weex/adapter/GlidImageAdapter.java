package com.farwolf.weex.adapter;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.farwolf.util.FileTool;
import com.farwolf.util.Picture;
import com.farwolf.weex.activity.WeexActivity;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;
import com.taobao.weex.utils.ImageDrawable;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/5/7.
 */

public class GlidImageAdapter implements IWXImgLoaderAdapter {


    static HashMap<String,Drawable>placeholders=new HashMap<>();



    @Override
    public void setImage(String url, ImageView view, WXImageQuality quality, WXImageStrategy strategy) {

        if(url==null)
            return;


        if(url.startsWith("http"))
        {
            Drawable pladrawable=null;
            if(strategy.placeHolder!=null)
            {
                if(placeholders.containsKey(strategy.placeHolder))
                {
                    pladrawable=placeholders.get(strategy.placeHolder);
                }
                else
                {
                    if(strategy.placeHolder!=null)
                    {
                        WeexActivity a= (WeexActivity)view.getContext();
                        String  placeholder=Weex.getRelativeUrl(strategy.placeHolder,a.mWXSDKInstance);
//                        String placeholder=strategy.placeHolder.replace("root:",Weex.baseurl);
                        Bitmap bm= FileTool.loadAssetImage(placeholder,((Activity)view.getContext()).getApplicationContext());
                        pladrawable =new BitmapDrawable(bm);
                        placeholders.put(strategy.placeHolder,pladrawable);
                    }

                }

            }

            if(url.toLowerCase().contains(".gif"))
            {
                Glide
                        .with((Activity)view.getContext())
                        .load(url)
                        .asGif()

                        .placeholder(pladrawable)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(view);
            }
            else
            {

                ImageDrawable PL;
                GlideBitmapDrawable d;

                Glide.with(view.getContext().getApplicationContext())
                        .load(url)
                        .placeholder(pladrawable)
                        .transform(new GlideRoundTransform(view.getContext().getApplicationContext(),strategy.blurRadius))
//                        .transform(new GlideCircleTransform(view.getContext().getApplicationContext(),strategy.blurRadius))
                        .into(view);
            }
        }

        else
        {

            if(url.startsWith("sdcard:"))
            {
                url=url.replace("sdcard:","");
                Bitmap  bm= Picture.getBitmap(url);
                view.setImageBitmap(bm);
                return;
            }

            url=Weex.getSingleRealUrl(url);
            Bitmap bm=null;
            if(url.startsWith("base64==="))
            {
                url=url.replace("base64===","");
                bm= base64ToBitmap(url);
            }
            else
            {
                bm= FileTool.loadAssetImage(url,view.getContext().getApplicationContext());
            }

            view.setImageBitmap(bm);
        }
    }


    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


}
