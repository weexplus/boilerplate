package com.farwolf.weex.component;

import android.graphics.Color;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.component.list.WXListComponent;

/**
 * Created by zhengjiangrong on 2017/8/15.
 */

public class WXFListComponent extends WXListComponent {


    public WXFListComponent(WXSDKInstance instance, WXDomObject node, WXVContainer parent, boolean lazy) {
        super(instance, node, parent, lazy);
    }



    @WXComponentProp(name = Constants.Name.BACKGROUND_COLOR)
    public void setBacgroundColor(String color) {

         this.getHostView().setBackgroundColor(Color.parseColor(color));

    }





}
