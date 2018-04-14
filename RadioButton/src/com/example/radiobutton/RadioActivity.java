package com.example.radiobutton;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Enumeration;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * RadioButton ��ѡ��ť   ���RadioButton����һ��RadioGroup��ʵ�ֻ���
 * CompoundButton��RadioButton�ĸ��࣬Ҳ��CheckBox�ĸ��� 
 *OnCheckedChangeListener����CompoundButton�ж����,�����඼���Ե���
 *
 *
 */
public class RadioActivity extends Activity {
	private RadioButton boy,girl;
	private RadioGroup group;
	@Override
	//��ʽһ   ���ѡ���OnCheckedChangeListener�¼�
	/*protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio);
		//��ʼ���ؼ�
		boy=(RadioButton) findViewById(R.id.boy);
		girl=(RadioButton) findViewById(R.id.girl);
		//���oncheckedChangeListener�¼� �����ڲ���ʵ��
		boy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				Toast.makeText(RadioActivity.this, "��ѡ����Ա�Ϊ:"+boy.getText(), Toast.LENGTH_SHORT).show();
			}
		});
		
		girl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				Toast.makeText(RadioActivity.this, "��ѡ����Ա�Ϊ:"+girl.getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}*/
	//��ʽ��  ͨ������ButtonGroupʵ�ֻ���  ��˾
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio);
		//��ʼ���ؼ�
		boy=(RadioButton) findViewById(R.id.boy);
		girl=(RadioButton) findViewById(R.id.girl);
		setContentView(R.layout.activity_radio);
		group=(RadioGroup)findViewById(R.id.group);
		group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				switch(arg1){
					case R.id.boy://��
						Toast.makeText(RadioActivity.this, "��ѡ����Ա�Ϊ:"+boy.getText(),Toast.LENGTH_SHORT).show();
						break;
					case R.id.girl://Ů
						Toast.makeText(RadioActivity.this, "��ѡ����Ա�Ϊ:"+girl.getText(),Toast.LENGTH_SHORT).show();
						break;
					default:
						break;
				}
			}
		});
	}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.radio, menu);
		return true;
	}

}
