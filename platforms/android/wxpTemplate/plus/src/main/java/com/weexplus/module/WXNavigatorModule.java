
package com.weexplus.module;

import android.app.Activity;
import android.app.Application;

import com.alibaba.fastjson.JSONObject;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.weexplus.activity.WxpActivity;
import com.weexplus.core.render.WeexRender;
import com.weexplus.util.Const;
import com.weexplus.util.Path;
import com.weexplus.util.StringUtil;
import com.weexplus.util.WXModuleBase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by zhengjiangrong on 2017/5/17.
 */

public class WXNavigatorModule extends WXModuleBase {


    public static     HashMap<String,HashMap<String, HashMap<String,JSCallback>>>  callbacks=new   HashMap<String,HashMap<String, HashMap<String,JSCallback>>> ();

    public static HashMap<String,HashMap<String,Stack<Activity>>>  stacks=new  HashMap<String,HashMap<String,Stack<Activity>>>();

    @JSMethod
    public void push(String url)
    {

        HashMap pa=new HashMap();
        pa.put("url",url);
        pa.put("param",null);
        pa.put("isPortrait",true);
        this.pushFull(pa,null);
    }

    public static void addActivity(String rootid,String module,Activity a)
    {
        if(rootid!=null)
        {
            HashMap<String,Stack<Activity>> stacks=WXNavigatorModule.stacks.get(module);
            if(stacks==null){
                stacks=new  HashMap<String,Stack<Activity>>();
                WXNavigatorModule.stacks.put(module,stacks);
            }
            Stack<Activity> s= stacks.get(rootid);
            if(s!=null)
                s.push(a);
        }

    }

    public static void pop(String rootid,String module)
    {
        if(rootid!=null)
        {
            HashMap<String,Stack<Activity>> stacks=WXNavigatorModule.stacks.get(module);
            Stack<Activity> s= stacks.get(rootid);
            if(s!=null&&!s.isEmpty())
                s.pop();
        }

    }

    @JSMethod
    public void pushParam(String url,JSONObject param )
    {
        HashMap pa=new HashMap();
        pa.put("url",url);
        pa.put("param",param);
        pa.put("isPortrait",true);
        this.pushFull(pa,null);
    }

    @JSMethod
    public void pushFull(HashMap parameters, JSCallback callback)
    {

        String url=parameters.get("url")+"";
        JSONObject param= (JSONObject)parameters.get("param");
        boolean isPortrait= true;
        if(parameters.containsKey("isPortrait")){
            isPortrait= Boolean.parseBoolean(parameters.get("isPortrait")+"");
        }
        boolean preload= true;
        if(parameters.containsKey("preload")){
            preload= Boolean.parseBoolean(parameters.get("preload")+"");
        }
        boolean showLoading= true;
        if(parameters.containsKey("showLoading")){
            showLoading= Boolean.parseBoolean(parameters.get("showLoading")+"");
        }

        String module= getWxpInstance().module.name;
        if(parameters.containsKey("module")){
            module=parameters.get("module")+"";
        }
        if(StringUtil.isNullOrEmpty(module)){
            module=Const.MAIN;
        }
        this.goNext(url,param,module,callback, true,false,isPortrait,preload,showLoading);

    }




    @JSMethod
    public void back()
    {
        this.backFull(null,true);

    }


    @JSMethod
    public void backFull(HashMap param,boolean animate )
    {
        finish(param);

    }



    @JSMethod
    public void setPageId(String id)
    {
        WxpActivity a=  (WxpActivity)this.mWXSDKInstance.getContext();
        String pageId=a.pageId;
        HashMap<String, HashMap<String,JSCallback>> callbacks=WXNavigatorModule.callbacks.get(a.module);
        if(StringUtil.isNullOrEmpty(pageId)){
            HashMap m= callbacks.get(pageId);
            callbacks.remove(pageId);
            if(m!=null)
            callbacks.put(id,m);
        }
        if(a!=null)
            a.pageId=id;
        setRoot("main");
    }

