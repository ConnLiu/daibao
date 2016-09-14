package com.example.easyshop;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import com.example.customview.EditDialog;
import com.example.entity.MyUser;
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

public class Buy extends Activity implements OnClickListener{
	private Button btn_buy;
	private TextView TvBuy_phone,tvBuy_goodName,tvBuy_price;
	private MyUser user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy);
		Intent intent = getIntent();
		user = UserSingleton.getInstance();

		tvBuy_goodName =(TextView) findViewById(R.id.tvBuy_goodName);
		tvBuy_goodName.setText(intent.getStringExtra("goodname"));
		tvBuy_price = (TextView)findViewById(R.id.tvBuy_price);
		tvBuy_price.setText(intent.getStringExtra("goodprice"));
		TvBuy_phone = (TextView)findViewById(R.id.TvBuy_phone);
		TvBuy_phone.setText(user.getPhone().toString());
		btn_buy = (Button) findViewById(R.id.btn_buy);
		
		btn_buy.setOnClickListener(this);
		TvBuy_phone.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.TvBuy_phone:
			final EditDialog.Builder editbuilder = new EditDialog.Builder(Buy.this);
			editbuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface builder, int which) {
					builder.dismiss();
					TvBuy_phone.setText(editbuilder.getEdit().toString());
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
			toast("购买成功!");
			finish();
			break;
		}
	}
		
	 void toast(String s){
			Toast.makeText(this, s, Toast.LENGTH_LONG).show();
		}
}
