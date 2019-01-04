package com.example.test_ui_thread;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button1=(Button) findViewById(R.id.button1);
		//Ϊ��ť���ö���Ч��  ����:��ʼ���� ��X Y���ƫ����
		//jquery animate
		TranslateAnimation animation=new TranslateAnimation(0, 200, 0, 0);
		//�����ظ�����
		animation.setRepeatCount(30);
		//����ʱ����
		animation.setDuration(3000);
		button1.setAnimation(animation);
		
		btn=(Button) findViewById(R.id.button2);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new DownloadTask().execute();
			}
		});
			
			//��ʽһ:view.post
			//@Override
		/*	public void onClick(final View v) {
				System.out.println("==="+Thread.currentThread().getId());
				new Thread(new Runnable() {
					int sum=0;
					@Override
					
					public void run() {
						System.out.println("===�߳̿�ʼִ��===");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sum+=10;
					
					v.post(new Runnable() {
						
						@Override
						public void run() {
							System.out.println("view.post ��ʼִ�У�����");
							System.out.println("==="+Thread.currentThread().getId());
							TextView textView=(TextView)v;
							textView.setText(""+sum);
						}
					});
					System.out.println("View.post ִ�н���������");
					}
					
				}).start();
				System.out.println("===�߳�ִ�н���!===");
			}*/	
			

		//��ʽ�� ansyt statcker
		
			
		
		
	

}

	
 private class  DownloadTask extends AsyncTask<String, Void, Integer>{

	@Override
	protected Integer doInBackground(String... params) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		int sum=10;
		
		return sum;
	}
	
	@Override
	protected void onPostExecute(Integer result) {
		btn.setText(""+result);
	
	}
 }
 }
	