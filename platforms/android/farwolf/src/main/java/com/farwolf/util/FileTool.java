package com.farwolf.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.androidannotations.annotations.RootContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@EBean(scope=Scope.Singleton)
public class FileTool {

	@RootContext
	Context context;


	public static String loadAsset(String path, Context context) throws  IOException {
		if (context == null || TextUtils.isEmpty(path)) {
			return null;
		}
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;

			inputStream = context.getAssets().open(path);
			StringBuilder builder = new StringBuilder(inputStream.available() + 10);
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			char[] data = new char[4096];
			int len = -1;
			while ((len = bufferedReader.read(data)) > 0) {
				builder.append(data, 0, len);
			}

			return builder.toString();


	}


	public static Bitmap loadAssetImage(String src,Context context)
	{

		try {
			InputStream is = context.getAssets().open(src);
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			return bitmap;
		}
		catch (IOException io)
		{
			return null;
		}

	}
	
	private final String[][] MIME_MapTable={  
            //{后缀名， MIME类型}  
            {".3gp",    "video/3gpp"},  
            {".apk",    "application/vnd.android.package-archive"},  
            {".asf",    "video/x-ms-asf"},  
            {".avi",    "video/x-msvideo"},  
            {".bin",    "application/octet-stream"},  
            {".bmp",    "image/bmp"},  
            {".c",  "text/plain"},  
            {".class",  "application/octet-stream"},  
            {".conf",   "text/plain"},  
            {".cpp",    "text/plain"},  
            {".doc",    "application/msword"},  
            {".docx",   "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},  
            {".xls",    "application/vnd.ms-excel"},   
            {".xlsx",   "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},  
            {".exe",    "application/octet-stream"},  
            {".gif",    "image/gif"},  
            {".gtar",   "application/x-gtar"},  
            {".gz", "application/x-gzip"},  
            {".h",  "text/plain"},  
            {".htm",    "text/html"},  
            {".html",   "text/html"},  
            {".jar",    "application/java-archive"},  
            {".java",   "text/plain"},  
            {".jpeg",   "image/jpeg"},  
            {".jpg",    "image/jpeg"},  
            {".js", "application/x-javascript"},  
            {".log",    "text/plain"},  
            {".m3u",    "audio/x-mpegurl"},  
            {".m4a",    "audio/mp4a-latm"},  
            {".m4b",    "audio/mp4a-latm"},  
            {".m4p",    "audio/mp4a-latm"},  
            {".m4u",    "video/vnd.mpegurl"},  
            {".m4v",    "video/x-m4v"},   
            {".mov",    "video/quicktime"},  
            {".mp2",    "audio/x-mpeg"},  
            {".mp3",    "audio/x-mpeg"},  
            {".mp4",    "video/mp4"},  
            {".mpc",    "application/vnd.mpohun.certificate"},        
            {".mpe",    "video/mpeg"},    
            {".mpeg",   "video/mpeg"},    
            {".mpg",    "video/mpeg"},    
            {".mpg4",   "video/mp4"},     
            {".mpga",   "audio/mpeg"},  
            {".msg",    "application/vnd.ms-outlook"},  
            {".ogg",    "audio/ogg"},  
            {".pdf",    "application/pdf"},  
            {".png",    "image/png"},  
            {".pps",    "application/vnd.ms-powerpoint"},  
            {".ppt",    "application/vnd.ms-powerpoint"},  
            {".pptx",   "application/vnd.openxmlformats-officedocument.presentationml.presentation"},  
            {".prop",   "text/plain"},  
            {".rc", "text/plain"},  
            {".rmvb",   "audio/x-pn-realaudio"},  
            {".rtf",    "application/rtf"},  
            {".sh", "text/plain"},  
            {".tar",    "application/x-tar"},     
            {".tgz",    "application/x-compressed"},   
            {".txt",    "text/plain"},  
            {".wav",    "audio/x-wav"},  
            {".wma",    "audio/x-ms-wma"},  
            {".wmv",    "audio/x-ms-wmv"},  
            {".wps",    "application/vnd.ms-works"},  
            {".xml",    "text/plain"},  
            {".z",  "application/x-compress"},  
            {".zip",    "application/x-zip-compressed"},  
            {"",        "*/*"}    
        };  

	public FileTool(Context context) {
		super();
		this.context = context;
	}
	
	
	public  void openFile(String path){  
	      
	    Intent intent = new Intent("android.intent.action.VIEW");  
	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	    //设置intent的Action属性  
	    intent.setAction(Intent.ACTION_VIEW);  
	    //获取文件file的MIME类型  
	    String type = getMIMEType(path);  
	    //设置intent的data和Type属性。  
	    intent.setDataAndType(/*uri*/Uri.fromFile(new File(path)), type);  
	    //跳转  
	    if(isIntentAvailable(context,intent))
	    {
	    	Toast.makeText(context, "未安装对应的软件", 1).show();
	    	return;
	    }
	    context.startActivity(intent);    
	      
	}  
	
	
	private boolean isIntentAvailable(Context context, Intent intent) { 
        final PackageManager packageManager = context.getPackageManager(); 
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, 
                PackageManager.GET_ACTIVITIES); 
        return list.size() > 0; 
    }
	  
	/** 
	 * 根据文件后缀名获得对应的MIME类型。 
	 * @param file 
	 */  
	public String getMIMEType(String path) {  
	      
	    String type="*/*";  
	    String fName = path;  
	    //获取后缀名前的分隔符"."在fName中的位置。  
	    int dotIndex = fName.lastIndexOf(".");  
	    if(dotIndex < 0){  
	        return type;  
	    }  
	    /* 获取文件的后缀名 */  
	    String end=fName.substring(dotIndex,fName.length()).toLowerCase();  
	    if(end=="")return type;  
	    //在MIME和文件类型的匹配表中找到对应的MIME类型。  
	    for(int i=0;i<MIME_MapTable.length;i++){ //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？  
	        if(end.equals(MIME_MapTable[i][0]))  
	            type = MIME_MapTable[i][1];  
	    }         
	    return type;  
	}  
	
	public String getBaseDir()
	{
		  AppMainfest a=new AppMainfest(context);
		  String basedir="";
		  if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
//		    	 basedir=Environment.getExternalStorageDirectory().getPath()+"/"+"ca"+"/";
		  basedir=Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+a.getPakageName()+"/";
	        else
	        	basedir=context.getCacheDir()+"";
		  return basedir;
//		  return context.getCacheDir()+"";
	}
	
	public String getDir(String dir)
	{
		return getBaseDir()+dir+"/";
	}
	public static boolean makeDir(String dir)
	{
		 
		 File f=new File(dir);
		 
			 if(!f.exists())
			 {
				return f.mkdirs(); 
			 }
			 return true;
		
	}
	
	public static long getSize(String path)
	{
		File f=new File(path);
		if(!f.isFile())
		{
			return -1;
		}
		return f.getTotalSpace();
		
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


	public static void copyAssets(Context context, String oldPath, String newPath) {
		try {
			String fileNames[] = context.getAssets().list(oldPath);// 获取assets目录下的所有文件及目录名
			if (fileNames.length > 0) {// 如果是目录
				File file = new File(newPath);
				file.mkdirs();// 如果文件夹不存在，则递归
				for (String fileName : fileNames) {
					copyAssets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
				}
			} else {// 如果是文件
				InputStream is = context.getAssets().open(oldPath);
				FileOutputStream fos = new FileOutputStream(new File(newPath));
				byte[] buffer = new byte[1024];
				int byteCount = 0;
				while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
					// buffer字节
					fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
				}
				fos.flush();// 刷新缓冲区
				is.close();
				fos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	  
	
	
	
	
	
}
