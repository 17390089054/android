package org.wrf.mydiary.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.wrf.mydiary.dao.DiaryDao;
import org.wrf.mydiary.dbutil.SQLiteDButil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DiaryActivity extends Activity {
	//����ListView����
	private ListView listView; 
	//����SimpleAdapter����
	private SimpleAdapter simpleAdapter;
	//���弯�϶���
	private List<Map<String,?>>data;
	//�����û�ID
	private int userid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_diary);
		//ʵ����ListView����
		listView=(ListView) findViewById(R.id.list_view);
		//��ȡ�û�ID
		SharedPreferences sharedPreferences = getSharedPreferences("publicData", MODE_PRIVATE);
	    userid=sharedPreferences.getInt("uid", 1);
		//��ȡ����
		data=this.getData();
		//ʵ����Adapter
		simpleAdapter=new SimpleAdapter(DiaryActivity.this,data,R.layout.diary_item,
				new String[]{"date","time","content"}, new int[]{R.id.date,R.id.time,R.id.content});
		//ΪListView����������
		listView.setAdapter(simpleAdapter);
		//ΪListViewע�������Ĳ˵�
		registerForContextMenu(listView);
		
		//����ListView��item�����¼�
		//listView.setOnItemLongClickListener(new ListViewListener());
		
	}
	
	//����һ������������ListView�е�ÿһ��Item
	@SuppressWarnings("unused")
	private class ListViewListener implements AdapterView.OnItemLongClickListener{

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View v,
				int position, long id) {
				
			showCustomerToast(android.R.drawable.btn_star_big_on, "����˵�"+position+"��");
			return false;
		}

		
	}
	
	
	
	
	//�˵�����ѡ�� ʵ�ֵ���ѡ��˵�
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info=(AdapterContextMenuInfo)item.getMenuInfo();
		//Log.v("msg",String.valueOf(info.position)+" "+data.get(info.position).get("id"));
		//��ȡѡ��item��data�����е�λ��Position
		final int position=info.position;
		//��ȡѡ��Item���ݿ��¼ID
		final Integer did=(Integer) data.get(position).get("id");
		//�ж�ѡ�еĲ˵�ѡ���Ǳ༭����ɾ��
		switch (item.getItemId()) {
		case R.id.edit:
			//����Ǳ༭���� ��ô����ǰItem�����ݿ�id����Intent ����ת���༭ҳ��
			Intent intent=new Intent();
			intent.putExtra("did", did);
			intent.setClass(DiaryActivity.this, EditActivity.class);
			startActivity(intent);
			//showCustomerToast(android.R.drawable.btn_radio, "�༭����");
			break;
		case R.id.delete:
			//�����Ի�����ʾ�Ƿ�ɾ��
			AlertDialog.Builder builder=new Builder(DiaryActivity.this);
			builder.setMessage("ȷ��ɾ��ô��");
			builder.setTitle("��ʾ");
			//���setPositiveButton()����
			builder.setPositiveButton("ȷ��", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//ɾ��ѡ���ռ���Ϣ
					DiaryDao.deleteDiary(did,DiaryActivity.this);
					if(data.remove(position)!=null){
						Log.i("msg","success");
						showCustomerToast(android.R.drawable.btn_radio, "ɾ���ɹ���");
					}else{
						Log.i("msg","failed");
						showCustomerToast(android.R.drawable.btn_radio, "ɾ��ʧ�ܣ�");
					}
					//�������ݼ�
					simpleAdapter.notifyDataSetChanged();
					
				}
			});
			//���setNegativeButton()
			builder.setNegativeButton("ȡ��", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			});
			builder.create().show();
			
			
			break;

		default:
			break;
		}
		
		return super.onContextItemSelected(item);
	}


	//��д onCreateContextMenu���������Ĳ˵�
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		//��ȡAdpaterContextMenuInfo ��Ϊ�����˵���ͨ������AdapterView�����õ���
		//AdapaterContextMenuInfo��ʵ����menuInfo�ӿڣ��ṩ��targetView,Position,Id 
		//AdapterContextMenuInfo info=(AdapterContextMenuInfo)menuInfo;
		
		super.onCreateContextMenu(menu, v, menuInfo);
		//����xml�������Ĳ˵�
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.main, menu);
	}

	
	/**
	 * ��ȡ���� ������ݼ�
	 * @return List<Map<String,?>> ���ݼ�
	 */
	public List<Map<String,?>>  getData(){
		//����һ�����϶���������
		List<Map<String,?>>lstData=new ArrayList<Map<String,?>>();
		//�������ݿ����Ӷ���
		SQLiteDButil dButil=new SQLiteDButil(DiaryActivity.this);
		//ֻ����ʽ��ȡ���ݿ����
		SQLiteDatabase db = dButil.getReadableDatabase();
		//����SQL��ѯ���
		String strSQL="select * from diarys where uid=? order by diarydate desc";
		//ִ��SQL����ȡ�α����Cursor
		Cursor cursor = db.rawQuery(strSQL, new String[]{String.valueOf(userid)});
		//�ж��Ƿ������� û���������ʾ
		if(cursor.getCount()==0){
			showCustomerToast(0, "Ŀǰ�����ռ�");
		}
		
		
		//����cursor�����MoveToNext��������
		while(cursor.moveToNext()){
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("id", cursor.getInt(0));
			map.put("content", cursor.getString(1));
			//SQLite���ݿ�Date�������ʹ���
			Date date=null;//���ڽ������ݿ������������ֶ�
			String diaryDate=null;//���ڽ������ݿ�������ʱ�����ͽ��в�ֵ���������
			String diaryTime=null;//���ڽ������ݿ�������ʱ�����ͽ��в�ֵ�ʱ������
			
			try {
				//ʹ��SimpleDateFormat�������ݿ��е���������
				date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA).parse(cursor.getString(cursor.getColumnIndex("diarydate")));
				diaryDate=new SimpleDateFormat("MM��dd��",Locale.CHINA).format(date);
				diaryTime=new SimpleDateFormat("HH:mm",Locale.CHINA).format(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			map.put("date", diaryDate);
			map.put("time", diaryTime);
			map.put("uid", cursor.getString(cursor.getColumnIndex("uid")));
			lstData.add(map);
		}
		//�ر��α����
		cursor.close();
		//�ر����Ӷ���
		db.close();

		return lstData;
		
	}
	

	//ʵ��ActionBar�е�ѡ�ť ��дOnCreateOPtionMenu����
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//����1 ����һ��Inflaterӳ����
		MenuInflater menuInflater = getMenuInflater();
		//����2 ����inflate�����󶨲����ļ�
		menuInflater.inflate(R.menu.actionbar_menu_add, menu);
		menuInflater.inflate(R.menu.actionbar_menu_exit, menu);
		return true;
	}
	//ʵ��ActionBar�еĵ���¼� ��дonOptionsItemSelected����
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.ab_add:
			//��ת����ӽ���
			Intent intent=new Intent();
			intent.setClass(DiaryActivity.this, AddActivity.class);
			startActivity(intent);
			break;
		case R.id.ab_exit:
			//��ת����¼ҳ��
			Intent i =new Intent();
			i.setClass(DiaryActivity.this, LoginActivity.class);
			startActivity(i);
			finish();
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	  //�Զ���Toast��Ϣ��ʾ
    public void showCustomerToast(final int icon,final String message){
    	//����1�� ����һ��layoutInflater�ӿڶ���
    	LayoutInflater layoutInflater = getLayoutInflater();
    	//����2�� ʹ��layoutInflater��inflate�������Զ���Toast���� ��ָ��toast_layout_root���ڵ�
    	View layout = layoutInflater.inflate(R.layout.toast_customer,(ViewGroup) findViewById( R.id.toast_layout_root));
    	
    	ImageView toastIcon=(ImageView) layout.findViewById(R.id.toastIcon);
    	toastIcon.setBackgroundResource(icon);
    	
    	//����3�� ��ȡLayout�е�TextView�����Ϊ�䶯̬��ֵ
    	TextView toastMessage=(TextView) layout.findViewById(R.id.toastMessage);
    	//����4�� Ϊ�����̬��ֵ
    	toastMessage.setText(message);
    	//����5��ʵ����һ��Toast������󲢽�����ʾ
    	Toast toast=new Toast(getApplicationContext());
    	toast.setDuration(Toast.LENGTH_LONG);
    	//��ǰ�Ĳ����úõĶ��Ʋ����뵱ǰ��Toast������а�
    	toast.setView(layout);
    	//����6 ��ʾToast���
    	toast.show();
    	
    }
	
	
}
