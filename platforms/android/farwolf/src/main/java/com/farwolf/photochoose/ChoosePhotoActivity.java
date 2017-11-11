package com.farwolf.photochoose;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.farwolf.base.ActivityBase;
import com.farwolf.libary.R;
import com.farwolf.photochoose.cropimage.CropImage;
import com.farwolf.util.FileTool;
import com.farwolf.util.Picture;
import com.farwolf.util.ScreenTool;
import com.farwolf.util.UILImageLoader;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

@EActivity
public class ChoosePhotoActivity extends ActivityBase{


	
	public static final int GotoPhoto = 0;
	public static final int GotoCamera = 1;
	public static final int ChooseCompelete = 2;
	public static final int PhotoUrl = 3;
	public static final int FileUrl = 4;
	public static final int StreamUrl = 5;
	
	private static final String IMAGE_FILE_NAME = "temp.jpg";

	int width=320;
	int height=320;


	String themeColor="#29A1F7";



	
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


	
	
	boolean resize=false;


	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		init();
	}

	@Override
	public int getViewId() {
		// TODO Auto-generated method stub

		return  R.layout.api_choosephoto_activity;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		WindowManager.LayoutParams lp =getWindow().getAttributes();
		lp.x=0;
		lp.y=stool.getScreenHeight();
		lp.horizontalMargin=0;
		lp.verticalMargin=0;
		resize=getIntent().getBooleanExtra("resize", true);
		width=getIntent().getIntExtra("width",320);
		height=getIntent().getIntExtra("height",320);
		themeColor=getIntent().getStringExtra("themeColor");
	}
	
	@Click
	void cancelClicked()
	{
		finish();
	}
	
	
	@Click
	void cameraClicked()
	{

		openCamrea();
		
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(0,R.anim.bottom_out_top);
	}

	void openPhoto()
	{
		File editdir=new File(getCacheDir()+"gallery/edit");
		File takephoto=new File(getCacheDir()+"gallery/photo");
		ThemeConfig theme = new ThemeConfig.Builder()
				.setTitleBarBgColor(Color.parseColor(themeColor))
				.setFabNornalColor(Color.parseColor(themeColor))
				.setFabPressedColor(Color.parseColor(themeColor))
				.setCropControlColor(Color.parseColor(themeColor))
				.build();
//        //配置功能
		FunctionConfig functionConfig = new FunctionConfig.Builder()
				.setEnableCamera(true)
				.setEnableEdit(true)
				.setEnableCrop(true)
				.setEnableRotate(true)

				.setCropWidth(width)
				.setCropHeight(height)
				.setForceCrop(true)
				.setForceCropEdit(true)
				.setEnablePreview(true)

				.build();
		CoreConfig coreConfig = new CoreConfig.Builder(this, new UILImageLoader(), theme)
				.setFunctionConfig(functionConfig)
				.setEditPhotoCacheFolder(editdir)
				.setTakePhotoFolder(takephoto)

				.build();
		GalleryFinal.init(coreConfig);

		GalleryFinal.openGallerySingle(1012,functionConfig, new GalleryFinal.OnHanlderResultCallback() {
			@Override
			public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
				String url= resultList.get(0).getPhotoPath();
				Log.i("url",url);
				Intent in=new Intent();
				in.putExtra("path", url);
				setResult(PhotoUrl, in);
				finish();
			}

			@Override
			public void onHanlderFailure(int requestCode, String errorMsg) {

			}
		});
	}


	void openCamrea()
	{
		File editdir=new File(getCacheDir()+"gallery/edit");
		File takephoto=new File(getCacheDir()+"gallery/photo");
		ThemeConfig theme = new ThemeConfig.Builder()
				.setTitleBarBgColor(Color.parseColor(themeColor))
				.setFabNornalColor(Color.parseColor(themeColor))
				.setFabPressedColor(Color.parseColor(themeColor))
				.setCropControlColor(Color.parseColor(themeColor))
				.build();
//        //配置功能
		FunctionConfig functionConfig = new FunctionConfig.Builder()


				.setEnableEdit(true)
				.setEnableCrop(true)
				.setEnableRotate(true)

				.setCropWidth(width)
				.setCropHeight(height)
				.setForceCrop(true)
				.setForceCropEdit(true)
				.setEnablePreview(true)

				.build();
		CoreConfig coreConfig = new CoreConfig.Builder(this, new UILImageLoader(), theme)
				.setFunctionConfig(functionConfig)
				.setEditPhotoCacheFolder(editdir)
				.setTakePhotoFolder(takephoto)

				.build();
		GalleryFinal.init(coreConfig);
		GalleryFinal.openCamera(1011, functionConfig,new GalleryFinal.OnHanlderResultCallback() {
			@Override
			public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
				String url= resultList.get(0).getPhotoPath();
				Log.i("url",url);
				Intent in=new Intent();
				in.putExtra("path", url);
				setResult(FileUrl, in);
				finish();
			}

			@Override
			public void onHanlderFailure(int requestCode, String errorMsg) {

			}
		});
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode!=1011&&requestCode!=1012)
		{
			finish();
		}
	}

	@Click
	void photoClicked()
	{

		openPhoto();

	}
	
