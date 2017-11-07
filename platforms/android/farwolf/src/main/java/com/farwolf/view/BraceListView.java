package com.farwolf.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

 
public class BraceListView extends LinearLayout {

	
	
	BaseAdapter adapter;
	
	
	public void setAdapter(BaseAdapter adapter) {
		this.adapter = adapter;
		 load();
	}

	public BraceListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public BraceListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	
	void  init()
	{
		this.setOrientation(LinearLayout.VERTICAL);
	}
	
	void load()
	{
		this.removeAllViews();
		
		 int c=adapter.getCount();
		 for(int i=0;i<c;i++)
		 {
			 View v= adapter.getView(i, null, null);
			 LayoutParams p=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
			 v.setLayoutParams(p);
			 this.addView(v);
		 }
	}

	
	
	
	
}
