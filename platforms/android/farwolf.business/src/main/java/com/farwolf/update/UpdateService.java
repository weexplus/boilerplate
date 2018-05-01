package com.farwolf.update;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.farwolf.json.JsonReader;
import com.farwolf.json.ProgressJsonListner;
import com.farwolf.reader.FarwolfReq;
import com.farwolf.util.AppMainfest;
import com.farwolf.util.SDCard;
import com.farwolf.view.FreeDialog;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by zhengjiangrong on 2017/4/12.
 */
@EBean
public class UpdateService {

    @Pref
    UpdatePref_ pref;

    @Bean
    AppMainfest appMainfest;



    @RootContext
    Context context;

    String appid;

    String vcurl;

    String theme;

    public void init(String appid,String vcurl,String theme)
    {
        this.appid=appid;
        this.vcurl=vcurl;
        this.theme=theme;

    }

    public void doCheck(final boolean failtoast,final boolean showprogress)
    {


        FarwolfReq f=new FarwolfReq<UpdateReader>(context)
        {
            @Override
            public String getUrl() {
                return  vcurl;
            }

            @Override
            public UpdateReader getJson(String s) {
                return new UpdateReader(s);
            }
        };
        f.addParam("appid",appid);
        f.addParam("systype","1");
        f.addParam("vcode",appMainfest.getVersionCode());
//        f.addParam("vcode","0");
//        f.addParam("source","");

        f.excute(new ProgressJsonListner(context) {

            @Override
            public void start() {
                if(showprogress)
                    super.start();
            }

            @Override
            public String getTitle() {
                return "检测中";
            }

            @Override
            public void compelete() {
                if(showprogress)
                    super.compelete();
            }


            @Override
            public void fail(JsonReader j, String code, String msg) {
                if(failtoast)
                {
                    Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void exception(Object o) {

                if(failtoast)
                {
                    super.exception(o);
                }
            }

            @Override
            public void success(JsonReader j) {
                UpdateDialog a= UpdateDialog_.build(context);
                FreeDialog f=new FreeDialog(context,a);
                Version v=  j.toBean("version",Version.class);
                a.f=f;
                f.setCanceledOnTouchOutside(false);
                f.setCancelable(v.level!=2);
                a.init(v,theme);
                if(v.level==0)
                {
                    if(v.versionName!=null&&v.versionName.equals(pref.version().get()))
                    {
                        long delt= System.currentTimeMillis()-pref.time().get();
                        long week=1000*60*60*24*7;
                        long min=1000*20;
                        if(delt<min)
                        {
                            return;
                        }
                        else
                        {
                            pref.edit().time().put(0);
                            pref.edit().version().put("");
                            f.show();
                        }

                    }
                    else
                    {

                        f.show();
                    }
                }
                else
                {
                    f.show();
                }

            }
        });

    }



    public void doCheckJs(String jsversion,final boolean failtoast,final boolean showprogress)
    {


        FarwolfReq f=new FarwolfReq<UpdateReader>(context)
        {
            @Override
            public String getUrl() {
                return  vcurl;
            }

            @Override
            public UpdateReader getJson(String s) {
                return new UpdateReader(s);
            }
        };
        f.addParam("appid",appid);
        f.addParam("systype","1");
        f.addParam("jsversion",jsversion);
        f.addParam("nativeversion",appMainfest.getVersionCode());
//        f.addParam("vcode","0");
//        f.addParam("source","");

        f.excute(new ProgressJsonListner(context) {

            @Override
            public void start() {
                if(showprogress)
                    super.start();
            }

            @Override
            public String getTitle() {
                return "检测中";
            }

            @Override
            public void compelete() {
                if(showprogress)
                    super.compelete();
            }


            @Override
            public void fail(JsonReader j, String code, String msg) {
                if(failtoast)
                {
                    Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void exception(Object o) {

                if(failtoast)
                {
                    super.exception(o);
                }
            }

            @Override
            public void success(JsonReader j) {

                JsVersion v=  j.toBean("version",JsVersion.class);
                if(v.mode==1)
                {
                    UpdateJsDialog a= UpdateJsDialog_.build(context);
                    FreeDialog f=new FreeDialog(context,a);
                    a.f=f;
                    f.setCanceledOnTouchOutside(false);
                    f.setCancelable(false);
                    a.url=v.url;
                    a.size=v.size;
                    a.path=SDCard.getBasePath(context)+"zip/app.zip";
                    f.show();
                    a.start();
                }
                else if(v.mode==0)
                {
                    new JsDownloader().start(v.url, context, null);

                }



            }
        });

    }




    public  File getFileFromServer(String path, ProgressDialog pd) throws Exception{
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的

        URL url = new URL(path);
        HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        //获取到文件的大小
        pd.setMax(conn.getContentLength());
        InputStream is = conn.getInputStream();
        File file = new File(SDCard.getBasePath(context), "updata.apk");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] buffer = new byte[1024];
        int len ;
        int total=0;
        while((len =bis.read(buffer))!=-1){
            fos.write(buffer, 0, len);
            total+= len;
            //获取当前下载量
            pd.setProgress(total);
        }
        fos.close();
        bis.close();
        is.close();
        return file;

    }
    private class  UpdateReader extends JsonReader
    {

        public UpdateReader(String s) {
            super(s);
        }

        @Override
        public <T> List<T> toList(Class<T> c) {
            return null;
        }

        @Override
        public String getErrMsg() {
            return j.optString("msg");
        }

        @Override
        public String getErrCode() {
            return j.optString("error");
        }

        @Override
        public boolean isEnd() {
            return false;
        }
    }






}
