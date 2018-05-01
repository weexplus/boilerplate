package com.farwolf.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.provider.MediaStore.Images.ImageColumns;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Picture {
	
	/**
	 * 根据url获取图片名称
	 * @param url
	 * @param type jpg,png.....
	 * @return
	 */
	  public   String getPicNameFromUrl(String url,String type)
	    {
//		  Log.i("url", url);
	    	Pattern p=Pattern.compile("[^\\/]*"+type);
			Matcher m = p.matcher(url);
			if(m.find())				
		   return m.group();
			else
				return null;
	    }
	    
	  /**
	   * 获取jpg或者jpeg类型的图片名称
	   * @param url
	   * @return
	   */
	  public   String getPicName(String url)
	    {
	    			
		  String s= getPicNameFromUrl(url,"jpg");
		  if(s!=null)
			  return s;
		  s= getPicNameFromUrl(url,"jpeg");
		  if(s!=null)
			  return s;
		  s= getPicNameFromUrl(url,"png");		 
			  return s;
	    }
	    
	  /**
	   * 根据名称获取图片类型
	   * @param picname
	   * @return
	   */
	   public String getPicType(String picname)
	     {
	    	 String p[]= picname.split(".");
	    	 if(p.length>1)
	    	 {
	    		 return p[1];
	    	 }
	    	 else
	    	 {
	    		 
	    		 Log.i("Picture.getPicType", picname+"锟斤拷锟斤拷锟斤拷效锟斤拷图片锟斤拷锟�");
	    		 return null;
	    	 }
	    	 
	     }
	   
	   
	   

	   /**
	    * 将图片存到sd上
	    * @param path
	    * @param b
	    * @return
	    */
       public static boolean saveImageToSDCard(String path,Bitmap b)
       {
    	   
    	   
    	   
//    	   if(!SDCard.IsSDCardExist())
//    	   {
//    		   return false;
//    	   }
    	   File f = new File(path) ;
            try {
            	if(!f.exists())
            	{
            		if(!f.createNewFile())
    				{
    					return false;
    				}
            	}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  try {
			    FileOutputStream out = new FileOutputStream(f);
			    if(b!=null)
	           	b.compress(Bitmap.CompressFormat.PNG, 100, out);          
				out.flush();
				out.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		  finally
		  {
			  
		  }
            

       }
       
       /**
        * 从sd上获取图片
        * @param path
        * @return
        */
	    public  static Bitmap getBitmap(String path)
	    {
	    	if(!SDCard.IsFileExist(path))
	    	{
	    		return null;
	    	}
	    	Options mOptions = new BitmapFactory.Options();
	    	mOptions.inSampleSize= mOptions.outWidth / 200;
	    	return	BitmapFactory.decodeFile(path,mOptions);
	    	
	    }
	    
	    
	    public  static String getSysPath(final Context context, final Uri uri)
	    {
	    	if ( null == uri ) return null;
	        final String scheme = uri.getScheme();
	        String data = null;
	        if ( scheme == null )
	            data = uri.getPath();
	        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
	            data = uri.getPath();
	        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
	            Cursor cursor = context.getContentResolver().query( uri, new String[] { ImageColumns.DATA }, null, null, null );
	            if ( null != cursor ) {
	                if ( cursor.moveToFirst() ) {
	                    int index = cursor.getColumnIndex( ImageColumns.DATA );
	                    if ( index > -1 ) {
	                        data = cursor.getString( index );
	                    }
	                }
	                cursor.close();
	            }
	        }
	        return data;
	    }

	    
	    public static InputStream Bitmap2InputStream(Bitmap bm) {  
	    	if(bm==null)
	    		return null;
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);  
	        InputStream is = new ByteArrayInputStream(baos.toByteArray());  
	        return is;  
	    }


	   public static byte[] bitmapToByte(Bitmap bitmap)
	   {
		   ByteArrayOutputStream baos = new ByteArrayOutputStream();
		   bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		   byte[] datas = baos.toByteArray();
		   return datas;
	   }
	    
	    
	    
	    public  Bitmap compressImageBySize(Bitmap image,long size) {  
	    	  
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中  
	        int options = 100;  
	        while ( baos.toByteArray().length / 1024>size) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩         
	            baos.reset();//重置baos即清空baos  
	            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中  
	            options -= 10;//每次都减少10  
	        }  
	        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中  
	        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片  
	        return bitmap;  
	    } 
	    
	    
		public Bitmap compressImage(Bitmap image,float maxw,float maxheight,long size) {
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();		
			image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出	
				baos.reset();//重置baos即清空baos
				image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
			}
			ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
			BitmapFactory.Options newOpts = new BitmapFactory.Options();
			//开始读入图片，此时把options.inJustDecodeBounds 设回true了
			newOpts.inJustDecodeBounds = true;
			Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
			newOpts.inJustDecodeBounds = false;
			int w = newOpts.outWidth;
			int h = newOpts.outHeight;
			//现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
			float hh = maxw;//这里设置高度为800f
			float ww = maxheight;//这里设置宽度为480f
			//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
			int be = 1;//be=1表示不缩放
			if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
				be = (int) (newOpts.outWidth / ww);
			} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
				be = (int) (newOpts.outHeight / hh);
			}
			if (be <= 0)
				be = 1;
			newOpts.inSampleSize = be;//设置缩放比例
			//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
			isBm = new ByteArrayInputStream(baos.toByteArray());
			bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
			return compressImageBySize(bitmap,size);//压缩好比例大小后再进行质量压缩
		}
		
		
		
		public static int getImageHeight(int acw,int ach,int w)
		{
			 if(w==0)
				 return 0;
			   ach*=(float)acw/(float)w;
			  return ach;
			 
		}
}
