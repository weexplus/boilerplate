package com.farwolf.view.qrCode;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.farwolf.libary.R;
import com.farwolf.util.ScreenTool_;

public class ViewFinderView extends View {
    private static final String TAG = "ViewFinderView";

    private Rect mFramingRect;

    private static final int MIN_FRAME_WIDTH = 240;
    private static final int MIN_FRAME_HEIGHT = 240;

    private static final float LANDSCAPE_WIDTH_RATIO = 5f / 8;
    private static final float LANDSCAPE_HEIGHT_RATIO = 5f / 8;
    private static final int LANDSCAPE_MAX_FRAME_WIDTH = (int) (1920 * LANDSCAPE_WIDTH_RATIO); // = 5/8 * 1920
    private static final int LANDSCAPE_MAX_FRAME_HEIGHT = (int) (1080 * LANDSCAPE_HEIGHT_RATIO); // = 5/8 * 1080

    private static final float PORTRAIT_WIDTH_RATIO = 7f / 8;
    private static final float PORTRAIT_HEIGHT_RATIO = 3f / 8;
    private static final int PORTRAIT_MAX_FRAME_WIDTH = (int) (1080 * PORTRAIT_WIDTH_RATIO); // = 7/8 * 1080
    private static final int PORTRAIT_MAX_FRAME_HEIGHT = (int) (1920 * PORTRAIT_HEIGHT_RATIO); // = 3/8 * 1920

    private static final int[] SCANNER_ALPHA = {64, 80, 96, 102, 128, 144, 160, 176, 192, 208, 224, 240, 255,
            240, 224, 208, 192, 176, 160, 144, 128, 102, 96, 80, 64};
    private int scannerAlpha = 0;
    private int position = 0;
    private final int STEP_SIZE = 8;
    private Bitmap spark;
    private Rect sparkRect;
    private Paint paint;
    private static final int POINT_SIZE = 10;
    private static final long ANIMATION_DELAY = 20;
    private boolean onlyOnce = true;

    public ViewFinderView(Context context) {
        super(context);
        init();
    }

