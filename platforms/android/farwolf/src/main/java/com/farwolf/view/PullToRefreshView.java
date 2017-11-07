package com.farwolf.view;



import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.farwolf.libary.R;
import com.farwolf.util.DateTool;
 

public class PullToRefreshView extends LinearLayout {
	// refresh states
	private static final int PULL_TO_REFRESH = 2;
	private static final int RELEASE_TO_REFRESH = 3;
	private static final int REFRESHING = 4;





	private View touchView;
	// pull state
	private static final int PULL_UP_STATE = 0;
	private static final int PULL_DOWN_STATE = 1;
	/**
	 * last y
	 */
	private int mLastMotionY;
	/**
	 * lock
	 */
	private boolean mLock;
	/**
	 * header view
	 */
	private View mHeaderView;
	/**
	 * footer view
	 */
	private View mFooterView;
	/**
	 * list or grid
	 */
	private AdapterView<?> mAdapterView;
	/**
	 * scrollview
	 */
	private ScrollView mScrollView;
	/**
	 * header view height
	 */
	private int mHeaderViewHeight;
	/**
	 * footer view height
	 */
	private int mFooterViewHeight;
	/**
	 * header view image
	 */
	private ImageView mHeaderImageView;
	/**
	 * header tip text
	 */
	private TextView mHeaderTextView;
	/**
	 * footer tip text
	 */
	private TextView mFooterTextView;
	/**
	 * header refresh time
	 */
	private TextView mHeaderUpdateTextView;
	/**
	 * footer refresh time
	 */
	// private TextView mFooterUpdateTextView;
	/**
	 * header progress bar
	 */
	private ProgressBar mHeaderProgressBar;
	/**
	 * footer progress bar
	 */
	private ProgressBar mFooterProgressBar;
	/**
	 * layout inflater
	 */
	private LayoutInflater mInflater;
	/**
	 * header view current state
	 */
	private int mHeaderState;
	/**
	 * footer view current state
	 */
	private int mFooterState;
	/**
	 * pull state,pull up or pull down;PULL_UP_STATE or PULL_DOWN_STATE
	 */
	private int mPullState;
	private RotateAnimation mFlipAnimation;
	private RotateAnimation mReverseFlipAnimation;
	/**
	 * footer refresh listener
	 */
	private OnFooterRefreshListener mOnFooterRefreshListener;
	/**
	 * footer refresh listener
	 */
	private OnHeaderRefreshListener mOnHeaderRefreshListener;

	private int mTouchSlop;

    private boolean footerRefreshState = true;//是否可以加载更多 默认可以

    private boolean headerRefreshState = true;//是否可以下拉刷新 默认可以
	/**
	 * last update time
	 */
	// private String mLastUpdateTime;
	public PullToRefreshView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PullToRefreshView(Context context) {
		super(context);
		init();
	}

	public void setTouchView(View touchView) {
		this.touchView = touchView;
	}
	/**
	 * init
	 * 
	 * @description
	 */
	private void init() {
		// Load all of the animations we need in code rather than through XML
		mFlipAnimation = new RotateAnimation(0, -180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		mFlipAnimation.setInterpolator(new LinearInterpolator());
		mFlipAnimation.setDuration(250);
		mFlipAnimation.setFillAfter(true);
		mReverseFlipAnimation = new RotateAnimation(-180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
		mReverseFlipAnimation.setDuration(250);
		mReverseFlipAnimation.setFillAfter(true);

		final ViewConfiguration configuration = ViewConfiguration
				.get(getContext());
		mTouchSlop = configuration.getScaledTouchSlop();
//		mTouchSlop = 0;

		mInflater = LayoutInflater.from(getContext());
		addHeaderView();
	}

	
	public boolean isRefreshing()
	{
		return mHeaderState==REFRESHING;
	}
	
	
	private void addHeaderView() {
		// header view
		mHeaderView = mInflater.inflate(R.layout.api_pulltorefresh_header, this, false);

		mHeaderImageView = (ImageView) mHeaderView
				.findViewById(R.id.xlistview_header_arrow);
		mHeaderTextView = (TextView) mHeaderView
				.findViewById(R.id.xlistview_header_hint_textview);
		mHeaderUpdateTextView = (TextView) mHeaderView
				.findViewById(R.id.xlistview_header_time);
		mHeaderProgressBar = (ProgressBar) mHeaderView
				.findViewById(R.id.xlistview_header_progressbar);
		// header layout
		measureView(mHeaderView);
		mHeaderViewHeight = mHeaderView.getMeasuredHeight();
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				mHeaderViewHeight);
		params.topMargin = -(mHeaderViewHeight);
		// mHeaderView.setLayoutParams(params1);
		addView(mHeaderView, params);

	}

	private void addFooterView() {
		// footer view
		mFooterView = mInflater.inflate(R.layout.api_pulltorefresh_footer, this, false);
		mFooterTextView = (TextView) mFooterView
				.findViewById(R.id.xlistview_footer_hint_textview);
		mFooterProgressBar = (ProgressBar) mFooterView
				.findViewById(R.id.xlistview_footer_progressbar);
		// footer layout
		measureView(mFooterView);
		mFooterViewHeight = mFooterView.getMeasuredHeight();
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				mFooterViewHeight);
		addView(mFooterView, params);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		addFooterView();
		initContentAdapterView();
	}

