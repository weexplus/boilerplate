package com.weexplus.util;

import android.os.AsyncTask;

public class BackTask<T> extends AsyncTask<Object,Integer, T> {


    public OnFinishCallback onFinish;
    public Callback callback;
    public PreCallback onPreCallback;

    public BackTask(PreCallback onPreCallback,Callback callback, OnFinishCallback onFinish) {
        this.onPreCallback = onPreCallback;
        this.callback = callback;
        this.onFinish = onFinish;
    }

    public BackTask(Callback callback, OnFinishCallback onFinish) {
        this(null,callback,onFinish);
    }


    @Override
    protected void onPreExecute() {
        if(onPreCallback!=null)
            onPreCallback.invoke();
    }

    @Override
    protected T doInBackground(Object... m) {
        return (T)callback.invoke(m);
    }


    @Override
    protected void onPostExecute(T t) {
        if(onFinish!=null)
        onFinish.invoke(t);
    }

    public static interface PreCallback{
        public void invoke();
    }

    public static interface Callback<T>{
        public T invoke(Object... base64);
    }

    public static interface OnFinishCallback<T>{
        public void invoke(T s);
    }
}
