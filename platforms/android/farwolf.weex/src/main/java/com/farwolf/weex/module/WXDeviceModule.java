package com.farwolf.weex.module;

import android.content.Intent;
import android.net.Uri;

import com.farwolf.util.DeviceTool_;
import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;

public class WXDeviceModule extends WXModuleBase {

    @JSMethod(uiThread = false)
    public String mac(){
        return DeviceTool_.getInstance_(getContext()).getMac();
    }

    @JSMethod(uiThread = false)
    public String deviceId(){
        return DeviceTool_.getInstance_(getContext()).getDeviceId();
    }


    @JSMethod
    public void tel(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        getActivity().startActivity(intent);
    }

    @JSMethod
    public void openUrl(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        getActivity().startActivity(intent);
    }


}
