package com.farwolf.weex.core;

import android.view.View;

import com.farwolf.weex.util.Weex;
import com.taobao.weex.WXSDKInstance;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/5/9.
 */

public class Page {


    public String id;
    public View v;
    public WXSDKInstance instance;
    public String url;
    public boolean trigger=false;
    HashMap param;

    public boolean hasLoad()
    {

       return Weex.hasLoad(this.v);

    }


}
