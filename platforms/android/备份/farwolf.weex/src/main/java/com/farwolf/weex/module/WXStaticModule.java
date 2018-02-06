package com.farwolf.weex.module;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/6/6.
 */

public class WXStaticModule extends WXModule {


    public static HashMap<String,Object> m=new HashMap<>();


    @JSMethod(uiThread = false)
    public Object get(String key)
    {


        return  m.get(key);
    }

    /**
     *
     * @param key
     * @param value
     */
    @JSMethod(uiThread = false)
    public void set(String key,HashMap value)
    {
        m.put(key,value);
    }

    @JSMethod(uiThread = false)
    public String getString(String key)
    {
        if(m.containsKey(key))
            return m.get(key)+"";
        return null;
    }

    @JSMethod
    public void remove(String key)
    {
        m.remove(key);
    }

    @JSMethod(uiThread = false)
    public void setString(String key,String value)
    {
        m.put(key,value);
    }



}
