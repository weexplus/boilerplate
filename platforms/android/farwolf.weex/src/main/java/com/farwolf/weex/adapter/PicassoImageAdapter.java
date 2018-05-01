/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.farwolf.weex.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.farwolf.util.FileTool;
import com.farwolf.util.Picture;
import com.farwolf.weex.activity.WeexActivity;
import com.farwolf.weex.core.local.Local;
import com.farwolf.weex.util.Weex;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

import java.util.HashMap;

import static com.farwolf.weex.core.local.Local.getBitmap;

public class PicassoImageAdapter implements IWXImgLoaderAdapter {

  static HashMap<String,Drawable> placeholders=new HashMap<>();

  public PicassoImageAdapter() {
  }

  @Override
  public void setImage(final String url, final ImageView view,
                       final WXImageQuality quality, final WXImageStrategy strategy) {

//    WXSDKManager.getInstance().postOnUiThread(new Runnable() {
//
//      @Override
//      public void run() {
        if(view==null||view.getLayoutParams()==null){
          return;
        }
        if (TextUtils.isEmpty(url)) {
          view.setImageBitmap(null);
            if(strategy==null||strategy.placeHolder==null)
             return;
        }
        String temp = url;

        if (view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
          return;
        }

          WeexActivity a= (WeexActivity)view.getContext();
        temp=Weex.getRelativeUrl(url,a.mWXSDKInstance);


        if(temp.startsWith("http"))
        {
          loadHttp(temp,view,quality,strategy);
        }
        else
        {
          loadLocal(temp,view,quality,strategy);
        }



//      }
//    },0);
  }



  public  void loadHttp(final String url,final ImageView view,
                        WXImageQuality quality, final WXImageStrategy strategy)
  {
    Drawable pladrawable=null;
    if(!TextUtils.isEmpty(strategy.placeHolder)){


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
//          String placeholder=strategy.placeHolder.replace("root:",Weex.baseurl);
            placeholder=placeholder.replace(Weex.getBaseUrl(a.mWXSDKInstance),"app/");
            Bitmap bm= getBitmap(((Activity)view.getContext()).getApplicationContext(),placeholder);
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

          return;
      }

    Picasso.with(WXEnvironment.getApplication())
            .load(url)
            .placeholder(pladrawable)
            .transform(new BlurTransformation(strategy.blurRadius))
            .into(view, new Callback() {
              @Override
              public void onSuccess() {
                if(strategy.getImageListener()!=null){
                   strategy.getImageListener().onImageFinish(url,view,true,null);
                }


              }

              @Override
              public void onError() {
                if(strategy.getImageListener()!=null){
                  strategy.getImageListener().onImageFinish(url,view,false,null);
                }
              }
            });



  }

  public  void loadLocal(String url,final ImageView view,
                         WXImageQuality quality, final WXImageStrategy strategy)
  {
      Drawable pladrawable=null;
      if(!TextUtils.isEmpty(strategy.placeHolder)){


          if(placeholders.containsKey(strategy.placeHolder))
          {
              pladrawable=placeholders.get(strategy.placeHolder);
          }
          else
          {
              if(strategy.placeHolder!=null)
              {
//                  String placeholder=strategy.placeHolder.replace("root:",Weex.baseurl);
                  WeexActivity a= (WeexActivity)view.getContext();
                  String  placeholder=Weex.getRelativeUrl(strategy.placeHolder,a.mWXSDKInstance);
//                  Bitmap bmx= FileTool.loadAssetImage(placeholder,((Activity)view.getContext()).getApplicationContext());
                  Bitmap bmx= getBitmap(((Activity)view.getContext()).getApplicationContext(),placeholder);
                  pladrawable =new BitmapDrawable(bmx);
                  placeholders.put(strategy.placeHolder,pladrawable);
              }

          }
      }
      view.setImageDrawable(pladrawable);
      if(url.toLowerCase().contains(".gif"))
      {
//          Glide.with((Activity)view.getContext()).load()
          Bitmap bm= Local.getBitmap(((Activity)view.getContext()).getApplicationContext(),url);
          Glide
                  .with((Activity)view.getContext())
//                  .load("file:///android_asset/"+url)
                  .load( Picture.bitmapToByte(bm))
                  .asGif()

                  .placeholder(pladrawable)
                  .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                  .into(view);

          return;
      }


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

      if(bm!=null)
          view.setImageBitmap(bm);

  }


  public static Bitmap base64ToBitmap(String base64Data) {
    byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
  }


}
