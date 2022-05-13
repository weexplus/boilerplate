package com.weexplus.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {


    public static void setInt(Context context,String key,int value){
        SharedPreferences sharedPreferences =  getSharedPreferences(context);//私有数据
        sharedPreferences.edit().putInt(key,(int)value).commit();

    }
    public static void setLong(Context context,String key,long value){
        SharedPreferences sharedPreferences =  getSharedPreferences(context);//私有数据
        sharedPreferences.edit().putLong(key,value).commit();

    }
    public static void setString(Context context,String key,String value){
        SharedPreferences sharedPreferences =  getSharedPreferences(context);//私有数据
        sharedPreferences.edit().putString(key, value).commit();
    }
    public static SharedPreferences getSharedPreferences(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("farwolf_weex", Context.MODE_PRIVATE); //私有数据
        return sharedPreferences;
    }


    public static int getInt(Context context,String key,int defaul){
        SharedPreferences sharedPreferences =  getSharedPreferences(context);//私有数据
       return sharedPreferences.getInt(key,defaul);
    }

    public static String getString(Context context,String key,String defaul){
        SharedPreferences sharedPreferences =  getSharedPreferences(context);//私有数据
        return sharedPreferences.getString(key,defaul);
    }

    public static boolean getBoolean(Context context,String key,boolean defaul){
        SharedPreferences sharedPreferences =  getSharedPreferences(context);//私有数据
        return sharedPreferences.getBoolean(key,defaul);
    }

    public static float getFloat(Context context,String key,float defaul){
        SharedPreferences sharedPreferences =  getSharedPreferences(context);//私有数据
        return sharedPreferences.getFloat(key,defaul);
    }

    public static float getLong(Context context,String key,long defaul){
        SharedPreferences sharedPreferences =  getSharedPreferences(context);//私有数据
        return sharedPreferences.getLong(key,defaul);
    }


}
