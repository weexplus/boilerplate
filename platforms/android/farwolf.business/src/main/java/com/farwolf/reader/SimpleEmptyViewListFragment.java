package com.farwolf.reader;

import android.view.View.OnClickListener;

import com.farwolf.fragment.onelist.EmptyView;
import com.farwolf.fragment.onelist.ExceptionEmptyView;
import com.farwolf.fragment.onelist.OneListViewFragment;

 

public abstract class SimpleEmptyViewListFragment<T>  extends OneListViewFragment<T>{

	
	
	
//    @Override
//	public void onHeaderParepareRefresh(PullToRefreshView view) {
//		// TODO Auto-generated method stub
//		super.onHeaderParepareRefresh(view);
//		hideEmptyView();
//		hideExceptionView();
//	}
//    
//    @Override
//    public void onHeaderRefreshCancle(PullToRefreshView view) {
//    	// TODO Auto-generated method stub
//    	super.onHeaderRefreshCancle(view);
//    	if(isException)
//    	{
//    		showExceptionView();
//    	}
//    	if(isEmpty)
//    	{
//    		showEmptyView();
//    	}
//    	
//    }
	 
	public void setEmptyImage(int id)
	{
		
		this.setEmptyImage(id, null);
	}
	public void setEmptyText(String txt,OnClickListener l)
	{
		if(empty_view instanceof EmptyView)
		{
			((EmptyView)empty_view).setText(txt);
			empty_view.setOnClickListener(l);
			 
		}
		
	}
	public void setEmptyImage(int id,OnClickListener l)
	{
		 
		if(empty_view instanceof EmptyView)
		{
			((EmptyView) empty_view).drawableTop(id, l);
			 
		}
		
		 
	}
	public EmptyView getmEmptyView()
	{
		return (EmptyView) empty_view;
	}
	
	 
	public ExceptionEmptyView getmExceptionView() {
		// TODO Auto-generated method stub
		return (ExceptionEmptyView) exception_empty_view;
	}
	
	
	public void setExceptionImage(int id)
	{
		if(exception_empty_view instanceof ExceptionEmptyView)
		{
			((ExceptionEmptyView) exception_empty_view).drawableTop(id);
		}
		
	}
	

	
	public void setExceptionText(String text)
	{
		if(exception_empty_view instanceof ExceptionEmptyView)
		{
			((ExceptionEmptyView)exception_empty_view).setText(text);
			notifyDataSetChanged();
		}
	}
	
	

}
