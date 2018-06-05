package com.farwolf.alipay;

import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;
import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import java.util.Map;

/**
 * Created by zhengjiangrong on 2017/8/9.
 */

public class WXAlipayModule extends WXModuleBase {





    @JSMethod
    public void open(String signstr,final JSCallback callback)
    {

        MyTask my=new MyTask(callback);
        my.execute(signstr);

    }


    public class MyTask extends AsyncTask<String, Integer, Map>
    {


        JSCallback callback;
        public MyTask(JSCallback callback) {
            this.callback = callback;
        }




        @Override
        protected Map doInBackground(String... params) {
            PayTask alipay = new PayTask(getActivity());
                Map result = alipay.payV2(params[0],true);
                return result;
        }

        @Override
        protected void onPostExecute(Map map) {
            if(callback!=null)
                callback.invokeAndKeepAlive(map);
        }
    }

}
