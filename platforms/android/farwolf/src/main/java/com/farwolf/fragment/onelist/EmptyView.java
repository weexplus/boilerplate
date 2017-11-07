package com.farwolf.fragment.onelist;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.farwolf.base.ViewBase;
import com.farwolf.libary.R;

@EViewGroup
public class EmptyView extends ViewBase {

	@ViewById
	public TextView txt;
	
	
	public EmptyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}



	public EmptyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}



	@Override
	public int getViewId() {
		// TODO Auto-generated method stub
		return R.layout.api_empty_view;
	}

	public void drawableTop(int id,OnClickListener l)
	{
		Drawable drawable = getResources().getDrawable(id);
        float k=0.8f;
		drawable.setBounds(0, -10, (int)(drawable.getMinimumWidth()*k),  (int)(drawable.getMinimumHeight()*k));		 
//		drawable.setBounds(0, 0, 0, 0);		 
		(txt).setCompoundDrawables(null, drawable, null, null);
		this.setOnClickListener(l);
		invalidate();
	}
	
	
	public void setText(String s)
	{
		this.txt.setText(s);
	}
	
	@Override	
	public void init() {
		// TODO Auto-generated method stub
         
	}

}