//	@OnActivityResult(GotoPhoto)
	void onPhotoChoose(int result,Intent in)
	{
		if(in==null)
		{
			setResult(PhotoUrl, in);
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
			setResult(PhotoUrl, in);
			finish();
		}
	}

	private void startCropImage() {

		File temp = new File(tool.getBaseDir(), IMAGE_FILE_NAME);
		Intent intent = new Intent(this, CropImage.class);
		intent.putExtra(CropImage.IMAGE_PATH, temp.getPath());
		intent.putExtra(CropImage.SCALE, true);

		intent.putExtra(CropImage.ASPECT_X, 3);
		intent.putExtra(CropImage.ASPECT_Y, 2);


		startActivityForResult(intent, ChooseCompelete);
	}


	public static void copyStream(InputStream input, OutputStream output)
			throws IOException {

		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
	}
//	@OnActivityResult(GotoCamera)
	void onCameraChoose(int result,Intent in)
	{
		   
		
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
			setResult(FileUrl, in);
			finish();
		}
		
	}
	
	@OnActivityResult(ChooseCompelete)
	void onChooseCompelete(int result,Intent in)
	{
		if(in==null)
		{
			in=new Intent();

		}


		Bitmap photo = in.getParcelableExtra("data");
		if(photo==null)
		{
			String path = in.getStringExtra(CropImage.IMAGE_PATH);

			in.putExtra("path",path);
		}
//		Drawable drawable = new BitmapDrawable(photo);
		boolean t=photo!=null;
		setResult(StreamUrl, in);
//		toast(t+"");
		finish();
		
		 
	}




	public void startPhotoZoom(Uri uri) {

//		String path=SDCard.getBasePath(this);
//		path+="temp/1.jpg";

		Intent intent = new Intent("com.android.camera.action.CROP");
		String path=tool.getBaseDir()+IMAGE_FILE_NAME;
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", width);
		intent.putExtra("aspectY", height);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", width);
		intent.putExtra("outputY", height);
		intent.putExtra("output",path);

		intent.putExtra("return-data", true);
		startActivityForResult(intent, ChooseCompelete);



//
//		Intent intent = new Intent(this, CropImage.class);
//          Log.i("",uri.getPath());
//		// tell CropImage activity to look for image to crop
//		String filePath = tool.getBaseDir()+IMAGE_FILE_NAME;
//		intent.putExtra(CropImage.IMAGE_PATH, uri.getPath());
//
//		// allow CropImage activity to rescale image
//		intent.putExtra(CropImage.SCALE, true);
//
//		// if the aspect ratio is fixed to ratio 3/2
//		intent.putExtra(CropImage.ASPECT_X, 3);
//		intent.putExtra(CropImage.ASPECT_Y, 2);
//		startActivityForResult(intent, ChooseCompelete);
	}
	

	
	
	
	
	
	
	
}
