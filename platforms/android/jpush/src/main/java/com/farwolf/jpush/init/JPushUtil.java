package com.farwolf.jpush.init;

import android.content.Context;

import com.farwolf.weex.annotation.ModuleEntry;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zhengjiangrong on 2018/4/21.
 */
@ModuleEntry
public class JPushUtil {




    public  void init(Context context)
    {

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(context);     		// 初始化 JPush

    }

}
