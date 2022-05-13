package com.weexplus.module;

import android.util.Log;


import com.taobao.weex.annotation.JSMethod;
import com.weexplus.debug.HotRefreshManager;
import com.weexplus.util.WXModuleBase;

import java.util.HashMap;

public class WXFLogModule extends WXModuleBase {


    @JSMethod
    public void log(HashMap param)
    {
        String msg=param.get("msg")+"";
        String level="info";
        if(param.containsKey("level")){
            level=param.get("level")+"";
        }
        Log.i("weexplus",msg);
//        HotRefreshManager.getInstance().send("log:"+msg);
        HotRefreshManager.getInstance().send("log:"+level+"level:"+msg);

    }
}
