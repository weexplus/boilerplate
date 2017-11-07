package com.farwolf.weex.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.farwolf.util.ScreenTool_;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by zhengjiangrong on 2017/6/22.
 */

public class WXItem extends WXComponent<LinearLayout> {


    public WXSDKInstance instance;


    public WXItem(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, int type) {
        super(instance, dom, parent, type);
    }

    public WXItem(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
    }

    String src="";


    @Override
    protected LinearLayout initComponentHostView(@NonNull Context context) {
        LinearLayout l=new LinearLayout(getContext());
        return l;
    }

    @WXComponentProp(name = Constants.Name.SRC)
    public void setSrc(String src) {

        ScreenTool_ tool=ScreenTool_.getInstance_(getContext());
        this.src=src;
        instance=new WXSDKInstance(getContext());
        instance.setBundleUrl(src);
        instance.setSize(tool.getScreenWidth(),tool.getScreenHeight());

        instance.registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {

                ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lp);

                getHostView().addView(view);
            }

            @Override
            public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

            }

            @Override
            public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

            }

            @Override
            public void onException(WXSDKInstance instance, String errCode, String msg) {

            }
        });
        instance.renderByUrl("farwolf", src ,null, null, WXRenderStrategy.APPEND_ASYNC);

    }
}
