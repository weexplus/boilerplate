package com.farwolf.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.farwolf.base.ViewBase;
import com.farwolf.libary.R;
import com.farwolf.util.AppTool;
import com.farwolf.util.KeyBoardTool;
import com.farwolf.util.KeyBoardTool_;


public class TitleBar extends ViewBase {


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

	@Override
	public int getViewId() {
		// TODO Auto-generated method stub

		if(AppTool.OSVersion()>=19)
		{
			return R.layout.api_titlebar60;
		}
		else
		{
			return R.layout.api_titlebar;
		}
//		return R.layout.api_titlebar;

	}

	public void initial()
	{
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
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
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
	
	public void setBack()
	{
		this.leftview.setVisibility(View.VISIBLE);
		setLeftClick(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				KeyBoardTool tool= KeyBoardTool_.getInstance_(TitleBar.this.getContext());
				tool.dismiss();
				((Activity)getContext()).finish();
			}
		});
	}
	
	public void setRightClick(OnClickListener l)
	{
		this.rightview.setOnClickListener(l);
	}

	public void setTitleClick(OnClickListener l)
	{
		this.title.setOnClickListener(l);
	}


	public void setLeftClick(OnClickListener l)
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
