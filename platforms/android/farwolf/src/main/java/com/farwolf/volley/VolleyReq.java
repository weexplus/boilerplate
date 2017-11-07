package com.farwolf.volley;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farwolf.interfac.IHttp;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public abstract class VolleyReq {
	
	protected HashMap<String,String> param=new HashMap<String,String>();
	protected HashMap<String,String> header=new HashMap<String,String>();
	public int timeout=30*1000;
    int retryTimes=0 ;
	public static RequestQueue que;
    StringRequest req;
    

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}
	public static void initQue(RequestQueue que)
	{
		VolleyReq.que=que;
	}

	String cookie; 
	public String getCookie() {
		return cookie;
	}
	public abstract String getUrl();
	
	Context context;
	public VolleyReq(Context context) {
		super();
		this.context = context;
		if(que==null)
		que=getRequestQueue();
	}
	public void addParams(HashMap<String,String> param)
	{
		this.param.putAll(param);
	}
	public void addParam(String key,String value)
	{
		param.put(key, value);
	}
	public void addParam(String key,Object value)
	{
		param.put(key, value+"");
	}
	
	public void addParamNotNull(String key,Object value)
	{
		if(value!=null)
		param.put(key, value+"");
	}
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public void addHeader(String key,String value)
	{
		header.put(key, value);
	}
	public void addHeaders(HashMap header)
	{
		 this.header.putAll(header);
	}
	public void addCookie(String value)
	{
		header.put("Cookie",value);
	}

	public void excute(final IHttp l,boolean usePost)
	{
	
		if(l!=null)
		   {
			   l.OnPostStart(null);
		   }
		if(context==null)
		{
			l.OnPostCompelete(null);
			return;
		}
			
        String url=getUrl();

		int method=Method.POST;
		if(!usePost)
		{
			method=Method.GET;

			url+=prepareGetParams();

		}
		final String tempurl=url;
		Log.i("url", url);
		req=  new StringRequest(method, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				if(context==null )
				{
					if(context instanceof  Activity)
					{
						if(((Activity)context).isFinishing())
						{
							Log.i("销毁", "");
							req.cancel();
						}
					}

					return;
				}
			   if(l!=null)
			   {
				   l.OnPostCompelete(response);
			   }
			   
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

				Log.i("网络异常", tempurl);
				Log.i("网络异常", error+"");
				if(context==null||((Activity)context).isFinishing())
				{
					req.cancel();
					Log.i("销毁", "");
					return;
				}
				if(l!=null)
				   {
					   l.OnException(error);
				   }
			}
		})
		{
			
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				 Log.i("params", param+"");
				return param;
			}

			
			@Override
			protected Response<String> parseNetworkResponse(
					NetworkResponse response) {
				// TODO Auto-generated method stub
				 try {  
                     
	                    Map<String, String> responseHeaders = response.headers;  
	                    cookie = responseHeaders.get("Set-Cookie");  
//	                    Log.i("rawCookies", cookie+"");
	                    String dataString = new String(response.data, "UTF-8");  
	                    return Response.success(dataString,HttpHeaderParser.parseCacheHeaders(response));  
	                } catch (UnsupportedEncodingException e) {  
	                    return Response.error(new ParseError(e));  
	                } 
			}
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				return header;
			}
			
			
			
		 
			
			
			
			
		};
		DefaultRetryPolicy df=new DefaultRetryPolicy(timeout, retryTimes, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
		req.setRetryPolicy(df);
		req.setShouldCache(false);
		que.add(req);
		 
	 
	}


	public String prepareGetParams()
	{
		 Object []keys= param.keySet().toArray();
		String s="";
		for(Object key:keys)
		{
		   String value=param.get(key)+"";

			try {
				value = URLEncoder.encode(value, "utf-8");
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			s+=key+"="+value+"&";

		}
		if(keys.length>0)
		{
			s="?"+s;
			s=s.substring(0,s.length()-1);

		}

		return s;
	}



	public void cancle(){
	     if(req!=null)
	    	 req.cancel();
	}
	
	
	private RequestQueue getRequestQueue() {
		RequestQueue   mRequestQueue = Volley.newRequestQueue(context);

        File cacheDir = new File(context.getCacheDir(), "volley");
        DiskBasedCache cache = new DiskBasedCache(cacheDir);
        mRequestQueue.start();

        // clear all volley caches.
        mRequestQueue.add(new ClearCacheRequest(cache, null));
        return mRequestQueue;
    }

	
	
	
	

}
