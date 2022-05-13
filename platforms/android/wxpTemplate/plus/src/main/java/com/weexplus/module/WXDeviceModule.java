package com.weexplus.module;

import android.content.Intent;
import android.net.Uri;


import com.taobao.weex.annotation.JSMethod;
import com.weexplus.util.DeviceTool;
import com.weexplus.util.WXModuleBase;

public class WXDeviceModule extends WXModuleBase {

    @JSMethod(uiThread = false)
    public String mac(){
        return new DeviceTool(getContext()).getMac();
    }

    @JSMethod(uiThread = false)
    public String deviceId(){
        return  new DeviceTool(getContext()).getDeviceId();
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
