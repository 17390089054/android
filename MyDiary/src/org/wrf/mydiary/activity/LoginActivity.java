package org.wrf.mydiary.activity;

import org.wrf.mydiary.dbutil.SQLiteDButil;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	//步骤1 声明一个交互式组件
	private Button btnLogin,btnRegister;
	private EditText txtAccount,txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        //步骤2 实例化一个交互式组件 findViewById(Id资源名称)
        this.btnLogin=(Button) this.findViewById(R.id.btnLogin);
        this.btnRegister=(Button) this.findViewById(R.id.btnRegister);
        this.txtAccount=(EditText) this.findViewById(R.id.txtAccount);
        this.txtPassword=(EditText) this.findViewById(R.id.txtPassword);
        //步骤4 按钮组件与监听器类进行绑定
        btnLogin.setOnClickListener(new ViewOcl());
        btnRegister.setOnClickListener(new ViewOcl());
        //步骤5 读取SharedPreference中存放的公共账号的值
        SharedPreferences sharedPreferences = getSharedPreferences("publicData", MODE_PRIVATE);
        String account=sharedPreferences.getString("account", "");
        this.txtAccount.setText(account);
        
        //初始化数据库
        initDatabase();
        
    }
    
    //步骤3 声明一个组件监听器类 用于监听该窗体下所有的交互式组件 该类必须实现View.OnClickListener接口 重写Onclick方法
    private class ViewOcl implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnLogin:
				//1.获取账号密码
				String account=txtAccount.getText().toString().trim();
				String password=txtPassword.getText().toString().trim();
				
				boolean isEmpty=checkForm(account, password);
				if(!isEmpty) return;
				//2.判断账号密码是否正确
				boolean loginFlag=Login(account, password);
				
				//3.根据正确性提示
				String message=loginFlag?"欢迎登录 "+account:"账号或密码错误";
				if(loginFlag){
					// 声明一个SharedPreferences.Editor对象 并调用putString方法存入数据
					SharedPreferences.Editor editor=getSharedPreferences("publicData",Context.MODE_PRIVATE).edit();
					// 将验证通过的账号信息写入该文件中
					editor.putString("account", account);
					//将数据进行提交
					editor.commit();
					
					showCustomerToast(android.R.drawable.ic_menu_view,message);
					//跳转到日志主页面
					Intent intent=new Intent();
					intent.setClass(LoginActivity.this,DiaryActivity.class);
					startActivity(intent);
					finish();
				}else{
					showCustomerToast(android.R.drawable.ic_delete,message);
				}
				//Toast.makeText(getApplicationContext(), message,Toast.LENGTH_LONG).show();
				break;
			case R.id.btnRegister:
				//1.定义一个Intent对象
				Intent intent=new Intent();
				//intent存储变量
				//intent.putExtra("msg", "hello");
				//2.定义跳转路径
				intent.setClass(LoginActivity.this, RegisterActivity.class);
				//3.启动Activity
				startActivity(intent);
				break;
			default:
				break;
			}
			
			
		}
    	
    }
    
    //账号密码验证
    public boolean Login(String account,String password){
		boolean loginFlag=false;
		//步骤1 创建SQLiteDBUtil对象
		SQLiteDButil dbutil=new SQLiteDButil(LoginActivity.this);
		//步骤2 以读方式打开数据库
		SQLiteDatabase db = dbutil.getReadableDatabase();
		//步骤3 创建SQL语句
		String strSQL="select * from users where account=? and password=?";
		//步骤4 调用数据库对象的rawQuery方法获得Cursor对象
		Cursor cursor = db.rawQuery(strSQL, new String[]{account,password});
		//步骤5 根据cursor对象的MoveToFirst判断账号密码是否存在 boolean
		if(cursor.moveToFirst()){
			loginFlag=true;
			//将userId暂时存入SharedPreferences
			SharedPreferences.Editor editor=getSharedPreferences("publicData",Context.MODE_PRIVATE).edit();
			int uid=cursor.getInt(0);
			editor.putInt("uid", uid);
			editor.commit();
		}
		cursor.close();
		db.close();
		return loginFlag;
    }
    
    
    
    
    
    //登录验空
    public boolean checkForm(String account,String password){
    	if("".equals(account)){
			//Toast.makeText(getApplicationContext(), "账号不能为空",Toast.LENGTH_LONG).show();
			showCustomerToast(android.R.drawable.ic_delete,"账号不能为空");
    		return false;
		}
		if("".equals(password)){
			//Toast.makeText(getApplicationContext(), "密码不能为空",Toast.LENGTH_LONG).show();
			showCustomerToast(android.R.drawable.ic_delete,"密码不能为空");
			return false;
		}
    	return true;
    }
    
    //定义一个方法完成对数据库的创建
    private void initDatabase(){
    	//创建一个SQLite操作类对象
    	SQLiteDButil dbUtil=new SQLiteDButil(LoginActivity.this);
    	//使用写入模式打开数据库
    	dbUtil.getWritableDatabase();
    	
    	
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
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
}
