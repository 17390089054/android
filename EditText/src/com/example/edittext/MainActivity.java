package com.example.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
/*
 *EditText  �ı�����ؼ� ��ʵ�ֱ༭�ı�
 *text  �ı�����
 *textColor �ı���ɫ
 *textSize �ı������С
 *textColor �ı���ɫ
 *ems  �ı��ַ����������(ֻ����warp_content��������)
 *hint ��ʾ����
 *inputType �ı���������
 *EditTextΪTextView������ ��ʹ�����µ��������Լ�����
 *	setTransformationMethod �������л�
 *
 *
 */
public class MainActivity extends Activity {
	private  CheckBox checkBox;
	private  EditText input;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ���ؼ�
		checkBox=(CheckBox) findViewById(R.id.checkBox);
		input=(EditText) findViewById(R.id.input);
		//���ù���Ƿ�ɼ�
		input.setCursorVisible(false);
		//checkBox���OncheckedListener
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if(checkBox.isChecked()){//��ʾ����
					input.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				}else{//��ʾ����
					input.setTransformationMethod(PasswordTransformationMethod.getInstance());
				}
				//���۽��������ĩβ
				input.setSelection(input.getText().length());
			}
		});
	}

}
