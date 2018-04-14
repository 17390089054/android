package com.wrf.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import com.example.myadapter.R;
import com.example.myadapter.R.layout;
import com.example.myadapter.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class AdapterActivity extends Activity {
	//自定义适配器
	private ListView lv;
	private List<String>list;
	private MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adapter);
		//初始化ListView
        lv=(ListView) findViewById(R.id.lv2);
        //初始化数据源
        list=new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
			list.add("数据项"+i);
		}
        //初始化适配器
        adapter=new MyAdapter(list, this);
        //将适配器添加到ListView
        lv.setAdapter(adapter);
		
		
		
	}

	

}
