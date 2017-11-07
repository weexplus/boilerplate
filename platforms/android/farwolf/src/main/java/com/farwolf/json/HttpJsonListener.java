package com.farwolf.json;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public abstract class HttpJsonListener<T extends JsonReader> implements JsonListener<T>{

	Context context;
	
	
	public HttpJsonListener(Context context) {
		super();
		this.context = context;
	}

 
 

	@Override
	public void fail(T j, String code, String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(context, msg, 100).show();
	}

	@Override
	public void exception(Object o) {
		// TODO Auto-generated method stub
		Log.e("网络异常!",this.getClass()+"");
		Toast.makeText(context, "网络异常", 100).show();
	}

}
