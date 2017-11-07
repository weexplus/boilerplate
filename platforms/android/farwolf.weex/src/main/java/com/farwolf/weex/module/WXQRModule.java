package com.farwolf.weex.module;

import android.app.Activity;
import android.content.Intent;

import com.farwolf.weex.activity.QrActivity_;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/7/16.
 */

public class WXQRModule extends WXModule {


    JSCallback callback;

    @JSMethod
    public void open(HashMap param, JSCallback callback){
        this.callback=callback;
        String color=param.get("color")+"";
        String bgcolor=param.get("bgcolor")+"";
        Intent in=new Intent(this.mWXSDKInstance.getContext(), QrActivity_.class);
        if(color!=null)
            in.putExtra("titleColor",color);
        if(bgcolor!=null)
            in.putExtra("bgColor",bgcolor);
        ((Activity)this.mWXSDKInstance.getContext()).startActivityForResult(in,2);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==2)
        {
            if(resultCode==1)
            {
                String url=data.getStringExtra("url");
                callback.invokeAndKeepAlive(url);
            }
        }

    }
}
