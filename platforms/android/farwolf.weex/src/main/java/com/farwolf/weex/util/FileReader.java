package com.farwolf.weex.util;

import com.farwolf.net.HttpTool;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.util.HashMap;

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
			public void onStart(Request<String, ? extends Request> request) {
				j.start();
			}

//			@Override
//			public void onBefore(BaseRequest request) {
//				 j.start();
//			}


			@Override
			public void onSuccess(com.lzy.okgo.model.Response<String> response) {
				j.success(response.body());
			}

			@Override
			public void onFinish() {
				super.onFinish();
				j.compelete();
			}

			@Override
			public void onError(com.lzy.okgo.model.Response<String> response) {
				super.onError(response);
				j.exception(null);
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
