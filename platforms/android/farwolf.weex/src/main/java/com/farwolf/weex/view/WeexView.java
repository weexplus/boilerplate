package com.farwolf.weex.view;

import android.content.Context;
import android.util.AttributeSet;

import com.farwolf.base.ViewBase;
import com.taobao.weex.WXSDKInstance;

/**
 * Created by zhengjiangrong on 2018/4/26.
 */

public abstract class WeexView extends ViewBase {


    public WXSDKInstance instance;

    public WeexView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeexView(Context context) {
        super(context);
    }

    public WXSDKInstance getInstance() {
        return instance;
    }

    public void setInstance(WXSDKInstance instance) {
        this.instance = instance;
    }




}
