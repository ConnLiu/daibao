package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.GoodslistAdapter;
import com.example.assist.OrderAdapter;
import com.example.entity.MyUser;
import com.example.singleton.GoodsSingleton;
import com.example.singleton.OrderSingleton;
import com.example.singleton.UserSingleton;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;

public class Mine_goods extends Activity implements OnClickListener{

	private ListView LvMineGoods;
	private TextView TvMinegoods_top;
	private ImageView IvMinegoods_rb,IvMinegoods_search;
	private MyUser user = UserSingleton.getInstance();
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

		OrderAdapter orderadapter = new OrderAdapter(this, OrderSingleton.getOrder(user.getObjectId().toString()));
		LvMineGoods.setAdapter(orderadapter);
		LvMineGoods.setOnItemClickListener(itemListener);
		TvMinegoods_top.setText(getIntent().getExtras().getString("minegoods"));
		
	}
	 OnItemClickListener itemListener = new OnItemClickListener() {  
	        
	        public void onItemClick(AdapterView<?> parent, View view, int position,  
	                long id) {  
	            // 这里的view是我们在list.xml中定义的LinearLayout对象.  
	            // 所以可以通过findViewById方法可以找到list.xml中定义的它的子对象,如下: 	
	        	Intent intent = new Intent();
	        	intent.setClass(Mine_goods.this, Order.class);
	        	intent.putExtra("position", position);
	        	startActivity(intent);
	        }

	    };  

	private List<Map<String,Object>> getData() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i = 0 ; i <4; i ++ )
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsname", "商品名称");
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
