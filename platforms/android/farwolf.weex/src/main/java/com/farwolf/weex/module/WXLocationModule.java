package com.farwolf.weex.module;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;
import android.widget.Toast;

import com.farwolf.weex.base.WXModuleBase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by zhengjiangrong on 2018/5/4.
 */

public class WXLocationModule extends WXModuleBase {




    @RequiresPermission(anyOf = {ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION})
    public void start()
    {
        LocationManager  locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        //得到LocationManager实例后，再结合LocationProvider就能够得到经纬度了，LocationProvider分为两种：
        String provider =null;//1.通过GPS定位，较精确。也比較耗电
        //进行定位前，须要对两种LocationProvider是否存在进行推断:
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) )
        {
            provider = LocationManager.NETWORK_PROVIDER;
        }
        else
        {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) )
            {
                provider = LocationManager.GPS_PROVIDER;
            }
        }



        if (provider!=null )
        {


            locationManager.requestLocationUpdates(provider, 1000l, 10.0f, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

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
        else
        {
            //无法定位：1、提示用户打开定位服务；2、跳转到设置界面
            Toast.makeText(getContext(), "无法定位，请打开定位服务", Toast.LENGTH_SHORT).show();
            Intent i = new Intent();
            i.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            getContext().startActivity(i);
        }







    }



    public void query(HashMap param)
    {
        float lat=Float.valueOf(param.get("lat")+"");
        float lon=Float.valueOf(param.get("lon")+"");
        Geocoder gc = new Geocoder(getContext(), Locale.getDefault());
        List<Address> locationList = null;
        try {
            locationList = gc.getFromLocation(lat, lon, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap res=new HashMap();
        Address address = locationList.get(0);//得到Address实例
        String countryName = address.getCountryName();//得到国家名称，比方：中国
        String locality = address.getLocality();//得到城市名称，比方：北京市
        res.put("country",countryName);
        res.put("countryCode",address.getCountryCode());
        res.put("province",address.getSubLocality());
        for (int i = 0; address.getAddressLine(i) != null; i++) {
            String addressLine = address.getAddressLine(i);//得到周边信息。包含街道等。i=0，得到街道名称

        }
    }










}
