package com.farwolf.photochoose;

import java.io.File;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.farwolf.base.ActivityBase;
import com.farwolf.libary.R;
import com.farwolf.util.FileTool;
import com.farwolf.util.Picture;
import com.farwolf.util.ScreenTool;
import com.farwolf.view.imgae.crop.Crop;


@EActivity
public class NewChooseActivity extends ActivityBase{

	
	public static final int GotoPhoto = 0;
	public static final int GotoCamera = 1;
	private static final String IMAGE_FILE_NAME = "temp.jpg";
	
	@ViewById
	View camera;
	
	@ViewById
	View photo;
	
	@ViewById
	View cancel;
	
	@Bean
	FileTool tool;
	
	@Bean
	ScreenTool stool;
	
	boolean resize;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		WindowManager.LayoutParams lp =getWindow().getAttributes();
		lp.x=0;
		lp.y=stool.getScreenHeight();
		lp.horizontalMargin=0;
		lp.verticalMargin=0;
		resize=getIntent().getBooleanExtra("resize", true);
	}
	
	
	@Override
	public int getViewId() {
		// TODO Auto-generated method stub
		return  R.layout.api_choosephoto_activity;
	}
	
	@Click
	void cancelClicked()
	{
		 finish();
	}
	
	
	@Click
	void cameraClicked()
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);	
		tool.makeDir(tool.getBaseDir());
		intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(tool.getBaseDir(),IMAGE_FILE_NAME))); 
		startActivityForResult(intent,GotoCamera);
		
	}
	
	
	@Click
	void photoClicked()
	{
	
		Intent intent = new Intent();
		intent.setType("image/*"); // 设置文件类型
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent,GotoPhoto);
	}
	
	
	
	@OnActivityResult(GotoPhoto)
	void onPhotoChoose(int result,Intent in)
	{
		if(in==null)
		{
//			setResult(PhotoUrl, in);
			finish();
			return;
		}
		
		if(resize)
		{
			startPhotoZoom(in.getData());
		}	
		else
		{
			 
			 		 
			in.putExtra("path", in.getData());
//			setResult(PhotoUrl, in);
			finish();
		}
	}
	
  private void beginCrop(Uri source) {
        
     
        File cropFile = new File( tool.getBaseDir(), IMAGE_FILE_NAME);
        Uri outputUri = Uri.fromFile( cropFile);
        new Crop( source).output( outputUri).setCropType( true).start( this);
    }
  
  
  
  
    @OnActivityResult(Crop.REQUEST_CROP)
	void onCrop(int result,Intent in)
	{
//    	toast("剪裁");
    	 if(in==null)
    		 return;
    	 Uri u= in.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
//    	 Bitmap src =  BitmapFactory.decodeFile( u.getPath());
    	 in.putExtra("data", u);
    	 setResult(0,in);
	     finish();
	}
    
    
	@OnActivityResult(GotoCamera)
	void onCameraChoose(int result,Intent in)
	{
	    if(result!=-1)
	    {
	   	   setResult(-1,in);
	       finish();
		   return;
	    }		
		if(resize)
		{
			String path=tool.getBaseDir()+IMAGE_FILE_NAME;
			Log.i("path", path);
			File tempFile =new File(tool.getBaseDir(),IMAGE_FILE_NAME);
			startPhotoZoom(Uri.fromFile(tempFile));
			 
		}
		else
		{
			String path=tool.getBaseDir()+IMAGE_FILE_NAME;
			Picture p=new Picture();  
			in=new Intent();			
//			in.putExtra("data", p.getBitmap(path));
			in.putExtra("path", path);			 
//			setResult(FileUrl, in);
			finish();
		}
		
	}
 
	
	public void startPhotoZoom(Uri uri) 
	{
		  beginCrop(uri);
	}

}
