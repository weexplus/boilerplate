package com.farwolf.alipay;

import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

/**
 * Created by zhengjiangrong on 2018/5/30.
 */

public class AlipayUtil {


    public static void init()
    {
        try {
            WXSDKEngine.registerModule("alipay",WXAlipayModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }
}
