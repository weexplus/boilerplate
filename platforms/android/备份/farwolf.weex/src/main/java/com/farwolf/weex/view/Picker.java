package com.farwolf.weex.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.farwolf.base.ViewBase;
import com.farwolf.util.ScreenTool;
import com.farwolf.util.ScreenTool_;
import com.farwolf.view.pickerview.ArrayWheelAdapter;
import com.farwolf.view.pickerview.OnItemSelectedListener;
import com.farwolf.view.pickerview.WheelView;
import com.farwolf.weex.R;
import com.farwolf.weex.activity.WeexActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by zhengjiangrong on 2017/7/18.
 */

@EViewGroup
public class Picker  extends ViewBase {


    @ViewById
    TextView tvTitle;
    @ViewById
    public WheelView options1;
    @ViewById
    public WheelView options2;
    @ViewById
    public WheelView options3;

    RelativeLayout layout;

    @ViewById
    LinearLayout optionspicker;
    @ViewById
    Button btnCancel;
    @ViewById
    Button btnSubmit;
    @ViewById
    RelativeLayout titlebg;

    public Picker(Context context) {
        super(context);


    }

    public void setOnChangeListener( OnItemSelectedListener l1, OnItemSelectedListener l2, OnItemSelectedListener l3)
    {
        this.options1.setOnItemSelectedListener(l1);
        this.options2.setOnItemSelectedListener(l2);
        this.options3.setOnItemSelectedListener(l3);
    }

    public void setTheme(String bgcolor,String btncolor)
    {
        this.titlebg.setBackgroundColor(Color.parseColor(bgcolor));
        this.btnCancel.setTextColor(createColorStateList(Color.parseColor(btncolor),Color.parseColor("#eeeeee"),Color.parseColor("#eeeeee"),Color.parseColor("#eeeeee")));
    }

    private ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };
        int[][] states = new int[6][];
        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
        states[2] = new int[] { android.R.attr.state_enabled };
        states[3] = new int[] { android.R.attr.state_focused };
        states[4] = new int[] { android.R.attr.state_window_focused };
        states[5] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    @Override
    public int getViewId() {
        return R.layout.picker;
    }

    @Override
    public void init() {
        this.options1.setCyclic(false);
        this.options2.setCyclic(false);
        this.options3.setCyclic(false);
        int textSize = 25;
        options1.setTextSize(textSize);
        options2.setTextSize(textSize);
        options3.setTextSize(textSize);
        this.titlebg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        layout=new RelativeLayout(getContext());
        layout.setBackgroundColor(Color.argb(153,Color.red(Color.BLACK),Color.green(Color.BLACK),Color.blue(Color.BLACK)));
        RelativeLayout.LayoutParams lplayout=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(lplayout);
        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });

        ((WeexActivity) getActivity()).root.addView(layout);
        this.layout.setVisibility(View.GONE);
    }

    public void setCount(int count)
    {
        if(count==2)
        {
            this.options3.setVisibility(View.GONE);
        }
        if(count==1)
        {
            this.options3.setVisibility(View.GONE);
            this.options2.setVisibility(View.GONE);

        }

    }





    public void select(int p ,int row)
    {
         if(p==1)
         {
             this.options1.setCurrentItem(row);
         }
         else if(p==2)
         {
            this.options2.setCurrentItem(row);
         }
         else if(p==3)
         {
             this.options3.setCurrentItem(row);
         }

    }


    public void show()
    {
        this.setVisibility(View.GONE);
        this.layout.setVisibility(View.GONE);

        if(this.getParent()==null)
        {
            ScreenTool tool= ScreenTool_.getInstance_(getContext());
            RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,tool.toDip(300));
            lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            this.setLayoutParams(lp);
            layout.addView(this);
            this.layout.setVisibility(View.VISIBLE);

        }
        else
        {
            this.setVisibility(View.VISIBLE);
            this.layout.setVisibility(View.VISIBLE);
        }
        Animation an= AnimationUtils.loadAnimation(this.getContext(), R.anim.picker_com);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Picker.this.setVisibility(View.VISIBLE);
//               final Rect viewRect = new Rect();
//                Picker.this.getGlobalVisibleRect(viewRect);
                ((WeexActivity) getActivity()).root.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent ev) {
                        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                            View v = Picker.this;
                            if (!isTouch(v, ev)) {
                                dismiss();
                            }
                        }
                        return false;
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        this.startAnimation(an);



    }



//    @Override
//    public boolean dispatchTouchEvent(final MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = this;
//            if (isTouch(v, ev)) {
//                 dismiss();
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }


    private boolean isTouch(View v, MotionEvent event) {
        if (v != null && (v instanceof Picker)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return true;
            } else {
                return false;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }


    public void dismiss()
    {

        Animation an= AnimationUtils.loadAnimation(this.getContext(), R.anim.picker_gone);

        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Picker.this.setVisibility(View.GONE);
                Picker.this.layout.setVisibility(View.GONE);
                ((WeexActivity) getActivity()).root.setOnTouchListener(null);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        this.startAnimation(an);
//        this.setVisibility(View.GONE);
    }


    public void setItems1(ArrayList d)
    {
        this.options1.setAdapter(new ArrayWheelAdapter(d));
    }

    public void setItems2(ArrayList d)
    {
        this.options2.setAdapter(new ArrayWheelAdapter(d));
    }

    public void setItems3(ArrayList d)
    {
        this.options3.setAdapter(new ArrayWheelAdapter(d));
    }

    OnClickListener doneListener;
    public void  setDoneListener(OnClickListener listener)
    {
          this.doneListener=listener;
    }

    @Click
    public void btnCancelClicked() {

        dismiss();
    }

    @Click
    public void btnSubmitClicked() {

        dismiss();
        if(doneListener!=null)
        doneListener.onClick(this);
    }
}
