package com.farwolf.view.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**   
 * @ClassName AutoImageIndicatorView.java 
 * @Description 自动播放ViewPager指示
 * @author 徐湘
 * @date 2016年3月25日 下午4:09:09 
 */ 
public class AutoImageIndicatorView extends ImageIndicatorView {
	/**
	 * 定时服务
	 */
	private ScheduledExecutorService scheduler;
	/**
	 * 自动播放标志位，默认播放
	 */
	private boolean broadcastEnagle = false;
	/**
	 * 自动播放启动默认时间
	 */
	private static final long DEFAULT_STARTMILS = 2 * 1000;
	/**
	 * 自动播放间隔默认时间
	 */
	private static final long DEFAULT_INTEVALMILS = 3 * 1000;
	/**
	 * 启动时间ms
	 */
	private long startMils = DEFAULT_STARTMILS;
	/**
	 * 间隔ms
	 */
	private long intevalMils = DEFAULT_INTEVALMILS;
	/**
	 * 向右
	 */
	private final static int RIGHT = 0;
	/**
	 * 向左
	 */
	private final static int LEFT = 1;

	/**
	 * 当前方向
	 */
	private int direction = RIGHT;

	/**
	 * 自动播放默认次数（无限）
	 */
	private static final int DEFAULT_TIMES = -1;

	/**
	 * 自动播放次数
	 */
	private int broadcastTimes = DEFAULT_TIMES;

	/**
	 * 自动播放次数记数
	 */
	private int timesCount = 0;

	/**
	 * 循环播放
	 */
	private Handler broadcastHandler = null;

	public AutoImageIndicatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.init();
	}

	public AutoImageIndicatorView(Context context) {
		super(context);
		this.init();
	}

	private void init() {
		this.broadcastHandler = new  BroadcastHandler(AutoImageIndicatorView.this);
		this.scheduler = Executors.newScheduledThreadPool(1);
	}

	/**
	 * 设置自动播放启动时间和间隔
	 * 
	 * @param startMils
	 *            启动时间ms
	 * @param intevelMils
	 *            间隔ms
	 */
	public void setBroadcastTimeIntevel(long startMils, long intevelMils) {
		this.startMils = startMils;
		this.intevalMils = intevelMils;
	}

	/**
	 * 设置自动播放开关
	 * 
	 * @param flag
	 *            打开或关闭
	 */
	public void setBroadcastEnable(boolean flag) {
		this.broadcastEnagle = flag;
	}

	/**
	 * 设置循环播放次数
	 * 
	 * @param times
	 *            循环播放次数
	 */
	public void setBroadCastTimes(int times) {
		this.broadcastTimes = times;
	}

	@Override
	public void show() {
		super.show();
		// 定时播放服务
		this.scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (broadcastEnagle) {
					if (System.currentTimeMillis() - getRefreshTime() < 5 * 1000) {// 最近一次划动间隔小于5s
						return;
					}
					if ((broadcastTimes != DEFAULT_TIMES) && (timesCount > broadcastTimes)) {// 循环次数用完
						return;
					}
					broadcastHandler.sendEmptyMessage(0);
				}
			}
		}, this.startMils, this.intevalMils, TimeUnit.MILLISECONDS);
	}

	protected void handleMessage(Message msg) {
		if (direction == RIGHT) {// roll right
			if (getCurrentIndex() < getTotalCount()) {
				if (getCurrentIndex() == getTotalCount() - 1) {
					timesCount++;// 循环次数次数增加1
					direction = LEFT;
				} else {
					getViewPager().setCurrentItem(getCurrentIndex() + 1, true);
				}
			}
		} else {// roll left
			if (getCurrentIndex() >= 0) {
				if (getCurrentIndex() == 0) {
					direction = RIGHT;
				} else {
					getViewPager().setCurrentItem(getCurrentIndex() - 1, true);
				}
			}
		}
	}
}

class BroadcastHandler extends Handler {
	private  AutoImageIndicatorView autoImageIndicatorView;

	public BroadcastHandler( AutoImageIndicatorView autoImageIndicatorView) {
		this.autoImageIndicatorView = autoImageIndicatorView;
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		if (this.autoImageIndicatorView != null) {
			autoImageIndicatorView.handleMessage(msg);
		}
	}
}
