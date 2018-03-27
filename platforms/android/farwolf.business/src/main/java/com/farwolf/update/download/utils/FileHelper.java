package com.farwolf.update.download.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileHelper {
	/**
	 * 判断文件(文件夹，文件，比如app)是否存在
	 *
	 * @param pathName
	 * @param pathName
	 * @return
	 */
	public static boolean checkFileIsExists(String pathName) {
		boolean status = false;
		if(!TextUtils.isEmpty(pathName)){
			File fileApk = new File(pathName);
			status = fileApk.exists();
		}
		return status;
	}

	/**
	 * Android判断某个apk是否存在：
	 * packageName：apk的 包名
	 */
	public  boolean isPkgInstalled(Context context, String packageName) {

		if (packageName == null || "".equals(packageName))
			return false;
		android.content.pm.ApplicationInfo info = null;
		try {
			info = context.getPackageManager().getApplicationInfo(packageName, 0);
			return info != null;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}
		/**
         * 判断path路径下是否存在文件
         *
         * @param path
         * @param name
         * @return
         */
	public static boolean checkFileExists(String path, String name) {
		boolean status;
		if (!name.equals("")) {
			File newPath = new File(path + name);
			status = newPath.exists();
		} else {
			status = false;
		}
		return status;
	}

	/**
	 * 判断path路径下是否存在文件
	 * @param filepath
	 * @return
	 */
	public static boolean checkFileExists(String filepath) {
		boolean status;
		if (!TextUtils.isEmpty(filepath)) {
			File newPath = new File(filepath);
			status = newPath.exists();
		} else {
			status = false;
		}
		return status;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param dirName
	 */
	public static void creatDir(String dirName) {
		File file = new File(dirName);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 获取文件返回的流
	 * 
	 * @param filepath
	 * @param fileName
	 * @return
	 */
	public static InputStream getfileInputStream(String filepath, String fileName) {
		File file = new File(filepath, fileName);
		if (file.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				return fis;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 将字符串写入文件当中
	 * 
	 * @param path
	 * @param FileName
	 * @param string
	 */
	public static void saveStringToFile(String path, String FileName, String string) {
		File file = new File(path, FileName);
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				fileOutputStream.write(string.getBytes());
				fileOutputStream.flush();
				fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

	/**
	 * 读取文件中的字符串
	 * 
	 * @param path
	 * @param FileName
	 * @return
	 */
	public static String getStringFromFile(String path, String FileName) {
		File file = new File(path, FileName);
		if (checkFileExists(path, FileName)) {
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[512];
				int length = -1;
				while ((length = fileInputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, length);
				}
				outStream.close();
				fileInputStream.close();
				return outStream.toString();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}

	}

	/**
	 * 保存bitmap
	 * 
	 * @param path
	 * @param Filename
	 * @param bitmap
	 */
	public static void saveBitmap(String path, String Filename, Bitmap bitmap) {
		File file = new File(path, Filename);
		if (!checkFileExists(path, Filename)) {
			try {
				FileOutputStream out = new FileOutputStream(file);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void saveInputStreamToFile(InputStream is, String filepath, String fileName) {
		File file = new File(filepath, fileName);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = is.read(data)) != -1) {
				fos.write(data, 0, len);
			}
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String readInStream(InputStream inStream) {
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}

			outStream.close();
			inStream.close();
			return outStream.toString();
		} catch (IOException e) {
			Log.i("FileTest", e.getMessage());
		}
		return null;
	}

	public static byte[] toByteArray(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte[] imgdata = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}

	public static void copyFile(String sourePath, String derPath) throws FileNotFoundException {
		try {
			File oldfile = new File(sourePath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(sourePath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(derPath);
				byte[] buffer = new byte[1024];
				int byteread = 0;
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 根据传过来url创建文件
	 * 
	 */
	public static File getFile(String url) {
		File files = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), getFilePath(url));
		return files;
	}

	/**
	 * 截取出url后面的apk的文件名
	 * 
	 * @param url
	 * @return
	 */
	public static String getFilePath(String url) {
		return url.substring(url.lastIndexOf("/"));
	}
}