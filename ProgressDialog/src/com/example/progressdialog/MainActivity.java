package com.example.progressdialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv;
	/*private Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
			}
		
	};*/
	private Handler handler=new Handler(new Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			ProgressDialog dialog=(ProgressDialog) msg.obj;
			dialog.setProgress(msg.arg1);
			//dialog.incrementProgressBy(1); dialog.getProgress()
			if(msg.arg1==dialog.getMax()){
				dialog.cancel();
				tv.setText("���سɹ�!!!");
			}
			
			
			
			//ȡ���Ի���
			/*dialog.cancel();
			if(msg.arg1==1){
				tv.setText("���سɹ�!!!");
			}else{
				tv.setText("����ʧ��!!!");
			}*/
			return false;
		}
	});
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView) findViewById(R.id.tv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //���ȶԻ���
    public void test(View v){
    	final ProgressDialog pg=new ProgressDialog(MainActivity.this);
    	pg.setTitle("��ͨ�Ի���");
    	pg.setMessage("��������...");
    	pg.show();
    	//ִ�к�ʱ������Ҫ������߳�
    	new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//����UI��� ȡ���Ի��� ��ʾ��ʾ�ı�
				Message msg=new Message();
				msg.obj=pg;
				msg.arg1=2;//1 �������سɹ�! 2 ��������ʧ��!
				//Bundle bundle = msg.getData();//������map������ ��ֵ����ΪString
				handler.sendMessage(msg);
			}
		}).start();
    	
    	
    }
    
    public void test02(View v){
    	final ProgressDialog pg=new ProgressDialog(MainActivity.this);
    	pg.setTitle("���ضԻ���");
    	pg.setMessage("��������>>>");
    	pg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    	pg.setMax(100);
    	//pg.setIndeterminate(true);
    	pg.show();
    	new Thread(new Runnable() {
			@Override
			public void run() {
				int i=0;
				while(i<=pg.getMax()){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Message msg=new Message();
					msg.arg1=i;
					msg.obj=pg;
					handler.sendMessage(msg);
					i++;
				}
			}
		}).start();
    	
    	
    	
    }
    
}