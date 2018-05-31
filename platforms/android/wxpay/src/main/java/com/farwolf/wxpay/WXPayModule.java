package com.farwolf.wxpay;

import com.farwolf.json.JsonTool;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by zhengjiangrong on 2017/8/9.
 */

public class WXPayModule  {



    @JSMethod
    public void open(Map param, JSCallback callback)
    {

        IWXAPI api=null;
        PayReq request = JsonTool.toBean(new JSONObject(param),PayReq.class);

//        request.appId = "wxd930ea5d5a258f4f";
//        request.partnerId = "1900000109";
//        request.prepayId= "1101000000140415649af9fc314aa427";
//        request.packageValue = "Sign=WXPay";
//        request.nonceStr= "1101000000140429eb40476f8896f4c9";
//        request.timeStamp= "1398746574";
//        request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
        api.sendReq(request);
    }
}
