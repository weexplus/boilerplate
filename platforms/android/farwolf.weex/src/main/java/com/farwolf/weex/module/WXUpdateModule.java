package com.farwolf.weex.module;

import com.farwolf.update.UpdateService;
import com.farwolf.update.UpdateService_;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2018/3/22.
 */

public class WXUpdateModule extends WXModule {



    @JSMethod
    public void docheck(HashMap param)
    {
        String appid=param.get("appid")+"";
        String vcurl=param.get("url")+"";
        boolean failtoast=(boolean)param.get("failtoast");
        boolean showprogress=(boolean)param.get("showprogress");

         UpdateService updateService= UpdateService_.getInstance_(mWXSDKInstance.getContext());
         updateService.init(appid,vcurl);
         updateService.doCheck(failtoast,showprogress);
    }


}
