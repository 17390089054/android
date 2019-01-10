package org.wrf.notes.activity;

import java.io.ObjectOutputStream.PutField;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.wrf.notes.R;
import org.wrf.notes.dbUtils.DBUtil;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {
	private DBUtil dbUtil;
	private SQLiteDatabase db;
	private Button textBtn,imageBtn,vedioBtn;
	private ListView lv;
	private MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InitView();
		
		
	}
	
	private void InitView(){
		//实例化组件
		textBtn=(Button) findViewById(R.id.text);
		imageBtn=(Button) findViewById(R.id.image);
		vedioBtn=(Button) findViewById(R.id.vedio);
		//添加监听
		textBtn.setOnClickListener(this);
		imageBtn.setOnClickListener(this);
		vedioBtn.setOnClickListener(this);
		
		lv=(ListView) findViewById(R.id.lv);
		
		dbUtil=new DBUtil(this);
		db=dbUtil.getReadableDatabase();
		
	}
	
	
	public void addDB(){
		//创建数据库
		dbUtil=new DBUtil(this);
		db = dbUtil.getWritableDatabase();
		//添加记录
		addDB();
		
		
		ContentValues values=new ContentValues();
		values.put(DBUtil.CONTENT, "我是你爸爸");
		values.put(DBUtil.TIME, getTime());
		db.insert(DBUtil.TABLE_NAME, null, values);
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	private String getTime() {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		return sf.format(new Date());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void selectDB(){
		Cursor cursor = db.query(DBUtil.TABLE_NAME, null, null, null, null, null, null);
		adapter=new MyAdapter(this, cursor);
		lv.setAdapter(adapter);
	}
	
	
	
	
	@Override
	public void onClick(View v) {
		Intent i=new Intent(this,AddActivity.class);
		switch (v.getId()) {
		case R.id.text:
			i.putExtra("flag", "1");
			break;

		case R.id.image:
			i.putExtra("flag", "2");
			break;
			
		case R.id.vedio:
			i.putExtra("flag", "3");
			break;
		}
		startActivity(i);
	}

	@Override
	protected void onResume() {
		super.onResume();
		selectDB();
	}




}
