package com.example.dip;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 *���Բ��ְ���ˮƽ���ߴ�ֱ�����Ų�  Ĭ��ˮƽ����Horizonal��
 *orientation:����ָ����ǰ�����Ų��ķ���
 *wrap_content:�������������Ų�
 *match_parent:���ݸ�Ԫ�ؽ����Ų�
 *margin:�ؼ���Ե�������ؼ���Ե�ļ��(��߾�)
 *padding:�ؼ��ڲ���ؼ���Ե�ļ��(�ڱ߾�)
 *
 *gravity:�ؼ����ݶԱ���Ķ��뷽ʽ
 *layout_gravity:�ؼ������ڸ��಼���еĶ��뷽ʽ
 *������Բ��ֵ��Ų���ʽΪˮƽ,��ôlayout_gravity��ˮƽ���򽫲������ã�ֻ�ڴ�ֱ���������� ��֮��Ȼ
 *
 *layout_weight:Ȩ�� �������հٷֱȵ���ʽ���л��֡�
 *����ؼ����ֵ�����Ϊmatch_parent ,Ȩ��Խ����ô��ռ�ı���ԽС
 *����ؼ����ֵ�����Ϊwrap_content,Ȩ��Խ����ô��ռ�ı���Խ��
 *
 *��һ��ҳ���� һ����Ȩ�صĿؼ���һ����Ȩ�صĿؼ���ϵͳ���ȸ�˭����ռ䣿
 *  ϵͳ�϶������Ȩ�صĿؼ�����ռ�Ż�õ���Ҫ�İٷֱȵĿռ������аٷֱȵĻ���
 *  Ȩ�صĸ���ֻ���������Բ���(LinearLayout)��
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
