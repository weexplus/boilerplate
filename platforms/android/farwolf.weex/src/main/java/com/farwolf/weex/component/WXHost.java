package com.farwolf.weex.component;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.farwolf.weex.view.WXTabView;
import com.farwolf.weex.view.WXTabView_;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengjiangrong on 2017/8/17.
 */

public class WXHost extends WXVContainer<WXTabView> {


    public WXHost(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
    }



    @Override
    protected WXTabView initComponentHostView(@NonNull Context context) {
        WXTabView w= WXTabView_.build(context);
        w.setInstance(getInstance());
//        WXTabView w= new WXTabView(context);
//        w.holdComponent(this);
        return w;
    }


    @Override
    public ViewGroup.LayoutParams getChildLayoutParams(WXComponent child, View childView, int width, int height, int left, int right, int top, int bottom) {
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return lp;
    }

    @Override
    public void addChild(WXComponent child) {
//        child.getDomObject().getStyles().put(Constants.Name.POSITION,"absolute");
//        child.getDomObject().getStyles().put(Constants.Name.TOP,"0");
//        child.getDomObject().getStyles().put(Constants.Name.LEFT,"0");
//        child.getDomObject().getStyles().put(Constants.Name.BOTTOM,"0");
//        child.getDomObject().getStyles().put(Constants.Name.RIGHT,"0");
        Map m= child.getDomObject().getStyles();
        super.addChild(child);

    }

    @Override
    public void addChild(WXComponent child, int index) {
        super.addChild(child, index);
        child.fireEvent("load",mInstance.param);
//        Weex.fireChildEvent(child,"load",mInstance.param);
    }

    @Override
    public void updateStyle(WXComponent component) {
        super.updateStyle(component);
}


    //    @Override
//    public void applyLayoutAndEvent(WXComponent component) {
//
//        long startNanos = System.nanoTime();
////        if(!isLazy()) {
//            if (component == null) {
//                component = this;
//            }
//            super.applyLayoutAndEvent(component);
//            int count = childCount();
//            for (int i = 0; i < count; i++) {
//                WXComponent child = getChild(i);
//                child.getDomObject().getStyles().put(Constants.Name.POSITION,"absolute");
//                child.getDomObject().getStyles().put(Constants.Name.TOP,"0");
//                child.getDomObject().getStyles().put(Constants.Name.LEFT,"0");
//                child.getDomObject().getStyles().put(Constants.Name.BOTTOM,"0");
//                child.getDomObject().getStyles().put(Constants.Name.RIGHT,"0");
//                child.applyLayoutAndEvent(((WXVContainer)component).getChild(i));
//            }
//
////        }
//        mTraceInfo.uiThreadNanos += (System.nanoTime() - startNanos);
//    }

    @Override
    public void addSubView(View child, int index) {
        this.getHostView().addChild(child);
    }

//    @WXComponentProp(name = "items")
//    public void setItems(ArrayList l)
//    {
//
//        ArrayList lx=new ArrayList();
//        for(Object q:l)
//        {
//            lx.add(Weex.getRelativeUrl(q+"", this.getInstance()));
//        }
//
//        this.getHostView().setItems(lx);
//    }

    @WXComponentProp(name = "index")
    public void setIndex(int index)
    {
        this.getHostView().setIndex(index);
        HashMap m=new HashMap();
        m.put("index",index);
        fireEvent("change",m);
        getChild(index).fireEvent("show",mInstance.param);
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
        fireChildEvent(WXHost.this);
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

    public void fireChildEvent(WXVContainer c){
        c.fireEventWait("load",mInstance.param);
        for(int i=0;i<getChildCount();i++){
            WXComponent cx= c.getChild(i);
            if(cx instanceof  WXVContainer) {
                fireChildEvent((WXVContainer) cx);
            }
            else {
                cx.fireEventWait("load",mInstance.param);
            }
        }
    }

    @Override
    public void onActivityDestroy() {
        super.onActivityDestroy();
    }

    @Override
    protected void onFinishLayout() {
        super.onFinishLayout();
//        for(int i=0;i<getChildCount();i++){
//            WXComponent c= getChild(i);
//            c.fireEvent("load",mInstance.param);
//        }
//        fireChildEvent(WXHost.this);

//        getHostView().postDelayed(WXThread.secure(new Runnable() {
//            @Override
//            public void run() {
//                fireChildEvent(WXHost.this);
//            }
//        }), 100);
    }
}
