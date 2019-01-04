package com.example.test_notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void test(View v){
	 NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
	 
	 NotificationCompat.Builder builder=
			 new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_launcher)
			 .setContentTitle("应用通知")
			 .setContentText("上课啦上课啦上课啦上课啦!")
			 .setAutoCancel(true)
			 .setTicker("您有一条通知")
			 ;
	 Notification notification=builder.build();
	 
	 notification.defaults=notification.DEFAULT_SOUND|notification.DEFAULT_VIBRATE;
	 manager.notify(1,notification);
	
	 
	 // manager.notify(1,notification);//发送通知
	 //manager.cancel(1);//取消通知
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
