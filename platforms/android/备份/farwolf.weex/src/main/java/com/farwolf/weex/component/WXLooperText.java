package com.farwolf.weex.component;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;

import com.farwolf.view.LooperTextView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXViewUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.taobao.weex.WXSDKInstance.getViewPortWidth;

/**
 * Created by zhengjiangrong on 2017/8/16.
 */

public class WXLooperText extends WXComponent<LooperTextView> {


    LooperTextView text;

    public WXLooperText(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
    }

    public WXLooperText(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, int type) {
        super(instance, dom, parent, type);
    }



    @Override
    protected LooperTextView initComponentHostView(@NonNull Context context) {
         text=new LooperTextView(context);
//        text.setBackgroundColor(Color.BLUE);

        text.setOnChangeListener(new LooperTextView.OnChangeListener() {
            @Override
            public void onChange(int index) {

                HashMap m=new HashMap();
                m.put("index",index-1);
                fireEvent("change",m);
            }
        });
        return text;
    }



    @WXComponentProp(name="data")
    public void setData(List l) {
        if(l!=null)
        text.setTipList(l);

    }


    @WXComponentProp(name="color")
    public void setTextColor(String color) {
        text.tv_tip_in.setTextColor(ColorStateList.valueOf(Color.parseColor(color)));
        text.tv_tip_out.setTextColor(ColorStateList.valueOf(Color.parseColor(color)));
    }


    @WXComponentProp(name="interval")
    public void setInterval(int interval) {
        text.interval=interval;
        text.initAnimation();
    }

    @WXComponentProp(name="fontSize")
    public void setFontSize(int fontsize) {

        int size=   (int) WXViewUtils.getRealPxByWidth(fontsize,getViewPortWidth());
        text.tv_tip_in.getPaint().setTextSize(size);
        text.tv_tip_out.getPaint().setTextSize(size);

    }

    @Override
    public void updateProperties(Map<String, Object> props) {
        super.updateProperties(props);

    }

    @JSMethod(uiThread = true)
    public void getIndex(JSCallback callback)
    {
        HashMap m=new HashMap();
        int index=text.index-1;
        if(index<0)
            index=0;
        m.put("index",index);
        callback.invoke(m);
    }

    @WXComponentProp(name="textAlign")
    public void setTextAlign(String align) {
        if("center".equals(align))
        {
            text.tv_tip_in.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
            text.tv_tip_out.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        }
        else if("right".equals(align))
        {
            text.tv_tip_in.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
            text.tv_tip_out.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
        }
        else
        {
            text.tv_tip_in.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
            text.tv_tip_out.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        }



    }
}
