package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.GoodslistAdapter;
import com.example.customview.ListViewForScrollView;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.app.Activity;

public class CellDetail extends Activity implements OnClickListener{

	private ListViewForScrollView LvCellD;
	private ScrollView SvCellD;
	private ImageView IvCellD_head,IvCellD_rb,IvCellD_search;
	private TextView TvCellD_name,TvCellD_rank,TvCellD_publishnum,TvCellD_peoplenum,TvCellD_addnum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cell_detail);
		
		LvCellD =(ListViewForScrollView) findViewById(R.id.LvCellD);
		SvCellD =(ScrollView) findViewById(R.id.SvCellD);
		IvCellD_head =(ImageView) findViewById(R.id.IvCellD_head);
		IvCellD_rb =(ImageView) findViewById(R.id.IvCellD_rb);
		IvCellD_search =(ImageView) findViewById(R.id.IvCellD_search);
		TvCellD_name =(TextView) findViewById(R.id.TvCellD_name);
		TvCellD_rank =(TextView) findViewById(R.id.TvCellD_rank);
		TvCellD_publishnum =(TextView) findViewById(R.id.TvCellD_publishnum);
		TvCellD_peoplenum =(TextView) findViewById(R.id.TvCellD_peoplenum);
		TvCellD_addnum =(TextView) findViewById(R.id.TvCellD_addnum);
		
		SvCellD.smoothScrollTo(0, 0);
		IvCellD_rb.setOnClickListener(this);
		IvCellD_search.setOnClickListener(this);
		GoodslistAdapter goodslistadapter = new GoodslistAdapter(this, getData());
		LvCellD.setAdapter(goodslistadapter);
		LvCellD.setDividerHeight(0);
	}

	private List<Map<String,Object>> getData() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i = 0 ; i <4; i ++ )
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsname", "ÉÌÆ·Ãû³Æ");
			map.put("goodsmoney", "0.00");
			map.put("goodslikenum", "88");
			map.put("goodsimage", R.drawable.tip_selected);
			map.put("goodslike", R.drawable.zan);
			list.add(map);
		}
		return list;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.IvCellD_rb:
			finish();
		case R.id.IvCellD_search:
			break;
		}
		
	}
}
