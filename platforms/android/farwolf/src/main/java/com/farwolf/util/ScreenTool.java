package com.farwolf.util;

import android.content.Context;
import android.view.WindowManager;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class ScreenTool {
	
	
	@RootContext
	Context context;
	
	public  int toDip( float dipValue)
	{ 
        final float scale = context.getResources().getDisplayMetrics().density; 
        return (int)(dipValue * scale + 0.5f); 
    } 

	public  int toPx(float pxValue)
	{ 
	        final float scale = context.getResources().getDisplayMetrics().density; 
	        return (int)(pxValue / scale + 0.5f); 
	}

	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result =  context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
	
	
	public int getScreenWidth()
	{
		WindowManager wm = getWindowManager();

			 return wm.getDefaultDisplay().getWidth();
		 
	}
	public int getScreenHeight()
	{
		WindowManager wm =getWindowManager();
		return  wm.getDefaultDisplay().getHeight();
	}
	public WindowManager getWindowManager()
	{
		return (WindowManager)context
				.getSystemService(Context.WINDOW_SERVICE);
		 
	}
	
	
	
}
