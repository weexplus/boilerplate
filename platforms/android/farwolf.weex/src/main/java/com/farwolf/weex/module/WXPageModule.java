package com.farwolf.weex.module;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.farwolf.weex.activity.ActivityManager;
import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.core.WeexFactory_;
import com.farwolf.weex.event.Event;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static com.taobao.weex.WXEnvironment.getApplication;

/**
 * Created by zhengjiangrong on 2017/6/15.
 */

public class WXPageModule extends WXModuleBase {



    @JSMethod
    public void resetFrame()
    {
        getActivity().resetFrame();

    }

    @JSMethod
    public void prerender(String url)
    {
        WeexFactory_ w=WeexFactory_.getInstance_(getActivity());
        w.preRender(url,url,null);

    }


    @JSMethod(uiThread = true)
    public void reload()
    {
       getActivity().reload();
    }


    @JSMethod(uiThread = true)
    public void setMainPage(String url)
    {
        url=Weex.getRelativeUrl(url,mWXSDKInstance);
        SharedPreferences sharedPreferences = this.mWXSDKInstance.getContext().getSharedPreferences("farwolf_weex", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("mainurl", url).commit();

    }

    @JSMethod
    public void closeSplash()
    {
        EventBus.getDefault().post(new Event("closeSplash",null));

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
        List<Activity> l= ActivityManager.getActivitiesByApplication(getApplication());
        for(Activity a:l)
        {
            a.finish();
        }
        System.exit(0);
    }


}
