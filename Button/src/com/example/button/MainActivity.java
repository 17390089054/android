package com.example.button;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity{

	@Override
	/*
	 * ��ʽһ:�����ڲ���ʵ��onclick����
	 * protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//�ҵ���ӵİ�ť����
		Button btn=(Button)findViewById(R.id.btn);
		//��Ӽ����¼�(�ڲ���)
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("TAG", "����������ť(��ʽһ)!");
			}
		});
		
		
	}*/

	
	/*
	 * ��ʽ��:xml �а�ť��onclick�¼� ����������ʵ�ָ÷���
	 * protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void help(View v){
		Log.i("TAG", "����������ť(��ʽ��)!");
	}*/
	
	
	/*
	 * ��ʽ��:(������ʵ��OnClickListener�ӿ� ���ñ���ʵ�ֵ���¼�)
	 *  protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button)findViewById(R.id.btn);
		button.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Log.i("TAG", "����������ť(��ʽ��)!");
	}
*/
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ťһ
		Button btn=(Button)findViewById(R.id.btn);
		btn.setOnClickListener(new Onclick());
		//��ť��
		Button btn2=(Button)findViewById(R.id.btn2);
		btn2.setOnClickListener(new Onclick());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 *��ʽ��:(�����ڲ���ʵ��OnClickListener�ӿ�) 
	 */
	class Onclick implements  OnClickListener{
		@Override
		public void onClick(View v) {
			/**
			 * ���ֲ�ͬ�İ�ť
			 */
			switch(v.getId()){
			case R.id.btn:				
				Log.i("TAG", "����˰�ťһ");
				break;
			case R.id.btn2:
				Log.i("TAG", "����˰�ť��");
				break;
			default:
				break;
			}
			
		}
		
	}

	
}
