package com.farwolf.json;

import com.farwolf.view.progress.DialogProgress_;
import com.farwolf.view.progress.DialogProgress;
import com.farwolf.view.progress.IProgress;

import android.content.Context;

public abstract class ProgressJsonListner<T extends JsonReader> extends HttpJsonListener<T> {

 
	 protected  DialogProgress progress;
	 
	 
	 public IProgress getProgress()
	 {

		 if(progress==null)
		 progress= DialogProgress_.getInstance_(context);
		 progress.init(getTitle(), getContent());
		 return progress;
	 }
	
	 public String getTitle()
	 {
		 return "加载中";
	 }
	 public String getContent()
	 {
		 return "请稍候...";
	 }
	
	public ProgressJsonListner(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
		getProgress().show();
	}

	@Override
	public void compelete() {
		// TODO Auto-generated method stub
		getProgress().dismiss();
	}

}
