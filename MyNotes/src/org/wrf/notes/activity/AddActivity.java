package org.wrf.notes.activity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.wrf.notes.R;
import org.wrf.notes.dbUtils.DBUtil;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

public class AddActivity extends Activity implements OnClickListener{
	private Button addBtn,cancelBtn;
	private ImageView c_image;
	private VideoView c_video;
	private EditText c_text;
	private String flag;
	private SQLiteDatabase db;
	private DBUtil dbUtil;
	private File photo;
	private File video;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		//获取Flag
		flag=getIntent().getExtras().getString("flag");
		//实例化组件
		addBtn=(Button) findViewById(R.id.c_add);
		cancelBtn=(Button) findViewById(R.id.c_cacnel);
		c_image=(ImageView) findViewById(R.id.c_image);
		c_video= (VideoView) findViewById(R.id.c_video);
		c_text=(EditText) findViewById(R.id.c_text);
		//创建数据库
		dbUtil=new DBUtil(this);
		db = dbUtil.getWritableDatabase();	
		InitView();
		
		//添加监听
		addBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
	
	}
	

	
	private void InitView(){
		//Log.i("info",flag);
		//判断按钮类型
		if(flag.equals("1")){//文字
			c_image.setVisibility(View.GONE);
			c_video.setVisibility(View.GONE);
		}
		
		if(flag.equals("2")){//图片
			c_image.setVisibility(View.VISIBLE);
			c_video.setVisibility(View.GONE);
			//调用系统拍照功能
			Intent sysImg=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			//定义文件路径
			photo=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/"+getTime()+".jpg");
			//图片存放于SD中
			sysImg.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
			//开始跳转
			startActivityForResult(sysImg, 1);
		}
		
		if(flag.equals("3")){//视频
			c_image.setVisibility(View.GONE);
			c_video.setVisibility(View.VISIBLE);
			//调用系统录像功能
			Intent sysVedio=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			//定义文件路径
			video=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/"+getTime()+".mp4");
			//图片存放于SD中
			sysVedio.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(video));
			//开始跳转
			startActivityForResult(sysVedio, 2);
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.c_add:
			//添加记录
			addDB();
			finish();
			break;

		case R.id.c_cacnel:
			finish();
			break;
		}
		
		
	}
	
	
	public void addDB(){
		
		ContentValues values=new ContentValues();
		values.put(DBUtil.CONTENT, c_text.getText().toString().trim());
		values.put(DBUtil.TIME, getTime());
		values.put(DBUtil.PATH,""+photo);
		values.put(DBUtil.VIDEO,""+video);
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1){
			Bitmap bitmap=BitmapFactory.decodeFile(photo.getAbsolutePath());
			c_image.setImageBitmap(bitmap);
		}
		if(requestCode==2){
			c_video.setVideoURI(Uri.fromFile(video));
			c_video.start();
			
		}
		
		
	}
	

}
