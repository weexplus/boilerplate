package com.farwolf.weex.module;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.WindowManager;

import com.farwolf.util.ActivityManager;
import com.farwolf.util.AppTool;
import com.farwolf.weex.activity.WeexActivity;
import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.core.Page;
import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.core.WeexFactory_;
import com.farwolf.weex.event.Event;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import org.greenrobot.eventbus.EventBus;

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
    public void preRender(String src,final JSCallback success)
    {
        String url= Weex.getRelativeUrl(src,mWXSDKInstance);
        WeexFactory_ w=WeexFactory_.getInstance_(getActivity());
        w.preRender(url,url,new WeexFactory.OnRenderFinishListener() {
            @Override
            public void onRenderFinish(Page p) {

                success.invoke(p.url);

            }

            @Override
            public void onRenderFailed(Page p) {

            }
        });

    }

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
        AppTool.exit(getActivity());
    }


    @JSMethod
    public void kill()
    {
        AppTool.kill();
    }



    @JSMethod(uiThread = false)
    public String getTopPage(){
        ActivityManager am=ActivityManager.getInstance();
        if(am==null)
            return "null";
        WeexActivity ac= (WeexActivity)am.getCurrentActivity();
        if(ac!=null)
            return  ac.mWXSDKInstance.getBundleUrl();
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
