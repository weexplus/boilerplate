package com.farwolf.view;


import java.util.HashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.farwolf.interfac.IFullHttp;
import com.farwolf.libary.R;
import com.farwolf.view.HijackWebView.IHijackPost;

public class ProcessWebView extends LinearLayout {

	String firstUrl="";
	String url;
	IFullHttp  httpListener;
	HijackWebView webview;
	ProgressBar progress;
	public ProcessWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Intial();
		
	
	}

	public ProcessWebView(Context context) {
		super(context);
		Intial();
	}
	
	public void setHijackPostListener(IHijackPost hijackPostListener) {
		webview.setHijackPostListener(hijackPostListener);
	}

	public void setOnProcesschangeListener(IFullHttp httpListener) {
		this.httpListener = httpListener;
		
	}
	
	public String getCurrentUrl()
	{
		return webview.getUrl();
	}
	
	public boolean IsBacktoReloadPoint()
	{
		return firstUrl.equals(getCurrentUrl());
	}
	
	
	private void Intial()
	{
		LayoutInflater l=LayoutInflater.from(getContext());
		l.inflate(R.layout.api_process_webview, this);
		webview=(HijackWebView) findViewById(R.id.api_progresswebview_webview);
		progress=(ProgressBar) findViewById(R.id.api_progresswebview_processbar);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.setProceesChangeListener(new IFullHttp() {
			
			@Override
			public void OnPostStart(Object o) {
				// TODO Auto-generated method stub
				
				progress.setVisibility(View.VISIBLE);
				if(httpListener!=null)
					httpListener.OnPostStart(o);
			}
			
			
			
			@Override
			public void OnPostProcess(int newProgress) {
				// TODO Auto-generated method stub
//				Log.i("newProgress", newProgress+"");
				progress.setProgress(newProgress);
				if(httpListener!=null)
					httpListener.OnPostProcess(newProgress);
			}

			@Override
			public void OnException(Object o) {
				if(httpListener!=null)
					httpListener.OnException(o);
				// TODO Auto-generated method stub
				
			}


		 



			@Override
			public void OnPostCompelete(Object o) {
				// TODO Auto-generated method stub
				progress.setVisibility(View.GONE);
				if(httpListener!=null)
					httpListener.OnPostCompelete(null);
			}




		
		});
		
	
		
	}
	
	public void loadUrl(String url)
	{
		this.url=url;
		this.webview.loadUrl(url);
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("Referer", "http://www.baidu.com");
		this.webview.loadUrl(url, map);
	}
   
	
	public boolean canGoBack()
	{
		return webview.canGoBack();
	}
	
	public void goBack()
	{
		if(webview.canGoBack())
			webview.goBack();
	}
	public void goForward()
	{
		if(webview.canGoForward())
			webview.goForward();
	}
	
	public void Reload()
	{
		webview.reload();
	}
	
	
	

}
