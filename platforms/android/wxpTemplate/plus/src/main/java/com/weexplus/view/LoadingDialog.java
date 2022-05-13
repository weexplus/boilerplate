package com.weexplus.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weex.weexplus.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by zhengjiangrong on 2017/7/4.
 */

public class LoadingDialog extends LinearLayout {


    public LoadingView loading;


    public TextView txt;
    public RelativeLayout root;


    public FreeDialog f;


    public LoadingDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingDialog(Context context) {
        super(context);
        init();
    }


    public int getViewId() {
        return R.layout.api_loadingview;
    }


    public void init() {

        LayoutInflater.from(getContext()).inflate(getViewId(),this);
        loading=findViewById(R.id.loading);
        txt=findViewById(R.id.txt);
        root=findViewById(R.id.root);
        loading.setStyle(LoadingView.BallSpinFadeLoader);

    }

    public void setTxt(String msg){
       txt.setText(msg);
        txt.setVisibility(View.VISIBLE);
    }
    public void setStyle(int type){
        loading.setStyle(type);
    }

    public void setSize(int width){
        LinearLayout.LayoutParams lp= new  LinearLayout.LayoutParams(width,width);
        root.setLayoutParams(lp);
    }

    public void setBgColor(String color,int radius){
//        loading.getBackground().setColorFilter();
        if(radius==0){
            radius=25;
        }
        StateListDrawable mySelectorGrad = (StateListDrawable)root.getBackground();
        try {
            Class slDraClass = StateListDrawable.class;
            Method getStateCountMethod = slDraClass.getDeclaredMethod("getStateCount", new Class[0]);
            Method getStateSetMethod = slDraClass.getDeclaredMethod("getStateSet", int.class);
            Method getDrawableMethod = slDraClass.getDeclaredMethod("getStateDrawable", int.class);
            int count = (Integer) getStateCountMethod.invoke(mySelectorGrad, new Object[]{});//对应item标签

            for(int i=0;i < count;i++) {
                int[] stateSet = (int[]) getStateSetMethod.invoke(mySelectorGrad, i);//对应item标签中的 android:state_xxxx
                if (stateSet == null || stateSet.length == 0) {

                    GradientDrawable drawable = (GradientDrawable) getDrawableMethod.invoke(mySelectorGrad, i);//这就是你要获得的Enabled为false时候的drawable
                    drawable.setColor(Color.parseColor(color));
                    drawable.setCornerRadius(radius);
                } else {
                    for (int j = 0; j < stateSet.length; j++) {
                        Drawable drawable = (Drawable) getDrawableMethod.invoke(mySelectorGrad, i);//这就是你要获得的Enabled为false时候的drawable
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
