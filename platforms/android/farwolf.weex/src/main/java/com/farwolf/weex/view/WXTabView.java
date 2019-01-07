package com.farwolf.weex.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.farwolf.weex.R;
import com.taobao.weex.ui.component.WXVContainer;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengjiangrong on 2017/8/17.
 */
@EViewGroup
public class WXTabView extends WeexView {

    public List<String> urls=new ArrayList<>();


    int defaultIndex=-1;
    WXVContainer parent;

    Map param;
    @ViewById
    LinearLayout root;
    List<View>pages=new ArrayList<>();


    public WXTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WXTabView(Context context) {
        super(context);
    }


    @Override
    public int getViewId() {
        return R.layout.tabcomponent;
    }

    @Override
    public void init() {

//         this.setBackgroundColor(Color.RED);

    }

    public int size(){
        return pages.size();
    }

    public void addChild(final View v)
    {
        if(this.pages.size()-1==this.defaultIndex){
            v.setVisibility(View.VISIBLE);
        }else{
            v.setVisibility(View.GONE);
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                root.addView(v);
                pages.add(v);
                if(defaultIndex!=-1){
                    if(defaultIndex<=pages.size()-1){
                        setIndex(defaultIndex,parent,param);
                        defaultIndex=-1;
                    }
                }
            }
        });


    }











    public void setIndex(final int index,final WXVContainer parent,final Map param)
    {
        if(index>pages.size()-1){
            this.defaultIndex=index;
            this.parent=parent;
            return;
        }
        for(View p:pages)
        {
            if(pages.indexOf(p)==index)
            {
                p.setVisibility(View.VISIBLE);
                final HashMap m=new HashMap();
                m.put("index",index);
                this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parent.fireEvent("change",m);
                        parent.getChild(index).fireEvent("show",param);
                    }
                });

//                p.fireResume();
            }
            else
            {
                p.setVisibility(View.GONE);
//                p.fireLeave();
            }
        }
    }












}
