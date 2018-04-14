package com.example.gridlayout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 *GridLayout 网格布局
 *rowCount 行数
 *columnCount 列数
 *layout_columnSpan 合并列数
 *layout_rowSpan 合并行数
 *layout_gravity fill
 *按照表格布局进行排列
 *网格布局特点
 *安卓4.0以后发布 最低版本API14
 *
 *网格布局与表格布局的区别 
 *TableLayout只能通过TableRow来呈现内容
 *GridLayOut可以直接通过控件来呈现内容
 *表格布局只能合并列
 *网格布局可以合并行和列
 *
 */
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

}
