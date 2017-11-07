package com.farwolf.wxpay;

import android.app.Activity;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseResp;

/**
 * Created by zhengjiangrong on 2017/8/10.
 */

public abstract class WXPayEntryActivity extends Activity {

    public void onResp(BaseResp resp){
        if(resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){

            if(resp.errCode==0)
            {
                success(resp);
            }
            else
            {
                fail(resp);
            }


        }
    }



    public abstract   void success(BaseResp res);
    public abstract   void fail(BaseResp res);



}
