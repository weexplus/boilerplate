package com.farwolf.weex.component;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by zhengjiangrong on 2017/6/15.
 */

public class WXFImage extends WXImage {


    public WXFImage(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }

    @WXComponentProp(name = Constants.Name.SRC)
    public void setSrc(String src) {


//        if(src.startsWith("root:"))
//        super.setSrc(Weex.getRootUrl(src,this.getInstance()));
//        else
        super.setSrc(src);

    }


}
