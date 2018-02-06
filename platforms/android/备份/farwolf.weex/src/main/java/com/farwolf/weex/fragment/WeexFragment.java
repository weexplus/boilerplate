package com.farwolf.weex.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.farwolf.util.FileTool;
import com.farwolf.weex.R;
import com.farwolf.weex.core.Page;
import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.core.WeexFactory_;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import java.io.IOException;
import java.util.HashMap;


public class WeexFragment extends Fragment implements IWXRenderListener {



  FrameLayout fragment_container;

  private String mBundleUrl;


  public WeexFactory weexFactory;


  private WXSDKInstance mWXSDKInstance;

  protected View view;

  public WeexFragment(){

  }

  public static WeexFragment newInstance(String bundleUrl) {
    WeexFragment fragment = new WeexFragment();
//    fragment.setCache(true);
    Bundle args = new Bundle();
    args.putString(WXSDKInstance.BUNDLE_URL, bundleUrl);
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    if(view==null)
    {
        view=inflater.inflate(getViewId()	, null);
        fragment_container=(FrameLayout)view.findViewById(R.id.fragment_container);
        mBundleUrl = getArguments() != null ? getArguments().getString(WXSDKInstance.BUNDLE_URL) : null;
        mWXSDKInstance = new WXSDKInstance(getActivity());
        if(mBundleUrl==null&&getArguments()!=null)
          mBundleUrl=getArguments().getString("url");
        mWXSDKInstance.registerRenderListener(this);
        HashMap<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, mBundleUrl);
        weexFactory = WeexFactory_.getInstance_(getContext());
        Page page= weexFactory.getPage(mBundleUrl);
        if(page!=null)
        {
          loadPage();
        }
        else
        {
          loadurl();
        }
//        AndroidBug5497Workaround.assistActivity(fragment_container);
      return view;
    }
    else
    {
      return view;
    }

  }

  public void loadPage()
  {
    Page page= weexFactory.getPage(mBundleUrl);
    fragment_container.addView(page.v);

//    url=page.url;
    mWXSDKInstance= page.instance;
//    pageid=page.id;
    mWXSDKInstance.setContext(getContext());
    mWXSDKInstance.registerRenderListener(this);
    mWXSDKInstance.onActivityCreate();
    mWXSDKInstance.fireGlobalEventCallback("onPageInit",null);
    page=null;
  }

  public void loadurl()
  {
    if(mBundleUrl.startsWith("http"))
    {
      mWXSDKInstance.renderByUrl("farwolf", mBundleUrl, null, null, WXRenderStrategy.APPEND_ASYNC);
    }
    else
    {
      try {
        mWXSDKInstance.render("farwolf", FileTool.loadAsset(mBundleUrl, this.getContext()), null, null, WXRenderStrategy.APPEND_ASYNC);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


  public int getViewId() {
    return  R.layout.fragment_weex;
  }



  @Override
  public void onStart() {
    super.onStart();
    if(mWXSDKInstance!=null){
      mWXSDKInstance.onActivityStart();
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if(mWXSDKInstance!=null){
      mWXSDKInstance.onActivityResume();
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    if(mWXSDKInstance!=null){
      mWXSDKInstance.onActivityPause();
    }
  }

  @Override
  public void onStop() {
    super.onStop();
    if(mWXSDKInstance!=null){
      mWXSDKInstance.onActivityStop();
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if(mWXSDKInstance!=null){
      mWXSDKInstance.onActivityDestroy();
    }
    if(view!=null)
    {
      if(view.getParent()!=null)
        ((ViewGroup)view.getParent()).removeAllViews();
    }
  }




  @Override
  public void onDetach() {
    super.onDetach();
  }

  @Override
  public void onViewCreated(WXSDKInstance instance, View view) {
    fragment_container.addView(view);
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
}
