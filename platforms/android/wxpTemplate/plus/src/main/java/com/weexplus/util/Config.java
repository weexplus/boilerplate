package com.weexplus.util;

import android.content.Context;


import com.weexplus.core.render.Local;

import org.json.JSONObject;


/**
 * Created by zhengjiangrong on 2017/12/7.
 */

public class Config {





    public static JSONObject config(Context c,String module) {
        String s = Local.getString(c,"modules/"+module+"/weexplus.json");


        try {
            JSONObject j = new JSONObject(s);
            return j;
        } catch (Exception var3) {
            return null;
        }
    }

    public static  com.alibaba.fastjson.JSONObject routerTranlater(Context c,String module) {
        String s = Local.getString(c,"modules/"+module+"/router-translator.json");
        try {
            return com.alibaba.fastjson.JSONObject.parseObject(s);
        } catch (Exception var3) {
            return null;
        }
    }


    public static JSONObject assetConfig(Context c,String module) {
        String s =Local.getAssetManager(c).getString(c,"modules/"+module+"/weexplus.json");

        try {
            JSONObject j = new JSONObject(s);
            return j;
        } catch (Exception var3) {
            return new  JSONObject();
        }
    }

    public static JSONObject diskConfig(Context c,String module) {


        String s =Local.getDiskManager(c).getString(c,"modules/"+module+"/weexplus.json");

        try {
            JSONObject j = new JSONObject(s);
            return j;
        } catch (Exception var3) {
            return  new  JSONObject();
        }
    }





    public static String schema(Context c)
    {
        return config(c,Const.MAIN).optString("schema");
    }
    public static int jsVersion(Context c)
    {
        return config(c,Const.MAIN).optInt("jsVersion");
    }

    public static int assetJsVersion(Context c)
    {
        return  assetConfig(c,Const.MAIN).optInt("jsVersion");
    }

    public static int diskJsVersion(Context c)
    {
        return  diskConfig(c,Const.MAIN).optInt("jsVersion");
    }

    public static boolean debug(Context c)
    {
        return assetConfig(c,Const.MAIN).optBoolean("debug");
    }

    public static boolean showError(Context c)
    {
        return config(c,Const.MAIN).optBoolean("showerror");
    }


    public static boolean isPortrait(Context c)
    {
        return config(c,Const.MAIN).optBoolean("isPortrait");
    }

    public static String socketPort(Context c)
    {
        return config(c,Const.MAIN).optString("socketPort","9897");
    }


    public static String debugIp(Context c)
    {
        return config(c,Const.MAIN).optString("debugIp");
    }

    public static String entry(Context c)
    {
        return config(c,Const.MAIN).optString("entry");
    }

    public static String debugEntry(Context c)
    {
        return config(c,Const.MAIN).optString("debugEntry");
    }

    public static String releaseEntry(Context c)
    {
        return config(c,Const.MAIN).optString("releaseEntry");
    }

    public static String notifyEntry(Context c)
    {
        return config(c,Const.MAIN).optString("notifyEntry");
    }


    public static String wechatEntry(Context c)
    {
        return config(c,Const.MAIN).optString("wechatEntry");
    }

    public static String appBoard(Context c)
    {
        return config(c,Const.MAIN).optString("appBoard");
    }

    public static String splash(Context c)
    {
        return config(c,Const.MAIN).optString("splash");
    }

    public static String getUMAndroid(Context c)
    {
        return config(c,Const.MAIN).optJSONObject("static").optJSONObject("umeng").optJSONObject("android").optString("appkey");
    }

    public static String getUMIOS(Context c)
    {
        return config(c,Const.MAIN).optJSONObject("static").optJSONObject("umeng").optJSONObject("ios").optString("appkey");
    }





}
