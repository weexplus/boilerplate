package com.farwolf.fragment.onelist;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.farwolf.base.FragmentBase;
import com.farwolf.base.ItemView;
import com.farwolf.libary.R;
import com.farwolf.view.HeaderGridView;
import com.farwolf.view.PullToRefreshView;
import com.farwolf.view.PullToRefreshView.OnHeaderRefreshListener;
import com.farwolf.view.progress.IProgress;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EFragment
public abstract class OneListViewFragment<T> extends FragmentBase implements OnHeaderRefreshListener {

	
	
	
	
	
	@ViewById	 
	protected AbsListView list;
	
		 
	protected View exception_empty_view;
	
	@ViewById
	protected PullToRefreshView pull;
	
	
	protected View  empty_view;


	protected View  progress_view;
	
	@ViewById
	protected View  layout;
	
	protected BaseAdapter adapter;
	
	protected boolean isException=false;
	
	protected boolean isEmpty=false;
	 
	
    protected HashMap<View,View> attach=new HashMap<View, View>();
	 

	public abstract IProgress getProgress() ;
		 



	private List<T> data=new ArrayList<T>();
	
	protected boolean  hasMore = true ;
	    
	protected boolean  isLoading = false ;


	public  AbsListView getListView()
	{
		return list;
	}

	@Override
	public int getViewId() {
		// TODO Auto-generated method stub
		return R.layout.api_onelist_fragment;
	}
	
	public List<T> getDataSource()
	{
		return data;
	}
	
	public void enableHeaderRefresh()
	{
		pull.enableHeaderRefresh();
	}
	
	
	public void disableHeaderRefresh()
	{
		pull.disableHeaderRefresh();
	}

	
	public void setPullEnd()
	{
		  if(pull!=null)
			pull.setHeaderRefreshComplete();
		   
	}
	
	
	public void setPullRefreshEnable(boolean pullRefreshEnable) {

		if(pull!=null)
		pull.setRefreshHeaderState(pullRefreshEnable);
	}


	public void showExceptionView()
	{
		this.showExceptionView("网络异常，点我重新加载");
	}
	public void showExceptionView(String msg)
	{
		if(exception_empty_view==null)
			return;
		((ExceptionEmptyView)exception_empty_view).setText(msg);
//		exception_empty_view.setVisibility(View.VISIBLE);
		showAttach(exception_empty_view);
	}
	public void hideExceptionView()
	{
		if(exception_empty_view==null)
			return;
//		exception_empty_view.setVisibility(View.GONE);
		hideAttach(exception_empty_view);
	}
	public void showEmptyView()
	{
		if(empty_view==null)
			return;
//		empty_view.setVisibility(View.VISIBLE);
		showAttach(empty_view);
	}
	public void showProgressView()
	{
		if(progress_view==null)
			return;
//		empty_view.setVisibility(View.VISIBLE);
		showAttach(progress_view);
	}
	public void hideEmptyView()
	{
		if(empty_view==null)
			return;
		hideAttach(empty_view);
	}
	public void hideProgressView()
	{
		if(progress_view==null)
			return;
		hideAttach(progress_view);
	}

	protected void hideAttach(View v)
	{
		v.setVisibility(View.GONE);
		if(attach.containsKey(v))
		{
			attach.get(v).setVisibility(View.GONE);
		}
		 
	}
	
	
	protected void showAttach(View v)
	{
		v.setVisibility(View.VISIBLE);
		if(attach.containsKey(v))
		{
			attach.get(v).setVisibility(View.VISIBLE);
		}
		 
	}
	
	 
	boolean hasloaded=false;
	 

