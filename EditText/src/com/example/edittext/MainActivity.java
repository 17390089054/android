package com.example.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
/*
 *EditText  文本输入控件 可实现编辑文本
 *text  文本内容
 *textColor 文本颜色
 *textSize 文本字体大小
 *textColor 文本颜色
 *ems  文本字符呈现最大宽度(只有在warp_content下有作用)
 *hint 提示文字
 *inputType 文本内容类型
 *EditText为TextView的子类 可使用其下的所有属性及方法
 *	setTransformationMethod 明密文切换
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
		//初始化控件
		checkBox=(CheckBox) findViewById(R.id.checkBox);
		input=(EditText) findViewById(R.id.input);
		//设置光标是否可见
		input.setCursorVisible(false);
		//checkBox添加OncheckedListener
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if(checkBox.isChecked()){//显示明文
					input.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				}else{//显示密文
					input.setTransformationMethod(PasswordTransformationMethod.getInstance());
				}
				//光标聚焦在输入框末尾
				input.setSelection(input.getText().length());
			}
		});
	}

}
