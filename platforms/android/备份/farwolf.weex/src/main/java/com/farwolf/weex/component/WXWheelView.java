package com.farwolf.weex.component;

import android.content.Context;
import android.support.annotation.NonNull;

import com.farwolf.view.pickerview.ArrayWheelAdapter;
import com.farwolf.view.pickerview.OnItemSelectedListener;
import com.farwolf.view.pickerview.WheelView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhengjiangrong on 2017/7/17.
 */
@Component(lazyload = true)
public class WXWheelView  extends WXComponent<WheelView> {


    ArrayList<String>data;

    public WXWheelView(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }


    @Override
    protected WheelView initComponentHostView(@NonNull Context context) {
        WheelView w=new WheelView(context);
        data=new ArrayList<>();
        w.setAdapter(new ArrayWheelAdapter(data));
        w.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                HashMap m=new HashMap();
                m.put("value",data.get(index));
                m.put("index",index);
                fireEvent(Constants.Event.CHANGE,  m);
            }
        });
         w.setCyclic(false);
        return w;
    }


    @WXComponentProp(name = Constants.Name.FONT_SIZE)
    public void setFontSize(float size) {

        this.getHostView().setTextSize(size);
    }


    @WXComponentProp(name = Constants.Name.COLOR)
    public void setColor(String color) {

//        this.getHostView().setTextColor(color);
    }


    @WXComponentProp(name ="selectColor")
    public void setSelectColor(String color) {

//        this.getHostView().setSelectColor(color);
    }

    @WXComponentProp(name ="lineColor")
    public void setLineColor(String color) {

//        this.getHostView().setLineColor(color);
    }


    @WXComponentProp(name ="data")
    public void setdata(List d) {
        this.data.clear();
        this.data.addAll(d);


//        this.getHostView().setAdapter(new ArrayWheelAdapter(data));

    }

    @JSMethod
    public void select(int index)
    {
        this.getHostView().setCurrentItem(index);
    }

    @JSMethod(uiThread = false)
    public void load(List d)
    {
           this.data=(ArrayList<String>) d;
           this.data.clear();
           this.data.addAll(d);



    }






}
