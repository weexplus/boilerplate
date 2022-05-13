package com.weexplus.core.render;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.taobao.weex.utils.WXFileUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhengjiangrong on 2018/5/1.
 */

public class Asset implements ILocal {



    public String getString(Context c,String path)
    {
        return WXFileUtils.loadAsset(path,c);
    }


    public Bitmap getBitmap(Context c, String path)
    {
        return  loadAssetImage(path,c);
    }


    public static Bitmap loadAssetImage(String src,Context context)
    {

        try {
            InputStream is = context.getAssets().open(src);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        }
        catch (IOException io)
        {
            return null;
        }

    }
    public boolean isExist(Context c){
        String q[]= new String[0];
        try {
            q = c.getAssets().list("modules/main");
        } catch (IOException e) {
            return false;
        }
        return q.length>0;
    }

    public InputStream getFileInputStream(Context c, String path)
    {
        try {

            InputStream inputStream = c.getAssets().open(path);
            return inputStream;
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        return null;
    }








}
