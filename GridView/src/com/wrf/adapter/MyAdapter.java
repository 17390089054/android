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
	//�������Ĺ��췽��
	public MyAdapter(int[] images, String[] names, Context context) {
		super();
		this.images = images;
		this.names = names;
		this.context = context;
	}
	//����item��Ŀ
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
 *�Զ��������� 
 *����:ͼƬ ͼƬ�� �����Ķ���
 */
public class MyAdapter extends BaseAdapter {
	private int [] images;
	private String [] names;
	private Context context;
	
	//��ȡItem��Ŀ
>>>>>>> android开发
	@Override
	public int getCount() {
		return names.length;
	}
<<<<<<< HEAD
	//��ȡָ��item
	@Override
	public String getItem(int arg0) {
		return names[arg0];
=======
	//��ȡitem����
	@Override
	public String  getItem(int position) {
		return names[position];
>>>>>>> android开发
	}
	//��ȡItemId
	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
<<<<<<< HEAD
	//����item��Ӧ��View   ����ListView�Ż�
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView hv = null;
		if(convertView==null){
			//��xml�ļ�ӳ���View����  LayoutInflater����ӳ����
			convertView=LayoutInflater.from(context).inflate(R.layout.grid_adapter_item, null);
			hv=new HolderView();
			hv.tv=(TextView) convertView.findViewById(R.id.name);
			hv.image=(ImageView) convertView.findViewById(R.id.image);
			//���ǩ
			convertView.setTag(hv);
		}else{
			//��ͼ����
			hv=(HolderView) convertView.getTag();
		}
		//��ֵ
		hv.image.setImageResource(images[position]);
		hv.tv.setText(names[position]);
		
		return convertView;
	}
	
	class HolderView {
		private TextView tv;
		private ImageView image;
		
=======
	//��ȡitem����ͼView
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//�������ļ�ת��ΪView���� ����ӳ����LayOutFa
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
>>>>>>> android开发
	}

}
