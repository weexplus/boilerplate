package com.farwolf.update.download.utils;

import android.content.Context;
import android.widget.Toast;

public class GetToast {
	private static Toast toast = null;
	public static Toast useString(Context context, String string) {
		if (context == null)
			return null;
		if(toast == null){
			toast = Toast.makeText(context,string,Toast.LENGTH_SHORT);
		}else{
			toast.setText(string);
		}
		toast.show();
		return toast;
	}

	/**
	 *
	 * @param context
	 * @param id StringVuales下的StringId
	 * @return
	 */
	public static Toast useid(Context context, int id) {
		if (context == null)
			return null;
		if(toast == null){
			toast = Toast.makeText(context,id,Toast.LENGTH_SHORT);
		}else{
			toast.setText(id);
		}
		toast.show();
		return toast;
	}
	public static Toast useInt(Context context, int id) {
		if (context == null)
			return null;
		if(toast == null){
			toast = Toast.makeText(context,""+id,Toast.LENGTH_SHORT);
		}else{
			toast.setText(""+id);
		}
		toast.show();
		return toast;
	}
	public static Toast setToastPosition(Context context, String string ,int grivity,int xOffset, int yOffset){
		if (context == null)
			return null;
		if(toast == null){
			toast = Toast.makeText(context,string,Toast.LENGTH_SHORT);
		}else{
			toast.setText(string);
		}
		toast.setGravity(grivity,xOffset, yOffset);
		return toast;
	}


}
