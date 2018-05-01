package com.farwolf.weex.core.local;

import android.content.Context;
import android.graphics.Bitmap;

import com.farwolf.util.SDCard;

import java.io.InputStream;

/**
 * Created by zhengjiangrong on 2018/5/1.
 */

public class Disk implements ILocal {

    @Override
    public String getString(Context c, String path) {



        return SDCard.getString(c,path);
    }

    @Override
    public Bitmap getBitmap(Context c, String path) {
        return SDCard.getBitMap(c,path);
    }

    @Override
    public InputStream getFileInputStream(Context c, String path) {

        return SDCard.getFileStream(c,path);
    }
}
