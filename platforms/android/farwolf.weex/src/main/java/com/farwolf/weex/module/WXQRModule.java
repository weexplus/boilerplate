package com.farwolf.weex.module;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import com.farwolf.perssion.Perssion;
import com.farwolf.perssion.PerssionCallback;
import com.farwolf.qrcode.zxing.android.CaptureActivity;
import com.farwolf.util.Picture;
import com.farwolf.weex.event.PermissionEvent;
import com.farwolf.weex.util.Const;
import com.farwolf.weex.util.Weex;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by zhengjiangrong on 2017/7/16.
 */

public class WXQRModule extends WXModule {


    JSCallback callback;
    HashMap param;


    @JSMethod
    public void makeQr(final HashMap param,final JSCallback callback){
        final  String path=mWXSDKInstance.getContext().getCacheDir()+"/1.png";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url="";
                int size=100;
                if(param.containsKey("size")){
                    size=Integer.parseInt(param.get("size")+"");
                }
                url=param.get("str")+"";
                size=(int)Weex.length((float) size);

                int w=size;
                int h=size;
                try
                {
                    //判断URL合法性
                    if (url == null || "".equals(url) || url.length() < 1)
                    {
                        return;
                    }
                    Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
                    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                    //图像数据转换，使用了矩阵转换
                    BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, w, h, hints);
                    int[] pixels = new int[w * h];
                    //下面这里按照二维码的算法，逐个生成二维码的图片，
                    //两个for循环是图片横列扫描的结果
                    for (int y = 0; y < h; y++)
                    {
                        for (int x = 0; x < w; x++)
                        {
                            if (bitMatrix.get(x, y))
                            {
                                pixels[y * w + x] = 0xff000000;
                            }
                            else
                            {
                                pixels[y * w + x] = 0xffffffff;
                            }
                        }
                    }
                    //生成二维码图片的格式，使用ARGB_8888
                    Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                    bitmap.setPixels(pixels, 0, w, 0, 0, w, h);

                    Picture.saveImageToSDCard(path,bitmap);
                    HashMap res=new HashMap();
                    res.put("path",Const.PREFIX_SDCARD+path);
                    callback.invoke(res);
                    //显示到我们的ImageView上面

                }
                catch (WriterException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @JSMethod
    public void makeBarCode(final HashMap param,final JSCallback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final  String path=mWXSDKInstance.getContext().getCacheDir()+"/1.png";
                String str="";
                if(param.containsKey("str")){
                    str=param.get("str")+"";
                }
                int width=0;
                if(param.containsKey("width")){
                    width=Integer.parseInt(param.get("width")+"");
                }

                int height=0;
                if(param.containsKey("height")){
                    height=Integer.parseInt(param.get("height")+"");
                }
                width=(int)Weex.length(width);
                height=(int)Weex.length(height);
                Bitmap bitmap= creatBarcode(str,width,height);
                Picture.saveImageToSDCard(path,bitmap);
                HashMap res=new HashMap();
                res.put("path",Const.PREFIX_SDCARD+path);
                callback.invoke(res);
            }
        }).start();

    }



    private static final int BLACK = 0xff000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static BarcodeFormat barcodeFormat= BarcodeFormat.CODE_128;
    public  static Bitmap creatBarcode(String contents, int desiredWidth,int desiredHeight) {
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result=null;
        try {
            result = writer.encode(contents, barcodeFormat, desiredWidth,
                    desiredHeight);
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        // All are 0, or black, by default
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }




    @JSMethod
    public void open(final HashMap param, final JSCallback callback){


        Perssion.check((Activity) mWXSDKInstance.getContext(), Manifest.permission.CAMERA,new PerssionCallback(){


            @Override
            public void onGranted() {


                Perssion.check((Activity) mWXSDKInstance.getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE , new PerssionCallback() {
                    @Override
                    public void onGranted() {
                        WXQRModule.this.param=param;
                        WXQRModule.this.callback=callback;
                        if(!EventBus.getDefault().isRegistered(WXQRModule.this))
                        {
                            EventBus.getDefault().register(WXQRModule.this);
                        }

                        dojob(param,callback);

                    }
                });



            }
        });

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PermissionEvent event) {

        if(event.type==PermissionEvent.CAMREA)
        {
            dojob(param,callback);
        }

    }

    void dojob(HashMap param, JSCallback callback)
    {

        String color=param.get("color")+"";
        String bgcolor=param.get("bgcolor")+"";
        Intent in=new Intent(this.mWXSDKInstance.getContext(), CaptureActivity.class);
        if(param.containsKey("color"))
            in.putExtra("titleColor",color);
        if(param.containsKey("bgcolor"))
            in.putExtra("bgColor",bgcolor);
        ((Activity)this.mWXSDKInstance.getContext()).startActivityForResult(in,2);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==2)
        {
            if(resultCode==1)
            {
                String url = data.getStringExtra("url");
                callback.invokeAndKeepAlive(url);
            }
        }

    }
}
