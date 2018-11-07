package com.farwolf.alipay.module;

import android.os.AsyncTask;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.farwolf.weex.annotation.WeexModule;
import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import java.util.Map;

/**
 * Created by zhengjiangrong on 2017/8/9.
 */
@WeexModule(name="alipay")
public class WXAlipayModule extends WXModuleBase {



    @JSMethod
    public void pay(Map param,final JSCallback callback)
    {

        String signstr=param.get("signstr")+"";
        MyTask my=new MyTask(0,callback);
        my.execute(signstr);

    }


    @JSMethod
    public void login(Map param,final JSCallback callback)
    {

        String signstr=param.get("signstr")+"";
        MyTask my=new MyTask(1,callback);
        my.execute(signstr);

    }


    public class MyTask extends AsyncTask<String, Integer, Map>
    {

        int type=0;

        JSCallback callback;

        public MyTask(int type, JSCallback callback) {
            this.type = type;
            this.callback = callback;
        }

        @Override
        protected Map doInBackground(String... params) {


            Map result = null;
            if (type == 0) {
                PayTask alipay = new PayTask(getActivity());
                result = alipay.payV2(params[0], true);
            } else {
                AuthTask authTask = new AuthTask(getActivity());
                result = authTask.authV2(params[0], true);
            }
            return result;
        }

        @Override
        protected void onPostExecute(Map map) {
            if(callback!=null)
                callback.invokeAndKeepAlive(map);
        }
    }

}
