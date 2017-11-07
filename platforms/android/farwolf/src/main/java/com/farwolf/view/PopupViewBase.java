package com.farwolf.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import com.farwolf.util.ScreenTool;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

@EViewGroup
public  class PopupViewBase extends LinearLayout {

	public  boolean isPopOpen=false;
	protected   PopupWindow popupWindow;
	
	@ViewById
	protected   View cover;
	
	@Bean
	protected ScreenTool tool;
	
	protected   View anchor;
	
	boolean coverClickClose=true;

	PopupWindow.OnDismissListener onDismissListener;

	public void setAnchor(View anchor) {
		this.anchor = anchor;
	}

	public PopupViewBase(Context context, AttributeSet attrs) {
		super(context, attrs);
	
	
		// TODO Auto-generated constructor stub
	}

	public PopupViewBase(Context context) {
		super(context);
		
		// TODO Auto-generated constructor stub
	}

	public void setonDismissListener(PopupWindow.OnDismissListener l)
	{
		onDismissListener=l;
	}


	@AfterViews
	protected void init()
	{
		
//		WindowManager wm = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);
		int width = tool.getScreenWidth();
        int height =  tool.getScreenHeight();
//        int height =  DimenTool.getDisplayHeight(getContext());
//		LayoutInflater.from(getContext()).inflate(getViewId(), this);
//		this.setBackgroundColor(Color.parseColor("#bb000000"));
		popupWindow=new PopupWindow(this, width, height,true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			
			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub
				close();
				if(onDismissListener!=null)
					onDismissListener.onDismiss();
			}
		});
        this.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(coverClickClose)
				close();
			}
		});
		if(cover!=null)
		cover.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(coverClickClose)
				close();
			}
		});
		
	}
	
	
	
	
	public boolean isCoverClickClose() {
		return coverClickClose;
	}

	public void setCoverClickClose(boolean coverClickClose) {
		this.coverClickClose = coverClickClose;
	}

	public void open()
	{
//		popupWindow.showAsDropDown(anchor, 0, 0);
		isPopOpen=true;
		if(popupWindow!=null)
		{
			 
				popupWindow.showAtLocation(anchor, Gravity.FILL, 0, 0);
			 
		}
			
		if(cover!=null)
		cover.setVisibility(View.VISIBLE);
	}
	
	public void open(int xoff,int yoff)
	{
//		popupWindow.showAsDropDown(anchor, 0, 0);
		isPopOpen=true;
		if(popupWindow!=null)
			popupWindow.showAsDropDown(anchor, xoff, yoff);
//		popupWindow.showAtLocation(anchor, Gravity.FILL, x, y);
		if(cover!=null)
			cover.setVisibility(View.VISIBLE);
	}
	
	public void close()
	{
		if(cover!=null)
		cover.setVisibility(View.INVISIBLE);
		popupWindow.dismiss();
		isPopOpen=false;
		
	}
	

 
	
	

}
