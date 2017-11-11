package com.farwolf.vshop;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.farwolf.movie.R;
import com.farwolf.weex.activity.WeexActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity
public class MainActivity extends WeexActivity {




    @Override
    public void init() {

        url=pref.url().get();

        this.title.setBack();
        this.title.setRightImage(R.drawable.refresh_selector);
//        this.title.rightview.setVisibility(View.VISIBLE);
//        this.title.right_image.setBackgroundResource(R.drawable.refresh_selector);
        this.title.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showTool();
//                weexFactory.jump("index.js",SecondActivity_.class);
                render(url);

            }
        });

        this.title.rightview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showTool();
                return false;
            }
        });



//        render("app/index.js");

        final Handler achandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                render("app/index.js");
            }
        };
        achandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                achandler.sendEmptyMessage(0);
            }
        }, 500);



    }


    @Override
    public int getViewId() {
        return R.layout.main_activity;
    }


    @Click
    public void refreshClicked() {
        render(url);

    }
}
