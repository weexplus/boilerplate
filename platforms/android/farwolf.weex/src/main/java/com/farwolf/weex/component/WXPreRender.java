package com.farwolf.weex.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.core.WeexFactory_;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXDiv;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.WXFrameLayout;

/**
 * Created by zhengjiangrong on 2017/8/19.
 */

public class WXPreRender extends WXDiv {

    WeexFactory w;

    public WXPreRender(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }
//    public WXPreRender(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
//        super(instance, dom, parent);
//    }

    @Override
    protected WXFrameLayout initComponentHostView(@NonNull Context context) {
        WXFrameLayout f= super.initComponentHostView(context);
        f.setLayoutParams(new ViewGroup.LayoutParams(0,0));
        return f;
    }

    @WXComponentProp(name = Constants.Name.SRC)
    public void setSrc(String src) {
       if(w==null)
           w= WeexFactory_.getInstance_(getContext());
       String url= Weex.getRelativeUrl(src,mInstance);
        w.preRender(url, url, null);
    }



}
