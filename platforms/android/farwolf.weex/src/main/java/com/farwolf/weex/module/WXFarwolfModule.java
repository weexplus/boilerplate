package com.farwolf.weex.module;

import com.farwolf.weex.util.Weex;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

/**
 * Created by zhengjiangrong on 2017/6/15.
 */

public class WXFarwolfModule extends WXModule {



    @JSMethod
    public void setBaseDir(String basedir)
    {
        Weex.basedir=basedir;
    }



    @JSMethod(uiThread = true)
    public String getBaseDir()
    {
        return Weex.basedir;
    }



}
