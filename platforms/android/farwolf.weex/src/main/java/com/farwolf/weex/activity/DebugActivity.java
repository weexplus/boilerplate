package com.farwolf.weex.activity;

import com.farwolf.weex.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

/**
 * Created by zhengjiangrong on 2017/11/8.
 */
@EActivity
public class DebugActivity  extends WeexActivity {




    @Override
    public void init() {

        url=pref.url().get();
//        render("http://172.16.103.113:9898/mv/SignIn.js");
//        render("http://172.16.103.113:9898/mv/demo/demo/net.js");
        render("http://192.168.12.221:9898/mv/demo/demo/url.js");

//        final Handler achandler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                render(url);
//            }
//        };
//        achandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                achandler.sendEmptyMessage(0);
//            }
//        }, 500);




    }

    @Click
    public void setClicked() {
        showTool();

    }


    @Override
    public int getViewId() {
        return R.layout.debug_activity;
    }


    @Click
    public void refreshClicked() {
        render(url);

    }
}
