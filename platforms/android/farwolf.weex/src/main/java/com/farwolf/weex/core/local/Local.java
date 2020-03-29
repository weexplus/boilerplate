package com.farwolf.weex.core.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.farwolf.util.AppTool;
import com.farwolf.util.AppTool_;
import com.farwolf.util.FileTool;
import com.farwolf.util.SDCard;
import com.farwolf.util.ZipHelper;
import com.farwolf.weex.bean.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.farwolf.util.SDCard.getBasePath;

/**
 * Created by zhengjiangrong on 2018/5/1.
 */

public class Local  {



    public static String getDiskBasePath(Context context){
        return  context.getExternalFilesDir("Caches")+"/";
    }


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
        AppTool appTool=AppTool_.getInstance_(c);
        if(!isDiskExist(c)||Config.assetJsVersion(c)>Config.diskJsVersion(c)||appTool.isDebugMode())
        {
            if(!FileTool.IsFileExist(newpath))
            {
                FileTool.makeDir(newpath);
            }else{
                FileTool.delete(newpath);
                FileTool.makeDir(newpath);
            }

            FileTool.copyAssets(c,"app",newpath);
        }
        if(isDiskExist(c))
        {
//            &&Config.assetJsVersion(c)>Config.diskJsVersion(c)
            SharedPreferences sharedPreferences = c.getSharedPreferences("farwolf_weex", Context.MODE_PRIVATE); //私有数据
            int version= sharedPreferences.getInt("downloadJsVersion",-1);

            if(version>Config.diskJsVersion(c)&&version>Config.assetJsVersion(c))
            {
                 if(isZipExist(c))
                 {
                     unzip(c);
                     sharedPreferences.edit().remove("downloadJsVersion").apply();
                 }

            }

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

        try {
            Asset a=new Asset();
            String path= SDCard.getBasePath(c)+"/zip/app.zip";
            InputStream is=new FileInputStream(new File(path));
            String to= SDCard.getBasePath(c)+"";
            File f=new File(to+"/app");
            if(f.exists())
            {
                FileTool.deleteFile(f);
            }
            ZipHelper.unZipFile(is,to);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    final String path= SDCard.getBasePath(c)+"/zip/app.zip";
//    String zip=SDCard.getBasePath(c)+"/zip";

    public static boolean isZipExist(Context c)
    {
        String path= SDCard.getBasePath(c)+"/zip/app.zip";
        File f=new File(path);
        return f.exists();
    }
    public static boolean isDiskExist(Context c)
    {
        String path= getBasePath(c);
        path+="/app/weexplus.json";
        File f=new File(path);
        return f.exists();
    }








}
