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
 * ������
 *   ArrayAdapter SimpleAdapter  �Զ���Adapter
 * ������View	
 *  ListView �����б�
 * 	Spinner �����б�
 * 	GridView �����б�
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
		//��ʼ��Spinner	
		spinner=(Spinner) findViewById(R.id.spinner);
		//init();
		initByXml();
		//����������ӵ�Spinner��
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
		//��ʼ������Դ
		list=new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			list.add("������"+i);
		}
		//��ʼ��������
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}


}
