package com.example.textview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

<<<<<<< HEAD
/**
 *TextView  ÎÄ±¾¿Ø¼ş 
 *android: ÉùÃ÷ÔÚxmlnsµÄ¹¤×÷¿Õ¼äÄÚ
 *android:text ÎÄ±¾ÄÚÈİ
 *android:textColor ×ÖÌåÑÕÉ«
 * android:textSize ×ÖÌå´óĞ¡
 *android:visibility ÎÄ±¾¿É¼ûĞÔ
 * 	visibile ¿É¼û£¨Ä¬ÈÏ£©
 *  invisible ²»¿É¼û±£Áô¿Õ¼ä
 *  gone ²»¿É¼û²»±£ÁôÎ»ÖÃ
 *android:autoLink Á´½ÓÀàĞÍ
 *web  ÍøÒ³ĞÍÁ´½Ó  
 * 
 *TextViewÊµÏÖÅÜÂíµÆĞ§¹û
 *	android:singleLine="true" µ¥ĞĞÏÔÊ¾
 *	android:ellipsize="marquee" 
 *	android:focusable="true" ÉèÖÃ¾Û½¹
 *	android:focusableInTouchMode="true"
 *	android:scrollHorizontally="true" ÉèÖÃ¹ö¶¯
 *	android:marqueeRepeatLimit="marquee_forever" ÉèÖÃÑ­»·¹ö¶¯ 
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
    
>>>>>>> androidå¼€å‘
}
