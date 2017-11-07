package com.farwolf.weex.module;

import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.core.WeexFactory_;
import com.taobao.weex.annotation.JSMethod;

/**
 * Created by zhengjiangrong on 2017/6/15.
 */

public class WXPageModule extends WXModuleBase {



    @JSMethod
    public void resetFrame()
    {
        getActivity().resetFrame();

    }

    @JSMethod
    public void prerender(String url)
    {
        WeexFactory_ w=WeexFactory_.getInstance_(getActivity());
        w.preRender(url,url,null);

    }


    @JSMethod(uiThread = true)
    public void reload()
    {
       getActivity().reload();
    }



}
