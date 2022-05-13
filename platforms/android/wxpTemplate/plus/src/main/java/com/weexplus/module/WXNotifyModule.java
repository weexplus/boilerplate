package com.weexplus.module;



import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.weexplus.activity.WxpActivity;
import com.weexplus.event.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/5/18.
 */

public class WXNotifyModule extends WXModule {




    public HashMap<String,ArrayList<JSCallback>>callbacks=new HashMap<String,ArrayList<JSCallback>>();


    @JSMethod
    public void regist(String key, JSCallback callback)
    {
        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        ArrayList<JSCallback> ary=null;
        if(callbacks.containsKey(key)){
            ary=callbacks.get(key);
        }else{
            ary=new ArrayList<JSCallback>();
        }
        ary.add(callback);
        callbacks.put(key,ary);
    }





    @JSMethod
    public void send(String key,HashMap param)
    {
        WxpActivity a=  (WxpActivity)this.mWXSDKInstance.getContext();
        EventBus.getDefault().post(new Event(key,a.module,param));
    }



    @JSMethod
    public void sendNative(String key,HashMap param)
    {
        WxpActivity a=  (WxpActivity)this.mWXSDKInstance.getContext();
        EventBus.getDefault().post(new Event(key,a.module,param));
    }


    @Override
    public void onActivityDestroy() {
        super.onActivityDestroy();
        EventBus.getDefault().unregister(this);
        callbacks.clear();
        callbacks=null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Event event) {
        WxpActivity a=  (WxpActivity)this.mWXSDKInstance.getContext();
        if(callbacks.containsKey(event.key)&&a.module.equals(event.module)){
            ArrayList<JSCallback> ary=callbacks.get(event.key);
            for(JSCallback callback:ary){
                if(callback!=null)
                    callback.invokeAndKeepAlive(event.param);
            }
        }




    }

    @JSMethod
    public void setNumber(int number){

    }


}
