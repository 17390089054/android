package com.example.textview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

<<<<<<< HEAD
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

=======
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
>>>>>>> android开发
}
