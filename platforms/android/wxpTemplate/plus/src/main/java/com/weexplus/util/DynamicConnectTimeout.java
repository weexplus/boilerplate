package com.weexplus.util;




import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class DynamicConnectTimeout implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request reequest = chain.request();

        String questUrl = reequest.url().toString();
        //设置需要动态修改的接口
//        boolean isSignApi = questUrl.contains(OkHttpUrlUtils.ORDER_SIGN_URL);
//        if (isSignApi) {
//
//            return chain.withConnectTimeout((int)OkHttpUrlUtils.LONG_CONNECT_MILLISECONDS, TimeUnit.MILLISECONDS)
//                    .withReadTimeout((int)OkHttpUrlUtils.LONG_CONNECT_MILLISECONDS, TimeUnit.MILLISECONDS)
//                    .withWriteTimeout((int)OkHttpUrlUtils.LONG_CONNECT_MILLISECONDS, TimeUnit.MILLISECONDS)
//                    .proceed(reequest);
//        }
        return chain.proceed(reequest);
    }


}

