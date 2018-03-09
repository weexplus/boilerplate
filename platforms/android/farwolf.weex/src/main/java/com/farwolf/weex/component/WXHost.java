package com.farwolf.weex.component;

import android.content.Context;
import android.support.annotation.NonNull;

import com.farwolf.weex.util.Weex;
import com.farwolf.weex.view.WXTabView;
import com.farwolf.weex.view.WXTabView_;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.ArrayList;

/**
 * Created by zhengjiangrong on 2017/8/17.
 */

public class WXHost extends WXComponent<WXTabView> {


    public WXHost(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
    }

    public WXHost(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, int type) {
        super(instance, dom, parent, type);
    }


    @Override
    protected WXTabView initComponentHostView(@NonNull Context context) {
        WXTabView w= WXTabView_.build(context);
//        WXTabView w= new WXTabView(context);
//        w.holdComponent(this);

        return w;
    }


    @WXComponentProp(name = "items")
    public void setItems(ArrayList l)
    {

        ArrayList lx=new ArrayList();
        for(Object q:l)
        {
             lx.add(Weex.getRelativeUrl(q+"", this.getInstance()));
        }

        this.getHostView().setItems(lx);
    }

    @WXComponentProp(name = "index")
    public void setItems(int index)
    {

        this.getHostView().setIndex(index);
    }


    @Override
    public void onActivityResume() {
        super.onActivityResume();
        Context c=  mInstance.getContext();
        getHostView().setChildContext(c);
    }


}
