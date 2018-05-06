package com.farwolf.util;

import android.Manifest;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2018/5/4.
 */

public class PerssionUtil {

       public  static String getPerssionName(String p)
       {
           HashMap m=new HashMap();
           m.put(Manifest.permission.CALL_PHONE,"电话");
           m.put(Manifest.permission.READ_PHONE_STATE,"电话");
           m.put(Manifest.permission.CAMERA,"相机");
           m.put(Manifest.permission.GET_ACCOUNTS,"联系人");
           if(m.containsKey(p))
               return m.get(p)+"";
           return "某些";

       }


}
