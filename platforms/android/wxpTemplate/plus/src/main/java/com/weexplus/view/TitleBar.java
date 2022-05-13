package com.weexplus.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weex.weexplus.R;
import com.weexplus.util.AppTool;


public class TitleBar extends LinearLayout {


	public ViewGroup layout;
	public LinearLayout leftview;   
	public TextView title;   
	public LinearLayout rightview;

	public ImageView leftimage;
	public TextView left_text;

	public ImageView right_image;
	public TextView right_text;

	public View bottomline;

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		 initial();

		// TODO Auto-generated constructor stub
	}

	public TitleBar(Context context) {
		super(context);
		 initial();
		// TODO Auto-generated constructor stub
	}



	public void initial()
	{

		if(AppTool.OSVersion()>=19)
		{
			LayoutInflater.from(getContext()).inflate(R.layout.api_titlebar60,this);
		}
		else
		{
			LayoutInflater.from(getContext()).inflate(R.layout.api_titlebar,this);

		}
		leftview=(LinearLayout) findViewById(R.id.left_view);
		layout=(ViewGroup) findViewById(R.id.layout);
		title=(TextView) findViewById(R.id.title_text);
		rightview=(LinearLayout) findViewById(R.id.right_view);

		left_text=(TextView) findViewById(R.id.left_text);
		right_text=(TextView) findViewById(R.id.right_text);
		leftimage=(ImageView) findViewById(R.id.leftimage);
		right_image=(ImageView) findViewById(R.id.right_image);
		bottomline= findViewById(R.id.bottomline);


	}

	public void setBackImageTheme(String theme){
         if("black".equals(theme)){
			 leftimage.setBackgroundDrawable(getResources().getDrawable(R.drawable.api_black_back_selector));
		 }
	}
	

	
	public void init(boolean back,String title)
	{
		setTitle(title);
		if(back)
		{
			setBack();
			leftview.setVisibility(View.VISIBLE);
		}
		else
		{
			leftview.setVisibility(View.GONE);
			this.leftview.setOnClickListener(null);
		}
	}
	
	public void setTitle(String title)
	{
		this.title.setText(title);
	}

	public void setTitleColor(String color)
	{
		this.title.setTextColor(createColorStateList(Color.parseColor(color),Color.parseColor(color),Color.parseColor(color),Color.parseColor(color)));

	}
	
	public void setBack()
	{
		this.leftview.setVisibility(View.VISIBLE);
		setLeftClick(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				if(imm!=null&getContext()!=null&&((Activity)getContext()).getCurrentFocus()!=null)
				{
					imm.hideSoftInputFromWindow( ((Activity)getContext()).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				}
				((Activity)getContext()).finish();
			}
		});
	}
	
	public void setRightClick(View.OnClickListener l)
	{
		this.rightview.setOnClickListener(l);
	}

	public void setTitleClick(View.OnClickListener l)
	{
		this.title.setOnClickListener(l);
	}


	public void setLeftClick(View.OnClickListener l)
	{
		this.leftview.setOnClickListener(l);
	}


	public void setRightImage(int id)
	{
		this.rightview.setVisibility(View.VISIBLE);
		this.right_image.setImageDrawable(getResources().getDrawable(id));
		this.right_image.setVisibility(View.VISIBLE);
	}

	public void setLeftImage(int id)
	{
		this.leftview.setVisibility(View.VISIBLE);
		this.leftimage.setImageDrawable(getResources().getDrawable(id));
		this.leftimage.setVisibility(View.VISIBLE);
	}


	public void setLeftText(String text)
	{
		this.leftview.setVisibility(View.VISIBLE);
		this.left_text.setText(text);
		this.left_text.setVisibility(View.VISIBLE);
	}


	public void setRightText(String text)
	{
		this.rightview.setVisibility(View.VISIBLE);
		this.right_text.setText(text);
		this.right_text.setVisibility(View.VISIBLE);

	}


	public void setRightTextColor(String color)
	{
		this.right_text.setTextColor(createColorStateList(Color.parseColor(color),Color.parseColor("#eeeeee"),Color.parseColor("#eeeeee"),Color.parseColor("#eeeeee")));
	}

	/** 对TextView设置不同状态时其文字颜色。 */
	private ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
		int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };
		int[][] states = new int[6][];
		states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
		states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
		states[2] = new int[] { android.R.attr.state_enabled };
		states[3] = new int[] { android.R.attr.state_focused };
		states[4] = new int[] { android.R.attr.state_window_focused };
		states[5] = new int[] {};
		ColorStateList colorList = new ColorStateList(states, colors);
		return colorList;
	}


}
