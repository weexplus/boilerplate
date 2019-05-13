package com.farwolf.weex.component;

import android.Manifest;
import android.app.Activity;
import android.webkit.WebView;

import com.farwolf.perssion.Perssion;
import com.farwolf.perssion.PerssionCallback;
import com.farwolf.util.SDCard;
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

//    @JSMethod
//    public void excuteJs(String js){
//        WebView web=(WebView)mWebView.getView();
//        web.loadUrl("javascript:"+js);
//    }

    @Override
    public void setUrl(String url) {
        url= Weex.getRelativeUrl(url,this.mInstance);
        super.setUrl(url);

    }

    protected void createWebView(){
//        mWebView = new WXWebView(getContext(), "androidwebdata://");
        mWebView = new WXWebView(getContext(), "/storage/emulated/0/Android/data/com.tark.rose/files/Caches/app/file/kline");
    }


    @WXComponentProp(name = "showScrollbar")
    public void setShowScrollbar(boolean show){
        WebView web=(WebView)mWebView.getView();
        if(web!=null)
            web.setHorizontalScrollBarEnabled(show);

    }



    @Override
    public void loadUrl(final String url) {

        Perssion.check((Activity) mInstance.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE,new PerssionCallback(){


            @Override
            public void onGranted() {

                if(url.startsWith("http")){
                    WXFWeb.super.loadUrl(url);
                }else{
                    String param="";
                    String tempurl=url;
                    if(url.contains("?")){
                        param=  url.split("\\?")[1];
                        tempurl=url.split("\\?")[0];
                    }
                    String s=Local.getString(mInstance.getContext(),tempurl);
                    WXWebView web=(WXWebView)mWebView;

                    web.getWebView().getSettings().setJavaScriptEnabled(true);
                    web.getWebView().getSettings().setAllowFileAccessFromFileURLs(true);
                    web.getWebView().getSettings().setAllowFileAccess(true);
                    web.getWebView().getSettings().setAllowContentAccess(true);
//            String base=  mInstance.getContext().getExternalFilesDir("Caches")
//            web.getWebView().loadDataWithBaseURL("androidwebdata://?"+param,s,"text/html","utf-8","");
                    String base="file://"+ SDCard.getBasePath(mInstance.getContext())+"/"+url+"?"+param;
                    web.getWebView().loadDataWithBaseURL(base,s,"text/html","utf-8","");
//            web.getWebView().loadDataWithBaseURL("file:///android_asset/app/file/kline",s,"text/html","utf-8","");
                }


            }
        });



    }
}
