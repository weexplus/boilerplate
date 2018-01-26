
package com.farwolf.weex.module;

import android.text.TextUtils;
import android.util.Log;

import com.farwolf.weex.activity.WeexActivity;
import com.farwolf.weex.activity.WeexActivity_;
import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.core.WeexFactory_;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;


public class WXEventModule extends WXModule {

  private static final String WEEX_CATEGORY = "com.taobao.android.intent.category.WEEX";
  private static final String WEEX_ACTION = "com.taobao.android.intent.action.WEEX";


  @JSMethod(uiThread = true)
  public void openURL(String url) {
    Log.i("WXEventModule",url);
    if (TextUtils.isEmpty(url)) {
      return;
    }
//    url= Weex.getRootUrl(url,this.mWXSDKInstance);
    String realurl="";
    if(url.startsWith("root:"))
    {
        realurl=Weex.getRelativeUrl(url,this.mWXSDKInstance);
    }
    else
    {
      url= Weex.getRootUrl(url,this.mWXSDKInstance);
      realurl=getRealUrl(url);
    }
    WeexFactory w= WeexFactory_.getInstance_(mWXSDKInstance.getContext());
    WeexActivity a=  (WeexActivity)this.mWXSDKInstance.getContext();
    w.jump(realurl, WeexActivity_.class,a.rootid);

  }

  public String getRealUrl(String url)
  {

    if(url.startsWith("./"))
    {
      url=url.substring(2);
    }
    if(url.startsWith("/"))
    {
       url=url.substring(1);
    }
    if(url.contains("/./"))
    {
      url=url.replace("/./","/");
    }

    String q[]=url.split("\\.\\.\\/");
    String x[]= q[0].split("\\/");
    if(q.length==1)
      return q[0];
    String p="";
    if(x.length>=q.length-1)
    {
      for(int i=0;i<x.length-q.length+1;i++)
      {
        p+=x[i]+"/";
      }
    }
    p+=q[q.length-1];
    return p;
  }


}
