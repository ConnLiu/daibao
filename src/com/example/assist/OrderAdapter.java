package com.example.assist;


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
		//Log.d("GoodslistAdapter","origin_path:"+origin_path);
		SetView(viewholder.goodsimage,list.get(position).getGood().getObjectId()+"_0image.png");
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
	private void SetView(ImageView img,String file_name){
    	FileInputStream localstream = null;
		try {
			localstream = context.openFileInput(file_name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bitmap bm = BitmapFactory.decodeStream(localstream);
		if(bm != null){
			img.setImageBitmap(bm);
		}else{
			img.setImageResource(R.drawable.tip_selected);
		}
    }
	class ViewHolder{
		public TextView goodsname;
		public TextView goodsmoney;
		public ImageView goodsimage;
		public ImageView state;
	}
}
