package com.weexplus.util;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Build;

public class PermissionManger {

    PermissionFragment permissionFragment;
    static final String TAG = "HundsunPermissions";

    Activity activity;

    public PermissionManger(Activity activity) {
        this.activity = activity;
        permissionFragment=getRxPermissionsFragment(activity);
    }



    public void setGoSetting(String per,PerssionCallback callback , final PerssionDenyCallback denyCallback){
        permissionFragment.request_permission=per;
        permissionFragment.passCallback=callback;
        permissionFragment.denyCallback=denyCallback;
    }


    private PermissionFragment getRxPermissionsFragment(Activity activity) {
        PermissionFragment rxPermissionsFragment = findPermissionsFragment(activity);
        boolean isNewInstance = rxPermissionsFragment == null;
        if (isNewInstance) {
            rxPermissionsFragment = new PermissionFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(rxPermissionsFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return rxPermissionsFragment;
    }

    private PermissionFragment findPermissionsFragment(Activity activity) {
        return (PermissionFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }



    public void check(String permission, Callback callback){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionFragment.check(permission,callback);
        }else{
            //6.0以下是安装时检测，不同意不让安装，所以直接是true
            callback.onInvoke(true);
        }


    }



}
