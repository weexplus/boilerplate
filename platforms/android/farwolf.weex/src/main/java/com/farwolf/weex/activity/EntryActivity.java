package com.farwolf.weex.activity;

import android.view.View;
import android.widget.Button;

import com.farwolf.weex.R;
import com.farwolf.weex.bean.Config;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zhengjiangrong on 2017/12/7.
 */
@EActivity
public class EntryActivity extends WeexActivity {


    @ViewById
    Button set;
    @ViewById
    Button refresh;

    @Override
    public void init() {

       if(!Config.debug(this))
       {
         this.set.setVisibility(View.GONE);
         this.refresh.setVisibility(View.GONE);
       }


    }


    @Click
    public void setClicked() {
        showTool();

    }


    @Click
    public void refreshClicked() {
        render(url);

    }



    @Override
    public int getViewId() {
        return R.layout.debug_activity;
    }

}
