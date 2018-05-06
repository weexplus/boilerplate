package com.farwolf.weex.core.local;

import android.content.Context;
import android.graphics.Bitmap;

import com.farwolf.util.FileTool;
import com.farwolf.util.SDCard;
import com.farwolf.util.ZipHelper;
import com.farwolf.weex.bean.Config;

import java.io.File;
import java.io.InputStream;

import static com.farwolf.util.SDCard.getBasePath;

/**
 * Created by zhengjiangrong on 2018/5/1.
 */

public class Local  {






    public static String getString(Context c,String path)
    {
        return  getLocalManager(c).getString(c,path);
    }


    public static Bitmap getBitmap(Context c, String path)
    {
        return  getLocalManager(c).getBitmap(c,path);
    }


    public static InputStream getFileInputStream(Context c, String path)
    {
        return  getLocalManager(c).getFileInputStream(c,path);
    }



    public static void copyAssetToDisk(Context c)
    {
        String newpath= getBasePath(c)+"/app";

        int aj=Config.assetJsVersion(c);
        int dj=-1;
        if(isDiskExist(c))
        {
            dj=Config.diskJsVersion(c);
        }
        if(!isDiskExist(c)||Config.assetJsVersion(c)>Config.diskJsVersion(c))
        {
            if(!FileTool.IsFileExist(newpath))
            {
                FileTool.makeDir(newpath);
            }
            FileTool.copyAssets(c,"app",newpath);
        }



    }

    public static ILocal getLocalManager(Context c)
    {
//        return new Asset();
        if(isDiskExist(c))
        {
            return new Disk();
        }
        else
        {
            return new Asset();
        }

    }


    public static ILocal getDiskManager(Context c)
    {
        return new Disk();

    }


    public static ILocal getAssetManager(Context c)
    {
        return new Asset();

    }


    public static void unzip(Context c)
    {

        Asset a=new Asset();
        InputStream is= a.getFileInputStream(c,"app/app.zip");
        String to= SDCard.getBasePath(c)+"";
        File f=new File(to+"/app");
        if(f.exists())
        {
            f.delete();
        }
        ZipHelper.unZipFile(is,to);
    }


    public static boolean isDiskExist(Context c)
    {
        String path= getBasePath(c);
        path+="/app";
        File f=new File(path);
        return f.exists();
    }








}
