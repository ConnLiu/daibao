package com.example.easyshop;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class Order extends Activity {
	private Button btn_cfm_get;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		btn_cfm_get = (Button) findViewById(R.id.btn_cfm_get);
		btn_cfm_get.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				toast("¹ºÂò³É¹¦!");
				finish();
				Intent intent = new Intent(Order.this,Order_com.class);
				startActivity(intent);
			}
		});
	}
	 void toast(String s){
			Toast.makeText(this, s, Toast.LENGTH_LONG).show();
		}
}
