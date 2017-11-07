package com.farwolf.base;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;

import com.farwolf.libary.R;

public abstract class TabHostActivity extends TabActivity {
	protected TabHost mTabHost;
	protected TabWidget mTabWidget;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getViewId());
		mTabHost = (TabHost) getTabHost();
		mTabWidget = getTabWidget();
		getTabWidget().setDividerDrawable(null);

	}

	public int getViewId() {
		return R.layout.api_tabhost_activity;
	}

	protected void AddTab(final String name, final Class<?> cls) {

		View v = getItemView(name);
		v.setTag(name);
		TabSpec tabSpec = mTabHost.newTabSpec(name);
		tabSpec.setIndicator(v);
		tabSpec.setContent(new Intent(getBaseContext(), cls));
		mTabHost.addTab(tabSpec);
	    v.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			onItemClick(name);
		}
	});

	}
	
	public void onItemClick(String name)
	{
		show(name);
	}

	public abstract View getItemView(String name);

	public void show(String name) {
		for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
			View v = mTabHost.getTabWidget().getChildAt(i);
			if (name.equals(v.getTag() + "")) {
				mTabHost.setCurrentTab(i);
			}

		}
	}
	
	
	public void showTab()
	{
		mTabWidget.setVisibility(View.VISIBLE);
	}
	
	public void hideTab()
	{
		mTabWidget.setVisibility(View.GONE);
	}
	
	
	public boolean isTabVisable()
	{
		return mTabWidget.getVisibility()==View.VISIBLE;
	}

}
