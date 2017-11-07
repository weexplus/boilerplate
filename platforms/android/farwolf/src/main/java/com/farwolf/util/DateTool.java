package com.farwolf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTool {
	
	public static Date getDate(String time)  {
		try {
			SimpleDateFormat  df = new  SimpleDateFormat("yyyy-MM-dd");
			java.util.Date  cDate  =  df.parse(time);
			return cDate;
		}
		catch (Exception e)
		{
			return new Date();
		}

		
	}

	public static Date getDate(String time,String format)  {
		try {
			SimpleDateFormat  df = new  SimpleDateFormat(format);
			java.util.Date  cDate  =  df.parse(time);
			return cDate;
		}
		catch (Exception e)
		{
			return new Date();
		}


	}
	
	public static String ToString( Date  d,String pattern)
	{
		SimpleDateFormat  df = new  SimpleDateFormat(pattern);  
		return  df.format(d);
	}
	
	public static String Pattern(Date date,String pattern){
	
	    SimpleDateFormat  df = new  SimpleDateFormat(pattern);   
		return df.format(date);
	}
	public static String Pattern(String time,String orgin,String target){
		
		SimpleDateFormat  df = new  SimpleDateFormat(orgin);   
		SimpleDateFormat  df1 = new  SimpleDateFormat(target);   
		 Date d=null;
		try {
			d = df.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return df1.format(d);
	}
	public static String Pattern(String date,String pattern){
		 SimpleDateFormat  df = new  SimpleDateFormat(pattern);   
		Date d=null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		Calendar cal=Calendar.getInstance();		
		cal.setTime(d);	   
		return df.format(date);
		
	}
	public static String Now(){
		Calendar cal=Calendar.getInstance();
	    SimpleDateFormat  df = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		return df.format(cal.getTime());
	}
	public static int getDayofWeek(String s,String pattern){
		Calendar cal=Calendar.getInstance();
	    SimpleDateFormat  df = new  SimpleDateFormat(pattern);   
	    try {
			cal.setTime(df.parse(s));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return cal.get(Calendar.DAY_OF_WEEK);
	}
	public static int getDayofWeek(String s){
		 return getDayofWeek(s, "yyyy-MM-dd");
	}
	public static String Date(){
		Calendar cal=Calendar.getInstance();
	    SimpleDateFormat  df = new  SimpleDateFormat("yyyy-MM-dd");   
		return df.format(cal.getTime());
	}
	public static String Date(int k,int type){
		Calendar cal=Calendar.getInstance();
	    SimpleDateFormat  df = new  SimpleDateFormat("yyyy-MM-dd");   
	    cal.add(type, k);
		return df.format(cal.getTime());
	}
	 public static String TimeTampMili()
	  {
		   Calendar cal = Calendar.getInstance();
		    return cal.getTime().getTime()+"";
	  }
	 
	 
	 public static String TimestampToTime(long time,String format)
	 {
		 SimpleDateFormat sdf = new SimpleDateFormat(format);
		 String date = sdf.format(new Date(time*1000));
		 return date;
	 }
	 public static String TimestampToTime(long time)
	 {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String date = sdf.format(new Date(time));
		 return date;
	 }
	
	public static void main(String arg[])
	{
	}
	
}
