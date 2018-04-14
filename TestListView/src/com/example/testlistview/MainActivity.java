package com.example.testlistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView lv;
	private List<String>list;
	private ArrayAdapter<String>adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化ListVire
		lv=(ListView) findViewById(R.id.lv);
		//初始化数据源
		list=new ArrayList<String>();
		for(int i=0;i<20;i++){
			list.add("数据项"+i);
		}
		//初始化adapter
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
		//初始化图片控件
		View header = LayoutInflater.from(this).inflate(R.layout.list_header, null);
		//添加header
		lv.addHeaderView(header);
		//添加Footer
		//footer();
		View footer = LayoutInflater.from(this).inflate(R.layout.list_footer, null);
		lv.addFooterView(footer, null, true);
		//将适配器设置到listView上
		lv.setAdapter(adapter);
	}
	private void footer() {
		//动态添加按钮
		Button footer=new Button(this);
		//设置按钮内容
		footer.setText("加载更多");
		lv.addFooterView(footer);
	}
}
