package org.wrf.mydiary.activity;
import java.util.Map;
import org.wrf.mydiary.dao.DiaryDao;
import android.app.Activity;
import android.content.Intent;
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

public class EditActivity extends Activity {
	//定义一个EditView组件
	private EditText editText; 
	//日记内容
	private Map<String,Object>diary;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_edit);
		//实例化EditText组件
		editText=(EditText) findViewById(R.id.diary_edit);
		//查询初始化数据
		initContent();
		
	}
	//初始化编辑框中的内容
	public void initContent(){
		Intent intent=getIntent();
		//取出日记ID信息
		int did = intent.getIntExtra("did", -1);
		//查询日记内容信息
		diary = DiaryDao.queryDiary(did,EditActivity.this);
		//获取内容信息
		String content=diary.get("content").toString();
		//设置编辑框的内容
		editText.setText(content);
		
	}
	//实现ActionBar中的选项按钮 重写OnCreateOPtionMenu方法
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// TODO Auto-generated method stub
			//步骤1 定义一个Inflater映射器
			MenuInflater menuInflater = getMenuInflater();
			//步骤2 利用inflate方法绑定布局文件
			menuInflater.inflate(R.menu.actionbar_menu_finish, menu);
			return true;
		}
		
		//实现ActionBar中的点击事件 重写onOptionsItemSelected方法
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			case R.id.ab_finish:
				//获取EditText文本内容
				String content = editText.getText().toString().trim();
				//修改日记内容
				DiaryDao.editDiary(Integer.parseInt(diary.get("did").toString()), content, EditActivity.this);
				//释放资源
				finish();
			
				//提示
				showCustomerToast(android.R.drawable.ic_lock_lock, "编辑成功!");
				
				//跳转回登录界面
				Intent intent=new Intent();
				intent.setClass(EditActivity.this, DiaryActivity.class);
				startActivity(intent);
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