    public ViewFinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        spark = BitmapFactory.decodeResource(getResources(), R.drawable.scanner);
        paint = new Paint();
        sparkRect = new Rect();
    }

    public void setupViewFinder() {
        updateFramingRect();
        invalidate();
    }

    public Rect getFramingRect() {
        return mFramingRect;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (mFramingRect == null) {
            return;
        }

        drawViewFinderMask(canvas);
        drawViewFinderBorder(canvas);
        drawLaser(canvas);
    }

    public void drawViewFinderMask(Canvas canvas) {
        Paint paint = new Paint();
        Resources resources = getResources();
        paint.setColor(resources.getColor(R.color.viewfinder_mask));

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        canvas.drawRect(0, 0, width, mFramingRect.top, paint);
        canvas.drawRect(0, mFramingRect.top, mFramingRect.left, mFramingRect.bottom + 1, paint);
        canvas.drawRect(mFramingRect.right + 1, mFramingRect.top, width, mFramingRect.bottom + 1, paint);
        canvas.drawRect(0, mFramingRect.bottom + 1, width, height, paint);
    }

    public void drawViewFinderBorder(Canvas canvas) {
        Paint paint = new Paint();
        Resources resources = getResources();
        paint.setColor(resources.getColor(R.color.viewfinder_border));
        paint.setStyle(Paint.Style.STROKE);
        ScreenTool_ tool=ScreenTool_.getInstance_(getContext());

        paint.setStrokeWidth(tool.toDip(16));
        int lineLength =  tool.toDip(16);

        canvas.drawLine(mFramingRect.left - 1, mFramingRect.top - 1, mFramingRect.left - 1, mFramingRect.top - 1 + lineLength, paint);
        canvas.drawLine(mFramingRect.left - 2, mFramingRect.top - 1, mFramingRect.left - 1 + lineLength, mFramingRect.top - 1, paint);

        canvas.drawLine(mFramingRect.left - 1, mFramingRect.bottom + 1, mFramingRect.left - 1, mFramingRect.bottom + 1 - lineLength, paint);
        canvas.drawLine(mFramingRect.left - 2, mFramingRect.bottom + 1, mFramingRect.left - 1 + lineLength, mFramingRect.bottom + 1, paint);

        canvas.drawLine(mFramingRect.right + 1, mFramingRect.top - 1, mFramingRect.right + 1, mFramingRect.top - 1 + lineLength, paint);
        canvas.drawLine(mFramingRect.right + 2, mFramingRect.top - 1, mFramingRect.right + 1 - lineLength, mFramingRect.top - 1, paint);

        canvas.drawLine(mFramingRect.right + 1, mFramingRect.bottom + 1, mFramingRect.right + 1, mFramingRect.bottom + 1 - lineLength, paint);
        canvas.drawLine(mFramingRect.right + 2, mFramingRect.bottom + 1, mFramingRect.right + 1 - lineLength, mFramingRect.bottom + 1, paint);

        TextView textView = (TextView) BarcodeScannerView.layout.getChildAt(1);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, mFramingRect.bottom + 15, 0, 0);
        textView.setLayoutParams(params);
        textView.setText("将二维码放入框内，即可自动扫描");
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
    }


    private void drawAnim() {
        if (onlyOnce) {
            ImageView imageView = (ImageView) BarcodeScannerView.layout.getChildAt(2);
            imageView.setImageBitmap(spark);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(mFramingRect.width(), RelativeLayout.LayoutParams.WRAP_CONTENT));
            TranslateAnimation anim = new TranslateAnimation(mFramingRect.left, mFramingRect.left, mFramingRect.top, mFramingRect.bottom);
            anim.setDuration(1500);
            anim.setRepeatCount(Animation.INFINITE);
            imageView.startAnimation(anim);
            onlyOnce=false;
        }
    }

    public void drawLaser(Canvas canvas) {
        drawAnim();
//        position = mFramingRect.height() / 2;
//        sparkRect.set(mFramingRect.left, mFramingRect.top + position, mFramingRect.right, mFramingRect.top + position + 2);
////        Resources resources = getResources();
//        // Draw a red "laser scanner" line through the middle to show decoding is active
////        paint.setColor(resources.getColor(R.color.viewfinder_laser));
//        paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
////        paint.setStyle(Paint.Style.FILL);
//        scannerAlpha = (scannerAlpha + 1) % SCANNER_ALPHA.length;
////        position = (position + STEP_SIZE) % (mFramingRect.top - mFramingRect.bottom);
////        int middle = mFramingRect.height() / 2 + mFramingRect.top;
////        canvas.drawRect(mFramingRect.left + 2, middle - 1, mFramingRect.right - 1, middle + 2, paint);
//        canvas.drawBitmap(spark, null, sparkRect, paint);
//        postInvalidateDelayed(ANIMATION_DELAY,
//                mFramingRect.left - POINT_SIZE,
//                mFramingRect.top - POINT_SIZE,
//                mFramingRect.right + POINT_SIZE,
//                mFramingRect.bottom + POINT_SIZE);
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
        updateFramingRect();
    }

    public synchronized void updateFramingRect() {
        Point viewResolution = new Point(getWidth(), getHeight());
        if (viewResolution == null) {
            return;
        }
        int width;
        int height;
        int orientation = DisplayUtils.getScreenOrientation(getContext());

        if (orientation != Configuration.ORIENTATION_PORTRAIT) {
            width = findDesiredDimensionInRange(LANDSCAPE_WIDTH_RATIO, viewResolution.x, MIN_FRAME_WIDTH, LANDSCAPE_MAX_FRAME_WIDTH);
            height = findDesiredDimensionInRange(LANDSCAPE_HEIGHT_RATIO, viewResolution.y, MIN_FRAME_HEIGHT, LANDSCAPE_MAX_FRAME_HEIGHT);
        } else {
            width = findDesiredDimensionInRange(PORTRAIT_WIDTH_RATIO, viewResolution.x, MIN_FRAME_WIDTH, PORTRAIT_MAX_FRAME_WIDTH);
            height = findDesiredDimensionInRange(PORTRAIT_HEIGHT_RATIO, viewResolution.y, MIN_FRAME_HEIGHT, PORTRAIT_MAX_FRAME_HEIGHT);
        }
        int sideLength = width < height ? width : height;
        int leftOffset = (viewResolution.x - sideLength) / 2;
        int topOffset = (viewResolution.y - sideLength) / 3;
        mFramingRect = new Rect(leftOffset, topOffset, leftOffset + sideLength, topOffset + sideLength);
    }

    private static int findDesiredDimensionInRange(float ratio, int resolution, int hardMin, int hardMax) {
        int dim = (int) (ratio * resolution);
        if (dim < hardMin) {
            return hardMin;
        }
        if (dim > hardMax) {
            return hardMax;
        }
        return dim;
    }

}
