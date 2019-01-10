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
		//��ȡFlag
		flag=getIntent().getExtras().getString("flag");
		//ʵ�������
		addBtn=(Button) findViewById(R.id.c_add);
		cancelBtn=(Button) findViewById(R.id.c_cacnel);
		c_image=(ImageView) findViewById(R.id.c_image);
		c_video= (VideoView) findViewById(R.id.c_video);
		c_text=(EditText) findViewById(R.id.c_text);
		//�������ݿ�
		dbUtil=new DBUtil(this);
		db = dbUtil.getWritableDatabase();	
		InitView();
		
		//��Ӽ���
		addBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
	
	}
	

	
	private void InitView(){
		//Log.i("info",flag);
		//�жϰ�ť����
		if(flag.equals("1")){//����
			c_image.setVisibility(View.GONE);
			c_video.setVisibility(View.GONE);
		}
		
		if(flag.equals("2")){//ͼƬ
			c_image.setVisibility(View.VISIBLE);
			c_video.setVisibility(View.GONE);
			//����ϵͳ���չ���
			Intent sysImg=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			//�����ļ�·��
			photo=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/"+getTime()+".jpg");
			//ͼƬ�����SD��
			sysImg.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
			//��ʼ��ת
			startActivityForResult(sysImg, 1);
		}
		
		if(flag.equals("3")){//��Ƶ
			c_image.setVisibility(View.GONE);
			c_video.setVisibility(View.VISIBLE);
			//����ϵͳ¼����
			Intent sysVedio=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			//�����ļ�·��
			video=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/"+getTime()+".mp4");
			//ͼƬ�����SD��
			sysVedio.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(video));
			//��ʼ��ת
			startActivityForResult(sysVedio, 2);
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.c_add:
			//��Ӽ�¼
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
	 * ��ȡ��ǰʱ��
	 * @return
	 */
	private String getTime() {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy��MM��dd��  HH:mm:ss");
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
