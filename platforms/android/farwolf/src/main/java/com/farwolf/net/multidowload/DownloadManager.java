package com.farwolf.net.multidowload;

import java.io.File;
import java.io.IOException;



import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

 

public class DownloadManager {
	
	
	private final static int Processing = 1;//正在下载标志
	private final static int Failure = -1;//下载失败标志、
	
	private MyHandler mHandler = new MyHandler();
	long total=0;
	long current=0;
	
	Downloadask task;
	
	boolean isrun=false;
	
	String fileid="";

	Context context;
	String url;
	String path;
	
	public DownloadManager(Context context,String fileid,String url,String path) {
		super();
		this.context = context;
		this.fileid=fileid;
		this.url=url;
		this.path=path;
	}



	 
	 
	
	
    
    
    DownloadListener downloadListener;
    
    public void setDownloadListener(DownloadListener downloadListener) {
		this.downloadListener = downloadListener;
		
	}

	public static interface DownloadListener
    {
    	void onProgress(String fileid,long current,long total);
    	void onCompelete(String fileid);
    	void onException(String fileid);
    }
	
	
	public boolean isRun()
	{
		return isrun;
	}
	
	public void start()
	{
		isrun=true;
		 task = new Downloadask(url,path);//实例化下载任务,开始下载
		 task.start(); //开始下载任务
	}
	
	public void stop()
	{ 
		isrun=false;
		if (task != null)   task.exit();
	}
	
	
	
	 private class MyHandler extends Handler{
			/* (non-Javadoc)
			 * @see android.os.Handler#handleMessage(android.os.Message)
		     ***************************************************************************************
			 *系统会自动回调方法，用于处理消息事件
			 *Message 一般会包含消息的标志和消息的内容以及消息的处理器Handler
			 */
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case Processing:   //下载时
					//从消息中获取已经下载的数据长度
					 current = msg.getData().getInt("size");					  
//					 Log.i(TAG, "下载中Processing,"+"size "+current+"KB,  result百分比是："+result+"%");
					 
					 if(downloadListener!=null)
                     {
						 if(current==total)
						 {
							  downloadListener.onCompelete(fileid);
							  isrun=false;
						 }
						 else
						 {
							  downloadListener.onProgress(fileid, current, total);
						 }
                   	  
                   	    return;
                     }
					 
					break;
					
				case Failure :    //下载失败时
					  isrun=false;
					break;

				default:
					break;
				}
			}
		}
    
	
	private class Downloadask extends Thread{
		private String path;//下载路径
		private String saveDir; //下载的保存文件
		private FileDownloader loader; //文件下载器
		/******
		 * < Constructor > 初始化变量
		 * @param path 文件下载路径
		 * @param saveDir 文件保存路径
	     *****************************************************************************************
		 */
		public Downloadask(String path, String saveDir) {
			this.path = path;
			this.saveDir = saveDir;
		}

		/*****
		 *  < Method >  如果下载器存在，就退出下载
	     *****************************************************************************************
		 */
		public void exit(){
			if (loader != null) {
				loader.exit();//如果下载器存在，就退出下载
			}
		}
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
	     *****************************************************************************************
		 */
		@Override
		public void run() {
			
	 
			try {
				loader = new FileDownloader(context, path, saveDir, 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 if(downloadListener!=null)
                 {
					 downloadListener.onException(fileid);
                 }
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				 if(downloadListener!=null)
                 {
					 downloadListener.onException(fileid);
                 }
				e.printStackTrace();
				return;
			}//初始化下载
//			Log.i(TAG, "进度条最大值(文件大小)："+loader.getFileSize());
			if(loader==null)
			{
				if(downloadListener!=null)
                {
					 downloadListener.onException(fileid);
                }
			  return;
			}
			total=loader.getFileSize();
//			progressBar.setMax(loader.getFileSize());//设置进度条最大值为文件的大小
			loader.downLoad(new DownloadProgressListener() {
				@Override
				public void onDownloadSize(int downloadSize) {
					Message msg = new Message();//用于向主线程发送下载进度的Messageduixia
					msg.what = Processing;//设置为1
					msg.getData().putInt("size", downloadSize);//把文件下载的长度放到Message对象
					mHandler.sendMessage(msg);//发送消息到消息队列
				}

				@Override
				public void onDownloadCompelete() {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}
    
}
