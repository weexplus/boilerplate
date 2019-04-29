package com.farwolf.weex.component;


import android.view.View;
import android.view.ViewGroup;

import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXEmbed;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/6/26.
 */

@Component(lazyload = false)
public class WXFEmbed extends WXEmbed {


    HashMap param;

    public WXFEmbed(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }


//    public WXFEmbed(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
//        super(instance, node, parent);
//
//
//    }

    public ViewGroup.LayoutParams getSize(){
        int width=-1;
        int height=-1;
        View v=this.getHostView();
        while(v.getLayoutParams().width==-1&&v.getParent()!=null){
            v=(View)v.getParent();
        }
        if(v!=null)
            return v.getLayoutParams();
        return null;
    }

    public void loadUrl(String url,WXSDKInstance instance,ViewGroup.LayoutParams layoutParams)
    {


//        instance.setContext( this.getParent().getInstance().getContext());
        this.getParent().getInstance().addChildInstance(instance);
        if(url.startsWith("root:"))
        {
            url= Weex.getRootUrl(url, this.getParent().getInstance());
        }
        if(url.startsWith("http"))
        {
            ViewGroup.LayoutParams lp=getSize();
            int width=layoutParams.width;
            int height=layoutParams.height;
            if(lp!=null){
                width=lp.width;
                height=lp.height;
            }
            instance.setSize(width,height);
            WeexFactory.downloadJs(url,instance);

//            instance.renderByUrl(WXPerformance.DEFAULT, url, null, null, width,
//                    height, WXRenderStrategy.APPEND_ASYNC);


        }
        else
        {

            instance.render(url, Weex.loadLocal(url,  this.getParent().getInstance().getContext()), null, null, WXRenderStrategy.APPEND_ASYNC);

        }
    }


//    @Override
//    public void onAppear() {
//        super.onAppear();
//        this.mInstance.setContext(getContext());
//    }

    @WXComponentProp(name = "param")
    public void setParam(HashMap param) {
        if(param==null||param.size()==0)
            return;
        this.param=param;
    }

    public void onRenderFinish()
    {
        this.getChildInstance().hasInit=true;
        this.getChildInstance().param=param;
        this.getChildInstance().firePageInit();

    }

}
