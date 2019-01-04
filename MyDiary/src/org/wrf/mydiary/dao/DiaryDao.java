package org.wrf.mydiary.dao;

import java.util.HashMap;
import java.util.Map;
import org.wrf.mydiary.dbutil.SQLiteDButil;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DiaryDao {
	/**
	 * �����ռ�ID��ѯ������¼
	 * @param did �ռ�ID
	 * @param context �����Ļ���
	 * @return Map<String,Object> �ռ�����
	 */
	public static Map<String,Object> queryDiary(int did,Context context){
		Map<String,Object>diary=new HashMap<String,Object>();
		//�������ݿ����Ӷ���
		SQLiteDButil dButil=new SQLiteDButil(context);
		//�Զ��ķ�ʽ�����ݿ�
		SQLiteDatabase db = dButil.getReadableDatabase();
		//ƴдSQL���
		String sql="select * from diarys where did=?";
		//ִ��SQL���
		Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(did)});
		//ʹ��Cursor�������
		while(cursor.moveToNext()){
			diary.put("did",cursor.getInt(0));
			diary.put("content", cursor.getString(1));
		}
		
		return diary;
	}
	
	/**
	 * �༭�ռ�
	 * @param did �ռ����ݿ�ID
	 * @param content �ռ�����
	 * @param context �����Ļ���
	 */
	public static  void editDiary(int did,String content,Context context){
		//���ݿ����
		SQLiteDatabase db =null;
		try {
			//�������ݿ����Ӷ���
			SQLiteDButil dButil=new SQLiteDButil(context);
			//��д�ķ�ʽ�������ݿ����
			db= dButil.getWritableDatabase();
			//����SQL���
			String strSQL="update diarys set content=?,diarydate=datetime(CURRENT_TIMESTAMP,'localtime') where did=?";
			//ִ��SQL���
			db.execSQL(strSQL, new Object[]{content,did});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر�db����
			db.close();
		}
	}
	
	/**
	 * �����ռ�
	 * @param content
	 * @param uid
	 * @param context
	 */
	public static  void addDiary(String content,int uid,Context context){
		//���ݿ����
		SQLiteDatabase db =null;
		try {
			//�������ݿ����Ӷ���
			SQLiteDButil dButil=new SQLiteDButil(context);
			//��д�ķ�ʽ�������ݿ����
			db = dButil.getWritableDatabase();
			//����SQL���
			String strSQL="insert into diarys values (null,?,datetime(CURRENT_TIMESTAMP,'localtime'),?)";
			//ִ��SQL���
			db.execSQL(strSQL, new Object[]{content,uid});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر�db����
			db.close();
		}
	}
	
	
	
	/**
	 * ɾ��һ���ռ���Ϣ
	 * @param did
	 * @param context
	 */
	public static  void deleteDiary(Integer did,Context context){
		SQLiteDatabase db=null;
		try {
			//����SQLiteDBUtil���߶���
			SQLiteDButil dButil=new SQLiteDButil(context);
			//��д�ķ�ʽ�����ݿ�
			db= dButil.getWritableDatabase();
			//��дSQL���
			String sql="delete from diarys where did=?";
			//ִ��SQL���
			db.execSQL(sql, new Object[]{did});
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر�����
			db.close();
		}
	}
	
	
	
}
