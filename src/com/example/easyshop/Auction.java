package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.CelllistAdapter;
import com.example.assist.GoodslistAdapter;
import com.example.singleton.GoodsSingleton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
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

		GoodslistAdapter listadapter = new GoodslistAdapter(this, GoodsSingleton.getInstance());
		LvAuction_list.setAdapter(listadapter);
		LvAuction_list.setOnItemClickListener(itemListener);
	}
	
    OnItemClickListener itemListener = new OnItemClickListener() {     
        public void onItemClick(AdapterView<?> parent, View view, int position,  
                long id) {  
            // 这里的view是我们在list.xml中定义的LinearLayout对象.  
            // 所以可以通过findViewById方法可以找到list.xml中定义的它的子对象,如下: 
        	Intent intent = new Intent();
        	intent.setClass(Auction.this, AuctionGoods.class);
        	intent.putExtra("position", position);
        	startActivity(intent);
        }

    };  
	
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
			GoodslistAdapter listadapter = new GoodslistAdapter(this,GoodsSingleton.getInstance());
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
			GoodslistAdapter listadapter1 = new GoodslistAdapter(this, GoodsSingleton.getInstance());
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
			GoodslistAdapter listadapter2 = new GoodslistAdapter(this, GoodsSingleton.getInstance());
			LvAuction_list.setAdapter(listadapter2);
			break;
		}
	}

}
