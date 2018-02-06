package com.farwolf.weex.activity;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.farwolf.weex.R;
import com.farwolf.weex.bean.Config;
import com.taobao.weex.WXSDKInstance;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zhengjiangrong on 2017/12/7.
 */
@EActivity
public class EntryActivity extends WeexActivity {


    @ViewById
    Button set;
    @ViewById
    Button refresh;
    @ViewById
    LinearLayout weextool;



    private int					screenHeight;
    private int					screenWidth;


    @Override
    public void init() {

        initDebug();

    }

    void initDebug()
    {
        if(!Config.debug(this))
        {
            this.set.setVisibility(View.GONE);
            this.weextool.setVisibility(View.GONE);
            this.refresh.setVisibility(View.GONE);
        }
//        this.set.setVisibility(View.VISIBLE);
//        this.weextool.setVisibility(View.VISIBLE);
//        this.refresh.setVisibility(View.VISIBLE);
//       String url= Config.entry(this);
//       render(url);

        weextool.setBackgroundColor(Color.argb(50,Color.red(Color.BLACK),Color.green(Color.BLACK),Color.blue(Color.BLACK)));
        this.screenHeight = this.getWindowManager().getDefaultDisplay()
                .getHeight();
        this.screenWidth = this.getWindowManager().getDefaultDisplay()
                .getWidth();


        int lastx =   pref.lastX().getOr(0);
        int lasty =   pref.lastY().getOr( this.screenHeight/2);


        RelativeLayout.LayoutParams params = ( RelativeLayout.LayoutParams ) this.weextool
                .getLayoutParams();
        params.leftMargin = lastx;
        params.topMargin = lasty;
        this.weextool.setLayoutParams( params );

        this.weextool.setOnTouchListener( new View.OnTouchListener() {
            int	startX;
            int	startY;

            @Override
            public boolean onTouch( View v, MotionEvent event ) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:// 手指第一次触摸到屏幕
                        this.startX = ( int ) event.getRawX();
                        this.startY = ( int ) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:// 手指移动
                        int newX = ( int ) event.getRawX();
                        int newY = ( int ) event.getRawY();

                        int dx = newX - this.startX;
                        int dy = newY - this.startY;

                        // 计算出来控件原来的位置
                        int l = EntryActivity.this.weextool.getLeft();
                        int r = EntryActivity.this.weextool.getRight();
                        int t = EntryActivity.this.weextool.getTop();
                        int b = EntryActivity.this.weextool.getBottom();

                        int newt = t + dy;
                        int newb = b + dy;
                        int newl = l + dx;
                        int newr = r + dx;

                        if ( ( newl < 0 ) || ( newt < 0 )
                                || ( newr > EntryActivity.this.screenWidth )
                                || ( newb > EntryActivity.this.screenHeight ) ) {
                            break;
                        }

                        // 更新iv在屏幕的位置.
                        EntryActivity.this.weextool.layout( newl , newt , newr , newb );
                        this.startX = ( int ) event.getRawX();
                        this.startY = ( int ) event.getRawY();




                        break;
                    case MotionEvent.ACTION_UP: // 手指离开屏幕的一瞬间
                        int lastx = EntryActivity.this.weextool.getLeft();
                        int lasty = EntryActivity.this.weextool.getTop();
                        pref.edit().lastX().put(lastx).apply();
                        pref.edit().lastY().put(lasty).apply();
                        RelativeLayout.LayoutParams params = ( RelativeLayout.LayoutParams ) EntryActivity.this.weextool.getLayoutParams();
                        params.leftMargin = lastx;
                        params.topMargin = lasty;
                        EntryActivity.this.weextool.setLayoutParams(params);
//                        SharedPreferences.Editor editor = EntryActivity.this.sp.edit();
//                        editor.putInt( "lastx" , lastx );
//                        editor.putInt( "lasty" , lasty );
//                        editor.commit();
                        break;
                }
                return true;
            }
        } );
    }

    @Click
    public void setClicked() {
        showTool();

    }


    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
        super.onRefreshSuccess(instance, width, height);
        initDebug();
    }

    @Click
    public void refreshClicked() {
        render(url);

    }



    @Override
    public int getViewId() {
        return R.layout.debug_activity;
    }

}
