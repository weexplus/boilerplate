package com.farwolf.update;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import com.farwolf.interfac.IFullHttp;
import com.farwolf.util.AppTool;
import com.farwolf.util.Downloader;
import com.farwolf.util.FileTool;
import com.farwolf.util.SDCard;
import com.farwolf.util.ZipHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by zhengjiangrong on 2018/5/1.
 */

public class JsDownloader  {



    public void start(String url,final int version,final int mode, final Context c, final IFullHttp listener)
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



                    SharedPreferences sharedPreferences = c.getSharedPreferences("farwolf_weex", Context.MODE_PRIVATE); //私有数据
                    SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                    editor.putInt("downloadJsVersion", version).commit();
                    if(listener!=null)
                        listener.OnPostCompelete(o);


                    if(mode==2)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder((Activity)c);
                        builder.setTitle("请注意");
                        builder.setMessage("更新包️已下载完毕,是否退出应用重新加载?");//提示内容
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
//                                    Toast.makeText(HybridCore.getInstance().getPageManager().getCurrentActivity(),"请给予权限!",Toast.LENGTH_SHORT).show();
                                try {
                                    FileInputStream fs=new FileInputStream(new File(path));
                                    String to= SDCard.getBasePath(c)+"";
                                    File f=new File(to+"/app");
                                    if(f.exists())
                                    {
                                        FileTool.deleteFile(f);
                                    }
                                    ZipHelper.unZipFile(fs,SDCard.getBasePath(c));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                AppTool.exit((Activity) c);
                                return;
                            }
                        });
                        builder.show();
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
