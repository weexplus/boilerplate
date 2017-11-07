package com.farwolf.net.multidowload;

/**********
 *  @author   YinJie
 *  <Type Create time>     2013年11月14日  、    上午1:13:16
 *  TODO  >>  下载进度监听器
 * ****************************************************************************************
 */
public interface DownloadProgressListener {

	/*****
	 *  < Method >  下载进度监听方法，获取、处理下载点数据的大小
	 *  @param downloadSize 数据大小
     *****************************************************************************************
	 */
	void onDownloadSize(int downloadSize);
	void onDownloadCompelete();
}
