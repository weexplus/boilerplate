package com.farwolf.json;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonTool {
	
	
	public static <T> T toBean(JSONObject j,Class<T>c)
	{
		return  toBean(j+"", c);
	}
	public static <T> T toBean(String s,Class<T>c)
	{
		return new Gson().fromJson(s, c);
	}
	public static <T> ArrayList<T> toList(JSONArray ja,Class<T> c)
	{
		ArrayList<T> l=new ArrayList<T>();
		if(ja==null)
		{
			return l;
		}
		 for(int i=0;i<ja.length();i++)
	     {
	     
			try {
				 
				l.add((T) toBean(ja.getString(i)+"",c));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
	     }
		 return l;
	}
	public static <T> List<T> toList(String s,Class<T> c)  
	{
		List<T> l=new ArrayList<T>();
		JSONArray ja=null;
		try {
			ja = new JSONArray(s);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(ja==null)
		{
			return l;
		}
	      
	    return toList(ja, c);
	}
	
}
