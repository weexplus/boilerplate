package com.farwolf.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SDCard {

	
	public static String getBasePath(Context context)
	{
//		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
//        	return Environment.getExternalStorageDirectory().getPath();
//        else
        	return context.getCacheDir()+"";
	}

	public static String readStreamToString(InputStream inputStream) {
		BufferedReader bufferedReader = null;
		try {
			StringBuilder builder = new StringBuilder(inputStream.available() + 10);
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			char[] data = new char[4096];
			int len = -1;
			while ((len = bufferedReader.read(data)) > 0) {
				builder.append(data, 0, len);
			}

			return builder.toString();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {

			}
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {

			}
		}

		return "";
	}

	public static Bitmap getBitMap(Context context,String path)
	{
		FileInputStream fs=getFileStream(context,path);
		Bitmap bitmap = BitmapFactory.decodeStream(fs);
		return bitmap;
	}

	public static String getString(Context context,String path)
	{
		FileInputStream fs=getFileStream(context,path);
	    return readStreamToString(fs);

	}



	public static FileInputStream getFileStream(Context context,String path)
	{

		String p= context.getCacheDir()+"/"+path;

		try {
			  FileInputStream is = new FileInputStream(new File(p));
             return is;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;

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
