package com.farwolf.weex.bean;

import com.farwolf.view.pickerview.IPickerViewData;

public class Type implements IPickerViewData {
	
	public Type(String code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}
	
	public Type()
	{
		
	}

	public String code;
	
	public String desc;
	
	@Override
	public String getPickerViewText() {
		// TODO Auto-generated method stub
		return desc;
	}
}
