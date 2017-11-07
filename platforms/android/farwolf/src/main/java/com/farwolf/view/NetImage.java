package com.farwolf.view;

import java.io.File;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.farwolf.net.HttpTool;
import com.farwolf.util.FileTool;
import com.farwolf.util.Md5;
import com.farwolf.util.Picture;
import com.farwolf.util.SDCard;

public class NetImage  extends ImageView{

    public NetImage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public  String picname;
    public static  String basedir;
   
    String url;
    String dir;
	private Picture p=new Picture();
    private  Context context;
    private Handler h=new Handler();
    private boolean saveImage=false;
	public boolean isSaveImage() {
		return saveImage;
	}

	public void setSaveImage(boolean saveImage) {
		setSaveImage(saveImage,"system");
	}
	
	public void setSaveImage(boolean saveImage,String dir) {
		this.saveImage = saveImage;		
	    Activity a=	(Activity) getContext();
	    
		if(saveImage)
		{
			if(dir==null)
				return;
		     if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		    	 basedir=Environment.getExternalStorageDirectory().getPath()+"/"+a.getApplication().getApplicationInfo().name+"/";
	        else
	        	basedir=context.getCacheDir()+"/farwolf/";
		     
		     
	        if(!SDCard.IsFileExist(basedir+dir))
			{
				File f=new File(basedir+dir);
				f.mkdirs();
			}
		}
	}

	public NetImage(Context context) {
		super(context);
		Intial(context);
	}
	
	public NetImage(Context context, AttributeSet attrs) {
		super(context,attrs);
		Intial(context);
	}
	
	private void Intial(Context context)
	{
		this.context=context;
//	     setScaleType(ScaleType.FIT_XY);

	   
	}
	
	
	public void clearCache()
	{
		Md5 md5=new Md5();
		String savePath=basedir+ dir+"/"+md5.getMD5(NetImage.this.url);
		SDCard.delete(savePath);
	}
	public void saveImage(Bitmap m)
	{
		Md5 md5=new Md5();
		String savePath=basedir+ dir+"/"+md5.getMD5(NetImage.this.url);
		 p.saveImageToSDCard(savePath, m);
	}
	
	
	public String getSavePath()
	{
		Md5 md5=new Md5();
		String savePath=basedir+ dir+"/"+md5.getMD5(NetImage.this.url);
		return savePath;
	}
	 

 
	public class Task extends  AsyncTask<HashMap,Integer,Bitmap>
	{

		@Override
		protected Bitmap doInBackground(HashMap... arg0) {
			// TODO Auto-generated method stub
          Bitmap m=null;
       
			Md5 md5=new Md5();
			String savePath=basedir+ dir+"/"+md5.getMD5(NetImage.this.url);
			
			if(SDCard.IsFileExist(savePath))
			{
				FileTool f=new FileTool(context);
				if(f.getSize(savePath)==0)
				{
					f.delete(savePath);
					m=HttpTool.getBitMap(url);
					if(isSaveImage())
					{
						 p.saveImageToSDCard(savePath, m);
					}
				}
				else
				{
					m=p.getBitmap(savePath);
				}
					
				
			}
			else
			{
//				m=HttpTool.getHttpBitmap(url);
				m=HttpTool.getBitMap(url);
				if(isSaveImage())
				{
					 p.saveImageToSDCard(savePath, m);
				}
			}
			if(arg0.length>0)
			{
				   HashMap cache=arg0[0];
				   if(cache!=null)
				   cache.put(NetImage.this.url, m);
			}
			return m;
		}
		
		
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			setImageBitmap(result); 
		}
		
	}
	
	public void cancle()
	{
		 Task t=(Task) this.getTag();
		 if(t==null)
			 return;
		 t.cancel(true);
	}
	 
	
	public  void setImageSource( final String dir,final String url,HashMap cache,boolean loadimediatly)
	{
//		Log.i("basedir", basedir);
		 
		if(this.url!=null&&!this.url.equals(url))
		{
			cancle();
		}
		
		 this.url=url;
		 this.dir=dir;
		 
		 if(loadimediatly)
		 {
			 if(SDCard.IsFileExist(getSavePath()))
				{
					setImageBitmap(p.getBitmap(getSavePath()));
					return;
				}
		 }
	    
		 Task t= new Task();
		 
		 this.setTag(t);
		 t.execute(cache);
	}
	
	public  void setImageSource( final String url)
	{
	      if(url==null||"".equals(url))
		  {
		    setImageBitmap(null);
		    return;
		  }
	      setImageSource("system",url,null,false);

	}
	public  void setImageSource( final String url,HashMap m,boolean loadimdiatly)
	{
		if(url==null)
			return;
		setImageSource("system",url,m,loadimdiatly);
	
	}
	public  void setImageSourceLoadImediatly( final String url,HashMap m)
	{
		if(url==null)
			return;
		setImageSource("system",url,m,true);
		
	}

	
	
	 

}
