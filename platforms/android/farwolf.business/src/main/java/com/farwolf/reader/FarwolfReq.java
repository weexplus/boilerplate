package com.farwolf.reader;

import android.content.Context;
import android.util.Log;

import com.farwolf.interfac.IHttp;
import com.farwolf.json.JsonListener;
import com.farwolf.json.JsonReader;
import com.farwolf.volley.VolleyReq;

public class FarwolfReq <T extends JsonReader> extends VolleyReq {

	public FarwolfReq(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}



	public void excuteGet(final JsonListener<T> j)
	{
		super.excute(new IHttp() {

			@Override
			public void OnPostStart(Object o) {
				// TODO Auto-generated method stub
				if(j!=null)
					j.start();
			}

			@Override
			public void OnPostCompelete(Object o) {
				// TODO Auto-generated method stub
				if(j==null)
					return;
				j.compelete();
				String bs=o+"";
				T jx= getJson(bs);
				Log.i("back", bs+"");
				if(jx.isSuccess())
				{
					j.success(jx);
				}
				else
				{
					j.fail(jx,jx.getErrCode(),jx.getErrMsg());
				}
			}

			@Override
			public void OnException(Object o) {
				// TODO Auto-generated method stub
				if(j==null)
					return;
				j.compelete();
				j.exception(o);
			}
		},false);
	}
	public void excute(final JsonListener<T> j)
	{
		super.excute(new IHttp() {
			
			@Override
			public void OnPostStart(Object o) {
				// TODO Auto-generated method stub
				if(j!=null)
					j.start();
			}
			
			@Override
			public void OnPostCompelete(Object o) {
				// TODO Auto-generated method stub
				if(j==null)
					return;
				j.compelete();
				String bs=o+"";
				T jx= getJson(bs);
				Log.i("back", bs+"");
				if(jx.isSuccess())
				{
					j.success(jx);
				}
				else
				{
					j.fail(jx,jx.getErrCode(),jx.getErrMsg());
				}
			}
			
			@Override
			public void OnException(Object o) {
				// TODO Auto-generated method stub
				if(j==null)
					return;
				j.compelete();
				j.exception(o);
			}
		},true);
	}

	
	public   T getJson(String s)
	{
		return (T) new Json(s);
	}



	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
