package com.farwolf.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.farwolf.base.ViewBase;
import com.farwolf.libary.R;

 
public class Title extends ViewBase {
	
	public View view;
 
	
	public View back;   
	public ImageView img;		 
	public TextView text;      
	public TextView righttext;      

	public Title(Context context, AttributeSet attrs) {
		super(context, attrs);
		IntialComponent();
		// TODO Auto-generated constructor stub
	}

	public Title(Context context) {
		super(context);
		IntialComponent();
		// TODO Auto-generated constructor stub
	}

	 


	protected void IntialComponent()   {
		// TODO Auto-generated method stub
		view = (View)findViewById(R.id.layout);
		
		back = findViewById(R.id.title_back);
		img = (ImageView) findViewById(R.id.title_rightbtn);
		text = (TextView) findViewById(R.id.title_text);
        righttext=(TextView) findViewById(R.id.right_text);
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((Activity) getContext()).finish();
			}
		});
	}
	
	public void setBgColor(int color) {
		this.view.setBackgroundResource(color);
	}
	
	public void setColor(int color) {
		this.text.setTextColor(color);
	}
	
	public void setTitle(String title) {
		this.text.setText(title);
	}
	public void setRightText(String text,OnClickListener l) {
		this.righttext.setText(text);
		this.righttext.setOnClickListener(l);
		this.righttext.setVisibility(View.VISIBLE);
	}

	public void setIsBack(boolean isback) {
		if (isback) {
			back.setVisibility(View.VISIBLE);
		} else {
			back.setVisibility(View.GONE);
		}
	}

	public void setDrawbleId(int id) {
		if(id!=-1)
		{
			this.img.setBackgroundResource(id);
			this.img.setVisibility(View.VISIBLE);
		}
	}
	
	

	public void setListener(OnClickListener listerer) {
		if(listerer!=null)
		this.img.setOnClickListener(listerer);
	}
	
	public void setBackListener(OnClickListener listerer) {
		if(listerer!=null)
			this.back.setOnClickListener(listerer);
	}

	 
	public void init(boolean isback, String title, int drawbleid, OnClickListener listerer) {
		if (drawbleid == -1 && listerer == null) {
			if(img!=null)
			img.setVisibility(View.INVISIBLE);
		}
		else
		{
			if(img!=null)
			img.setVisibility(View.VISIBLE);
		}
		setIsBack(isback);
		setTitle(title);
		
		setDrawbleId(drawbleid);
		setListener(listerer);
	}

	public void init(boolean isback, String title) {
		setIsBack(isback);
		setTitle(title);
		this.init(isback, title, -1, null);
	}

	public void init(String title, int drawbleid, OnClickListener listerer) {
		setTitle(title);
		setDrawbleId(drawbleid);
		setListener(listerer);
		this.init(false, title, drawbleid, listerer);
	}
 
	
	public View getLayout()
	{
		return view;
	}

	@Override
	public int getViewId() {
		// TODO Auto-generated method stub
		return R.layout.api_title_view;
	}

	@Override	
	public void init() {
		// TODO Auto-generated method stub
		 
	}

}
