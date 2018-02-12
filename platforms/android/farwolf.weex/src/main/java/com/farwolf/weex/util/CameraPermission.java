package com.farwolf.weex.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by zhengjiangrong on 2018/2/12.
 */

public class CameraPermission {

    public static boolean check(Context c)
    {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(c, Manifest.permission.CAMERA);
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){

                return false;
            }
            return true;
        }
        return true;
    }


    public static void requestCameraPermission(Activity a) {

        // 相机权限未被授予，需要申请！
        if (ActivityCompat.shouldShowRequestPermissionRationale(a,
                Manifest.permission.CAMERA)) {
            // 如果访问了，但是没有被授予权限，则需要告诉用户，使用此权限的好处
            ActivityCompat.requestPermissions(a,
                    new String[]{Manifest.permission.CAMERA},
                    222);
        } else {
            // 第一次申请，就直接申请
            ActivityCompat.requestPermissions(a, new String[]{Manifest.permission.CAMERA},
                    222);
        }
    }



}
