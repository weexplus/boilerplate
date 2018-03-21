package com.farwolf.weex.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.farwolf.base.ViewBase;
import com.farwolf.util.ScreenTool;
import com.farwolf.weex.R;
import com.farwolf.weex.core.Page;
import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXPerformance;
import com.taobao.weex.common.WXRenderStrategy;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zhengjiangrong on 2017/8/19.
 */
@EViewGroup
public class WXPageView extends ViewBase   {


    @ViewById
    public FrameLayout root;
    private String src;
    public WXSDKInstance instance;

    private boolean mIsVisible = true;

    @Bean
    public WeexFactory weexFactory;

    Page page;


    RenerListerner renderListener;


    @Bean
    public ScreenTool tool;

    public WXPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WXPageView(Context context) {
        super(context);
    }


    @Override
    public int getViewId() {
        return R.layout.wxpageview;
    }

    @Override
    public void init() {



    }



    public void setRenderListener(RenerListerner renderListener) {
        this.renderListener = renderListener;
    }

    public void fireResume()
    {

        instance.fireGlobalEventCallback("onResume",null);
    }


    public void setSrc(String src,Context c) {

        this.src = src;
        if (instance != null) {
            instance.destroy();
            instance = null;
        }
        this.page=  weexFactory.getPage(src);
        if(page!=null)
        {
            instance=page.instance;
            if(c!=null)
                instance.setContext(c);
            root.removeAllViews();
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            page.v.setLayoutParams(layoutParams);
            root.addView(page.v);
            instance.setSize(layoutParams.width,layoutParams.height);
            instance.fireGlobalEventCallback("onPageInit",null);
            if(renderListener!=null)
            {
                renderListener.onRenderSuccess();
            }
        }
        else
        {
            this.loadUrl(src);
        }
    }

    public String getSrc() {
        return src;
    }



    private WXSDKInstance createInstance() {
        WXSDKInstance   instance =new WXSDKInstance(getContext());
        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        instance.setSize(layoutParams.width,layoutParams.height);
        instance.registerRenderListener(new IWXRenderListener(){

            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {


                root.removeAllViews();
                ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lp);
                root.addView(view);
                instance.fireGlobalEventCallback("onPageInit",null);
            }

            @Override
            public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
                if(renderListener!=null)
                {
                    renderListener.onRenderSuccess();
                }
            }

            @Override
            public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

            }

            @Override
            public void onException(WXSDKInstance instance, String errCode, String msg) {

            }
        });
        String url=src;


        //zjr add

        return instance;
    }

    //zjr add
    public void loadUrl(String url)
    {


        this.instance=createInstance();
        this.instance.setBundleUrl(url);

        if(url.startsWith("http"))
        {
            instance.renderByUrl(WXPerformance.DEFAULT, url, null, null, WXRenderStrategy.APPEND_ASYNC);
        }
        else
        {

//            instance.setSize(tool.getScreenWidth(),tool.getScreenHeight());
            instance.render(url, Weex.loadAsset(url, instance.getContext()), null, null, WXRenderStrategy.APPEND_ASYNC);

        }


    }


    public  static interface  RenerListerner
    {
        void onRenderSuccess();
    }

}