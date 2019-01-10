package com.farwolf.weex.module;


import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.farwolf.interfac.IFullHttp;
import com.farwolf.util.Downloader;
import com.farwolf.util.Md5;
import com.farwolf.util.SDCard;
import com.farwolf.weex.util.Const;
import com.farwolf.weex.util.FileReader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.lzy.okgo.request.BaseRequest;
import com.lzy.okgo.request.PostRequest;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.Response;

/**
 * Created by zhengjiangrong on 2017/5/22.
 */

public class WXNetModule extends WXModule {



    public void fetch(boolean usepost,boolean usejson, final  String url, HashMap param,  HashMap header, final JSCallback start, final JSCallback success, final JSCallback compelete, final JSCallback exception)
    {
        BaseRequest req= OkGo.get(url);
        OkGo.getInstance().setCookieStore(new PersistentCookieStore());
        if(usepost)
        {
            req=OkGo.post(url);

        }
        req=req.tag(this);
        if(!usejson)
        {
            Object []keys= param.keySet().toArray();
            for(Object key:keys)
            {
                req=req.params(key+"",param.get(key)+"");
            }
        }
        else
        {
            PostRequest preq=(PostRequest)req;
            JSONObject j=new JSONObject(param);
            preq.upJson(j.toJSONString());
            preq.headers("content-Type","application/json");
        }

        Object []hkeys= header.keySet().toArray();
        for(Object key:hkeys)
        {
            req=req.headers(key+"",header.get(key)+"");
        }

        try {
            req.cacheKey("cacheKey").cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {

                @Override
                public void onBefore(BaseRequest request) {
                    start.invokeAndKeepAlive(null);
                }

                @Override
                public void onSuccess(String s, Call call, Response response) {


                    try {
                        Log.i("back",s);
//                        HashMap res=new HashMap();
//                        res.put("res",JSONObject.parse(s));
                        String cookie=response.headers().get("Set-Cookie");
//                        res.put("sessionid",cookie);
//                        if(success!=null)
//                            success.invoke(res);
                        s+="";
                        s=s.trim();
                        HashMap m=new HashMap();
                        if(s.startsWith("{")){
                            Map maps = (Map)  JSONObject.parse(s);
                            m.put("res",maps);
                        } if(s.startsWith("[")){
                            JSONArray ja= JSONObject.parseArray(s);
                            m.put("res",ja);
                        }else{
                            m.put("res",s);
                        }
                        m.put("sessionid",cookie);
                        success.invoke(m);
                    }
                    catch (Exception e)
                    {
                        if(exception!=null)
                            exception.invoke(null);
                    }

                }

                @Override
                public void onError(Call call, Response response, Exception e) {
                    super.onError(call, response, e);
                    if(exception!=null)
                        exception.invoke(null);
                }

                @Override
                public void onAfter(@Nullable String s, @Nullable Exception e) {
                    if(compelete!=null)
                        compelete.invoke(s);
                }
            });
        }
        catch (Exception e)
        {
            if(exception!=null)
                exception.invoke(null);
        }








    }


    @JSMethod(uiThread = false)
    public String getSessionId(String url)
    {
        PersistentCookieStore p= new PersistentCookieStore();
        HttpUrl h=  HttpUrl.parse(url);
        List<Cookie> l=  p.loadCookies(h);
        for(Cookie c:l)
        {
            if("SESSION".equals(c.name()))
            {
                return c.name();
            }
        }
        return null;
    }

    @JSMethod(uiThread = false)
    public boolean hasSessionId(String url)
    {
        PersistentCookieStore p= new PersistentCookieStore();
        HttpUrl h=  HttpUrl.parse(url);
        List<Cookie> l=  p.loadCookies(h);
        for(Cookie c:l)
        {
            if("SESSION".equals(c.name()))
            {
                return true;
            }
        }
        return false;
    }

    @JSMethod
    public void removeAllCookies()
    {
        PersistentCookieStore p= new PersistentCookieStore();
        p.removeAllCookie();
    }

    /**
     *
     * @param url
     * @param param
     * @param header
     * @param start
     * @param success
     * @param compelete
     * @param exception
     */
    @JSMethod
    public void post(String url , HashMap param, HashMap header, final JSCallback start, final JSCallback success, final JSCallback compelete, final JSCallback exception)
    {
        this.fetch(true,false,url,param,header,start,success,compelete,exception);
    }

    /**
     *
     * @param url
     * @param param
     * @param header
     * @param start
     * @param success
     * @param compelete
     * @param exception
     */
    @JSMethod
    public void postJson(String url , HashMap param, HashMap header, final JSCallback start, final JSCallback success, final JSCallback compelete, final JSCallback exception)
    {

        this.fetch(true,true,url,param,header,start,success,compelete,exception);
    }


    /**
     *
     * @param url
     * @param param
     * @param header
     * @param start
     * @param success
     * @param compelete
     * @param exception
     */
    @JSMethod
    public void get(String url , HashMap param, HashMap header, final JSCallback start, final JSCallback success, final JSCallback compelete, final JSCallback exception)
    {
        this.fetch(false,false,url,param,header,start,success,compelete,exception);
    }


    /**
     *
     * @param url
     * @param param
     * @param header
     * @param path
     * @param start
     * @param success
     * @param compelete
     * @param exception
     */
    @JSMethod
    public void postFile(String url , HashMap param, HashMap header, HashMap path,final JSCallback start, final JSCallback success, final JSCallback compelete, final JSCallback exception)
    {
        FileReader f=new FileReader();
        Object []keys= path.keySet().toArray();
        for(Object s:keys)
        {
            String key=s+"";
            String p=path.get(s)+"";
            p=p.replace("sdcard:","");
            f.addFile(s+"",new File(p));
        }
        f.excute(url, param, header, new FileReader.HttpListener() {
            @Override
            public void start() {

                if(start!=null)
                    start.invoke(null);

            }

            @Override
            public void compelete() {
                if(compelete!=null)
                    compelete.invoke(null);
            }

            @Override
            public void success(String s) {

                s+="";
                s=s.trim();
                HashMap m=new HashMap();
                if(s.startsWith("{")){
                    Map maps = (Map)  JSONObject.parse(s);
                    m.put("res",maps);
                } if(s.startsWith("[")){
                    JSONArray ja= JSONObject.parseArray(s);
                    m.put("res",ja);
                }else{
                    m.put("res",s);
                }
                success.invoke(m);
            }

            @Override
            public void exception(Object o) {
                if(exception!=null)
                    exception.invoke(o);
            }
        });
    }


    @JSMethod
    public void download(String url,final JSCallback progress,final JSCallback compelete,final JSCallback exception)
    {

        final String path= SDCard.getBasePath(mWXSDKInstance.getContext())+"/download/"+Md5.toMd5(url);;
        String zip=SDCard.getBasePath(mWXSDKInstance.getContext())+"/download";
        File f= new File(zip);
        if(f.exists())
        {
            f.delete();
        }
        f= new File(zip);
        Downloader downloader= new Downloader(new IFullHttp() {

            @Override
            public void OnPostProcess(float newProgress,float current,float total) {

                HashMap m=new HashMap();
                m.put("percent",newProgress);
                m.put("current",current);
                m.put("total",total);
                progress.invokeAndKeepAlive(m);
            }

            @Override
            public void OnPostStart(Object o) {

            }

            @Override
            public void OnPostCompelete(Object o) {
                HashMap m=new HashMap();
                m.put("path", Const.PREFIX_SDCARD+path);
                compelete.invoke(m);
            }

            @Override
            public void OnException(Object o) {
                exception.invoke(new HashMap());
            }
        });

        downloader.execute(url,path);
    }





}
