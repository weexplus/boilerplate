package com.farwolf.view.progress;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import android.view.View;
import android.widget.ProgressBar;

@EBean
public class CircleProgressBar extends ViewProgress{

	@ViewById
	View progress;
	
	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return progress;
	}

}
