package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.CelllistAdapter;
import com.example.assist.CommentlistAdapter;
import com.example.customview.ListViewForScrollView;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.app.Activity;

public class GoodsDetail extends Activity implements OnClickListener{

	private ListViewForScrollView LvGoodsDetail;
	private ScrollView SvGoodsDetail;
	private ImageView IvGoodsDetail_rb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_detail);
		
		LvGoodsDetail =(ListViewForScrollView) findViewById(R.id.LvGoodsDetail);
		SvGoodsDetail =(ScrollView) findViewById(R.id.SvGoodsDetail);
		IvGoodsDetail_rb =(ImageView) findViewById(R.id.IvGoodsDetail_rb);

		SvGoodsDetail.smoothScrollTo(0, 0);
		CommentlistAdapter listadapter = new CommentlistAdapter(this, getData());
		LvGoodsDetail.setDividerHeight(0);
		LvGoodsDetail.setAdapter(listadapter);
		
		IvGoodsDetail_rb.setOnClickListener(this);
	}

	private List<Map<String,Object>> getData() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i = 0 ; i <4; i ++ )
		{
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("username", "username");
			map.put("comment", "comment");
			map.put("time", "time");
			map.put("headimage", R.drawable.tip_selected);
			list.add(map);
		}
		return list;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.IvGoodsDetail_rb:
			finish();
		}
		
	}
}
