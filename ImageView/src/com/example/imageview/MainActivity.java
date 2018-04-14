package com.example.imageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 *imageView ͼƬ��ʾ�ؼ� ��View������
 * 	src  ͼƬ·��
 *	background ����·��
 *	scaleType ͼƬ��ʾ��λ�ã���С���ֲ� 
 *imageButton ͼƬ��ť  ImageView������
 *
 */
public class MainActivity extends Activity {
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//�󶨵���¼�
		imageView=(ImageView) findViewById(R.id.image);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "�������һ��ImageView", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
