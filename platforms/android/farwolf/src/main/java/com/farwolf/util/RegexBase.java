package com.farwolf.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexBase {


	public static List<String> regex(String s,String start,String end)
	{
		 return    regex(s,start,end,true);
	}
	public static List<String> regexNoReplace(String s,String start,String end)
	{
		 return    regex(s,start,end,false);
	}
	public static String regexNoReplaceOne(String s,String start,String end)
	{
		List<String> l=  regexNoReplace(s, start, end);
	  	   if(l.size()>0)
	  		   return l.get(0);
	  	   else
	  		   return null;
	}
	public static List<String> regex(String s,String start,String end,boolean ifreplace)
	{
		   List<String> l=new ArrayList<String>();
		   Pattern p=Pattern.compile(start+"(.+?)"+end,Pattern.DOTALL);			
		   Matcher m=p.matcher(s);		
		   while(m.find())
		   {
			   String sx=m.group();
			   if(ifreplace)
			   {
				   sx=sx.replace(start, "").replace(end, "");
			   }
			   l.add(sx);
		   }
		   return l;		   
	}
	public static String regexOne(String s,String start,String end)
	{
		List<String> l=  regex(s, start, end);
		  	   if(l.size()>0)
		  		   return l.get(0);
		  	   else
		  		   return null;
	}
	
}
