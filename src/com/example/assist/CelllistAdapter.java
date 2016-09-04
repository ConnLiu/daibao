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

import com.example.assist.GoodslistAdapter.ViewHolder;
import com.example.easyshop.R;
import com.example.entity.CellInfo;

public class CelllistAdapter extends BaseAdapter{

	private List<CellInfo> list;
	private LayoutInflater inflater;

	public CelllistAdapter(Context context, List<CellInfo> list) {
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
			convertview = inflater.inflate(R.layout.celllist_item, null);
			viewholder = new ViewHolder();

			viewholder.cellname = (TextView) convertview.findViewById(R.id.cellname);	
			viewholder.publishnum = (TextView) convertview.findViewById(R.id.publishnum);	
			viewholder.peoplenum = (TextView) convertview.findViewById(R.id.peoplenum);	
			viewholder.cellimage = (ImageView) convertview.findViewById(R.id.cellimage);
			convertview.setTag(viewholder);
		 
		}else {
			viewholder = (ViewHolder) convertview.getTag();
		}
		
		viewholder.cellname.setText(list.get(position).getName());
		viewholder.publishnum.setText(String.valueOf(list.get(position).getPublish()));
		viewholder.peoplenum.setText(String.valueOf(list.get(position).getPeople()));
		viewholder.cellimage.setImageResource(R.drawable.tip_selected); 
		
		return convertview;
	}
	class ViewHolder{
		public TextView cellname;
		public TextView publishnum;
		public TextView peoplenum;
		public ImageView cellimage;
	}
}
