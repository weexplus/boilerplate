package com.farwolf.weex.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;

;

/**
 * Created by zhengjiangrong on 2017/6/18.
 */

public class WXPrefModule extends WXModule {


    @JSMethod
    public void setString(String key,String value)
    {
        if(this.mWXSDKInstance==null||this.mWXSDKInstance.getContext()==null)
            return ;
        SharedPreferences sharedPreferences = this.mWXSDKInstance.getContext().getSharedPreferences("farwolf_weex", Context.MODE_MULTI_PROCESS); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString(key, value).commit();


    }

    @JSMethod(uiThread = false)
    public String getString(String key)
    {
        if(this.mWXSDKInstance==null||this.mWXSDKInstance.getContext()==null)
            return "";
        SharedPreferences sharedPreferences = this.mWXSDKInstance.getContext().getSharedPreferences("farwolf_weex", Context.MODE_MULTI_PROCESS); //私有数据
        String  s=  sharedPreferences.getString(key,null);
        return s;

    }


    @JSMethod
    public void remove(String key)
    {
        if(this.mWXSDKInstance==null||this.mWXSDKInstance.getContext()==null)
            return;
        SharedPreferences sharedPreferences = this.mWXSDKInstance.getContext().getSharedPreferences("farwolf_weex", Context.MODE_MULTI_PROCESS); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器

        editor.remove(key).commit();

    }

    @JSMethod
    public void set(String key,HashMap value)
    {

        if(this.mWXSDKInstance==null||this.mWXSDKInstance.getContext()==null)
            return;
        JSONObject j=new JSONObject(value);
        SharedPreferences sharedPreferences = this.mWXSDKInstance.getContext().getSharedPreferences("farwolf_weex", Context.MODE_MULTI_PROCESS); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString(key, j.toJSONString()).commit();

    }


    @JSMethod(uiThread = false)
    public Object get(String key)
    {
        if(this.mWXSDKInstance==null||this.mWXSDKInstance.getContext()==null)
            return new HashMap();
        SharedPreferences sharedPreferences = this.mWXSDKInstance.getContext().getSharedPreferences("farwolf_weex", Context.MODE_MULTI_PROCESS); //私有数据
        String s=  sharedPreferences.getString(key,null);
        if(s==null)
            return null;
        JSONObject j= JSONObject.parseObject(s);
        return j;

    }



}
