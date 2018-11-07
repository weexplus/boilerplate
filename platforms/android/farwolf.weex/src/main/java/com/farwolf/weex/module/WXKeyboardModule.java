package com.farwolf.weex.module;

import android.view.WindowManager;

import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.event.KeyboardEvent;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

public class WXKeyboardModule extends WXModuleBase {



    JSCallback show;
    JSCallback hide;

    @JSMethod
    public void enableAutoContorl(boolean enable)
    {

    }

    @JSMethod
    public void setKeyboardContorl(JSCallback show,JSCallback hide){
//        if(!EventBus.getDefault().isRegistered(this)){
//            EventBus.getDefault().register(this);
//        }
//        this.show=show;
//        this.hide=hide;

    }

    @JSMethod
    public void setKeyboardMode(String mode)
    {
        if("adjust_resize".equals(mode))
        {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        else if("adjust_pan".equals(mode))
        {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
        else if("adjust_nothing".equals(mode))
        {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }
    }



    @Subscribe
    public void onKeyboardChange(KeyboardEvent event){
        HashMap m=new HashMap();
        m.put("height",event.height);
        if(event.height>0){
          this.show.invokeAndKeepAlive(event);
        }else{
          this.hide.invokeAndKeepAlive(event);
        }
    }


}
