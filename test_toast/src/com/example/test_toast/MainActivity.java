package com.example.test_toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	public void test(View view){
	/*	//��Ϣ��ʾ���� ����:�����Ķ���(Activity.this||Appliction.this) ��ʾ�ı�  ��ʾʱ��
		Toast toast = Toast.makeText(this, "���ǲ���", Toast.LENGTH_SHORT);
		//������ʾ��Ϣ����ʾ  ����:��Ϣλ��  ƫ��x������ ƫ��y������
		toast.setGravity(Gravity.CENTER, 10, 0);
		toast.show();*/
	
	Toast toast = Toast.makeText(this.getApplicationContext(),"����Appication����", Toast.LENGTH_SHORT);
	toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
	toast.show();
	//Ӧ�ó��������Ķ���������
	/**
	 * Activity.this ֻ�ڵ�ǰActivityҳ����Ч  ��תҳ���ʧЧ
	 * Application.this ��ǰ������Ч ��Ӧ�ó���������Ӧ�ó���ر�
	 */
	
	
	
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}