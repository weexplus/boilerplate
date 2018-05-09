package com.farwolf.weex.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;

import com.farwolf.base.ViewBase;
import com.farwolf.util.ScreenTool;
import com.farwolf.weex.R;
import com.farwolf.weex.activity.WeexActivity;
import com.taobao.weex.WXSDKInstance;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.Map;

/**
 * Created by even on 2017/10/18.
 */
@EViewGroup
public class DrawerLayoutView extends ViewBase {
    @Bean
    public ScreenTool tool;

    @ViewById
    public WXPageView_ main_view;
    @ViewById
    public WXPageView nav_view;
    @ViewById
    public DrawerLayout drawer_layout;

    public DrawerLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawerLayoutView(Context context) {
        super(context);
    }


    @Override
    public int getViewId() {
        return R.layout.drawerlayoutview;
    }

    @Override
    public void init() {
        Log.e("init state", "init");
    }

    public void setSlidUrl(String url)
    {
         WeexActivity a= (WeexActivity)getActivity();
        Map param=a.getIntent().getParcelableExtra("param");
        nav_view.setSrc(url,getContext(),param);
        Log.e("nav_view url", url);
    }

    public void setMainUrl(String url)
    {
        WeexActivity a= (WeexActivity)getActivity();
        Map param=a.getIntent().getParcelableExtra("param");
        main_view.setSrc(url,getContext(),param);
        Log.e("main_view url", url);
    }


    public void setLeftWidth(int width)
    {
        DrawerLayout.LayoutParams o= ( DrawerLayout.LayoutParams) nav_view.getLayoutParams();
        o.width=width;
        if(nav_view==null)
            return;
        nav_view.setLayoutParams(null);
        nav_view.setLayoutParams(o);



    }

    public void setChildContext(Context c)
    {
        this.main_view.instance.setContext(c);
        this.nav_view.instance.setContext(c);

    }

    public void setParentInstance(WXSDKInstance instance)
    {

        this.main_view.setParentInstance(instance);
        this.nav_view.setParentInstance(instance);


    }



    @Click
    public void main_viewClicked() {

    }
}
