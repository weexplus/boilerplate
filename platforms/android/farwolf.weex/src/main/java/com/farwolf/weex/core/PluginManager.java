package com.farwolf.weex.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.farwolf.weex.annotation.ModuleEntry;
import com.farwolf.weex.annotation.WeexComponent;
import com.farwolf.weex.annotation.WeexModule;
import com.taobao.weex.WXSDKEngine;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

public class PluginManager {

    public static List<String> packages=new ArrayList<>();
    public static void add(String pa){
//        Log.e("scanx",packages.size()+"");
        packages.add(pa);
    }

    public static ArrayList<DexFile> getMultiDex()
    {
        BaseDexClassLoader dexLoader = (BaseDexClassLoader) getClassLoader();
        Field f = getField("pathList", getClassByAddressName("dalvik.system.BaseDexClassLoader"));
        Object pathList = getObjectFromField(f, dexLoader);
        Field f2 = getField("dexElements", getClassByAddressName("dalvik.system.DexPathList"));
        Object[] list = getObjectFromField(f2, pathList);
        Field f3 = getField("dexFile", getClassByAddressName("dalvik.system.DexPathList$Element"));

        ArrayList<DexFile> res = new ArrayList<>();

        for(int i = 0; i < list.length; i++)
        {
            DexFile d = getObjectFromField(f3, list[i]);
            res.add(d);
        }

        return res;
    }

    public static Field getField(String name,Class c){
        try{

            return c.getDeclaredField(name);
        }
        catch (Exception e){
            return null;
        }

    }

    public static ClassLoader getClassLoader()
    {
        return Thread.currentThread().getContextClassLoader();
    }


    public static Class<?> getClassByAddressName(String classAddressName)
    {
        Class mClass = null;
        try
        {
            mClass = Class.forName(classAddressName);
        } catch(Exception e)
        {
        }
        return mClass;
    }


    public static <T extends Object> T getObjectFromField(Field field, Object arg)
    {
        try
        {
            field.setAccessible(true);
            return (T) field.get(arg);
        } catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void scan(Context ctx)
    {
        ArrayList<DexFile> list=getMultiDex();
        for(DexFile dex:list){
            regist(dex,ctx);
        }
    }

    public static List<Class<?>> scan(Context ctx,String entityPackage) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        try {
            PathClassLoader classLoader = (PathClassLoader) Thread
                    .currentThread().getContextClassLoader();
            String path=ctx.getPackageResourcePath();
            DexFile dex = new DexFile(ctx.getPackageResourcePath());
            Enumeration<String> entries = dex.entries();
            List m=new ArrayList();
            int c=0;
            while (entries.hasMoreElements()) {
                String entryName = entries.nextElement();
                if(entryName.contains("module")){
                    Log.e("xxxx",entryName+"");
                }
                if (canLoad(entryName)) {
                    c++;
                    Log.e("packageCount",entryName+"");
                    try {
                        Class entryClass = Class.forName(entryName, true, classLoader);//疑问：<span style="font-size: 1em; line-height: 1.5;">Class.forName(entryName);这种方式不知道为什么返回null，哪位大神知道原因，请指点一下小弟吧  感激不尽</span>
                        WeexModule wxmodlue = (WeexModule) entryClass.getAnnotation(WeexModule.class);

                        if (wxmodlue != null) {
                            WXSDKEngine.registerModule(wxmodlue.name(), entryClass);
                            Log.i("farwolf", "注册module:" + wxmodlue.name() + "=" + entryClass);
                            continue;
                        }

                        WeexComponent wxcomponent = (WeexComponent) entryClass.getAnnotation(WeexComponent.class);
                        if (wxcomponent != null) {
                            WXSDKEngine.registerComponent(wxcomponent.name(), entryClass);
                            Log.i("farwolf", "注册component:" + wxcomponent.name() + "=" + entryClass);
                            continue;
                        }

                        ModuleEntry wxentry = (ModuleEntry) entryClass.getAnnotation(ModuleEntry.class);
                        if (wxentry != null) {
                            Object o = entryClass.newInstance();
                            Method me = entryClass.getMethod("init",Context.class);
                            me.invoke(o, ctx);
                            Log.i("farwolf", "执行模块初始化:" + entryClass);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (NoClassDefFoundError ex) {
                        ex.printStackTrace();
                    }
                }
            }
            Log.e("packageCount",c+"");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    public static void regist(DexFile dex,Context ctx){
        if(dex==null)
            return;
        Enumeration<String> entries = dex.entries();
        List m=new ArrayList();
        int c=0;
        PathClassLoader classLoader = (PathClassLoader) Thread
                .currentThread().getContextClassLoader();
        while (entries.hasMoreElements()) {
            String entryName = entries.nextElement();
            if(entryName.contains("module")){
                Log.e("xxxx",entryName+"");
            }
            if (canLoad(entryName)) {
                c++;
                Log.e("packageCount",entryName+"");
                try {
                    Class entryClass = Class.forName(entryName, true, classLoader);//疑问：<span style="font-size: 1em; line-height: 1.5;">Class.forName(entryName);这种方式不知道为什么返回null，哪位大神知道原因，请指点一下小弟吧  感激不尽</span>
                    WeexModule wxmodlue = (WeexModule) entryClass.getAnnotation(WeexModule.class);

                    if (wxmodlue != null) {
                        WXSDKEngine.registerModule(wxmodlue.name(), entryClass);
                        Log.i("farwolf", "注册module:" + wxmodlue.name() + "=" + entryClass);
                        continue;
                    }

                    WeexComponent wxcomponent = (WeexComponent) entryClass.getAnnotation(WeexComponent.class);
                    if (wxcomponent != null) {
                        WXSDKEngine.registerComponent(wxcomponent.name(), entryClass);
                        Log.i("farwolf", "注册component:" + wxcomponent.name() + "=" + entryClass);
                        continue;
                    }

                    ModuleEntry wxentry = (ModuleEntry) entryClass.getAnnotation(ModuleEntry.class);
                    if (wxentry != null) {
                        Object o = entryClass.newInstance();
                        Method me = entryClass.getMethod("init",Context.class);
                        me.invoke(o, ctx);
                        Log.i("farwolf", "执行模块初始化:" + entryClass);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (NoClassDefFoundError ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static boolean canLoad(String pack){
        List ignore=new ArrayList();
        ignore.add("com.taobao.weex");
        ignore.add("com.alipay.security");
        ignore.add("com.bumptech.glide");
        ignore.add("$");
        if(contains(ignore,pack))
            return false;

        List need=new ArrayList();
        need.add("module");
        need.add("component");
        need.add("init");
        return contains(need,pack);

    }

    public static boolean contains(List l,String s){
        for(Object q:l){
            if(s.contains(q+""))
                return true;
        }
        return false;
    }

    public static void op(Context context){
        try {

//获取包管理器

            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(

                    context.getPackageName(), PackageManager.GET_META_DATA);

            Boolean flag = (Boolean) info.metaData.get("isUpdate");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static String getHead(String v){
        String q[]=  v.split("\\.");
        if(q.length<2)
            return q[0];
        return q[0]+"."+q[1];
    }


    public static void init(Context context){
//        Log.e("scan",packages.size()+"");
        scan(context);
//        scan(context,"");
    }

}
