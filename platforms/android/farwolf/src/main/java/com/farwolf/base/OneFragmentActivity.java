package com.farwolf.base;

import android.support.v4.app.Fragment;

import com.farwolf.libary.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity 
public abstract  class OneFragmentActivity extends TitleActivityBase{

    protected FragmentBase fragment;
 
	
	@AfterViews
	public void initFragment() {
		// TODO Auto-generated method stub
			if(fragment==null)
			{
				
				fragment= getFragment();
			}
			if(fragment!=null)
			replace(R.id.fragment, fragment);
			init();
	}
	
	@Override
	public int getViewId() {
		// TODO Auto-generated method stub
		return R.layout.api_onefragment_activity;
	}




	public Fragment getOrginFragment()
	{
		return fragment;
	}
	 
	
	
	public abstract  FragmentBase getFragment();
	 

}
