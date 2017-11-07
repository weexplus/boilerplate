package com.farwolf.camera;

import java.io.FileOutputStream;
import java.io.IOException;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.farwolf.base.FragmentBase;
import com.farwolf.business.R;

@EFragment
public abstract class CameraFragment extends FragmentBase {

	
	public static final int GotoPhoto = 0;
	public static final int GotoCamera = 1;
	public static final int ChooseCompelete = 2;
	public static final int RESIZE = 3;
	  @ViewById
	 public SurfaceView preview; 
	  
	  @ViewById
	  public ImageView img; 
	  
	  SurfaceHolder surfaceHodler;  
	
	  int screenWidth, screenHeight;  
	  
	   public  Camera camera;  
	    // 是否存在预览中  
	   public  boolean isPreview = false;  
	    
	 
	
    @AfterViews
	public void init()
	{

		getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
	
        // 获取窗口管理器  
        WindowManager wm = getActivity().getWindowManager();  
        Display display = wm.getDefaultDisplay();  
        DisplayMetrics metrics = new DisplayMetrics();  
        // 获取屏幕的宽和高  
        display.getMetrics(metrics);  
        screenWidth = metrics.widthPixels;  
        screenHeight = metrics.heightPixels;  
     
        // 设置surface不需要自己的维护缓存区  
        preview.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  
        // 获得SurfaceView的SurfaceHolder  
        surfaceHodler = preview.getHolder();  
        initListener();
	}
	
    
    public void startShot()
    {
    	preview.setVisibility(View.VISIBLE);
    	preview.requestLayout();
    	 surfaceHodler = preview.getHolder();  
         initListener();
    }
    
    
    @OnActivityResult(ChooseCompelete)
	public void onChooseCompelete(int result,Intent in)
	{
		if(in==null)
		{
			onPhotoResize(null);
			return;
		}
		Bitmap bm = in.getParcelableExtra("data");
//		Drawable drawable = new BitmapDrawable(photo);
		 
		onPhotoResize(bm);
		
		 
	}
    
    @OnActivityResult(GotoPhoto)
    public void onPhotoChoose(int result,Intent in)
	{
    	
		if(in==null)
		{
			this.onPhotoChoose(null);
			return;
		}
	 
	    this.onPhotoChoose(in.getData());
		 
			 
		 
	 
	}
    
