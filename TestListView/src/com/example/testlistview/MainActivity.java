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
		//��ʼ��ListVire
		lv=(ListView) findViewById(R.id.lv);
		//��ʼ������Դ
		list=new ArrayList<String>();
		for(int i=0;i<20;i++){
			list.add("������"+i);
		}
		//��ʼ��adapter
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
		//��ʼ��ͼƬ�ؼ�
		View header = LayoutInflater.from(this).inflate(R.layout.list_header, null);
		//���header
		lv.addHeaderView(header);
		//���Footer
		//footer();
		View footer = LayoutInflater.from(this).inflate(R.layout.list_footer, null);
		lv.addFooterView(footer, null, true);
		//�����������õ�listView��
		lv.setAdapter(adapter);
	}
	private void footer() {
		//��̬��Ӱ�ť
		Button footer=new Button(this);
		//���ð�ť����
		footer.setText("���ظ���");
		lv.addFooterView(footer);
	}
}
