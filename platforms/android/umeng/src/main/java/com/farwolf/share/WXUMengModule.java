package com.farwolf.share;

import android.content.Context;

import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/8/9.
 */

public class WXUMengModule extends WXModuleBase {



    public static  void initUM(Context c)
    {
        JSONObject j= Weex.config(c);
        JSONObject st=j.optJSONObject("static");
        JSONObject um=st.optJSONObject("umeng");
        JSONObject android=um.optJSONObject("android");
        String appkey=android.optString("appkey");
        String channel=android.optString("channel");
        MobclickAgent.UMAnalyticsConfig config=new MobclickAgent.UMAnalyticsConfig(c,appkey,channel);
        MobclickAgent. startWithConfigure(config);
        Config.DEBUG = true;
        initPlatforms(j.optJSONObject("platform"));
        try {
            WXSDKEngine.registerModule("umeng", WXUMengModule.class);
        }
        catch (Exception e)
        {

        }

    }

    public static void initPlatforms(JSONObject pl)
    {
         if(pl==null)
             return;
        JSONObject wx=  pl.optJSONObject("wx");
        JSONObject qq=  pl.optJSONObject("qq");
        JSONObject weibo=  pl.optJSONObject("weibo");

        if(wx!=null)
        {
            PlatformConfig.setWeixin(wx.optString("appkey"),wx.optString("appsecret"));
        }
        if(qq!=null)
        {
            PlatformConfig.setQQZone(qq.optString("appkey"),qq.optString("appsecret"));
        }
        if(weibo!=null)
        {
            PlatformConfig.setSinaWeibo(weibo.optString("appkey"),weibo.optString("appsecret"),weibo.optString("url"));
        }

    }

    @JSMethod
    public  void initUM(String appkey, String channel)
    {
        MobclickAgent.UMAnalyticsConfig config=new MobclickAgent.UMAnalyticsConfig(getActivity(),appkey,channel);
        MobclickAgent. startWithConfigure(config);


    }


    @JSMethod
    public  void openShareBox(final JSCallback callback)
    {

        new ShareAction(getActivity())

                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.SINA,SHARE_MEDIA.TENCENT)
                .setShareboardclickCallback(new ShareBoardlistener(){

                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        HashMap m=new HashMap();
                        String name=  platformToName(share_media);
                        m.put("platform",name);
                        callback.invokeAndKeepAlive(m);
                    }
                })

                .open();
    }

    /**
     * 自定义事件
     * @param eventId
     * @param param
     */
    @JSMethod
    public void onEvent(String eventId, HashMap param)
    {
        if(param==null)
        {
            MobclickAgent.onEvent(getActivity(), eventId);
        }
        else
        {
            MobclickAgent.onEvent(getActivity(), eventId,param);
        }
    }


    @JSMethod
    public void shareText(String platform,String text,JSCallback callback)
    {
        new ShareAction(getActivity())
                .setPlatform(getPlatform(platform))
                .withText(text)
                .setCallback(getUMShareListener(callback))
                .share();
    }


    @JSMethod
    public void shareImage(String platform,String thumimg,String imgurl,String title,String text,JSCallback callback)
    {
        UMImage image = new UMImage(getActivity(), imgurl);
        UMImage thumb =  new UMImage(getActivity(), thumimg);
        image.setThumb(thumb);
        image.setTitle(title);
        image.setDescription(text);
        new ShareAction(getActivity())
                .withText(text)
                .setPlatform(getPlatform(platform))
                .withMedia(image)
                .setCallback(getUMShareListener(callback))
                .share();
    }


//    -(void)shareWebPage:(NSString*)platform imgurl:(NSString*)imgurl  desc:(NSString*)desc  url:(NSString*)url callback:(WXModuleKeepAliveCallback)callback


    @JSMethod
    public void shareWebPage(String platform,String imgurl,String title,String desc,String url,JSCallback callback)
    {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(new UMImage(getActivity(), imgurl));  //缩略图
        web.setDescription(desc);//描述
        new ShareAction(getActivity())
                .withMedia(web)
                .setPlatform(getPlatform(platform))
                .setCallback(getUMShareListener(callback))
                .share();
    }


    public UMShareListener getUMShareListener(final JSCallback callback)
    {
      final  HashMap m=new HashMap();
       return new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
//                callback(@{@"res":resp.originalResponse,@"err":@(0)},false);
            }

            @Override
            public void onResult(SHARE_MEDIA share_media) {
                m.put("err",0);
                m.put("res",0);
                callback.invoke(m);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                m.put("err",1);
                m.put("res",throwable.getMessage());
                callback.invoke(m);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                m.put("err",2);
                m.put("res","取消");
                callback.invoke(m);
            }
        };
    }

    public SHARE_MEDIA getPlatform(String name)
    {
        if("wx_timeline".equals(name))
        {
            return SHARE_MEDIA.WEIXIN_CIRCLE;
        }
        else  if("wx_session".equals(name))
        {
            return SHARE_MEDIA.WEIXIN;
        }
        else  if("qzone".equals(name))
        {
            return SHARE_MEDIA.QZONE;
        }
        else  if("qq".equals(name))
        {
            return SHARE_MEDIA.QQ;
        }
        else  if("sina".equals(name))
        {
            return SHARE_MEDIA.SINA;
        }
        else  if("tencentwb".equals(name))
        {
            return SHARE_MEDIA.TENCENT;
        }
        return SHARE_MEDIA.WEIXIN_CIRCLE;

    }

    public String platformToName(SHARE_MEDIA p)
    {
        if(p==SHARE_MEDIA.WEIXIN_CIRCLE)
        {
            return "wx_timeline";
        }
        else if(p==SHARE_MEDIA.WEIXIN)
        {
            return "wx_session";
        }
        else if(p==SHARE_MEDIA.QZONE)
        {
            return "qzone";
        }
        else if(p==SHARE_MEDIA.QQ)
        {
            return "qq";
        }
        else if(p==SHARE_MEDIA.SINA)
        {
            return "sina";
        }
        else if(p==SHARE_MEDIA.TENCENT)
        {
            return "tencentwb";
        }
       return "wx_timeline";
    }

}
