package com.weexplus.util;


import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class PermissionFragment extends Fragment {

    final int REQUEST_PERMISSION_CODE=10998;
    public   String request_permission="";
    public PerssionCallback passCallback;
    public   PerssionDenyCallback denyCallback;
    Callback callback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_PERMISSION_CODE){
            boolean granted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            callback.onInvoke(granted);
        }


    }


    @Override
    public void onResume() {
        super.onResume();
        if(!StringUtil.isNullOrEmpty(request_permission)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(isGranted(request_permission)){
                    if(passCallback!=null)
                    passCallback.onGranted();
                }else{
                    if(denyCallback!=null)
                    denyCallback.onDeny();
                }
            }else{
                if(passCallback!=null)
                    passCallback.onGranted();
            }
            passCallback=null;
            denyCallback=null;
            request_permission="";
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void check(String permission, Callback callback){
        if(isGranted(permission)){
            callback.onInvoke(true);
            return;
        }
        if(isRevoked(permission)){
            callback.onInvoke(false);
            return;
        }
        this.callback=callback;
        String []ps=new String[1];
        ps[0]=permission;
        requestPermissions(ps,REQUEST_PERMISSION_CODE);
    }


    @RequiresApi(Build.VERSION_CODES.M)
    boolean isRevoked(String permission) {
        return getActivity().getPackageManager().isPermissionRevokedByPolicy(permission, getActivity().getPackageName());
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    boolean isGranted(String permission) {
        return getActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

}