    public void startPhotoZoom(Uri uri,int aspectX,int aspectY,int outputX,int outputY) 
    {

		Intent intent = new Intent("com.android.camera.action.CROP");		 
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", aspectX);
		intent.putExtra("aspectY", aspectY);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, ChooseCompelete);
	}
	
    
     
	public void initListener()
	{
		 surfaceHodler.addCallback(new Callback() {  
			  
	            @Override  
	            public void surfaceDestroyed(SurfaceHolder arg0) {  
	                // 如果camera不为null，释放摄像头  
//	            	toast("销毁摄像头");
	                if (camera != null) {  
	                    if (isPreview)  
	                        camera.stopPreview();  
	                    camera.release();  
	                    camera = null;  
	                }  
	            }  
	  
	            @Override  
	            public void surfaceCreated(SurfaceHolder arg0) {  
	                // 打开摄像头  
	                initCamera();  
	               
	            }  
	  
	            @Override  
	            public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,  
	                    int arg3) {  
	            }  
	        });  
	}
	
	
	 
	
    @Click	
	public void shotClicked()
	{
    	if(camera!=null)
    	camera.takePicture(null, null, myJpegCallback);
    	
    	
	}
 
    
	@Click
	public void gophotoClicked()
	{
	
		Intent intent = new Intent();
		intent.setType("image/*"); // 设置文件类型
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent,GotoPhoto);
	}
	
	@SuppressLint("NewApi")
	private void initCamera() {  
//		 toast("isPreview="+isPreview);
        if (!isPreview) {  
            // 此处默认打开后置摄像头  
            // 通过传入参数可以打开前置摄像头  
            camera = Camera.open();  
            camera.setDisplayOrientation(90); 
//            toast("新建摄像头");
        }  
        if (!isPreview && camera != null) {  
            Camera.Parameters parameters = camera.getParameters();  
            // 设置预览照片的大小  
            parameters.setPreviewSize(screenWidth, screenHeight);  
            // 设置预览照片时每秒显示多少帧的最小值和最大值  
            parameters.setPreviewFpsRange(4, 10);  
            // 设置照片的格式  
            parameters.setPictureFormat(ImageFormat.JPEG);  
            // 设置JPG照片的质量  
            parameters.set("jpeg-quality", getQality());  
            // 设置照片的大小  
            parameters.setPictureSize(screenWidth, screenHeight);  
            // 通过SurfaceView显示取景画面  
            
            
            
            if (Integer.parseInt(Build.VERSION.SDK) >= 8) { 
                camera.setDisplayOrientation(90); 
            } else { 
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) { 
                   parameters.set("orientation", "portrait"); 
                   parameters.set("rotation", 90); 
                } 
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) { 
                   parameters.set("orientation", "landscape"); 
                   parameters.set("rotation", 90); 
                } 
            }  
            
            
            
            try {  
                camera.setPreviewDisplay(surfaceHodler);  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
            // 开始预览  
            camera.startPreview();  
            isPreview = true;  
        }  
    }  

	public int getQality()
	{
		return 85;
	}
	
	 public void capture(View source) {  
	        if (camera != null) {  
	            // 控制摄像头自动对焦后才拍摄  
	            camera.autoFocus(autoFocusCallback);  
	        }  
	    }  
	  
	    AutoFocusCallback autoFocusCallback = new AutoFocusCallback() {  
	  
	        @Override  
	        public void onAutoFocus(boolean arg0, Camera arg1) {  
	            if (arg0) {  
	                // takePicture()方法需要传入三个监听参数  
	                // 第一个监听器；当用户按下快门时激发该监听器  
	                // 第二个监听器；当相机获取原始照片时激发该监听器  
	                // 第三个监听器；当相机获取JPG照片时激发该监听器  
	                camera.takePicture(new ShutterCallback() {  
	  
	                    @Override  
	                    public void onShutter() {  
	                        // 按下快门瞬间会执行此处代码  
	                    }  
	                }, new PictureCallback() {  
	  
	                    @Override  
	                    public void onPictureTaken(byte[] arg0, Camera arg1) {  
	                        // 此处代码可以决定是否需要保存原始照片信息  
	                    }  
	                }, myJpegCallback);  
	            }  
	  
	        }  
	    };  
	    PictureCallback myJpegCallback = new PictureCallback() {  
	  
	        @Override  
	        public void onPictureTaken(byte[] data, Camera camera) {  
	            // 根据拍照所得的数据创建位图  
	              Bitmap bm = BitmapFactory.decodeByteArray(data, 0,  
	                    data.length);  
	            camera.stopPreview();  
	            camera.startPreview();  
	            
	            
	            Matrix matrix = new Matrix();
	            matrix.setRotate(90);
	            bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
	          
	            
	            
	            
//	            isPreview=true;  
//	            preview.setVisibility(View.GONE);
	            onPhotoTake(bm);
//	            img.setImageBitmap(bm);
	            
	        }  
	    };  
	    
	    
	    public abstract void onPhotoTake(Bitmap bm);
	    public abstract void onPhotoChoose(Uri url);
	    public  void onPhotoResize(Bitmap bm)
	    {
	    	
	    }
	   
	    @Override
	    public void onResume() {
	    	// TODO Auto-generated method stub
	    	super.onResume();
	    	preview.setVisibility(View.VISIBLE);
//	    	startShot();
	    }
	    
	    @Override
	    public void onPause() {
	    	// TODO Auto-generated method stub
	    	super.onPause();
	    	preview.setVisibility(View.INVISIBLE);
			isPreview=false;
	    }
	    
	@Override
	public int getViewId() {
		// TODO Auto-generated method stub
		return R.layout.api_camera_fragment;
	}

}
