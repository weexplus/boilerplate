package com.farwolf.weex.module;


import com.farwolf.util.encrypt.rsa.Base64Utils;
import com.farwolf.util.encrypt.rsa.RsaUtils;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

/**
 * Created by dotcool on 2017/6/18.
 */

public class WXRsaModule extends WXModule {


    @JSMethod(uiThread = false)
    public String encrypt(Map param)
    {
        String publicStr=param.get("pubKey")+"";
        String data=param.get("data")+"";
        String out = "";
        if(this.mWXSDKInstance==null||this.mWXSDKInstance.getContext()==null) return out;
         try {
                PublicKey publicKey = RsaUtils.loadPublicKey(publicStr);
                byte[] bytes = RsaUtils.encryptData(data.getBytes(), publicKey);
                out = Base64Utils.encode(bytes);
          } catch (Exception e) {
               e.printStackTrace();
        }
        return out;

    }
    @JSMethod(uiThread = false)
    public String decrypt(Map param)
    {
        String privateStr=param.get("priKey")+"";
        String data=param.get("data")+"";
        String out = "";
        if(this.mWXSDKInstance==null||this.mWXSDKInstance.getContext()==null) return out;
         try {
                PrivateKey privateKey = RsaUtils.loadPrivateKey(privateStr);
                byte[] bytes = RsaUtils.decryptData(Base64Utils.decode(data), privateKey);
                if (null != bytes) out=new String(bytes);
         } catch (Exception e) {
                     e.printStackTrace();
         }
        return out;

    }
    
}
