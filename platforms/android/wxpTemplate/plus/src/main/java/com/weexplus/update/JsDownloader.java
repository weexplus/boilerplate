package com.weexplus.update;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import com.weexplus.util.AppTool;
import com.weexplus.util.Const;
import com.weexplus.util.Downloader;
import com.weexplus.util.FileTool;
import com.weexplus.util.IFullHttp;
import com.weexplus.util.Path;
import com.weexplus.util.Pref;
import com.weexplus.util.SDCard;
import com.weexplus.util.ZipHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by zhengjiangrong on 2018/5/1.
 */

public class JsDownloader {



    public void start(String url,final int version,final int mode,final String module, final Context c, final IFullHttp listener)
    {

        final String path= SDCard.getBasePath(c)+"/zip/app.zip";
        String zip=SDCard.getBasePath(c)+"/zip";
        File f= new File(zip);
        if(f.exists())
        {
            f.delete();
        }
        Downloader downloader= new Downloader(new IFullHttp() {
            @Override
            public void OnPostProcess(float newProgress,float current,float total) {
                if(listener!=null)
                listener.OnPostProcess(newProgress,current,total);
            }

            @Override
            public void OnPostStart(Object o) {
                if(listener!=null)
                listener.OnPostStart(o);
            }

            @Override
            public void OnPostCompelete(Object o) {
                    if(listener!=null)
                        listener.OnPostCompelete(o);

                    if(Const.MAIN.equals(module)){
                        Pref.setInt(c,"downloadJsVersion",version);
                    }

                    if(mode==2&&Const.MAIN.equals(module))
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder((Activity)c);
                        builder.setTitle("请注意");
                        builder.setMessage("更新包️已下载完毕,是否退出应用重新加载?");//提示内容
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                unzip(path,module,c);
                                if(Const.MAIN.equals(module)){
                                    AppTool.exit((Activity) c);
                                }

                            }
                        });
                        builder.show();
                    }else{
                        unzip(path,module,c);
                    }






            }

            @Override
            public void OnException(Object o) {
                if(listener!=null)
                listener.OnException(o);
            }
        });

        downloader.execute(url,path);


    }


    void unzip(String path,String module,Context c){
        try {
            FileInputStream fs=new FileInputStream(new File(path));
            String to= SDCard.getBasePath(c)+"/modules/"+module;
            File f=new File(to);
            if(f.exists())
            {
                FileTool.deleteFile(f);
            }
            String zip=  SDCard.getBasePath(c)+"/app";
            ZipHelper.unZipFile(fs,SDCard.getBasePath(c));
            new File(zip).renameTo(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
