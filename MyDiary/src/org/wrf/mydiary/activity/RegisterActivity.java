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
	//����ɽ������
	private EditText regAccount,regPassword,RegConPassword;
	private Button regRegister;
	private RegisterHandler registerHandler; 
	//����1 ��дActivity�е�Oncreate���� 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//����2 ���ô��������󶨵���Դ�ļ�
		this.setContentView(R.layout.activity_register);
		//ʵ�������
		this.regAccount=(EditText) findViewById(R.id.regAccount);
		this.regPassword=(EditText) findViewById(R.id.regPassword);
		this.RegConPassword=(EditText) findViewById(R.id.RegConPassword);
		this.regRegister=(Button) findViewById(R.id.regRegister);
		//����һ��Intent�����������
		//Intent intent=getIntent();
		//��String��ʽȡ��
		//String msg = intent.getStringExtra("msg");
		//������ʾ
		//Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
		
		//Ϊ�����Ӽ�����
		this.regRegister.setOnClickListener(new ViewOcl());
		//ʵ����Handler����
		this.registerHandler=new RegisterHandler();
	
	}
	private class ViewOcl implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.regRegister:
				//����֤
				boolean flag=checkForm();
				//�˺�����
				boolean accountFlag=isAccountRepeat();
				if(accountFlag){
					showCustomerToast(android.R.drawable.ic_delete, "�˺��Ѵ��ڣ�");
				}
				
				//��֤ͨ��falg=true&&accountFlag=false����ת����¼ҳ��
				if(flag&&!accountFlag){
					//��ȡ�˺���������
					String account=regAccount.getText().toString().trim();
					
					//����1 ����һ��SharedPreferences.Editor���� ������putString������������
					SharedPreferences.Editor editor=getSharedPreferences("publicData",Context.MODE_PRIVATE).edit();
					//����2  ����֤ͨ�����˺���Ϣд����ļ���
					editor.putString("account", account);
					//����3 �����ݽ����ύ
					editor.commit();
					
					//���û���Ϣ�־û������ݿ�
					//addUser(account,password);
					//showCustomerToast(android.R.drawable.ic_menu_share, "ע��ɹ���");
					
					
					//���߳�ʵ��ע�����
					RegisterThread registerThread=new RegisterThread();
					new Thread(registerThread).start();
				
				}
				break;
			default:
				break;
			}
		}
	}
	
	//��������
	public boolean checkForm(){
		//��ȡ�˺ſ������ֵ
		String account=regAccount.getText().toString().trim();
		String password=regPassword.getText().toString().trim();
		String confrimPassword=RegConPassword.getText().toString().trim();
		//�ж��˺��Ƿ�Ϊ��
		if(account!=null&&"".equals(account)){
			//Toast.makeText(getApplicationContext(), "�˺Ų���Ϊ��!", Toast.LENGTH_LONG).show();
			showCustomerToast(android.R.drawable.ic_delete, "�˺Ų���Ϊ��!");
			return false;
		}
		//�ж������Ƿ�Ϊ��
		if(password!=null&&"".equals(password)){
			//Toast.makeText(getApplicationContext(), "���벻��Ϊ��!", Toast.LENGTH_LONG).show();
			showCustomerToast(android.R.drawable.ic_delete, "���벻��Ϊ��!");
			return false;
		}
		//�ж�ǰ�������Ƿ�һ��
		if(!password.equals(confrimPassword)){
			//Toast.makeText(getApplicationContext(), "ǰ�����벻һ��", Toast.LENGTH_LONG).show();
			showCustomerToast(android.R.drawable.ic_delete,"ǰ�����벻һ��!");
			return false;
		}
		return true;
	}
	
	//�˺���֤�Ƿ��ظ�
	public boolean isAccountRepeat(){
		//�ظ���־
		boolean flag=false;
		//��ȡ�˺���Ϣ
		String account=regAccount.getText().toString().trim();
		//�������ݿ����Ӷ���
		SQLiteDButil dButil=new SQLiteDButil(RegisterActivity.this);
		//��ֻ����ʽ�����ݿ�
		SQLiteDatabase db = dButil.getReadableDatabase();
		//ƴдSQL���
		String strSQL="select * from users where account=?";
		//ִ��SQL���
		Cursor cursor = db.rawQuery(strSQL, new String[]{account});
		//�ж��˺������Ƿ����0
		if(cursor.getCount()>0){
			flag=true;
		}
		//�ر����ݿ�����
		db.close();
		
		return flag;
	}
	
	
	
	
	//ʵ��ActionBar�е�ѡ�ť ��дOnCreateOPtionMenu����
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//����1 ����һ��Inflaterӳ����
		MenuInflater menuInflater = getMenuInflater();
		//����2 ����inflate�����󶨲����ļ�
		menuInflater.inflate(R.menu.actionbar_menu_register, menu);
		return true;
	}
	//ʵ��ActionBar�еĵ���¼� ��дonOptionsItemSelected����
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.ab_return:
			//��ת�ص�¼����
			Intent intent=new Intent();
			intent.setClass(RegisterActivity.this, LoginActivity.class);
			startActivity(intent);
			//ϵͳ���ý����ջ�����
			finish();
			
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	//�û�ע�� ����û���Ϣ�����ݿ�
	public void addUser(String account,String password){
		//����1  ����SQLiteDBUtil���������
		SQLiteDButil dbutil=new SQLiteDButil(RegisterActivity.this);
		//����2 ��д�ķ�ʽ�����ݿ�
		SQLiteDatabase db = dbutil.getWritableDatabase();
		//����3 ��̬ƴдSQL���
		
		String strSQL="insert into users values (null,'"+account+"','"+password+"')";
		//����4 ִ��SQL���  ���û�ע����Ϣд�����ݿ�
		db.execSQL(strSQL);
		//����5 �ر����ݿ�����
		db.close();
		
		
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
    
	
	
	
	
	//������߳��ڲ��� ʵ�ִ���ע��ҵ���߼�
	private class RegisterThread implements Runnable{
		
		@Override
		public void run() {
			//����һ�������Looper��Ϣѭ�������� ����ϵͳʹ��ʱ��Ƭ��ת�㷨ʵ�����߳�����
			Looper.prepare();
			
			//ִ��״̬
			boolean flag=false;
			//���ݿ�����
			SQLiteDatabase db =null;
			try{
				//����1  ����SQLiteDBUtil���������
				SQLiteDButil dbutil=new SQLiteDButil(RegisterActivity.this);
				//����2 ��д�ķ�ʽ�����ݿ�
				db= dbutil.getWritableDatabase();
				//����3 ��̬ƴдSQL���
				String account=regAccount.getText().toString().trim();
				String password=regPassword.getText().toString().trim();
				String strSQL="insert into users values (null,'"+account+"','"+password+"')";
				//����4 ִ��SQL���  ���û�ע����Ϣд�����ݿ�
				db.execSQL(strSQL);
				flag=true;
			}catch(RuntimeException ex){
				flag=false;
				ex.printStackTrace();
			}finally{
				//����5 �ر����ݿ�����
				db.close();
			}
			
			//������������Handler
			Message msg=new Message();
			//����һ��Bundle�����Ž��
			Bundle bundle=new Bundle();
			//��װ��Ϣ��bundle������
			bundle.putBoolean("data", flag);
			//��bundle�������Message����
			msg.setData(bundle);
			//��Ϣת��
			registerHandler.handleMessage(msg);
			
		}
		
		
	}
	
	//����һ���ڲ���ʵ��Handler��Ϣ����  Handler����ģʽ
	private class RegisterHandler extends Handler{

		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message msg) {
			//������Ϣ�����Activity UI���н���
			boolean register_flag=msg.getData().getBoolean("data");
			if(register_flag){
				showCustomerToast(android.R.drawable.ic_menu_share,"ע��ɹ�");
				//����һ��Intent����
				Intent intent=new Intent();
				//������ת·��
				intent.setClass(RegisterActivity.this, LoginActivity.class);
				//��������
				startActivity(intent);
			}else{
				showCustomerToast(android.R.drawable.ic_delete, "ע��ʧ��");
			}
		
		}
		
	}
	
	

	
	
	
}
