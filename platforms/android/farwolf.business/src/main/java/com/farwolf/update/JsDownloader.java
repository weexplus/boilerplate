package com.farwolf.update;

import android.content.Context;

import com.farwolf.interfac.IFullHttp;
import com.farwolf.util.Downloader;
import com.farwolf.util.SDCard;
import com.farwolf.util.ZipHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by zhengjiangrong on 2018/5/1.
 */

public class JsDownloader  {



    public void start(String url, final Context c, final IFullHttp listener)
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
            public void OnPostProcess(int newProgress) {
                if(listener!=null)
                listener.OnPostProcess(newProgress);
            }

            @Override
            public void OnPostStart(Object o) {
                if(listener!=null)
                listener.OnPostStart(o);
            }

            @Override
            public void OnPostCompelete(Object o) {

                try {
                    FileInputStream fs=new FileInputStream(new File(path));
                    ZipHelper.unZipFile(fs,SDCard.getBasePath(c));
                    if(listener!=null)
                    listener.OnPostCompelete(o);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
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


}
