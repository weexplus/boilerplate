/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.taobao.weex.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXRuntimeException;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.flat.widget.Widget;
import com.taobao.weex.ui.flat.widget.WidgetGroup;
import com.taobao.weex.ui.view.border.BorderDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Utility class for views
 */
public class WXViewUtils {

  @IntDef({PixelFormat.TRANSLUCENT, PixelFormat.TRANSPARENT, PixelFormat.OPAQUE})
  @Retention(RetentionPolicy.SOURCE)
  public @interface Opacity {}

  /**
   * Use {@link PixelFormat#TRANSLUCENT} instead
   */
  @Deprecated
  public static final int TRANSLUCENT = PixelFormat.TRANSLUCENT;

  /**
   * Use {@link PixelFormat#TRANSPARENT} instead.
   */
  @Deprecated
  public static final int TRANSPARENT = PixelFormat.TRANSPARENT;

  /**
   * Use {@link PixelFormat#OPAQUE} instead
   */
  @Deprecated
  public static final int OPAQUE = PixelFormat.OPAQUE;

  public static final int DIMENSION_UNSET = -1;
  private static final boolean mUseWebPx = false;

  private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

  @SuppressLint("NewApi")
  public static int generateViewId() {

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
      for (;;) {
        final int result = sNextGeneratedId.get();
        // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
        int newValue = result + 1;
        if (newValue > 0x00FFFFFF)
          newValue = 1; // Roll over to 1, not 0.
        if (sNextGeneratedId.compareAndSet(result, newValue)) {
          return result;
        }
      }
    } else {
      return View.generateViewId();
    }
  }

  private static int mScreenWidth;
  private static int mScreenHeight;


  public static int getWeexHeight(String instanceId){
    WXSDKInstance instance = WXSDKManager.getInstance().getSDKInstance(instanceId);
    if (instance != null) {
      int weexHeight = instance.getWeexHeight();
      if (weexHeight >= 0 || weexHeight == -2) {
        return weexHeight;
      }
      else {
        return getScreenHeight(WXEnvironment.sApplication);
      }
    }
    return -3;
  }



  public static int getWeexWidth(String instanceId){
    WXSDKInstance instance = WXSDKManager.getInstance().getSDKInstance(instanceId);
    if (instance != null) {
      int weexWidth = instance.getWeexWidth();
      if (weexWidth >= 0 || weexWidth == -2) {
        return weexWidth;
      }
      else {
        return getScreenWidth(WXEnvironment.sApplication);
      }
    }
    return -3;
  }

  @Deprecated
  public static int getScreenWidth( ) {
    return getScreenWidth(WXEnvironment.sApplication);
  }

  @Deprecated
  public static int setScreenWidth(int screenWidth) {
    return mScreenWidth = screenWidth;
  }

  public static float getScreenDensity(Context ctx){
    if(ctx != null){
      try{
        Resources res = ctx.getResources();
        return res.getDisplayMetrics().density;
      }catch (Exception e){
        WXLogUtils.e("getScreenDensityDpi exception:"+e.getMessage());
      }
    }
    return Constants.Value.DENSITY;
  }

  public static void updateApplicationScreen(Context context){
    if(context == null || WXEnvironment.sApplication == null){
        return;
    }
    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
    DisplayMetrics displayMetrics = WXEnvironment.sApplication.getResources().getDisplayMetrics();
    displayMetrics.heightPixels = metrics.heightPixels;
    displayMetrics.widthPixels = metrics.widthPixels;
    displayMetrics.density = metrics.density;
    displayMetrics.densityDpi = metrics.densityDpi;
    displayMetrics.scaledDensity = metrics.scaledDensity;
    displayMetrics.xdpi = metrics.xdpi;
  }

  public static int getScreenWidth(Context ctx) {
    if(ctx!=null){
      Resources res = ctx.getResources();
      mScreenWidth = res.getDisplayMetrics().widthPixels;
      if(WXEnvironment.SETTING_FORCE_VERTICAL_SCREEN){
        mScreenHeight = res
                .getDisplayMetrics()
                .heightPixels;
        mScreenWidth = mScreenHeight > mScreenWidth ? mScreenWidth : mScreenHeight;
      }
    } else if(WXEnvironment.isApkDebugable()){
      throw new WXRuntimeException("Error Context is null When getScreenHeight");
    }
    return mScreenWidth;
  }


  public static int getStatusBarHeight(Context context){
      Resources resources = context.getResources();
      int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
      if (resourceId > 0) {
        int statusBarHeight = resources.getDimensionPixelSize(resourceId);
        return statusBarHeight;
      }
      return -1;
  }


  @Deprecated
  public static int getScreenHeight() {
    return getScreenHeight(WXEnvironment.sApplication);
  }


  public static int getScreenHeight(String instanceId){
    WXSDKInstance instance = WXSDKManager.getInstance().getSDKInstance(instanceId);
    return instance.isFullScreenHeightEnabled()?getFullScreenHeight(WXEnvironment.sApplication):getScreenHeight(WXEnvironment.sApplication);
  }

