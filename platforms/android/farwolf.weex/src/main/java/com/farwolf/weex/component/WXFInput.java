package com.farwolf.weex.component;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXInput;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by zhengjiangrong on 2017/12/13.
 */

public class WXFInput extends WXInput {


    public WXFInput(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, boolean isLazy) {
        super(instance, dom, parent, isLazy);
    }


    @Override
    public void setType(String type) {
        super.setType(type);
        getHostView().setSelection(getHostView().getText().length());
    }
}
