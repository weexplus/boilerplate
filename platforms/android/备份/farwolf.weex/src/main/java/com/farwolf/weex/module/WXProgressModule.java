package com.farwolf.weex.module;

import com.farwolf.view.FreeDialog;
import com.farwolf.weex.view.LoadingDialog;
import com.farwolf.weex.view.LoadingDialog_;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

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
        showFull("加载中");
    }



    @JSMethod
    public void showFull(String txt)
    {
        if(f==null)
        {


            progress = LoadingDialog_.build(this.mWXSDKInstance.getContext());
            progress.txt.setText(txt);
            f=new FreeDialog(this.mWXSDKInstance.getContext(),progress);
            f.setCancelable(false);
            progress.f=f;

        }
        f.show();

    }




    @JSMethod
    public void dismiss()
    {
        if(f!=null)
        f.dismiss();
    }

}
