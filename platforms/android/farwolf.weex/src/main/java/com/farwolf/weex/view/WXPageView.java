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
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXPerformance;
import com.taobao.weex.common.WXRenderStrategy;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import static com.taobao.weex.utils.WXFileUtils.loadAsset;

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


    public void fireResume()
    {

        instance.fireGlobalEventCallback("onResume",null);
    }


    public void setSrc(String src) {

        this.src = src;
        if (instance != null) {
            instance.destroy();
            instance = null;
        }
        this.page=  weexFactory.getPage(src);
        if(page!=null)
        {
            instance=page.instance;
            root.removeAllViews();
            ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
            page.v.setLayoutParams(layoutParams);
            root.addView(page.v);
            instance.setSize(layoutParams.width,layoutParams.height);
            instance.fireGlobalEventCallback("onPageInit",null);
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
            instance.render(url, loadAsset(url, instance.getContext()), null, null, WXRenderStrategy.APPEND_ASYNC);

        }


    }
//    @Override
//    public void setVisibility(String visibility) {
//        super.setVisibility(visibility);
//        boolean visible = TextUtils.equals(visibility, Constants.Value.VISIBLE);
//        if (!TextUtils.isEmpty(src) && visible) {
//            if (instance == null) {
//                loadContent();
//            } else {
//                instance.onViewAppear();
//            }
//        }
//
//        if (!visible) {
//            if (instance != null) {
//                instance.onViewDisappear();
//            }
//        }
//        mIsVisible = visible;
//    }

//    @Override
//    public void destroy() {
//        super.destroy();
//        if (instance != null) {
//            instance.destroy();
//            instance = null;
//        }
//        src = null;
//        if (getInstance() != null) {
//            getInstance().removeOnInstanceVisibleListener(this);
//        }
//    }

//    @Override
//    public void onAppear() {
//        //appear event from root instance will not trigger visibility change
//        if(mIsVisible && instance != null){
//            WXComponent comp = instance.getRootComponent();
//            if(comp != null)
//                comp.fireEvent(Constants.Event.VIEWAPPEAR);
//        }
//    }
//
//    @Override
//    public void onDisappear() {
//        //appear event from root instance will not trigger visibility change
//        if(mIsVisible && instance != null){
//            WXComponent comp = instance.getRootComponent();
//            if(comp != null)
//                comp.fireEvent(Constants.Event.VIEWDISAPPEAR);
//        }
//    }
//
//    @Override
//    public void onActivityStart() {
//        super.onActivityStart();
//        if (instance != null) {
//            instance.onActivityStart();
//        }
//    }
//
//    @Override
//    public void onActivityResume() {
//        super.onActivityResume();
//        if (instance != null) {
//            instance.onActivityResume();
//        }
//    }
//
//    @Override
//    public void onActivityPause() {
//        super.onActivityPause();
//        if (instance != null) {
//            instance.onActivityPause();
//        }
//    }

//    @Override
//    public void onActivityStop() {
//        super.onActivityStop();
//        if (instance != null) {
//            instance.onActivityStop();
//        }
//    }
//
//    @Override
//    public void onActivityDestroy() {
//        super.onActivityDestroy();
//        if (instance != null) {
//            instance.onActivityDestroy();
//        }
//    }
}