package com.farwolf.weex.module;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.farwolf.util.AppTool;
import com.farwolf.util.ScreenTool;
import com.farwolf.util.ScreenTool_;
import com.farwolf.util.StringUtil;
import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zhengjiangrong on 2017/5/10.
 */

public class WXNavBarModule extends WXModuleBase {



    @JSMethod
    public void setTitle(String title)
    {

         if(getTitleBar()!=null)
         getTitleBar().setTitle(title);
    }

    @JSMethod
    public void setTitleColor(String color)
    {
        if(getTitleBar()!=null)
        getTitleBar().title.setTextColor(Color.parseColor(color));
    }

    @JSMethod
    public void setBack(boolean back,String style)
    {
        if(getTitleBar()==null)
            return;
           if(back)
           {
               getTitleBar().setBack();
               if("black".equals(style))
               {
//
                   getTitleBar().leftimage.setBackgroundResource(com.farwolf.libary.R.drawable.api_black_back_selector);

               }
           }
           else
           {
               getTitleBar().leftview.setVisibility(View.GONE);

           }
    }

    @JSMethod
    public void makeTransparent()
    {
        getTitleBar().layout.setBackgroundColor(Color.TRANSPARENT);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(0, 0, 0, 0);
        getActivity().rootContainer.setLayoutParams(lp);
    }


    @JSMethod
    public void makeUnTransparent(String color)
    {
        getTitleBar().layout.setBackgroundColor(Color.parseColor(color));
        ScreenTool tool= ScreenTool_.getInstance_(getActivity());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        if(AppTool.OSVersion()>=19)
        {

            lp.setMargins(0, tool.toDip(60), 0, 0);

        }
        else
        {
            lp.setMargins(0, tool.toDip(52), 0, 0);
        }
        getActivity().rootContainer.setLayoutParams(lp);
    }



    @JSMethod
    public void hideBottomLine(boolean hide)
    {
        if(hide)
        {
            getTitleBar().bottomline.setVisibility(View.GONE);
        }
        else
        {
            getTitleBar().bottomline.setVisibility(View.VISIBLE);
        }
    }




    @JSMethod
    public void setStatusBarStyle( String style)
    {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = ((Activity)this.mWXSDKInstance.getContext()).getWindow();
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                if("black".equals(style))
                {

                    setStatusBarIconDark(true);
//                    window.setStatusBarColor(Color.BLACK);
//                    setMiuiStatusBarDarkMode(((Activity)this.mWXSDKInstance.getContext()),true);
//                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }
                else
                {
                    setStatusBarIconDark(false);
//                    setMiuiStatusBarDarkMode(((Activity)this.mWXSDKInstance.getContext()),false);
//                    window.setStatusBarColor(Color.WHITE);
                }


                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStatusBarIconDark(boolean dark) {
        try {
            Object win  = ((Activity)this.mWXSDKInstance.getContext()).getWindow();
            Class<?> cls = win.getClass();
            Method method = cls.getDeclaredMethod("setStatusBarIconDark", boolean.class);
            method.invoke(win, dark);
        } catch (Exception e) {
            Log.v("ff", "statusBarIconDark,e=" + e);
        }
    }

    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public boolean setStatusBarLightMode(Activity activity, boolean isFontColorDark) {
        Window window = activity.getWindow();
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (isFontColorDark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }



    @JSMethod
    public void hide()
    {
        if(getTitleBar()==null)
            return;
        getTitleBar().setVisibility(View.GONE);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(0, 0, 0, 0);
        getActivity().rootContainer.setLayoutParams(lp);
    }


    @JSMethod
    public void show()
    {
        if(getTitleBar()==null)
            return;
        getTitleBar().setVisibility(View.VISIBLE);
        ScreenTool tool= ScreenTool_.getInstance_(getActivity());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        if(AppTool.OSVersion()>=19)
        {

            lp.setMargins(0, tool.toDip(60), 0, 0);

        }
        else
        {
            lp.setMargins(0, tool.toDip(52), 0, 0);
        }
        getActivity().rootContainer.setLayoutParams(lp);
    }

    @JSMethod
    public void setBackgroundColor(String color)
    {
        if(getTitleBar()==null)
            return;
        getTitleBar().layout.setBackgroundColor(Color.parseColor(color));
    }

    @JSMethod
    public void setRightText(String text,String color,final JSCallback callback)
    {
        getTitleBar().setRightText(text);
        getTitleBar().setRightTextColor(color);
        getTitleBar().setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.invokeAndKeepAlive(null);
            }
        });
    }

    @JSMethod
    public void setLeftText(String text)
    {
        if(getTitleBar()==null)
            return;
        getTitleBar().setLeftText(text);
    }

    @JSMethod
    public void setRightImageFull(String src,float width,float height, final JSCallback callback)
    {
        if(getTitleBar()==null)
            return;
        getTitleBar().rightview.setVisibility(View.VISIBLE);
        getTitleBar().right_image.setVisibility(View.VISIBLE);
        getTitleBar().setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.invokeAndKeepAlive(null);
            }
        });
        if(src.startsWith("root:"))
            src= Weex.getRootUrl(src,this.mWXSDKInstance);
        else
            src=StringUtil.getRealUrl(this.mWXSDKInstance.getBundleUrl(),src);

        Weex.downloadImg(src, getTitleBar().right_image,this.mWXSDKInstance.getContext());
    }
    @JSMethod
    public void setRightImage(String src,final JSCallback callback)
    {
       this.setRightImageFull(src,25,25,callback);
    }
    @JSMethod
    public void setLeftImage(String src,final JSCallback callback)
    {
        this.setLeftImageFull(src,25,25,callback);
    }


    @JSMethod
    public void setLeftImageFull(String src,float width,float height,final JSCallback callback)
    {
        if(getTitleBar()==null)
            return;
        getTitleBar().leftview.setVisibility(View.VISIBLE);
        getTitleBar().leftimage.setVisibility(View.VISIBLE);
        getTitleBar().leftimage.setBackgroundDrawable(null);
        getTitleBar().setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.invokeAndKeepAlive(null);
            }
        });
        if(src.startsWith("root:"))
           src= Weex.getRootUrl(src,this.mWXSDKInstance);
        else
        src=StringUtil.getRealUrl(this.mWXSDKInstance.getBundleUrl(),src);

        Weex.downloadImg(src, getTitleBar().leftimage,this.mWXSDKInstance.getContext());
    }

}
