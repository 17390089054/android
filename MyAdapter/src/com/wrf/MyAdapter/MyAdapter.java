package com.wrf.MyAdapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myadapter.R;

public class MyAdapter extends BaseAdapter{
	private List<String>list;
	private Context context;
	
	public MyAdapter(List<String> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

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
			LayoutInflater inflater= LayoutInflater.from(context);
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
		return convertView;
	}
	
	class ViewHolder{
		 private TextView tv;
	 }

}
