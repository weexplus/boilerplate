package com.farwolf.weex.activity;

import org.androidannotations.annotations.EActivity;

/**
 * Created by zhengjiangrong on 2017/5/17.
 */
@EActivity
public class PresentActivity extends WeexActivity {


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,com.farwolf.business.R.anim.dismiss);
    }


}
