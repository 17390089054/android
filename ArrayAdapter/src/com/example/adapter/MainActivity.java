package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
/**
 *ArrayAdpater 适用于每一项都是字符串的情况
 *条件  1.手动设置数据源  2.将数据源设置到适配器上  3.将适配器(adapt)设置到adaptView上  
 *数据源 :List<String>  String[] string-array
 * 适配器:ArrayAdapter
 * 适配器容器:ListView
 * 
 * MVC 前端显示和后端数据分离
 * M：ListView中的数据源(Model)  
 * V:ListView
 * C:item对应的Adpater
 * 
 * 
 * 
 */
public class MainActivity extends Activity {
	List<String>list;
	private ListView lv;
	private ArrayAdapter<String> adapt;
	//配置文件读取数据
	//private ArrayAdapter<CharSequence>adapt1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化数据源
		list=new ArrayList<String>();
		for(int i=0;i<20;i++){
			list.add("数据项:"+i);
		}
		//初始化适配器View
		lv=(ListView)findViewById(R.id.lv);
		//初始化适配器
		//方式一:  参数一：上下文对象  参数二：使用到的布局文件 给item使用 参数三：数据源对象
		//adapt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
		//自定义 布局文件
		adapt=new ArrayAdapter<String>(this,R.layout.item,list);
		//方式二: 从resource里读取数据源 创建适配器  参数:上下文对象  string-array 系统布局文件
		//adapt1=ArrayAdapter.createFromResource(this, R.array.string_list, android.R.layout.simple_list_item_1);
		//将适配器添加到适配器View
		lv.setAdapter(adapt);
		
		//为ListView每个item配置点击事件
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			/*
			* parent: 设置的ListView
			* view:item对应的View
			* position:ListView对应的索引
			* id:ListView对应的id
			*/
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				Toast.makeText(MainActivity.this,"点击了position:"+position+" id:"+id, Toast.LENGTH_SHORT).show();
			}
		});
		
	//为ListVie每个Item添加长按事件
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			/* (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemLongClickListener#onItemLongClick(android.widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position , long id) {
				//删除索引
				list.remove(position);
				
				//同步前后台内容  更新ListView的内容
				/*adapt=new ArrayAdapter<String>(MainActivity.this,R.layout.item,list);
				lv.setAdapter(adapt);*/
				adapt.notifyDataSetChanged();
				
				return true;
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
