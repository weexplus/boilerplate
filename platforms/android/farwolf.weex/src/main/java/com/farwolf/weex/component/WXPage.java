package com.farwolf.weex.component;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.farwolf.util.ScreenTool;
import com.farwolf.util.ScreenTool_;
import com.farwolf.weex.core.Page;
import com.farwolf.weex.core.WeexFactory_;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXPerformance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.NestedContainer;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXDiv;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXUtils;

import static com.taobao.weex.utils.WXFileUtils.loadAsset;

/**
 * Created by zhengjiangrong on 2017/8/19.
 */

public class WXPage extends WXDiv implements WXSDKInstance.OnInstanceVisibleListener,NestedContainer {



    private String src;
    private WXSDKInstance instance;

    private boolean mIsVisible = true;

    WeexFactory_ weexFactory;

    Page page;



    ScreenTool tool;



    @Deprecated
    public WXPage(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, String instanceId, boolean isLazy) {
        this(instance,dom,parent);

    }

    public WXPage(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
        init();



    }

    void init()
    {
        weexFactory=WeexFactory_.getInstance_(getContext());
        tool= ScreenTool_.getInstance_(getContext());
    }

    @Override
    public void setOnNestEventListener(OnNestedInstanceEventListener listener){

    }

    @Override
    public ViewGroup getViewContainer() {
        return getHostView();
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        switch (key) {
            case Constants.Name.SRC:
                String src = WXUtils.getString(param,null);
                if (src != null)
                    setSrc(src);
                return true;
        }
        return super.setProperty(key, param);
    }

    @Override
    public void renderNewURL(String url) {
        src = url;
        loadContent();
    }



    @Override
    public void reload() {
        if (!TextUtils.isEmpty(src)) {
            loadContent();
        }
    }


    @WXComponentProp(name = Constants.Name.SRC)
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
            getHostView().removeAllViews();
            ViewGroup.LayoutParams layoutParams = getHostView().getLayoutParams();
            page.v.setLayoutParams(layoutParams);
            getHostView().addView(page.v);
        }
        else
        {
            this.loadUrl(src);
        }

    }

    public String getSrc() {
        return src;
    }

    /**
     * Load embed content, default behavior is create a nested instance.
     */
    protected void loadContent(){

    }

    private WXSDKInstance createInstance() {
        WXSDKInstance sdkInstance = getInstance().createNestedInstance(this);
        getInstance().addOnInstanceVisibleListener(this);
        sdkInstance.registerRenderListener(new IWXRenderListener(){

            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {

                FrameLayout hostView =getHostView();
                hostView.removeAllViews();
                hostView.addView(view);
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
        ViewGroup.LayoutParams layoutParams = getHostView().getLayoutParams();

        //zjr add

        return sdkInstance;
    }

    //zjr add
    public void loadUrl(String url)
    {

        ViewGroup.LayoutParams layoutParams=getHostView().getLayoutParams();
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
    @Override
    public void setVisibility(String visibility) {
        super.setVisibility(visibility);
        boolean visible = TextUtils.equals(visibility, Constants.Value.VISIBLE);
        if (!TextUtils.isEmpty(src) && visible) {
            if (instance == null) {
                loadContent();
            } else {
                instance.onViewAppear();
            }
        }

        if (!visible) {
            if (instance != null) {
                instance.onViewDisappear();
            }
        }
        mIsVisible = visible;
    }

    @Override
    public void destroy() {
        super.destroy();
        if (instance != null) {
            instance.destroy();
            instance = null;
        }
        src = null;
        if (getInstance() != null) {
            getInstance().removeOnInstanceVisibleListener(this);
        }
    }

    @Override
    public void onAppear() {
        //appear event from root instance will not trigger visibility change
        if(mIsVisible && instance != null){
            WXComponent comp = instance.getRootComponent();
            if(comp != null)
                comp.fireEvent(Constants.Event.VIEWAPPEAR);
        }
    }

    @Override
    public void onDisappear() {
        //appear event from root instance will not trigger visibility change
        if(mIsVisible && instance != null){
            WXComponent comp = instance.getRootComponent();
            if(comp != null)
                comp.fireEvent(Constants.Event.VIEWDISAPPEAR);
        }
    }

    @Override
    public void onActivityStart() {
        super.onActivityStart();
        if (instance != null) {
            instance.onActivityStart();
        }
    }

    @Override
    public void onActivityResume() {
        super.onActivityResume();
        if (instance != null) {
            instance.onActivityResume();
        }
    }

    @Override
    public void onActivityPause() {
        super.onActivityPause();
        if (instance != null) {
            instance.onActivityPause();
        }
    }

    @Override
    public void onActivityStop() {
        super.onActivityStop();
        if (instance != null) {
            instance.onActivityStop();
        }
    }

    @Override
    public void onActivityDestroy() {
        super.onActivityDestroy();
        if (instance != null) {
            instance.onActivityDestroy();
        }
    }
}