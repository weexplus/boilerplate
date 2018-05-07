package com.farwolf.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
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
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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
	
	 
	   
	   

		public static String get(String url,HashMap param,HashMap header)  {
			BufferedReader in = null;

			String content = null;
			try {
				// 定义HttpClient
				HttpClient client = new DefaultHttpClient();
				// 实例化HTTP方法
				HttpGet request = new HttpGet();
			    if(header!=null)
	            {
	            	Iterator itx=   header.keySet().iterator();
	  	            while(itx.hasNext())
	  	            {
	  	            	String key=itx.next()+"";
	  	            	request.addHeader(key	, header.get(key)+"");
	  	            }
	            }
			    if(param!=null&&param.size()>0)
	            {
			    	 url+="?";
	            	 Iterator it=   param.keySet().iterator();
	 	            while(it.hasNext())
	 	            {
	 	            	String key=it.next()+"";
	 	            	String value=param.get(key)+"";
	 	            	url+=key+"="+value+"&";	 	            	 
	 	            }
	 	            if(url.endsWith("&"))
	 	            {
	 	            	url=url.substring(0, url.length()-1);
	 	            }
	 	             	            
	            }
				request.setURI(new URI(url));
				HttpResponse response = client.execute(request);

				in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while ((line = in.readLine()) != null) {
					sb.append(line + NL);
				}
				in.close();
				content = sb.toString();
			} finally {
				if (in != null) {
					try {
						in.close();// 最后要关闭BufferedReader
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return content;
			}
		}



	public static void upload(String url,StringCallback callback,HashMap param,HashMap header,HashMap files) {


		PostRequest post= OkGo.post(url)
				.tag(url);
		Object keys[]= param.keySet().toArray();
		for(Object key:keys)
		{
			post=post.params(key+"",param.get(key)+"");
		}
		Object headerkeys[]= header.keySet().toArray();
		for(Object key:headerkeys)
		{
			post=post.headers(key+"",param.get(key)+"");
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


}
