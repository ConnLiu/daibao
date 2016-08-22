package com.example.easyshop;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;

public class Buy extends Activity {
	private Button btn_buy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy);
		btn_buy = (Button) findViewById(R.id.btn_buy);
		btn_buy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				toast("¹ºÂò³É¹¦!");
				finish();
			}
		});
	}
	 void toast(String s){
			Toast.makeText(this, s, Toast.LENGTH_LONG).show();
		}
}
