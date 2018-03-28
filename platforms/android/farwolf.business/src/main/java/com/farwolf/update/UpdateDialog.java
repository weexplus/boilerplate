package com.farwolf.update;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.farwolf.base.ViewBase;
import com.farwolf.business.R;
import com.farwolf.update.download.UpdateService;
import com.farwolf.util.AppMainfest;
import com.farwolf.view.FreeDialog;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by zhengjiangrong on 2017/4/12.
 */
@EViewGroup
public class UpdateDialog extends ViewBase {

    public FreeDialog f;
    @ViewById
    TextView version_name;
    @ViewById
    TextView size;
    @ViewById
    TextView desc;

    Version data;

    @Bean
    AppMainfest appMainfest;

    @Pref
    UpdatePref_ pref;
    @ViewById
    CheckBox ignore;
    @ViewById
    Button cancel;
    @ViewById
    TextView title;
    @ViewById
    View line;
    @ViewById
    Button ok;

    public UpdateDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UpdateDialog(Context context) {
        super(context);
    }

    @Override
    public int getViewId() {
        return R.layout.api_update_dialog;
    }

    @Override
    public void init() {
        desc.setMovementMethod(ScrollingMovementMethod.getInstance());
    }


    public void init(Version v,String theme)
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
        ok.setBackground(stateListDrawable);


        this.data=v;
        this.desc.setText(v.desc);
        this.version_name.setText("最新版本:"+v.versionName);
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

    @Click
    public void ignoreClicked() {

        Toast.makeText(getActivity(), pref.version().get(),Toast.LENGTH_LONG);
        pref.edit().version().put(this.data.versionName).apply();
        pref.edit().time().put(System.currentTimeMillis()).apply();
        f.dismiss();
    }

    @Click
    public void okClicked() {



        UpdateService.Builder.create(data.downloadUrl)
                .setStoreDir(null)
                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                .build(this.getContext());
        this.f.dismiss();
    }



    @Click
    public void cancelClicked() {

        f.dismiss();
    }
}



