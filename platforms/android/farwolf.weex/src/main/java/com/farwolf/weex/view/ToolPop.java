package com.farwolf.weex.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import com.farwolf.base.ViewBase;
import com.farwolf.qrcode.zxing.android.CaptureActivity;
import com.farwolf.util.RegexBase;
import com.farwolf.util.StringUtil;
import com.farwolf.view.FreeDialog;
import com.farwolf.weex.R;
import com.farwolf.weex.activity.WeexActivity;
import com.farwolf.weex.bean.Config;
import com.farwolf.weex.core.local.Local;
import com.farwolf.weex.event.PermissionEvent;
import com.farwolf.weex.event.RefreshEvent;
import com.farwolf.weex.pref.WeexPref_;
import com.farwolf.weex.util.CameraPermission;
import com.farwolf.weex.util.HotRefreshManager;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by zhengjiangrong on 2017/5/8.
 */
@EViewGroup
public class ToolPop extends ViewBase{

    @Pref
    WeexPref_ pref;

    public FreeDialog f;
    @ViewById
    Button hotreload;
    @ViewById
    TextView url;



    @Bean
    Weex weex;
    @ViewById
    Button debug_reconnetc;

    @ViewById
    TextView ip;

    @ViewById
    Button clearcache;
    @ViewById
    Button loaddefault;

    public ToolPop(Context context) {
        super(context);
    }

    public ToolPop(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getViewId() {
        return R.layout.toolpop;
    }

    @Override
    public void init() {

        WeexActivity a=(WeexActivity)getActivity();
        if(WXEnvironment.sDebugServerConnectable)
        {
            hotreload.setText("关闭热更新");

        }
        else
        {
            hotreload.setText("开启热更新");
        }
        debug_reconnetc.setText(!WXEnvironment.sDebugServerConnectable?"开启Debug":"关闭Debug");
       String from= Local.isDiskExist(getContext())?"磁盘":"assets";
        url.setText(from+" "+a.url);

//        ip.setText("debug_ip:"+ Config.debugIp(a));


        String url= pref.url().get();
        String ipx= RegexBase.regexOne(url,"http://",":");
        if(!StringUtil.isNullOrEmpty(ipx))
        {
            ip.setText("debug_ip:"+ ipx);
        }
        else
        {
            ip.setText("debug_ip:"+ Config.debugIp(a));
        }
        EventBus.getDefault().register(this);
//        debug_reconnetc.setText("关闭Debug");


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PermissionEvent event) {

          if(event.type==PermissionEvent.CAMREA)
          {
              f.dismiss();
              Intent in=new Intent(getActivity(), CaptureActivity.class);
              getActivity().startActivityForResult(in,1);
          }

    }


    public String getDebugUrl()
    {
        return  "ws://" + pref.ip().get() + ":8088/debugProxy/native";
    }






    boolean isHotReloadOpen()
    {
        WeexActivity a=(WeexActivity)getActivity();
        return !StringUtil.isNullOrEmpty(a.url)&&a.url.contains(".weex.js");
    }

    @Click
    public void hotreloadClicked() {

        WeexActivity a=(WeexActivity)getActivity();

        if( HotRefreshManager.getInstance().isOpen)
        {

//           a.url=a.url.replace("8082","8081");
//            a.stopHotRefresh();
        }
        else
        {
//           a.url=a.url.replace("8081","8082");
//            a.startHotRefresh();


        }
        a.render(a.url);
        f.dismiss();

    }

    @Click
    public void qrClicked() {

        if(!CameraPermission.check(getActivity()))
        {
            CameraPermission.requestCameraPermission(getActivity());
            return;
        }

        f.dismiss();
        Intent in=new Intent(getActivity(), CaptureActivity.class);
        getActivity().startActivityForResult(in,1);

    }

    @Click
    public void debug_reconnetcClicked() {


        if(WXEnvironment.sDebugServerConnectable)
        {
            stopDebug();
            debug_reconnetc.setText("开启Debug");

        }
        else
        {
            String url= pref.url().get();
            String ip= RegexBase.regexOne(url,"http://",":");
            if(StringUtil.isNullOrEmpty(url)||StringUtil.isNullOrEmpty(ip))
            {
                weex.startDebug(getActivity());
            }
            else
            {
                weex.startDebug(ip);
            }

            debug_reconnetc.setText("关闭Debug");

        }

        f.dismiss();
    }


    void stopDebug()
    {
//        if(WXEnvironment.sDebugServerConnectable)
//        {
            WXEnvironment.sDebugServerConnectable = false;
            WXEnvironment.sRemoteDebugMode = false;
            WXSDKEngine.reload();

        final Handler achandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                ((WeexActivity)getActivity()).reload();

            }
        };
        achandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                achandler.sendEmptyMessage(0);
            }
        }, 300);

//        }


    }

    @Click
    public void closeClicked() {

        f.dismiss();
    }

    @Click
    public void clearcacheClicked() {

//        Picasso.with(imageView.getContext()).load(imageUrl).networkPolicy(NetworkPolicy‌​.NO_CACHE).into(imageView)
    }

    @Click
    public void loaddefaultClicked() {

//        pref.url().put("").;
        ((WeexActivity)getActivity()).url=Config.entry(getActivity());
        pref.edit().url().put("").apply();
        EventBus.getDefault().post(new RefreshEvent("refresh"));
        f.dismiss();

    }
}
