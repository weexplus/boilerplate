package com.farwolf.util;

import android.graphics.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtil {

	
	public static boolean isNullOrEmpty(String s)
	{
		if(s==null)
			return true;
		if("".equals(s))
			return true;
		return false;
		
	}

	public static int getColor(String color,float percent)
	{
		int c= Color.parseColor(color);
		float alph=percent*255;
		return Color.argb((int)alph,Color.red(c),Color.green(c),Color.blue(c));

	}

	public static String streamToString(InputStream is) {
        /*
          * To convert the InputStream to String we use the BufferedReader.readLine()
          * method. We iterate until the BufferedReader return null which means
          * there's no more data to read. Each line will appended to a StringBuilder
          * and returned as String.
          */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}



//	public static String getRealUrl(String baseurl,String target)
//	{
//		if(!baseurl.contains("/"))
//		{
//			if(target.startsWith("/"))
//			{
//				target=target.substring(1);
//			}
//          return target;
//		}
//		baseurl=baseurl.substring(0, baseurl.lastIndexOf("/"));
//		while(target.startsWith("../"))
//		{
//			target=target.substring(target.indexOf("/")+1,target.length());
//			if(baseurl.contains("/"))
//			baseurl=baseurl.substring(0, baseurl.lastIndexOf("/"));
//		}
//
//		return baseurl+"/"+target;
//	}

	public static String getRealUrl(String baseurl,String target)
	{
		if(!baseurl.contains("/"))
		{
			if(target.startsWith("/"))
			{
				target=target.substring(1);
			}
			return target;
		}
		if(baseurl.contains("/"))
			baseurl=baseurl.substring(0, baseurl.lastIndexOf("/"));
		String q[]=target.split("\\.\\.\\/");
		String x[]=baseurl.split("\\/");
		String p="";
		if(x.length>=q.length-1)
		{
			for(int i=0;i<x.length-q.length+1;i++)
			{
				p+=x[i]+"/";
			}
		}
		p+=q[q.length-1];
		return p;
//		System.out.println(p);
	}
}
