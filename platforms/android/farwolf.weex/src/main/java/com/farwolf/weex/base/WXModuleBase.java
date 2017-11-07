package com.farwolf.weex.base;

import com.farwolf.view.TitleBar;
import com.farwolf.weex.activity.WeexActivity;
import com.taobao.weex.common.WXModule;

/**
 * Created by zhengjiangrong on 2017/5/10.
 */

public class WXModuleBase extends WXModule {


    public WeexActivity getActivity()
    {
        WeexActivity a=(WeexActivity) mWXSDKInstance.getContext();
        return a;
    }


    public TitleBar getTitleBar()
    {
        if(getActivity()==null)
            return null;
        return getActivity().getTitleBar();
    }
}
