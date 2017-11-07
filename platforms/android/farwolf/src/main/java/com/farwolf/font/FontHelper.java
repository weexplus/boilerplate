package com.farwolf.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FontHelper {
	
	public static void applyFont(final Context context, final View root, final String fontName) {
	    try {
	        if (root instanceof ViewGroup) {
	            ViewGroup viewGroup = (ViewGroup) root;
	            for (int i = 0; i < viewGroup.getChildCount(); i++)
	                applyFont(context, viewGroup.getChildAt(i), fontName);
	        } else if (root instanceof TextView)
	            ((TextView) root).setTypeface(Typeface.createFromAsset(context.getAssets(), fontName));
	    } catch (Exception e) {
//	        Log.e(TAG, String.format("Error occured when trying to apply %s font for %s view", fontName, root));
	        e.printStackTrace();
	    }
	}
}

