package com.farwolf.vshop.activity.tab;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ImageView;

import com.farwolf.movie.R;
import com.farwolf.weex.core.Page;
import com.farwolf.weex.core.WeexFactory;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;

import static com.farwolf.vshop.activity.tab.MainActivity.Collection;
import static com.farwolf.vshop.activity.tab.MainActivity.MainPage;
import static com.farwolf.vshop.activity.tab.MainActivity.Movie;
import static com.farwolf.vshop.activity.tab.MainActivity.Serial;

/**   
 * @ClassName LaunchActivity.java 
 * @Description 启动页面
 * @author 徐湘
 * @date 2016年3月16日 下午9:13:37 
 */
@EActivity
@Fullscreen
public class LaunchActivity extends Activity {



	@Bean
	WeexFactory weexFactory;
	@ViewById
	ImageView img;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_launch);

		bindDelay(1000);

	}

	public static HashMap<String,Page> holder=new HashMap<>();

    int finishCount=0;
	public void jump()
	{



		final HashMap<String,String> ex=new 	HashMap<String,String>();
		ex.put("app/busi/tab/mainpage.js",MainPage);
		ex.put("app/busi/tab/serial.js",Serial);

		ex.put("app/busi/tab/movie.js", Movie);
		ex.put("app/busi/tab/collection.js",Collection);



		for(Object key:ex.keySet().toArray())
		{
			final String url=key+"";
			weexFactory.preRender(url,url, new WeexFactory.OnRenderFinishListener() {
				@Override
				public void onRenderFinish(Page p) {
					finishCount++;
					String name=ex.get(url);
					holder.put(name,p);
					if(finishCount==4)
					{


						startActivity(new Intent(LaunchActivity.this, MainActivity_.class));
						finish();
						releaseImageViewResouce(img);
//						img.setImageBitmap(null);
					}
				}
			});
		}

	}


	public static void releaseImageViewResouce(ImageView imageView) {
		if (imageView == null) return;
		Drawable drawable = imageView.getDrawable();
		if (drawable != null && drawable instanceof BitmapDrawable) {
			BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
			Bitmap bitmap = bitmapDrawable.getBitmap();
			if (bitmap != null && !bitmap.isRecycled()) {
				bitmap.recycle();
			}
		}
	}
	
	private void bindDelay(final long time) {
		final Handler achandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
//				getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//				startActivity(new Intent(LaunchActivity.this, MainActivity_.class));
				jump();
//				finish();
			}
		};
		achandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				achandler.sendEmptyMessage(0);
			}
		}, time);
	}


	public void onResume()
	{
		super.onResume();

	}


	public void onPause()
	{
		super.onPause();

	}

}
