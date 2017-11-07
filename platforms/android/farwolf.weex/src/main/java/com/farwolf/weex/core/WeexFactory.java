package com.farwolf.weex.core;

import android.content.Intent;
import android.view.View;

import com.farwolf.base.ServiceBase;
import com.farwolf.util.ScreenTool;
import com.farwolf.util.StringUtil;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by zhengjiangrong on 2017/5/9.
 */
@EBean
public class WeexFactory  extends ServiceBase{


    static HashMap<String,Page>m=new HashMap<>();




    @Bean
    ScreenTool tool;


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
        this.jump(url,cls,rootid,"hidden");
    }

    public void jump(String url,  Class cls,String rootid,String  navbarVisibility)
    {
        Intent in =new Intent(context,cls);
        in.putExtra("rootid",rootid);
        in.putExtra("navbarVisibility",navbarVisibility);
        jump(url,in,false);
    }


    public   void preRender(String pageid,String url,final OnRenderFinishListener listener)
    {
        final Page p=new Page();

        p.instance=new WXSDKInstance(context);
        p.instance.setBundleUrl(url);
        if(Weex.baseurl==null)
        {
            Weex.setBaseUrl(p.instance);
        }
        p.id=pageid;
        if(p.id==null)
        p.id=new Random().nextLong()+"";
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

            }
        });
        m.put(p.id,p);
        render(p.instance,url);
    }

    public static interface OnRenderFinishListener
    {
        void onRenderFinish(Page p);
    }

    public void jump(String url, final Intent in,final boolean forResult)
    {

        if(url.startsWith("http"))
        {

            in.putExtra("url",url);
            if(!forResult)
                context.startActivity(in);
                else
            getActivity().startActivityForResult(in,10001);
            return;
        }

        final Page p=new Page();


        p.instance=new WXSDKInstance(context);
        p.instance.setBundleUrl(url);
        String pageid=new Random().nextLong()+"";
        in.putExtra("pageid",pageid);
        p.id=pageid;
        p.instance.registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, final View view) {


//                ((WeexActivity)context).mask.addView(view);
                p.v=view;
                p.instance.setSize(tool.getScreenWidth(),tool.getScreenHeight());
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

        m.put(p.id,p);
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
            instance.render("farwolf", WXFileUtils.loadAsset(url, context), null, null, WXRenderStrategy.APPEND_ASYNC);

        }


    }





}
