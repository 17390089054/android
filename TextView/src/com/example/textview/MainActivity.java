package com.example.textview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

<<<<<<< HEAD
/**
 *TextView  文本控件 
 *android: 声明在xmlns的工作空间内
 *android:text 文本内容
 *android:textColor 字体颜色
 * android:textSize 字体大小
 *android:visibility 文本可见性
 * 	visibile 可见（默认）
 *  invisible 不可见保留空间
 *  gone 不可见不保留位置
 *android:autoLink 链接类型
 *web  网页型链接  
 * 
 *TextView实现跑马灯效果
 *	android:singleLine="true" 单行显示
 *	android:ellipsize="marquee" 
 *	android:focusable="true" 设置聚焦
 *	android:focusableInTouchMode="true"
 *	android:scrollHorizontally="true" 设置滚动
 *	android:marqueeRepeatLimit="marquee_forever" 设置循环滚动 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.line);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

=======
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
>>>>>>> android寮�鍙�
}
