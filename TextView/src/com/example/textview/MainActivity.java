package com.example.textview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 *TextView  �ı��ؼ� 
 *android: ������xmlns�Ĺ����ռ���
 *android:text �ı�����
 *android:textColor ������ɫ
 * android:textSize �����С
 *android:visibility �ı��ɼ���
 * 	visibile �ɼ���Ĭ�ϣ�
 *  invisible ���ɼ������ռ�
 *  gone ���ɼ�������λ��
 *android:autoLink ��������
 *web  ��ҳ������  
 * 
 *TextViewʵ�������Ч��
 *	android:singleLine="true" ������ʾ
 *	android:ellipsize="marquee" 
 *	android:focusable="true" ���þ۽�
 *	android:focusableInTouchMode="true"
 *	android:scrollHorizontally="true" ���ù���
 *	android:marqueeRepeatLimit="marquee_forever" ����ѭ������ 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.line);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
