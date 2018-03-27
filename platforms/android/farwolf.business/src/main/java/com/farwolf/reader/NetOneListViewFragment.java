package com.farwolf.reader;

import android.util.Log;

import com.farwolf.business.R;
import com.farwolf.json.JsonListener;
import com.farwolf.json.JsonReader;
import com.farwolf.view.progress.CircleProgressBar;
import com.farwolf.view.progress.IProgress;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.List;

@EFragment
public abstract class NetOneListViewFragment<T,U extends JsonReader> extends SimpleEmptyViewListFragment<T> implements JsonListener<U>{
 
	
	
	@Bean
	protected CircleProgressBar p;
 
 
	
	public IProgress getProgress()
	{
		return p;
	}
	
	
 
	
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		 isLoading=true;
	 
		 setPullRefreshEnable(false);

		 if(getProgressView()!=null)
		 {    
//			 Log.i("pxx", pull.isRefreshing()+"");
			 showProgressView();

		 }
		 hideEmptyView();
		 hideExceptionView();
	}

	@Override
	public void compelete() {
		// TODO Auto-generated method stub
		
		  isLoading=false;
	      setPullEnd();
	      if(getProgress()!=null)
		  {
	    	  getProgress().dismiss();
		  }
			if(getProgressView()!=null)
			{
	//			 Log.i("pxx", pull.isRefreshing()+"");
				hideProgressView();

			}
	      setPullRefreshEnable(true);
	}

//	public <U extends com.farwolf.json.JsonReader> void success(U j) {
//		
//		
//	};
	
	@Override
	public void success(U j) {
		// TODO Auto-generated method stub
		if(j.isEnd())
		{
			hasMore=false;
		}	 
		List<T> l= j.toList(getBeanClass());
		getDataSource().addAll(l);
		notifyDataSetChanged();
		if(getDataSource().size()==0)
		{
			isEmpty=true;
			showEmptyView();
		}
	}
	
	 
	
	
	public abstract Class<T> getBeanClass();
	
	
	
//	@Override
//	public void showEmptyView() {
//		// TODO Auto-generated method stub
//		 
//		if(list instanceof ListView)
//		{
//			ListView l=(ListView) list;
//			if(l!=null)
//			{
//				if(l.getHeaderViewsCount()==0)
//				{
//					super.showEmptyView();
//					return;
//				}
//			}
//		}
//		if(list instanceof HeaderGridView)
//		{
//			HeaderGridView l=(HeaderGridView) list;
//			if(l!=null)
//			{
//				if(l.getHeaderViewCount()==0)
//				{
//					super.showExceptionView();
//					return;
//				}
//			}
//		}
//		super.showEmptyView();
//		 
//		
//	}
//	 
//	@Override
//	public void showExceptionView() {
//		// TODO Auto-generated method stub
//		 
//		if(list instanceof ListView)
//		{
//			ListView l=(ListView) list;
//			if(l!=null)
//			{
//				if(l.getHeaderViewsCount()==0)
//				{
//					super.showExceptionView();
//					return;
//				}
//			}
//		}
//		
//		if(list instanceof HeaderGridView)
//		{
//			HeaderGridView l=(HeaderGridView) list;
//			if(l!=null)
//			{
//				if(l.getHeaderViewCount()==0)
//				{
//					super.showExceptionView();
//					return;
//				}
//			}
//		}
//		
//		
//	}
	
 
	 @Override
	public void fail(U j, String code, String msg) {
		// TODO Auto-generated method stub
		 toast(msg);
		 if(getDataSource().size()==0)
		 {
//			 isException=true;
			 showExceptionView(msg);
		 }
	}

	@Override
	public void exception(Object o) {
		// TODO Auto-generated method stub
		Log.e("网络异常!",this.getClass()+"");
		toast("网络异常!");
		if(getDataSource().size()==0)
		{
			isException=true;
		    showExceptionView();
		}
	}

	
	
	@Override
	public int getViewId() {
		// TODO Auto-generated method stub
		return R.layout.netonelistview_fragment;
	}
	 
	 

	
	
	
	
}
