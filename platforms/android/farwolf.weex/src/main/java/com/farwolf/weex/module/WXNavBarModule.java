package com.farwolf.weex.module;

import com.farwolf.util.StatusBar;
import com.farwolf.weex.activity.WeexActivity;
import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;

/**
 * Created by zhengjiangrong on 2017/5/10.
 */

public class WXNavBarModule extends WXModuleBase {




    @JSMethod
    public   void setStatusBarStyle(String style){

        WeexActivity.statuBarColor=style;
        StatusBar.setStatusBarStyle(style,getActivity());

    }



}
