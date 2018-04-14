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
 * SimpleAdapter ������ʾ�����ֵ����ɵ� �ɰ�����������
 * from ��to�ĺ���
 * list����Դ������
 * �Ա�ArrayAdapter
 * ��Ӧ��Item����ʾ�ؼ�����
 * �Ը���
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
		//��ʼ��ListView	
		lv=(ListView) findViewById(R.id.lv);
		//��ʼ������Դ
		list=new ArrayList<Map<String,Object>>();
		//��ʼ������Դ
		for(int i=0;i<20;i++){
			map=new HashMap<String,Object>();
			map.put("data", "����"+i);
			map.put("intro", "����"+i);
			list.add(map);
		}
		
		String [] from ={"data","intro"};
		int [] to={R.id.data,R.id.intro};
		//��ʼ��������
		//����  1.Ӧ�ó���������   2.����Դ List<Map<String,Object>>  3.�����ļ�  4.����Դ��map�е�key��ɵ�����     5.�ؼ��ж�Ӧid��ɵ��������� 
		adapt=new SimpleAdapter(this, list, R.layout.list_view_item, from, to);
		//����������ӵ�������View��
		lv.setAdapter(adapt);
		
	}

}
