package com.farwolf.weex.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.farwolf.util.ScreenTool_;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjiangrong on 2017/6/22.
 */

public class WXViewPager extends WXComponent<ViewPager> {



    List<View> views=new ArrayList<>();


    List<String> urls=new ArrayList<>();

    public WXViewPager(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }


    @Override
    protected ViewPager initComponentHostView(@NonNull Context context) {
        ViewPager v=new ViewPager(this.getContext());
//        FrameLayout l=new FrameLayout(this.getContext());
//        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        v.setLayoutParams(lp);
//        l.addView(v);
        return v;
    }

    @WXComponentProp(name = Constants.Name.SRC)
    public void setSrc(String src) {


       String q[]=src.split(";");
        for(String p:q)
        {
            urls.add(p);
            render(p);
        }

    }

    public void render(String url)
    {
        ScreenTool_ tool=ScreenTool_.getInstance_(getContext());
//        this.src=src;
        WXSDKInstance instance=new WXSDKInstance(getContext());
        instance.setBundleUrl(url);
        instance.setSize(tool.getScreenWidth(),tool.getScreenHeight());

        instance.registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {

                ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lp);
                views.add(view);
                if (views.size()>=2) {
                    getHostView().setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                            return views.size();
                        }

                        @Override
                        public boolean isViewFromObject(View view, Object object) {
                            return true;
                        }

                        @Override
                        public Object instantiateItem(ViewGroup container, int position) {

                            View v = views.get(position);
                            if (v.getParent() == null)
                                getHostView().addView(v);

                            return v;
                        }

                        @Override
                        public void destroyItem(ViewGroup container, int position, Object object) {

                        }
                    });

                }

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
        instance.renderByUrl("farwolf", url ,null, null, WXRenderStrategy.APPEND_ASYNC);
    }


}
