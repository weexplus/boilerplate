package com.weexplus.module;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.WindowManager;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.weexplus.activity.WxpActivity;
import com.weexplus.core.WeexPlus;
import com.weexplus.event.Event;
import com.weexplus.util.ActivityManager;
import com.weexplus.util.AppTool;
import com.weexplus.util.WXModuleBase;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;

/**
 * Created by zhengjiangrong on 2017/6/15.
 */

public class WXPageModule extends WXModuleBase {





    @JSMethod
    public void setKeyboardMode(String mode)
    {
        if("adjust_resize".equals(mode))
        {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        else if("adjust_pan".equals(mode))
        {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
        else if("adjust_nothing".equals(mode))
        {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }
    }


    @JSMethod(uiThread = true)
    public void reload()
    {
       getActivity().refresh();
    }




    @JSMethod
    public void closeSplash()
    {
        WxpActivity a=  (WxpActivity)this.mWXSDKInstance.getContext();
        EventBus.getDefault().post(new Event("closeSplash",a.module,null));

    }

    @JSMethod(uiThread = false)
    public int statusBarHeight(){
        Class c = null;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            int statusBarHeight = this.getContext().getResources().getDimensionPixelSize(x);
            return  (int) WeexPlus.toWeex(statusBarHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @JSMethod
    public void doubleBack()
    {
        getActivity().exitEnable=true;
    }

    @JSMethod
    public void enableBackKey(boolean enable)
    {
        getActivity().backKeyEnable=enable;
    }

    @JSMethod
    public void setBackKeyCallback(JSCallback callback)
    {
        getActivity().backkeyCallback=callback;
    }


    @JSMethod
    public void exit()
    {
        AppTool.exit(getActivity());
    }


    @JSMethod
    public void kill()
    {
        AppTool.kill();
    }



    @JSMethod(uiThread = false)
    public String getTopPage(){
        ActivityManager am= ActivityManager.getInstance();
        if(am==null)
            return "null";
        WxpActivity ac= (WxpActivity)am.getCurrentActivity();
        if(ac!=null)
            return  ac.instance.getBundleUrl();
        return "";
    }


    @JSMethod
    public void pressHome()
    {
        Intent intent= new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //如果是服务里调用，必须加入new task标识
        intent.addCategory(Intent.CATEGORY_HOME);
        getActivity().startActivity(intent);

    }

}
