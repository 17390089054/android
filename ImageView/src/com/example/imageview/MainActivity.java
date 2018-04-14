package com.example.imageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 *imageView 图片显示控件 是View的子类
 * 	src  图片路径
 *	background 背景路径
 *	scaleType 图片显示的位置，大小，分布 
 *imageButton 图片按钮  ImageView的子类
 *
 */
public class MainActivity extends Activity {
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//绑定点击事件
		imageView=(ImageView) findViewById(R.id.image);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "您点击了一个ImageView", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
