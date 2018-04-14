package com.example.myadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
/**
 *自定义适配器 
 *  继承自BaseAdapter 需要实现四个方法   getCount()  getItem(int id)  getItemId(int position)
 *  getView(int position, View convertView, ViewGroup parent)
 *  只需将适配的item数目指定 返回指定的View即可
 *  
 *  LayoutInflater 布局映射器
 *  将xml映射成View对象
 *	通过View.findViewById来查找TextView
 *	通过from方法获取布局映射器 调用该对象的inflate方法获取view对象
 *
 *	当一个屏幕可以呈现n个item时，系统会创建n+1个item
 *	当有一个item滑出屏幕时，由于屏幕是有限的，必有一个item滑入屏幕，此时只要将滑出的item复用,并赋值给滑入的item
 *	即可最大程度上提高运行效率
 *	将滑出的item对象付给convertView对象 创建一个ViewHolder对象用于存放滑出的item的控件 将convertView与ViewHolder进行关联
 *	复用是重新取出即可
 */
public class MainActivity extends Activity {
	//自定义适配器
	private ListView lv;
	private List<String>list;
	private MyAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化ListView
        lv=(ListView) findViewById(R.id.lv);
        //初始化数据源
        list=new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
			list.add("数据项"+i);
		}
        //初始化适配器
        adapter=new MyAdapter();
        //将适配器添加到ListView
        lv.setAdapter(adapter);
	}
	 class MyAdapter extends BaseAdapter{
			@Override
			//获取adapter中的item数量
			public int getCount() {
				return list.size();
			}

			@Override
			//获取指定id对应的item
			public String getItem(int id) {
				return list.get(id);
			}

			@Override
			//获取item的ID
			public long getItemId(int position) {
				return position;
			}
			//获取每个item对应的View
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				//convertView 系统复用的视图
				ViewHolder vh;
				//系统第一次运行
				if(convertView==null){
					LayoutInflater inflater= LayoutInflater.from(MainActivity.this);
					convertView= inflater.inflate(R.layout.my_adpater, null);
					//赋值给ViewHolder
					vh=new ViewHolder();
					vh.tv=(TextView) convertView.findViewById(R.id.tv);
					//打标签
					convertView.setTag(vh);
					Log.i("MSG","创建:"+convertView);
				}else{//视图复用
					vh=(ViewHolder) convertView.getTag();
					Log.i("MSG","复用:"+convertView);
				}
				//数据源更新
				vh.tv.setText(list.get(position));
				
				
				/*	//获取布局映射器对象
				 * Log.i("MSG","getView方法被调用"+position);
				LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
				//将xml文件转换为view对象
				View view = inflater.inflate(R.layout.my_adpater, null);
				//给View对象中的空间赋值
				TextView tv = (TextView) view.findViewById(R.id.tv);
				//赋值
				tv.setText(getItem(position));*/
				return convertView;
			}
	    	
	 }
	 
	 class ViewHolder{
		 private TextView tv;
	 }

}
