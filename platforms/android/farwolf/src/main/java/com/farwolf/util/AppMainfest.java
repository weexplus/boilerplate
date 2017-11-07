package com.farwolf.util;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

@EBean(scope=Scope.Singleton)
public class AppMainfest {

	
 
	public String getVersionName() {
		return getInfo().versionName;
	}


	public int getVersionCode() {
		return getInfo().versionCode;
	}
	
	public String getAppName() {
		 
		return getApplicationInfo().name;
	}
	public String getPakageName() {
		
		return getApplicationInfo().packageName;
	}
	
	public ApplicationInfo getApplicationInfo()
	{
		return getInfo().applicationInfo;
	}

 
	
	
	@RootContext
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
