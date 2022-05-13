package com.weexplus.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.weex.weexplus.R;
import com.weexplus.activity.WxpActivity;
import com.weexplus.util.ActivityManager;



public class ToolView extends LinearLayout {


    public ToolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToolView(Context context) {
        super(context);
        init();
    }




    public void init() {
        LayoutInflater.from(getContext()).inflate( R.layout.api_tool_layout,this);
        findViewById(R.id.refresh).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity ac= ActivityManager.getInstance().getCurrentActivity();
                if(ac instanceof WxpActivity){
                    WxpActivity wa=(WxpActivity)ac;
                    wa.refresh();

                }
            }
        });
        findViewById(R.id.set).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity ac= ActivityManager.getInstance().getCurrentActivity();
                if(ac instanceof WxpActivity){

                    ToolPop tool=new ToolPop(ac);
                    FreeDialog f=new FreeDialog(ac,tool);
                    f.setCanceledOnTouchOutside(false);
                    tool.f=f;
                    f.show();

                }
            }
        });
    }




}
