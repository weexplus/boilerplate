package com.farwolf.weex.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhengjiangrong on 2018/1/18.
 */

public class ArcView extends View {


    private float startAngle;
    private float sweepAngle;
    private String color;

    public ArcView(Context context) {
        super(context);
    }

    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.drawPie(canvas);

    }

    void drawPie(Canvas canvas)
    {
        Paint p = new Paint();
        p.setColor(Color.parseColor(color));
        int width=this.getLayoutParams().width;
        RectF rectF = new RectF(0, 0, width, width);
        canvas.drawArc(rectF, startAngle, sweepAngle, true, p);
    }

    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
        this.invalidate();
    }

    public void setColor(String color){
        this.color = color;
    }

}
