package com.farwolf.util;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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
	
	
}
