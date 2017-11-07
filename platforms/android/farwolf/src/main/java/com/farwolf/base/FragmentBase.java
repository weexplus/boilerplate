package com.farwolf.base;

import org.androidannotations.annotations.EFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Toast;

@EFragment
public abstract  class FragmentBase extends Fragment{

 

	protected boolean isIntialized=false;
 
    public boolean isIntialized() {
		return isIntialized;
	}
    
    
    public abstract int getViewId();

	protected View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
           
		
		if(view!=null)
		{
			if(isCache)
			{
				return view;
			}
		}
		else
		{
			view=inflater.inflate(getViewId()	, null);
			 
		}

		
		try {			 
			ViewTreeObserver vx=container.getViewTreeObserver();
			vx.addOnPreDrawListener(new OnPreDrawListener() {
				
				@Override
				public boolean onPreDraw() {
					// TODO Auto-generated method stub
					if(!isIntialized)
					{
						if(onLoadListener!=null)
							onLoadListener.onFragmentLoaded();
						isIntialized=true;
						onLoaded();
					}			
					return true;
				}
			});
//			if(onLoadListener!=null)
//				onLoadListener.onFragmentLoaded();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		super.onCreate(savedInstanceState);
		return view;
	}
	


	protected void onLoaded()
	{
		
	}
	

	
	 protected boolean isCache=false;
	
	

	
	public void setCache(boolean isCache) {
		this.isCache = isCache;
	}

	
	public View findViewById(int id)
	{
		return view.findViewById(id);
	}
	
	 
	
	public Intent getIntent()
	{
		if(getActivity()!=null)
		    return getActivity().getIntent();
		else {
			return null;
		}
	}
	
	
	public  Activity getBaseActivity()
	{
//		Fragment fx=  getParentFragment();
//		Fragment f=getParentFragment();
//		
//		if(fx==null)
//			return getActivity();
//		while(f!=null)
//		{
//			
//			f=getParentFragment();
//			if(f!=null)
//				fx=getParentFragment();
//		}
//		return fx.getActivity();
		
		
		return getActivity();
	}
	public void toast(String msg,int time)
	{
		
		 if(getBaseActivity()!=null)
		Toast.makeText(getBaseActivity(), msg+"", time).show();
	}
	
	public void replace(int id,Fragment fragment)
	{

//		getFragmentManager().beginTransaction().replace(id, fragment).commitAllowingStateLoss();
		getChildFragmentManager().beginTransaction().replace(id, fragment).commitAllowingStateLoss();
//		getFragmentManager().beginTransaction().replace(id, fragment).commit();
	}
	public void childReplace(int id,Fragment fragment)
	{
		
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(id, fragment).commitAllowingStateLoss();
	}
	
	public void hide()
	{
		 hide(this);
	}
	
	public void hide(Fragment fragment)
	{
		
		getFragmentManager().beginTransaction().hide(fragment).commitAllowingStateLoss();
	}
	
	public void show(Fragment fragment)
	{
		
		getFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
	}
	
	public void show()
	{
		show(this);
	}
	
	
	public Fragment getByTag(String tag)
	{
	   return	getFragmentManager().findFragmentByTag(tag);
	}
	
	public void toast(String msg)
	{
		this.toast(msg, 200);
	}
	
	public onLoadListener onLoadListener;
	public void setOnLoadListener(onLoadListener onLoadListener) {
		this.onLoadListener = onLoadListener;
	}
	public interface onLoadListener
	{
		void onFragmentLoaded();
	} 
	


	
	

	
	
	
	 

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		if(isCache)
		{
			if(view!=null)
			{
				if(view.getParent()!=null)
				((ViewGroup)view.getParent()).removeAllViews();
			}
		}
		super.onDestroyView();
	}
	
}
