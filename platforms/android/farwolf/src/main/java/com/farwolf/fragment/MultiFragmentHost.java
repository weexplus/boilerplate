package com.farwolf.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost;

import com.farwolf.base.FragmentBase;
import com.farwolf.libary.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public abstract class MultiFragmentHost extends FragmentBase {

    @ViewById
    public FragmentTabHost tabhost;


    public int count = 0;

    @AfterViews
    public void initMultiFragmentHost() {
        tabhost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

//        tabhost.getTabWidget().setVisibility(View.GONE);
        init();
    }


    public abstract void init();

    public void add(Class c) {


        tabhost.addTab(tabhost.newTabSpec("tag" + count).setIndicator(count + ""), c, null);
        count++;
    }

    public void add(String tag, Class c) {

        tabhost.addTab(tabhost.newTabSpec(tag).setIndicator(count + ""), c, null);
        count++;


    }

    public void add(String tag, Intent in) {

        TabHost.TabSpec t=tabhost.newTabSpec(tag);
        t.setIndicator(count + "");
        t.setContent(in);
        tabhost.addTab(t);
        count++;


    }



    @Override
    public int getViewId() {
        // TODO Auto-generated method stub
        return R.layout.api_multifragment_host_fragment;
    }

    public void setCurrent(int index) {
        if(tabhost!=null)
        tabhost.setCurrentTab(index);

    }

    public int getCurrentTabIndex()
    {
         return  tabhost.getCurrentTab();
    }

    public Fragment getCurrentFragment()
    {


        return getChildFragmentManager().findFragmentByTag(tabhost.getCurrentTabTag());

    }

    public Fragment getChildFragment(int index)
    {


        return getChildFragmentManager().findFragmentByTag("tag"+index);

    }

    public void setCurrentByTag(String tag) {
        tabhost.setCurrentTabByTag(tag);
    }
}
