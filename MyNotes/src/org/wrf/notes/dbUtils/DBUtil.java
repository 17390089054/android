package org.wrf.notes.dbUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 数据库工具类
 * @author knight
 *
 */
public class DBUtil extends SQLiteOpenHelper {
	public static final String TABLE_NAME="note";
	public static final String ID="_id";
	public static final String CONTENT="content";
	public static final String PATH="path";
	public static final String VIDEO="video";
	public static final String TIME="time";
	

	public DBUtil(Context context) {
		super(context, "notes", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "+TABLE_NAME+"("+
				ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
				CONTENT+" TEXT NOT NULL,"+
				PATH+" TEXT ,"+
				VIDEO+" TEXT ,"+ 
				TIME+" TEXT NOT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
