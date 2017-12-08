package com.farwolf.vshop.activity.tab;

import android.os.Bundle;

import com.farwolf.movie.R;
import com.farwolf.weex.activity.WeexActivity;

import org.androidannotations.annotations.EActivity;

/**
 * Created by zhengjiangrong on 2017/5/23.
 */
@EActivity
public class SerialActivity extends WeexActivity {


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if(arg0!=null)
        {
            this.url="app/busi/tab/serial.j";
        }
    }



    @Override
    public void init() {
        this.title.setTitle("分类");

//        this.navbarVisibility="hidden";
        this.resetFrame();
//        this.render("app/busi/tab/serial.js",false);
    }

    @Override
    public int getViewId() {
        return R.layout.app_weex_activity;
    }
}
