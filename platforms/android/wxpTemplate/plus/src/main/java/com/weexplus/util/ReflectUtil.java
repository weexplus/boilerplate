package com.weexplus.util;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtil {



    public static void  setInt(Class cls,String name,int v,Object j){
        try {
            Field f = null;
            f = cls.getDeclaredField(name);
            f.setAccessible(true);
            f.setInt(j,v);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void  setFloat(Class cls,String name,float v,Object j){
        try {
            Field f = null;
            f = cls.getDeclaredField(name);
            f.setAccessible(true);
            f.setFloat(j,v);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void  setDouble(Class cls,String name,double v,Object j){
        try {
            Field f = null;
            f = cls.getDeclaredField(name);
            f.setAccessible(true);
            f.setDouble(j,v);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void  setObject(Class cls,String name,String v,Object j){
        try {
            Field f = null;
            f = cls.getDeclaredField(name);
            f.setAccessible(true);
            f.set(j,v);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static String get(Class cls,String name,Object j){
        try {
            Field f = null;
            f = cls.getDeclaredField(name);
            f.setAccessible(true);
            return f.get(j)+"";
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }




}
