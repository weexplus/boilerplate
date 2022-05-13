package com.weexplus.component;

import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.adapter.URIAdapter;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXImageSharpen;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.SingleFunctionParser;
import com.taobao.weex.utils.WXUtils;
import com.weex.weexplus.R;
import com.weexplus.util.ReflectUtil;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WXImage extends com.taobao.weex.ui.component.WXImage {


    private static SingleFunctionParser.FlatMapper<Integer> BLUR_RADIUS_MAPPER = new SingleFunctionParser.FlatMapper<Integer>() {
        @Override
        public Integer map(String raw) {
            return WXUtils.getInteger(raw,0);
        }
    };

    public WXImage(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }


    @WXComponentProp(name = Constants.Name.SRC)
    public void setSrc(String src) {

        if (getInstance().getImageNetworkHandler() != null) {
            String localUrl = getInstance().getImageNetworkHandler().fetchLocal(src);
            if (!TextUtils.isEmpty(localUrl)) {
                src = localUrl;
            }
        }

        //zjr
        if (src == null&&!getAttrs().containsKey(Constants.Name.PLACEHOLDER)) {
            return;
        }

        ImageView image = getHostView();

        String tmSrc=ReflectUtil.get(com.taobao.weex.ui.component.WXImage.class,"mSrc",this);
        if(image != null){
            if(image.getDrawable() != null && !TextUtils.equals(tmSrc, src)){
                image.setImageDrawable(null);
            }
        }
        ReflectUtil.setObject(com.taobao.weex.ui.component.WXImage.class,"mSrc",src,this);

        WXSDKInstance instance = getInstance();
        Uri rewrited = instance.rewriteUri(Uri.parse(src), URIAdapter.IMAGE);
        int blur = 0;
        String blurStr = getStyles().getBlur();
        blur = parseBlurRadius(blurStr);
        setRemoteSrc(rewrited, blur);

    }

    private int parseBlurRadius(@Nullable String rawRadius) {
        if(rawRadius == null) {
            return 0;
        }
        SingleFunctionParser<Integer> parser = new SingleFunctionParser<Integer>(rawRadius,BLUR_RADIUS_MAPPER);
        List<Integer> list = null;
        try {
            list = parser.parse("blur");
        }catch (Exception e) {
            return 0;
        }
        if(list == null || list.isEmpty()) {
            return 0;
        }
        return list.get(0);
    }


    private void setRemoteSrc(Uri rewrited, int blurRadius) {

        WXImageStrategy imageStrategy = new WXImageStrategy(getInstanceId());
        imageStrategy.isClipping = true;

        WXImageSharpen imageSharpen = getAttrs().getImageSharpen();
        imageStrategy.isSharpen = imageSharpen == WXImageSharpen.SHARPEN;

        imageStrategy.blurRadius = Math.max(0, blurRadius);

        ReflectUtil.setInt(com.taobao.weex.ui.component.WXImage.class,"mBlurRadius",blurRadius,this);


        final String rewritedStr = rewrited.toString();
        imageStrategy.setImageListener(new  MyImageListener(this,rewritedStr));

        String placeholder=null;
        if(getAttrs().containsKey(Constants.Name.PLACEHOLDER)){
            placeholder= (String) getAttrs().get(Constants.Name.PLACEHOLDER);
        }else if(getAttrs().containsKey(Constants.Name.PLACE_HOLDER)){
            placeholder=(String)getAttrs().get(Constants.Name.PLACE_HOLDER);
        }
        //zjr add
        if(placeholder!=null){
            imageStrategy.placeHolder=placeholder;
        }

        imageStrategy.instanceId = getInstanceId();
        IWXImgLoaderAdapter imgLoaderAdapter = getInstance().getImgLoaderAdapter();
        getHostView().setTag(R.id.imageTag,getInstance());
        if (imgLoaderAdapter != null) {
            imgLoaderAdapter.setImage(rewritedStr, getHostView(),
                    getImageQuality(), imageStrategy);
        }
    }

    public  static class MyImageListener implements WXImageStrategy.ImageListener {

        private WeakReference<com.taobao.weex.ui.component.WXImage> wxImageWeakReference;

        private String rewritedStr;

        MyImageListener(com.taobao.weex.ui.component.WXImage image, String rewritedStr) {
            this.wxImageWeakReference = new WeakReference<com.taobao.weex.ui.component.WXImage>(image);
            this.rewritedStr = rewritedStr;
        }

        @Override
        public void onImageFinish(String url, ImageView imageView, boolean result, Map extra) {
            com.taobao.weex.ui.component.WXImage image = wxImageWeakReference.get();
            if(image == null)
                return;

            if (image.getEvents().contains(Constants.Event.ONLOAD)) {
                Map<String, Object> params = new HashMap<String, Object>();
                Map<String, Object> size = new HashMap<String, Object>(2);
                if (imageView != null && imageView instanceof Measurable) {
                    size.put("naturalWidth", ((Measurable) imageView).getNaturalWidth());
                    size.put("naturalHeight", ((Measurable) imageView).getNaturalHeight());
                } else {
                    size.put("naturalWidth", 0);
                    size.put("naturalHeight", 0);
                }
                if (image.containsEvent(Constants.Event.ONLOAD)) {
                    params.put("success", result);
                    params.put("size", size);
                    image.fireEvent(Constants.Event.ONLOAD, params);
                }
            }
            try {
                Method  method = com.taobao.weex.ui.component.WXImage.class.getMethod("monitorImgSize", ImageView.class,String.class);
                method.invoke(this,imageView,rewritedStr);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }



}
