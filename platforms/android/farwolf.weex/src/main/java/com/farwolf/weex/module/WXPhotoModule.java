package com.farwolf.weex.module;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Base64;

import com.farwolf.photochoose.ChoosePhotoActivity_;
import com.farwolf.util.UILImageLoader;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zhengjiangrong on 2017/5/22.
 */

public class WXPhotoModule extends WXModule {


    JSCallback  callback;


    /**
     *
     * @param aspX
     * @param aspY
     * @param themeColor
     * @param titleColor
     * @param cancelColor
     * @param callback
     */
    @JSMethod
    public void open(int aspX, int aspY , String themeColor, String titleColor, String cancelColor, JSCallback callback)
    {
         takePhoto(aspX,aspY,themeColor);
         this.callback=callback;
    }

    public void takePhoto(int width,int height,String themeColor)
    {


        Intent in=new Intent(mWXSDKInstance.getContext(), ChoosePhotoActivity_.class);
        in.putExtra("resize", true);
        in.putExtra("width", width);

        in.putExtra("height", height);
        in.putExtra("themeColor",themeColor);
        ((Activity)mWXSDKInstance.getContext()).startActivityForResult(in,1);

    }

    public void onResult(Intent in)
    {
        if(in==null)
            return;
        String path=in.getStringExtra("path");
        HashMap m=new HashMap();
        m.put("path","sdcard:"+path);
        callback.invoke(m);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
         if(requestCode==1)
         {
             if(resultCode==3||resultCode==4)
             {
                 onResult(data);
             }
         }
    }

    /**
     *
     * @param width
     * @param height
     * @param themeColor
     * @param titleColor
     * @param cancelColor
     * @param callback
     */
    @JSMethod
    public void openPhoto(int width,int height,String themeColor,String titleColor, String cancelColor,final JSCallback callback )
    {
        File editdir=new File(mWXSDKInstance.getContext().getCacheDir()+"gallery/edit");
        File takephoto=new File(mWXSDKInstance.getContext().getCacheDir()+"gallery/photo");
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.parseColor(themeColor))
                .setFabNornalColor(Color.parseColor(themeColor))
                .setFabPressedColor(Color.parseColor(themeColor))
                .setCropControlColor(Color.parseColor(themeColor))
                .build();
//        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropWidth(width)
                .setCropHeight(height)
                .setForceCrop(true)
                .setForceCropEdit(true)
                .setEnablePreview(true)
                .build();
        CoreConfig coreConfig = new CoreConfig.Builder(mWXSDKInstance.getContext(), new UILImageLoader(), theme)
                .setFunctionConfig(functionConfig)
                .setEditPhotoCacheFolder(editdir)
                .setTakePhotoFolder(takephoto)

                .build();
        GalleryFinal.init(coreConfig);

        GalleryFinal.openGallerySingle(1012,functionConfig, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                String url= resultList.get(0).getPhotoPath();
//                Bitmap   bm = BitmapFactory.decodeFile(url);
//                String s= bitmapToBase64(bm);
//                s="base64==="+s;
                HashMap m=new HashMap();
//                m.put("base64",s);
                m.put("path","sdcard:"+url);
                callback.invoke(m);

            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
    }


    /**
     *
     * @param width
     * @param height
     * @param themeColor
     * @param callback
     */
    @JSMethod
    public void openCamera(int width,int height,String themeColor,final JSCallback callback )
    {
        File editdir=new File(mWXSDKInstance.getContext().getCacheDir()+"gallery/edit");
        File takephoto=new File(mWXSDKInstance.getContext().getCacheDir()+"gallery/photo");
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.parseColor(themeColor))
                .setFabNornalColor(Color.parseColor(themeColor))
                .setFabPressedColor(Color.parseColor(themeColor))
                .setCropControlColor(Color.parseColor(themeColor))
                .build();
//        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()


                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)

                .setCropWidth(width)
                .setCropHeight(height)
                .setForceCrop(true)
                .setForceCropEdit(true)
                .setEnablePreview(true)

                .build();
        CoreConfig coreConfig = new CoreConfig.Builder(mWXSDKInstance.getContext(), new UILImageLoader(), theme)
                .setFunctionConfig(functionConfig)
                .setEditPhotoCacheFolder(editdir)
                .setTakePhotoFolder(takephoto)

                .build();
        GalleryFinal.init(coreConfig);
        GalleryFinal.openCamera(1011, functionConfig,new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                String url= resultList.get(0).getPhotoPath();
//                Bitmap   bm = BitmapFactory.decodeFile(url);
//                String s= bitmapToBase64(bm);
//                s="base64==="+s;
                HashMap m=new HashMap();
                m.put("path","sdcard:"+url);
//                m.put("base64",s);
                callback.invoke(m);
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
    }

    public InputStream Bitmap2InputStream(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(bm==null)
            return null;
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }

    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
