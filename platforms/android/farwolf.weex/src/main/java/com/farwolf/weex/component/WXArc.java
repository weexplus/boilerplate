package com.farwolf.weex.component;

import android.content.Context;
import android.support.annotation.NonNull;

import com.farwolf.weex.view.ArcView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by zhengjiangrong on 2018/1/18.
 */

public class WXArc extends WXComponent<ArcView> {


    public WXArc(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
    }

    public WXArc(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, int type) {
        super(instance, dom, parent, type);
    }


    @Override
    protected ArcView initComponentHostView(@NonNull Context context) {
        return new ArcView(context);
    }

    @WXComponentProp(name = "start")
    public void setPercent(float start) {
        this.getHostView().setStartAngle(start);
    }

    @WXComponentProp(name = "angle")
    public void setSweepAngle(float sweepAngle) {
        this.getHostView().setSweepAngle(sweepAngle);
    }

    @WXComponentProp(name = "color")
    public void setColor(String color) {
       this.getHostView().setColor(color);
    }

}
