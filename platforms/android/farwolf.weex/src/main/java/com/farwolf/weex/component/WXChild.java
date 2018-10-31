package com.farwolf.weex.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXDiv;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.WXFrameLayout;

/**
 * Created by zhengjiangrong on 2018/8/12.
 */

public class WXChild extends WXDiv {

    private String role;

    public WXChild(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }
//    public WXChild(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
//        super(instance, node, parent);
//    }

    @WXComponentProp(name = "role")
    public void setRole(String role)
    {
       this.role=role;
//        if(getParent() instanceof WXDrawerLayout)
//        {
//            WXDrawerLayout drawerLayout= ((WXDrawerLayout) getParent());
////            if("slid".equals(role))
////            {
////                if(!drawerLayout.hasInitLeftChild())
////                {
////                    ((WXDrawerLayout) getParent()).addLeftChild(this);
////                }
////            }
////            else
////            {
////                if(!drawerLayout.hasInitMainChild())
////                {
////                    ((WXDrawerLayout) getParent()).addMainChild(this);
////                }
////            }
//
//
//
//        }
    }

    @Override
    public ViewGroup.LayoutParams getChildLayoutParams(WXComponent child, View childView, int width, int height, int left, int right, int top, int bottom) {
//        FrameLayout.MarginLayoutParams lp=new FrameLayout.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        return lp;

       float h=  child.getLayoutHeight();
        float w= child.getLayoutWidth();

        ViewGroup.LayoutParams lp= super.getChildLayoutParams(child,childView,width,height,left,right,top,bottom);
        lp.width=750;
        lp.height=1500;
        return  lp;
    }





    @Override
    protected void onHostViewInitialized(WXFrameLayout host) {
        super.onHostViewInitialized(host);

    }






    @Override
    protected WXFrameLayout initComponentHostView(@NonNull Context context) {

        WXFrameLayout f= super.initComponentHostView(context);
        f.setTag(this);
        return f;
    }

    @Override
    protected void onFinishLayout() {
        super.onFinishLayout();
    }

    //    @Override
//    protected void onFinishLayout() {
//        super.onFinishLayout();
//        for(int i=0;i<getChildCount();i++)
//        {
//            WXComponent child=getChild(i);
//            ViewGroup vg=(ViewGroup)child.getRealView();
//            for(int j=0;j<vg.getChildCount();j++)
//            {
//                vg.getChildAt(j).getLayoutParams().width=-1;
//                vg.getChildAt(j).getLayoutParams().height=-1;
//            }
//
//            View v= child.getRealView();
//            v.getLayoutParams().width=-1;
//            v.getLayoutParams().height=-1;
//
//            v.requestLayout();
//            v.invalidate();
//        }
//    }

    public String getRole()
    {
        return this.role;
    }

}
