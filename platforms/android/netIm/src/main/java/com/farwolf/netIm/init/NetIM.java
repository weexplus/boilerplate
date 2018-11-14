package com.farwolf.netIm.init;

import android.content.Context;

import com.farwolf.weex.annotation.ModuleEntry;
import com.netease.nim.uikit.api.NimUIKit;

@ModuleEntry
public class NetIM {


    public void init(Context context){
        NimUIKit.init(context);
//        NimUIKit.setLocationProvider(new NimDemoLocationProvider());

        // 会话窗口的定制: 示例代码可详见demo源码中的SessionHelper类。
        // 1.注册自定义消息附件解析器（可选）
        // 2.注册各种扩展消息类型的显示ViewHolder（可选）
        // 3.设置会话中点击事件响应处理（一般需要）
//        SessionHelper.init();

        // 通讯录列表定制：示例代码可详见demo源码中的ContactHelper类。
        // 1.定制通讯录列表中点击事响应处理（一般需要，UIKit 提供默认实现为点击进入聊天界面)
//        ContactHelper.init();

        // 在线状态定制初始化。
//        NimUIKit.setOnlineStateContentProvider(new DemoOnlineStateContentProvider());
    }
}
