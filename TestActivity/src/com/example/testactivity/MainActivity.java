package com.example.testactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Activity �����ҳ�� 
 * ����ʵ����ǰ̨�û��Ľ���
 * �̳���Activity ��Ҫ��дOnCreate����
 * ������ø����onCreate����(�����û�״̬savedInstanceState)
 * SuperNotCalledException  did not call through to super.onCreate()
 * Intent ��ͼ 
 * ����ʵ�ֲ�ͬ��Activity��ת
 * ����:ԴActivity��Ŀ��Activity�������Ķ���
 * startActivity ҳ����ת����
 * 
 * ��Ҫʹ�õ�Activity������ AndroidManifest.xml�ļ�������
 * ActivityNotFoundException:
 *  Unable to find explicit activity class {com.example.testactivity/com.example.testactivity.NewActivity}; have you declared this activity in your AndroidManifest.xml?
 * 
 */
public class MainActivity extends Activity {
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ���ؼ�
		btn=(Button) findViewById(R.id.btn);
		//����תҳ��
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//������ת����ͼ
			Intent intent=new Intent(MainActivity.this,NewActivity.class);
			startActivity(intent);	
			}
		});
	}


}
