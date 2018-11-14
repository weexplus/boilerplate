package com.farwolf.netIm.module;

import com.farwolf.weex.annotation.WeexModule;
import com.farwolf.weex.base.WXModuleBase;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import java.util.HashMap;

@WeexModule(name = "netIm")
public class WXNetImModule extends WXModuleBase {


    @JSMethod
    public void login(HashMap param, JSCallback callback){
        String account=param.get("account")+"";
        String token=param.get("token")+"";
       final HashMap res=new HashMap();
        NimUIKit.login(new LoginInfo(account, token), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo param) {
                res.put("err",0);
            }

            @Override
            public void onFailed(int code) {
                res.put("err",code);
            }

            @Override
            public void onException(Throwable exception) {
                res.put("err",-2);
            }
        });
    }



    @JSMethod
    public void openP2P(HashMap param){

        String account=param.get("account")+"";
        NimUIKit.startP2PSession(getContext(), account);

    }

    @JSMethod
    public void openTeam(HashMap param){

        String teamId=param.get("teamId")+"";
        NimUIKit.startTeamSession(getContext(), teamId);

    }


}
