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
	//数组中图片序列
	private int count=0;
	//图片在R文件下生成的ID数组
	private int [] images={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture);
		//初始化控件
		image=(ImageView) findViewById(R.id.image);
		pre=(Button) findViewById(R.id.pre);
		next=(Button) findViewById(R.id.next);
		//绑定点击事件
		pre.setOnClickListener(this);
		next.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.pre://上一张
				if(count==0){
					Toast.makeText(this, "已经是第一张！",Toast.LENGTH_SHORT).show();
					break;
				}else{					
					count--;
					image.setImageResource(images[count]);
					break;
				}
			case R.id.next://下一张
				if(count>=images.length-1){
					Toast.makeText(this, "已经是最后一张！",Toast.LENGTH_SHORT).show();
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
