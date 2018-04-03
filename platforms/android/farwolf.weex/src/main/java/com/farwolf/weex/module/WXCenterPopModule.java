package com.farwolf.weex.module;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.farwolf.util.ScreenTool;
import com.farwolf.util.ScreenTool_;
import com.farwolf.view.imgae.crop.Log;
import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.core.Page;
import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.core.WeexFactory_;
import com.farwolf.weex.event.PopEvent;
import com.farwolf.weex.util.Weex;
import com.farwolf.weex.view.WXPageView;
import com.farwolf.weex.view.WXPageView_;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengjiangrong on 2017/12/8.
 */

public class WXCenterPopModule extends WXModuleBase {

    WXPageView maskView;
    WXPageView popView;
    RelativeLayout.LayoutParams startLP;
    RelativeLayout.LayoutParams endLP;
    ScaleAnimation showAnimation;
    ScaleAnimation hideAnimation;
    JSCallback callback;

    String url;
    HashMap style;
    Map param;
    Boolean clickDismiss;

    @JSMethod
    public void show(String url, HashMap style, Map param, Boolean clickDismiss, JSCallback callback)
    {
        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        this.callback=callback;
        this.url = Weex.getRelativeUrl(url,this.mWXSDKInstance);
        this.param = param;
        this.style = style;
        this.clickDismiss = clickDismiss;
        clear();

        WeexFactory factory = WeexFactory_.getInstance_(getActivity());
        factory.preRender(this.url, new WeexFactory.OnRenderFinishListener() {
            @Override
            public void onRenderFinish(Page p) {
                initMaskView();
                initSlidView();
            }

            @Override
            public void onRenderFailed(Page p) {

            }
        });

    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PopEvent event) {

        if("centerpop".equals(event.type)) {
            this.clear();
            this.invoke(event.param);
        }


    }




    @JSMethod
    public void dismiss(HashMap param) {
        EventBus.getDefault().post(new PopEvent("centerpop",param));
    }

    void close()
    {
        hideAnimation.setDuration(160);
        popView.startAnimation(hideAnimation);
        hideAnimation.startNow();

        hideAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                popView.clearAnimation();
                clear();
                invoke(null);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }


    void invoke(HashMap param)
    {
        if(callback!=null)
            callback.invokeAndKeepAlive(param);
    }

    void clear() {
        if (maskView != null) {
            maskView.removeView(popView);
            getActivity().root.removeView(maskView);
        }
        popView = null;
        maskView = null;

        Log.e("删除");
    }

    void initMaskView() {
        if (maskView == null) {
            maskView = WXPageView_.build(getActivity());
            RelativeLayout.LayoutParams startLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            maskView.setLayoutParams(startLP);
            maskView.setBackgroundColor(Color.argb(140,Color.red(Color.BLACK),Color.green(Color.BLACK),Color.blue(Color.BLACK)));
            getActivity().root.addView(maskView);

            if (clickDismiss) {
                maskView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            dismiss(null);
                        }
                        return true;
                    }
                });
            }
        }
    }

    void initSlidView() {
        WindowManager wm = (WindowManager) getActivity().getBaseContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int screenWidth = wm.getDefaultDisplay().getWidth();
        int screenHeight = wm.getDefaultDisplay().getHeight();

        if (popView == null) {
            popView = WXPageView_.build(getActivity());
        }
        popView.setRenderListener(new WXPageView.RenerListerner() {
            @Override
            public void onRenderSuccess() {
                popView.startAnimation(showAnimation);
                showAnimation.startNow();
            }
        });


//        popView. setBackgroundColor(Color.RED);

        final int width =  (int)Weex.length(style.get("width") != null?(Integer) style.get("width"):300);
        final int height = (int)Weex.length(style.get("height") != null?(Integer) style.get("height"):400);

        ScreenTool tool= ScreenTool_.getInstance_(this.mWXSDKInstance.getContext());
//        int top = (style.get("top") != null?(Integer) style.get("top"):(screenHeight-height)/2);
        int top=(screenHeight-height)/2;

        startLP = new RelativeLayout.LayoutParams(1, 1);
        startLP.setMargins((screenWidth - 1) / 2,(int) (top + height / 2 - 0.5),(screenWidth - 1) / 2,(int) (screenHeight - top + height / 2 + 0.5f));

        showAnimation = new ScaleAnimation(0, width, 0, height, 0.5f, 0.5f);
        hideAnimation = new ScaleAnimation(1, 0, 1, 0, width/2, height/2);

        endLP = new RelativeLayout.LayoutParams(width, height);
        endLP.setMargins((screenWidth - width) / 2,top,(screenWidth - width) / 2,screenHeight - height - top);

        popView.setLayoutParams(startLP);
        Log.e("数据：" + endLP.leftMargin + "," + endLP.topMargin + "," + endLP.rightMargin + "," + endLP.bottomMargin);
        showAnimation.setDuration(30);
        showAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                popView.clearAnimation();
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(endLP.width, endLP.height);
                params.setMargins(endLP.leftMargin, endLP.topMargin, endLP.rightMargin, endLP.bottomMargin);
                popView.setLayoutParams(params);

            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


        popView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("点击了pop页面");
            }
        });
        popView.setSrc(url,mWXSDKInstance.getContext(),this.param);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(0, 0);
        params.setMargins(endLP.leftMargin, endLP.topMargin, endLP.rightMargin, endLP.bottomMargin);
        popView.instance.setSize(width,height);
        popView.instance.param=this.param;
        popView.setLayoutParams(params);
        maskView.root.addView(popView);



    }

}
