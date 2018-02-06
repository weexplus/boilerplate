package com.farwolf.weex.adapter;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by zhengjiangrong on 2017/11/10.
 */

public class GlideCircleTransform extends BitmapTransformation {


    float radius;

    public GlideCircleTransform(Context context,float radius) {
        super(context);
        this.radius=radius;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    private Bitmap circleCrop(BitmapPool pool, Bitmap toTransform) {
        return toTransform;
//        if (toTransform == null) return null;
//        int size = Math.min(toTransform.getWidth(), toTransform.getHeight());
//        int x = (toTransform.getWidth() - size) / 2;
//        int y = (toTransform.getHeight() - size) / 2;
//
//        // TODO this could be acquired from the pool too
//        Bitmap squared = Bitmap.createBitmap(toTransform, x, y, size, size);
//
//        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
//        if (result == null) {
//            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
//        }
//        /**
//         * 这里的操作可以自己绘制任何你想要的图形,例如带边框,边框带色等等
//         */
//        Canvas canvas = new Canvas(result);
//        Paint paint = new Paint();
//        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
//        paint.setAntiAlias(true);
//        float r = size / 2f;
//        float ra=r;
//        canvas.drawCircle(r, r, ra, paint);
//        return result;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}
