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
package com.farwolf.vshop;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.farwolf.movie.R;
import com.farwolf.weex.activity.AbsWeexActivity;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.utils.WXFileUtils;

public class IndexActivity extends AbsWeexActivity {

  private static final String TAG = "IndexActivity";
  private static final int CAMERA_PERMISSION_REQUEST_CODE = 0x1;
  private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 0x2;
  private static final String DEFAULT_IP = "your_current_IP";
  private static String sCurrentIp = DEFAULT_IP; // your_current_IP

  private ProgressBar mProgressBar;
  private TextView mTipView;

  private BroadcastReceiver mReloadReceiver;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_index);
    setContainer((ViewGroup) findViewById(R.id.index_container));
//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);
//    getWindow().setFormat(PixelFormat.TRANSLUCENT);

    mProgressBar = (ProgressBar) findViewById(R.id.index_progressBar);
    mTipView = (TextView) findViewById(R.id.index_tip);
    mProgressBar.setVisibility(View.VISIBLE);
    mTipView.setVisibility(View.VISIBLE);


//    if (!WXSoInstallMgrSdk.isCPUSupport()) {
//      mProgressBar.setVisibility(View.INVISIBLE);
////      mTipView.setText(R.string.cpu_not_support_tip);
//      return;
//    }
    renderPage(WXFileUtils.loadAsset("app/index.js", this), "app/index.js");
//    if (TextUtils.equals(sCurrentIp, DEFAULT_IP)) {
//      renderPage(WXFileUtils.loadAsset("app/index.js", this), getIndexUrl());
//    } else {
//      renderPageByURL(getIndexUrl());
//    }



    LocalBroadcastManager.getInstance(this).registerReceiver(mReloadReceiver, new IntentFilter(WXSDKEngine.JS_FRAMEWORK_RELOAD));
  }



  @Override
  public void onRenderSuccess(WXSDKInstance wxsdkInstance, int i, int i1) {
    super.onRenderSuccess(wxsdkInstance,i,i1);
    mProgressBar.setVisibility(View.GONE);
    mTipView.setVisibility(View.GONE);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    LocalBroadcastManager.getInstance(this).unregisterReceiver(mReloadReceiver);
  }

  @Override
  public void onPause() {
    super.onPause();
//    WXSDKManager.getInstance().takeJSHeapSnapshot("/sdcard/weex/");
  }

  private static String getIndexUrl() {
    return "http://" + sCurrentIp + ":12580/examples/build/index.js";
  }
}

