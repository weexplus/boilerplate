package com.farwolf.weex.component;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.farwolf.weex.util.Weex;
import com.farwolf.weex.view.DrawerLayoutView;
import com.farwolf.weex.view.DrawerLayoutView_;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by even on 2017/10/18.
 */

public class WXDrawerLayout extends WXVContainer<DrawerLayoutView> {

    DrawerLayoutView_ drawerLayoutView_;
    ViewGroup main;
    int childCount=0;

    public WXDrawerLayout(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }


//    public WXDrawerLayout(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
//        super(instance, dom, parent);
//    }


    @Override
    public void addChild(final WXComponent child, int index) {

        childCount++;
        super.addChild(child,index);
        child.fireEvent("load",mInstance.param);
    }



    public boolean hasInitLeftChild()
    {
        return  drawerLayoutView_.nav_view.getChildCount()>0;
    }
    public void addLeftChild(WXChild child)
    {

        drawerLayoutView_.setSlidView(child.getHostView());

    }


    public boolean hasInitMainChild()
    {
        return  drawerLayoutView_.main_view.getChildCount()>0;
    }
    public void addMainChild(WXChild child)
    {

        drawerLayoutView_.setMainView(child.getHostView());

    }

    @Override
    public ViewGroup.LayoutParams getChildLayoutParams(WXComponent child, View childView, int width, int height, int left, int right, int top, int bottom) {
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return lp;
    }

    @Override
    public void addSubView(View child, int index) {
//         if(!(child.getTag() instanceof WXChild))
//         {
//             return;
//         }
        if(childCount==1)
        {
            drawerLayoutView_.setSlidView((ViewGroup) child);
        }
        else if(childCount==2)
        {
            this.main=(ViewGroup) child;
            drawerLayoutView_.setMainView((ViewGroup) child);
        }

    }



    @Override
    protected DrawerLayoutView initComponentHostView(@NonNull Context context) {
        drawerLayoutView_ = (DrawerLayoutView_) DrawerLayoutView_.build(context);
//        drawerLayoutView_.setParentInstance(getInstance());
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
//        drawerLayoutView_.setSlidUrl(url);
    }

    @WXComponentProp(name = "src")
    public void setMainUrl(String url)
    {
        url= Weex.getRelativeUrl(url,this.getInstance());
//        drawerLayoutView_.setMainUrl(url);
    }

    @WXComponentProp(name = "leftWidth")
    public void setLeftWidth(float leftwidth)
    {
        drawerLayoutView_.setLeftWidth((int)Weex.length(leftwidth));
    }


    @WXComponentProp(name = "isOpen")
    public void setOpen(boolean open)
    {
        if (open) {
            drawerLayoutView_.drawer_layout.openDrawer(drawerLayoutView_.nav_view);
        }else {
            drawerLayoutView_.drawer_layout.closeDrawer(drawerLayoutView_.nav_view);
        }
    }


    @JSMethod
    public  void getIsOpen(JSCallback callback)
    {
        callback.invoke(drawerLayoutView_.drawer_layout.isDrawerOpen(drawerLayoutView_.nav_view));
    }


    @Override
    public void onActivityResume() {
        super.onActivityResume();
//        drawerLayoutView_.setChildContext(getInstance().getContext());
//        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
//            drawerLayoutView_.main_view.instance.onActivityResume();
//        if(drawerLayoutView_.nav_view!=null&&drawerLayoutView_.nav_view.instance!=null)
//            drawerLayoutView_.nav_view.instance.onActivityResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
//            drawerLayoutView_.main_view.instance.onActivityResult(requestCode,resultCode,data);
//        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
//            drawerLayoutView_.nav_view.instance.onActivityResult(requestCode,resultCode,data);
    }



    @Override
    public void onActivityPause() {
        super.onActivityPause();
//        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
//            drawerLayoutView_.main_view.instance.onActivityPause();
//        if(drawerLayoutView_.nav_view!=null&&drawerLayoutView_.nav_view.instance!=null)
//            drawerLayoutView_.nav_view.instance.onActivityPause();
    }

    @Override
    public void onActivityStart() {
        super.onActivityStart();
//        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
//            drawerLayoutView_.main_view.instance.onActivityStart();
//        if(drawerLayoutView_.nav_view!=null&&drawerLayoutView_.nav_view.instance!=null)
//            drawerLayoutView_.nav_view.instance.onActivityStart();
    }

    @Override
    public boolean onActivityBack() {

//        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
//            drawerLayoutView_.main_view.instance.onActivityBack();
//        if(drawerLayoutView_.nav_view!=null&&drawerLayoutView_.nav_view.instance!=null)
//            drawerLayoutView_.nav_view.instance.onActivityBack();

        return super.onActivityBack();
    }

    @Override
    public void onActivityStop() {
        super.onActivityStop();
//        if(drawerLayoutView_.main_view!=null&&drawerLayoutView_.main_view.instance!=null)
//            drawerLayoutView_.main_view.instance.onActivityStop();
//        if(drawerLayoutView_.nav_view!=null&&drawerLayoutView_.nav_view.instance!=null)
//            drawerLayoutView_.nav_view.instance.onActivityStop();

    }



    @Override
    protected void onFinishLayout() {
        super.onFinishLayout();
        Weex.fireChildEvent(this,"load",mInstance.param);
    }
}
