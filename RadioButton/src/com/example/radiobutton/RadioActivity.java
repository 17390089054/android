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
 * RadioButton 单选按钮   多个RadioButton放在一个RadioGroup中实现互斥
 * CompoundButton是RadioButton的父类，也是CheckBox的父类 
 *OnCheckedChangeListener是在CompoundButton中定义的,其子类都可以调用
 *
 *
 */
public class RadioActivity extends Activity {
	private RadioButton boy,girl;
	private RadioGroup group;
	@Override
	//方式一   逐个选项绑定OnCheckedChangeListener事件
	/*protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio);
		//初始化控件
		boy=(RadioButton) findViewById(R.id.boy);
		girl=(RadioButton) findViewById(R.id.girl);
		//添加oncheckedChangeListener事件 匿名内部类实现
		boy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				Toast.makeText(RadioActivity.this, "您选择的性别为:"+boy.getText(), Toast.LENGTH_SHORT).show();
			}
		});
		
		girl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				Toast.makeText(RadioActivity.this, "您选择的性别为:"+girl.getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}*/
	//方式二  通过遍历ButtonGroup实现互斥  吐司
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio);
		//初始化控件
		boy=(RadioButton) findViewById(R.id.boy);
		girl=(RadioButton) findViewById(R.id.girl);
		setContentView(R.layout.activity_radio);
		group=(RadioGroup)findViewById(R.id.group);
		group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				switch(arg1){
					case R.id.boy://男
						Toast.makeText(RadioActivity.this, "您选择的性别为:"+boy.getText(),Toast.LENGTH_SHORT).show();
						break;
					case R.id.girl://女
						Toast.makeText(RadioActivity.this, "您选择的性别为:"+girl.getText(),Toast.LENGTH_SHORT).show();
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
