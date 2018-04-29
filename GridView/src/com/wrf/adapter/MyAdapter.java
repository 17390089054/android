package com.wrf.adapter;

<<<<<<< HEAD
import com.example.gridview.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	private int []images;
	private String []names;
	private Context context;
	//´ø²ÎÊıµÄ¹¹Ôì·½·¨
	public MyAdapter(int[] images, String[] names, Context context) {
		super();
		this.images = images;
		this.names = names;
		this.context = context;
	}
	//·µ»ØitemÊıÄ¿
=======
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
/**
 *×Ô¶¨ÒåÊÊÅäÆ÷ 
 *²ÎÊı:Í¼Æ¬ Í¼Æ¬Ãû ÉÏÏÂÎÄ¶ÔÏó
 */
public class MyAdapter extends BaseAdapter {
	private int [] images;
	private String [] names;
	private Context context;
	
	//»ñÈ¡ItemÊıÄ¿
>>>>>>> androidå¼€å‘
	@Override
	public int getCount() {
		return names.length;
	}
<<<<<<< HEAD
	//»ñÈ¡Ö¸¶¨item
	@Override
	public String getItem(int arg0) {
		return names[arg0];
=======
	//»ñÈ¡item¶ÔÏó
	@Override
	public String  getItem(int position) {
		return names[position];
>>>>>>> androidå¼€å‘
	}
	//»ñÈ¡ItemId
	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
<<<<<<< HEAD
	//·µ»Øitem¶ÔÓ¦µÄView   ²ÉÓÃListViewÓÅ»¯
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView hv = null;
		if(convertView==null){
			//½«xmlÎÄ¼şÓ³Éä³ÉView¶ÔÏó  LayoutInflater²¼¾ÖÓ³ÉäÆ÷
			convertView=LayoutInflater.from(context).inflate(R.layout.grid_adapter_item, null);
			hv=new HolderView();
			hv.tv=(TextView) convertView.findViewById(R.id.name);
			hv.image=(ImageView) convertView.findViewById(R.id.image);
			//´ò±êÇ©
			convertView.setTag(hv);
		}else{
			//ÊÓÍ¼¸´ÓÃ
			hv=(HolderView) convertView.getTag();
		}
		//¸³Öµ
		hv.image.setImageResource(images[position]);
		hv.tv.setText(names[position]);
		
		return convertView;
	}
	
	class HolderView {
		private TextView tv;
		private ImageView image;
		
=======
	//»ñÈ¡itemµÄÊÓÍ¼View
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//½«²¼¾ÖÎÄ¼ş×ª»»ÎªView¶ÔÏó ²¼¾ÖÓ³ÉäÆ÷LayOutFa
		if(convertView==null){
			//LayoutInflater.from(context).inflate(R.layout., root)
		
		}
		
		return null;
	}

	public MyAdapter(int[] images, String[] names, Context context) {
		super();
		this.images = images;
		this.names = names;
		this.context = context;
>>>>>>> androidå¼€å‘
	}

}
