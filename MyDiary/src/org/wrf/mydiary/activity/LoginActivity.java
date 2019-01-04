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

	//����1 ����һ������ʽ���
	private Button btnLogin,btnRegister;
	private EditText txtAccount,txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ȥ��������
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        //����2 ʵ����һ������ʽ��� findViewById(Id��Դ����)
        this.btnLogin=(Button) this.findViewById(R.id.btnLogin);
        this.btnRegister=(Button) this.findViewById(R.id.btnRegister);
        this.txtAccount=(EditText) this.findViewById(R.id.txtAccount);
        this.txtPassword=(EditText) this.findViewById(R.id.txtPassword);
        //����4 ��ť��������������а�
        btnLogin.setOnClickListener(new ViewOcl());
        btnRegister.setOnClickListener(new ViewOcl());
        //����5 ��ȡSharedPreference�д�ŵĹ����˺ŵ�ֵ
        SharedPreferences sharedPreferences = getSharedPreferences("publicData", MODE_PRIVATE);
        String account=sharedPreferences.getString("account", "");
        this.txtAccount.setText(account);
        
        //��ʼ�����ݿ�
        initDatabase();
        
    }
    
    //����3 ����һ������������� ���ڼ����ô��������еĽ���ʽ��� �������ʵ��View.OnClickListener�ӿ� ��дOnclick����
    private class ViewOcl implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnLogin:
				//1.��ȡ�˺�����
				String account=txtAccount.getText().toString().trim();
				String password=txtPassword.getText().toString().trim();
				
				boolean isEmpty=checkForm(account, password);
				if(!isEmpty) return;
				//2.�ж��˺������Ƿ���ȷ
				boolean loginFlag=Login(account, password);
				
				//3.������ȷ����ʾ
				String message=loginFlag?"��ӭ��¼ "+account:"�˺Ż��������";
				if(loginFlag){
					// ����һ��SharedPreferences.Editor���� ������putString������������
					SharedPreferences.Editor editor=getSharedPreferences("publicData",Context.MODE_PRIVATE).edit();
					// ����֤ͨ�����˺���Ϣд����ļ���
					editor.putString("account", account);
					//�����ݽ����ύ
					editor.commit();
					
					showCustomerToast(android.R.drawable.ic_menu_view,message);
					//��ת����־��ҳ��
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
				//1.����һ��Intent����
				Intent intent=new Intent();
				//intent�洢����
				//intent.putExtra("msg", "hello");
				//2.������ת·��
				intent.setClass(LoginActivity.this, RegisterActivity.class);
				//3.����Activity
				startActivity(intent);
				break;
			default:
				break;
			}
			
			
		}
    	
    }
    
    //�˺�������֤
    public boolean Login(String account,String password){
		boolean loginFlag=false;
		//����1 ����SQLiteDBUtil����
		SQLiteDButil dbutil=new SQLiteDButil(LoginActivity.this);
		//����2 �Զ���ʽ�����ݿ�
		SQLiteDatabase db = dbutil.getReadableDatabase();
		//����3 ����SQL���
		String strSQL="select * from users where account=? and password=?";
		//����4 �������ݿ�����rawQuery�������Cursor����
		Cursor cursor = db.rawQuery(strSQL, new String[]{account,password});
		//����5 ����cursor�����MoveToFirst�ж��˺������Ƿ���� boolean
		if(cursor.moveToFirst()){
			loginFlag=true;
			//��userId��ʱ����SharedPreferences
			SharedPreferences.Editor editor=getSharedPreferences("publicData",Context.MODE_PRIVATE).edit();
			int uid=cursor.getInt(0);
			editor.putInt("uid", uid);
			editor.commit();
		}
		cursor.close();
		db.close();
		return loginFlag;
    }
    
    
    
    
    
    //��¼���
    public boolean checkForm(String account,String password){
    	if("".equals(account)){
			//Toast.makeText(getApplicationContext(), "�˺Ų���Ϊ��",Toast.LENGTH_LONG).show();
			showCustomerToast(android.R.drawable.ic_delete,"�˺Ų���Ϊ��");
    		return false;
		}
		if("".equals(password)){
			//Toast.makeText(getApplicationContext(), "���벻��Ϊ��",Toast.LENGTH_LONG).show();
			showCustomerToast(android.R.drawable.ic_delete,"���벻��Ϊ��");
			return false;
		}
    	return true;
    }
    
    //����һ��������ɶ����ݿ�Ĵ���
    private void initDatabase(){
    	//����һ��SQLite���������
    	SQLiteDButil dbUtil=new SQLiteDButil(LoginActivity.this);
    	//ʹ��д��ģʽ�����ݿ�
    	dbUtil.getWritableDatabase();
    	
    	
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
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
}
