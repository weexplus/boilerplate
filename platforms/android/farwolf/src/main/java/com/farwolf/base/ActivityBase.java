package com.farwolf.base;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.farwolf.util.AppTool;

import org.androidannotations.annotations.EActivity;

@EActivity
public abstract class ActivityBase extends FragmentActivity{

	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(getViewId());
		if(AppTool.OSVersion()>=19)
		{
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			getWindow().setStatusBarColor(Color.TRANSPARENT);
		}
	}
	
	public abstract int getViewId();
	

	public abstract void init();
	
	public void replace(int id,Fragment fragment)
	{
		getSupportFragmentManager().beginTransaction().replace(id, fragment).commitAllowingStateLoss();
	}
 
	
	public void toast(String msg,int time)
	{
		Toast.makeText(getBaseContext(), msg, time).show();
	}
	
	public void toast(String msg)
	{
		this.toast(msg, 200);
	}


//	@AfterPermissionGranted(10010)
//	public boolean requirePermission(String perssion) {
//		String[] perms = {
//				perssion
//		};
//		if (EasyPermissions.hasPermissions(this, perms)) {
//			// Already have permission, do the thing
//			// ...
////			Toast.makeText(this, "Permissions Granted!", Toast.LENGTH_LONG).show();
//			return true;
//		} else {
//			// Do not have permissions, request them now
//			EasyPermissions.requestPermissions(this, PerssionUtil.getPerssionName(perssion),
//					10010, perms);
//			return false;
//		}
//	}
//
//	@Override
//	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//		// EasyPermissions handles the request result.
//		EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//	}




}