    @JSMethod(uiThread = false)
    public Map param()
    {
        return getWxpInstance().param;
    }


    @JSMethod
    public void backTo(String id,JSONObject param)
    {

        WxpActivity a=  (WxpActivity)this.mWXSDKInstance.getContext();
        HashMap<String,Stack<Activity>> stacks=WXNavigatorModule.stacks.get(a.module);
        Stack<Activity> s=  stacks.get(a.rootId);
        if(s==null||s.isEmpty())
            return;
        WxpActivity wa= (WxpActivity)s.peek();
        String callbackId= wa.getIntent().getStringExtra("callbackId");
        HashMap<String, HashMap<String,JSCallback>> callbacks=WXNavigatorModule.callbacks.get(a.module);
        HashMap<String,JSCallback> page=callbacks.get(wa.pageId);
        while(wa!=null)
        {
            if(id.equals(wa.pageId))
            {
                if(param!=null){

                    if(callbackId!=null){
                        JSCallback callback= page.get(callbackId);
                        if(callback!=null){
                            callback.invoke(param);
                        }
                    }
                }
                break;
            }
            else
            {
                wa.finish();

            }
            if(!s.isEmpty()){
                wa= (WxpActivity)s.peek();
                if(!id.equals(wa.pageId)){
                    page= callbacks.get(wa.pageId);
                    callbackId= wa.getIntent().getStringExtra("callbackId");
                }

            }
            else
            {
                break;
            }
        }

    }


    @JSMethod
    public void present(String url)
    {
        JSONObject pa=new JSONObject();
        pa.put("url",url);
        pa.put("param",null);
        pa.put("isPortrait",true);
        this.presentFull(pa,null);
    }

    @JSMethod
    public void presentParam(String url,JSONObject param )
    {
        JSONObject pa=new JSONObject();
        pa.put("url",url);
        pa.put("param",param);
        pa.put("isPortrait",true);
        this.presentFull(pa,null);
    }

    @JSMethod
    public void presentFull( JSONObject parameters, JSCallback callback )
    {
        String url=parameters.get("url")+"";
        JSONObject param= (JSONObject)parameters.get("param");
        boolean isPortrait= true;
        if(parameters.containsKey("isPortrait")){
            isPortrait= Boolean.parseBoolean(parameters.get("isPortrait")+"");
        }
        boolean preload= true;
        if(parameters.containsKey("preload")){
            preload= Boolean.parseBoolean(parameters.get("preloadpreload")+"");
        }
        String module= Const.MAIN;
        if(parameters.containsKey("module")){
            module=parameters.get("module")+"";
        }
        boolean showLoading= true;
        if(parameters.containsKey("showLoading")){
            showLoading= Boolean.parseBoolean(parameters.get("showLoading")+"");
        }
        this.goNext(url,param,module,callback,false,true,isPortrait,preload,showLoading);

    }


    public void goNext(String url, JSONObject param,String module, JSCallback callback, boolean isPush, boolean isroot, boolean isPortrait, boolean preload ,boolean showLoading)
    {
        url= Path.getUrl(url,getWxpInstance().getBundleUrl(),module);
        if(!url.startsWith(Const.HTTP)){
            url=Path.getPath(url,module);
        }
        String rootId=getActivity().rootId;
        String callbackId=System.currentTimeMillis()+"";
        WxpActivity a=  (WxpActivity)this.mWXSDKInstance.getContext();
        String pageId=  WeexRender.jump(url,rootId,param,false,isPortrait,preload,isPush,getContext(),module,showLoading,callbackId,null);
        if(callback!=null){
            HashMap m=new HashMap();
            m.put(callbackId,callback);
            HashMap<String, HashMap<String,JSCallback>> callbacks=WXNavigatorModule.callbacks.get(a.module);
            if(callbacks==null){
                callbacks=new HashMap<String, HashMap<String,JSCallback>>();
                WXNavigatorModule.callbacks.put(module,callbacks);
            }
            callbacks.put(pageId,m);
        }
    }

