package com.farwolf.weexplus;

import com.farwolf.jpush.JPushUtil;
import com.farwolf.weex.app.WeexApplication;
import com.farwolf.weexplus.adapter.MyAdapter;

import org.androidannotations.annotations.EApplication;

/**
 * Created by zhengjiangrong on 2017/5/18.
 */
@EApplication
public class MVApplication extends WeexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        JPushUtil.init(this,MyAdapter.class);
    }
}
