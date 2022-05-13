package com.weexplus.core.render;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;
import com.weexplus.activity.WxpActivity;
import com.weexplus.activity.WxpEntryActivity;
import com.weexplus.activity.WxpLanscapeActivity;
import com.weexplus.activity.WxpPresentActivity;
import com.weexplus.bean.Page;
import com.weexplus.core.CacheManager;
import com.weexplus.core.Module;
import com.weexplus.core.WeexPlus;
import com.weexplus.core.WxpInstance;
import com.weexplus.util.AppMainfest;
import com.weexplus.util.AppTool;
import com.weexplus.util.BackTask;
import com.weexplus.util.Callback;
import com.weexplus.util.Config;
import com.weexplus.util.HttpTool;
import com.weexplus.util.Md5;
import com.weexplus.util.Path;
import com.weexplus.util.SDCard;
import com.weexplus.util.Const;
import com.weexplus.util.StringUtil;
import com.weexplus.view.FreeDialog;
import com.weexplus.view.LoadingDialog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class WeexRender {

    static HashMap<String,Page>pageCache=new HashMap<String,Page>();

    static HashMap modules = new HashMap();

    /**
     * @param url 绝对路径
     * @param modulename 模块名
     * @param param 参数
     * @param context
     * @param callback
     */
    public static void renderByUrl(final String url, final String modulename, final JSONObject param, final Map options,final Context context,boolean showLoading, final IWXRenderListener callback) {
        if (url.startsWith(Const.HTTP)) {

            loadHttpJs(url, getModule(modulename),context,showLoading, new Callback() {
                @Override
                public void onInvoke(Object o) {
                    if(o==null){
                        callback.onException(null,"-1","js下载失败");
                        return;
                    }
                    renderByJsString(o+"",modulename, param,options, context, callback).setBundleUrl(url);
                }
            });
        } else {
             String bs= loadLocalJs(url,context,getModule(modulename));
             renderByJsString(bs,modulename, param,options, context, callback).setBundleUrl(url);;
        }
    }


    public static Module getModule(String name) {
        Module m = (Module) modules.get(name);
        if (m == null) {
            m = new Module();
            m.name=name;
            modules.put(name, m);
        }
        return m;
    }

    public static Page getPage(String id)
    {
        if(pageCache.containsKey(id))
        {
            Page p= pageCache.get(id);
            pageCache.remove(id);
            return p;
        }
        return null;
    }



    public static WxpInstance renderByJsString(String js,String module, JSONObject param,Map options, Context context, IWXRenderListener callback) {
        WxpInstance instance = new WxpInstance(context);
        instance.param = param;
        instance.module = getModule(module);

        instance.registerRenderListener(callback);
        JSONObject m=new JSONObject();
        if(options!=null)
        m.putAll(options);
        instance.render(WeexPlus.BRAND, js, m, null, WXRenderStrategy.APPEND_ASYNC);
        return instance;
    }

    private static String resetAppboard(String js,String modulename){
        Module module=  getModule(modulename);
        String bs=js;
        String sp = "/";
        sp += Const.APPBOARRD_SPLITOR;
        sp += "/";
        if (bs.contains(sp)) {
            String appaboard = bs.split("\\/\\*\\*\\*\\*\\*\\*\\*weexplus_split_weexplus\\*\\*\\*\\*\\*\\*\\/")[0];
            module.appboard = appaboard;
            module.navDic=getNavDic(modulename,appaboard);
            bs = bs.replace(sp, "");
        } else {
            bs = module.appboard + bs;
        }
        return bs;
    }

    public static  Map getNavDic(String modulename,String appBoardContent){
        Map navDic=new JSONObject();
        Module module=getModule(modulename);
        if(appBoardContent.contains("weexplus_split_router_weexplus")) {
            String appaboard = appBoardContent.split("\\/\\*\\*\\*\\*\\*\\*\\*weexplus_split_router_weexplus\\*\\*\\*\\*\\*\\*\\/")[0];
            if (navDic == null || !(module.router + "").equals(appaboard)) {
                module.router = appaboard;
                String q[] = appaboard.split("\n");
                String router = q[q.length - 1];
                router = router.substring(3, router.length());
                navDic = com.alibaba.fastjson.JSONObject.parseObject(router);
            }
            return navDic;
        }
        return null;


    }

    public static void loadHttpJs(String url, final Module module, final Context context,final boolean showLoading, final Callback callback) {
        final String temp = url;
        String md5 = Md5.toMd5(module.appboard);
        final String modulename=module.name;
        url = url + "?md5=" + md5;
        url += "&p=" + System.currentTimeMillis()+""+new Random(1000000).nextInt();
        final String furl=url;
        downloadHttpHash(url, module.name, context,new Callback() {
            @Override
            public void onInvoke(Object o) {
                 boolean needDownload=  CacheManager.needDownload(temp,modulename,context);
                 if(!needDownload){
                       String bs=getLocalJs(context,module,Path.getPath(temp,module.name));
                       onHttpJsDownload(bs,module,temp,context,callback);
                      return;
                 }
                FreeDialog f=null;
                 if(showLoading){
                       f= showLoading(context);
                 }
                 final  FreeDialog tf=f;
                Request req = OkGo.<String>get(furl);
                req.execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String bs = response.body();
                        CacheManager.cache(furl,bs.getBytes(),modulename,context);
                        onHttpJsDownload(bs,module,furl,context,callback);
                        if(tf!=null)
                            tf.dismiss();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callback.onInvoke(null);
                        if(tf!=null)
                            tf.dismiss();
                    }
                });
            }
        });


    }

    public static void onHttpJsDownload(final String js,final Module module,final String furl,final Context context,final Callback callback){
        final String  bs= resetAppboard(js+"",module.name);
        if(module.appboard==null){
            downloadHttpAppboard(furl, module.name, bs,context, new Callback() {
                @Override
                public void onInvoke(Object o) {
                    if(module.navDic==null){
                        downloadRouterJson(furl,module.name,o+"",context,callback);
                    }else{
                        callback.onInvoke(o+"");
                    }
                }
            });
        }else{
            if(module.navDic==null){
                downloadRouterJson(furl,module.name,bs,context,callback);
            }else{
                callback.onInvoke(bs);
            }
        }
    }


    public static void downloadHttpAppboard(final String url, final String modulename, final String bs,final Context context, final Callback callback){
        String base=Path.getBaseUrl(url,modulename);
        String appboardUrl=base+"mixin/appBoard.js";
        boolean needDownload=  CacheManager.needDownload(appboardUrl,modulename,context);
        if(!needDownload){
            Module module=  getModule(modulename);
            String as=getLocalJs(context,module,Path.getPath(appboardUrl,modulename));
            module.appboard=as;
            callback.onInvoke(as+bs);
            return;
        }
        appboardUrl += "?p=" + new Random(100000).nextInt();
        final String fappboardUrl=appboardUrl;
        Request req = OkGo.<String>get(appboardUrl);
        req.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                String as = response.body();
                CacheManager.cache(fappboardUrl,as.getBytes(),modulename,context);
                Module module=  getModule(modulename);
                module.appboard=as;
                callback.onInvoke(as+bs);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                callback.onInvoke(null);
            }
        });

    }


    public static void downloadHttpHash(final String url, final String modulename,Context context, final Callback callback){
        if(url.contains(Const.DEBUG_URL_FLAG)){
            callback.onInvoke(null);
            return;
        }
       final Module module=  getModule(modulename);
        if(module.hash!=null){
            callback.onInvoke(null);
            return;
        }
        final String ver=new AppMainfest(context).getVersionName();
        String base=Path.getBaseUrl(url,modulename);
        final String hashUrl=base+"hash.json?p=" + new Random(100000).nextInt();
        new BackTask<HashMap>( new BackTask.Callback() {
           @Override
           public String invoke(Object... base64) {
               HashMap m= new HashMap();
               m.put("timeout",10000);
               String res=  HttpTool.get(hashUrl,m,new HashMap());
               return res;
           }
       }, new BackTask.OnFinishCallback() {
           @Override
           public void invoke(Object s) {
               String res=(String)s;
               if(!StringUtil.isNullOrEmpty(res)){
                   JSONObject jb=com.alibaba.fastjson.JSONObject.parseObject(res);
                   String spt=jb.getString("spt_android");
                   if(spt!=null&&spt.contains(ver)){
                       module.hash=com.alibaba.fastjson.JSONObject.parseObject(res);
                   }else{
                       module.hash=new HashMap();
                   }
               }else{
                   module.hash=new HashMap();
               }
               callback.onInvoke(null);

           }
       }).execute();




    }

    public static void downloadRouterJson(String url, final String modulename, final String bs, final Context context, final Callback callback){
        String base=Path.getBaseUrl(url,modulename);
        String routerUrl=base+"router-translator.json";
        routerUrl += "?p=" + new Random(100000).nextInt();
        final String frouterUrl=routerUrl;
        boolean needDownload=  CacheManager.needDownload(routerUrl,modulename,context);
        if(!needDownload){
            Module module=  getModule(modulename);
            String rs=getLocalJs(context,module,Path.getPath(routerUrl,modulename));
            module.navDic=com.alibaba.fastjson.JSONObject.parseObject(rs);
            callback.onInvoke(bs);
            return;
        }
        Request req = OkGo.<String>get(routerUrl);
        req.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                String rs = response.body();
                CacheManager.cache(frouterUrl,rs.getBytes(),modulename,context);
                Module module=  getModule(modulename);
                module.navDic=com.alibaba.fastjson.JSONObject.parseObject(rs);
                callback.onInvoke(bs);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                callback.onInvoke(null);
            }
        });

    }



    public static String loadLocalJs(String url,Context context, final Module module){
        String bs=  getLocalJs(context,module,url);
       if(module.appboard ==null){
           String appboardPath="modules/"+module.name+"/mixin/appBoard.js";
           module.appboard= SDCard.getString(context,appboardPath);
           if(module.appboard==null){
               module.appboard=  WXFileUtils.loadAsset(appboardPath,context);
           }
      }
       if(module.navDic==null){
           module.navDic=  Config.routerTranlater(context,module.name);
       }
        bs = module.appboard + bs;
       return bs;


    }


    public static String getLocalJs(Context context,Module module,String path){
        String bs= SDCard.getString(context,"modules/"+module.name+"/"+path);
        if(StringUtil.isNullOrEmpty(bs)){
            bs=  WXFileUtils.loadAsset("modules/"+module.name+"/"+path,context);
        }
        return bs;
    }



    public static String jump(final String url , final String rootid, final JSONObject param, final boolean isEntry,final boolean ispotrait, final boolean preRender, final boolean isPush, final Context context, final String module,final boolean showLoading,final String callbackId,final Callback callback){

        final Page p=new Page();
        p.id=url;
        if(p.id==null)
            p.id=new Random().nextLong()+"";


        Class cls= WxpActivity.class;
        if(!ispotrait){
            cls= WxpLanscapeActivity.class;
        }else{
            if(!isPush){
                cls= WxpPresentActivity.class;
            }
        }
        if(isEntry){
            cls= WxpEntryActivity.class;
        }
        String pageId=System.currentTimeMillis()+"";

        final Intent intent=new Intent(context, cls);
        intent.putExtra("url",url);
        intent.putExtra("module",module);
        intent.putExtra("param",param);
        intent.putExtra("pageId",pageId);
        if(preRender)
        intent.putExtra("id",p.id);
        if(rootid!=null)
        intent.putExtra("rootId",rootid);
        if(callbackId!=null)
            intent.putExtra("callbackId",callbackId);

        intent.putExtra("isPortrait",ispotrait);
        if(!preRender){
            context.startActivity(intent);
            return pageId;
        }

        pageCache.put(p.id,p);
        renderByUrl(url,module,param,null,context,showLoading,new IWXRenderListener(){

            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {
                p.view=view;
                p.instance=(WxpInstance) instance;
                p.instance.hasInit=true;
                p.instance.module=WeexRender.getModule(module);
                intent.putExtra("module", p.instance.module.name);
                WindowManager wm =  (WindowManager)context
                        .getSystemService(Context.WINDOW_SERVICE);
                if(ispotrait)
                    p.instance.setSize(wm.getDefaultDisplay().getWidth(),getDisplayScreenHeight(context));
                else
                    p.instance.setSize(wm.getDefaultDisplay().getHeight(),wm.getDefaultDisplay().getWidth());

                context.startActivity(intent);
                if(callback!=null){
                    callback.onInvoke(null);
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
        return pageId;

    }

    public static FreeDialog showLoading(Context context){
            FreeDialog  f;
            if(context instanceof Activity){
                Activity a= (Activity)context;
                if(a==null||a.isFinishing())
                    return null;
            }
            LoadingDialog  progress =new LoadingDialog(context);
            f=new FreeDialog(context,progress);
            f.setCancelable(new AppTool(context).isDebugMode());
            f.setCanceledOnTouchOutside(new AppTool(context).isDebugMode());
            progress.f=f;
            progress.setStyle(15);
            progress.setBgColor("#000000",75);
            progress.setSize(100);
            f.show();
            return f;
    }

    public static int getDisplayScreenHeight(Context context)
    {
//        int realWidth = 0;//宽
        int realHeight = 0;//高
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            Point size = new Point();
            display.getRealSize(size);
//            realWidth = size.x;
            realHeight = size.y;
        } else if (android.os.Build.VERSION.SDK_INT < 17
                && android.os.Build.VERSION.SDK_INT >= 14) {
            try {
                Method mGetRawH =Display.class.getMethod("getRawHeight");
//                Method mGetRawW = Display.class.getMethod("getRawWidth");
//                realWidth = (Integer) mGetRawW.invoke(display);
                realHeight = (Integer) mGetRawH.invoke(display);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
//            realWidth = metrics.widthPixels;
            realHeight = metrics.heightPixels;
        }
        return realHeight;
    }
}
