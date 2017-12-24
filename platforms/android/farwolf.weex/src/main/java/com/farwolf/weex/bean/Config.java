package com.farwolf.weex.bean;

import android.content.Context;

import com.farwolf.json.JsonTool;
import com.taobao.weex.utils.WXFileUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjiangrong on 2017/12/7.
 */

public class Config {





    public static JSONObject config(Context c) {
        String s = WXFileUtils.loadAsset("app/config.json", c);

        try {
            JSONObject j = new JSONObject(s);
            return j;
        } catch (Exception var3) {
            return null;
        }
    }



    public static String schema(Context c)
    {
        return config(c).optString("schema");
    }


    public static boolean debug(Context c)
    {
        return config(c).optBoolean("debug");
    }


    public static String socketPort(Context c)
    {
        return config(c).optString("socketPort","9897");
    }


    public static String debugIp(Context c)
    {
        return config(c).optString("debugIp");
    }

    public static String entry(Context c)
    {
        return config(c).optString("entry");
    }


    public static String splash(Context c)
    {
        return config(c).optString("splash");
    }

    public static String getUMAndroid(Context c)
    {
        return config(c).optJSONObject("static").optJSONObject("umeng").optJSONObject("android").optString("appkey");
    }

    public static String getUMIOS(Context c)
    {
        return config(c).optJSONObject("static").optJSONObject("umeng").optJSONObject("ios").optString("appkey");
    }


    public  static Platform getPlatformWX(Context c)
    {
        JSONObject  j= config(c).optJSONObject("platform").optJSONObject("wx");
        return JsonTool.toBean(j,Platform.class);

    }
    public  static Platform getPlatformQQ(Context c)
    {
        JSONObject  j= config(c).optJSONObject("platform").optJSONObject("qq");
        return JsonTool.toBean(j,Platform.class);

    }
    public  static Platform getPlatformWeibo(Context c)
    {
        JSONObject  j= config(c).optJSONObject("platform").optJSONObject("weibo");
        return JsonTool.toBean(j,Platform.class);

    }

    public static List<String> preload(Context c)
    {
        List<String> l=new ArrayList<>();
        JSONArray j= config(c).optJSONArray("preload");
        for(int i=0;i<j.length();i++)
        {
             l.add(j.optString(i));
        }
        return l;
    }


}
