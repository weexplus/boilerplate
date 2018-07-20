package com.farwolf.wechat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.farwolf.view.imgae.crop.Log;
import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.core.WeexFactory_;
import com.taobao.weex.bridge.JSCallback;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/8/10.
 */

public abstract class WechatEntryActivity extends Activity implements IWXAPIEventHandler {


    public static JSCallback callback;
    public static  IWXAPI wxApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(wxApi!=null)
            wxApi.handleIntent(getIntent(),this);
        else
        {
            Toast.makeText(this,"请先调用regist方法注册appId!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    public void onResp(BaseResp resp){

        Log.e("接受到微信返回:"+resp.errCode);
        WeexFactory factory= WeexFactory_.getInstance_(this);
        final HashMap m=new HashMap();
        m.put("type",getType(resp.getType()));
        m.put("errCode",resp.errCode);
        m.put("errMsg",resp.errStr);
        m.put("transaction",resp.transaction);
        m.put("openId",resp.openId);
        if("login".equals(getType(resp.getType())))
        {
            String authCode = ((SendAuth.Resp) resp).code;
            m.put("authCode",authCode);
        }

        callback.invoke(m);
        finish();

    }

    public String getType(int type)
    {
        if(type== ConstantsAPI.COMMAND_PAY_BY_WX)
        {
            return "pay";
        }
        else if(type== ConstantsAPI.COMMAND_SENDAUTH)
        {
            return "login";
        }
        return "share";
    }



}
