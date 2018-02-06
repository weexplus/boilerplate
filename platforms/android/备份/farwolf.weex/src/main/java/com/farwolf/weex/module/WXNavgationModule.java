package com.farwolf.weex.module;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.farwolf.weex.activity.PresentActivity_;
import com.farwolf.weex.activity.WeexActivity;
import com.farwolf.weex.activity.WeexActivity_;
import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.core.WeexFactory_;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

/**
 * Created by zhengjiangrong on 2017/5/17.
 */

public class WXNavgationModule extends WXModuleBase {


    HashMap<String,JSCallback>callbacks=new  HashMap<String,JSCallback>();

    public static HashMap<String,Stack<Activity>>stacks=new  HashMap<String,Stack<Activity>>();

    @JSMethod
    public void push(String url)
    {

        this.pushFull(url,null,null,true);
    }

    public static void addActivity(String rootid,Activity a)
    {
        if(rootid!=null)
        {
            Stack<Activity> s= stacks.get(rootid);
            if(s!=null)
                s.push(a);
        }

    }

    public static void pop(String rootid)
    {
        if(rootid!=null)
        {
            Stack<Activity> s= stacks.get(rootid);
            if(s!=null&&!s.isEmpty())
                s.pop();
        }

    }

    @JSMethod
    public void pushParam(String url,HashMap param )
    {

        this.pushFull(url,param,null,true);
    }


    @JSMethod
    public void pushFull(String url, HashMap param, JSCallback callback, boolean animate)
    {


        this.goNext(url,param,callback,WeexActivity_.class,false);



    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode==10001)
        {
            if(resultCode==10002)
            {
                String id=  data.getStringExtra("callbackid");
                Map m= (Map)data.getSerializableExtra("res");
                if(id!=null)
                {
                    JSCallback j=  callbacks.get(id);
                    callbacks.remove(id);
                    if(j!=null)
                    {
                        j.invoke(m);
                    }

                }

            }

        }


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

    public void openOther(String url)
    {

    }

    @JSMethod
    public void setPageId(String id)
    {
        WeexActivity a=  (WeexActivity)this.mWXSDKInstance.getContext();
        a.pageid=id;
    }

    @JSMethod(uiThread = false)
    public HashMap param()
    {
        return this.mWXSDKInstance.param;
    }


    @JSMethod
    public void backTo(String id)
    {

        WeexActivity a=  (WeexActivity)this.mWXSDKInstance.getContext();
        Stack<Activity> s=  stacks.get(a.rootid);
        if(s==null||s.isEmpty())
            return;
        WeexActivity wa= (WeexActivity)s.peek();
        while(wa!=null)
        {
            if(id.equals(wa.pageid))
            {
                break;
            }
            else
            {
                wa.finish();

            }
            if(!s.isEmpty())
                wa= (WeexActivity)s.peek();
            else
            {
                break;
            }
        }

    }


    @JSMethod
    public void present(String url)
    {
        this.presentFull(url,null,null,true);
    }

    @JSMethod
    public void presentFull(String url, HashMap param, JSCallback callback,boolean animate)
    {
        this.goNext(url,param,callback,PresentActivity_.class,true);

    }


    public void goNext(String url,HashMap param,JSCallback callback,Class c,boolean isroot )
    {



        WeexFactory w= WeexFactory_.getInstance_(mWXSDKInstance.getContext());
        Intent in=new Intent(mWXSDKInstance.getContext(),c);
        Activity a=  (Activity)this.mWXSDKInstance.getContext();
        in.putExtra("param",param);
//        in.putExtra("navbarVisibility",navbarVisibility);
        if(!isroot)
        {
            WeexActivity  wa=  (WeexActivity)a;
            if(wa!=null)
                in.putExtra("rootid",wa.rootid);
        }


            url=  Weex.getRelativeUrl(url,this.mWXSDKInstance);
        in.putExtra("url",url);
        if(callback!=null)
        {
            String id= new Random().nextLong()+"";
            callbacks.put(id,callback);
            in.putExtra("callbackid",id);
            w.jump(url,in,true);
            return;
        }
        w.jump(url,in,callback!=null);


    }

    @JSMethod
    public void addBackGestureSelfControl()
    {

    }

    public void finish(HashMap param)
    {
        WeexActivity a=  (WeexActivity)this.mWXSDKInstance.getContext();
        String id= a.getIntent().getStringExtra("callbackid");

        if(id!=null)
        {
            Intent in=new Intent();
            in.putExtra("callbackid",id);
            in.putExtra("res",param);
            a.setResult(10002,in);

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
        WeexActivity a=  (WeexActivity)this.mWXSDKInstance.getContext();
        a.rootid=id;
        Stack<Activity> stack=  new Stack<Activity>();
        stack.add(a);
        stacks.put(a.rootid,stack);
        a.isRoot=true;
    }


    public  static HashMap<String,JSCallback> static_callbacks=new  HashMap<String,JSCallback>();

    @JSMethod
    public void invokeNativeCallBack(HashMap param)
    {
        WeexActivity a=  (WeexActivity)this.mWXSDKInstance.getContext();
        String id= a.getIntent().getStringExtra("callbackid");
        JSCallback callback= static_callbacks.get(id);
        callback.invoke(param);
//        static_callbacks.remove(id);


    }


    @JSMethod
    public void dismissFull(HashMap param,boolean animate)
    {
        finish(param);
    }



    private   List<Activity> getActivityStack(Application application) {
        List<Activity> list = new ArrayList<>();
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
