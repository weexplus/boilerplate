package com.farwolf.weex.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.farwolf.base.ViewBase;
import com.farwolf.view.FreeDialog;
import com.farwolf.weex.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zhengjiangrong on 2017/7/4.
 */
@EViewGroup
public class LoadingDialog extends ViewBase {

    @ViewById
    public LoadingView loading;

    @ViewById
    public TextView txt;


    public FreeDialog f;


    public LoadingDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    public int getViewId() {
        return R.layout.loadingview;
    }

    @Override
    public void init() {

//        loading=(LoadingView)findViewById(R.id.loading);
        loading.setStyle(LoadingView.BallSpinFadeLoader);
//        txt=(TextView) findViewById(R.id.txt);
    }


}
