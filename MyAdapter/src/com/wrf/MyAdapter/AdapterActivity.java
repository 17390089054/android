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
	//�Զ���������
	private ListView lv;
	private List<String>list;
	private MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adapter);
		//��ʼ��ListView
        lv=(ListView) findViewById(R.id.lv2);
        //��ʼ������Դ
        list=new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
			list.add("������"+i);
		}
        //��ʼ��������
        adapter=new MyAdapter(list, this);
        //����������ӵ�ListView
        lv.setAdapter(adapter);
		
		
		
	}

	

}
