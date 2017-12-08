package com.farwolf.vshop.activity.tab;

import com.farwolf.movie.R;
import com.farwolf.weex.activity.WeexActivity;

import org.androidannotations.annotations.EActivity;

/**
 * Created by zhengjiangrong on 2017/5/23.
 */
@EActivity
public class MainPageActivity extends WeexActivity {


    @Override
    public void init() {
        this.title.setTitle("主页");
//       this.navbarVisibility="hidden";
        this.resetFrame();
//        render("app/busi/tab/main/mainpage.js",false);
    }



    @Override
    public int getViewId() {
        return R.layout.app_weex_activity;
    }
}
