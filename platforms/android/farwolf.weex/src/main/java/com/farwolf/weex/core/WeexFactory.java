package com.farwolf.weex.core;

import android.content.Intent;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.farwolf.base.ServiceBase;
import com.farwolf.util.ScreenTool;
import com.farwolf.util.StringUtil;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by zhengjiangrong on 2017/5/9.
 */
@EBean
public class WeexFactory  extends ServiceBase{


    static HashMap<String,Page>m=new HashMap<>();


    int renderCount=0;

    @Bean
    ScreenTool tool;


    public static boolean hasCache(String url)
    {
        return m.containsKey(url);
    }

    public static void addCache(String id,Page p)
    {
        if(m==null)
            m=new HashMap<>();
        m.put(id,p);
    }
    public Page getPage(String id)
    {
        if(m.containsKey(id))
        {
            Page p= m.get(id);
            remove(id);
            return p;
        }
        return null;
    }

    public void remove(String id)
    {
        m.remove(id);

    }

    public void jump(String url,  Class cls,String rootid)
    {
        Intent in=new Intent(getActivity(),cls);
        in.putExtra("url",url);
        in.putExtra("rootid",rootid);
        this.jump(url,in,false);
    }


    public   void preRender(final List<String> urls, final OnMultiRenderFinishListener listener)
    {
        renderCount=0;
        for(String url :urls)
        {
            if(renderCount==-1)
            {
                break;
            }
            this.preRender(url, new OnRenderFinishListener() {
                @Override
                public void onRenderFinish(Page p) {

                    renderCount++;
                    if(renderCount==urls.size())
                    {
                        listener.onRenderFinish();
                    }

                }

                @Override
                public void onRenderFailed(Page p) {
                    listener.onRenderFailed();
                    renderCount=-1;
                }
            });
        }
    }

//    public int getCacheCount(List<String> urls)
//    {
//        int c=0;
//        for(String url :urls)
//        {
//            if(hasCache(url))
//            {
//                c++;
//            }
//        }
//        return c;
//    }

    public   void preRender(String url,final OnRenderFinishListener listener)
    {
        final Page p=new Page();

        p.instance=new WXSDKInstance(context);
        p.instance.setBundleUrl(url);
//        if(Weex.baseurl==null)
//        {
//            Weex.setBaseUrl(p.instance);
//        }
        p.id=url;
        if(p.id==null)
            p.id=new Random().nextLong()+"";
        p.instance.registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {

                p.v=view;
                p.instance.setSize(tool.getScreenWidth(),tool.getScreenHeight());
                m.put(p.id,p);
                if(listener!=null)
                {
                    listener.onRenderFinish(p);
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
                if(listener!=null)
                {
                    listener.onRenderFailed(p);
                }
            }
        });

        render(p.instance,url);
    }

    public   void preRender(String pageid,String url,final OnRenderFinishListener listener)
    {
        final Page p=new Page();

        p.instance=new WXSDKInstance(context);
        p.instance.setBundleUrl(url);
//        if(Weex.baseurl==null)
//        {
//            Weex.setBaseUrl(p.instance);
//        }
        p.id=pageid;
        if(p.id==null)
            p.id=new Random().nextLong()+"";
        addCache(url,p);
        p.instance.registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {

                p.v=view;
                p.instance.setSize(tool.getScreenWidth(),tool.getScreenHeight());
                if(listener!=null)
                {
                    listener.onRenderFinish(p);
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
                if(listener!=null)
                {
                    listener.onRenderFailed(p);
                }
            }
        });

        render(p.instance,url);
    }


    public static interface OnMultiRenderFinishListener
    {
        void onRenderFinish();
        void onRenderFailed();


    }

    public static interface OnRenderFinishListener
    {
        void onRenderFinish(Page p);
        void onRenderFailed(Page p);

    }

    public void jump(String url, final Intent in,final boolean forResult)
    {

//        if(url.startsWith("http"))
//        {
//
//            in.putExtra("url",url);
//            if(!forResult)
//                context.startActivity(in);
//            else
//                getActivity().startActivityForResult(in,10001);
//            return;
//        }


        if(hasCache(url))
        {
            Page p=m.get(url);
            in.putExtra("url",url);
            if(!forResult)
                context.startActivity(in);
            else
                getActivity().startActivityForResult(in,10001);
            return;
        }


        final  Page p=new Page();
        p.instance=new WXSDKInstance(context);
        p.instance.setBundleUrl(url);
        String pageid=new Random().nextLong()+"";
        in.putExtra("url",url);
        final boolean ispotrait=in.getBooleanExtra("isPortrait",true);
        addCache(url,p);
        p.id=pageid;
        p.instance.param=(JSONObject)in.getSerializableExtra("param");
        p.instance.registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, final View view) {


//                ((WeexActivity)context).mask.addView(view);
                p.v=view;
                if(ispotrait)
                    p.instance.setSize(tool.getScreenWidth(),tool.getScreenHeight());
                else
                    p.instance.setSize(tool.getScreenHeight(),tool.getScreenWidth());
                if(!forResult)
                    context.startActivity(in);
                else
                    getActivity().startActivityForResult(in,10001);


            }

            @Override
            public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
//                ((WeexActivity)context).mask.removeAllViews();



            }

            @Override
            public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

            }

            @Override
            public void onException(WXSDKInstance instance, String errCode, String msg) {

            }
        });


        render(p.instance,url);
    }

    public void render(WXSDKInstance instance, String url)
    {
        if(StringUtil.isNullOrEmpty(url))
            return;


        if(url.startsWith("http"))
        {
            instance.renderByUrl("farwolf", url, null, null, WXRenderStrategy.APPEND_ASYNC);
        }
        else
        {
            instance.render("farwolf", Weex.loadLocal(url, context), null, null, WXRenderStrategy.APPEND_ASYNC);

        }


    }





}
