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
 *ArrayAdpater ������ÿһ����ַ��������
 *����  1.�ֶ���������Դ  2.������Դ���õ���������  3.��������(adapt)���õ�adaptView��  
 *����Դ :List<String>  String[] string-array
 * ������:ArrayAdapter
 * ����������:ListView
 * 
 * MVC ǰ����ʾ�ͺ�����ݷ���
 * M��ListView�е�����Դ(Model)  
 * V:ListView
 * C:item��Ӧ��Adpater
 * 
 * 
 * 
 */
public class MainActivity extends Activity {
	List<String>list;
	private ListView lv;
	private ArrayAdapter<String> adapt;
	//�����ļ���ȡ����
	//private ArrayAdapter<CharSequence>adapt1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ������Դ
		list=new ArrayList<String>();
		for(int i=0;i<20;i++){
			list.add("������:"+i);
		}
		//��ʼ��������View
		lv=(ListView)findViewById(R.id.lv);
		//��ʼ��������
		//��ʽһ:  ����һ�������Ķ���  ��������ʹ�õ��Ĳ����ļ� ��itemʹ�� ������������Դ����
		//adapt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
		//�Զ��� �����ļ�
		adapt=new ArrayAdapter<String>(this,R.layout.item,list);
		//��ʽ��: ��resource���ȡ����Դ ����������  ����:�����Ķ���  string-array ϵͳ�����ļ�
		//adapt1=ArrayAdapter.createFromResource(this, R.array.string_list, android.R.layout.simple_list_item_1);
		//����������ӵ�������View
		lv.setAdapter(adapt);
		
		//ΪListViewÿ��item���õ���¼�
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			/*
			* parent: ���õ�ListView
			* view:item��Ӧ��View
			* position:ListView��Ӧ������
			* id:ListView��Ӧ��id
			*/
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				Toast.makeText(MainActivity.this,"�����position:"+position+" id:"+id, Toast.LENGTH_SHORT).show();
			}
		});
		
	//ΪListVieÿ��Item��ӳ����¼�
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			/* (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemLongClickListener#onItemLongClick(android.widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position , long id) {
				//ɾ������
				list.remove(position);
				
				//ͬ��ǰ��̨����  ����ListView������
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
