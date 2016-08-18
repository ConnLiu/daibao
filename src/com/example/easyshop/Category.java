package com.example.easyshop;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Category extends Activity implements OnClickListener{

	private ImageView Iv_class1,Iv_class2,Iv_class3,Iv_class4,Iv_class5,Iv_class6,Iv_class7,Iv_class8,Iv_class9,Iv_class10,Iv_class11,Iv_class12;
	private ImageView IvCategory_rb;
	private String classname[] = {"书籍","首饰","玩具","鞋包","服装","化妆品","卡券","工艺品","数码","运动用品","小电器","生活用品"};
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
		IvCategory_rb = (ImageView)findViewById(R.id.IvCategory_rb);
		
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
		IvCategory_rb.setOnClickListener(this);
	}
	
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	Intent intent = new Intent();
	Bundle bundle = new Bundle();
	switch(v.getId()){
	case R.id.IvCategory_rb:
		finish();
		break;
	case R.id.Iv_class1:
		intent.setClass(Category.this, ClassifiedGoods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putString("classname", classname[0]);
		intent.putExtras(bundle);
		startActivity(intent);
		break;
	case R.id.Iv_class2:
		intent.setClass(Category.this, ClassifiedGoods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putString("classname", classname[1]);
		intent.putExtras(bundle);
		startActivity(intent);
		break;
	case R.id.Iv_class3:
		intent.setClass(Category.this, ClassifiedGoods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putString("classname", classname[2]);
		intent.putExtras(bundle);
		startActivity(intent);
		break;
	case R.id.Iv_class4:
		intent.setClass(Category.this, ClassifiedGoods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putString("classname", classname[3]);
		intent.putExtras(bundle);
		startActivity(intent);
		break;
	case R.id.Iv_class5:
		intent.setClass(Category.this, ClassifiedGoods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putString("classname", classname[4]);
		intent.putExtras(bundle);
		startActivity(intent);
		break;
	case R.id.Iv_class6:
		intent.setClass(Category.this, ClassifiedGoods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putString("classname", classname[5]);
		intent.putExtras(bundle);
		startActivity(intent);
		break;
	case R.id.Iv_class7:
		intent.setClass(Category.this, ClassifiedGoods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putString("classname", classname[6]);
		intent.putExtras(bundle);
		startActivity(intent);
		break;
	case R.id.Iv_class8:
		intent.setClass(Category.this, ClassifiedGoods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putString("classname", classname[7]);
		intent.putExtras(bundle);
		startActivity(intent);
		break;
	case R.id.Iv_class9:
		intent.setClass(Category.this, ClassifiedGoods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putString("classname", classname[8]);
		intent.putExtras(bundle);
		startActivity(intent);
		break;
	case R.id.Iv_class10:
		intent.setClass(Category.this, ClassifiedGoods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putString("classname", classname[9]);
		intent.putExtras(bundle);
		startActivity(intent);
		break;
	case R.id.Iv_class11:
		intent.setClass(Category.this, ClassifiedGoods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putString("classname", classname[10]);
		intent.putExtras(bundle);
		startActivity(intent);
		break;
	case R.id.Iv_class12:
		intent.setClass(Category.this, ClassifiedGoods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putString("classname", classname[11]);
		intent.putExtras(bundle);
		startActivity(intent);
		break;
	}
	
}

}
