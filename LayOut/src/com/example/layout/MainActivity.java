package com.example.layout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 *RelativeLayOut 相对布局
 *控件之间的相对位置进行排布。存在一个参照物，一般的相对布局中的控件会存在id的属性
 *layout_centerInParent 位于父类中部
 *alignParentRight 对齐父类的右方放行可以更换
 *alignBottom:底部对齐 alignRight：右边对齐 
 *@+id:添加系统中不存在的ID 进入系统
 *@id 表示的是从系统中去除的已添加的ID
 *alignBaseLine: 基准线对齐
 *
 *
 *TableLayOut 表格布局
 *是LinearLayOut的子类
 *stretchColumns: 拉升某一列，让布局紧凑
 *shrinkColumn:压缩某一行，让怎提内容得以呈现
 *collapseColumns:隐藏某一列
 *
 *TableRow的宽高可以不指定 系统可以自动生产
 *
 *
 *FrameLayOut 帧布局
 *每一个控件都是一个独立的页面 多个控件实现页面覆盖
 *坐标从左上(0,0)开始排布
 *帧布局适用范围
 *手机联系人字母字母导航
 *帧动画
 *
 *
 *AbsoluteLayOut 绝对布局(不推荐使用)
 *根据像素点进行排布
 *默认从左上角(0,0)开始
 *通过LayOut_X 和LayOut_Y 进行排布
 *不利于屏幕适配
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
