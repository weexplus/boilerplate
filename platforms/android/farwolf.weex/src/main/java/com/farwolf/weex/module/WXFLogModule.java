package com.farwolf.weex.module;

import android.util.Log;

import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.util.HotRefreshManager;
import com.taobao.weex.annotation.JSMethod;

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
