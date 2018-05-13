package com.farwolf.weex.module;

import com.farwolf.util.AppMainfest;
import com.farwolf.util.AppMainfest_;
import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.bean.Config;
import com.taobao.weex.annotation.JSMethod;

/**
 * Created by zhengjiangrong on 2018/5/13.
 */

public class WXEnvModule extends WXModuleBase {




    @JSMethod(uiThread = false)
    public String versionCode()
    {
         AppMainfest appMainfest= AppMainfest_.getInstance_(getContext());
         return appMainfest.getVersionCode()+"";
    }


    @JSMethod(uiThread = false)
    public String versionName()
    {
        AppMainfest appMainfest= AppMainfest_.getInstance_(getContext());
        return appMainfest.getVersionName()+"";
    }



    @JSMethod(uiThread = false)
    public String jsVersionCode()
    {
        return  Config.jsVersion(getContext())+"";
    }
}
