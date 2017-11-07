package com.farwolf.vshop.module;

import com.farwolf.vshop.ShowTabEvent;
import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;
import com.ypy.eventbus.EventBus;

/**
 * Created by zhengjiangrong on 2017/6/29.
 */

public class TabModule extends WXModuleBase {




    @JSMethod
    public void show(String name )
    {
        EventBus.getDefault().post(new ShowTabEvent(name));
    }






}
