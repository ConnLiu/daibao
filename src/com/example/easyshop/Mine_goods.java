package com.example.easyshop;

import java.util.List;
import cn.bmob.v3.Bmob;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;

public class Mine_goods extends Activity implements OnClickListener{

	private ListView LvMineGoods;
	private TextView TvMinegoods_top;
	private ImageView IvMinegoods_rb,IvMinegoods_search;
	private MyUser user = UserSingleton.getInstance(); //当前用户
	private List<OrderAll> order_all = OrderSingleton.getInstance();
	private String minegoods_name = null;
	private List<OrderAll> order = null;
	private OrderAll curorder = null;
	private Goods curgood = null;
	private List<Goods> goods = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_goods);
		Bmob.initialize(this, "79f7c1d79f0db04370bf7b20720440db");
		minegoods_name = getIntent().getExtras().getString("minegoods");
		LvMineGoods =(ListView) findViewById(R.id.LvMineGoods);
		init_order();
		TvMinegoods_top =(TextView) findViewById(R.id.TvMinegoods_top);
		IvMinegoods_rb =(ImageView) findViewById(R.id.IvMinegoods_rb);
		IvMinegoods_search =(ImageView) findViewById(R.id.IvMinegoods_search);
		
		IvMinegoods_rb.setOnClickListener(this);
		IvMinegoods_search.setOnClickListener(this);

		
		LvMineGoods.setOnItemClickListener(itemListener);
		TvMinegoods_top.setText(minegoods_name);
		
	}
	 OnItemClickListener itemListener = new OnItemClickListener() {  
	        
	        public void onItemClick(AdapterView<?> parent, View view, int position,  
	                long id) {  
	            // 这里的view是我们在list.xml中定义的LinearLayout对象.  
	            // 所以可以通过findViewById方法可以找到list.xml中定义的它的子对象,如下: 
	        	Intent intent = new Intent();
	        	if(minegoods_name.equals("我发布的")){
	        		int weizhi = GoodsSingleton.getPosition("13",position);
	        		intent.setClass(Mine_goods.this, GoodsDetail.class);
	        		intent.putExtra("position", weizhi);
	        	}else if(minegoods_name.equals("我的订单")){
		        	if(order!=null){
			        	intent.setClass(Mine_goods.this, Order.class);
		        		intent.putExtra("orderId", order.get(position).getObjectId());
		        	}else{
			        	intent.setClass(Mine_goods.this, GoodsDetail.class);
		        		intent.putExtra("goodId", goods.get(position).getObjectId());
		        	}
	        	}
	        	startActivity(intent);
	        }
	    };  
	    
	//一个界面的复用
	private void init_order(){
		Log.d("OrderSingleton","order_all:"+order_all);
		if(order_all==null){
			toast("订单为空！");
		}else{   //如果 本机已经保存了order的数据
			if(minegoods_name.equals("我发布的")){
				goods = GoodsSingleton.getTypeGoods("13");
				if(goods==null){
					toast("您当前没有发布商品");
					return;
				}
				GoodslistAdapter goodslistadapter = new GoodslistAdapter(Mine_goods.this,goods);
				Log.d("OrderSingleton","1success_order_all:"+order_all);
				LvMineGoods.setAdapter(goodslistadapter);
			}else if(minegoods_name.equals("我卖出的")){
				order = OrderSingleton.getOrder_Seller(user.getObjectId().toString());
				if(order==null){
					toast("您当前没有卖出商品");
					
					return;
				}
				OrderAdapter orderadapter = new OrderAdapter(Mine_goods.this,order);
				Log.d("OrderSingleton","1success_order_all:"+order_all);
				LvMineGoods.setAdapter(orderadapter);
			}else if(minegoods_name.equals("我买到的")){
				order = OrderSingleton.getOrder_Buyer(user.getObjectId().toString());
				if(order==null){
					toast("您当前没有买到商品");
					return;
				}
				OrderAdapter orderadapter = new OrderAdapter(Mine_goods.this,order);
				Log.d("OrderSingleton","1success_order_all:"+order_all);
				LvMineGoods.setAdapter(orderadapter);
			}else if(minegoods_name.equals("我赞过的")){
				toast("待完成");
				return;
			}else if(minegoods_name.equals("我的订单")){
				order = OrderSingleton.getOrder_Undone(user.getObjectId().toString());
				if(order==null){
					toast("您当前没有订单");
					return;
				}
				OrderAdapter orderadapter = new OrderAdapter(Mine_goods.this,order);
				Log.d("OrderSingleton","1success_order_all:"+order_all);
				LvMineGoods.setAdapter(orderadapter);
			}
		}
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
    void toast(String s){
  		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
  	}
}
