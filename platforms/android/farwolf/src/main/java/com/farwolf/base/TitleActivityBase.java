package com.farwolf.base;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.farwolf.view.TitleBar;

@EActivity
public abstract class TitleActivityBase extends ActivityBase {

	@ViewById
	protected TitleBar title;
	
	
 
	public TitleBar getTitleBar()
	{
		return title;
	}
	

	 
}
