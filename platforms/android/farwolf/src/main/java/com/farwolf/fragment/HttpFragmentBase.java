package com.farwolf.fragment;

import org.androidannotations.annotations.EFragment;

import android.os.Bundle;
import android.util.Log;

import com.farwolf.base.FragmentBase;
import com.farwolf.json.JsonListener;
import com.farwolf.json.JsonReader;
import com.farwolf.view.progress.IProgress;

@EFragment
public abstract  class HttpFragmentBase<T extends JsonReader> extends FragmentBase implements JsonListener<T>{

	
	IProgress iProgress;
	
 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub	
		super.onCreate(savedInstanceState);
		iProgress =getProgress();
	}
	 
	
	 
	@Override
	public void fail(T j, String code, String msg) {
		// TODO Auto-generated method stub
      toast(msg);
	}
	
	
	
	 
	 
	 public abstract IProgress getProgress();
	 
	
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		if(iProgress!=null)
		iProgress.show();
	}


	@Override
	public void exception(Object o) {
		// TODO Auto-generated method stub
//		  toast(getClass()+ "网络异常!");
		  Log.e("网络异常!",this.getClass()+"");
		  toast("网络异常!");


	}
	
	@Override
	public void compelete() {
		// TODO Auto-generated method stub
		if(iProgress!=null)
		iProgress.dismiss();
	}

 
 

	 
 

}
