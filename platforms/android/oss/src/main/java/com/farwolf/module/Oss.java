package com.farwolf.module;

import com.farwolf.weex.core.PluginManager;
import com.taobao.weex.WXSDKEngine;

/**
 * Created by zhengjiangrong on 2017/12/15.
 */

public class Oss {

    static {
        PluginManager.add("com.farwolf.module");
    }

    public static void init()
    {
        try {
            WXSDKEngine.registerModule("oss", WXOSSModule.class);
        }
        catch (Exception e)
        {

        }
    }
}
