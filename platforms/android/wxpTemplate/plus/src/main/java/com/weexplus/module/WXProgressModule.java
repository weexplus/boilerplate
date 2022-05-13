package com.weexplus.module;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.weexplus.core.WeexPlus;
import com.weexplus.util.StringUtil;
import com.weexplus.view.FreeDialog;
import com.weexplus.view.LoadingDialog;

/**
 * Created by zhengjiangrong on 2017/7/4.
 */

public class WXProgressModule extends WXModule {



//    protected DialogProgress progress;

    FreeDialog f;
    LoadingDialog progress;

    @JSMethod
    public void show()
    {
        JSONObject j=new JSONObject();
//        j.put("txt","加载中");
        j.put("cancel",true);
        j.put("type",15);
        j.put("bgColor","#000000");
        j.put("size",100);
        j.put("radius",50);
//        type:5,bgColor:"#000000",cancel:true,size:100,radius:50,needBg:true
        showFull(j);
    }



    @JSMethod
    public void showFull(JSONObject param)
    {
        String txt=param.getString("txt");
        boolean cancle=param.getBoolean("cancel");
        boolean needBg=true;
        if(param.containsKey("needBg"))
            needBg= param.getBoolean("needBg");
        int type=param.getInteger("type");
        int radius=75;
        if(param.containsKey("radius"))
        {
            radius=param.getInteger("radius");
        }
        int size=150;
        if(param.containsKey("size"))
        {
            size= param.getInteger("size");
        }
        size=(int) WeexPlus.toNative(size);
        radius= (int)WeexPlus.toNative(radius);
        String bgColor=param.getString("bgColor");
        if(f==null)
        {

            Activity a= (Activity)this.mWXSDKInstance.getContext();
            if(a==null||a.isFinishing())
                return;
            progress =new LoadingDialog(this.mWXSDKInstance.getContext());
            f=new FreeDialog(this.mWXSDKInstance.getContext(),progress);
            f.setCancelable(cancle);
            f.setCanceledOnTouchOutside(cancle);
            progress.f=f;
            progress.setStyle(type);
            if(!StringUtil.isNullOrEmpty(bgColor))
            progress.setBgColor(bgColor,radius);
            if(!needBg)
            f.getWindow().setDimAmount(0f);
            progress.setSize(size);
            if(!StringUtil.isNullOrEmpty(txt)){
                progress.setTxt(txt);
            }

        }
        progress.txt.setText(txt);
        f.show();

    }




    @JSMethod
    public void dismiss()
    {
        if(f==null||!f.isShowing())
            return;
        Activity a= (Activity)this.mWXSDKInstance.getContext();
        if(a==null||a.isFinishing())
        {
            return;
        }
        f.dismiss();
    }


    @Override
    public void onActivityDestroy() {
        super.onActivityDestroy();
        if(f==null||f.isShowing())
        {
            f.dismiss();
        }
    }
}
