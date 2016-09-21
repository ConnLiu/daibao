package com.example.easyshop;

import java.util.List;

import com.example.entity.OrderAll;
import com.example.singleton.OrderSingleton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class Order extends Activity implements OnClickListener{
	private Button btn_cfm_get;
	private ImageView IvOrder_rb,img_good;
	private TextView tv_goodName,tv_price,TvOrder_tel,TvOrder_price,TvOrder_state;
	private OrderAll order = null;
	private List<OrderAll> instance = OrderSingleton.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		
		btn_cfm_get = (Button) findViewById(R.id.btn_cfm_get);
		IvOrder_rb =(ImageView) findViewById(R.id.IvOrder_rb);
		img_good =(ImageView) findViewById(R.id.img_good);
		tv_goodName =(TextView) findViewById(R.id.tv_goodName);
		tv_price =(TextView) findViewById(R.id.tv_price);
		TvOrder_tel =(TextView) findViewById(R.id.TvOrder_tel);
		TvOrder_price =(TextView) findViewById(R.id.TvOrder_price);
		TvOrder_state =(TextView) findViewById(R.id.TvOrder_state);
		
		Intent intent = getIntent();
		Init(intent);
		
		btn_cfm_get.setOnClickListener(this);
		IvOrder_rb.setOnClickListener(this);
	}
	
	public void Init(Intent intent){
		String orderId = intent.getStringExtra("orderId").toString();
		for(OrderAll i:instance){
			if(i.getObjectId().equals(orderId)){
				this.order = i;
				break;
			}
		}
		if(order != null){
			String origin_path = order.getGood().getHead_path();
			if(origin_path==null){
				img_good.setImageResource(R.drawable.tip_selected); 
			}else{
				String path[]=origin_path.split(",");
				for(int i=0;i<path.length;i++)
					Log.d("GoodslistAdapter","path :"+path[i]);
				////////////////////////////////////////////////////////
				//download(path[0],order.getGood().getObjectId(),viewholder);
			}
			//tv_goodName。setText(order.getGood().getName().toString());  //奇怪的报错
			//tv_price.setText(order.getPrice());              //float 不能直接转为int，改数据类型或其他
			//TvOrder_pricesetText(order.getPrice());          //同上
			
			TvOrder_tel.setText(order.getBuyer().getMobilePhoneNumber().toString());
			if(order.getState()==2){
				TvOrder_state.setText("已结束"); 
			}
			else if(order.getState()==1){
				TvOrder_state.setText("待收货"); 
			}
			else{
				TvOrder_state.setText("待发货"); 
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.IvOrder_rb:
			finish();
		case R.id.btn_cfm_get:
			toast("购买成功!");
			finish();
			Intent intent = new Intent(Order.this,Order_com.class);
			startActivity(intent);
		}
		
	}
	 void toast(String s){
			Toast.makeText(this, s, Toast.LENGTH_LONG).show();
		}
}
