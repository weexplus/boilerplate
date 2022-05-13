package com.weexplus.util;

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


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.List;


public class FileTool {


	private static final String MEM_INFO_PATH = "/proc/meminfo";

	public static final String MEMTOTAL = "MemTotal";
	public static final String MEMFREE = "MemFree";


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
	 * @param
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
		return SDCard.getBasePath(context);
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

	public   long getTotalMemory() {
		return getMemInfoIype(context, MEMTOTAL);
	}

	public   long getMemoryFree() {
		return getMemInfoIype(context, MEMFREE);
	}


	public static long getMemInfoIype(Context context, String type) {
		try {
			FileReader fileReader = new FileReader(MEM_INFO_PATH);
			BufferedReader bufferedReader = new BufferedReader(fileReader, 4 * 1024);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				if (str.contains(type)) {
					break;
				}
			}
			bufferedReader.close();
            /* \\s表示   空格,回车,换行等空白符,
            +号表示一个或多个的意思     */
			String[] array = str.split("\\s+");
			// 获得系统总内存，单位是KB，乘以1024转换为Byte

			long length = Long.valueOf(array[1]) * 1024;
			return length;
//			return android.text.format.Formatter.formatFileSize(context, length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
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


	public static void deleteFile(File file) {
		if (file.isFile()) {
			deleteFileSafely(file);
			return;
		}
		if (file.isDirectory()) {
			File[] childFiles = file.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				deleteFileSafely(file);
				return;
			}
			for (int i = 0; i < childFiles.length; i++) {
				deleteFile(childFiles[i]);
			}
			deleteFileSafely(file);
		}
	}


	/**
	 * 安全删除文件.
	 * @param file
	 * @return
	 */
	public static boolean deleteFileSafely(File file) {
		if (file != null) {
			String tmpPath = file.getParent() + File.separator + System.currentTimeMillis();
			File tmp = new File(tmpPath);
			file.renameTo(tmp);
			return tmp.delete();
		}
		return false;
	}



	public static void writeFileToFile(byte []content, String filePath, String fileName) {
		//生成文件夹之后，再生成文件，不然会出错
		makeFile(filePath, fileName);
		String mFilePath = filePath + fileName;
		// 每次写入时，都换行写
		try {
			File file = new File(mFilePath);
			if (!file.exists()) {

				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			RandomAccessFile mRandomAccessFile = new RandomAccessFile(file, "rwd");
			mRandomAccessFile.seek(file.length());
			mRandomAccessFile.write(content);
			mRandomAccessFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//生成文件
	public static File makeFile(String filePath, String fileName) {
		File file = null;
		makeDirectory(filePath);
		try {
			file = new File(filePath + fileName);
			if(file.exists()){
				file.delete();
			}
			file.createNewFile();
		} catch (IOException e) {
			 e.printStackTrace();
		}
		return file;
	}
	//生成文件夹
	public static void makeDirectory(String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




}
