package com.farwolf.weex.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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

import java.util.Map;

/**
 * Created by zhengjiangrong on 2017/8/19.
 */
@EViewGroup
public class WXPageView extends WeexView   {




    WXSDKInstance parentInstance;

    @ViewById
    public FrameLayout root;
    private String src;


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
        if(instance!=null)
        instance.fireGlobalEventCallback("onResume",null);
    }

    public void fireLeave()
    {

        if(instance!=null)
        instance.fireGlobalEventCallback("onLeave",null);
    }

    public WXSDKInstance getParentInstance() {
        return parentInstance;
    }

    public void setParentInstance(WXSDKInstance parentInstance) {
        this.parentInstance = parentInstance;
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        firePageInit();

    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        firePageInit();
    }

    public void firePageInit()
    {

        if(this.page!=null)
        {
            String url= instance.getBundleUrl();
            String parent=null;
            if(getParentInstance()!=null)
                parent= getParentInstance().getBundleUrl();

            if(getParentInstance()!=null)
            {
                boolean parentfired=getParentInstance().isFirePageInit();
                if(!parentfired)
                    return;
            }
            if(instance!=null)
                instance.firePageInit();
        }




    }


    public void setSrc(String src, Context c,final Map param) {

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
            instance.param=param;
            root.removeAllViews();
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            page.v.setLayoutParams(layoutParams);
            root.addView(page.v);
            instance.setSize(layoutParams.width,layoutParams.height);
            getParentInstance().addChildInstance(instance);
            firePageInit();
//            instance.fireGlobalEventCallback("onPageInit",param);
            if(renderListener!=null)
            {
                renderListener.onRenderSuccess();
            }
        }
        else
        {
            this.loadUrl(src,param);
        }
    }

    public String getSrc() {
        return src;
    }



    private WXSDKInstance createInstance() {
        WXSDKInstance   instance =new WXSDKInstance(getContext());
        getParentInstance().addChildInstance(instance);
        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        instance.setSize(layoutParams.width,layoutParams.height);
        instance.registerRenderListener(new IWXRenderListener(){

            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {


                root.removeAllViews();
                ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lp);
                root.addView(view);
//                instance.fireGlobalEventCallback("onPageInit",null);

                Page p=new Page();
                p.v=view;

                if(instance!=null)
                {
                    instance.hasInit=true;
                    instance.firePageInit();
                }

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
    public void loadUrl(String url,Map param)
    {


        this.instance=createInstance();
        this.instance.setBundleUrl(url);
        this.instance.param=param;

        if(url.startsWith("http"))
        {
            instance.renderByUrl(WXPerformance.DEFAULT, url, null, null, WXRenderStrategy.APPEND_ASYNC);
        }
        else
        {

//            instance.setSize(tool.getScreenWidth(),tool.getScreenHeight());
            instance.render(url, Weex.loadLocal(url, instance.getContext()), null, null, WXRenderStrategy.APPEND_ASYNC);

        }


    }


    public  static interface  RenerListerner
    {
        void onRenderSuccess();
    }

}