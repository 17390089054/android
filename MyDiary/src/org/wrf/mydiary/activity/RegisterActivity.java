package org.wrf.mydiary.activity;

import org.wrf.mydiary.dbutil.SQLiteDButil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class RegisterActivity extends Activity {
	//定义可交互组件
	private EditText regAccount,regPassword,RegConPassword;
	private Button regRegister;
	private RegisterHandler registerHandler; 
	//步骤1 重写Activity中的Oncreate方法 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//步骤2 设置窗体类所绑定的资源文件
		this.setContentView(R.layout.activity_register);
		//实例化组件
		this.regAccount=(EditText) findViewById(R.id.regAccount);
		this.regPassword=(EditText) findViewById(R.id.regPassword);
		this.RegConPassword=(EditText) findViewById(R.id.RegConPassword);
		this.regRegister=(Button) findViewById(R.id.regRegister);
		//定义一个Intent对象接受数据
		//Intent intent=getIntent();
		//以String形式取出
		//String msg = intent.getStringExtra("msg");
		//界面提示
		//Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
		
		//为组件添加监听器
		this.regRegister.setOnClickListener(new ViewOcl());
		//实例化Handler对象
		this.registerHandler=new RegisterHandler();
	
	}
	private class ViewOcl implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.regRegister:
				//表单验证
				boolean flag=checkForm();
				//账号验重
				boolean accountFlag=isAccountRepeat();
				if(accountFlag){
					showCustomerToast(android.R.drawable.ic_delete, "账号已存在！");
				}
				
				//验证通过falg=true&&accountFlag=false则跳转到登录页面
				if(flag&&!accountFlag){
					//获取账号名和密码
					String account=regAccount.getText().toString().trim();
					
					//步骤1 声明一个SharedPreferences.Editor对象 并调用putString方法存入数据
					SharedPreferences.Editor editor=getSharedPreferences("publicData",Context.MODE_PRIVATE).edit();
					//步骤2  将验证通过的账号信息写入该文件中
					editor.putString("account", account);
					//步骤3 将数据进行提交
					editor.commit();
					
					//将用户信息持久化到数据库
					//addUser(account,password);
					//showCustomerToast(android.R.drawable.ic_menu_share, "注册成功！");
					
					
					//多线程实现注册过程
					RegisterThread registerThread=new RegisterThread();
					new Thread(registerThread).start();
				
				}
				break;
			default:
				break;
			}
		}
	}
	
	//输入框验空
	public boolean checkForm(){
		//获取账号框的输入值
		String account=regAccount.getText().toString().trim();
		String password=regPassword.getText().toString().trim();
		String confrimPassword=RegConPassword.getText().toString().trim();
		//判断账号是否为空
		if(account!=null&&"".equals(account)){
			//Toast.makeText(getApplicationContext(), "账号不能为空!", Toast.LENGTH_LONG).show();
			showCustomerToast(android.R.drawable.ic_delete, "账号不能为空!");
			return false;
		}
		//判断密码是否为空
		if(password!=null&&"".equals(password)){
			//Toast.makeText(getApplicationContext(), "密码不能为空!", Toast.LENGTH_LONG).show();
			showCustomerToast(android.R.drawable.ic_delete, "密码不能为空!");
			return false;
		}
		//判断前后密码是否一致
		if(!password.equals(confrimPassword)){
			//Toast.makeText(getApplicationContext(), "前后密码不一致", Toast.LENGTH_LONG).show();
			showCustomerToast(android.R.drawable.ic_delete,"前后密码不一致!");
			return false;
		}
		return true;
	}
	
	//账号验证是否重复
	public boolean isAccountRepeat(){
		//重复标志
		boolean flag=false;
		//获取账号信息
		String account=regAccount.getText().toString().trim();
		//创建数据库连接对象
		SQLiteDButil dButil=new SQLiteDButil(RegisterActivity.this);
		//以只读方式打开数据库
		SQLiteDatabase db = dButil.getReadableDatabase();
		//拼写SQL语句
		String strSQL="select * from users where account=?";
		//执行SQL语句
		Cursor cursor = db.rawQuery(strSQL, new String[]{account});
		//判断账号数量是否大于0
		if(cursor.getCount()>0){
			flag=true;
		}
		//关闭数据库连接
		db.close();
		
		return flag;
	}
	
	
	
	
	//实现ActionBar中的选项按钮 重写OnCreateOPtionMenu方法
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//步骤1 定义一个Inflater映射器
		MenuInflater menuInflater = getMenuInflater();
		//步骤2 利用inflate方法绑定布局文件
		menuInflater.inflate(R.menu.actionbar_menu_register, menu);
		return true;
	}
	//实现ActionBar中的点击事件 重写onOptionsItemSelected方法
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.ab_return:
			//跳转回登录界面
			Intent intent=new Intent();
			intent.setClass(RegisterActivity.this, LoginActivity.class);
			startActivity(intent);
			//系统将该界面从栈中清除
			finish();
			
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	//用户注册 添加用户信息至数据库
	public void addUser(String account,String password){
		//步骤1  创建SQLiteDBUtil工具类对象
		SQLiteDButil dbutil=new SQLiteDButil(RegisterActivity.this);
		//步骤2 以写的方式打开数据库
		SQLiteDatabase db = dbutil.getWritableDatabase();
		//步骤3 动态拼写SQL语句
		
		String strSQL="insert into users values (null,'"+account+"','"+password+"')";
		//步骤4 执行SQL语句  将用户注册信息写入数据库
		db.execSQL(strSQL);
		//步骤5 关闭数据库连接
		db.close();
		
		
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
    
	
	
	
	
	//定义多线程内部类 实现处理注册业务逻辑
	private class RegisterThread implements Runnable{
		
		@Override
		public void run() {
			//将下一任务放入Looper消息循环队列中 便于系统使用时间片轮转算法实现子线程任务
			Looper.prepare();
			
			//执行状态
			boolean flag=false;
			//数据库连接
			SQLiteDatabase db =null;
			try{
				//步骤1  创建SQLiteDBUtil工具类对象
				SQLiteDButil dbutil=new SQLiteDButil(RegisterActivity.this);
				//步骤2 以写的方式打开数据库
				db= dbutil.getWritableDatabase();
				//步骤3 动态拼写SQL语句
				String account=regAccount.getText().toString().trim();
				String password=regPassword.getText().toString().trim();
				String strSQL="insert into users values (null,'"+account+"','"+password+"')";
				//步骤4 执行SQL语句  将用户注册信息写入数据库
				db.execSQL(strSQL);
				flag=true;
			}catch(RuntimeException ex){
				flag=false;
				ex.printStackTrace();
			}finally{
				//步骤5 关闭数据库连接
				db.close();
			}
			
			//将处理结果传入Handler
			Message msg=new Message();
			//定义一个Bundle对象存放结果
			Bundle bundle=new Bundle();
			//封装消息至bundle对象中
			bundle.putBoolean("data", flag);
			//将bundle对象存入Message对象
			msg.setData(bundle);
			//消息转发
			registerHandler.handleMessage(msg);
			
		}
		
		
	}
	
	//定义一个内部类实现Handler消息传递  Handler代理模式
	private class RegisterHandler extends Handler{

		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message msg) {
			//根据消息结果与Activity UI进行交互
			boolean register_flag=msg.getData().getBoolean("data");
			if(register_flag){
				showCustomerToast(android.R.drawable.ic_menu_share,"注册成功");
				//声明一个Intent对象
				Intent intent=new Intent();
				//设置跳转路径
				intent.setClass(RegisterActivity.this, LoginActivity.class);
				//启动界面
				startActivity(intent);
			}else{
				showCustomerToast(android.R.drawable.ic_delete, "注册失败");
			}
		
		}
		
	}
	
	

	
	
	
}
