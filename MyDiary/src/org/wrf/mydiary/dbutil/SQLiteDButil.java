package org.wrf.mydiary.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDButil extends SQLiteOpenHelper {
	private static final String DBNAME="diary";
	private static final int VERSION=1;
	
	
	//方法1 用于对数据库工具类进行构造   通过参数指明数据库名称以及版本号等信息
	public SQLiteDButil(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	//重写该类构造方法
	public SQLiteDButil(Context context) {
		super(context,DBNAME,null,VERSION);
	}
	
	//方法2 用于创建数据库中的数据表   该方法在前台调用时自动调用(CallBack)
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		//编写一个创建数据表的SQL语句
		String userTableSQL="create table users(userid integer primary key autoincrement,account varchar(30),password varchar(30))";
		//使用该方法执行SQL语句，用于创建数据表
		sqLiteDatabase.execSQL(userTableSQL);
		
		//编写一个创建数据表的SQL语句用于创建日记表
		String diarysTableSQL="create table diarys(did integer primary key autoincrement,content text,diarydate date,uid int)";
		//执行SexecSQL方法创建数据表
		sqLiteDatabase.execSQL(diarysTableSQL);
		
		
	}

	//方法3 当系统需要升级 (数据库升级)
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	/**
	* 删除数据库
	* @param context
	* @return boolean 删除状态
	*/
	public boolean deleteDatabase(Context context) {
		return context.deleteDatabase(DBNAME);
	}

}
