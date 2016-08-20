package com.example.assist;

import java.util.List;
import java.util.Map;

import com.example.easyshop.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WordslistAdapter extends BaseAdapter{

	
	private List<Map<String, Object>> list;
	private LayoutInflater inflater;

	public WordslistAdapter(Context context, List<Map<String, Object>> list) {
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
			convertview = inflater.inflate(R.layout.words_list_item, null);
			viewholder = new ViewHolder();

			viewholder.goodsname = (TextView) convertview.findViewById(R.id.goodsname);	
			viewholder.goodsmoney = (TextView) convertview.findViewById(R.id.goodsmoney);	
			viewholder.goodslikenum = (TextView) convertview.findViewById(R.id.goodslikenum);	
			viewholder.goodsimage = (ImageView) convertview.findViewById(R.id.goodsimage);
			viewholder.img_userHead = (ImageView) convertview.findViewById(R.id.img_userHead);
			viewholder.tv_time = (TextView) convertview.findViewById(R.id.tv_time);
			viewholder.tv_userName = (TextView) convertview.findViewById(R.id.tv_userName);
			viewholder.tv_content = (TextView) convertview.findViewById(R.id.tv_content);
			convertview.setTag(viewholder);
		}else {
			viewholder = (ViewHolder) convertview.getTag();
		}
		viewholder.tv_content.setText(list.get(position).get("content").toString());
		viewholder.tv_time.setText(list.get(position).get("time").toString());
		viewholder.tv_userName.setText(list.get(position).get("userName").toString());
		viewholder.goodsname.setText(list.get(position).get("goodsname").toString());
		viewholder.goodsmoney.setText(list.get(position).get("goodsmoney").toString());
		viewholder.goodslikenum.setText(list.get(position).get("goodslikenum").toString());
		//Log.d("Home", list.get(position).get("newsimage").toString());
		
		//viewholder.goodsimage.setImageResource(Integer.parseInt(list.get(position).get("goodsimage").toString())); 
		//viewholder.img_userHead.setImageResource(Integer.parseInt(list.get(position).get("goodslike").toString())); 
		
		return convertview;
	}
	class ViewHolder{
		public TextView goodsname;
		public TextView goodsmoney;
		public TextView tv_userName;
		public TextView tv_time;
		public TextView tv_content;
		public TextView goodslikenum;
		public ImageView goodsimage;
		public ImageView img_userHead;
	}
}
