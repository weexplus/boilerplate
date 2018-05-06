package com.farwolf.weex.base;

import android.content.Context;

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

    public Context getContext()
    {
        return mWXSDKInstance.getContext();

    }


    public void requirePermission(String per)
    {
        getActivity().requirePermission(per);
    }


}
