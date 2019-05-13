package com.farwolf.weex.module;

import android.app.Activity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

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


    @JSMethod
    public void hide(JSCallback callback){
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        boolean t= inputMethodManager.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
        HashMap m=new HashMap();
        m.put("res",t);
        callback.invoke(m);
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
