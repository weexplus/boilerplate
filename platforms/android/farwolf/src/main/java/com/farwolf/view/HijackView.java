package com.farwolf.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class HijackView extends LinearLayout {

	OnTouchListener listener;
	public void setTouchListener(OnTouchListener listener) {
		this.listener = listener;
	}

	public HijackView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Intial();
		// TODO Auto-generated constructor stub
	}

	public HijackView(Context context) {
		super(context);
		Intial();
		// TODO Auto-generated constructor stub
	}

	private void Intial()
	{
		setOnTouchListener(null);
	}
	

	
	@Override
	public void setOnTouchListener(OnTouchListener l) {
		// TODO Auto-generated method stub
		super.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(listener!=null)
					listener.onTouch(v, event);
				return true;
			}
		});
	}
}
