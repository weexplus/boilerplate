package com.farwolf.net.multidowload;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**********
 *  @author   YinJie
 *  <Type Create time>     2013年11月13日  、    下午11:38:57
 *  TODO  >>  
 *   业务Bean,实现对数据库的操作
 *****************************************************************************************
 */
public class FileService {
	//private  final static String TAG = "FileService";
	private DBOpenHelper openHelper;  //声明数据库管理类
	
	/***
	 * < Constructor >
     *****************************************************************************************
	 */
	public FileService(Context context) {
		//实例化数据库数据库管理器
		openHelper = new DBOpenHelper(context);
	}

	/***
	 *  < Method >  
	 *  获取每条线程下载长度的方法
	 *  @param path 文件下载的路径，用来从数据库中获取该路径的文件下载信息
	 *  @return 返回线程ID和此线程下载的长度的集合
     *****************************************************************************************
	 */
	public Map<Integer,Integer> getData(String path){
		//获得可读数据库句柄，一般情况下该操作的内部实现中实际返回的是可写的数据库句柄
		SQLiteDatabase db = openHelper.getReadableDatabase();
		//根据下载的路径查询所有线程下载的数据，返回的cursor返回第一条记录
		Cursor cursor = db.rawQuery("select threadid,downlength from downloadlog where downpath=?", 
																		new String[]{path});
		//定义一个哈希表用于储存每条线程已经下载的文件长度
		Map<Integer, Integer> data = new HashMap<Integer, Integer>();
			//遍历cursor对象
			while (cursor.moveToNext()) {
				//把线程ID号(threadid)和该线程已经下载的长度(downlength)作为键值对存到哈希表
				//map.put(cursor.getInt(0), cursor.getInt(1));
				data.put(cursor.getInt(cursor.getColumnIndex("threadid")), 
									cursor.getInt(cursor.getColumnIndex("downlength")));
			}
		cursor.close();//关闭cursor释放资源
		db.close();//关闭数据库
		return data;//返回每条线程下载的长度集
	}
	/***
	 *  < Method >  
	 *  保存每条线程下载的文件的长度
	 *  @param path 文件下载路径
	 *  @param map 线程的ID和此线程下载长度的集合
     *****************************************************************************************
	 */
	public void save(String path , Map<Integer,Integer> map){
		//获得可读数据库句柄，一般情况下该操作的内部实现中实际返回的是可写的数据库句柄
				SQLiteDatabase db = openHelper.getReadableDatabase();
				db.beginTransaction();//开始事务，因为此处要插入多批数据
				try {
					for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
						//采用For-Each方式遍历数据集合
						//插入特定的路径、线程及其所下载的文件的长度
						db.execSQL("insert into downloadlog(downpath,threadid,downlength) values (?,?,?);", 
												new Object[]{path,entry.getKey(),entry.getValue()});
						//设置事务执行的标志为成功
					}
					db.setTransactionSuccessful();
				}finally{
					//如果不杀死虚拟机的话，此部分代码是必定会被执行的
					//结束一个事务，如果事务设置了成功标志则提交事务，否则事务回滚
					db.endTransaction();
				}
				db.close();//关闭数据库释放资源
	}
	
	/*****
	 *  < Method >  
	 *  实时更新每条线程已经下载的文件的长度
	 *  @param path 文件下载路径
	 *  @param threadId 线程的ID
	 *  @param position 下载的位置
     *****************************************************************************************
	 */
	public void update(String path , int threadId , int position){
		//获得可读数据库句柄，一般情况下该操作的内部实现中实际返回的是可写的数据库句柄
		SQLiteDatabase db = openHelper.getReadableDatabase();
		db.beginTransaction();
		try{
			//更新特定的下载路径、特定线程，已经下载的文件长度
			db.execSQL("update downloadlog set downlength=? where downpath=? and threadid=?;",
									new Object[]{position, path, threadId});
			db.setTransactionSuccessful();
		}finally{
			if(db.isOpen())
			db.endTransaction();
		}
		db.close();//关闭数据库释放资源
	}
	
	/*****
	 *  < Method >  
	 *  当文件下载完成之后删除相关的下载记录
	 *  @param path 文件下载的路径
     *****************************************************************************************
	 */
	public void delete(String path){
		//获得可读数据库句柄，一般情况下该操作的内部实现中实际返回的是可写的数据库句柄
		SQLiteDatabase db = openHelper.getReadableDatabase();
		//删除特定下载路径的所有线程记录
		db.execSQL("delete from downloadlog where downpath=?", new String[]{path});
		db.close();//关闭数据库释放资源
	}
}
