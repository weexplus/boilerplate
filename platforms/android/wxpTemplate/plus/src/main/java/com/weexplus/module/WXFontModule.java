package com.weexplus.module;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.utils.FontDO;
import com.taobao.weex.utils.TypefaceUtil;
import com.weexplus.core.render.Local;
import com.weexplus.util.Path;
import com.weexplus.util.SDCard;
import com.weexplus.util.WXModuleBase;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2018/1/15.
 */

public class WXFontModule extends WXModuleBase {


    @JSMethod
    public void addFont(String name, String url)
    {



        HashMap param=new HashMap();
        url= Path.getUrl(url,getWxpInstance().getBundleUrl(),getWxpInstance().module.name);
        param.put("src","url('"+url+"')");
        param.put(Constants.Name.FONT_FAMILY,name);
        JSONObject j=new JSONObject(param);




        FontDO fontDO = parseFontDO(j, this.mWXSDKInstance);

        if(url.startsWith("http"))
        {
            fontDO.mType= FontDO.TYPE_NETWORK;
        }
        else
        {
            if(Local.isDiskExist(mWXSDKInstance.getContext())){

                fontDO.mType= FontDO.TYPE_FILE;
//                fontDO.mUrl= SDCard.getBasePath(mWXSDKInstance.getContext())+"/"+url;
                fontDO.mUrl= url;
            }
            else
            {
                fontDO.mType= FontDO.TYPE_LOCAL;
                fontDO.mUrl="/"+url;
            }

        }
        if (fontDO != null && !TextUtils.isEmpty(fontDO.getFontFamilyName())) {
            FontDO cacheFontDO = TypefaceUtil.getFontDO(fontDO.getFontFamilyName());
            if (cacheFontDO == null || !TextUtils.equals(cacheFontDO.getUrl(), fontDO.getUrl())) {
                TypefaceUtil.putFontDO(fontDO);
                TypefaceUtil.loadTypeface(fontDO,true);
            } else {
                TypefaceUtil.loadTypeface(cacheFontDO,true);
            }
        }

    }

    private FontDO parseFontDO(JSONObject jsonObject, WXSDKInstance instance) {
        if(jsonObject == null) {
            return null;
        }
        String src = jsonObject.getString(Constants.Name.SRC);
        String name = jsonObject.getString(Constants.Name.FONT_FAMILY);

        return new FontDO(name, src,instance);
    }

}
