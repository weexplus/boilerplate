package com.farwolf.weex.component;


import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.farwolf.weex.util.Weex;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.WXPerformance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXEmbed;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXFileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zhengjiangrong on 2017/6/26.
 */

@Component(lazyload = false)
public class WXFEmbed extends WXEmbed {


    public WXFEmbed(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);

    }

    public void loadUrl(String url,WXSDKInstance instance,ViewGroup.LayoutParams layoutParams)
    {

        if(url.startsWith("root:"))
        {
            url= Weex.getRootUrl(url, this.getParent().getInstance());
        }
        if(url.startsWith("http"))
        {
            instance.renderByUrl(WXPerformance.DEFAULT, url, null, null, layoutParams.width,
                    layoutParams.height, WXRenderStrategy.APPEND_ASYNC);


        }
        else
        {

            instance.render(url, WXFileUtils.loadAsset(url,  this.getParent().getInstance().getContext()), null, null, WXRenderStrategy.APPEND_ASYNC);

        }
    }

    public static String loadAsset(String path, Context context) throws IOException {
        if (context == null || TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        inputStream = context.getAssets().open(path);
        StringBuilder builder = new StringBuilder(inputStream.available() + 10);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        char[] data = new char[4096];
        int len = -1;
        while ((len = bufferedReader.read(data)) > 0) {
            builder.append(data, 0, len);
        }

        return builder.toString();


    }
}
