package com.farwolf.weex.module;

import android.app.Activity;
import android.content.Intent;

import com.farwolf.qrcode.zxing.android.CaptureActivity;
import com.farwolf.weex.event.PermissionEvent;
import com.farwolf.weex.util.CameraPermission;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/7/16.
 */

public class WXQRModule extends WXModule {


    JSCallback callback;
    HashMap param;

    @JSMethod
    public void open(HashMap param, JSCallback callback){

        this.param=param;
        this.callback=callback;
        if(!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }
        if(!CameraPermission.check(mWXSDKInstance.getContext()))
        {
            CameraPermission.requestCameraPermission((Activity) mWXSDKInstance.getContext());
            return;
        }
        dojob(param,callback);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PermissionEvent event) {

        if(event.type==PermissionEvent.CAMREA)
        {
           dojob(param,callback);
        }

    }

    void dojob(HashMap param, JSCallback callback)
    {

        String color=param.get("color")+"";
        String bgcolor=param.get("bgcolor")+"";
        Intent in=new Intent(this.mWXSDKInstance.getContext(), CaptureActivity.class);
        if(param.containsKey("color"))
            in.putExtra("titleColor",color);
        if(param.containsKey("bgcolor"))
            in.putExtra("bgColor",bgcolor);
        ((Activity)this.mWXSDKInstance.getContext()).startActivityForResult(in,2);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==2)
        {
            if(resultCode==1)
            {
                String url = data.getStringExtra("url");
                callback.invokeAndKeepAlive(url);
            }
        }

    }
}
