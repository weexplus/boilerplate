package com.farwolf.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public abstract class ItemView<T> extends LinearLayout{

	
	
	protected T t;
	
	
	public ItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}

	public ItemView(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}
	
 
	
	public  void init()
	{
		LayoutInflater.from(getContext()).inflate(getViewId(), this);
	}
	
	public abstract void update(T o);
	public  void update_(T o)
	{
		this.t=o;
		if(this.t==null)
			return;
		this.update(t);
	}
	
	public abstract int getViewId();
	
	
	
	public Activity getActivity()
	{
		 
		 return (Activity) getContext();
	}

	
	
	public Fragment getFragment()
	{
		OneFragmentActivity f= (OneFragmentActivity) getActivity();
		return f.getOrginFragment();
		
	}
}
