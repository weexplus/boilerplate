package com.farwolf.fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import com.farwolf.base.FragmentBase;
import com.farwolf.base.OneFragmentActivity;
import com.farwolf.libary.R;
import com.farwolf.view.ProcessWebView;
import com.farwolf.view.TitleBar;

@EFragment
public class WebviewFragment extends FragmentBase {

	@ViewById
	ProcessWebView webview;
	
	
	@AfterViews
	void init()
	{
		String url=getIntent().getStringExtra("url");
		String title=getIntent().getStringExtra("title");
	
		 
		this.webview.loadUrl(url);
		OneFragmentActivity f=(OneFragmentActivity) getActivity();
		TitleBar t= f.getTitleBar();
		if(t!=null)
		t.init(true, title);
		
		
	}
	
	
	
	@Override
	public int getViewId() {
		// TODO Auto-generated method stub
		return R.layout.api_webview_fragment;
	}

}
