package com.farwolf.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.lang.reflect.Method;

@EBean
public class KeyBoardTool {

	
	
	@RootContext
	Context context;
	
	public void dismiss(){


		 InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		 if(imm!=null&context!=null&&((Activity)context).getCurrentFocus()!=null)
		 {
			 imm.hideSoftInputFromWindow( ((Activity)context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);   
		 }
		
		 
//		 ((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow( ((Activity)context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);   
	}
	
	public void show(View v)
	{
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);
	}
	
	public boolean isOpen()
	{
		
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开
        return isOpen;
	}

	public static int getDpi(Context context) {
		int dpi = 0;
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		@SuppressWarnings("rawtypes")
		Class c;
		try {
			c = Class.forName("android.view.Display");
			@SuppressWarnings("unchecked")
			Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
			method.invoke(display, displayMetrics);
			dpi = displayMetrics.heightPixels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dpi;
	}

	/**
	 * 获取 虚拟按键的高度
	 *
	 * @param context
	 * @return
	 */
	public static int getKeyboardHeight(Context context) {
//		int totalHeight = getDpi(context);
////
////		int contentHeight = getScreenHeight(context);
////
////		return totalHeight - contentHeight;

		Rect r = new Rect();
		//获取当前界面可视部分
		((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
		//获取屏幕的高度
		int screenHeight =  ((Activity)context).getWindow().getDecorView().getRootView().getHeight();
		//此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
		int heightDifference = screenHeight - r.bottom;
		Log.d("Keyboard Size", "Size: " + heightDifference);
		return heightDifference;
	}

	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}
	
	
}
