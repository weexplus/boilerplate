package com.farwolf.weex.module;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by zhengjiangrong on 2018/5/4.
 */

public class WXLocationModule extends WXModuleBase {

    Map param;
    JSCallback callback;




    @JSMethod
    @RequiresPermission(anyOf = {ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION})
    public void start(Map param, final JSCallback callback)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            int cros = ContextCompat.checkSelfPermission(mWXSDKInstance.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
            int fine = ContextCompat.checkSelfPermission(mWXSDKInstance.getContext(), Manifest.permission.ACCESS_FINE_LOCATION);

            if (cros != PackageManager.PERMISSION_GRANTED)
            {
                this.callback=callback;
                ActivityCompat.requestPermissions((Activity) this.mWXSDKInstance.getContext(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        11);
                return;
            }
            if (fine != PackageManager.PERMISSION_GRANTED)
            {
                this.callback=callback;
                ActivityCompat.requestPermissions((Activity) this.mWXSDKInstance.getContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        22);
                return;
            }

            this.dostart(param,callback);


        }
        else
        {
            this.dostart(param,callback);
        }
    }



    @RequiresPermission(anyOf = {ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION})

    public void dostart(Map param, final JSCallback callback) {
        this.param=param;
        this.callback=callback;
        boolean once =true;
        if(param!=null&&param.containsKey("once"))
            once= Boolean.valueOf(param.get("once") + "");




        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        //得到LocationManager实例后，再结合LocationProvider就能够得到经纬度了，LocationProvider分为两种：
        String provider = null;//1.通过GPS定位，较精确。也比較耗电
        //进行定位前，须要对两种LocationProvider是否存在进行推断:
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                provider = LocationManager.GPS_PROVIDER;
            }
        }


        if (provider != null) {

            if (once) {

                locationManager.requestSingleUpdate(provider, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        HashMap param = new HashMap();
                        param.put("lat", location.getLatitude());
                        param.put("lon", location.getLongitude());
                        query(param, callback);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                }, Looper.myLooper());
            } else {
                locationManager.requestLocationUpdates(provider, 1000l, 10.0f, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        HashMap param = new HashMap();
                        param.put("lat", location.getLatitude());
                        param.put("lon", location.getLongitude());
                        query(param, callback);

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });
            }


        } else {
            //无法定位：1、提示用户打开定位服务；2、跳转到设置界面
            Toast.makeText(getContext(), "无法定位，请打开定位服务", Toast.LENGTH_SHORT).show();
            Intent i = new Intent();
            i.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            getContext().startActivity(i);
        }


    }


    @RequiresPermission(anyOf = {ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION})
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int c=0;
        if(requestCode==11||requestCode==22)
        {
            int cros = ContextCompat.checkSelfPermission(mWXSDKInstance.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
            int fine = ContextCompat.checkSelfPermission(mWXSDKInstance.getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
            if (cros== PackageManager.PERMISSION_GRANTED&&fine==PackageManager.PERMISSION_GRANTED)
            {
                start(param,callback);
            }

        }




    }


    public void query(HashMap param, JSCallback callback) {
        float lat = Float.valueOf(param.get("lat") + "");
        float lon = Float.valueOf(param.get("lon") + "");
        Geocoder gc = new Geocoder(getContext(), Locale.getDefault());
        List<Address> locationList = null;
        try {
            locationList = gc.getFromLocation(lat, lon, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }


        HashMap res = new HashMap();
        res.putAll(param);
        if(locationList==null)
        {
            res.put("err", 1);
            callback.invokeAndKeepAlive(res);
            return;
        }
        Address address = locationList.get(0);//得到Address实例
        String countryName = address.getCountryName();//得到国家名称，比方：中国
        String locality = address.getLocality();//得到城市名称，比方：北京市
        res.put("country", countryName);
        res.put("err", 0);
        res.put("countryCode", address.getCountryCode());
        res.put("country", address.getCountryName());
        res.put("province", address.getAdminArea());
        res.put("city", address.getLocality());
        res.put("subLocality", address.getSubLocality());
        res.put("distreet", address.getFeatureName());
        res.put("lat", address.getLatitude());
        res.put("lon", address.getLongitude());
        String add="";
        for (int i = 0; address.getAddressLine(i) != null; i++) {
            add+= address.getAddressLine(i);//得到周边信息。包含街道等。i=0，得到街道名称
        }
        res.put("address", add);
        callback.invokeAndKeepAlive(res);
    }


}
