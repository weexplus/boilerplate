package com.weexplus.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.PostRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HttpTool {









    public static String postJson(String strURL, HashMap param,HashMap header) {
        Iterator it=   param.keySet().iterator();
        JSONObject js=new JSONObject();
        while(it.hasNext())
        {
            String key=it.next()+"";
            try {
                js.put(key, param.get(key)+"");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
//            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            if(header!=null)
            {
                Iterator itx=   header.keySet().iterator();
                while(itx.hasNext())
                {
                    String key=itx.next()+"";
                    connection.setRequestProperty(key	, header.get(key)+"");
                }
            }
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(js.toString());
            out.flush();
            out.close();
            // 读取响应



            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            return sb+"";



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }

    public static String  post(String url,HashMap param,HashMap header)
    {
        BufferedReader in = null;
        try {
            HttpClient client = new DefaultHttpClient();
//	            HttpClient client = new DefaultHttpClient(params)
            HttpParams parms = new BasicHttpParams();

            HttpPost request = new HttpPost(url);

            //使用NameValuePair来保存要传递的Post参数
            List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            //添加要传递的参数
            if(param!=null)
            {
                Iterator it=   param.keySet().iterator();
                while(it.hasNext())
                {
                    String key=it.next()+"";
                    postParameters.add(new BasicNameValuePair(key, param.get(key)+""));
                }
            }

            if(header!=null)
            {
                Iterator itx=   header.keySet().iterator();
                while(itx.hasNext())
                {
                    String key=itx.next()+"";
                    request.addHeader(key	, header.get(key)+"");
                }
            }

//	         request.addHeader("Content-Type", "application/x-www-form-urlencoded");
//	         request.addHeader("Content-Type", "txt/html");

            //实例化UrlEncodedFormEntity对象
            HttpEntity entity = new UrlEncodedFormEntity(postParameters,HTTP.UTF_8);

//	            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
//	            formEntity.setContentEncoding("utf-8");
            //使用HttpPost对象来设置UrlEncodedFormEntity的Entity
            request.setEntity(entity);

            HttpResponse response = client.execute(request);
            in = new BufferedReader(
                    new InputStreamReader(
                            response.getEntity().getContent()));

            StringBuffer string = new StringBuffer("");
            String lineStr = "";
            while ((lineStr = in.readLine()) != null) {
                string.append(lineStr + "\n");
            }
            in.close();

            String resultStr = string.toString();
            return resultStr;

        } catch(Exception e) {
            e.printStackTrace();
            // Do something about exceptions
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }



    public static String get(String stUrl,HashMap param,HashMap header)  {
        try {
        URL url = null;
        String data="";
        if(param!=null&&param.size()>0)
        {

            Iterator it=   param.keySet().iterator();
            while(it.hasNext())
            {
                String key=it.next()+"";
                String value=param.get(key)+"";
                data+=key+"="+value+"&";
            }
            if(stUrl.endsWith("&"))
            {
                data=stUrl.substring(0, stUrl.length()-1);
            }
            stUrl+="?"+data;

        }
        url = new URL(stUrl);
        //打开服务器
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //连接服务器
        int timeout=  param.containsKey("timeout")?Integer.parseInt(param.get("timeout")+""):30000;
        conn.setConnectTimeout(timeout);
        conn.setReadTimeout(timeout);
        conn.setUseCaches(false);
        conn.connect();
        //得到输入流
       InputStream is = conn.getInputStream();
         BufferedReader  br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        //将数据存储在StringBuffere中
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb+"";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return null;
    }



    public static void upload(String url, StringCallback callback, HashMap param, HashMap header, HashMap files) {


        PostRequest post= OkGo.<String>post(url)
                .tag(url);

        Object keys[]= param.keySet().toArray();
        for(Object key:keys)
        {
            post.params(new com.lzy.okgo.model.HttpParams(key+"",param.get(key)+""));
//			post=post.params(key+"",param.get(key)+"",false);
        }
        Object headerkeys[]= header.keySet().toArray();
        for(Object key:headerkeys)
        {
            post.headers(new HttpHeaders(key+"",header.get(key)+""));
//			post=post.headers(key+"",header.get(key)+"");
        }
        Object filekeys[]= files.keySet().toArray();
        for(Object key:filekeys)
        {
            ArrayList arry=new ArrayList<File>();
            arry.add(files.get(key));
            post.addFileParams(key+"",arry);
        }

        post.execute(callback);




    }

    public static String postFile(String url,HashMap param,HashMap header,HashMap<String,InputStream> file) throws ClientProtocolException, IOException, JSONException {
        HttpClient httpclient = new DefaultHttpClient();
        //设置通信协议版本
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpPost httppost = new HttpPost(url);
        MultipartEntity mpEntity = new MultipartEntity(); //文件传输

        if(param!=null)
        {
            Iterator it=   param.keySet().iterator();
            while(it.hasNext())
            {
                String key=it.next()+"";
                mpEntity.addPart(key, new StringBody(param.get(key)+"",Charset.forName("UTF-8")));
            }
        }
        if(header!=null)
        {
            Iterator itx=   header.keySet().iterator();
            while(itx.hasNext())
            {
                String key=itx.next()+"";
                httppost.addHeader(key	, header.get(key)+"");
            }
        }
        if(file!=null)
        {
            Iterator it=   file.keySet().iterator();
            while(it.hasNext())
            {
                String key=it.next()+"";
                mpEntity.addPart(key, new InputStreamBody(file.get(key),key));

            }
        }


        httppost.setEntity(mpEntity);
        System.out.println("executing request " + httppost.getRequestLine());

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity resEntity = response.getEntity();

        System.out.println(response.getStatusLine());//通信Ok
        String s="";
        String path="";
        if (resEntity != null) {
            //System.out.println(EntityUtils.toString(resEntity,"utf-8"));
            s=EntityUtils.toString(resEntity,"utf-8");
            return s;
        }
        if (resEntity != null) {
            resEntity.consumeContent();
        }

        httpclient.getConnectionManager().shutdown();
        return path;
    }




    public static Bitmap getBitMap(String url)
    {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpGet httpget = new HttpGet(url);

        try {
            HttpResponse resp = httpclient.execute(httpget);
            //判断是否正确执行
            if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
                //将返回内容转换为bitmap
                HttpEntity entity = resp.getEntity();
                InputStream in = entity.getContent();
                return BitmapFactory.decodeStream(in);

                //向handler发送消息，执行显示图片操作

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return null;
    }


    public static HashMap postErr(String ul,HashMap param, HashMap header){
        String message="";
        HashMap res=new HashMap();
        try {
            URL url=new URL(ul);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            int timeout=  param.containsKey("timeout")?Integer.parseInt(param.get("timeout")+""):30000;
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            Log.i("weexplus", "timeout="+timeout);
//            connection.setRequestProperty("Content-type","application/x-www-form-urlencoded");
//            connection.setRequestProperty("Content-type","application/json;charset=UTF-8");
            connection.connect();
            OutputStream outputStream=connection.getOutputStream();
            StringBuffer sb=new StringBuffer();
            Iterator it = param.keySet().iterator();
            JSONObject js = new JSONObject();
            while(it.hasNext()) {
                String key = it.next() + "";
//                    js.put(key, param.get(key) + "");
                sb.append(key+"="+param.get(key));
                if(it.hasNext()){
                    sb.append("&");
                }

            }
            if (header != null) {
                Iterator itx = header.keySet().iterator();
                while(itx.hasNext()) {
                    String key = itx.next() + "";
                    connection.setRequestProperty(key, header.get(key) + "");
                }
            }
            String params=sb.toString();
            outputStream.write(params.getBytes());
            outputStream.flush();
            outputStream.close();
            Log.d("ddddd","responseCode"+connection.getResponseCode());
            int code=connection.getResponseCode();
            if(code<200||code>299){
                res.put("code",code);
                return  res;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb1 = new StringBuffer("");
            String lines;
            while((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb1.append(lines);
            }
            message=sb1.toString();
            connection.disconnect();
            res.put("code",0);
            res.put("res",message);

        } catch (Exception e) {
            e.printStackTrace();
            res.put("code",-1);
            res.put("res",e.getMessage());
        }
        return res;

    }

}
