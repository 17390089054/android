package com.example.testactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Activity 活动管理页面 
 * 用于实现与前台用户的交互
 * 继承自Activity 需要重写OnCreate方法
 * 必须调用父类的onCreate方法(保存用户状态savedInstanceState)
 * SuperNotCalledException  did not call through to super.onCreate()
 * Intent 意图 
 * 用于实现不同的Activity跳转
 * 参数:源Activity和目标Activity的上下文对象
 * startActivity 页面跳转方法
 * 
 * 需要使用的Activity必须在 AndroidManifest.xml文件中声明
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
		//初始化控件
		btn=(Button) findViewById(R.id.btn);
		//绑定跳转页面
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//定义跳转的意图
			Intent intent=new Intent(MainActivity.this,NewActivity.class);
			startActivity(intent);	
			}
		});
	}


}
