package org.wrf.mydiary.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDButil extends SQLiteOpenHelper {
	private static final String DBNAME="diary";
	private static final int VERSION=1;
	
	
	//����1 ���ڶ����ݿ⹤������й���   ͨ������ָ�����ݿ������Լ��汾�ŵ���Ϣ
	public SQLiteDButil(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	//��д���๹�췽��
	public SQLiteDButil(Context context) {
		super(context,DBNAME,null,VERSION);
	}
	
	//����2 ���ڴ������ݿ��е����ݱ�   �÷�����ǰ̨����ʱ�Զ�����(CallBack)
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		//��дһ���������ݱ��SQL���
		String userTableSQL="create table users(userid integer primary key autoincrement,account varchar(30),password varchar(30))";
		//ʹ�ø÷���ִ��SQL��䣬���ڴ������ݱ�
		sqLiteDatabase.execSQL(userTableSQL);
		
		//��дһ���������ݱ��SQL������ڴ����ռǱ�
		String diarysTableSQL="create table diarys(did integer primary key autoincrement,content text,diarydate date,uid int)";
		//ִ��SexecSQL�����������ݱ�
		sqLiteDatabase.execSQL(diarysTableSQL);
		
		
	}

	//����3 ��ϵͳ��Ҫ���� (���ݿ�����)
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	/**
	* ɾ�����ݿ�
	* @param context
	* @return boolean ɾ��״̬
	*/
	public boolean deleteDatabase(Context context) {
		return context.deleteDatabase(DBNAME);
	}

}
