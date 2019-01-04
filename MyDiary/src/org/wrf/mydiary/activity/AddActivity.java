package org.wrf.mydiary.activity;

import org.wrf.mydiary.dao.DiaryDao;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends Activity {
	//定义一个EditView组件
	private EditText editText; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_add);
		//实例化EditText组件
		editText=(EditText) findViewById(R.id.txtContent);
		
	}
	
	//实现ActionBar中的选项按钮 重写OnCreateOPtionMenu方法
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// TODO Auto-generated method stub
			//步骤1 定义一个Inflater映射器
			MenuInflater menuInflater = getMenuInflater();
			//步骤2 利用inflate方法绑定布局文件
			menuInflater.inflate(R.menu.actionbar_menu_finish, menu);
			menuInflater.inflate(R.menu.actionbar_menu_exit, menu);
			return true;
		}
		//实现ActionBar中的点击事件 重写onOptionsItemSelected方法
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			case R.id.ab_finish:
				//获取EditText文本内容
				String content = editText.getText().toString().trim();
				
				//测试
				 SharedPreferences sharedPreferences = getSharedPreferences("publicData", MODE_PRIVATE);
			     int uid=sharedPreferences.getInt("uid", 1);
				
				//添加日记
				DiaryDao.addDiary(content,uid,AddActivity.this);
				//释放资源
				finish();
			
				//提示
				showCustomerToast(android.R.drawable.ic_lock_lock, "添加成功!");
				
				
				
				//跳转回登录界面
				Intent intent=new Intent();
				intent.setClass(AddActivity.this, DiaryActivity.class);
				startActivity(intent);
				break;
			case R.id.ab_exit:
				//跳转回登录页面
				Intent i=new Intent();
				i.setClass(AddActivity.this, LoginActivity.class);
				startActivity(i);
				//释放资源
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
