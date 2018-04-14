package com.example.layout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 *RelativeLayOut ��Բ���
 *�ؼ�֮������λ�ý����Ų�������һ�������һ�����Բ����еĿؼ������id������
 *layout_centerInParent λ�ڸ����в�
 *alignParentRight ���븸����ҷ����п��Ը���
 *alignBottom:�ײ����� alignRight���ұ߶��� 
 *@+id:���ϵͳ�в����ڵ�ID ����ϵͳ
 *@id ��ʾ���Ǵ�ϵͳ��ȥ��������ӵ�ID
 *alignBaseLine: ��׼�߶���
 *
 *
 *TableLayOut ��񲼾�
 *��LinearLayOut������
 *stretchColumns: ����ĳһ�У��ò��ֽ���
 *shrinkColumn:ѹ��ĳһ�У����������ݵ��Գ���
 *collapseColumns:����ĳһ��
 *
 *TableRow�Ŀ�߿��Բ�ָ�� ϵͳ�����Զ�����
 *
 *
 *FrameLayOut ֡����
 *ÿһ���ؼ�����һ��������ҳ�� ����ؼ�ʵ��ҳ�渲��
 *���������(0,0)��ʼ�Ų�
 *֡�������÷�Χ
 *�ֻ���ϵ����ĸ��ĸ����
 *֡����
 *
 *
 *AbsoluteLayOut ���Բ���(���Ƽ�ʹ��)
 *�������ص�����Ų�
 *Ĭ�ϴ����Ͻ�(0,0)��ʼ
 *ͨ��LayOut_X ��LayOut_Y �����Ų�
 *��������Ļ����
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
