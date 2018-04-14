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
 * CheckBox 有选中和未选中两种状态
 * 
 *Toast:提示机制 (土司)
 *提示用户信息不能和用户交互 时长有限
 *实例:Toast.makeText(this,"您选中的有:"+str1, Toast.LENGTH_SHORT).show();
 *使用步骤:
 *1.调用静态方法makeText，准备要传入的参数
 * Toast android.widget.Toast.makeText(Context context, CharSequence text, int duration)
 *2.设置上下文，文本内容，提示时长等参数  
 *3.调用show()方法进行展示
 */
public class MainActivity extends Activity {
	//定义控件对象
	private Button btn;
	private CheckBox basketball,run,sing,dance,swim;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//初始化控件
		setContentView(R.layout.activity_main);
		btn=(Button)findViewById(R.id.btn);
		basketball=(CheckBox)findViewById(R.id.basketball);
		run=(CheckBox)findViewById(R.id.run);
		sing=(CheckBox)findViewById(R.id.sing);
		dance=(CheckBox)findViewById(R.id.dance);
		swim=(CheckBox)findViewById(R.id.swim);
		//使用匿名内部类的方式实现OnClickListener
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showResult();
			}

		}
		);
		
	}
	
	/**
	 * 结果显示方法
	 */
	protected void showResult(){
		//定义可变字符串
		StringBuffer sb=new StringBuffer();
		if(basketball.isChecked()){
			sb.append(basketball.getText().toString()+"，");
		}
		if(run.isChecked()){
			sb.append(run.getText().toString()+"，");
		}
		if(sing.isChecked()){
			sb.append(sing.getText().toString()+"，");
		}
		if(dance.isChecked()){
			sb.append(dance.getText().toString()+"，");
		}
		if(swim.isChecked()){
			sb.append(swim.getText().toString()+"，");
		}
		if(sb.length()>0){
			String str1 = sb.substring(0, sb.length()-1);
			//回显至前台
			Toast.makeText(this,"您选中的有:"+str1, Toast.LENGTH_SHORT).show();
			Log.i("MSG","您选中的有:"+str1);
		}else{
			Toast.makeText(this,"请选择你要选择的内容", Toast.LENGTH_LONG).show();
			Log.i("MSG","请选择你要选择的内容");
		}
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	

}
