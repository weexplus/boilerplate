package com.farwolf.vshop.fragment;

import android.view.View;

import com.farwolf.base.ViewPagerFragmentBase;
import com.farwolf.weex.fragment.WeexFragment;

import org.androidannotations.annotations.EFragment;

/**
 * Created by zhengjiangrong on 2017/6/25.
 */
@EFragment
public class CollectionFragment extends ViewPagerFragmentBase {


    @Override
    public void init() {
//        this.add("1", WeexFragment.newInstance("app/busi/tab/main/mainpage.js"));
//        this.add("2", WeexFragment.newInstance("app/busi/tab/main/mainpage.js"));
//        this.add("3", WeexFragment.newInstance("app/busi/tab/main/mainpage.js"));
//        this.add("4", WeexFragment.newInstance("app/busi/tab/main/mainpage.js"));
//        this.add("5", WeexFragment.newInstance("app/busi/tab/main/mainpage.js"));

//        this.add("1", WeexFragment.newInstance("app/busi/tab/collection/class.js"));
        this.add("1", WeexFragment.newInstance("app/busi/tab/collection/clist.js"));
        this.add("2", WeexFragment.newInstance("app/busi/tab/collection/clist.js"));

//        this.add("5", WeexFragment.newInstance("app/busi/tab/collection/clist.js"));
//        viewpager.setPreloadSize(l.size() - 1);
        this.viewpager.indicator.setVisibility(View.GONE);
    }
}
