package com.farwolf.weex.pref;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by zhengjiangrong on 2017/5/8.
 */
@SharedPref(value= SharedPref.Scope.APPLICATION_DEFAULT)
public interface WeexPref {

    String ip();
    String socketPort();
    String url();
    int lastX();
    int lastY();
}
