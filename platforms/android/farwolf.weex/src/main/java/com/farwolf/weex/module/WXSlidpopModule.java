package com.farwolf.weex.module;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/12/8.
 */

public class WXSlidpopModule  extends WXModuleBase {

    WXPageView maskView;
    WXPageView slidView;
    RelativeLayout.LayoutParams startLP;
    RelativeLayout.LayoutParams endLP;
    TranslateAnimation showAnimation;
    TranslateAnimation hideAnimation;

    String url;
    HashMap param;
    int realdelt;
    HashMap offset;
    String side;

    /**
     *
     * @param url 弹出页面的js地址
     * @param param 参数
     * @param delt 宽度或者高度
     * @param offset  边距
     * @param side 上下左右(left,top,right,bottom)
     */
    @JSMethod
    public void show(String url, HashMap param,float delt,HashMap offset,String side)
    {
        this.url = Weex.getRelativeUrl(url,this.mWXSDKInstance);
        this.param = param;
        realdelt = (int) Weex.length(delt); //转化为真实的weex数值
        this.offset = offset;
        this.side = side;
        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);


        WeexFactory factory =WeexFactory_.getInstance_(getActivity());
        factory.preRender(this.url, new WeexFactory.OnRenderFinishListener() {
            @Override
            public void onRenderFinish(Page p) {
                if(maskView!=null)
                {
                    getActivity().root.removeView(maskView);
                    maskView=null;
                }
                initMaskView();
                initSlidView();
            }

            @Override
            public void onRenderFailed(Page p) {

            }
        });

        Log.e("传入的数据为： = " + offset + side);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PopEvent event) {

        if("slidpop".equals(event.type))
            this.close();

    }

    @JSMethod
    public  void dismiss()
    {
        EventBus.getDefault().post(new PopEvent("slidpop"));
    }


    void close()
    {
        if(maskView!=null)
            getActivity().root.removeView(maskView);
        if(slidView!=null)
            getActivity().root.removeView(slidView);
    }


    void initMaskView() {
        if (maskView == null) {
            maskView = WXPageView_.build(getActivity());
            RelativeLayout.LayoutParams startLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            maskView.setLayoutParams(startLP);

            getActivity().root.addView(maskView);
            maskView.setVisibility(View.VISIBLE);
            maskView.setBackgroundColor(Color.argb(140,Color.red(Color.BLACK),Color.green(Color.BLACK),Color.blue(Color.BLACK)));
            maskView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(slidView==null)
                        return false;
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        hideAnimation.setDuration(160);

                        slidView.startAnimation(hideAnimation);
                        hideAnimation.startNow();

                        hideAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                slidView.clearAnimation();
                                maskView.removeView(slidView);
                                if( getActivity()!=null&& getActivity().root!=null)
                                {
                                    getActivity().root.removeView(maskView);
                                }
                                slidView = null;
                                maskView = null;
                                Log.e("删除");
                            }
                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                    }
                    return true;
                }
            });
        }
    }

    void initSlidView() {
        WindowManager wm = (WindowManager) getActivity().getBaseContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        if(slidView!=null&&slidView.getParent()!=null)
        {
            ((ViewGroup)slidView.getParent()).removeView(slidView);
            slidView=null;
        }
        if (slidView == null) {
            slidView = WXPageView_.build(getActivity());
        }
        slidView.setSrc(url,mWXSDKInstance.getContext(),this.param);

        int left = offset.get("left") != null?(Integer) offset.get("left"):0;
        int right = offset.get("right") != null?(Integer) offset.get("right"):0;
        int top = offset.get("top") != null?(Integer) offset.get("top"):0;
        int bottom = offset.get("bottom") != null?(Integer) offset.get("bottom"):0;

        switch (side) {
            case "left": {
                startLP = new RelativeLayout.LayoutParams(realdelt, RelativeLayout.LayoutParams.MATCH_PARENT);
                startLP.setMargins(-realdelt,top,width,bottom);
                showAnimation = new TranslateAnimation(0, realdelt + left, 0, 0);
                hideAnimation = new TranslateAnimation(0, -realdelt - left, 0, 0);

                endLP = new RelativeLayout.LayoutParams(realdelt, RelativeLayout.LayoutParams.MATCH_PARENT);
                endLP.setMargins(left,top,0,bottom);
                break;
            }
            case "right": {
                startLP = new RelativeLayout.LayoutParams(realdelt, RelativeLayout.LayoutParams.MATCH_PARENT);
                startLP.setMargins(width,top,-realdelt,bottom);
                showAnimation = new TranslateAnimation(0, -realdelt - right, 0, 0);
                hideAnimation = new TranslateAnimation(0, realdelt + right, 0, 0);

                endLP = new RelativeLayout.LayoutParams(realdelt, RelativeLayout.LayoutParams.MATCH_PARENT);
                endLP.setMargins(width - realdelt - right,top,right,bottom);
                break;
            }
            case "top": {
                startLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, realdelt);
                startLP.setMargins(left,-realdelt,right,0);
                showAnimation = new TranslateAnimation(0, 0, 0, realdelt + top);
                hideAnimation = new TranslateAnimation(0, 0, 0, -realdelt - top);

                endLP = new RelativeLayout.LayoutParams(realdelt, RelativeLayout.LayoutParams.MATCH_PARENT);
                endLP.setMargins(left,top,right,0);
                break;
            }
            case "bottom": {
                startLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, realdelt);
                startLP.setMargins(left,height,right,-realdelt);
                showAnimation = new TranslateAnimation(0, 0, 0, -realdelt - bottom);
                hideAnimation = new TranslateAnimation(0, 0, 0, realdelt + bottom);

                endLP = new RelativeLayout.LayoutParams(realdelt, RelativeLayout.LayoutParams.MATCH_PARENT);
                endLP.setMargins(left,height - realdelt - bottom,right,bottom);
                break;
            }
            default: {
                startLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, realdelt);
                startLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                startLP.setMargins(left,0,right,-realdelt);
                showAnimation = new TranslateAnimation(0, 0, 0, realdelt + bottom);

                endLP = new RelativeLayout.LayoutParams(realdelt, RelativeLayout.LayoutParams.MATCH_PARENT);
                startLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                endLP.setMargins(left,top,width - realdelt,bottom);
                break;
            }
        }
        slidView.setLayoutParams(startLP);
        Log.e("数据：" + endLP.leftMargin + "," + endLP.topMargin + "," + endLP.rightMargin + "," + endLP.bottomMargin);
        showAnimation.setDuration(160);
        showAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                slidView.clearAnimation();
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) slidView.getLayoutParams();
                params.setMargins(endLP.leftMargin, endLP.topMargin, endLP.rightMargin, endLP.bottomMargin);// 改变位置,这里是左右不变，上下平移height高度
                slidView.setLayoutParams(params);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        slidView.startAnimation(showAnimation);
        showAnimation.startNow();

        maskView.root.addView(slidView);

    }

}
