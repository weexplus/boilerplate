package com.farwolf.json;

public interface JsonListener<T extends JsonReader> {

	
	public void start();
	public void compelete();
	public void success(T j);
	public void fail(T j,String code,String msg);
	public void exception(Object o);
	
	
	
	
}
