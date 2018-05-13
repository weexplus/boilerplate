package com.farwolf.weex.module;


import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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


                    Log.i("back",s);
                    HashMap res=new HashMap();
                    res.put("res",JSONObject.parse(s));
                    String cookie=response.headers().get("Set-Cookie");
                    res.put("sessionid",cookie);

                    if(success!=null)
                        success.invoke(res);
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

                HashMap m=new HashMap();
                Map maps = (Map) JSON.parse(s);
                m.put("res",maps);
                success.invoke(m);
            }

            @Override
            public void exception(Object o) {
                if(exception!=null)
                    exception.invoke(o);
            }
        });
    }



    private void ChangeImage() {

//        OkHttpClient okHttpClient=new OkHttpClient();
//
//        RequestBody body = new FormBody.Builder().add("useName", "addd").add("pwd", "123").build();
//        Request request = new Request.Builder()
//                .url("http://192.168.12.143:9090/login/")
//
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//
//                //获取session的操作，session放在cookie头，且取出后含有“；”，取出后为下面的 s （也就是jsesseionid）
//                Headers headers = response.headers();
//                Log.d("info_headers", "header " + headers);
//            }
//        });



//        String path = "http://192.168.12.143:9090/login/";

        // 2 创建okhttpclient对象
//        OkHttpClient client = new OkHttpClient();
//
//        FormBody body = new FormBody.Builder().add("usename", "CHUANGTAI").add("password", "111111").build();
//        // 3 创建请求方式
//        Request request = new Request.Builder().url(path).post(body).build();
//
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Headers headers = response.headers();
//                Log.d("info_headers", "header " + headers);
//            }
//        });
        // 4 执行请求操作
//        try {
//            Response response = client.newCall(request).execute();
//            Headers dears=response.headers();
//            if(response.isSuccessful()){
//                String string = response.body().string();
//                System.out.println(string);
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }





}
