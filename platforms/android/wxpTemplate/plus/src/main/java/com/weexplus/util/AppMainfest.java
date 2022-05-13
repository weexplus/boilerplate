package com.weexplus.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;


public class AppMainfest {

	
 
	public String getVersionName() {
		return getInfo().versionName;
	}


	public int getVersionCode() {
		return getInfo().versionCode;
	}
	

	public String getPakageName() {
		
		return getApplicationInfo().packageName;
	}
	
	public ApplicationInfo getApplicationInfo()
	{
		return getInfo().applicationInfo;
	}


	public   String getAppName() {
		try{
			int labelRes = getApplicationInfo().labelRes;
			return context.getResources().getString(labelRes);
		}catch (Exception e){
			return "";
		}
	}




	Context context;
	
	public AppMainfest(Context context) {
		super();
		this.context = context;
	}
	 


	public PackageInfo getInfo()
	{
		PackageManager manager = context.getPackageManager();
		PackageInfo info=null;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	    return info;
	}
 
	
}
