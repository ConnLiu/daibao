package com.example.easyshop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import com.example.entity.MyUser;
import com.example.entity.OrderAll;
import com.example.singleton.OrderSingleton;
import com.example.singleton.UserSingleton;

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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Order extends Activity implements OnClickListener{
	private Button btn_cfm_get;
	private ImageView IvOrder_rb,img_good;
	private TextView tv_goodName,origin_price,TvOrder_tel,TvOrder_price,TvOrder_state,tv_price;
	private OrderAll order = null;
	private MyUser user = UserSingleton.getInstance(); //当前用户
	private List<OrderAll> instance = OrderSingleton.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		
		btn_cfm_get = (Button) findViewById(R.id.btn_cfm_get);
		IvOrder_rb =(ImageView) findViewById(R.id.IvOrder_rb);
		img_good =(ImageView) findViewById(R.id.img_good);
		tv_goodName =(TextView) findViewById(R.id.tv_goodName);
		origin_price =(TextView) findViewById(R.id.origin_price);
		TvOrder_tel =(TextView) findViewById(R.id.TvOrder_tel);
		tv_price = (TextView) findViewById(R.id.tv_price);
		TvOrder_price =(TextView) findViewById(R.id.TvOrder_price);
		TvOrder_state =(TextView) findViewById(R.id.TvOrder_state);
		Intent intent = getIntent();
		Init(intent);
		
		btn_cfm_get.setOnClickListener(this);
		IvOrder_rb.setOnClickListener(this);
	}
	private void SetView(ImageView img,String file_name){
    	FileInputStream localstream = null;
		try {
			localstream = openFileInput(file_name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bitmap bm = BitmapFactory.decodeStream(localstream);
		if(bm != null){
			img.setImageBitmap(bm);
		}else{
			img.setImageResource(R.drawable.tip_selected);
		}
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
			SetView(img_good,order.getGood().getObjectId()+"_0image.png");
			Log.d("order","order.getGood:"+order.getGood().getObjectId()+"  order"+order);
			tv_goodName.setText(order.getGood().getName().toString());  //奇怪的报错
			tv_price.setText(String.valueOf(order.getGood().getPrice()));              //float 不能直接转为int，改数据类型或其他
			TvOrder_price.setText(String.valueOf(order.getGood().getPrice()));        //同上
			origin_price.setText(String.valueOf(order.getGood().getOld_price()));
			TvOrder_tel.setText(order.getPhone());
			if(order.getState()==2){
				TvOrder_state.setText("已结束"); 
				btn_cfm_get.setVisibility(View.GONE);
			}
			else if(order.getState()==1){
				TvOrder_state.setText("待收货"); 
				btn_cfm_get.setText("确认收货"); 
			}
			else{
				TvOrder_state.setText("待发货");
				btn_cfm_get.setText("确认发货");
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.IvOrder_rb:
			finish();
		case R.id.btn_cfm_get:
			if(order.getState()==1){
				if(!user.getObjectId().equals(order.getBuyer().getObjectId())){
					toast("请等待发货！");
					return;
				}
				finish();
				order.setState(2);
				order.update(new UpdateListener() {
					
					@Override
					public void done(BmobException e) {
						// TODO Auto-generated method stub
						if(e==null){
							toast("确认收货成功!");
						}else{
							toast("确认收货失败"+e.getMessage());
						}
					}
				});
				Intent intent = new Intent(Order.this,Order_com.class);
				startActivity(intent);
				finish();
			}else if(order.getState()==0){
				if(!user.getObjectId().equals(order.getSeller().getObjectId())){
					toast("请等待确认收货!");
					return;
				}
				toast("确认发货成功!");
				order.setState(1);
				order.update(new UpdateListener() {
					@Override
					public void done(BmobException e) {
						// TODO Auto-generated method stub
						if(e==null){
							toast("确认发货成功!");
						}else{
							toast("确认发货失败"+e.getMessage());
						}
					}
				});
			}
			
		}
		
	}
	 void toast(String s){
			Toast.makeText(this, s, Toast.LENGTH_LONG).show();
		}
}