      @AfterViews
	public void _InitOneListview() {
		// TODO Auto-generated method stub
       if(hasloaded)
    	   return;
       hasloaded=true;
		pull.setRefreshFooterState(false);

		pull.setOnHeaderRefreshListener(this);
		
		
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				 if(scrollState ==SCROLL_STATE_IDLE){
					 if(view.getLastVisiblePosition()==(view.getCount()-1)){
						 
						 if(hasMore && !isLoading){
						       
							 _load();
						 }
					 }
				 }
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				
			}
		});
        list.setOnItemClickListener(new AdapterView. OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(arg2<=data.size()-1)
				 onItemClicked(getDataSource().get(arg2),arg2);
			}
		});
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				onItemLongClicked(getDataSource().get(arg2),arg2);
				return true;
			}
		});
        initEmptyView();
		initExpetionView();
		  initProgressView();
		initAdapter();
		hideEmptyView();
		hideExceptionView();
		  hideProgressView();
		init();
      
	}
      
      
      protected  void initAdapter()
      {
    	  adapter=new MyAdapter();
    	  list.setAdapter(adapter);
      }
      
      
      
    protected    void init()
    {
    	
    }



	boolean isAutoLoad=true;
	
	@Override
	protected void onLoaded() {
		// TODO Auto-generated method stub
		 if(isAutoLoad)
		  _load();    	        
	      afterInit();
	}
	
	
	public void afterInit()
	{
		
	}
	
	protected void onItemClicked(T l,int p)
	{
//		GridView h;
//		h.setOnScrollListener(l)
	}
	protected void onItemLongClicked(T l,int p)
	{
//		GridView h;
//		h.setOnScrollListener(l)
	}

	public boolean isAutoLoad() {
		return isAutoLoad;
	}

	public void setAutoLoad(boolean autoLoad) {
		isAutoLoad = autoLoad;
	}

	public void notifyDataSetChanged()
	{
		 if(adapter!=null)
		 adapter.notifyDataSetChanged();
	}

	
	@Override
	public void onHeaderParepareRefresh(PullToRefreshView view) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onHeaderRefreshCancle(PullToRefreshView view) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		// TODO Auto-generated method stub
		refresh();
	}

	
	public void clear()
	{
		data.clear();
		hasMore=true;
//		adapter.notifyDataSetChanged();
	}
	
	
	public void   refresh()
	{
		clear();
		_load();
		
	}
	
	 
	void _load()
	{
		
		load();
	}
	
	public   void load()
	{
		
		
	}
	public View getProgressView()
	{
//		return null;
		return FooterProgressBar_.build(getActivity());
	}
	public View getEmptyView()
	{
//		return null;
		return EmptyView_.build(getActivity());
	}
	public View getExceptionView()
	{
//		return null;
		return EmptyView_.build(getActivity());
	}
	
	void initEmptyView()
	{
		if(empty_view!=null)
			return;
		empty_view=getEmptyView();
		addFooterView(empty_view);
	}

	void initProgressView()
	{
		if(progress_view!=null)
			return;
		progress_view=getProgressView();
		addFooterView(progress_view);
	}


	void addFooterView(View v)
	{
		
		FrameLayout footerLayoutHolder = new FrameLayout(getActivity());  
		footerLayoutHolder.addView(v, 0, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,  
		    FrameLayout.LayoutParams.WRAP_CONTENT));
		attach.put(v, footerLayoutHolder);
//		v.setVisibility(View.GONE);
		if(list instanceof ListView)
		{
			ListView l=(ListView) list;
			if(l!=null)
			{				 
				l.setDivider(null);
				l.addFooterView(footerLayoutHolder);
//				l.addHeaderView(v);
			}
		}
		if(list instanceof HeaderGridView)
		{
			HeaderGridView l=(HeaderGridView) list;
			if(l!=null)
			{
				l.addFooterView(footerLayoutHolder);
			}
		}
	}
	
	void initExpetionView()
	{
		if(exception_empty_view!=null)
			return;
		exception_empty_view=ExceptionEmptyView_.build(getActivity());
	    if(exception_empty_view!=null)
        {
        	exception_empty_view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					_load();
				}
			});
	 
        }
		addFooterView(exception_empty_view);
	}
	
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
	
	
	public abstract ItemView<T> getItemView(int p);
	 
   
	public int getItemViewType(int p)
	{
		return -1;
	}
	
	public int getViewTypeCount()
	{
		return -1;
	}
	

    
    
    public class MyAdapter  extends BaseAdapter
    {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return getDataSource().size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		
		@Override
		public int getItemViewType(int position) {
			// TODO Auto-generated method stub
			if(-1==OneListViewFragment.this.getItemViewType(position))
			return super.getItemViewType(position);
			else
				return OneListViewFragment.this.getItemViewType(position);
		}
		
		
	 
		
		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			if(-1==OneListViewFragment.this.getViewTypeCount())
			return super.getViewTypeCount();
			else
				return  OneListViewFragment.this.getViewTypeCount();
		}
		
		@Override
		public View getView(int p, View v, ViewGroup pa) {
			// TODO Auto-generated method stub
			if(v==null)
			{
				v=getItemView(p);
			}
			if(v!=null)
			{
				ItemView<T> it=(ItemView<T>) v;
				it.update_(getDataSource().get(p));
			}
			
			return v;
		}
    	
    }

}
