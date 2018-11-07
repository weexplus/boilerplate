package com.farwolf.jpush.module;


import com.farwolf.weex.annotation.WeexModule;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

import cn.jpush.android.api.JPushInterface;

/**
 * @author xch
 * @version 1.0
 * @create_date 2018/4/20
 */
@WeexModule(name = "jpush")
public class JpushModule extends WXModule {

    @JSMethod(uiThread =false)
    public String getJPushId(){
        return  JPushInterface.getRegistrationID(mWXSDKInstance.getContext());
    }
}
