package org.wrf.mydiary.activity;
import java.util.Map;
import org.wrf.mydiary.dao.DiaryDao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends Activity {
	//����һ��EditView���
	private EditText editText; 
	//�ռ�����
	private Map<String,Object>diary;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_edit);
		//ʵ����EditText���
		editText=(EditText) findViewById(R.id.diary_edit);
		//��ѯ��ʼ������
		initContent();
		
	}
	//��ʼ���༭���е�����
	public void initContent(){
		Intent intent=getIntent();
		//ȡ���ռ�ID��Ϣ
		int did = intent.getIntExtra("did", -1);
		//��ѯ�ռ�������Ϣ
		diary = DiaryDao.queryDiary(did,EditActivity.this);
		//��ȡ������Ϣ
		String content=diary.get("content").toString();
		//���ñ༭�������
		editText.setText(content);
		
	}
	//ʵ��ActionBar�е�ѡ�ť ��дOnCreateOPtionMenu����
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// TODO Auto-generated method stub
			//����1 ����һ��Inflaterӳ����
			MenuInflater menuInflater = getMenuInflater();
			//����2 ����inflate�����󶨲����ļ�
			menuInflater.inflate(R.menu.actionbar_menu_finish, menu);
			return true;
		}
		
		//ʵ��ActionBar�еĵ���¼� ��дonOptionsItemSelected����
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			case R.id.ab_finish:
				//��ȡEditText�ı�����
				String content = editText.getText().toString().trim();
				//�޸��ռ�����
				DiaryDao.editDiary(Integer.parseInt(diary.get("did").toString()), content, EditActivity.this);
				//�ͷ���Դ
				finish();
			
				//��ʾ
				showCustomerToast(android.R.drawable.ic_lock_lock, "�༭�ɹ�!");
				
				//��ת�ص�¼����
				Intent intent=new Intent();
				intent.setClass(EditActivity.this, DiaryActivity.class);
				startActivity(intent);
				break;

			default:
				break;
			}
			
			return super.onOptionsItemSelected(item);
		}

		//�Զ���Toast��Ϣ��ʾ
	    public void showCustomerToast(final int icon,final String message){
	    	//����1�� ����һ��layoutInflater�ӿڶ���
	    	LayoutInflater layoutInflater = getLayoutInflater();
	    	//����2�� ʹ��layoutInflater��inflate�������Զ���Toast���� ��ָ��toast_layout_root���ڵ�
	    	View layout = layoutInflater.inflate(R.layout.toast_customer,(ViewGroup) findViewById( R.id.toast_layout_root));
	    	
	    	ImageView toastIcon=(ImageView) layout.findViewById(R.id.toastIcon);
	    	toastIcon.setBackgroundResource(icon);
	    	
	    	//����3�� ��ȡLayout�е�TextView�����Ϊ�䶯̬��ֵ
	    	TextView toastMessage=(TextView) layout.findViewById(R.id.toastMessage);
	    	//����4�� Ϊ�����̬��ֵ
	    	toastMessage.setText(message);
	    	//����5��ʵ����һ��Toast������󲢽�����ʾ
	    	Toast toast=new Toast(getApplicationContext());
	    	toast.setDuration(Toast.LENGTH_LONG);
	    	//��ǰ�Ĳ����úõĶ��Ʋ����뵱ǰ��Toast������а�
	    	toast.setView(layout);
	    	//����6 ��ʾToast���
	    	toast.show();
	    	
	    }
	
}
