package com.farwolf.net.multidowload;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**********
 *  @author   YinJie
 *  <Type Create time>     2013年11月13日  、    下午11:33:38
 *  TODO  >>  
 *   数据库辅助操作类，实现创建和管理数据库表，版本变化时实现实现对表的操作
 * ****************************************************************************************
 */
public class DBOpenHelper extends SQLiteOpenHelper {

	private final static String DBNAME = "eric.db";//数据库名称
	private final static int  VERSION = 1;//数据库版本号
	
	public DBOpenHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}
	
	/*****
	 * < Constructor >
	 * 只有上下文环境的构造方法，在里面调用父类的构造方法
     *****************************************************************************************
	 */
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}



	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
     *****************************************************************************************
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		//如果表不存在就创建表
		String sql = "CREATE TABLE IF NOT EXISTS "
								+ "downloadlog(id integer primary key autoincrement,downpath varchar(100),"
								+ "threadid integer,downlength integer);";
		db.execSQL(sql);//执行语句
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 * 当数据库的版本号变化时会回调此方法
     *****************************************************************************************
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//数据库版本更新是首先要删除原来的表，在实际业务中一般要进行数据备份的
		String sql = "DROP TABLE IF EXISTS downloadlog;";
		db.execSQL(sql);
		//调用onCreate方法重新创建表，也可根据自己的需求自行创建新的表
		this.onCreate(db);
	}
}
