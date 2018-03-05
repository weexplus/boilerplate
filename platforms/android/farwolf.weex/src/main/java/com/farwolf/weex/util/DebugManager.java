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
package com.farwolf.weex.util;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ws.WebSocket;
import com.squareup.okhttp.ws.WebSocketCall;
import com.squareup.okhttp.ws.WebSocketListener;

import java.io.IOException;
import java.net.ConnectException;
import java.util.HashMap;

import okio.Buffer;
import okio.BufferedSource;

/**
 */
public class DebugManager {





  DebugListener debugListener;
  public  boolean isOpen=false;
  private static final String TAG = "HotRefreshManager";

  private static DebugManager debuggerInstance = new DebugManager();
  private WebSocket mWebSocket = null;
//  private Handler mHandler = null;

  private DebugManager() {
  }

  public static DebugManager getInstance() {
     if(debuggerInstance==null)
       debuggerInstance=new DebugManager();
    return debuggerInstance;
  }

  public void setDebugListener(DebugListener debugListener) {
    this.debugListener = debugListener;
  }


  public void destory()
  {
    if(debuggerInstance!=null)
    {
//      debuggerInstance.disConnect();
      mWebSocket=null;
      debugListener=null;
    }
     debuggerInstance=null;

  }



  public boolean disConnect() {
    isOpen=false;
    if (mWebSocket != null) {
      try {
        mWebSocket.close(1000, "activity finish!");
//        mWebSocket=null;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return true;
  }

  public boolean connect(String ip) {
    String url="ws://"+ip+":8088/page/entry";
    OkHttpClient httpClient = new OkHttpClient();
    Request request = new Request.Builder().url(url).addHeader("sec-websocket-protocol", "echo-protocol").build();
    WebSocketCall.create(httpClient, request).enqueue(new WXWebSocketListener(url));
    isOpen=true;
    return true;
  }


  public  static interface  DebugListener
  {
      void onSuccess(String channelId);
      void onFail();
  }


  public  void send(String msg)
  {
    Buffer b=new Buffer();
    b.writeUtf8(msg);
    try {
      if(mWebSocket!=null)
      mWebSocket.sendMessage(WebSocket.PayloadType.TEXT,b);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public void disconnect()
  {
    new Thread(new Runnable() {
      @Override
      public void run() {
        HashMap m=new HashMap();
        m.put("method","WxDebug.deviceDisconnect");
        JSONObject j=new JSONObject(m);
        send(j.toJSONString());
      }
    }).start();

  }



  class WXWebSocketListener implements WebSocketListener {

    private String mUrl;

    WXWebSocketListener(String url) {
      mUrl = url;
    }

    @Override
    public void onOpen(WebSocket webSocket, Request request, Response response) throws IOException {
      mWebSocket = webSocket;
      HashMap m=new HashMap();
      m.put("method","WxDebug.applyChannelId");
      JSONObject j=new JSONObject(m);
      send(j.toJSONString());

    }

    @Override
    public void onMessage(BufferedSource payload, WebSocket.PayloadType type) throws IOException {
      if (type == WebSocket.PayloadType.TEXT) {
         String temp = payload.readUtf8();
         Log.e(TAG, "into--[onMessage] msg:" + temp);
         payload.close();
         JSONObject j=JSONObject.parseObject(temp);
         String method= j.getString("method");
         if("WxDebug.pushChannelId".equals(method))
         {
           JSONObject dic=j.getJSONObject("params");
           String id=dic.getString("channelId");
           if(debugListener!=null)
           debugListener.onSuccess(id);
         }
         else if("WxDebug.reload".equals(method))
         {
//           LocalBroadcastManager.getInstance(this).sendBroadcast(new IntentFilter(WXSDKEngine.JS_FRAMEWORK_RELOAD));
//           WXSDKEngine.reload();
         }


      }
    }

    @Override
    public void onPong(Buffer payload) {

    }

    @Override
    public void onClose(int code, String reason) {
      mWebSocket = null;
//       destory();
    }

    @Override
    public void onFailure(IOException e) {
      mWebSocket = null;
      if(e instanceof ConnectException)
      {
        HotRefreshManager.getInstance().send("opendebug");
        if(debugListener!=null)
          debugListener.onFail();
      }



    }
  }
}
