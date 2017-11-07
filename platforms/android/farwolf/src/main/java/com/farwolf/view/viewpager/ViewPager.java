package com.farwolf.view.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.farwolf.libary.R;
import com.farwolf.view.viewpager.Indicator.OnItemClick;

public class ViewPager extends LinearLayout{

	android.support.v4.view.ViewPager viewpager;



	public Indicator indicator;
	
	public ViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		 init();
		// TODO Auto-generated constructor stub
	}

	public ViewPager(Context context) {
		super(context);
		 init();
		// TODO Auto-generated constructor stub
	}

	public void setPreloadSize(int size)
	{
		viewpager.setOffscreenPageLimit(size);
	}
	
	void init()
	{
		LayoutInflater.from(getContext()).inflate(getViewId(), this);
		viewpager=(android.support.v4.view.ViewPager) findViewById(R.id.api_viewpager);
		indicator=(Indicator) findViewById(R.id.api_indicator);

		indicator.setOnItemClickListener(new OnItemClick() {
			
			@Override
			public void onItemClick(int p) {
				// TODO Auto-generated method stub
				viewpager.setCurrentItem(p);
			}
		});
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int p) {
				// TODO Auto-generated method stub
				indicator.select(p);
				if(onPageChangeListener!=null)
					onPageChangeListener.onPageSelected(p);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				if(onPageChangeListener!=null)
					onPageChangeListener.onPageScrolled(arg0, arg1, arg2);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				if(onPageChangeListener!=null)
					onPageChangeListener.onPageScrollStateChanged(arg0);
			}
		});
	 
		 
	}
	
	public void setAdapter(PagerAdapter a)
	{
		this.viewpager.setAdapter(a);
	}
	public void addHead(String s)
	{
		indicator.add(s);
	}
 
	
	public android.support.v4.view.ViewPager getViewpager() {
		return viewpager;
	}
	
	public void setIndicatorItemViewId(int id)
	{
		this.indicator.setItemViewId(id);
	}
 

	public void select(int i)
	{
		
	   this.indicator.select(i);
	   PagerAdapter a=	viewpager.getAdapter();
		if(a==null)
		return;
		if(a.getCount()-1<i||i<0)
		return;
			
		this.viewpager.setCurrentItem(i);
		
	}
	
	 public void setIndicatorVisibility(int visibility)
	 {
		 this.indicator.setVisibility(visibility);
	 }
	
	public int getViewId()
	{
		return R.layout.api_viewpager;
	}

	
	OnPageChangeListener onPageChangeListener;

	public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
		this.onPageChangeListener = onPageChangeListener;
	}
}
