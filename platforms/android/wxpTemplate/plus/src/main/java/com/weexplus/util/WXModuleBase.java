package com.weexplus.util;

import android.content.Context;


import com.taobao.weex.common.WXModule;
import com.weexplus.activity.WxpActivity;
import com.weexplus.core.Module;
import com.weexplus.core.WxpInstance;

/**
 * Created by zhengjiangrong on 2017/5/10.
 */

public class WXModuleBase extends WXModule {


    public WxpActivity getActivity()
    {
        WxpActivity a=(WxpActivity) mWXSDKInstance.getContext();
        return a;
    }

    public Context getContext()
    {
        return mWXSDKInstance.getContext();

    }

    public WxpInstance getWxpInstance()
    {
        return (WxpInstance)mWXSDKInstance;

    }

    public Module getModule()
    {
        return  ((WxpInstance)mWXSDKInstance).module;

    }


}
