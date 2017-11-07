package com.farwolf.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

import com.farwolf.libary.R;

/**
 * Created by zhengjiangrong on 16/7/28.
 */
public class FreeDialog extends Dialog {



    public FreeDialog(Context context, View v) {
        super(context, R.style.free_dialog);
        setCanceledOnTouchOutside(true);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(v);


    }

}
