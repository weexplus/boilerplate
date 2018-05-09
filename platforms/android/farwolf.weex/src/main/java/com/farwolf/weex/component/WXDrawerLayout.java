package com.farwolf.weex.component;

import android.content.Context;
import android.content.Intent;
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
        drawerLayoutView_.setParentInstance(getInstance());
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

    @WXComponentProp(name = "slidSrc")
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

    @WXComponentProp(name = "leftWidth")
    public void setLeftWidth(float leftwidth)
    {
        drawerLayoutView_.setLeftWidth((int)Weex.length(leftwidth));
    }


    @Override
    public void onActivityResume() {
        super.onActivityResume();
        drawerLayoutView_.setChildContext(getInstance().getContext());
        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
            drawerLayoutView_.main_view.instance.onActivityResume();
        if(drawerLayoutView_.nav_view!=null&&drawerLayoutView_.nav_view.instance!=null)
            drawerLayoutView_.nav_view.instance.onActivityResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
            drawerLayoutView_.main_view.instance.onActivityResult(requestCode,resultCode,data);
        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
            drawerLayoutView_.nav_view.instance.onActivityResult(requestCode,resultCode,data);
    }


//    @Override
//    public void onActivityCreate() {
//        super.onActivityCreate();
//        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
//            drawerLayoutView_.main_view.instance.onActivityCreate();
//        if(drawerLayoutView_.nav_view!=null&&drawerLayoutView_.nav_view.instance!=null)
//            drawerLayoutView_.nav_view.instance.onActivityCreate();
//    }


//    @Override
//    public void onActivityDestroy() {
//        super.onActivityDestroy();
//        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
//            drawerLayoutView_.main_view.instance.onActivityDestroy();
//        if(drawerLayoutView_.nav_view!=null&&drawerLayoutView_.nav_view.instance!=null)
//            drawerLayoutView_.nav_view.instance.onActivityDestroy();
//    }


    @Override
    public void onActivityPause() {
        super.onActivityPause();
        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
            drawerLayoutView_.main_view.instance.onActivityPause();
        if(drawerLayoutView_.nav_view!=null&&drawerLayoutView_.nav_view.instance!=null)
            drawerLayoutView_.nav_view.instance.onActivityPause();
    }

    @Override
    public void onActivityStart() {
        super.onActivityStart();
        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
            drawerLayoutView_.main_view.instance.onActivityStart();
        if(drawerLayoutView_.nav_view!=null&&drawerLayoutView_.nav_view.instance!=null)
            drawerLayoutView_.nav_view.instance.onActivityStart();
    }

    @Override
    public boolean onActivityBack() {

        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
            drawerLayoutView_.main_view.instance.onActivityBack();
        if(drawerLayoutView_.nav_view!=null&&drawerLayoutView_.nav_view.instance!=null)
            drawerLayoutView_.nav_view.instance.onActivityBack();

        return super.onActivityBack();
    }

    @Override
    public void onActivityStop() {
        super.onActivityStop();
        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
            drawerLayoutView_.main_view.instance.onActivityStop();
        if(drawerLayoutView_.nav_view!=null&&drawerLayoutView_.nav_view.instance!=null)
            drawerLayoutView_.nav_view.instance.onActivityStop();

    }
}
