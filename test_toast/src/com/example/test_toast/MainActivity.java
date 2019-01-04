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
	/*	//消息提示方法 参数:上下文对象(Activity.this||Appliction.this) 提示文本  提示时长
		Toast toast = Toast.makeText(this, "这是测试", Toast.LENGTH_SHORT);
		//设置提示信息的提示  参数:消息位置  偏移x轴像素 偏移y轴像素
		toast.setGravity(Gravity.CENTER, 10, 0);
		toast.show();*/
	
	Toast toast = Toast.makeText(this.getApplicationContext(),"这是Appication内容", Toast.LENGTH_SHORT);
	toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
	toast.show();
	//应用程序上下文对象作用域
	/**
	 * Activity.this 只在当前Activity页面有效  跳转页面后即失效
	 * Application.this 当前程序有效 从应用程序启动到应用程序关闭
	 */
	
	
	
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
