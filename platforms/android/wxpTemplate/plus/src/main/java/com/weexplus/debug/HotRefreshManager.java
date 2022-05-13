/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.weexplus.debug;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ws.WebSocket;
import com.squareup.okhttp.ws.WebSocketCall;
import com.squareup.okhttp.ws.WebSocketListener;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.weexplus.event.RefreshEvent;
import com.weexplus.util.Const;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okio.Buffer;
import okio.BufferedSource;

/**
 */
public class HotRefreshManager {


  public  boolean isOpen=false;
  private static final String TAG = "HotRefreshManager";

  private static  HotRefreshManager hotRefreshInstance = new  HotRefreshManager();
  private WebSocket mWebSocket = null;
  public Handler mHandler = null;

  private HotRefreshManager() {
  }

  public static  HotRefreshManager getInstance() {
    return hotRefreshInstance;
  }

  public void setHandler(Handler handler) {
    mHandler = handler;
  }

  public boolean disConnect() {
    isOpen=false;
    if (mWebSocket != null) {
      try {
        mWebSocket.close(1000, "activity finish!");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return true;
  }

  public  void send(final String msg)
  {
    new Thread(new Runnable() {
      @Override
      public void run() {
        Buffer b=new Buffer();
        b.writeUtf8(msg);
        try {
          if(mWebSocket!=null)
            mWebSocket.sendMessage(WebSocket.PayloadType.TEXT,b);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
  public boolean connect(String url) {
    OkHttpClient httpClient = new OkHttpClient();
    Request request = new Request.Builder().url(url).addHeader("sec-websocket-protocol", "echo-protocol").build();
    WebSocketCall.create(httpClient, request).enqueue(new WXWebSocketListener(url));
    isOpen=true;
    return true;
  }

  public class WXWebSocketListener implements WebSocketListener {

    private String mUrl;

    WXWebSocketListener(String url) {
      mUrl = url;
    }

    @Override
    public void onOpen(WebSocket webSocket, Request request, Response response) throws IOException {
      mWebSocket = webSocket;
    }

    @Override
    public void onMessage(BufferedSource payload, WebSocket.PayloadType type) throws IOException {
      if (type == WebSocket.PayloadType.TEXT) {
        String temp = payload.readUtf8();
        Log.e(TAG, "into--[onMessage] msg:" + temp);
        payload.close();

        if (TextUtils.equals("refresh", temp) && mHandler != null) {
          mHandler.obtainMessage(Const.HOT_REFRESH_REFRESH, 0, 0, mUrl).sendToTarget();
        }
        else{
          if(temp.contains("debugReady")){
            String q[]=temp.split("=");
            String url=q[1];
            WXEnvironment.sDebugServerConnectable = true;
            WXEnvironment.sRemoteDebugMode = true;
            WXEnvironment.sRemoteDebugProxyUrl =url;
            WXSDKEngine.reload();
          }
        }
      }
    }

    @Override
    public void onPong(Buffer payload) {
      mHandler.obtainMessage(Const.HOT_REFRESH_CONNECT_ERROR, 0, 0, mUrl).sendToTarget();
    }

    @Override
    public void onClose(int code, String reason) {
      mWebSocket = null;
      mHandler.obtainMessage(Const.HOT_REFRESH_DISCONNECT, 0, 0, mUrl).sendToTarget();
      EventBus.getDefault().post(new RefreshEvent("connect"));
    }

    @Override
    public void onFailure(IOException e) {
      mWebSocket = null;
      mHandler.obtainMessage(Const.HOT_REFRESH_CONNECT_ERROR, 0, 0, mUrl).sendToTarget();
      EventBus.getDefault().post(new RefreshEvent("connect"));
    }
  }
}
