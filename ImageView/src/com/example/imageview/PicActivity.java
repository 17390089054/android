package com.example.imageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PicActivity extends Activity implements OnClickListener{
	private ImageView image;
	private Button pre;
	private Button next;
	//������ͼƬ����
	private int count=0;
	//ͼƬ��R�ļ������ɵ�ID����
	private int [] images={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture);
		//��ʼ���ؼ�
		image=(ImageView) findViewById(R.id.image);
		pre=(Button) findViewById(R.id.pre);
		next=(Button) findViewById(R.id.next);
		//�󶨵���¼�
		pre.setOnClickListener(this);
		next.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.pre://��һ��
				if(count==0){
					Toast.makeText(this, "�Ѿ��ǵ�һ�ţ�",Toast.LENGTH_SHORT).show();
					break;
				}else{					
					count--;
					image.setImageResource(images[count]);
					break;
				}
			case R.id.next://��һ��
				if(count>=images.length-1){
					Toast.makeText(this, "�Ѿ������һ�ţ�",Toast.LENGTH_SHORT).show();
					break;
				}else{
					count++;
					image.setImageResource(images[count]);
					break;
				}
			default:
				break;
		}
	}
}
