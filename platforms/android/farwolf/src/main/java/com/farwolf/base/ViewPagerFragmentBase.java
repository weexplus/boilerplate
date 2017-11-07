package com.farwolf.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.farwolf.libary.R;
import com.farwolf.view.viewpager.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

 
 

@EFragment
public abstract class ViewPagerFragmentBase extends FragmentBase {


	@ViewById
	public ViewPager viewpager;


	public List<Fragment> l=new ArrayList<Fragment>();
	
	
	
	
	@AfterViews
	protected void init_ViewPagerFragmentBase()
	{
		 this.viewpager.setIndicatorItemViewId(getIndicatorViewId());	
		 init();			 
		 this.viewpager.select(0);
		 viewpager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
				
				@Override
				public int getCount() {
					// TODO Auto-generated method stub
					return l.size();
				}
				
				@Override
				public Fragment getItem(int i) {
					// TODO Auto-generated method stub
					 
					return l.get(i);
				}
			});
		
	}
	
	
	public abstract void init();
	
	
	public void add(String name,Fragment f)
	{
		 this.viewpager.addHead(name);
		 l.add(f);
		 
	}
	
	  
	
	
	public int getIndicatorViewId()
	{
		return R.layout.api_indicator_itemview;
	}
	
	@Override
	public int getViewId() {
		// TODO Auto-generated method stub
		return R.layout.api_viewpager_fragment;
	}

}
