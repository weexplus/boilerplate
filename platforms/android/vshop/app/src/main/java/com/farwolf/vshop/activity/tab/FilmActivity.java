package com.farwolf.vshop.activity.tab;

import android.os.Bundle;

import com.farwolf.movie.R;
import com.farwolf.util.ScreenTool;
import com.farwolf.weex.activity.WeexActivity;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

/**
 * Created by zhengjiangrong on 2017/5/23.
 */
@EActivity
public class FilmActivity extends WeexActivity {


    @Bean
    ScreenTool tool;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if(arg0!=null)
        {
            this.url="app/busi/tab/movie.js";
        }
    }

    @Override
    public void init() {
        this.title.setTitle("购物车");
        this.navbarVisibility="hidden";
        this.resetFrame();
//        this.render("app/busi/tab/movie.js",false);

    }


    @Override
    public int getViewId() {
        return R.layout.app_weex_activity;
    }
}
