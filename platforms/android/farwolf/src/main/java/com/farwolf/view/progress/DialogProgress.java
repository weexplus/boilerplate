package com.farwolf.view.progress;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.app.ProgressDialog;
import android.content.Context;

@EBean
public class DialogProgress  implements IProgress{

	protected ProgressDialog dialog ;
	
	String title;
	

	String msg;
	
	@RootContext
	Context context;
	
	public void show()
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

 
	public void init(String title,String msg,Context context)
	{
		this.title=title;
		this.msg=msg;
		this.context=context;
	}
	public void init(String title,String msg)
	{
		this.title=title;
		this.msg=msg;
	
	}
 
	
}
