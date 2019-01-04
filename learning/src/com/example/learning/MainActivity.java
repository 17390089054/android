package com.example.learning;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button btn;
	private EditText school;
	private EditText grade;
	private EditText major;
	private EditText pros;
	private EditText cons;
	private RadioGroup group; 
	private RadioButton boy;
	private RadioButton girl;
    private CheckBox sing,playball,book,other;
	private List<CheckBox>checkBoxList=new ArrayList<CheckBox>();
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        //��ʼ���ؼ�
       school=(EditText) findViewById(R.id.school);
       major=(EditText) findViewById(R.id.major);
       grade=(EditText) findViewById(R.id.grade);
       pros=(EditText) findViewById(R.id.pros); 
       cons=(EditText) findViewById(R.id.cons);
       //�Ա��
       group=(RadioGroup) findViewById(R.id.group); 
       boy=(RadioButton) findViewById(R.id.boy);
       girl=(RadioButton)findViewById(R.id.girl);
       //���ÿ�
       sing=(CheckBox) findViewById(R.id.sing);
       playball=(CheckBox) findViewById(R.id.playball);
       book=(CheckBox) findViewById(R.id.book);
       other=(CheckBox) findViewById(R.id.other);

       checkBoxList.add(sing);
       checkBoxList.add(playball);
       checkBoxList.add(book);
       checkBoxList.add(other);
       
       btn=(Button) findViewById(R.id.btn);
       
       
       
       
       btn.setOnClickListener(new MyListener());
       
    
    }
    //�ж������Ƿ�Ϊ��
    private boolean isBlank(EditText et){
    	if(et.getText().toString().trim().equals("")){
    		return true;
    	}
    	return false;
    }
    //�ж��Ա���Ƿ�ѡ��
    private boolean isChecked(RadioGroup group){
    	if(!boy.isChecked()&&!girl.isChecked()){
    		return false;
    	}    	
    	return true;
    	
    }
    
    //�жϰ����Ƿ�ѡ��
    private boolean isSelected(){
    	for(CheckBox checkBox:checkBoxList){
    		if(checkBox.isChecked()){
    			return true;
    		}
    	}
    	return false;
    }
    
    //��ȡ��������
    private String hobbies(){
    	StringBuilder sb=new StringBuilder();
    	for(CheckBox checkBox:checkBoxList){
    		if(checkBox.isChecked()){
    			sb.append(checkBox.getText().toString()+" ");
    		}
    	}
    	return sb.toString();
    }
    
    
    
    /*   btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Toast.makeText(MainActivity.this, "ע��ɹ�",Toast.LENGTH_SHORT).show();
		}
	});    */
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private class MyListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			//У�������Ƿ�Ϊ��
			if(isBlank(school)){
				Toast.makeText(MainActivity.this, "ѧУ���Ʋ���Ϊ�գ�", Toast.LENGTH_LONG).show();
				return;
			}
			if(isBlank(grade)){
				Toast.makeText(MainActivity.this, "�꼶��Ų���Ϊ�գ�", Toast.LENGTH_LONG).show();
				return;
			}
			if(isBlank(major)){
				Toast.makeText(MainActivity.this, "רҵ���Ʋ���Ϊ�գ�", Toast.LENGTH_LONG).show();
				return;
			}
			if(isBlank(pros)){
				Toast.makeText(MainActivity.this, "���ƿ�Ŀ����Ϊ�գ�", Toast.LENGTH_LONG).show();
				return;
			}
			if(isBlank(cons)){
				Toast.makeText(MainActivity.this, "���ƿ�Ŀ����Ϊ�գ�", Toast.LENGTH_LONG).show();
				return;
			}
			
			//У�鵥ѡ��ť�Ƿ�ѡ�� ����RadioButton��ť
			if(isChecked(group)){
				
				group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(RadioGroup group, int checkId) {
						switch(checkId){
							case R.id.boy:
								Toast.makeText(MainActivity.this,"��ѡ�����У�", Toast.LENGTH_SHORT).show();
								break;
							case R.id.girl:
								Toast.makeText(MainActivity.this,"��ѡ����Ů��" , Toast.LENGTH_SHORT).show();
								break;
						}
					}
				});
				
			}else{
				Toast.makeText(MainActivity.this, "��ѡ���Ա�", Toast.LENGTH_SHORT).show();
				return;
			}
			
			//�жϰ���ѡ��
			if(isSelected()){
				//Toast.makeText(MainActivity.this, "���İ���Ϊ:"+hobbies(), Toast.LENGTH_SHORT).show();
				Log.i("TAG","���İ���Ϊ:"+hobbies());
			}else{
				Toast.makeText(MainActivity.this, "��ѡ�񰮺ã�", Toast.LENGTH_SHORT).show();
				return;
			}
			
			//��ʾע��ɹ�
			Toast.makeText(MainActivity.this, "ע��ɹ�", Toast.LENGTH_SHORT).show();
			
			
			Intent intent=new Intent(MainActivity.this,SecondActivity.class);
			startActivity(intent);
		} 
    	
		
		
		
    }
    
}