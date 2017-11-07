package com.farwolf.view.progress;

import android.view.View;

public abstract class ViewProgress implements IProgress{

	
	public abstract View getView();
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		if(getView()!=null)
		getView().setVisibility(View.VISIBLE);
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		if(getView()!=null)
			getView().setVisibility(View.GONE);
	}

}
