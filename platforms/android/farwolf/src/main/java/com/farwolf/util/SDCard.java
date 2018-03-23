package com.farwolf.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

public class SDCard {

	
	public static String getBasePath(Context context)
	{
//		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
//        	return Environment.getExternalStorageDirectory().getPath();
//        else
        	return context.getCacheDir()+"";
	}
	
	
	   public static  boolean IsSDCardExist()
       {
    	     	   
    	   String en =Environment.getExternalStorageState();
    	   String s=Environment.MEDIA_MOUNTED;
           return en.equals(s);


       }
	   
	   
	   
       
	   public  static boolean IsFileExist(String path)
	   {
		   File f_new = new File(path);
		   return f_new.exists();
	   }
	   public  static boolean delete(String path)
	   {
		   if(!IsFileExist(path))
			   return true;
		   File f_new = new File(path);
		   return f_new.delete();
	   }
	  
     
       public static float getFreeMemorySize()
       {
    	 
	       File path = Environment.getDataDirectory();
	       StatFs stat = new StatFs(path.getPath());
	       long blockSize = stat.getBlockSize();
	       long availableBlocks = stat.getAvailableBlocks();
	       return (availableBlocks * blockSize)*1.0f/(1024);
       }
}
