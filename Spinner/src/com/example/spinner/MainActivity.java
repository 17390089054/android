package com.example.spinner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
/*
 * 适配器
 *   ArrayAdapter SimpleAdapter  自定义Adapter
 * 适配器View	
 *  ListView 滑动列表
 * 	Spinner 下拉列表
 * 	GridView 网格列表
 */

public class MainActivity extends Activity {
	private Spinner spinner;
	private List<String>list;
	private TextView tv;
	private ArrayAdapter<CharSequence> adapterXml;
	private ArrayAdapter<String>adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化Spinner	
		spinner=(Spinner) findViewById(R.id.spinner);
		//init();
		initByXml();
		//将适配器添加到Spinner中
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				tv=(TextView) findViewById(R.id.tv);
				tv.setText(adapterXml.getItem(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		
		
		});
		
	}
	private void initByXml() {
		adapterXml= ArrayAdapter.createFromResource(this, R.array.stringList, android.R.layout.simple_spinner_dropdown_item);		
		spinner.setAdapter(adapterXml);
	}
	private void init() {
		//初始化数据源
		list=new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			list.add("数据项"+i);
		}
		//初始化适配器
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}


}
