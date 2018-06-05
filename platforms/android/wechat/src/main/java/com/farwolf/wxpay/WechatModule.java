package com.farwolf.wxpay;

import android.widget.Toast;

import com.farwolf.json.JsonTool;
import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import java.util.Map;

import static com.farwolf.wxpay.WechatEntryActivity.wxApi;

/**
 * Created by zhengjiangrong on 2017/8/9.
 */

public class WechatModule extends WXModuleBase {


    @JSMethod
    public void regist(String appId)
    {
        wxApi = WXAPIFactory.createWXAPI(getActivity(), appId, false);
        wxApi.registerApp(appId);
    }

    @JSMethod
    public void pay(Map param, JSCallback callback)
    {

        if(wxApi==null)
        {
            Toast.makeText(getContext(),"请先调用regist方法注册appId!",Toast.LENGTH_SHORT).show();
            return;
        }
        WechatEntryActivity.callback=callback;
        String appId=param.get("appId")+"";
        PayReq request = JsonTool.toBean(new JSONObject(param),PayReq.class);
        wxApi.sendReq(request);

    }


    @JSMethod
    public void login(Map param, JSCallback callback)
    {
        if(wxApi==null)
        {
            Toast.makeText(getContext(),"请先调用regist方法注册appId!",Toast.LENGTH_SHORT).show();
            return;
        }
        WechatEntryActivity.callback=callback;
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = param.get("scope")+"";
        req.state = param.get("state")+"";
        wxApi.sendReq(req);


    }
}
