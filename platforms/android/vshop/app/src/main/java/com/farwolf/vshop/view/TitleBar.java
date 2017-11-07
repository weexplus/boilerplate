package com.farwolf.vshop.view;

import android.content.Context;
import android.util.AttributeSet;

import com.farwolf.movie.R;

/**
 * Created by zhengjiangrong on 2017/6/10.
 */

public class TitleBar extends com.farwolf.view.TitleBar {


    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleBar(Context context) {
        super(context);
    }

    @Override
    public int getViewId() {
        // TODO Auto-generated method stub

           return R.layout.api_titlebar60;

    }
}
