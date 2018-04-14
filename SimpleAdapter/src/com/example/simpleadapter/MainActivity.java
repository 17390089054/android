package com.example.simpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * SimpleAdapter 可以显示多个键值对组成的 可包含多项内容
 * from 和to的含义
 * list数据源的内容
 * 对比ArrayAdapter
 * 对应的Item的显示控件更多
 * 稍复杂
 * 
 *
 */
public class MainActivity extends Activity {
	private ListView lv;
	private SimpleAdapter adapt;
	private List<Map<String,Object>>list;
	private Map<String,Object>map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化ListView	
		lv=(ListView) findViewById(R.id.lv);
		//初始化数据源
		list=new ArrayList<Map<String,Object>>();
		//初始化数据源
		for(int i=0;i<20;i++){
			map=new HashMap<String,Object>();
			map.put("data", "数据"+i);
			map.put("intro", "介绍"+i);
			list.add(map);
		}
		
		String [] from ={"data","intro"};
		int [] to={R.id.data,R.id.intro};
		//初始化适配器
		//参数  1.应用程序上下文   2.数据源 List<Map<String,Object>>  3.布局文件  4.数据源中map中的key组成的数组     5.控件中对应id组成的整型数组 
		adapt=new SimpleAdapter(this, list, R.layout.list_view_item, from, to);
		//将适配器添加到适配器View中
		lv.setAdapter(adapt);
		
	}

}
