package com.example.simpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
//ʵ��ͼ�Ļ���
public class PicActivity extends Activity {
	private ListView lv;
	private List<Map<String,Object>>list;
	private Map<String,Object>map;
	private SimpleAdapter adapter;
	private int [] images={R.drawable.user,R.drawable.home,R.drawable.id,R.drawable.mail,
			R.drawable.map,R.drawable.notepad,R.drawable.photos,R.drawable.dvd};
	private String [] names={"�û�","��ҳ","���֤","�ʼ�","��ͼ","���±�","��Ƭ","DVD"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ��ListView
		lv=(ListView) findViewById(R.id.lv);
		//��ʼ������Դ
		list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<names.length;i++){
			map=new HashMap<String,Object>();
			map.put("image",images[i]);//ͼƬid
			map.put("name", names[i]);
			map.put("desc","�Ƽ�ʹ�ã�"+names[i]);
			list.add(map);
		}
		String [] from ={"image","name","desc"};
		int [] to={R.id.image,R.id.name,R.id.desc};
		//��ʼ��Adapter
		adapter=new SimpleAdapter(this, list, R.layout.pic_adapter_item, from, to);
		//��Adapterװ�ؽ���AdapterView
		lv.setAdapter(adapter);
	}
}
