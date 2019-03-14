package com.farwolf.weex.component;

import android.webkit.WebView;

import com.farwolf.weex.core.local.Local;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.component.WXWeb;
import com.taobao.weex.ui.view.WXWebView;

/**
 * Created by zhengjiangrong on 2017/8/15.
 */

public class WXFWeb extends WXWeb {
    public WXFWeb(WXSDKInstance instance, WXVContainer parent, boolean isLazy, BasicComponentData basicComponentData) {
        super(instance, parent, isLazy, basicComponentData);
    }


//    public WXFWeb(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, boolean isLazy) {
//        super(instance, dom, parent, isLazy);
//    }


    @Override
    public void setUrl(String url) {
        url= Weex.getRelativeUrl(url,this.mInstance);
        super.setUrl(url);
    }

    protected void createWebView(){
        mWebView = new WXWebView(getContext(), "androidwebdata://");
    }


    @WXComponentProp(name = "showScrollbar")
    public void setShowScrollbar(boolean show){
        WebView web=(WebView)mWebView.getView();
        if(web!=null)
            web.setHorizontalScrollBarEnabled(show);
    }


    @Override
    public void loadUrl(String url) {
        if(url.startsWith("http")){
            super.loadUrl(url);
        }else{
            String s=Local.getString(mInstance.getContext(),url);
            this.loadDataWithBaseURL(s);
        }


    }
}
