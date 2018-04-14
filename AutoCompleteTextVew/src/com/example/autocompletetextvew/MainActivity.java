package com.example.autocompletetextvew;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
/**
 * AutoCompleteTextView实现热门提示
 * 
 *
 */
public class MainActivity extends Activity {
	private AutoCompleteTextView auto;
	private ArrayAdapter<String>adapter;
	private String [] tips={"a1","a2","aa1","b1","b3","b4"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化控件
		auto=(AutoCompleteTextView) findViewById(R.id.auto);
		adapter=new ArrayAdapter<String>(this,R.layout.text, tips); 
		//设置适配器到AutoCompleteTextView
		auto.setAdapter(adapter);
	}


}
