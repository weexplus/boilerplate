package com.farwolf.weex.adapter;

import com.taobao.weex.adapter.IWXJSExceptionAdapter;
import com.taobao.weex.common.WXJSExceptionInfo;
import com.taobao.weex.event.ErrorEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zhengjiangrong on 2018/3/30.
 */

public class ExceptionAdapter implements IWXJSExceptionAdapter {


    @Override
    public void onJSException(WXJSExceptionInfo e) {

        String  s= e.getException();
        if(!s.contains("invokeDestroyInstance"))
        EventBus.getDefault().post(new ErrorEvent(s));

    }
}