//get screen height with status bar on full screen
  public static int getFullScreenHeight(Context cxt) {
    if(cxt!=null){
      WindowManager wm;
      Resources res = cxt.getResources();
      if(Build.VERSION.SDK_INT >= 17 && (wm = (WindowManager)cxt.getSystemService(Context.WINDOW_SERVICE)) != null
              && wm.getDefaultDisplay() != null){
        Point size = new Point();
        wm.getDefaultDisplay().getRealSize(size);
        mScreenHeight = size.y;
      }
      else {
        mScreenHeight = cxt.getResources().getDisplayMetrics().heightPixels;
      }
      if(WXEnvironment.SETTING_FORCE_VERTICAL_SCREEN){
        mScreenWidth = res
                .getDisplayMetrics()
                .widthPixels;
        mScreenHeight = mScreenHeight > mScreenWidth ? mScreenHeight : mScreenWidth;
      }
    } else if (WXEnvironment.isApkDebugable()){
      throw new WXRuntimeException("Error Context is null When getScreenHeight");
    }
    return mScreenHeight;
  }
//  get screen height without status bar
  public static int getScreenHeight(Context cxt) {
    if(cxt!=null){
      Resources res = cxt.getResources();
      mScreenHeight = res.getDisplayMetrics().heightPixels;
      if(WXEnvironment.SETTING_FORCE_VERTICAL_SCREEN){
        mScreenWidth = res
                .getDisplayMetrics()
                .widthPixels;
        mScreenHeight = mScreenHeight > mScreenWidth ? mScreenHeight : mScreenWidth;
      }
    } else if (WXEnvironment.isApkDebugable()){
      throw new WXRuntimeException("Error Context is null When getScreenHeight");
    }
    return mScreenHeight;
  }


  /**
   * Convert distance from JS,CSS to native. As the JS considers the width of the screen is 750px.
   * There must be a transform when accessing distance from JS,CSS and use it.
   * Basically, this method calculates a scale factor(ScreenWidth/750) and use apply this scale
   * factor to JS,CSS distance.
   * @param pxValue the raw distance from JS or CSS. The result will be rounded to a closet int.
   * @return the actual distance in the screen.
   */

  @Deprecated
  public static float getRealPxByWidth(float pxValue) {
    return getRealPxByWidth(pxValue,750);
  }
  public static float getRealPxByWidth(float pxValue,int customViewport) {
    if (Float.isNaN(pxValue)) {
      return pxValue;
    }
    if (mUseWebPx) {
      return (float) Math.rint(pxValue);
    } else {
      float realPx = (pxValue * getScreenWidth() / customViewport);
      return realPx > 0.005 && realPx < 1 ? 1 : (float) Math.rint(realPx);
    }
  }

  @Deprecated
  public static float getRealSubPxByWidth(float pxValue) {
    return getRealSubPxByWidth(pxValue,750);
  }

  public static float getRealSubPxByWidth(float pxValue,int customViewport) {
    if (Float.isNaN(pxValue)) {
      return pxValue;
    }
    if (mUseWebPx) {
      return (float) Math.rint(pxValue);
    } else {
      float realPx = (pxValue * getScreenWidth() / customViewport);
      return realPx > 0.005 && realPx < 1 ? 1 : realPx;
    }
  }

  /**
   *  Internal interface that just for debug, you should never call this method because of accuracy loss obviously
   */
  @Deprecated
  public static float getWeexPxByReal(float pxValue) {
    return getWeexPxByReal(pxValue,750);
  }

  public static float getWeexPxByReal(float pxValue,int customViewport) {
    if (Float.isNaN(pxValue)) {
      return pxValue;
    }
    if (mUseWebPx) {
      return (float) Math.rint(pxValue);
    } else {
      return pxValue * customViewport / getScreenWidth();
    }
  }

  @Deprecated
  public static float getRealPxByWidth2(float pxValue) {
    return getRealPxByWidth2(pxValue,750);
  }
  public static int getRealPxByWidth2(float pxValue,int customViewport) {
    if (mUseWebPx) {
      return (int) pxValue;
    } else {
      float realPx = (pxValue * getScreenWidth() / customViewport);
      return realPx > 0.005 && realPx < 1 ? 1 : (int) realPx - 1;
    }
  }

  /**
   * Convert distance from native to JS,CSS. As the JS considers the width of the screen is 750px.
   * There must be a transform when return distance to JS,CSS.
   * Basically, this method calculates a scale factor(ScreenWidth/750) and use apply this scale
   * factor to native distance.
   * @param pxValue the raw distance of native. The result will be rounded to a closet int.
   * @return the distance in JS,CSS where the screenWidth is 750 px.
   */
  @Deprecated
  public static float getWebPxByWidth(float pxValue) {
    return getWebPxByWidth(pxValue,750);
  }

  public static float getWebPxByWidth(float pxValue,int customViewport) {
    if (pxValue < -1.9999 && pxValue > -2.005) {
      return Float.NaN;
    }
    if (mUseWebPx) {
      return pxValue;
    } else {
      float realPx = (pxValue * customViewport / getScreenWidth());
      return realPx > 0.005 && realPx < 1 ? 1 : realPx;
    }
  }


  /**
   * Convert dp to px
   * @param dpValue the dp value to be converted
   * @return the px value
   */
  public static int dip2px(float dpValue) {
    float scale = 2;
    try {
      scale = WXEnvironment.getApplication().getResources()
              .getDisplayMetrics().density;
    } catch (Exception e) {
      WXLogUtils.e("[WXViewUtils] dip2px:", e);
    }
    float finalPx = (dpValue * scale + 0.5f);
    return finalPx > 0 && finalPx < 1 ? 1 : (int) finalPx;
  }

  public static boolean onScreenArea(View view) {
    if (view == null || view.getVisibility() != View.VISIBLE) {
      return false;
    }

    int[] p = new int[2];
    view.getLocationOnScreen(p);
    LayoutParams lp = view.getLayoutParams();
    int viewH = 0;
    if (lp != null) {
      viewH = lp.height;
    } else {
      viewH = view.getHeight();
    }

    return (p[1] > 0 && (p[1] - WXViewUtils.getScreenHeight(WXEnvironment.sApplication) < 0))
            || (viewH + p[1] > 0 && p[1] <= 0);
  }

  /**
   * Multiplies the color with the given alpha.
   *
   * @param color color to be multiplied
   * @param alpha value between 0 and 255
   * @return multiplied color
   */
  public static int multiplyColorAlpha(int color, int alpha) {
    if (alpha == 255) {
      return color;
    }
    if (alpha == 0) {
      return color & 0x00FFFFFF;
    }
    alpha = alpha + (alpha >> 7); // make it 0..256
    int colorAlpha = color >>> 24;
    int multipliedAlpha = colorAlpha * alpha >> 8;
    return (multipliedAlpha << 24) | (color & 0x00FFFFFF);
  }

  @Opacity
  public static int getOpacityFromColor(int color) {
    int colorAlpha = color >>> 24;
    if (colorAlpha == 255) {
      return PixelFormat.OPAQUE;
    } else if (colorAlpha == 0) {
      return PixelFormat.TRANSPARENT;
    } else {
      return PixelFormat.TRANSLUCENT;
    }
  }

  @SuppressWarnings("deprecation")
  public static void setBackGround(View view, Drawable drawable, WXComponent component) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN){
      view.setBackgroundDrawable(drawable);
    }
    else{
      try {
        view.setBackground(drawable);
      } catch (Exception e) {
        if (component == null)
          return;
        WXExceptionUtils.commitCriticalExceptionRT(component.getInstanceId(),
                WXErrorCode.WX_RENDER_ERR_TEXTURE_SETBACKGROUND,
                component.getComponentType() + " setBackGround for android view",
                WXErrorCode.WX_RENDER_ERR_TEXTURE_SETBACKGROUND.getErrorMsg() + ": TextureView doesn't support displaying a background drawable!",
                null);
      }
    }
  }

  public static @Nullable
  BorderDrawable getBorderDrawable(@NonNull View view){
    Drawable drawable=view.getBackground();
    if(drawable instanceof BorderDrawable){
      return (BorderDrawable) drawable;
    }
    else if(drawable instanceof LayerDrawable){
      if(((LayerDrawable) drawable).getNumberOfLayers()>1) {
        Drawable innerDrawable=((LayerDrawable) drawable).getDrawable(0);
        if(innerDrawable instanceof BorderDrawable){
          return (BorderDrawable) innerDrawable;
        }
      }
    }
    return null;
  }

  public static void clipCanvasWithinBorderBox(View targetView, Canvas canvas) {
    Drawable drawable;
    if (clipCanvasDueToAndroidVersion(canvas) &&
            clipCanvasIfAnimationExist(targetView) &&
            ((drawable = targetView.getBackground()) instanceof BorderDrawable)) {
      BorderDrawable borderDrawable = (BorderDrawable) drawable;
      if (borderDrawable.isRounded()) {
        if (clipCanvasIfBackgroundImageExist(targetView, borderDrawable)) {
          Path path = borderDrawable.getContentPath(
                  new RectF(0, 0, targetView.getWidth(), targetView.getHeight()));
          canvas.clipPath(path);
        }
      }
    }
  }

  public static void clipCanvasWithinBorderBox(Widget widget, Canvas canvas) {
    BorderDrawable borderDrawable;
    if (clipCanvasDueToAndroidVersion(canvas) &&
            clipCanvasIfAnimationExist(null) &&
            (borderDrawable=widget.getBackgroundAndBorder())!=null ) {
      if (borderDrawable.isRounded() && clipCanvasIfBackgroundImageExist(widget, borderDrawable)) {
        Path path = borderDrawable.getContentPath(
                new RectF(0, 0, widget.getBorderBox().width(), widget.getBorderBox().height()));
        canvas.clipPath(path);
      }
      else {
        canvas.clipRect(widget.getBorderBox());
      }
    }
  }

  /**
   * According to https://developer.android.com/guide/topics/graphics/hardware-accel.html#unsupported
   API 18 or higher supports clipPath to canvas based on hardware acceleration.
   * @param canvas
   * @return
   */
  private static boolean clipCanvasDueToAndroidVersion(Canvas canvas) {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 ||
            !canvas.isHardwareAccelerated();
  }

  /**
   * According to https://code.google.com/p/android/issues/detail?id=225556&sort=-id&colspec=ID
   * clipPath doesn't work with rotation nor scale when API level is 24.
   * As animation will not cause redraw if hardware-acceleration enabled, clipCanvas feature has
   * to be disabled when API level is 24 without considering the animation property.
   */
  private static boolean clipCanvasIfAnimationExist(View targetView) {
    if (Build.VERSION.SDK_INT != VERSION_CODES.N) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Due limitation in Android platform, the linear gradient in the following page will not be
   * rounded if {@link Canvas#clipPath(Path)} of the parent view invoked when API level is lower
   * than 21.
   * http://dotwe.org/weex/963c9ade129f86757cecdd85651cd30e
   * @param targetView
   * @param borderDrawable
   * @return
   */
  private static boolean clipCanvasIfBackgroundImageExist(@NonNull View targetView,
                                                          @NonNull BorderDrawable borderDrawable) {
    if (targetView instanceof ViewGroup) {
      View child;
      ViewGroup parent = ((ViewGroup) targetView);
      int count = parent.getChildCount();
      for (int i = 0; i < count; i++) {
        child = parent.getChildAt(i);
        if (child.getBackground() instanceof BorderDrawable &&
                ((BorderDrawable) child.getBackground()).hasImage() &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
          return false;
        }
      }
    }
    return true;
  }

  private static boolean clipCanvasIfBackgroundImageExist(@NonNull Widget widget,
                                                          @NonNull BorderDrawable borderDrawable) {
    if (widget instanceof WidgetGroup) {
      for (Widget child : ((WidgetGroup) widget).getChildren()) {
        if (child.getBackgroundAndBorder().hasImage() &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
          return false;
        }
      }
    }
    return true;
  }

  public static boolean isViewVisible(View v) {
    if (null == v){
      return false;
    }

    boolean isAttachToWindow = Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT
        ?v.isAttachedToWindow()
        :v.getWindowToken() != null;

    if (!isAttachToWindow){
      return false;
    }
    if (v.getVisibility() != View.VISIBLE || v.getAlpha()<=0){
      return false;
    }

    Drawable bacDrawable = v.getBackground();
    if (null == bacDrawable){
      return true;
    }
    if (Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT){
      return bacDrawable.getAlpha()>0;
    }
    //< 4.4
    if (bacDrawable instanceof ColorDrawable){
      int alpha = Color.alpha(((ColorDrawable) bacDrawable).getColor());
      return alpha >0;
    }else if (bacDrawable instanceof BitmapDrawable){
      Paint paint = ((BitmapDrawable) bacDrawable).getPaint();
      return paint.getAlpha() > 0;
    }
    return true;
  }
}
