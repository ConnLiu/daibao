package com.example.easyshop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.example.customview.EditDialog;
import com.example.entity.Goods;
import com.example.entity.MyUser;
import com.example.entity.OrderAll;
import com.example.singleton.GoodsSingleton;
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
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Buy extends Activity implements OnClickListener{
	private Button btn_buy;
	private TextView TvBuy_phone,tvBuy_goodName,tvBuy_price,origin_price,real_price;
	private MyUser user,seller;
	private ImageView img_good,IvBuy_rb;
	private Goods good;
	private OrderAll order = new OrderAll();
	private int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy);
		Intent intent = getIntent();
		user = UserSingleton.getInstance();
		position=getIntent().getIntExtra("position", 0);
		good = GoodsSingleton.getInstance().get(position);
		
		seller = good.getAuthor();
		Log.i("buy_order","good:"+good+" seller:"+seller+" user"+user);
		tvBuy_goodName =(TextView) findViewById(R.id.tvBuy_goodName);
		tvBuy_goodName.setText(intent.getStringExtra("goodname"));
		tvBuy_price = (TextView)findViewById(R.id.tvBuy_price);
		img_good = (ImageView)findViewById(R.id.img_good);
		IvBuy_rb =(ImageView) findViewById(R.id.IvBuy_rb);
		real_price = (TextView)findViewById(R.id.real_price);
		origin_price = (TextView)findViewById(R.id.origin_price);
		real_price.setText(String.valueOf(good.getPrice()));
		origin_price.setText(String.valueOf(good.getOld_price()));
		tvBuy_price.setText(intent.getStringExtra("goodprice"));
		TvBuy_phone = (TextView)findViewById(R.id.TvBuy_phone);
		TvBuy_phone.setText(user.getPhone().toString());
		btn_buy = (Button) findViewById(R.id.btn_buy);
		SetView(img_good,good.getObjectId()+"_0image.png");
		btn_buy.setOnClickListener(this);
		TvBuy_phone.setOnClickListener(this);
		IvBuy_rb.setOnClickListener(this);
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
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.IvBuy_rb:
			finish();
		case R.id.TvBuy_phone:
			final EditDialog.Builder editbuilder = new EditDialog.Builder(Buy.this);
			editbuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface builder, int which) {
					builder.dismiss();
					if(editbuilder.getEdit().isEmpty()){
						Toast.makeText(Buy.this, "电话号码不得为空!", Toast.LENGTH_SHORT).show();
					}else{
						TvBuy_phone.setText(editbuilder.getEdit().toString());
					}
				}
			});
			editbuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface builder, int which) {
					builder.dismiss();
				}
			});
			editbuilder.create().show();
			break;
		case R.id.btn_buy:
			order.setBuyer(user);
			order.setSeller(seller);
			order.setPrice(Float.valueOf(tvBuy_price.getText().toString()));
			order.setGood(good);
			order.setPhone(TvBuy_phone.getText().toString());
			order.setGoodname(tvBuy_goodName.getText().toString());
			order.save(new SaveListener<String>(){
				@Override
				public void done(String objectId, BmobException e) {
					if(e == null){
						delGood();
					}else{
						Toast.makeText(Buy.this, "提交订单失败", Toast.LENGTH_SHORT).show();
						Log.i("buy_order","fail:"+e.getMessage());
					}
				}
			});
			finish();
			break;
		}
	}
	void delGood(){
		good.setType(13);
		good.update(new UpdateListener() {

		    @Override
		    public void done(BmobException e) {
		        if(e==null){
		        	Toast.makeText(Buy.this, "提交订单成功", Toast.LENGTH_SHORT).show();
		            Log.i("bmob","成功");
		        }else{
		            Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
		        }
		    }
		});
	}
	 void toast(String s){
			Toast.makeText(this, s, Toast.LENGTH_LONG).show();
		}
}
