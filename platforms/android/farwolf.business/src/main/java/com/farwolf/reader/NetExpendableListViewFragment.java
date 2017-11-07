package com.farwolf.reader;

import org.androidannotations.annotations.EFragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.farwolf.base.ItemView;
import com.farwolf.business.R;
import com.farwolf.fragment.onelist.IGroup;
import com.farwolf.json.JsonReader;

@EFragment
public abstract   class NetExpendableListViewFragment <X,T extends IGroup<X> ,U extends JsonReader> extends  NetOneListViewFragment<T,U>  {


	public MyAdpter adapter;
 
	
	@Override
	protected void initAdapter() {
		// TODO Auto-generated method stub
	 
		adapter =new MyAdpter();
		ExpandableListView l=(ExpandableListView) list;
		l.setGroupIndicator(null);
		l.setAdapter(adapter);
	}
 
	
	public class MyAdpter extends BaseExpandableListAdapter
	{

		@Override
		public Object getChild(int arg0, int arg1) {
			// TODO Auto-generated method stub
		 
			return getDataSource().get(arg0).getList().get(arg1);
		}

		@Override
		public long getChildId(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getChildView(int arg0, int arg1, boolean arg2, View v,
				ViewGroup arg4) {
			// TODO Auto-generated method stub
			if(v==null)
				v=getChildItemView(arg1);
			ItemView<X> i=(ItemView<X>) v;
			i.update(getDataSource().get(arg0).getList().get(arg1));
			return i;			 
		}

		@Override
		public int getChildrenCount(int arg0) {
			// TODO Auto-generated method stub
			return getDataSource().get(arg0).getList().size();
		}

		@Override
		public Object getGroup(int arg0) {
			// TODO Auto-generated method stub
			return getDataSource().get(arg0);
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return getDataSource().size();
		}

		@Override
		public long getGroupId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getGroupView(int arg0, boolean arg1, View v,
				ViewGroup arg3) {
			// TODO Auto-generated method stub
			if(v==null)
				v=getItemView(arg0);
			ItemView<T> i=(ItemView<T>) v;
			i.update(getDataSource().get(arg0));
			return i;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

 
	
	 
	public abstract ItemView<X> getChildItemView(int p) ;

 
	@Override
	public int getViewId() {
		// TODO Auto-generated method stub
		return R.layout.net_expendable_listview_fragment;
	}

}
