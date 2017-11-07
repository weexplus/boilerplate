package com.farwolf.reader;



import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.farwolf.json.JsonReader;
import com.farwolf.json.JsonTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
 

 

public class Json  extends JsonReader{

	
 
    JSONObject head;
	JSONObject body;
	
 
	public String orgin;

	public Json(String s) {
		super(s);
		 
	}
	
	public void load(String s)
	{
		 super.load(s);
		try {
			 
			head=j.getJSONObject("head");
			body=j.getJSONObject("body");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getErrMsg()  
	{
		try {
			return head.getString("errorMsg");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public String getHeadString(String key)  
	{
		try {
			return head.getString(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public int getHeadInt(String key)  
	{
		try {
			return head.getInt(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	
	
	public boolean isEnd()
	{
		return getHeadInt("isend")==1;
	}
	
	public String getErrCode()  
	{
		try {
			return head.getString("errorCode");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "-1";
	}
	 
	
	
	
	
	public Object getHeadBean(String key,Class c)
	{
		 
		return getBean(key, head, c);
	}
	public Object getBodyBean(String key,Class c)
	{
		return getBean(key, body, c);
	}
	public String getTable(String key)
	{
		try {
			return body.getString(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Object getBean(String key,JSONObject j,Class c)
	{
		if(!j.has(key))
			return null;
		String s=null;
		try {
			s = j.getString(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		return new Gson().fromJson(s, c);
	}
	
	
 
	
	 
	public  List getDefaultList(Class c)
	{
		return new Gson().fromJson(getTable("default"), getListType(c));
	}
	
	@Override
	public <T> List<T> toList(Class<T> c) {
		// TODO Auto-generated method stub
		return getList("default", c);
		
	}
	 
	public  <T> List<T> getList(String key,Class<T> c)
	{
		  String s;
		try {
			if(!body.has(key))
				return new ArrayList<T>();
			s = body.get(key)+"";
			 return JsonTool.toList(s, c);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return new ArrayList<T>();
	}
 
	
	public static <T> List<T> jsonToListObject(String jsonStr,TypeToken<List<T>> typeToken){
		return new Gson().fromJson(jsonStr, typeToken.getType());
	}
	
	public static  <T> Type getListType(Class<T> c)
	{
		return  new TypeToken<List<T>>(){}.getType();
	}
	
	
	
	
	
	
	
	
	
	
	
}
