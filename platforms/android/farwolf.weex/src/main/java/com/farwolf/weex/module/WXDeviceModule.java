package com.farwolf.weex.module;

import com.farwolf.util.DeviceTool_;
import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;

public class WXDeviceModule extends WXModuleBase {

    @JSMethod(uiThread = false)
    public String mac(){
        return DeviceTool_.getInstance_(getContext()).getMac();
    }
}
