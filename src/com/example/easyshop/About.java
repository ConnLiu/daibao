package com.example.easyshop;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.app.Activity;

public class About extends Activity implements OnClickListener{

	private ImageView IvAbout_rb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		IvAbout_rb =(ImageView) findViewById(R.id.IvAbout_rb);
		
		IvAbout_rb.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.IvAbout_rb:
			finish();
			break;
		}
	}

}
