package com.farwolf.fragment.onelist;

import android.content.Context;
import android.util.AttributeSet;

import com.farwolf.base.ViewBase;
import com.farwolf.libary.R;

import org.androidannotations.annotations.EViewGroup;

/**
 * Created by zhengjiangrong on 2017/4/25.
 */
@EViewGroup
public class FooterProgressBar extends ViewBase {

    public FooterProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FooterProgressBar(Context context) {
        super(context);
    }

    @Override
    public int getViewId() {
        return R.layout.api_footer_progressbar;
    }

    @Override
    public void init() {

    }
}
