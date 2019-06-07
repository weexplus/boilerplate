package com.farwolf.weex.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

import com.farwolf.base.ViewBase;
import com.farwolf.util.ActivityManager;
import com.farwolf.view.FreeDialog;
import com.farwolf.weex.R;
import com.farwolf.weex.activity.WeexActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;

@EViewGroup
public class ToolView extends ViewBase {


    public ToolView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToolView(Context context) {
        super(context);
    }

    @Override
    public int getViewId() {
        return R.layout.tool_layout;
    }

    @Override
    public void init() {

    }

    @Click
    public void refreshClicked() {

      Activity ac= ActivityManager.getInstance().getCurrentActivity();
       if(ac instanceof WeexActivity){
           WeexActivity wa=(WeexActivity)ac;
           wa.render(wa.url);

       }
    }

    @Click
    public void setClicked() {
        showTool();

    }
    public void showTool()
    {

        Activity ac= ActivityManager.getInstance().getCurrentActivity();
        ToolPop tool= ToolPop_.build(ac);
        FreeDialog f=new FreeDialog(ac,tool);
        f.setCanceledOnTouchOutside(false);
        tool.f=f;
        f.show();
    }

}
