package com.example.gridview;

<<<<<<< HEAD
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.wrf.adapter.MyAdapter;

public class MainActivity extends Activity {
	private GridView gridView;
	private String [] names={"�û�","��ҳ","���֤","�ʼ�","��ͼ","���±�","��Ƭ","DVD","�û�","��ҳ","���֤","�ʼ�","��ͼ","���±�","��Ƭ","DVD","�û�","��ҳ","���֤","�ʼ�","��ͼ","���±�","��Ƭ","DVD"};
	private int []images={R.drawable.user,R.drawable.home,R.drawable.id,R.drawable.mail,
			R.drawable.map,R.drawable.notepad,R.drawable.photos,R.drawable.dvd,R.drawable.user,R.drawable.home,R.drawable.id,R.drawable.mail,
			R.drawable.map,R.drawable.notepad,R.drawable.photos,R.drawable.dvd,R.drawable.user,R.drawable.home,R.drawable.id,R.drawable.mail,
			R.drawable.map,R.drawable.notepad,R.drawable.photos,R.drawable.dvd};
	private MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ��gridView
		gridView=(GridView) findViewById(R.id.grid);
		//��ʼ��������
		adapter=new MyAdapter(images, names, this);
		//����������ӵ�GridView��
		gridView.setAdapter(adapter);
		//����ӵ���¼�
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				Toast.makeText(MainActivity.this, adapter.getItem(position), Toast.LENGTH_SHORT).show();
			}
		
		});		
	}

	

=======
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
>>>>>>> android开发
}
