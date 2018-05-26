package com.farwolf.jpush;

import android.content.Context;

import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zhengjiangrong on 2018/4/21.
 */

public class JPushUtil {




    public static  void init(Context context)
    {

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(context);     		// 初始化 JPush

        try {
            WXSDKEngine.registerModule("jpush",JpushModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }
}
