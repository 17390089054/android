package org.wrf.mydiary.dao;

import java.util.HashMap;
import java.util.Map;
import org.wrf.mydiary.dbutil.SQLiteDButil;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DiaryDao {
	/**
	 * 根据日记ID查询单条记录
	 * @param did 日记ID
	 * @param context 上下文环境
	 * @return Map<String,Object> 日记内容
	 */
	public static Map<String,Object> queryDiary(int did,Context context){
		Map<String,Object>diary=new HashMap<String,Object>();
		//创建数据库连接对象
		SQLiteDButil dButil=new SQLiteDButil(context);
		//以读的方式打开数据库
		SQLiteDatabase db = dButil.getReadableDatabase();
		//拼写SQL语句
		String sql="select * from diarys where did=?";
		//执行SQL语句
		Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(did)});
		//使用Cursor遍历结果
		while(cursor.moveToNext()){
			diary.put("did",cursor.getInt(0));
			diary.put("content", cursor.getString(1));
		}
		
		return diary;
	}
	
	/**
	 * 编辑日记
	 * @param did 日记数据库ID
	 * @param content 日记内容
	 * @param context 上下文环境
	 */
	public static  void editDiary(int did,String content,Context context){
		//数据库对象
		SQLiteDatabase db =null;
		try {
			//创建数据库连接对象
			SQLiteDButil dButil=new SQLiteDButil(context);
			//以写的方式创建数据库对象
			db= dButil.getWritableDatabase();
			//创建SQL语句
			String strSQL="update diarys set content=?,diarydate=datetime(CURRENT_TIMESTAMP,'localtime') where did=?";
			//执行SQL语句
			db.execSQL(strSQL, new Object[]{content,did});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭db连接
			db.close();
		}
	}
	
	/**
	 * 新增日记
	 * @param content
	 * @param uid
	 * @param context
	 */
	public static  void addDiary(String content,int uid,Context context){
		//数据库对象
		SQLiteDatabase db =null;
		try {
			//创建数据库连接对象
			SQLiteDButil dButil=new SQLiteDButil(context);
			//以写的方式创建数据库对象
			db = dButil.getWritableDatabase();
			//创建SQL语句
			String strSQL="insert into diarys values (null,?,datetime(CURRENT_TIMESTAMP,'localtime'),?)";
			//执行SQL语句
			db.execSQL(strSQL, new Object[]{content,uid});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭db连接
			db.close();
		}
	}
	
	
	
	/**
	 * 删除一条日记信息
	 * @param did
	 * @param context
	 */
	public static  void deleteDiary(Integer did,Context context){
		SQLiteDatabase db=null;
		try {
			//创建SQLiteDBUtil工具对象
			SQLiteDButil dButil=new SQLiteDButil(context);
			//以写的方式打开数据库
			db= dButil.getWritableDatabase();
			//编写SQL语句
			String sql="delete from diarys where did=?";
			//执行SQL语句
			db.execSQL(sql, new Object[]{did});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭连接
			db.close();
		}
	}
	
	
	
}
