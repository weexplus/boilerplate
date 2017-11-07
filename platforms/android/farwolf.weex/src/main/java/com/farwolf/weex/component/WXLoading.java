package com.farwolf.weex.component;

import android.content.Context;
import android.support.annotation.NonNull;

import com.farwolf.weex.view.LoadingView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by zhengjiangrong on 2017/6/19.
 */

public class WXLoading extends WXComponent<LoadingView> {
    public WXLoading(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
    }

    public WXLoading(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, int type) {
        super(instance, dom, parent, type);
    }


    @Override
    protected LoadingView initComponentHostView(@NonNull Context context) {
        LoadingView a=new LoadingView(context);

        a.setStyle(LoadingView.BallSpinFadeLoader);
        a.setColor("#000000");
        return a;
    }


    @WXComponentProp(name = Constants.Name.COLOR)
    public void setColor(String color)
    {
        getHostView().setColor(color);
    }


}
