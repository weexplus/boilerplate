package com.farwolf.weex.component;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.farwolf.weex.util.Weex;
import com.farwolf.weex.view.WXTabView;
import com.farwolf.weex.view.WXTabView_;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by zhengjiangrong on 2017/8/17.
 */

public class WXHost extends  WXVContainer<WXTabView> {
    public WXHost(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }


    @Override
    protected WXTabView initComponentHostView(@NonNull Context context) {
        WXTabView w= WXTabView_.build(context);
        w.setInstance(getInstance());
        return w;
    }


    @Override
    public ViewGroup.LayoutParams getChildLayoutParams(WXComponent child, View childView, int width, int height, int left, int right, int top, int bottom) {
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return lp;
    }

    @Override
    public void addChild(WXComponent child) {
        super.addChild(child);
    }

    @Override
    public void addChild(WXComponent child, int index) {
        super.addChild(child, index);
        child.fireEvent("load",mInstance.param);
    }

    @Override
    public void addSubView(View child, int index) {
        this.getHostView().addChild(child);
    }


    @WXComponentProp(name = "page")
    public void setPage(int index)
    {
        this.getHostView().setIndex(index,this,mInstance.param);
    }


    @Override
    public void onActivityResume() {
        super.onActivityResume();
        Context c=  mInstance.getContext();
//        getHostView().setChildContext(c);
//        getHostView().onEventInvoke(OnActivityResume,-1,-1,null);
    }

    @Override
    public void onActivityCreate() {
        super.onActivityCreate();
//        getHostView().onEventInvoke(OnActivityCreate,-1,-1,null);
    }


    @Override
    public void onActivityStart() {
        super.onActivityStart();
//        getHostView().onEventInvoke(OnActivityStart,-1,-1,null);
    }

    @Override
    public boolean onActivityBack() {
//        getHostView().onEventInvoke(OnActivityBack,-1,-1,null);
        return super.onActivityBack();
    }


    @Override
    public void onActivityPause() {
        super.onActivityPause();
//        getHostView().onEventInvoke(OnActivityPause,-1,-1,null);
    }

//    @Override
//    public void onActivityDestroy() {
//        super.onActivityDestroy();
//        getHostView().onEventInvoke(OnActivityDestroy,-1,-1,null);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        getHostView().onEventInvoke(OnActivityResult,requestCode,resultCode,data);
    }

    @Override
    public void onActivityStop() {
        super.onActivityStop();
//        getHostView().onEventInvoke(OnActivityStop,-1,-1,null);
    }


    @Override
    protected void onFinishLayout() {
        super.onFinishLayout();
        Weex.fireChildEvent(this,"load",mInstance.param);
    }
}
