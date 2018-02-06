package com.farwolf.weex.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.farwolf.base.ViewBase;
import com.farwolf.weex.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjiangrong on 2017/8/17.
 */
@EViewGroup
public class WXTabView extends ViewBase {

    public List<String> urls=new ArrayList<>();

    @ViewById
    LinearLayout root;
    List<WXPageView>pages=new ArrayList<>();


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

    public void setItems(List<String> l)
    {
        if(this.urls.size()>0)
            return;
          this.urls=l;
        root.removeAllViews();
        pages.clear();
        for(String q:urls)
        {
            WXPageView p=WXPageView_.build(getContext());
//            p.setBackgroundColor(Color.GREEN);
            p.setVisibility(View.VISIBLE);
            p.setSrc(q);
            ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            p.setLayoutParams(lp);
            pages.add(p);
            this.root.addView(p);
        }

        setIndex(0);
    }


    public void setIndex(int index)
    {
        for(WXPageView p:pages)
        {
            if(pages.indexOf(p)==index)
            {
                p.setVisibility(View.VISIBLE);
                p.fireResume();
            }
            else
            {
                p.setVisibility(View.GONE);
            }
        }
    }
}