    @JSMethod
    public void enableBackGesture()
    {

    }

    public void finish(HashMap param)
    {
        WxpActivity a=  (WxpActivity)this.mWXSDKInstance.getContext();
        String id= a.getIntent().getStringExtra("callbackId");
        HashMap<String, HashMap<String,JSCallback>> callbacks=WXNavigatorModule.callbacks.get(a.module);
        HashMap<String,JSCallback> page=  callbacks.get(a.pageId);
        if(page!=null){
            JSCallback callback=page.get(id);
            if(callback!=null){
                page.remove(id);
            }
            if(page.size()==0){
                callbacks.remove(a.pageId);
            }
            if(id!=null&&param!=null&&param.size()>0)
            {
                if(callback!=null){
                    callback.invoke(param);
                }
            }
        }
        a.finish();
    }

    @JSMethod
    public void dismiss()
    {
        this.dismissFull(null,true);
    }


    @JSMethod
    public void setRoot(String id)
    {
        WxpActivity a=  (WxpActivity)this.mWXSDKInstance.getContext();
        a.rootId=id;
        HashMap<String,Stack<Activity>> stacks=WXNavigatorModule.stacks.get(a.module);
        if(stacks==null){
            stacks=new    HashMap<String,Stack<Activity>>();
            WXNavigatorModule.stacks.put(a.module,stacks);
        }
        if(stacks.containsKey(id)){
             Stack<Activity> stack=  stacks.get(id);
             if(stack.search(a)==-1){
                 stack.add(a);
             }
        }else{
            Stack<Activity> stack=  new Stack<Activity>();
            stack.add(a);
            stacks.put(a.rootId,stack);
            a.isRoot=true;
        }


    }


    public  static HashMap<String, JSCallback> static_callbacks=new  HashMap<String, JSCallback>();

    @JSMethod
    public void invokeNativeCallBack(HashMap param)
    {
        WxpActivity a=  (WxpActivity)this.mWXSDKInstance.getContext();
        String id= a.getIntent().getStringExtra("callbackId");
        JSCallback callback= static_callbacks.get(id);
        callback.invoke(param);
//        static_callbacks.remove(id);


    }






    @JSMethod
    public void dismissFull(HashMap param,boolean animate)
    {
        finish(param);
    }


    @Override
    public void onActivityDestroy() {
        super.onActivityDestroy();


    }

    public static void removeCallback(String module,String pageId){
        HashMap<String, HashMap<String,JSCallback>> callbacks=WXNavigatorModule.callbacks.get(module);
        if(callbacks!=null)
        callbacks.remove(pageId);
    }

    private   List<Activity> getActivityStack(Application application) {
        List<Activity> list = new ArrayList<Activity>();
        try {
            Class<Application> applicationClass = Application.class;
            Field mLoadedApkField = applicationClass.getDeclaredField("mLoadedApk");
            mLoadedApkField.setAccessible(true);
            Object mLoadedApk = mLoadedApkField.get(application);
            Class<?> mLoadedApkClass = mLoadedApk.getClass();
            Field mActivityThreadField = mLoadedApkClass.getDeclaredField("mActivityThread");
            mActivityThreadField.setAccessible(true);
            Object mActivityThread = mActivityThreadField.get(mLoadedApk);
            Class<?> mActivityThreadClass = mActivityThread.getClass();
            Field mActivitiesField = mActivityThreadClass.getDeclaredField("mActivities");
            mActivitiesField.setAccessible(true);
            Object mActivities = mActivitiesField.get(mActivityThread);
            // 注意这里一定写成Map，低版本这里用的是HashMap，高版本用的是ArrayMap
            if (mActivities instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<Object, Object> arrayMap = (Map<Object, Object>) mActivities;
                for (Map.Entry<Object, Object> entry : arrayMap.entrySet()) {
                    Object value = entry.getValue();
                    Class<?> activityClientRecordClass = value.getClass();
                    Field activityField = activityClientRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Object o = activityField.get(value);
                    list.add((Activity) o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

}
