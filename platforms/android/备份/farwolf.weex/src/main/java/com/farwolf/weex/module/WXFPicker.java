package com.farwolf.weex.module;

import android.view.View;

import com.farwolf.view.pickerview.OnItemSelectedListener;
import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.view.Picker;
import com.farwolf.weex.view.Picker_;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/6/26.
 */

public class WXFPicker extends WXModuleBase {



    Picker picker;
    JSCallback change1;
    JSCallback change2;
    JSCallback change3;

    @JSMethod
    public void select(int c,int row)
    {

        picker.select(c,row);
    }

    /**
     *
     * @param change1
     * @param change2
     * @param change3
     */
    @JSMethod
    public void setChange(JSCallback change1,JSCallback change2,JSCallback change3)
    {
        init();
        this.change1=change1;
        this.change2=change2;
        this.change3=change3;

        this.picker.setOnChangeListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if(WXFPicker.this.change1!=null)
                {
                    HashMap m=new HashMap();
                    m.put("index",index);
                    WXFPicker.this.change1.invokeAndKeepAlive(m);
                }
            }
        },new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if(WXFPicker.this.change2!=null)
                {

                    HashMap m=new HashMap();
                    m.put("index",index);
                    WXFPicker.this.change2.invokeAndKeepAlive(m);
                }
            }
        },new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if(WXFPicker.this.change3!=null)
                {

                    HashMap m=new HashMap();
                    m.put("index",index);
                    WXFPicker.this.change3.invokeAndKeepAlive(m);
                }
            }
        });



    }


    /**
     *
     * @param callback
     */
    @JSMethod
    public void setDone(final JSCallback callback)
    {
        init();
        this.picker.setDoneListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap m=new HashMap();
                m.put("index1",picker.options1.getCurrentItem());
                m.put("index2",picker.options2.getCurrentItem());
                m.put("index3",picker.options3.getCurrentItem());
                callback.invoke(m);

            }
        });
    }


    /**
     * @param d
     */
    @JSMethod
    public void setItems1(ArrayList d)
    {
        init();
        picker.setItems1(d);
    }

    @JSMethod
    public void setItems2(ArrayList d)
    {
        init();
        picker.setItems2(d);
    }



    @JSMethod
    public void setItems3(ArrayList d)
    {
        init();
        picker.setItems3(d);
    }

    @JSMethod
    public void setCount(int count)
    {
        picker=null;
        init();
        picker.setCount(count);

    }


    @JSMethod
    public void setTheme(String bgcolor,String btncolor)
    {
        init();
        picker.setTheme(bgcolor,btncolor);
    }

    void init()
    {
       if(picker==null)
        {
            picker= Picker_.build(getActivity());
            ArrayList l=new ArrayList();
            l.add("");
            l.add("");
            picker.setItems1(l);
            picker.setItems2(l);
            picker.setItems3(l);
        }

    }


    @JSMethod
    public void show()
    {
        init();
        picker.show();
    }




}
