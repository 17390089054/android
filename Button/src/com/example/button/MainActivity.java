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
	 * 方式一:匿名内部类实现onclick方法
	 * protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//找到添加的按钮对象
		Button btn=(Button)findViewById(R.id.btn);
		//添加监听事件(内部类)
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("TAG", "点击了这个按钮(方式一)!");
			}
		});
		
		
	}*/

	
	/*
	 * 方式二:xml 中按钮绑定onclick事件 在主方法中实现该方法
	 * protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void help(View v){
		Log.i("TAG", "点击了这个按钮(方式二)!");
	}*/
	
	
	/*
	 * 方式三:(主方法实现OnClickListener接口 调用本类实现点击事件)
	 *  protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button)findViewById(R.id.btn);
		button.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Log.i("TAG", "点击了这个按钮(方式三)!");
	}
*/
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//按钮一
		Button btn=(Button)findViewById(R.id.btn);
		btn.setOnClickListener(new Onclick());
		//按钮二
		Button btn2=(Button)findViewById(R.id.btn2);
		btn2.setOnClickListener(new Onclick());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 *方式四:(定义内部类实现OnClickListener接口) 
	 */
	class Onclick implements  OnClickListener{
		@Override
		public void onClick(View v) {
			/**
			 * 区分不同的按钮
			 */
			switch(v.getId()){
			case R.id.btn:				
				Log.i("TAG", "点击了按钮一");
				break;
			case R.id.btn2:
				Log.i("TAG", "点击了按钮二");
				break;
			default:
				break;
			}
			
		}
		
	}

	
}
