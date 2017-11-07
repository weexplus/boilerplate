package com.farwolf.reader;

import android.util.Log;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import com.farwolf.base.ServiceBase;
import com.farwolf.json.JsonListener;
import com.farwolf.json.JsonReader;
import com.farwolf.view.progress.DialogProgress;
import com.farwolf.view.progress.IProgress;

@EBean
public abstract class HttpServiceBase <T extends JsonReader> extends ServiceBase  implements JsonListener<T>{

	
	 @Bean
	 protected  DialogProgress progress;
	 
	 
	 public IProgress getProgress()
	 {
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


	 @Override
	public void fail(T j, String code, String msg) {
		// TODO Auto-generated method stub
		 toast(msg);
	}
	
	 

	@Override
	public void exception(Object o) {
		// TODO Auto-generated method stub
		Log.e("网络异常!",this.getClass()+"");
		toast("网络异常!");
	}

 
}
