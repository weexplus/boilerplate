package com.farwolf.reader;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.farwolf.interfac.IHttp;

public abstract class TaskBase<T> extends AsyncTask<String,Integer, DataReader<T>> {

	List<IHttp> listeners=new ArrayList<IHttp>();;
	Exception exception;
    protected Context context;
	boolean useToastOnException=true;
	protected ProgressDialog dialog ;
	public void setUseToastOnException(boolean useToastOnException) {
		this.useToastOnException = useToastOnException;
	}
	
	public TaskBase(Context c)
	{
		context=c;
	}
	
	public void AddListener(IHttp l)
	{
		listeners.add(l);
	}
	
	public void show(String title,String msg)
	{
		if(dialog==null)
		{
			if(context!=null)
			dialog=new ProgressDialog(context);
			else {
				return;
			}
		}
		
			
		dialog.setTitle(title);
		dialog.setMessage(msg);
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		dialog.show();
	}
	
	public void dismiss()
	{
		if(dialog!=null)
			dialog.dismiss();
			else {
				return;
			}
		
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		for(IHttp l:listeners)
		{
			if(l!=null)
				l.OnPostStart(null);
		}
		super.onPreExecute();
		
	}

	@Override
	protected void onPostExecute(DataReader<T> reader) {
		// TODO Auto-generated method stub
		dismiss();
		if(exception!=null)
		{
			
			 onException(reader);
			for(IHttp l:listeners)
			{
				if(l!=null)
					l.OnException(exception);
			}
			
			return;
		}
		try
		{
			if(reader.IsSuccess())
			{
				this.onSuccess(reader);
			}
			else
			{
				this.onFail(reader);
			}
			
		}
		catch(Exception e)
		{
			if(!(e instanceof UnknownHostException) )
			 e.printStackTrace();
			ToasShow("网络异常！");		
		}
		finally
		{
			onCompelete(reader);
			dismiss();
		}
		for(IHttp l:listeners)
		{
			if(l!=null)
				l.OnPostCompelete(reader);
		}
		
		
	}


	
	@Override
	protected DataReader<T> doInBackground(String... params) {
		// TODO Auto-generated method stub
		DataReader<T> d=null;
		exception=null;
		try
		{
			d=onDoInBackground(params);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			exception=e;
		}
		return d;
	}
	
   
    
    public  void onCompelete(DataReader<T> reader)
    {
    	
    }
    public abstract DataReader<T> onDoInBackground(String... params) throws Exception;
    public abstract void onSuccess(DataReader<T> reader);
    public  void onFail(DataReader<T> reader)
    {
    	if(reader!=null)
    	ToasShow(reader.getErrorMsg());
    	else
    	ToasShow("网络异常");
    }
    public  void onException(DataReader<T> reader)
    {
    	 
    		ToasShow("网络异常");
    }
   
   
    private void ToasShow(String msg)
    {
    	
    	if(useToastOnException&&context!=null)
    	Toast.makeText(context, msg, 1000).show();
    }

    
    protected String getExceptionMsg()
    {
    	return "网络异常";
    }
}
