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
	//定义ListView对象
	private ListView listView; 
	//定义SimpleAdapter对象
	private SimpleAdapter simpleAdapter;
	//定义集合对象
	private List<Map<String,?>>data;
	//定义用户ID
	private int userid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_diary);
		//实例化ListView对象
		listView=(ListView) findViewById(R.id.list_view);
		//获取用户ID
		SharedPreferences sharedPreferences = getSharedPreferences("publicData", MODE_PRIVATE);
	    userid=sharedPreferences.getInt("uid", 1);
		//获取数据
		data=this.getData();
		//实例化Adapter
		simpleAdapter=new SimpleAdapter(DiaryActivity.this,data,R.layout.diary_item,
				new String[]{"date","time","content"}, new int[]{R.id.date,R.id.time,R.id.content});
		//为ListView设置适配器
		listView.setAdapter(simpleAdapter);
		//为ListView注册上下文菜单
		registerForContextMenu(listView);
		
		//监听ListView中item长按事件
		//listView.setOnItemLongClickListener(new ListViewListener());
		
	}
	
	//定义一个监听器监听ListView中的每一个Item
	@SuppressWarnings("unused")
	private class ListViewListener implements AdapterView.OnItemLongClickListener{

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View v,
				int position, long id) {
				
			showCustomerToast(android.R.drawable.btn_star_big_on, "点击了第"+position+"项");
			return false;
		}

		
	}
	
	
	
	
	//菜单长按选中 实现弹出选择菜单
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info=(AdapterContextMenuInfo)item.getMenuInfo();
		//Log.v("msg",String.valueOf(info.position)+" "+data.get(info.position).get("id"));
		//获取选中item在data集合中的位置Position
		final int position=info.position;
		//获取选中Item数据库记录ID
		final Integer did=(Integer) data.get(position).get("id");
		//判断选中的菜单选项是编辑还是删除
		switch (item.getItemId()) {
		case R.id.edit:
			//如果是编辑操作 那么将当前Item的数据库id存入Intent 并跳转到编辑页面
			Intent intent=new Intent();
			intent.putExtra("did", did);
			intent.setClass(DiaryActivity.this, EditActivity.class);
			startActivity(intent);
			//showCustomerToast(android.R.drawable.btn_radio, "编辑操作");
			break;
		case R.id.delete:
			//弹出对话框提示是否删除
			AlertDialog.Builder builder=new Builder(DiaryActivity.this);
			builder.setMessage("确认删除么？");
			builder.setTitle("提示");
			//添加setPositiveButton()方法
			builder.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//删除选中日记信息
					DiaryDao.deleteDiary(did,DiaryActivity.this);
					if(data.remove(position)!=null){
						Log.i("msg","success");
						showCustomerToast(android.R.drawable.btn_radio, "删除成功！");
					}else{
						Log.i("msg","failed");
						showCustomerToast(android.R.drawable.btn_radio, "删除失败！");
					}
					//更新数据集
					simpleAdapter.notifyDataSetChanged();
					
				}
			});
			//添加setNegativeButton()
			builder.setNegativeButton("取消", new OnClickListener() {
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


	//重写 onCreateContextMenu加载上下文菜单
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		//获取AdpaterContextMenuInfo 因为创建菜单是通过长按AdapterView操作得到的
		//AdapaterContextMenuInfo中实现了menuInfo接口，提供了targetView,Position,Id 
		//AdapterContextMenuInfo info=(AdapterContextMenuInfo)menuInfo;
		
		super.onCreateContextMenu(menu, v, menuInfo);
		//加载xml中上下文菜单
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.main, menu);
	}

	
	/**
	 * 获取数据 填充数据集
	 * @return List<Map<String,?>> 数据集
	 */
	public List<Map<String,?>>  getData(){
		//定义一个集合对象存放数据
		List<Map<String,?>>lstData=new ArrayList<Map<String,?>>();
		//创建数据库连接对象
		SQLiteDButil dButil=new SQLiteDButil(DiaryActivity.this);
		//只读方式获取数据库对象
		SQLiteDatabase db = dButil.getReadableDatabase();
		//创建SQL查询语句
		String strSQL="select * from diarys where uid=? order by diarydate desc";
		//执行SQL语句获取游标对象Cursor
		Cursor cursor = db.rawQuery(strSQL, new String[]{String.valueOf(userid)});
		//判断是否有数据 没有则给出提示
		if(cursor.getCount()==0){
			showCustomerToast(0, "目前尚无日记");
		}
		
		
		//利用cursor对象的MoveToNext方法遍历
		while(cursor.moveToNext()){
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("id", cursor.getInt(0));
			map.put("content", cursor.getString(1));
			//SQLite数据库Date数据类型处理
			Date date=null;//用于接受数据库中日期类型字段
			String diaryDate=null;//用于接收数据库中日期时间类型进行拆分的日期类型
			String diaryTime=null;//用于接收数据库中日期时间类型进行拆分的时间类型
			
			try {
				//使用SimpleDateFormat处理数据库中的日期数据
				date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA).parse(cursor.getString(cursor.getColumnIndex("diarydate")));
				diaryDate=new SimpleDateFormat("MM月dd日",Locale.CHINA).format(date);
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
		//关闭游标对象
		cursor.close();
		//关闭连接对象
		db.close();

		return lstData;
		
	}
	

	//实现ActionBar中的选项按钮 重写OnCreateOPtionMenu方法
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//步骤1 定义一个Inflater映射器
		MenuInflater menuInflater = getMenuInflater();
		//步骤2 利用inflate方法绑定布局文件
		menuInflater.inflate(R.menu.actionbar_menu_add, menu);
		menuInflater.inflate(R.menu.actionbar_menu_exit, menu);
		return true;
	}
	//实现ActionBar中的点击事件 重写onOptionsItemSelected方法
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.ab_add:
			//跳转到添加界面
			Intent intent=new Intent();
			intent.setClass(DiaryActivity.this, AddActivity.class);
			startActivity(intent);
			break;
		case R.id.ab_exit:
			//跳转到登录页面
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
	
	  //自定义Toast信息显示
    public void showCustomerToast(final int icon,final String message){
    	//步骤1： 创建一个layoutInflater接口对象
    	LayoutInflater layoutInflater = getLayoutInflater();
    	//步骤2： 使用layoutInflater的inflate方法绑定自定义Toast对象 并指向toast_layout_root根节点
    	View layout = layoutInflater.inflate(R.layout.toast_customer,(ViewGroup) findViewById( R.id.toast_layout_root));
    	
    	ImageView toastIcon=(ImageView) layout.findViewById(R.id.toastIcon);
    	toastIcon.setBackgroundResource(icon);
    	
    	//步骤3： 获取Layout中的TextView组件并为其动态赋值
    	TextView toastMessage=(TextView) layout.findViewById(R.id.toastMessage);
    	//步骤4： 为组件动态赋值
    	toastMessage.setText(message);
    	//步骤5：实例化一个Toast组件对象并进行显示
    	Toast toast=new Toast(getApplicationContext());
    	toast.setDuration(Toast.LENGTH_LONG);
    	//将前四部设置好的定制布局与当前的Toast对象进行绑定
    	toast.setView(layout);
    	//步骤6 显示Toast组件
    	toast.show();
    	
    }
	
	
}
