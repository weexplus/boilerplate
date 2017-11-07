package com.farwolf.util;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

@EBean
public class DeviceTool {
	
	@RootContext
	Context context;

	
	public TelephonyManager getTelephonyManager()
	{
		return  (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);   
	}
	
	public String getDeviceId()
	{
		
		return getTelephonyManager().getDeviceId();   
	}
	
	
	public String getMacId()
	{
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		 
		WifiInfo info = wifi.getConnectionInfo();
		 
		return info.getMacAddress();
	}
	
	
	
	
	
	

}
