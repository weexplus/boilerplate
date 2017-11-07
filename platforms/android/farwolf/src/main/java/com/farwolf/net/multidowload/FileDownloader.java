package com.farwolf.net.multidowload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.util.Log;

/***
 *  @author   YinJie
 *  <Type Create time>     2013年11月14日  、    上午1:10:59
 *  TODO  >>  文件下载器
 *****************************************************************************************
 */
public class FileDownloader {
	
	private final static String TAG = "FileDownloader";//设置打印tag
	private Context context; //上下文对象
	private FileService fileService; // 本地数据库业务Bean
	private boolean isExited = false; //停止下载标志
	private int downloadedSize; //已经下载的文件的长度
	private int fileSize; //文件长度
	private  DownLoadThread[] threads; //根据下载线程下载数设置下载线程池
	private File saveFile; //保存到本地的文件
	//缓存各个线程的下载长度
	private Map<Integer, Integer> data = new ConcurrentHashMap<Integer, Integer>();
	private int block; //每条线程下载的长度
	private String downLoadUrl; //下载路径

	/******
	 * < Constructor > 构建文件下载器
	 * @param context 上下文环境
	 * @param downloadUrl 文件下载路径
	 * @param fileSaveDir  文件保存的目录
	 * @param threadNum 下载的线程数目
	 * @throws Exception 
	 * @throws IOException 
	 *********************************************************************************************
	 */
	public FileDownloader(Context context, String downloadUrl, String fileSaveDir, int threadNum) throws IOException, Exception {
//		try {
			//对变量赋值
			this.context = context;
			this.downLoadUrl = downloadUrl;
			fileService = new FileService(this.context);//实例化数据库业务Bean
			URL url = new URL(this.downLoadUrl);
			//如果指定的文件目录不存在则创建目录，可以创建多层目录
			 
			//根据下载线程数目创建线程池
			this.threads = new DownLoadThread[threadNum];
			//建立一个连接，此时并未真正的开始连接
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setConnectTimeout(5000);//连接超时时间为5秒
			httpConnection.setRequestMethod("GET");//设置为get请求
//			httpConnection.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, "
//					+ "image/pjpeg, application/x-shockwave-flash,"
//					+ " application/xaml+xml, application/vnd.ms-xpsdocument, "
//					+ "application/x-ms-xbap, application/x-ms-application, "
//					+ "application/vnd.ms-excel, application/vnd.ms-powerpoint,"
//					+ " application/msword, */*");//设置客户端可以接受的媒体类型
			//设置客户端的语言
			httpConnection.setRequestProperty("Accept-language", "zh-CN");
			//设置请求来源的页面，便于服务器请求
			httpConnection.setRequestProperty("Referer", downloadUrl);
			//设置客户端的编码方式
			httpConnection.setRequestProperty("Charset", "UTF-8");
			//设置用户代理
			//			httpConnection.setRequestProperty("User-Agent", "Mozilla/4.0");
			//设置connection的方式
			httpConnection.setRequestProperty("connection", "Keep-Alive");
			//设置用户代理
			httpConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; "
					+ "MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727;"
					+ " .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
			httpConnection.connect();//和远程的资源建立连接，尚无数据流返回
			printHttpResponseHeader(httpConnection);//打印HTTP头字段
			if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				//此处的请求会请求打开返回流并获取返回码，当返回码等于HTTP_OK(200)时执行下面的代码
				Log.i(TAG, "返回HTTP_OK，请求成功!====================================");
				this.fileSize = httpConnection.getContentLength();//获取文件大小
				if (this.fileSize  <= 0) {
					//当文件小于等于0时候抛出运行时异常
					throw new RuntimeException("未知的文件大小");
				}
//				String fileName = getFileName(httpConnection);//获取文件名
				Log.i(TAG, "文件大小"+ fileSize+"B");
				Log.i(TAG, "获取到的文件名 "+ fileSaveDir);
				//根据文件目录和文件名保存文件
				this.saveFile = new File(fileSaveDir);
			
				//获取数据库中的下载记录
				Map<Integer, Integer> logData = fileService.getData(downloadUrl);
				Log.i(TAG, "数据库中的缓存线程数："+ logData.size());
				if (logData.size()>0) { //如果数据库中存在下载记录
					Log.i(TAG, "数据库中存在下载记录，开始断点下载 ");
					for (Map.Entry<Integer, Integer> entry : logData.entrySet()) {//遍历集合
						//把数据库中缓存的各线程的已经下载的数据放到data中
						Log.i(TAG,"线程 " +entry.getKey()+"已经下载大小 "+ entry.getValue()+"B");
						data.put(entry.getKey(), entry.getValue());
					}
				}
				if (this.data.size() == this.threads.length) {
					//如果已经下载的数据的线程数和现在设置的线程数相同则计算所有的线程下载长度的和
					for (int i = 0; i <  this.threads.length; i++) {//遍历每条线程已经下载的数据
						//计算已经下载的数据之和
						this.downloadedSize += this.data.get(i+1);
					}
					Log.i(TAG, "已经下载的数据之和是 "+ this.downloadedSize+"B");
				}
			}
			else
			{
				throw new Exception("服务器异常:"+httpConnection.getResponseCode());
			}
			//计算每条线程的下载长度
			this.block = (this.fileSize%this.threads.length) == 0 ? 
					this.fileSize/this.threads.length  :
						this.fileSize/this.threads.length+1;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  
	}

	/*****
	 *  < Method >  获取文件名
	 *********************************************************************************************
	 */
	private String getFileName(HttpURLConnection httpConnection) {
		//从下载路径字符窜中提取文件名
		String fileName = this.downLoadUrl.substring(this.downLoadUrl.lastIndexOf('/')+1);
		if (fileName == null || "".equals(fileName.trim())) {
			//如果获取不到文件名
			for (int i = 0; ; i++) { //无线遍历循环
				String mine = httpConnection.getHeaderField(i);
				if (mine == null) break;//到文件末尾退出循环
				if ("content-disposition".equals(httpConnection.getHeaderFieldKey(i)
						.toLowerCase())) {//获取content-disposition 字段，里面可能包含文件名
					//使用正则表达式查询文件名
					Matcher matcher = Pattern.compile(".*filename=(.*)").matcher(mine.toLowerCase());
					if (matcher.find()) return matcher.group(1);//符合就返回
				}
			}
			return UUID.randomUUID()+".tmp";
		}
		//由网卡上的标识数字(每个网卡都有唯一的标示)
		//以及CPU时钟的唯一数字生成的一个十六进制的数字作为文件名
		return fileName;
	}

	/*****
	 *  < Method >  打印HTTP头字段
	 *********************************************************************************************
	 */
	private static void printHttpResponseHeader(HttpURLConnection httpConnection) {
		
		//使用LinkedHashMap可以保证插入和取出的顺序 相同
		Map<String, String> header = new LinkedHashMap<String, String>();
		for (int i = 0;true; i++) {//此处是无限循环，因为事先不知道头字段的数量
			//获取第i个头字段的值
			String fieldValue = httpConnection.getHeaderField(i);
			if (fieldValue == null)  break;//如果没有值了就表示头字段循环完毕，退出循环
			header.put(httpConnection.getHeaderFieldKey(i), fieldValue);
			//httpConnection.getHeaderFieldKey(i)返回第i个头字段的key
		}
		for (Map.Entry<String, String> entry : header.entrySet()) {
			//使用for-each循环遍历获取字段值，此时的便利顺序与插入的顺序相同
			//三目运算，当有键值的时候就获取键值，否则为空
			String key = entry.getKey()!=null ? entry.getKey() + ":  " : "";
			Log.v(TAG, key+entry.getValue());//打键值对组合
		}
	}

	/*****
	 *  < Method >  获取下载的线程数目
	 *********************************************************************************************
	 */
	public int getThreadSize(){
		return threads.length;
	}

	/*****
	 *  < Method > 设置退出标志为true,退出下载 
	 *********************************************************************************************
	 */
	public void exit(){
		this.isExited = true;
	}

	/*****
	 *  < Method >  获取是否已经退出了下载
	 *********************************************************************************************
	 */
	public boolean getExited(){
		return this.isExited;
	}

	/*****
	 *  < Method >  获取文件的大小
	 *********************************************************************************************
	 */
	public int getFileSize(){
		return fileSize; // 从成员变量中获取下载文件的大小
	}

	/*****
	 *  < Method >  累计已经下载的文件的大小
	 *  使用synchronized关键字解决并发访问的问题
     *****************************************************************************************
	 */
	protected synchronized void appendDownloadSize(int size){
		downloadedSize += size; //把实时下载的文件长度加到已下载的文件总长度中，累计已经下载的文件的大小
	}

	/*****
	 *  < Method >  更新指定线程最后下载的文件位置，多线程操作，使用synchronized关键字解决并发访问的问题
	 *  @param threadId 线程ID
	 *  @param downPosition 线程已经下载到的文件的位置
	 *********************************************************************************************
	 */
	protected synchronized void updateDownloadSize(int threadId , int downPosition){
		//把指定的线程的ID赋予最新的下载长度，原先的值会被覆盖掉
		this.data.put(threadId, downPosition);
		//更新数据库中的线程下载长度
		this.fileService.update(this.downLoadUrl, threadId, downPosition);
	}

	/*****
	 *  < Method >  开始下载数据
	 *  @param downloadProgressListener 监听下载的文件长度的变化，
	 *   如果不需要实时了解下载进度的变化可以传入null
	 *********************************************************************************************
	 */
	public int downLoad(DownloadProgressListener downloadProgressListener ){
			try {
				/*
				 * 在本地创建一个和所要下载的文件相等大小的文件
				 * 使用RandomAccessFile创建，以便不同线程从不同的位置写入文件
				 */
				if(saveFile==null)
					return this.downloadedSize;
				RandomAccessFile	raf = new RandomAccessFile(saveFile, "rw");
				if (this.fileSize>0) raf.setLength(this.fileSize);//设置文件的大小
				raf.close();//关闭文件使设置生效，成功创建本地文件
				URL url = new URL(this.downLoadUrl);
				if (this.data.size() != this.threads.length) { 
					//如果原先未下载或原先的下载线程数与现在的不一样
					this.data.clear();//清空所有元素
					for (int i = 0; i <this.threads.length; i++) {//遍历线程池
						data.put(i+1, 0);//初始化每条线程的下载量为0
					}
					this.downloadedSize = 0; //设置已经下载的长度为0 
				}
				for (int i = 0; i < this.threads.length; i++) {//开启线程进行下载
					int downloadedLength = this.data.get(i+1);//通过特定的线程id获取该线程的已经下载的文件长度
					if (downloadedLength < this.block &&this.downloadedSize<this.fileSize) {//判断是否下载完成
						this.threads[i] = new DownLoadThread(this,url,this.saveFile,this.block,this.data.get(i+1),i+1);
						this.threads[i].setPriority(7);//设置线程优先级
						this.threads[i].start();//开始下载
						Log.i(TAG, "线程 "+(i+1)+" 启动");
					}else {
						this.threads[i]  = null;//表名线程已经下载完成了，设置为null
					}
				}
				fileService.delete(this.downLoadUrl);//如果存在下载记录就删除它们然后再添加
				fileService.save(this.downLoadUrl, this.data);//把已经下载的写进数据库
				//Log.i(TAG, "data的数目是 "+data.size());
				boolean notFinished  =  true;//下载未完成
				while (notFinished) {//循环所有线程是都下载完成
					try {
						Thread.sleep(400);//休眠400毫秒
						notFinished = false;//假定所有线程都下载完成了
						for (int i = 0; i <this.threads.length; i++) {
							if ((this.threads[i] != null) && !(this.threads[i].isFinished())) {//如果有线程未下载完成
								notFinished = true;//设置标志位为下载完成
								if (this.threads[i].getDownlodedSize() == -1) {
									//如果下载失败，在原来已经下载的数据基础上重新下载
									//重新开辟新线程下载
									this.threads[i] = new DownLoadThread(this,url,this.saveFile,this.block,this.data.get(i+1),i+1);
									this.threads[i].setPriority(7);//设置线程优先级
									this.threads[i].start();//开始下载
								}
							}
							if (downloadProgressListener != null) //如果添加了监听器，通知目前已经下载的文件的长度
								downloadProgressListener.onDownloadSize(this.downloadedSize);
							if (downloadedSize == this.fileSize)//下载完成删除记录
								{
//								   Log.i("downloadsize=", downloadedSize+"/"+fileSize);
								   fileService.delete(this.downLoadUrl);
//								   if (downloadProgressListener != null) //如果添加了监听器，通知目前已经下载的文件的长度
//										downloadProgressListener.onDownloadCompelete();
								}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return this.downloadedSize;
	}
}
