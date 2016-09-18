package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import com.example.assist.GoodslistAdapter;
import com.example.assist.OrderAdapter;
import com.example.entity.Goods;
import com.example.entity.MyUser;
import com.example.entity.OrderAll;
import com.example.singleton.GoodsSingleton;
import com.example.singleton.OrderSingleton;
import com.example.singleton.UserSingleton;

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

public class Mine_goods extends Activity implements OnClickListener{

	private ListView LvMineGoods;
	private TextView TvMinegoods_top;
	private ImageView IvMinegoods_rb,IvMinegoods_search;
	private MyUser user = UserSingleton.getInstance(); //当前用户
	private List<OrderAll> order_all = OrderSingleton.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_goods);
		Bmob.initialize(this, "79f7c1d79f0db04370bf7b20720440db");
		init_order();
		LvMineGoods =(ListView) findViewById(R.id.LvMineGoods);
		TvMinegoods_top =(TextView) findViewById(R.id.TvMinegoods_top);
		IvMinegoods_rb =(ImageView) findViewById(R.id.IvMinegoods_rb);
		IvMinegoods_search =(ImageView) findViewById(R.id.IvMinegoods_search);
		
		IvMinegoods_rb.setOnClickListener(this);
		IvMinegoods_search.setOnClickListener(this);

		
		LvMineGoods.setOnItemClickListener(itemListener);
		TvMinegoods_top.setText(getIntent().getExtras().getString("minegoods"));
		
	}
	private void init_order(){
		Log.d("OrderSingleton","order_all:"+order_all);
		if(order_all==null){
			BmobQuery<OrderAll> query = new BmobQuery<OrderAll>();
			query.setLimit(10000);
//			query.addWhereEqualTo("buyer", user);    // 查询当前用户的所有订单
//			query.addWhereEqualTo("seller", user);    // 查询当前用户的所有订单
			query.order("-createdAt");
			query.include("buyer");	//get point "author" info when query 
			query.findObjects(new FindListener<OrderAll>() {
				@Override
				public void done(List<OrderAll> object, BmobException e) {
					if(e==null){
						Log.d("OrderSingleton","done_object nick:"+object.get(0).getBuyer().getNick());
						OrderSingleton.setInstance(object);   //将得到数据保存到单例对象中，后面数据量大可以考虑保存到本地数据库
						order_all = OrderSingleton.getInstance();
						Log.d("OrderSingleton","user.getObjectId():"+user.getObjectId());
						object = OrderSingleton.getOrder_Seller(user.getObjectId().toString());
						Log.d("OrderSingleton","3success_object:"+object);
						if(object==null){
							return;
						}
						OrderAdapter orderadapter = new OrderAdapter(Mine_goods.this,object);
						Log.d("OrderSingleton","1success_order_all:"+order_all);
						LvMineGoods.setAdapter(orderadapter);
						Log.d("OrderSingleton","success_order_all:"+order_all);
					}else{
						Log.i("OrderSingleton", "findObjects_failed"+e.getMessage()+","+e.getErrorCode());
					}
				}
			});	
		}
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
