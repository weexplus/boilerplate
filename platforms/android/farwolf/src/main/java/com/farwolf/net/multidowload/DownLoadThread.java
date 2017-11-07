package com.farwolf.net.multidowload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;

/**********
 *  @author   YinJie
 *  <Type Create time>     2013年11月14日  、    上午1:12:19
 *  TODO  >>  下载线程，根据具体的下载地址、数据保存到文件、下载块的大小、已经下载的数据大小等信息进行下载
 * ****************************************************************************************
 */
public class DownLoadThread extends Thread{

    private final static String TAG = "DownLoadThread";
	private File saveFile;  //下载的数据保存到文件
	private URL downUrl;   //下载的Url
	private int block; //每条线程下载的数据大小
	private int threadId = -1;//初始化线程id设置
	private int downloadedSize; //已经下载到的位置
	private boolean finished;//该线程是否下载完成的标志
	private FileDownloader downloader;//文件下载器

	/******
	 * < Constructor > 初始化变量
	 * @param fileDownloader 文件下载器
	 * @param downUrl 下载地址
	 * @param saveFile 保存的文件
	 * @param block 单个线程下载的长度
	 * @param downloadedSize 已经下载的长度
	 * @param threadId 线程ID
	 * ********************************************************************************************
	 */
	public DownLoadThread(FileDownloader fileDownloader, URL downUrl, 
					File saveFile, int block, int downloadedSize, int threadId) {
		this.downUrl = downUrl;
		this.saveFile = saveFile;
		this.block = block;
		this.downloader = fileDownloader;
		this.threadId = threadId;
		this.downloadedSize = downloadedSize;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 *********************************************************************************************
	 */
	@Override
	public void run() {
		if (downloadedSize<block) {//如果没有下载完成
			//打开连接
			try {
				//建立一个连接，此时并未真正的开始连接
				HttpURLConnection httpConnection = (HttpURLConnection) downUrl.openConnection();
				httpConnection.setConnectTimeout(5 * 1000);
				//使用Get方式下载
				httpConnection.setRequestMethod("GET");
				httpConnection.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, "
												+ "image/pjpeg, application/x-shockwave-flash,"
												+ " application/xaml+xml, application/vnd.ms-xpsdocument, "
												+ "application/x-ms-xbap, application/x-ms-application, "
												+ "application/vnd.ms-excel, application/vnd.ms-powerpoint,"
												+ " application/msword, */*");
				httpConnection.setRequestProperty("Accept-Language", "zh-CN");
				httpConnection.setRequestProperty("Referer", downUrl.toString()); 
				httpConnection.setRequestProperty("Charset", "UTF-8");
				int startPos = block * (threadId - 1) + downloadedSize;//开始位置
				int endPos = block * threadId -1;//结束位置
				httpConnection.setRequestProperty("Range", "bytes=" + startPos + "-"+ endPos);//设置获取实体数据的范围
				httpConnection.setRequestProperty("User-Agent", "Mozilla/4.0 "
						+ "(compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; "
						+ ".NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; "
						+ ".NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
				httpConnection.setRequestProperty("Connection", "Keep-Alive");
				InputStream inStream = httpConnection.getInputStream();//获取远程的输入流
				byte[] buffer = new byte[1024];//设置本地缓存数据位1MB
				int offset = 0;//设置每次读取的数据量
				Log.i(TAG, "线程 "+this.threadId+"  的开始下载位置是:"+ startPos+" B");//打印线程开始的位置
				RandomAccessFile threadFile = new RandomAccessFile(this.saveFile, "rwd");
				threadFile.seek(startPos);//文件指针指向文件开始的地方
				while (!downloader.getExited() && (offset = inStream.read(buffer, 0, 1024)) !=-1) {
					//当用户没有要停止下载，同时没有达到文件的末尾的时候会一直循环读取数据
					threadFile.write(buffer, 0, offset);//直接把数据写到文件中
					downloadedSize += offset;//新下载的长度加到已下载的长度中
					downloader.updateDownloadSize(this.threadId,  downloadedSize);//写进数据库
					downloader.appendDownloadSize(offset);//把新下载的数据长度加到已经下载的数据总长度中
				}
				//当线程下载数据完毕或者用户停止了下载
				threadFile.close();
				inStream.close();
				if (downloader.getExited()) {
					Log.i(TAG, "Thread " + this.threadId+ "停止下载");
				}else {
					Log.i(TAG, "Thread " + this.threadId+ "下载完成");
				}
				//设置下载完成标志位true，无论是下载完成还是用户停止下载
				this.finished = true;
			} catch (IOException e) {
				this.downloadedSize = -1;//设置线程已经下载的长度为-1
				e.printStackTrace();
			}
		}
	}
	/*****
	 *  < Method >  是否下载完成
	 *********************************************************************************************
	 */
	public boolean isFinished(){
		return this.finished;
	}
	/*****
	 *  < Method >  已经下载的内容的大小
	 *  @return 如果返回-1，表示下载失败
	 *********************************************************************************************
	 */
	public  int  getDownlodedSize() {
		return this.downloadedSize;
	}
}
