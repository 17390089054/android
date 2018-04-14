package com.example.dip;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 *线性布局按照水平或者垂直方向排布  默认水平方向（Horizonal）
 *orientation:用于指定当前线性排布的方向
 *wrap_content:根据自身内容排布
 *match_parent:根据父元素进行排布
 *margin:控件边缘与其他控件边缘的间距(外边距)
 *padding:控件内部与控件边缘的间距(内边距)
 *
 *gravity:控件内容对本身的对齐方式
 *layout_gravity:控件自身在父类布局中的对齐方式
 *如果线性布局的排布方式为水平,那么layout_gravity在水平方向将不起作用，只在垂直方向起作用 反之亦然
 *
 *layout_weight:权重 ，即按照百分比的形式进行划分。
 *如果控件划分的区域为match_parent ,权重越大，那么所占的比重越小
 *如果控件划分的区域为wrap_content,权重越大，那么所占的比例越大
 *
 *在一个页面中 一个有权重的控件和一个无权重的控件，系统会先给谁分配空间？
 *  系统肯定会给无权重的控件分配空间才会得到需要的百分比的空间来进行百分比的划分
 *  权重的概念只存在于线性布局(LinearLayout)中
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
