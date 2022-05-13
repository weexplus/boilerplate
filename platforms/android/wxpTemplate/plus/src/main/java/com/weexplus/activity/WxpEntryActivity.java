package com.weexplus.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.weex.weexplus.R;
import com.weexplus.util.ActivityManager;
import com.weexplus.util.Config;
import com.weexplus.util.Pref;
import com.weexplus.view.FreeDialog;
import com.weexplus.view.ToolPop;

public class  WxpEntryActivity extends WxpActivity {




    Button set;

    Button refresh;

    LinearLayout weextool;

    private int	  screenHeight;
    private int	 screenWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        set=findViewById(R.id.set);
        refresh=findViewById(R.id.refresh);
        weextool=findViewById(R.id.weextool);
        init();
    }

    public int getViewId(){
        return R.layout.api_entry_activity;
    }

    void init(){
        if(!Config.debug(this))
        {
            this.set.setVisibility(View.GONE);
            this.weextool.setVisibility(View.GONE);
            this.refresh.setVisibility(View.GONE);
        }else{

        }
        weextool.setBackgroundColor(Color.argb(50,Color.red(Color.BLACK),Color.green(Color.BLACK),Color.blue(Color.BLACK)));
        this.screenHeight = this.getWindowManager().getDefaultDisplay()
                .getHeight();
        this.screenWidth = this.getWindowManager().getDefaultDisplay()
                .getWidth();


        int lastx = Pref.getInt(this,"lastX",0);
        int lasty = Pref.getInt(this,"lastY",this.screenHeight/2);



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
                        int l = WxpEntryActivity.this.weextool.getLeft();
                        int r = WxpEntryActivity.this.weextool.getRight();
                        int t = WxpEntryActivity.this.weextool.getTop();
                        int b = WxpEntryActivity.this.weextool.getBottom();

                        int newt = t + dy;
                        int newb = b + dy;
                        int newl = l + dx;
                        int newr = r + dx;

                        if ( ( newl < 0 ) || ( newt < 0 )
                                || ( newr > WxpEntryActivity.this.screenWidth )
                                || ( newb > WxpEntryActivity.this.screenHeight ) ) {
                            break;
                        }

                        // 更新iv在屏幕的位置.
                        WxpEntryActivity.this.weextool.layout( newl , newt , newr , newb );
                        this.startX = ( int ) event.getRawX();
                        this.startY = ( int ) event.getRawY();




                        break;
                    case MotionEvent.ACTION_UP: // 手指离开屏幕的一瞬间
                        int lastx = WxpEntryActivity.this.weextool.getLeft();
                        int lasty = WxpEntryActivity.this.weextool.getTop();
                        Pref.setInt(WxpEntryActivity.this,"lastX",lastx);
                        Pref.setInt(WxpEntryActivity.this,"lastY",lasty);
                        RelativeLayout.LayoutParams params = ( RelativeLayout.LayoutParams ) WxpEntryActivity.this.weextool.getLayoutParams();
                        params.leftMargin = lastx;
                        params.topMargin = lasty;
                        WxpEntryActivity.this.weextool.setLayoutParams(params);
//                        SharedPreferences.Editor editor = WxpEntryActivity.this.sp.edit();
//                        editor.putInt( "lastx" , lastx );
//                        editor.putInt( "lasty" , lasty );
//                        editor.commit();
                        break;
                }
                return true;
            }
        } );

        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity ac= ActivityManager.getInstance().getCurrentActivity();
                if(ac instanceof WxpActivity){
                    WxpActivity wa=(WxpActivity)ac;
                    wa.refresh();

                }
            }
        });

        findViewById(R.id.set).setOnClickListener(new View.OnClickListener() {
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

    public void showTool()
    {
        ToolPop tool= new ToolPop(this);
        FreeDialog f=new FreeDialog(this,tool);
        f.setCanceledOnTouchOutside(false);
        tool.f=f;
        f.show();
    }






}
