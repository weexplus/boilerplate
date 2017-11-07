package com.farwolf.view;





import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.farwolf.interfac.IFullHttp;
import com.farwolf.net.HttpTool;

public class HijackWebView extends WebView {


	IFullHttp listener;
	IHijackPost hijackPostListener;
	
	public void setHijackPostListener(IHijackPost hijackPostListener) {
		this.hijackPostListener = hijackPostListener;
	}

	public void setProceesChangeListener(IFullHttp listener) {
		this.listener = listener;
	}

	
	public HijackWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		Intial();
		// TODO Auto-generated constructor stub
	}

	public HijackWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Intial();
		// TODO Auto-generated constructor stub
	}

	public HijackWebView(Context context) {
		super(context);
		Intial();
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void  loadString(String s,String url)
	{
		this.getSettings().setDefaultTextEncodingName("UTF-8") ;
		this.loadDataWithBaseURL(url, s, "text/html", "UTF-8", null);
	}
	
	public void loadUrlWithFilter(String url) {		
		 new GetHtmlString(url).execute();
	}
	
	
	
	
	
	
	
	
	
	
	public void Intial()
	{
		setWebViewClient(null);
		setWebChromeClient(null);
		
	}

	@Override
	public void setWebChromeClient(WebChromeClient client) {
		// TODO Auto-generated method stub
		super.setWebChromeClient(new WebChromeClient()
		{
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				
				if(listener!=null)
					 listener.OnPostProcess(newProgress);
			}
			
		});
	}
	
	
	/**
	 * ��������
	 * @return
	 */
	protected boolean HijackPost()
	{
		return false;
	}
	
	
	@Override
	public void setWebViewClient(WebViewClient client) {
		// TODO Auto-generated method stub
//		super.setWebViewClient(client);
		super.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				if(hijackPostListener!=null)
				{
				   if(hijackPostListener.Hijack(url))				   
				   {
					   return true;
				   }
				}
				if(url!=null)
					{
					  return super.shouldOverrideUrlLoading(view, url);
					}
				return true;
			}
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				if(listener!=null)
					 listener.OnPostCompelete(view);
			}
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
			
				if(listener!=null)
					 listener.OnPostStart(null);
			}
			
		});
	}
	
	
	public  static interface IHijackPost
	{
	     public boolean Hijack(String url);
	}
	
	
	
	public class  GetHtmlString extends AsyncTask<String, String, String> {

		String url;
		public GetHtmlString(String url)
		{
			this.url=url;
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			try {
				return HttpTool.get(url, null, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			String s= result;
		   if(onHijackStringListener!=null)
		   {
			   s= onHijackStringListener.OnHijack(s);						  
		   }
		   HijackWebView.this.loadString(s, url);
		}
		
	}
	
	OnHijackStringListener onHijackStringListener;
	public void setOnHijackStringListener(OnHijackStringListener onHijackStringListener) 
	{
		this.onHijackStringListener = onHijackStringListener;
	}


	public static interface OnHijackStringListener
	{
		public String OnHijack(String s);
		
	}
	
	
	
	

}
