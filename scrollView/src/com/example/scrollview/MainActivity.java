package com.example.scrollview;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ScrollView scrollView;
	private LinearLayout layout;
	private int i=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		layout=(LinearLayout) findViewById(R.id.layout);
		for(;i<50;i++){
			TextView tv=new TextView(MainActivity.this);
			tv.setText("这是TextView"+i);
			tv.setTextSize(20);
			layout.addView(tv);
		}
		
		scrollView=(ScrollView) findViewById(R.id.scrollView1);
		scrollView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_MOVE){
					int scrollY=v.getScrollY();//scrollView向上的高度
					int Height=v.getHeight();//scrollView的高度
					ScrollView view=(ScrollView)v;
					int linearHeight=view.getChildAt(0).getMeasuredHeight();
				
					System.out.println("scrollY:"+scrollY);
					System.out.println("Height:"+Height);
					System.out.println("linearHeight:"+linearHeight);
					System.out.println();
				
					if(scrollY+Height==linearHeight){
						Toast.makeText(MainActivity.this, "已经到末尾了!", Toast.LENGTH_SHORT).show();
					}
				}
				
				return false;
			}
		});
	}


}
