package com.farwolf.weex.component;

import android.Manifest;
import android.app.Activity;

import com.farwolf.perssion.Perssion;
import com.farwolf.perssion.PerssionCallback;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by zhengjiangrong on 2017/6/15.
 */

public class WXFImage extends WXImage {
    public WXFImage(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }


    @JSMethod(uiThread = false)
    public void save(final JSCallback saveStatuCallback) {
        Perssion.check((Activity) getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE , new PerssionCallback() {
            @Override
            public void onGranted() {

                WXFImage.super.save(saveStatuCallback);
            }
        });
    }


}
