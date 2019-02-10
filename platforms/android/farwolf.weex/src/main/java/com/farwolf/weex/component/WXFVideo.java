package com.farwolf.weex.component;

import com.farwolf.weex.util.Const;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.component.WXVideo;

public class WXFVideo extends WXVideo {
    public WXFVideo(WXSDKInstance instance, WXVContainer parent, boolean isLazy, BasicComponentData basicComponentData) {
        super(instance, parent, isLazy, basicComponentData);
    }

    @Override
    public void setSrc(String src) {
         src= Weex.getRelativeUrl(src,getInstance());
        src= src.replace(Const.PREFIX_SDCARD,"");
//        src="file://"+src;
//        File f=new File(src);
//         boolean t= f.exists();
//        WXVideoView wx=mWrapper.createIfNotExist();
//        wx.setVideoPath(src);
//        wx.start();
        super.setSrc(src);

//        if (!TextUtils.isEmpty(src)) {
//            WXSDKInstance instance = getInstance();
//            mWrapper.setVideoURI(instance.rewriteUri(Uri.parse(src), URIAdapter.VIDEO));
//            mWrapper.getProgressBar().setVisibility(View.VISIBLE);
//        }
    }
}
