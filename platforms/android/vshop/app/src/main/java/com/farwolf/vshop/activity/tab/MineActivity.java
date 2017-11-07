package com.farwolf.vshop.activity.tab;

import android.os.Bundle;

import com.farwolf.movie.R;
import com.farwolf.weex.activity.WeexActivity;

/**
 * Created by zhengjiangrong on 2017/5/23.
 */

public class MineActivity extends WeexActivity {


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if(arg0!=null)
        {
            this.url="app/busi/tab/mine.js";
        }
    }

    @Override
    public void init() {
        this.title.setTitle("我的");
//        render("app/busi/tab/mine.js",false);
    }




    @Override
    public int getViewId() {
        return R.layout.mine_activity;
    }
}
