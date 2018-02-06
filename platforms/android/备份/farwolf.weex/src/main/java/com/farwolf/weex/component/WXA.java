/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.farwolf.weex.component;

import android.text.TextUtils;
import android.util.Log;

import com.farwolf.weex.activity.WeexActivity;
import com.farwolf.weex.activity.WeexActivity_;
import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.core.WeexFactory_;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.ImmutableDomObject;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXDiv;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.WXFrameLayout;
import com.taobao.weex.utils.WXLogUtils;

@Component(lazyload = false)
public class WXA extends WXDiv {

  @Deprecated
  public WXA(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, String instanceId, boolean isLazy) {
    this(instance, dom, parent);
  }

  public WXA(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
    super(instance, dom, parent);
  }

  @Override
  protected void onHostViewInitialized(WXFrameLayout host) {
    super.onHostViewInitialized(host);
    addClickListener(new OnClickListener() {
      @Override
      public void onHostViewClick() {
        String href;
        ImmutableDomObject domObject = getDomObject();
        if (domObject != null) {
          WXAttr attr = domObject.getAttrs();
          if (attr !=null && (href = (String)attr.get("href")) != null) {

            String t=  ((String) attr.get("navbarVisibility")+"");
            openURL(href,t);
          }
        } else {
          WXLogUtils.d("WXA", "Property href is empty.");
        }
      }
    });
  }

  @Override
  protected boolean setProperty(String key, Object param) {
    switch(key){
      case Constants.Name.HREF:
        return true;
    }
    return super.setProperty(key, param);
  }

  @JSMethod(uiThread = true)
  public void openURL(String url,String navbarVisibility) {
    Log.i("WXEventModule",url);
    if (TextUtils.isEmpty(url)) {
      return;
    }
//    url= Weex.getRootUrl(url,this.mWXSDKInstance);
    String realurl=Weex.getRelativeUrl(url,this.getInstance());
    WeexFactory w= WeexFactory_.getInstance_(this.getInstance().getContext());
    WeexActivity a=  (WeexActivity)this.getInstance().getContext();
    w.jump(realurl, WeexActivity_.class,a.rootid);

  }

  public String getRealUrl(String url,String base)
  {


    String q[]=url.split("\\.\\.\\/");
    String x[]= base.split("\\/");

    String p="";

      for(int i=0;i<x.length-q.length;i++)
      {
         p+=x[i]+"/";
      }

    p+=q[q.length-1];
    return p;
  }
}
