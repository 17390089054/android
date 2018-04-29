package com.example.progressbarapplication;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
private ProgressBar progressBar;
private Handler handler=new Handler();
boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ʵ�ֽ��������Զ��޸�
        
        
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        //handler��Ϣ���д�����
        new Thread(new Runnable() {
			@Override
			public void run() {
				while(flag){
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//ͨ��handler֪ͨ��Ϣ����
					handler.post(new Runnable() {
						@Override
						public void run() {
							//��������������ս�����������ı�
							if(progressBar.getProgress()==progressBar.getMax()){
									//�Ƴ�PrograssBar
									ViewGroup group=(ViewGroup) progressBar.getParent();
								if(group!=null){
									group.removeView(progressBar);
									//��ʾ��Ϣ
									TextView tv=new TextView(MainActivity.this);
									tv.setText("���سɹ�!!!");
									group.addView(tv);
								
									flag=false;
								}
							}
							
						progressBar.incrementProgressBy(10);
						}
					});
				}
			}
		}).start();
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}