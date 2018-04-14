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

	//��ȡadapter�е�item����
	public int getCount() {
		return list.size();
	}

	@Override
	//��ȡָ��id��Ӧ��item
	public String getItem(int id) {
		return list.get(id);
	}

	@Override
	//��ȡitem��ID
	public long getItemId(int position) {
		return position;
	}
	//��ȡÿ��item��Ӧ��View
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//convertView ϵͳ���õ���ͼ
		ViewHolder vh;
		//ϵͳ��һ������
		if(convertView==null){
			LayoutInflater inflater= LayoutInflater.from(context);
			convertView= inflater.inflate(R.layout.my_adpater, null);
			//��ֵ��ViewHolder
			vh=new ViewHolder();
			vh.tv=(TextView) convertView.findViewById(R.id.tv);
			//���ǩ
			convertView.setTag(vh);
			Log.i("MSG","����:"+convertView);
		}else{//��ͼ����
			vh=(ViewHolder) convertView.getTag();
			Log.i("MSG","����:"+convertView);
		}
		//����Դ����
		vh.tv.setText(list.get(position));
		return convertView;
	}
	
	class ViewHolder{
		 private TextView tv;
	 }

}
