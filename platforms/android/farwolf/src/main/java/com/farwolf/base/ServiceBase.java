package com.farwolf.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class ServiceBase {

	@RootContext
	protected Context context;
	
	public void toast(String msg)
	{
		if(context!=null)
		Toast.makeText(context, msg+"", Toast.LENGTH_LONG).show();
	}
	
	
	public Activity getActivity()
	{
		return (Activity) context;
	}
	
	
	public Fragment getFragment()
	{
		OneFragmentActivity f= (OneFragmentActivity) getActivity();
		return f.getOrginFragment();
		
	}
	
	
	
}
