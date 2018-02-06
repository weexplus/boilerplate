package com.farwolf.weex.util;

import android.os.AsyncTask;
import android.util.Log;

import com.farwolf.net.HttpTool;

import java.io.InputStream;
import java.util.HashMap;

public class FileReader   {


//	public static   String Url="http://192.168.2.11:8080/webroot/upload/";
//	public static   String Url="http://koto.renturbo.com:9080/upload/";
	public static   String Url="";
//	public static   String Url=AppInfo.KOTO_URL;
	public static   String token;

	 HashMap<String, InputStream> file=new HashMap<String, InputStream>();

	


	public void excute(String url,HashMap param,HashMap header,HttpListener j) {
		// TODO Auto-generated method stub
//		 new MyTask(j).execute();
		new MyTask(url,header,param,j).execute();
		
	
	}

	public interface HttpListener {


		public void start();
		public void compelete();
		public void success(String j);
		public void exception(Object o);




	}


	public void addFile(String key,InputStream is)
	{
		file.put(key, is);
	}
	
	 public class MyTask extends AsyncTask<String, String, String>
	 {


		 Exception ex;
		 HashMap param;
		 HttpListener j;
		 HashMap header;
		 String url;

		 public MyTask(String url, HashMap header, HashMap param, HttpListener j) {
			 this.url = url;
			 this.header = header;
			 this.j = j;
			 this.param = param;
		 }









		 @Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			 
		   j.start();
			
		}
		

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String s=null;
			try {
				s = HttpTool.postFile(url, param, header,file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ex=e;
			}  

			Log.i("backs",s+"");
			return s;
		}
		
		@Override
		protected void onPostExecute(String s) {
			// TODO Auto-generated method stub
			try
			{
				if(ex!=null)
				{
					this.j.exception(ex);
					this.j.compelete();
					return;
				}
				 this.j.success(s);
			}
			catch(Exception e)
			{
				this.j.exception(e);
			}
			finally
			{
				this.j.compelete();
			}
			 
		}
		
		 
		 
	 }
 

}
