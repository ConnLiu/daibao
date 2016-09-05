package com.example.assist;

import java.util.List;
import java.util.Map;

import com.example.easyshop.R;
import com.example.entity.Goods;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodslistAdapter extends BaseAdapter{

	
	private List<Goods> list;
	private LayoutInflater inflater;

	public GoodslistAdapter(Context context, List<Goods> list) {
		super();
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
			convertview = inflater.inflate(R.layout.goodslist_item, null);
			viewholder = new ViewHolder();

			viewholder.goodsname = (TextView) convertview.findViewById(R.id.goodsname);	
			viewholder.goodsmoney = (TextView) convertview.findViewById(R.id.goodsmoney);	
			viewholder.goodslikenum = (TextView) convertview.findViewById(R.id.goodslikenum);	
			viewholder.goodsimage = (ImageView) convertview.findViewById(R.id.goodsimage);
			viewholder.goodslike = (ImageView) convertview.findViewById(R.id.goodslike);
			convertview.setTag(viewholder);
		 
		}else {
			viewholder = (ViewHolder) convertview.getTag();
		}
		Log.i("bmob----goodsadapter", "goods getName:"+list.get(position).getName());
		viewholder.goodsname.setText(list.get(position).getName());
		viewholder.goodsmoney.setText(String.valueOf(list.get(position).getPrice()));
		viewholder.goodslikenum.setText(String.valueOf(list.get(position).getLike_num()));
		//Log.d("Home", list.get(position).get("newsimage").toString());
		viewholder.goodsimage.setImageResource(R.drawable.tip_selected); 
		viewholder.goodslike.setImageResource(R.drawable.zan_red); 
		return convertview;
	}
	class ViewHolder{
		public TextView goodsname;
		public TextView goodsmoney;
		public TextView goodslikenum;
		public ImageView goodsimage;
		public ImageView goodslike;
	}
}
