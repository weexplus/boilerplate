package com.farwolf.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.farwolf.base.FragmentBase;
import com.farwolf.business.R;
import com.farwolf.view.qrCode.ScanView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjiangrong on 2017/4/19.
 */

public class QrFragment   extends FragmentBase implements ScanView.ResultHandler, ScanView.QrSize, View.OnClickListener {


    public   ScanView scanner;


    @Override
    public int getViewId() {
            return 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        scanner = new ScanView(getContext());
        scanner.setContentView(getScanViewId());
        scanner.setQrSize(this);
        setupFormats();
        return scanner;
        }


    public void setThemeColor(String color)
    {
        scanner.setCornersColor(color);
    }

    private void delayOpen(final long time) {


        final Handler achandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                scanner.startCamera(); // 打开摄像头

            }
        };
        achandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                achandler.sendEmptyMessage(0);
            }
        }, time);
    }

    public int getScanViewId()
    {
        return  R.layout.api_qrscan_view;
    }

    @Override
    public void onResume() {
        super.onResume();
        scanner.setResultHandler(this); // 设置处理结果回调
        if(!this.check())
        {
            return;
        }
        scanner.startCamera();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            //就像onActivityResult一样这个地方就是判断你是从哪来的。
            case 222:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    scanner.startCamera();
                } else {
                    // Permission Denied
                    Toast.makeText(this.getContext(), "很遗憾你把相机权限禁用了。请务必开启相机权限享受我们提供的服务吧。", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    public boolean check()
    {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.CAMERA);
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this.getActivity(),new String[]{Manifest.permission.CAMERA},222);
                return false;
            }
            return true;
        }
        return true;
    }


    @Override
     public void onPause() {
        super.onPause();
        scanner.stopCamera(); // 活动失去焦点的时候关闭摄像头
        }

     public void setupFormats() {
        List<BarcodeFormat> formats = new ArrayList<BarcodeFormat>();
        formats.add(BarcodeFormat.QR_CODE);
        if (scanner != null) {
        scanner.setFormats(formats);
        }
        }

     @Override
     public void handleResult(Result result) { // 实现回调接口，将数据回传并结束活动
//        Intent data = new Intent();
//        data.putExtra("text", result.getText());
          onResult(result.getText());

        }


    public void onResult(String content)
    {

    }


     @Override
     public void onClick(View v) {

        }

     @Override
     public Rect getDetectRect() {
        View view = findViewById(R.id.scan_window);
        int top = ((View) view.getParent()).getTop() + view.getTop();
        int left = view.getLeft();
        int width = view.getWidth();
        int height = view.getHeight();
        Rect rect = null;
        if (width != 0 && height != 0) {
        rect = new Rect(left, top, left + width, top + height);
        addLineAnim(rect);
        }
        return rect;
        }

      private void addLineAnim(Rect rect) {
        ImageView imageView = (ImageView) findViewById(R.id.scanner_line);
        imageView.setVisibility(View.VISIBLE);
        if (imageView.getAnimation() == null) {
        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, rect.height());
        anim.setDuration(1500);
        anim.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(anim);
        }
        }
        }
