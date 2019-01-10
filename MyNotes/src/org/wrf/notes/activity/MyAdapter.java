package org.wrf.notes.activity;

import org.wrf.notes.R;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	private Context context;
	private Cursor cursor;
	private LinearLayout layout;
	
	public MyAdapter(Context context,Cursor cursor){
		this.context=context;
		this.cursor=cursor;
	}
	
	
	@Override
	public int getCount() {
		return cursor.getCount();
	}

	@Override
	public Object getItem(int position) {
		return cursor.getPosition();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	@Override
	public View getView(int postion, View view, ViewGroup parent) {
		LayoutInflater inflater=LayoutInflater.from(context);
		layout=(LinearLayout) inflater.inflate(R.layout.ceil, null);
		TextView content = (TextView) layout.findViewById(R.id.lv_content);
		TextView time = (TextView) layout.findViewById(R.id.lv_time);
		ImageView image=(ImageView) layout.findViewById(R.id.lv_image);
		ImageView vedio=(ImageView) layout.findViewById(R.id.lv_vedio);
		//移动游标
		cursor.moveToPosition(postion);
		String txt_content = cursor.getString(cursor.getColumnIndex("content"));
		String txt_time = cursor.getString(cursor.getColumnIndex("time"));
		String url=cursor.getString(cursor.getColumnIndex("path"));
		String videoUrl=cursor.getString(cursor.getColumnIndex("video"));
		
		//设置内容
		content.setText(txt_content);
		time.setText(txt_time);
		image.setImageBitmap(getImageThumbnail(url, 200, 200));
		vedio.setImageBitmap(getVideoThumbnail(videoUrl, 200, 200,
				MediaStore.Images.Thumbnails.MICRO_KIND));
		return layout;
	}
	
	/**
	 * 获取缩略图
	 * @param uri
	 * @param width
	 * @param height
	 * @return
	 */
	public Bitmap getImageThumbnail(String uri,int width,int height){
		Bitmap bitmap=null;
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inJustDecodeBounds=true;
		bitmap=BitmapFactory.decodeFile(uri,options);
		options.inJustDecodeBounds=false;
		//计算缩略图实际宽高
		int beWidth=options.outWidth/width;
		int beHeight=options.outHeight/height;
		int be=1;
		if(beWidth<beHeight){
			be=beWidth;
		}else{
			be=beHeight;
		}
		if(be<=0){
			be=1;
		}
		options.inSampleSize=be;
		bitmap=BitmapFactory.decodeFile(uri,options);
		bitmap=ThumbnailUtils.extractThumbnail(bitmap, width, height,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
	
		return bitmap;
	}
	
	private Bitmap getVideoThumbnail(String uri,int width,int height,int kind){
		Bitmap bitmap=null;
		bitmap=ThumbnailUtils.createVideoThumbnail(uri, kind);
		bitmap=ThumbnailUtils.extractThumbnail(bitmap, width, height,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}

}
