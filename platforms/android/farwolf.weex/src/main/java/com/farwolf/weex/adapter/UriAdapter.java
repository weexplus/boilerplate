package com.farwolf.weex.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.adapter.DefaultUriAdapter;

public class UriAdapter extends DefaultUriAdapter {
    @NonNull
    @Override
    public Uri rewrite(String bundleURL, String type, Uri uri) {
        if (uri.toString().startsWith("http"))
            return super.rewrite(bundleURL, type, uri);
        else
         {
            return  uri;
        }

//         else if(uri.toString().startsWith("sdcard:")){
//            String ul= uri.toString().replace("sdcard:","");
//            return   Uri.parse(ul);
//        }
    }

    @NonNull
    @Override
    public Uri rewrite(WXSDKInstance instance, String type, Uri uri) {
        if(uri.toString().startsWith("http")){
            return super.rewrite(instance, type, uri);
        }else{
            return  uri;
        }

    }
}
