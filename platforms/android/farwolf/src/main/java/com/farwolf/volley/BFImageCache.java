package com.farwolf.volley;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.android.volley.toolbox.ImageLoader;
import com.farwolf.util.Md5;
 
public class  BFImageCache implements ImageLoader.ImageCache {
    /** 单例 */
    private static BFImageCache cache;
    /** 内存缓存 */
    private HashMap<String, Bitmap> memory;
    /** 缓存目录 */
    private String cacheDir;
    
    /** 获取单例 */
    public static BFImageCache getInstance() {
        if (null == cache) {
            cache = new BFImageCache();
        }
        return cache;
      
    }
    /** 初始化方法，Application的onCreate中调用 */
    public void initilize(Context context) {
        memory = new HashMap<String, Bitmap>();
//        cacheDir = context.getCacheDir().toString()+File.separator;
        
        
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
        		||!Environment.isExternalStorageRemovable()){
        	          cacheDir=context.getExternalCacheDir().getPath();
		}else{
			cacheDir=context.getCacheDir().getPath();
		}
        cacheDir = cacheDir+File.separator;
    }
    
    
    public  void delete(String url)
    {
    	 String key =Md5.toMd5(url);
    	  File file = new File(cacheDir + key);
    	  if (file.exists()) 
    	  {
    		  file.delete();
    	  }
    }
    
    
    @Override
    public Bitmap getBitmap(String url) {
        // 获取图片
        try {
            String key =Md5.toMd5(url);
            if (memory!=null&&memory.containsKey(key)) {
                return memory.get(key);
            } else {
                File file = new File(cacheDir + key);
                if (file.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.toString());
                    return bitmap;
                }
            }
        } catch (Exception e) {
//            Log.d("halfman", e.getMessage());
        }
        return null;
    }
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        // 尺寸超过10时，清理缓存并放入内存
        if (memory.size() >=0) {
            Iterator<String> it = memory.keySet().iterator();
            while (it.hasNext()) {
                try {
                    String key = it.next();
                    Bitmap temp = memory.get(key);
                    File file = new File(cacheDir+key);
                    FileOutputStream os;
                    os = new FileOutputStream(file, false);
                    temp.compress(CompressFormat.PNG, 100, os);
                    os.flush();
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            memory.clear();
        }
        // 放入图片到内存
        memory.put(Md5.toMd5(url), bitmap);
    }
}
