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
			//内容改变时 触发的方法
			//s表示改变的内容
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.i("MSG","onTextChanged被调用:-----start:"+start+"----before:"+before+"----count:"+count+"----"+s);
			}
			
			//内容改变前触发的方法
			//s表示改变前的内容 通常是start与count的组合  after表示改变之后新增的文本内容
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				Log.i("MSG", "beforeTextChanged被调用:----start:"+start+"---count:"+count+"---after:"+after+"---"+s);
			}
			
			//内容改变后触发的方法
			//e表示改变后的文本内容
			@Override
			public void afterTextChanged(Editable e) {
				if(editText.getText().length()==11){
					Toast.makeText(EditTextActivity.this, "中国联通", Toast.LENGTH_SHORT).show();
				}
				Log.i("MSG", "afterTextChanged被调用:---"+e+"---");
			}
		});
	}


}
