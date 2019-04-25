package com.farwolf.weex.module;

import android.util.Log;

import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.util.HotRefreshManager;
import com.taobao.weex.annotation.JSMethod;

public class WXFLogModule extends WXModuleBase {


    @JSMethod
    public void log(String msg)
    {
        Log.i("weexplus",msg);
        HotRefreshManager.getInstance().send("log:"+msg);

    }
}
