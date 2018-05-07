package com.farwolf.weex.util;

import android.support.annotation.Nullable;

import com.farwolf.net.HttpTool;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.io.File;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class FileReader   {


//	public static   String Url="http://192.168.2.11:8080/webroot/upload/";
//	public static   String Url="http://koto.renturbo.com:9080/upload/";
	public static   String Url="";
//	public static   String Url=AppInfo.KOTO_URL;
	public static   String token;

	 HashMap<String, File> file=new HashMap<String, File>();

	


	public void excute(String url,HashMap param,HashMap header,final HttpListener j) {
		// TODO Auto-generated method stub
//		 new MyTask(j).execute();
//		new MyTask(url,header,param,j).execute();


		HttpTool.upload(url, new StringCallback() {

			@Override
			public void onBefore(BaseRequest request) {
				 j.start();
			}

			@Override
			public void onSuccess(String s, Call call, Response response) {

				j.success(s);
			}

			@Override
			public void onAfter(@Nullable String s, @Nullable Exception e) {
				 j.compelete();
			}


			@Override
			public void onError(Call call, Response response, Exception e) {
				 j.exception(e);
			}
		},param,header,file);
		
	
	}

	public interface HttpListener {


		public void start();
		public void compelete();
		public void success(String j);
		public void exception(Object o);




	}


	public void addFile(String key,File is)
	{
		file.put(key, is);
	}
	

 

}
