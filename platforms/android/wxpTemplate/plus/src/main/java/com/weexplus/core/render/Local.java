package com.weexplus.core.render;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;


import com.weexplus.util.AppTool;
import com.weexplus.util.Config;
import com.weexplus.util.Const;
import com.weexplus.util.FileTool;
import com.weexplus.util.SDCard;
import com.weexplus.util.ZipHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;



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



    public static void copyAssetToDisk(Context c,String module)
    {
        String newpath= SDCard.getBasePath(c)+"/modules/"+module;
        int aj= Config.assetJsVersion(c);
        int dj=-1;
        if(isDiskExist(c))
        {
            dj=Config.diskJsVersion(c);
        }
        AppTool appTool=new AppTool(c);
        if(!isDiskExist(c)||Config.assetJsVersion(c)>Config.diskJsVersion(c)||appTool.isDebugMode())
        {
            if(!FileTool.IsFileExist(newpath))
            {
                FileTool.makeDir(newpath);
            }else{
                FileTool.delete(newpath);
                FileTool.makeDir(newpath);
            }

            FileTool.copyAssets(c,"modules/"+module,newpath);
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
                     unzip(c, Const.MAIN);
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


    public static void unzip(Context c,String module)
    {

        try {
            Asset a=new Asset();
            String path= SDCard.getBasePath(c)+"/zip/app.zip";
            InputStream is=new FileInputStream(new File(path));
            String to= SDCard.getBasePath(c)+"";
            File f=new File(to+"/modules/"+module);
            if(f.exists())
            {
                FileTool.deleteFile(f);
            }
            String zip=  SDCard.getBasePath(c)+"/app";
            ZipHelper.unZipFile(is,to);
            new File(zip).renameTo(f);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static boolean isZipExist(Context c)
    {
        String path= SDCard.getBasePath(c)+"/zip/app.zip";
        File f=new File(path);
        return f.exists();
    }
    public static boolean isDiskExist(Context c)
    {
        String path=SDCard.getBasePath(c);
        path+="/modules/main/weexplus.json";
        File f=new File(path);
        return f.exists();
    }








}
