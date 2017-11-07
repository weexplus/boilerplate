package com.farwolf.fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import com.farwolf.fragment.HttpFragmentBase;
import com.farwolf.json.JsonReader;
import com.farwolf.view.progress.DialogProgress;
import com.farwolf.view.progress.IProgress;

@EFragment
public abstract class DialogProgressFragmentBase<T extends JsonReader> extends HttpFragmentBase<T> {

	@Bean
	protected DialogProgress progress;
	
	@Override
	public IProgress getProgress() {
		// TODO Auto-generated method stub
		return progress;
	}
	
	@AfterViews
	protected void  init_DialogProgressFragmentBase()
	{
		progress.init(getTitle(), getContent());
	}
	
	
	
	public String getTitle()
	{
		return "加载中...";
	}
	public String getContent()
	{
		return "请稍候...";
	}
	
	
	
	
	

}
