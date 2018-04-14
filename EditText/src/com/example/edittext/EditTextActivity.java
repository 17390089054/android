package com.example.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 *
 */
public class EditTextActivity extends Activity {
	private EditText editText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_text);
		editText=(EditText) findViewById(R.id.text);
		editText.addTextChangedListener(new TextWatcher() {
			//���ݸı�ʱ �����ķ���
			//s��ʾ�ı������
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.i("MSG","onTextChanged������:-----start:"+start+"----before:"+before+"----count:"+count+"----"+s);
			}
			
			//���ݸı�ǰ�����ķ���
			//s��ʾ�ı�ǰ������ ͨ����start��count�����  after��ʾ�ı�֮���������ı�����
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				Log.i("MSG", "beforeTextChanged������:----start:"+start+"---count:"+count+"---after:"+after+"---"+s);
			}
			
			//���ݸı�󴥷��ķ���
			//e��ʾ�ı����ı�����
			@Override
			public void afterTextChanged(Editable e) {
				if(editText.getText().length()==11){
					Toast.makeText(EditTextActivity.this, "�й���ͨ", Toast.LENGTH_SHORT).show();
				}
				Log.i("MSG", "afterTextChanged������:---"+e+"---");
			}
		});
	}


}
