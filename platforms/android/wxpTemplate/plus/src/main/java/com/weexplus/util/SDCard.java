package com.weexplus.util;

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


	public static String getBasePath(Context context) {
		String path = context.getCacheDir() + "";
		return path;
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

	public static Bitmap getBitMap(Context context, String path) {
		FileInputStream fs = getFileStream(context, path);
		Bitmap bitmap = BitmapFactory.decodeStream(fs);
		return bitmap;
	}

	public static String getString(Context context, String path) {
		FileInputStream fs = getFileStream(context, path);
		if (fs == null)
			return "";
		return readStreamToString(fs);

	}


	public static FileInputStream getFileStream(Context context, String path) {

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


	public static boolean IsSDCardExist() {

		String en = Environment.getExternalStorageState();
		String s = Environment.MEDIA_MOUNTED;
		return en.equals(s);


	}


	public static boolean IsFileExist(String path) {
		File f_new = new File(path);
		return f_new.exists();
	}


	public static float getFreeMemorySize() {

		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return (availableBlocks * blockSize) * 1.0f / (1024);
	}


	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败:" + fileName + "不存在！");
			return false;
		} else {
			if (file.isFile())
				return deleteFile(fileName);
			else
				return deleteDirectory(fileName);
		}
	}

	/**
	 * 删除单个文件
	 *
	 * @param fileName 要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 *
	 * @param dir 要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			System.out.println("删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = deleteDirectory(files[i]
						.getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			System.out.println("删除目录失败！");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			return false;
		}

	}
}
