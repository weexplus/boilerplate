package com.farwolf.weex.component;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.LinearLayout;

import com.farwolf.view.BounceScrollView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by zhengjiangrong on 2017/6/23.
 */

public class WXFScrollView extends WXComponent<BounceScrollView> {


    LinearLayout layout;

    public WXFScrollView(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }


    @Override
    protected BounceScrollView initComponentHostView(@NonNull Context context) {
        BounceScrollView b=new BounceScrollView(context);
        layout=new LinearLayout(context);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(lp);
        LinearLayout  l1=new LinearLayout(context);
        LinearLayout.LayoutParams lp1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300);
        l1.setBackgroundColor(Color.BLUE);
        l1.setLayoutParams(lp1);

        layout.addView(l1);
        b.addView(layout);
        return b;
    }


}
