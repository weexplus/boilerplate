package com.farwolf.reader;

import com.farwolf.base.ServiceBase;
import com.farwolf.json.JsonListener;
import com.farwolf.json.JsonReader;

public abstract class SilenceHttpServiceBase<T extends JsonReader> extends ServiceBase implements JsonListener<T>{

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void compelete() {
		// TODO Auto-generated method stub
		
	}

	 
	@Override
	public void fail(T j, String code, String msg) {
		// TODO Auto-generated method stub
		
	}

	 

	@Override
	public void exception(Object o) {
		// TODO Auto-generated method stub
		
	}

}
