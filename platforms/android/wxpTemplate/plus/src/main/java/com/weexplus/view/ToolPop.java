package com.weexplus.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.weex.weexplus.R;

import com.weexplus.activity.WxpActivity;
import com.weexplus.core.render.Local;
import com.weexplus.debug.HotRefreshManager;
import com.weexplus.event.RefreshEvent;
import com.weexplus.qr.CaptureActivity;
import com.weexplus.util.Config;
import com.weexplus.util.Permission;
import com.weexplus.util.PerssionCallback;
import com.weexplus.util.Pref;
import com.weexplus.util.RegexBase;
import com.weexplus.util.StringUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by zhengjiangrong on 2017/5/8.
 */

public class ToolPop extends LinearLayout {



    public FreeDialog f;

    Button hotreload;

    TextView url;




    Button debug_reconnetc;


    TextView ip;




    public ToolPop(Context context) {
        super(context);
        init();
    }

    public ToolPop(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }




    public void init() {

        LayoutInflater.from(getContext()).inflate(R.layout.api_toolpop_view,this);
        WxpActivity a=(WxpActivity)getContext();
        this.hotreload=findViewById(R.id.hotreload);
        this.url=findViewById(R.id.url);
        this.ip=findViewById(R.id.ip);
        this.debug_reconnetc=findViewById(R.id.debug_reconnetc);

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



        String url= Pref.getString(getContext(),"url","");
        String ipx= RegexBase.regexOne(url,"http://",":");
        if(!StringUtil.isNullOrEmpty(ipx))
        {
            ip.setText("debug_ip:"+ ipx);
        }
        else
        {
            ip.setText("debug_ip:"+ Config.debugIp(a));
        }
//        EventBus.getDefault().register(this);

        findViewById(R.id.close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                f.dismiss();
            }
        });
        findViewById(R.id.qr).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Permission.checkOne((Activity) getContext(), Manifest.permission.CAMERA,"",new PerssionCallback(){


                    @Override
                    public void onGranted() {

                        f.dismiss();
                        Activity ac=(Activity)getContext();
                        Intent in=new Intent(ac, CaptureActivity.class);
                        ac.startActivityForResult(in,1);

                    }
                });
            }
        });
        hotreload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WxpActivity a=(WxpActivity)getContext();
                a.render(a.url,a.module);
                f.dismiss();
            }
        });
        findViewById(R.id.loaddefault).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((WxpActivity)getContext()).url=Config.debugEntry(getContext());
                Pref.setString(getContext(),"url","");
                EventBus.getDefault().post(new RefreshEvent("refresh"));
                f.dismiss();
            }
        });



    }




    public String getDebugUrl()
    {
        String ip= Pref.getString(getContext(),"ip","");
        return  "ws://" + ip + ":8088/debugProxy/native";
    }






    boolean isHotReloadOpen()
    {
        WxpActivity a=(WxpActivity)getContext();
        return !StringUtil.isNullOrEmpty(a.url)&&a.url.contains(".weex.js");
    }







    public void debug_reconnetcClicked() {


        if(WXEnvironment.sDebugServerConnectable)
        {
            stopDebug();
            debug_reconnetc.setText("开启Debug");

        }
        else
        {
            String url= Pref.getString(getContext(),"url","");
            String ip= RegexBase.regexOne(url,"http://",":");
            HotRefreshManager.getInstance().send("opendebug");
            debug_reconnetc.setText("关闭Debug");

        }

        f.dismiss();
    }


    void stopDebug()
    {

            WXEnvironment.sDebugServerConnectable = false;
            WXEnvironment.sRemoteDebugMode = false;
            WXSDKEngine.reload();

        final Handler achandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                ((WxpActivity)getContext()).refresh();

            }
        };
        achandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                achandler.sendEmptyMessage(0);
            }
        }, 300);



    }






}
