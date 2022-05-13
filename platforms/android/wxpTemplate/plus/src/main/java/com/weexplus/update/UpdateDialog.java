package com.weexplus.update;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.weex.weexplus.R;
import com.weexplus.util.Pref;
import com.weexplus.view.FreeDialog;


/**
 * Created by zhengjiangrong on 2017/4/12.
 */

public class UpdateDialog extends LinearLayout {

    public FreeDialog f;

    TextView version_name;

    TextView size;

    TextView desc;

    Version data;

    boolean silent;




    CheckBox ignore;

    Button cancel;

    TextView title;

    View line;

    Button ok;

    public UpdateDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UpdateDialog(Context context) {
        super(context);
        init();
    }


    public int getViewId() {
        return R.layout.api_update_dialog;
    }


    public void init() {
        LayoutInflater.from(getContext()).inflate(getViewId(),this);
        version_name=findViewById(R.id.version_name);
        size=findViewById(R.id.size);
        desc=findViewById(R.id.desc);
        ignore=findViewById(R.id.ignore);
        cancel=findViewById(R.id.cancel);
        title=findViewById(R.id.title);
        line=findViewById(R.id.line);
        ok=findViewById(R.id.ok);
        desc.setMovementMethod(ScrollingMovementMethod.getInstance());
    }


    public void setSilent(boolean silent)
    {
       this.silent=silent;
    }
    public void init(Version v, String theme)
    {

        if(theme==null)
        {
            theme="#0891f1";
        }
        title.setTextColor(Color.parseColor(theme));
        line.setBackgroundColor(Color.parseColor(theme));
        StateListDrawable stateListDrawable=new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.parseColor(theme)));// 按下显示的图片
        stateListDrawable.addState(new int[]{}, new ColorDrawable(Color.parseColor(theme)));// 抬起显示的图片
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ok.setBackground(stateListDrawable);
        }


        this.data=v;
        this.desc.setText(v.desc);
        this.version_name.setText("最新版本:"+v.version_name);
        this.size.setText("版本大小:"+v.size);
        this.desc.setText(v.desc);
        if(v.level==1)
        {
            this.ignore.setVisibility(View.GONE);
        }
        else if(v.level==2)
        {
            this.ignore.setVisibility(View.GONE);
            this.cancel.setVisibility(View.GONE);
        }
    }


    public void ignoreClicked() {

        Pref.setString(getContext(),"version",this.data.version_name);
        Pref.setLong(getContext(),"time",System.currentTimeMillis());
        f.dismiss();
    }


    public void okClicked() {



//        UpdateService.Builder.create(data.download_url)
//                .setStoreDir(null)
//                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
//                .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
//                .setSilent(this.silent)
//                .build(this.getContext());
//        this.f.dismiss();
    }




    public void cancelClicked() {

        f.dismiss();
    }
}



