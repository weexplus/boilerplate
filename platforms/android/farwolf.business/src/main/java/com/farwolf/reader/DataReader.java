package com.farwolf.reader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.farwolf.net.HttpTool;
import com.google.gson.Gson;


public abstract class DataReader<T> {

	JSONObject head=new JSONObject();
	JSONObject body=new JSONObject();
	protected Gson gson=new Gson();	
	HashMap param=new HashMap();
	HashMap header=new HashMap();
	HashMap<String,InputStream> inputStream=new HashMap<String,InputStream>();
//	public  final static  String httpUrl="http://10.0.2.2:8080/edu/"; 
//	public  final static  String httpUrl="http://192.168.0.137:8080/edu/"; 
//	public  final static  String httpUrl="http://219.140.199.91:9080/edu/"; 
//	public  final static  String httpUrl="http://192.168.3.82:8080/edu/"; 
//	public  final static  String httpUrl="http://192.168.1.107:8080/edu/"; 
//	public  final static  String httpUrl="http://121.40.83.38:8080/edu/"; 
//	public  final static  String httpUrl="http://www.app-hust-snde.com:9080/edu/"; 
	public String orgin;
	 public boolean IsSuccess()
     {
          return 0==getErrorCode();
     }	
	
	 public int getErrorCode() {
		try {
			return head.getInt("errorCode");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
	 }
	 
	 
	 public Object getHeadValue(String key)
	 {
		 try {
			return head.get(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	 public Object getBodyValue(String key)
	 {
		 try {
			 return body.get(key);
		 } catch (JSONException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 return null;
	 }
	 

	 public String getErrorMsg() {
		 try {
				return head.getString("errorMsg");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
	 }
	  public abstract String getInterfaceName();
	  public  void AddParam(String key,String value)
	  {
		  param.put(key, value);
	  }

	  public  void AddHeader(String key,String value)
	  {
		  header.put(key, value);
	  }
	  public  void AddStream(String key,InputStream value)
	  {
		  inputStream.put(key, value);
	  }
	  
	  public DataReader(String s)
	  {
		  this.load(s);
	  }
	  public DataReader( )
	  {
		 
	  }
	  
	  public abstract String getUrl();
	  
	  
	  void load(String s)
	  {
		  this.orgin=s;
		  try {
				JSONObject j=new JSONObject(s);
				head=(JSONObject) j.get("head");
				body=(JSONObject) j.get("body");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	 public  DataReader<T> excute() throws Exception
	 {
		    String url=this.getUrl()+this.getInterfaceName();						 
			Log.i("url", url);
			String s= HttpTool.post(url, param,header);		
			Log.i("back", s);
			 load(s);
			return this;
			
		  
	 }
	 public  DataReader<T> get() throws Exception
	 {
		 String url=this.getUrl()+this.getInterfaceName();						 
		 Log.i("url", url);
		 String s= HttpTool.get(url, param,header);		
		 Log.i("back", s);
		 load(s);
		 return this;
	 }
	 public  DataReader<T> postFile() throws Exception
	 {
		 String url=this.getUrl()+this.getInterfaceName();			
		 
		 Log.i("url", url);
 
		 String s= HttpTool.postFile(url, param, header, inputStream);
		 
		 Log.i("back", s);
		 load(s);
		 return this;
		 
		 
	 }
	 public String getDefaultTable()
	 {
		 try {
			 if(body.has("default"))
			return body.getJSONArray("default")+"";
			 else
				 return "";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return "";
		}
	 }
	 public abstract List<T> getList();
}
