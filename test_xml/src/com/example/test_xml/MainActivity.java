package com.example.test_xml;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void test(View v) throws Exception  {
		String text="";
		//��ȡxml
		XmlResourceParser xrp= this.getResources().getXml(R.xml.users);
		//����xml�ڵ�
		while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT){
			if(xrp.getEventType()==XmlResourceParser.START_TAG){
				String name = xrp.getName();
				if(name.equals("user")){
					String userName = xrp.getAttributeValue(0);
					String phone= xrp.getAttributeValue(1);
					
					text+="�û���:"+userName+"��ϵ��ʽ:"+phone+"\n";
				}
			}
			xrp.next();
		}
		
		TextView tv=(TextView) findViewById(R.id.textView1);
		tv.setText(text);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}