package com.farwolf.update.download.utils;
/*  * 文 件 名:  DataCleanManager.java  * 描    述:  主要功能有清除内/外缓存，清除数据库，清除sharedPreference，清除files和清除自定义目录  */

import android.content.Context;
import android.os.Environment;

import java.io.File;

/** * 本应用数据清除管理器 */
public class DataCleanManager {
    /** * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * @param context */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    /** * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * * @param context */
    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/databases"));
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) * * @param
     * context
     */
    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/shared_prefs"));
    }

    /** * 按名字清除本应用数据库 * * @param context * @param dbName */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /** * 清除/data/data/com.xxx.xxx/files下的内容 * * @param context */
    public static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
     * context
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            LogUtil.e("清除缓存的地址： " +context.getExternalCacheDir());
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /** * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * * @param filePath */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /** * 清除本应用所有的数据 * * @param context * @param filepath */
    public static void cleanApplicationData(Context context, String... filepath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        for (String filePath : filepath) {
            cleanCustomCache(filePath);
        }
    }

    /** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
    public  static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            int FileTotle = 0;
            for (File item : directory.listFiles()) {
                if(item.isDirectory()){
                    deleteFilesByDirectory(item);
                }else{
                    item.delete();
                }
                FileTotle++;
            }
            if(FileTotle == directory.listFiles().length){
                directory.delete();
            }
        }
    }
    /** * 删除方法  删除某个文件夹下的文件，及其文件 @param directory */
    public  static void deleteFilesByDirectory2(String finePath) {
        File directory = new File(finePath);
        if (directory != null && directory.exists() && directory.isDirectory()) {
            int FileTotle = 0;
            for (File item : directory.listFiles()) {
                if(item.isDirectory()){
                    deleteFilesByDirectory(item);
                }else{
                    item.delete();
                }
                FileTotle++;
            }
            if(FileTotle == directory.listFiles().length){
                directory.delete();
            }
        }else  if(directory != null && directory.exists() && directory.isFile()) {
            directory.delete();
        }
    }
    /**
     * 把long型转换为 kb M 或者g
     * @param size
     * @return
     */
    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }


    /**
     * 获取文件大小
     * @param file 文件
     * @return 长整形 大小
     */
    public static long getTotalSizeOfFilesInDir(final File file) {
        if (file.isFile())
            return file.length();
        final File[] children = file.listFiles();
        long total = 0;
        if (children != null)
            for (final File child : children)
                total += getTotalSizeOfFilesInDir(child);
        return total;
    }

    /**
     * 获取文件大小 字符串型
     * @param file
     * @return
     */
    public static String getFileSize(File file){
        long size = getTotalSizeOfFilesInDir(file);
        return convertFileSize(size);
    }
}
