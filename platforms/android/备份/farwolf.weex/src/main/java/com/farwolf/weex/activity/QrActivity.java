package com.farwolf.weex.activity;

import android.graphics.Color;

import com.farwolf.base.FragmentBase;
import com.farwolf.base.OneFragmentActivity;
import com.farwolf.weex.fragment.QrFragment_;

import org.androidannotations.annotations.EActivity;

/**
 * Created by zhengjiangrong on 2017/4/19.
 */
@EActivity
public class QrActivity extends OneFragmentActivity{
    @Override
    public FragmentBase getFragment() {
        return new QrFragment_();
    }

    @Override
    public void init() {
          this.title.setBack();
          this.title.setTitle("二维码扫描");


        String titlecolor=  getIntent().getStringExtra("titleColor");
        String bgcolor=   getIntent().getStringExtra("bgColor");

        if(titlecolor!=null)
        this.title.layout.setBackgroundColor(Color.parseColor(bgcolor));
        if(bgcolor!=null)
        this.title.title.setTextColor(Color.parseColor(titlecolor));
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,com.farwolf.business.R.anim.dismiss);
    }
}
