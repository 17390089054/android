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
 *�Զ��������� 
 *  �̳���BaseAdapter ��Ҫʵ���ĸ�����   getCount()  getItem(int id)  getItemId(int position)
 *  getView(int position, View convertView, ViewGroup parent)
 *  ֻ�轫�����item��Ŀָ�� ����ָ����View����
 *  
 *  LayoutInflater ����ӳ����
 *  ��xmlӳ���View����
 *	ͨ��View.findViewById������TextView
 *	ͨ��from������ȡ����ӳ���� ���øö����inflate������ȡview����
 *
 *	��һ����Ļ���Գ���n��itemʱ��ϵͳ�ᴴ��n+1��item
 *	����һ��item������Ļʱ��������Ļ�����޵ģ�����һ��item������Ļ����ʱֻҪ��������item����,����ֵ�������item
 *	�������̶����������Ч��
 *	��������item���󸶸�convertView���� ����һ��ViewHolder�������ڴ�Ż�����item�Ŀؼ� ��convertView��ViewHolder���й���
 *	����������ȡ������
 */
public class MainActivity extends Activity {
	//�Զ���������
	private ListView lv;
	private List<String>list;
	private MyAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ��ListView
        lv=(ListView) findViewById(R.id.lv);
        //��ʼ������Դ
        list=new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
			list.add("������"+i);
		}
        //��ʼ��������
        adapter=new MyAdapter();
        //����������ӵ�ListView
        lv.setAdapter(adapter);
	}
	 class MyAdapter extends BaseAdapter{
			@Override
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
					LayoutInflater inflater= LayoutInflater.from(MainActivity.this);
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
				
				
				/*	//��ȡ����ӳ��������
				 * Log.i("MSG","getView����������"+position);
				LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
				//��xml�ļ�ת��Ϊview����
				View view = inflater.inflate(R.layout.my_adpater, null);
				//��View�����еĿռ丳ֵ
				TextView tv = (TextView) view.findViewById(R.id.tv);
				//��ֵ
				tv.setText(getItem(position));*/
				return convertView;
			}
	    	
	 }
	 
	 class ViewHolder{
		 private TextView tv;
	 }

}
