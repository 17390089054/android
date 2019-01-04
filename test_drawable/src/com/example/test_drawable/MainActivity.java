package com.example.test_drawable;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void test01(View v){
		Drawable drawable = this.getResources().getDrawable(R.drawable.a);
		this.getWindow().setBackgroundDrawable(drawable);
	}
	
	public void test02(View v){
		Drawable drawable = this.getResources().getDrawable(R.drawable.b);
		this.getWindow().setBackgroundDrawable(drawable);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