	/**
	 * init AdapterView like ListView,GridView and so on;or init ScrollView
	 */
	private void initContentAdapterView() {
		int count = getChildCount();
		if (count < 3) {
			throw new IllegalArgumentException(
					"this layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
		}
		View view = null;
		for (int i = 0; i < count - 1; ++i) {
			view = getChildAt(i);
			if (view instanceof AdapterView<?>) {
				mAdapterView = (AdapterView<?>) view;
			}
			if (view instanceof ScrollView) {
				// finish later
				mScrollView = (ScrollView) view;
			}
		}
		if (mAdapterView == null && mScrollView == null) {
			throw new IllegalArgumentException(
					"must contain a AdapterView or ScrollView in this layout!");
		}
	}

	private void measureView(View child) {
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}




	@Override
	public boolean onInterceptTouchEvent(MotionEvent e) {

	  if(touchView!=null){
		//判断在视图内的动作则返回 false
		Rect rect = new Rect(touchView.getLeft(),touchView.getTop(),touchView.getRight(),touchView.getBottom());
		if(rect.contains((int)e.getX(), (int)e.getY())){
			return false;
		}}
		int y = (int) e.getRawY();
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			int deltaY = y - mLastMotionY;
			final int distance = Math.abs(deltaY);
			final boolean overscroll = getScrollY() != 0;
//			System.out.println("overscroll="+overscroll);
//			System.out.println("distance="+distance);
//			System.out.println("mTouchSlop="+mTouchSlop);
			if (overscroll || distance > mTouchSlop) {
				if (isRefreshViewScroll(deltaY)) {
//					System.out.println("kkkkk");
					return true;
				}
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			break;
		}
		return false;
	}

	public void setLock(boolean lock) {
		mLock = lock;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mLock) {
			return true;
		}
		int y = (int) event.getRawY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			int deltaY = y - mLastMotionY;
			if (mPullState == PULL_DOWN_STATE&&headerRefreshState) {
				headerPrepareToRefresh(deltaY);
			} else if (mPullState == PULL_UP_STATE && footerRefreshState) {
				footerPrepareToRefresh(deltaY);
			}
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			int topMargin = getHeaderTopMargin();
			if (mPullState == PULL_DOWN_STATE&&headerRefreshState) {
				if (topMargin >= 0) {
					headerRefreshing();
				} else {
					setHeaderTopMargin(-mHeaderViewHeight);
					if (mOnHeaderRefreshListener != null) {
						mOnHeaderRefreshListener.onHeaderRefreshCancle(this);
					}
				}
				 
			} else if (mPullState == PULL_UP_STATE && footerRefreshState) {
				if (Math.abs(topMargin) >= mHeaderViewHeight/2
						+ mFooterViewHeight) {
					footerRefreshing();
				} else {
					setHeaderTopMargin(-mHeaderViewHeight);
				}
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	private boolean isRefreshViewScroll(int deltaY) {
		if (mHeaderState == REFRESHING || mFooterState == REFRESHING) {
			return false;
		}
		if (mAdapterView != null) {
			if (deltaY > 0) {
				View child = mAdapterView.getChildAt(0);
				if (child == null) {
					mPullState = PULL_DOWN_STATE;
					return true;
				}
				if (mAdapterView.getFirstVisiblePosition() == 0
						&& child.getTop() == 0) {
					mPullState = PULL_DOWN_STATE;
					return true;
				}
				int top = child.getTop();
				int padding = mAdapterView.getPaddingTop();
				if (mAdapterView.getFirstVisiblePosition() == 0
						&& Math.abs(top - padding) <= 8) {
					mPullState = PULL_DOWN_STATE;
					return true;
				}

			} else if (deltaY < 0) {
				View lastChild = mAdapterView.getChildAt(mAdapterView
						.getChildCount() - 1);
				if (lastChild == null) {
					return false;
				}
				if (lastChild.getBottom() <= getHeight()
						&& mAdapterView.getLastVisiblePosition() == mAdapterView
								.getCount() - 1) {
					mPullState = PULL_UP_STATE;
					return true;
				}
			}
		}
		if (mScrollView != null) {
			View child = mScrollView.getChildAt(0);
			if (deltaY > 0 && mScrollView.getScrollY() == 0) {
				mPullState = PULL_DOWN_STATE;
				return true;
			} else if (deltaY < 0
					&& child.getMeasuredHeight() <= getHeight()
							+ mScrollView.getScrollY()) {
				mPullState = PULL_UP_STATE;
				return true;
			}
		}
		return false;
	}

	private void headerPrepareToRefresh(int deltaY) {
		
		if (mOnHeaderRefreshListener != null) {
			mOnHeaderRefreshListener.onHeaderParepareRefresh(this);
		}
		int newTopMargin = changingHeaderViewTopMargin(deltaY);
		if (newTopMargin >= 0 && mHeaderState != RELEASE_TO_REFRESH) {
			mHeaderTextView.setText(R.string.pull_to_refresh_release_label);
			mHeaderUpdateTextView.setVisibility(View.VISIBLE);
			mHeaderImageView.clearAnimation();
			mHeaderImageView.startAnimation(mFlipAnimation);
			mHeaderState = RELEASE_TO_REFRESH;
		} else if (newTopMargin < 0 && newTopMargin > -mHeaderViewHeight) {
			mHeaderImageView.clearAnimation();
			mHeaderImageView.startAnimation(mFlipAnimation);
			// mHeaderImageView.
			mHeaderTextView.setText(R.string.pull_to_refresh_pull_label);
			mHeaderState = PULL_TO_REFRESH;
		}
	}

	private void footerPrepareToRefresh(int deltaY) {
		int newTopMargin = changingHeaderViewTopMargin(deltaY);
		if (Math.abs(newTopMargin) >= (mHeaderViewHeight/2 + mFooterViewHeight)
				&& mFooterState != RELEASE_TO_REFRESH) {
			mFooterTextView
					.setText(R.string.pull_to_refresh_footer_release_label);
			mFooterState = RELEASE_TO_REFRESH;
		} else if (Math.abs(newTopMargin) < (mHeaderViewHeight/2 + mFooterViewHeight)) {
			mFooterTextView.setText(R.string.pull_to_refresh_footer_pull_label);
			mFooterState = PULL_TO_REFRESH;
		}
	}

	private int changingHeaderViewTopMargin(int deltaY) {
		LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
		float newTopMargin = params.topMargin + deltaY * 0.3f;
		if (deltaY > 0 && mPullState == PULL_UP_STATE
				&& Math.abs(params.topMargin) <= mHeaderViewHeight) {
			return params.topMargin;
		}
		if (deltaY < 0 && mPullState == PULL_DOWN_STATE
				&& Math.abs(params.topMargin) >= mHeaderViewHeight) {
			return params.topMargin;
		}
		params.topMargin = (int) newTopMargin;
		mHeaderView.setLayoutParams(params);
		invalidate();
		return params.topMargin;
	}

	public void headerRefreshing() {
		mHeaderState = REFRESHING;
		 
		setHeaderTopMargin(0);
		mHeaderImageView.setVisibility(View.GONE);
		mHeaderImageView.clearAnimation();
		mHeaderImageView.setImageDrawable(null);
		mHeaderProgressBar.setVisibility(View.VISIBLE);
		mHeaderTextView.setText(R.string.pull_to_refresh_refreshing_label);
		if (mOnHeaderRefreshListener != null) {
			mOnHeaderRefreshListener.onHeaderRefresh(this);
		}
	}

	private void footerRefreshing() {
		mFooterState = REFRESHING;
		int top = mHeaderViewHeight + mFooterViewHeight;
		setHeaderTopMargin(-top);
		mFooterProgressBar.setVisibility(View.VISIBLE);
		mFooterTextView
				.setText(R.string.pull_to_refresh_footer_refreshing_label);
		if (mOnFooterRefreshListener != null) {
			mOnFooterRefreshListener.onFooterRefresh(this);
		}
	}

	private void setHeaderTopMargin(int topMargin) {
		LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
		params.topMargin = topMargin;
		mHeaderView.setLayoutParams(params);
		invalidate();
	}

	public void onHeaderRefreshComplete() {
		setHeaderTopMargin(-mHeaderViewHeight);
		mHeaderImageView.setVisibility(View.VISIBLE);
		mHeaderImageView.setImageResource(R.drawable.xlistview_arrow);
		mHeaderTextView.setText(R.string.pull_to_refresh_pull_label);
		mHeaderProgressBar.setVisibility(View.GONE);
		mHeaderState = PULL_TO_REFRESH;
	}

	/**
	 * Resets the list to a normal state after a refresh.
	 * 
	 * @param lastUpdated
	 *            Last updated at.
	 */
	public void onHeaderRefreshComplete(CharSequence lastUpdated) {
		
		setLastUpdated(lastUpdated);
		onHeaderRefreshComplete();
	}
	
	public void setHeaderRefreshComplete() {
		
		setLastUpdated(DateTool.Now());
		onHeaderRefreshComplete();
	}
	
	

	public void onFooterRefreshComplete() {
		setHeaderTopMargin(-mHeaderViewHeight);
		mFooterTextView.setText(R.string.pull_to_refresh_footer_pull_label);
		mFooterProgressBar.setVisibility(View.GONE);
		mFooterState = PULL_TO_REFRESH;
	}

	/**
	 * Set a text to represent when the list was last updated.
	 * 
	 * @param lastUpdated
	 *            Last updated at.
	 */
	public void setLastUpdated(CharSequence lastUpdated) {
		if (lastUpdated != null) {
			mHeaderUpdateTextView.setVisibility(View.VISIBLE);
			mHeaderUpdateTextView.setText(lastUpdated);
		} else {
			mHeaderUpdateTextView.setVisibility(View.GONE);
		}
	}

	private int getHeaderTopMargin() {
		LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
		return params.topMargin;
	}

	public void setOnHeaderRefreshListener(
			OnHeaderRefreshListener headerRefreshListener) {
		mOnHeaderRefreshListener = headerRefreshListener;
	}

	public void setOnFooterRefreshListener(
			OnFooterRefreshListener footerRefreshListener) {
		mOnFooterRefreshListener = footerRefreshListener;
	}

	/**
	 * Interface definition for a callback to be invoked when list/grid footer
	 * view should be refreshed.
	 */
	public interface OnFooterRefreshListener {
		public void onFooterRefresh(PullToRefreshView view);
	}

	/**
	 * Interface definition for a callback to be invoked when list/grid header
	 * view should be refreshed.
	 */
	public interface OnHeaderRefreshListener {
		public void onHeaderRefresh(PullToRefreshView view);
		public void onHeaderParepareRefresh(PullToRefreshView view);
		public void onHeaderRefreshCancle(PullToRefreshView view);
	}

    public void setRefreshFooterState(boolean state) {
        this.footerRefreshState = state;
    }
    
    public void enableHeaderRefresh()
    {
    	setRefreshHeaderState(true);
    }
    public void disableHeaderRefresh()
    {
    	setRefreshHeaderState(false);
    }
    public void setRefreshHeaderState(boolean state) {
        this.headerRefreshState = state;
    }

}
