package com.farwolf.weexplus;


import com.farwolf.weex.app.WeexApplication;

import org.androidannotations.annotations.EApplication;

/**
 * Created by zhengjiangrong on 2017/5/18.
 */
@EApplication
public class MVApplication extends WeexApplication {


    @Override
    public void onCreate() {
        super.onCreate();

//        NIMClient.init(this, null, options(context));
//        NimUIKit.init(context);

    }
}
