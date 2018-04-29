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
	//带参数的构造方法
	public MyAdapter(int[] images, String[] names, Context context) {
		super();
		this.images = images;
		this.names = names;
		this.context = context;
	}
	//返回item数目
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
 *自定义适配器 
 *参数:图片 图片名 上下文对象
 */
public class MyAdapter extends BaseAdapter {
	private int [] images;
	private String [] names;
	private Context context;
	
	//获取Item数目
>>>>>>> android寮�鍙�
	@Override
	public int getCount() {
		return names.length;
	}
<<<<<<< HEAD
	//获取指定item
	@Override
	public String getItem(int arg0) {
		return names[arg0];
=======
	//获取item对象
	@Override
	public String  getItem(int position) {
		return names[position];
>>>>>>> android寮�鍙�
	}
	//获取ItemId
	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
<<<<<<< HEAD
	//返回item对应的View   采用ListView优化
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView hv = null;
		if(convertView==null){
			//将xml文件映射成View对象  LayoutInflater布局映射器
			convertView=LayoutInflater.from(context).inflate(R.layout.grid_adapter_item, null);
			hv=new HolderView();
			hv.tv=(TextView) convertView.findViewById(R.id.name);
			hv.image=(ImageView) convertView.findViewById(R.id.image);
			//打标签
			convertView.setTag(hv);
		}else{
			//视图复用
			hv=(HolderView) convertView.getTag();
		}
		//赋值
		hv.image.setImageResource(images[position]);
		hv.tv.setText(names[position]);
		
		return convertView;
	}
	
	class HolderView {
		private TextView tv;
		private ImageView image;
		
=======
	//获取item的视图View
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//将布局文件转换为View对象 布局映射器LayOutFa
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
>>>>>>> android寮�鍙�
	}

}
