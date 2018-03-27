package com.farwolf.update;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by zhengjiangrong on 2017/4/12.
 */
@SharedPref(value= SharedPref.Scope.APPLICATION_DEFAULT)
public interface UpdatePref {

    String version();

    long time();
}
