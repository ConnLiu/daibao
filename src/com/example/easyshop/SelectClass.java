package com.example.easyshop;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class SelectClass extends Activity implements OnClickListener{

	private ImageView Iv_class1,Iv_class2,Iv_class3,Iv_class4,Iv_class5,Iv_class6,Iv_class7,Iv_class8,Iv_class9,Iv_class10,Iv_class11,Iv_class12;
	private ImageView IvSelectc_rb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category);
		
		Iv_class1 = (ImageView)findViewById(R.id.Iv_class1);
		Iv_class2 = (ImageView)findViewById(R.id.Iv_class2);
		Iv_class3 = (ImageView)findViewById(R.id.Iv_class3);
		Iv_class4 = (ImageView)findViewById(R.id.Iv_class4);
		Iv_class5 = (ImageView)findViewById(R.id.Iv_class5);
		Iv_class6 = (ImageView)findViewById(R.id.Iv_class6);
		Iv_class7 = (ImageView)findViewById(R.id.Iv_class7);
		Iv_class8 = (ImageView)findViewById(R.id.Iv_class8);
		Iv_class9 = (ImageView)findViewById(R.id.Iv_class9);
		Iv_class10 = (ImageView)findViewById(R.id.Iv_class10);
		Iv_class11 = (ImageView)findViewById(R.id.Iv_class11);
		Iv_class12 = (ImageView)findViewById(R.id.Iv_class12);
		IvSelectc_rb = (ImageView)findViewById(R.id.IvCategory_rb);
		
		Iv_class1.setOnClickListener(this);
		Iv_class2.setOnClickListener(this);
		Iv_class3.setOnClickListener(this);
		Iv_class4.setOnClickListener(this);
		Iv_class5.setOnClickListener(this);
		Iv_class6.setOnClickListener(this);
		Iv_class7.setOnClickListener(this);
		Iv_class8.setOnClickListener(this);
		Iv_class9.setOnClickListener(this);
		Iv_class10.setOnClickListener(this);
		Iv_class11.setOnClickListener(this);
		Iv_class12.setOnClickListener(this);
		IvSelectc_rb.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.IvCategory_rb:
			finish();
			break;
		case R.id.Iv_class1:
			setResult(1);
			finish();
			break;
		case R.id.Iv_class2:
			setResult(2);
			finish();
			break;
		case R.id.Iv_class3:
			setResult(3);
			finish();
			break;
		case R.id.Iv_class4:
			setResult(4);
			finish();
			break;
		case R.id.Iv_class5:
			setResult(5);
			finish();
			break;
		case R.id.Iv_class6:
			setResult(6);
			finish();
			break;
		case R.id.Iv_class7:
			setResult(7);
			finish();
			break;
		case R.id.Iv_class8:
			setResult(8);
			finish();
			break;
		case R.id.Iv_class9:
			setResult(9);
			finish();
			break;
		case R.id.Iv_class10:
			setResult(10);
			finish();
			break;
		case R.id.Iv_class11:
			setResult(11);
			finish();
			break;
		case R.id.Iv_class12:
			setResult(12);
			finish();
			break;
		}
		
	}

}
