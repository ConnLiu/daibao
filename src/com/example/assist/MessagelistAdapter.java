package com.example.assist;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easyshop.R;

public class MessagelistAdapter extends BaseAdapter{

	
	private List<Map<String, Object>> list;
	private LayoutInflater inflater;

	public MessagelistAdapter(Context context, List<Map<String, Object>> list) {
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
			convertview = inflater.inflate(R.layout.messagelist_item, null);
			viewholder = new ViewHolder();

			viewholder.name = (TextView) convertview.findViewById(R.id.TvMessagelist_name);	
			viewholder.time = (TextView) convertview.findViewById(R.id.TvMessagelist_time);	
			viewholder.rank = (TextView) convertview.findViewById(R.id.TvMessagelist_rank);	
			viewholder.comment = (TextView) convertview.findViewById(R.id.TvMessagelist_comment);
			viewholder.head = (ImageView) convertview.findViewById(R.id.IvMessageitem_head);
			convertview.setTag(viewholder);
		 
		}else {
			viewholder = (ViewHolder) convertview.getTag();
		}
		
		viewholder.name.setText(list.get(position).get("name").toString());
		viewholder.time.setText(list.get(position).get("time").toString());
		viewholder.rank.setText(list.get(position).get("rank").toString());
		viewholder.comment.setText(list.get(position).get("comment").toString());
		viewholder.head.setImageResource(Integer.parseInt(list.get(position).get("head").toString())); 
		
		return convertview;
	}
	class ViewHolder{
		public TextView name;
		public TextView time;
		public TextView rank;
		public TextView comment;
		public ImageView head;
	}
}
