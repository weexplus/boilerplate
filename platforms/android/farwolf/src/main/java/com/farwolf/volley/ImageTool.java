package com.farwolf.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.farwolf.libary.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class ImageTool {

	@RootContext
	protected Context context;
	
	
	
	public static ImageTool build(Context c)
	{
		ImageTool tool=new ImageTool();
		tool.context=c;
		return tool;
	}


	public static Drawable imgaeToDrawable(Bitmap bmp)
	{
		Drawable drawable =new BitmapDrawable(bmp);
		return  drawable;
	}
	
	public void loadImage(ImageView img,String url,int defaultimgid,ImageLoader loader ){  
       
       
         
        ImageListener listener = ImageLoader.getImageListener(img, defaultimgid,R.drawable.ic_launcher);  
        loader.get(url, listener);  
    }  
}
