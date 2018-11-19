package com.farwolf.share;

import com.farwolf.weex.adapter.AnalyseAdapter;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by zhengjiangrong on 2018/4/3.
 */

public class UMAnalyseAdapter implements AnalyseAdapter {


    @Override
    public void onStart(String name) {
        MobclickAgent.onPageStart(name);
    }

    @Override
    public void onStop(String name) {
        MobclickAgent.onPageEnd(name);
    }
}
