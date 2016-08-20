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

public class CommentlistAdapter extends BaseAdapter{

	private List<Map<String, Object>> list;
	private LayoutInflater inflater;

	public CommentlistAdapter(Context context, List<Map<String, Object>> list) {
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
			convertview = inflater.inflate(R.layout.commentlist_item, null);
			viewholder = new ViewHolder();

			viewholder.username = (TextView) convertview.findViewById(R.id.TvComment_username);	
			viewholder.comment = (TextView) convertview.findViewById(R.id.TvComment_comment);	
			viewholder.time = (TextView) convertview.findViewById(R.id.TvComment_time);	
			viewholder.headimage = (ImageView) convertview.findViewById(R.id.IvCommentitem_head);
			convertview.setTag(viewholder);
		 
		}else {
			viewholder = (ViewHolder) convertview.getTag();
		}
		
		viewholder.username.setText(list.get(position).get("username").toString());
		viewholder.comment.setText(list.get(position).get("comment").toString());
		viewholder.time.setText(list.get(position).get("time").toString());
		viewholder.headimage.setImageResource(Integer.parseInt(list.get(position).get("headimage").toString())); 
		
		return convertview;
	}
	class ViewHolder{
		public TextView username;
		public TextView comment;
		public TextView time;
		public ImageView headimage;
	}
}
