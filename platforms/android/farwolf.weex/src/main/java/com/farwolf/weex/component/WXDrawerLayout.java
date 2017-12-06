package com.farwolf.weex.component;

import android.content.Context;
import android.support.annotation.NonNull;

import com.farwolf.weex.util.Weex;
import com.farwolf.weex.view.DrawerLayoutView;
import com.farwolf.weex.view.DrawerLayoutView_;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by even on 2017/10/18.
 */

public class WXDrawerLayout extends WXComponent<DrawerLayoutView> {

    DrawerLayoutView_ drawerLayoutView_;


    public WXDrawerLayout(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
    }

    public WXDrawerLayout(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, int type) {
        super(instance, dom, parent, type);
    }


    @Override
    protected DrawerLayoutView initComponentHostView(@NonNull Context context) {
        drawerLayoutView_ = (DrawerLayoutView_) DrawerLayoutView_.build(context);
        return drawerLayoutView_;
    }

    @JSMethod
    public void toggle() {
        if (!drawerLayoutView_.drawer_layout.isDrawerOpen(drawerLayoutView_.nav_view)) {
            drawerLayoutView_.drawer_layout.openDrawer(drawerLayoutView_.nav_view);
        }else {
            drawerLayoutView_.drawer_layout.closeDrawer(drawerLayoutView_.nav_view);
        }
    }

    @WXComponentProp(name = "slid_src")
    public void setSlidUrl(String url)
    {
        url= Weex.getRelativeUrl(url,this.getInstance());
        drawerLayoutView_.setSlidUrl(url);
    }

    @WXComponentProp(name = "src")
    public void setMainUrl(String url)
    {
        url= Weex.getRelativeUrl(url,this.getInstance());
        drawerLayoutView_.setMainUrl(url);
    }



}
