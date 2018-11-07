package com.farwolf.wechat.module;

import android.widget.Toast;

import com.farwolf.json.JsonTool;
import com.farwolf.wechat.WechatEntryActivity;
import com.farwolf.weex.annotation.WeexModule;
import com.farwolf.weex.app.WeexApplication;
import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import java.util.Map;

import static com.farwolf.wechat.WechatEntryActivity.wxApi;

/**
 * Created by zhengjiangrong on 2017/8/9.
 */
@WeexModule(name="wechat")
public class WechatModule extends WXModuleBase {


    @JSMethod
    public void regist(String appId)
    {

        wxApi = WXAPIFactory.createWXAPI(WeexApplication.getInstance(), appId, true);
        wxApi.registerApp(appId);
    }


    @JSMethod(uiThread = false)
    public boolean isInstalled()
    {
        if(wxApi==null)
        {
            Toast.makeText(getContext(),"请先调用regist方法注册appId!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return wxApi.isWXAppInstalled();

    }

    @JSMethod
    public void pay(Map param, JSCallback callback)
    {


        if(wxApi==null)
        {
            Toast.makeText(getContext(),"请先调用regist方法注册appId!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!wxApi.isWXAppInstalled())
        {
            Toast.makeText(getContext(),"未安装微信!",Toast.LENGTH_SHORT).show();
            return;
        }
        WechatEntryActivity.callback=callback;
        String appId=param.get("appId")+"";
        PayReq request = JsonTool.toBean(new JSONObject(param),PayReq.class);
        wxApi.sendReq(request);


    }


    @JSMethod
    public void login(final Map param, JSCallback callback)
    {
        if(wxApi==null)
        {
            Toast.makeText(getContext(),"请先调用regist方法注册appId!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!wxApi.isWXAppInstalled())
        {
            Toast.makeText(getContext(),"未安装微信!",Toast.LENGTH_SHORT).show();
            return;
        }
        WechatEntryActivity.callback=callback;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = param.get("scope")+"";
                req.state = param.get("state")+"";
                wxApi.sendReq(req);
            }
        });



    }
}
