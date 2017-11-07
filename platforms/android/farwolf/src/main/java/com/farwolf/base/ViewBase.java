package com.farwolf.base;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

@EViewGroup
public abstract class ViewBase extends LinearLayout{

	public ViewBase(Context context, AttributeSet attrs) {
		super(context, attrs);
		init_();
		// TODO Auto-generated constructor stub
	}

	public ViewBase(Context context) {
		super(context);
		init_();
		// TODO Auto-generated constructor stub
	}
	
	void init_()
	{
		LayoutInflater.from(getContext()).inflate(getViewId(), this);
		 
	}
	
	public abstract int getViewId();
	
	@AfterViews
	public abstract void init();
	 
	public Activity getActivity()
	{
		return (Activity)getContext();
	}
	

}
