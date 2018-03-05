package com.farwolf.weex.component;


import android.view.ViewGroup;

import com.farwolf.weex.util.Weex;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.WXPerformance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXEmbed;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXFileUtils;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/6/26.
 */

@Component(lazyload = false)
public class WXFEmbed extends WXEmbed {


    HashMap param;


    public WXFEmbed(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);


    }

    public void loadUrl(String url,WXSDKInstance instance,ViewGroup.LayoutParams layoutParams)
    {


        if(url.startsWith("root:"))
        {
            url= Weex.getRootUrl(url, this.getParent().getInstance());
        }
        if(url.startsWith("http"))
        {
            instance.renderByUrl(WXPerformance.DEFAULT, url, null, null, layoutParams.width,
                    layoutParams.height, WXRenderStrategy.APPEND_ASYNC);


        }
        else
        {

            instance.render(url, WXFileUtils.loadAsset(url,  this.getParent().getInstance().getContext()), null, null, WXRenderStrategy.APPEND_ASYNC);

        }
    }

    @WXComponentProp(name = "param")
    public void setParam(HashMap param) {
      this.param=param;
    }

    public void onRenderFinish()
    {
        this.getChildInstance().fireGlobalEventCallback("onPageInit",this.param);


    }

}
