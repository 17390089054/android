package com.example.simpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
//实现图文混排
public class PicActivity extends Activity {
	private ListView lv;
	private List<Map<String,Object>>list;
	private Map<String,Object>map;
	private SimpleAdapter adapter;
	private int [] images={R.drawable.user,R.drawable.home,R.drawable.id,R.drawable.mail,
			R.drawable.map,R.drawable.notepad,R.drawable.photos,R.drawable.dvd};
	private String [] names={"用户","主页","身份证","邮件","地图","记事本","照片","DVD"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化ListView
		lv=(ListView) findViewById(R.id.lv);
		//初始化数据源
		list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<names.length;i++){
			map=new HashMap<String,Object>();
			map.put("image",images[i]);//图片id
			map.put("name", names[i]);
			map.put("desc","推荐使用："+names[i]);
			list.add(map);
		}
		String [] from ={"image","name","desc"};
		int [] to={R.id.image,R.id.name,R.id.desc};
		//初始化Adapter
		adapter=new SimpleAdapter(this, list, R.layout.pic_adapter_item, from, to);
		//将Adapter装载进入AdapterView
		lv.setAdapter(adapter);
	}
}
