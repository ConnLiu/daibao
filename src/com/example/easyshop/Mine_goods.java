package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.GoodslistAdapter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class Mine_goods extends Activity implements OnClickListener{

	private ListView LvMineGoods;
	private TextView TvMinegoods_top;
	private ImageView IvMinegoods_rb,IvMinegoods_search;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_goods);
		
		LvMineGoods =(ListView) findViewById(R.id.LvMineGoods);
		TvMinegoods_top =(TextView) findViewById(R.id.TvMinegoods_top);
		IvMinegoods_rb =(ImageView) findViewById(R.id.IvMinegoods_rb);
		IvMinegoods_search =(ImageView) findViewById(R.id.IvMinegoods_search);
		
		IvMinegoods_rb.setOnClickListener(this);
		IvMinegoods_search.setOnClickListener(this);

		GoodslistAdapter goodslistadapter = new GoodslistAdapter(this, getData());
		LvMineGoods.setAdapter(goodslistadapter);
		TvMinegoods_top.setText(getIntent().getExtras().getString("minegoods"));

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
			map.put("goodslike", R.drawable.tip_selected);
			list.add(map);
		}
		return list;
	}
	
    @Override
    public void onClick(View v) {
		Intent intent = new Intent();
    	switch(v.getId()){
    	case R.id.IvMinegoods_rb:
    		finish();
    		break;
    	case R.id.IvMinegoods_search:
			intent.setClass(Mine_goods.this, Search.class);
			startActivity(intent);
    		break;
    	}
    	
    }
}
