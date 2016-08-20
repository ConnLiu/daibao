package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.CelllistAdapter;
import com.example.assist.GoodslistAdapter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;

public class Auction extends Activity implements OnClickListener{

	private int JOIN_CODE=1,END_CODE=2,BILL_CODE=3;
	private TextView TvAuction_join,TvAuction_end,TvAuction_bill;
	private ListView LvAuction_list;
	private ImageView IvAuction_rb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auction);

		TvAuction_join = (TextView)findViewById(R.id.TvAuction_join);
		TvAuction_end = (TextView)findViewById(R.id.TvAuction_end);
		TvAuction_bill = (TextView)findViewById(R.id.TvAuction_bill);
		LvAuction_list =(ListView) findViewById(R.id.LvAuction_list);
		IvAuction_rb =(ImageView) findViewById(R.id.IvAuction_rb);

		TvAuction_join.setOnClickListener(this);
		TvAuction_end.setOnClickListener(this);
		TvAuction_bill.setOnClickListener(this);
		IvAuction_rb.setOnClickListener(this);

		GoodslistAdapter listadapter = new GoodslistAdapter(this, getData(JOIN_CODE));
		LvAuction_list.setAdapter(listadapter);
	}

	private List<Map<String,Object>> getData(int code) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i = 0 ; i <4; i ++ )
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsname", "ÉÌÆ·Ãû³Æ"+code);
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
    	Intent intent = new Intent();
		Resources resource = (Resources) getBaseContext().getResources(); 
		ColorStateList whitecsl = (ColorStateList) resource.getColorStateList(R.color.white);
		ColorStateList backcsl = (ColorStateList) resource.getColorStateList(R.color.home); 
		switch(v.getId()){
		case R.id.IvAuction_rb:
			finish();
		case R.id.TvAuction_join:
			if (whitecsl != null && backcsl != null) {  
				TvAuction_join.setTextColor(whitecsl);  
				TvAuction_bill.setTextColor(backcsl); 
				TvAuction_end.setTextColor(backcsl); 
			}  
			TvAuction_join.setBackgroundResource(R.drawable.hometop_bg);
			TvAuction_bill.setBackgroundResource(R.color.white);
			TvAuction_end.setBackgroundResource(R.color.white);
			GoodslistAdapter listadapter = new GoodslistAdapter(this, getData(JOIN_CODE));
			LvAuction_list.setAdapter(listadapter);
			break;
		case R.id.TvAuction_end:
			if (whitecsl != null && backcsl != null) {  
				TvAuction_end.setTextColor(whitecsl);  
				TvAuction_join.setTextColor(backcsl); 
				TvAuction_bill.setTextColor(backcsl); 
			}  
			TvAuction_end.setBackgroundResource(R.drawable.hometop_bg);
			TvAuction_join.setBackgroundResource(R.color.white);
			TvAuction_bill.setBackgroundResource(R.color.white);
			GoodslistAdapter listadapter1 = new GoodslistAdapter(this, getData(END_CODE));
			LvAuction_list.setAdapter(listadapter1);
			break;
		case R.id.TvAuction_bill:
			if (whitecsl != null && backcsl != null) {  
				TvAuction_bill.setTextColor(whitecsl);  
				TvAuction_join.setTextColor(backcsl); 
				TvAuction_end.setTextColor(backcsl); 
			}  
			TvAuction_bill.setBackgroundResource(R.drawable.hometop_bg);
			TvAuction_join.setBackgroundResource(R.color.white);
			TvAuction_end.setBackgroundResource(R.color.white);
			GoodslistAdapter listadapter2 = new GoodslistAdapter(this, getData(BILL_CODE));
			LvAuction_list.setAdapter(listadapter2);
			break;
		}
	}

}
