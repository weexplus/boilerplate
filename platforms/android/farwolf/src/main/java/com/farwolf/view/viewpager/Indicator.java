package com.farwolf.view.viewpager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.farwolf.libary.R;

public class Indicator extends LinearLayout{

	LinearLayout layout;
	
 
	
	
	List<String> data=new ArrayList<String>();
	
 
	
	
	public Indicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}

	public Indicator(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}

	void init()
	{
		LayoutInflater.from(getContext()).inflate(getViewId(), this);
		layout=(LinearLayout) findViewById(R.id.api_layout);
		
	}
	
	
	
	public void update()
	{
		for(final String s:data)
		 {
			this.add(s);
		 }
	}
	
	
	public void clear()
	{
		this.layout.removeAllViews();
		this.data.clear();
	}
	OnItemClick onItemClickListener;
	
    public void setOnItemClickListener(OnItemClick onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}


	public static interface OnItemClick
    {
    	void onItemClick(int p);
    }

	public void setData(List<String> l) {
		this.data=l;
		update();
		 
	}
	
	public void add(final String s)
	{
		data.add(s);
	 
		View v=getItemView(s);
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int i=data.indexOf(s);
				select(i);
				if(onItemClickListener!=null)
					onItemClickListener.onItemClick(i);
				
			}
		});
		LayoutParams lp=new LayoutParams(0, LayoutParams.WRAP_CONTENT);
		lp.weight=1;
		v.setLayoutParams(lp);
//		v.setSelected(true);
		 this.layout.addView(v);
	}

	
	public void select(int p)
	{
		for(int i=0;i<layout.getChildCount();i++)
		{
			if(i==p)
			layout.getChildAt(i).setSelected(true);
			else
		    layout.getChildAt(i).setSelected(false);
			
		}
	}
	
	
	public int getViewId()
	{
		
		return R.layout.api_indicator;
	}
	
	int itemViewId=-1;
	public void setItemViewId(int itemViewId) {
		this.itemViewId = itemViewId;
	}

	public int getItemViewId()
	{
	      if(itemViewId!=-1)
		return itemViewId;
		return R.layout.api_indicator_itemview;
	}
	
	public View getItemView(String s)
	{
		View v=LayoutInflater.from(getContext()).inflate(getItemViewId(), null);
		TextView t=(TextView) v.findViewById(R.id.api_txt);
		t.setText(s);
		return v;
	}
	
	 

 

}
