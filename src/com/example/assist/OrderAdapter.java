package com.example.assist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;

import com.example.easyshop.R;
import com.example.entity.OrderAll;

public class OrderAdapter extends BaseAdapter{

	
	private List<OrderAll> list;
	private LayoutInflater inflater;
	private Context context;

	public OrderAdapter(Context context, List<OrderAll> list) {
		super();
		this.context = context;
		this.list = list;
	}
	@Override
	public int getCount() {
		return list.size();
	}
	@Override
	public Object getItem(int position) {
		return position;
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertview, ViewGroup parent) {
		ViewHolder viewholder = null;
		
		if (convertview == null) {
			inflater = LayoutInflater.from(parent.getContext());
			convertview = inflater.inflate(R.layout.orderlist_item, null);
			viewholder = new ViewHolder();

			viewholder.goodsname = (TextView) convertview.findViewById(R.id.TvO_name);	
			viewholder.goodsmoney = (TextView) convertview.findViewById(R.id.TvO_money);	
			viewholder.goodsimage = (ImageView) convertview.findViewById(R.id.IvO);
			viewholder.state = (ImageView) convertview.findViewById(R.id.IvO_state);
			convertview.setTag(viewholder);
		 
		}else {
			viewholder = (ViewHolder) convertview.getTag();
		}
		Log.i("bmob----goodsadapter", "goods getName:"+list.get(position).getGoodname());
		viewholder.goodsname.setText(list.get(position).getGoodname());
		viewholder.goodsmoney.setText(String.valueOf(list.get(position).getPrice()));
		String origin_path = list.get(position).getHead_path();
		//Log.d("GoodslistAdapter","origin_path:"+origin_path);
		if(origin_path==null){
			viewholder.goodsimage.setImageResource(R.drawable.tip_selected); 
		}else{
			String path[]=origin_path.split(",");
			for(int i=0;i<path.length;i++)
				Log.d("GoodslistAdapter","path :"+path[i]);
			download(path[0],list.get(position).getObjectId(),viewholder);
		}
		if(list.get(position).getState()==2){
			viewholder.state.setImageResource(R.drawable.state_finish); 
		}
		else if(list.get(position).getState()==1){
			viewholder.state.setImageResource(R.drawable.state_fetch); 
		}
		else{
			viewholder.state.setImageResource(R.drawable.state_send);
		}
		return convertview;
	}
	void download(String path,final String id,final ViewHolder viewholder){
		final String file_name = id+"_0image.png";
		FileInputStream localstream = null;
			try {
				localstream = context.openFileInput(file_name);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    final Bitmap bm = BitmapFactory.decodeStream(localstream);
	    if(bm != null){
	    	viewholder.goodsimage.setImageBitmap(bm);
			return;
		}
		BmobFile file =new BmobFile(file_name,"",
				path);
		 //允许设置下载文件的存储路径，默认下载文件的目录为：context.getApplicationContext().getCacheDir()+"/bmob/"
	    File saveFile = new File(context.getFilesDir(), file.getFilename());
	    file.download(saveFile, new DownloadFileListener() {
	        @Override
	        public void onStart() {
//	            toast("开始下载...");
	        }
	        @Override
	        public void done(String savePath,cn.bmob.v3.exception.BmobException e) {
	            if(e==null){
	            	Log.i("download:",savePath);
	            	FileInputStream localstream = null;
	        		try {
	        			localstream = context.openFileInput(file_name);
	        		} catch (FileNotFoundException e1) {
	        			// TODO Auto-generated catch block
	        			e1.printStackTrace();
	        		} 
	                final Bitmap bm = BitmapFactory.decodeStream(localstream);
	            	viewholder.goodsimage.setImageBitmap(bm);
	                //toast("下载成功");
	            }else{
	//                toast("下载失败："+e.getErrorCode()+","+e.getMessage());
	            }
	        }
	        @Override
	        public void onProgress(Integer value, long newworkSpeed) {
	//            Log.i("bmob","下载进度："+value+","+newworkSpeed);
	        }
	    });
	}
	class ViewHolder{
		public TextView goodsname;
		public TextView goodsmoney;
		public ImageView goodsimage;
		public ImageView state;
	}
}
