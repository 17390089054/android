package com.example.checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * CheckBox ��ѡ�к�δѡ������״̬
 * 
 *Toast:��ʾ���� (��˾)
 *��ʾ�û���Ϣ���ܺ��û����� ʱ������
 *ʵ��:Toast.makeText(this,"��ѡ�е���:"+str1, Toast.LENGTH_SHORT).show();
 *ʹ�ò���:
 *1.���þ�̬����makeText��׼��Ҫ����Ĳ���
 * Toast android.widget.Toast.makeText(Context context, CharSequence text, int duration)
 *2.���������ģ��ı����ݣ���ʾʱ���Ȳ���  
 *3.����show()��������չʾ
 */
public class MainActivity extends Activity {
	//����ؼ�����
	private Button btn;
	private CheckBox basketball,run,sing,dance,swim;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��ʼ���ؼ�
		setContentView(R.layout.activity_main);
		btn=(Button)findViewById(R.id.btn);
		basketball=(CheckBox)findViewById(R.id.basketball);
		run=(CheckBox)findViewById(R.id.run);
		sing=(CheckBox)findViewById(R.id.sing);
		dance=(CheckBox)findViewById(R.id.dance);
		swim=(CheckBox)findViewById(R.id.swim);
		//ʹ�������ڲ���ķ�ʽʵ��OnClickListener
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showResult();
			}

		}
		);
		
	}
	
	/**
	 * �����ʾ����
	 */
	protected void showResult(){
		//����ɱ��ַ���
		StringBuffer sb=new StringBuffer();
		if(basketball.isChecked()){
			sb.append(basketball.getText().toString()+"��");
		}
		if(run.isChecked()){
			sb.append(run.getText().toString()+"��");
		}
		if(sing.isChecked()){
			sb.append(sing.getText().toString()+"��");
		}
		if(dance.isChecked()){
			sb.append(dance.getText().toString()+"��");
		}
		if(swim.isChecked()){
			sb.append(swim.getText().toString()+"��");
		}
		if(sb.length()>0){
			String str1 = sb.substring(0, sb.length()-1);
			//������ǰ̨
			Toast.makeText(this,"��ѡ�е���:"+str1, Toast.LENGTH_SHORT).show();
			Log.i("MSG","��ѡ�е���:"+str1);
		}else{
			Toast.makeText(this,"��ѡ����Ҫѡ�������", Toast.LENGTH_LONG).show();
			Log.i("MSG","��ѡ����Ҫѡ�������");
		}
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	

}
