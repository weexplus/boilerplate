package com.farwolf.weex.core.local;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.InputStream;

/**
 * Created by zhengjiangrong on 2018/5/1.
 */

public interface ILocal {

    public String getString(Context c, String path);



    public Bitmap getBitmap(Context c, String path);

    public InputStream getFileInputStream(Context c, String path);


}
