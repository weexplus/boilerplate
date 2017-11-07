package com.farwolf.alipay;

import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import java.util.Map;

/**
 * Created by zhengjiangrong on 2017/8/9.
 */

public class WXAlipayModule extends WXModuleBase {


    JSCallback callback;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
//            Result result = new Result((String) msg.obj);
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();

            if(callback!=null)
                callback.invokeAndKeepAlive(msg.obj);

        };
    };


    @JSMethod
    public void open(String signstr,String appScheme,JSCallback callback)
    {
        this.callback=callback;
        final String orderInfo = signstr;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map result = alipay.payV2(orderInfo,true);

                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


}
