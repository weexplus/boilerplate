package com.farwolf.json;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class JsonReader {
	public JSONObject j;
	public String orgin;
	
	
	public  JsonReader(String s) {
		 
		load(s);
	}
	
	public void load(String s)
	{
		if(s==null)
			return;
		this.orgin = s;
		try {
			j=new JSONObject(s);
			 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	
	public  <T> T toBean(String key,Class<T>c)
	{
		try {
			return JsonTool.toBean(j.getJSONObject(key), c);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public <T> List<T> toList(String key,Class<T> c) 
	{
	   try {
		return 	JsonTool.toList(j.getJSONArray(key), c);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return new ArrayList<T>();
	}
	
	
	 
	
	public abstract <T> List<T> toList(Class<T> c); 
	public abstract String getErrMsg();
	public abstract String getErrCode();
	public abstract boolean isEnd();
 
	public boolean isSuccess()
	{
		return "0".equals(getErrCode());
	}
	
	
	
	
}
