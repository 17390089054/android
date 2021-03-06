package com.example.alertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	public String[] strs=new String []{
		"北京",	
		"上海",
		"重庆",
		"天津"
	};
	
	/*
	 * AlertDialog的使用方法
	 * 1.使用AlertDialog.builder()方法创建builder对象
	 * 2.builder设置标题，内容和按钮（可以设置多个按钮）
	 * 3.builder.create创建AlertDialog对象
	 *   调用AlertDialog的show方法进行展示
	 */
	public void test(View v){
		AlertDialog.Builder builder=new Builder(MainActivity.this); 
		//设置标题
		builder.setTitle("提示");
		builder.setMessage("这是提示信息!");
		builder.setPositiveButton("满意", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "感谢有你!", Toast.LENGTH_SHORT).show();
			}
		});
		
		builder.setNeutralButton("一般", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "感谢您的评价!", Toast.LENGTH_SHORT).show();
			}
		});
		
		builder.setNegativeButton("不满意", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "请留下您的建议!", Toast.LENGTH_SHORT).show();
			}
		});
		
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
	
	//用法二
	public void test02(View v){
		AlertDialog.Builder builder=new Builder(MainActivity.this); 
		//设置标题
		builder.setTitle("提示信息");
		builder.setItems(strs, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, strs[which], Toast.LENGTH_SHORT).show();
			}
		});
		
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
	
	//用法三
	public void test03(View v){
		AlertDialog.Builder builder=new Builder(MainActivity.this); 
		//设置标题
		builder.setTitle("提示信息");
		builder.setSingleChoiceItems(strs, 0,  new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, strs[which], Toast.LENGTH_SHORT).show();
			}
		});
		
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
	
	//用法四
	public void test04(View v){
		AlertDialog.Builder builder=new Builder(MainActivity.this); 
		//设置标题
		builder.setTitle("提示信息");
		builder.setMultiChoiceItems(strs, new boolean[]{true,false,true,false}, new OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				Toast.makeText(MainActivity.this, strs[which], Toast.LENGTH_SHORT).show();
			}
		});
		
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
	
	//自定义dialog
	public void test05(View v){
		AlertDialog.Builder builder=new Builder(this);
		builder.setTitle("登录窗口");
		View view = this.getLayoutInflater().inflate(R.layout.login, null);
		builder.setView(view);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

}
