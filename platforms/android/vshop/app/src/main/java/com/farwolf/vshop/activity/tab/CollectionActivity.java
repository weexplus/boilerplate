package com.farwolf.vshop.activity.tab;

import android.os.Bundle;

import com.farwolf.movie.R;
import com.farwolf.weex.activity.WeexActivity;

import org.androidannotations.annotations.EActivity;

/**
 * Created by zhengjiangrong on 2017/5/23.
 */
@EActivity
public class CollectionActivity  extends WeexActivity {


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if(arg0!=null)
        {
            this.url="app/busi/tab/collection/collection.js";
        }
    }



    @Override
    public void init() {
        this.title.setTitle("分类");
        this.navbarVisibility="hidden";
        this.resetFrame();
//        this.render("app/busi/tab/collection.js",false);
//        render("app/busi/tab/collection/collection.js",false);
//        render("http://192.168.2.101:9898/busi/tab/collection/collection.js",false);
    }

    @Override
    public int getViewId() {
        return R.layout.app_weex_activity;
    }

}
