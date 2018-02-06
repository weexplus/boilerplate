package com.farwolf.weex.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.RelativeLayout;

/**
 * Created by zhengjiangrong on 2017/6/10.
 */

public class RootLayout extends RelativeLayout {

    private int[] mInsets = new int[4];

    public RootLayout(Context context) {
        super(context);
    }

    public RootLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RootLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public final int[] getInsets() {
        return mInsets;
    }

       @Override
    public final WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            mInsets[0] = insets.getSystemWindowInsetLeft();
            mInsets[1] = 0;
            mInsets[2] = insets.getSystemWindowInsetRight();
            return super.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0,
            insets.getSystemWindowInsetBottom()));
        } else {
        return insets;
        }
    }

    @Override
    protected final boolean fitSystemWindows(Rect insets) {
//        super.fitSystemWindows(insets);
//        return false;
//        if (Build.VERSION.SDK_INT >= 19) {
            mInsets[0] = insets.left;
            mInsets[1] = -100;
            mInsets[2] = insets.right;
            return super.fitSystemWindows(insets);
//        } else {
//            return super.fitSystemWindows(insets);
//        }
    }
}