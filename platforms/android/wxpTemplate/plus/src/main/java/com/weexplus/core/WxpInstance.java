package com.weexplus.core;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WxpInstance  extends WXSDKInstance {



    public JSONObject param;
    public Module module;
    private List<WxpInstance> childInstances=new ArrayList<WxpInstance>();
    public boolean firePageInit =false;
    public boolean hasInit =false;
    public WxpInstance(Context context) {
        super(context);
    }

    public void addChildInstance(WxpInstance instance)
    {
        childInstances.add(instance);
    }
    public void firePageInit()
    {
        String url= getBundleUrl();
        if(firePageInit||!hasInit)
            return;
        firePageInit =true;
        if(param==null){
            param=new JSONObject();
        }
        fireGlobalEventCallback("onPageInit",param);
        for(WxpInstance instance:childInstances)
        {
            instance.setContext(getContext());
            instance.firePageInit();
        }
    }

    public boolean isFirePageInit() {
        return firePageInit;
    }



}
