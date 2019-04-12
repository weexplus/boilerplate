package com.farwolf.weex.module;

import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.util.HotRefreshManager;
import com.taobao.weex.annotation.JSMethod;

public class WXFLogModule extends WXModuleBase {


    @JSMethod
    public void log(String msg)
    {
        HotRefreshManager.getInstance().send("log:"+msg);
    }
}
