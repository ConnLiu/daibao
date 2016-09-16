package com.example.easyshop;

import com.example.assist.GoodslistAdapter;
import com.example.singleton.GoodsSingleton;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;

public class ClassifiedGoods extends Activity implements OnClickListener{

	private ListView LvClassified;
	private TextView TvClassfied_class;
	private ImageView IvClass_rb,IvClassified_search;
	private String type;
	private String classname[] = {"书籍","饰品","玩具","鞋包","服装","化妆品","卡券","工艺品","数码","运动用品","小电器","生活用品"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classified_goods);
		//Intent intent = new Intent();
		
		LvClassified = (ListView)findViewById(R.id.LvClassified);
		TvClassfied_class =(TextView) findViewById(R.id.TvClassfied_class);
		IvClass_rb =(ImageView) findViewById(R.id.IvClass_rb);
		IvClassified_search =(ImageView) findViewById(R.id.IvClassified_search);
		
		IvClass_rb.setOnClickListener(this);
		IvClassified_search.setOnClickListener(this);
		LvClassified.setOnItemClickListener(itemListener);
		type=getIntent().getExtras().getString("type");
		GoodslistAdapter goodslistadapter = new GoodslistAdapter(this, GoodsSingleton.getTypeGoods(type));
		LvClassified.setAdapter(goodslistadapter);
		
		TvClassfied_class.setText(classname[Integer.valueOf(type)]);
		
	}
		

	    OnItemClickListener itemListener = new OnItemClickListener() {  
	        
	        public void onItemClick(AdapterView<?> parent, View view, int position,  
	                long id) {  
	            // 这里的view是我们在list.xml中定义的LinearLayout对象.  
	            // 所以可以通过findViewById方法可以找到list.xml中定义的它的子对象,如下: 
	        	Intent intent = new Intent();
	        	intent.setClass(ClassifiedGoods.this, GoodsDetail.class);
	        	intent.putExtra("position",GoodsSingleton.getCentGood(type, position));
	        	startActivity(intent);
	        }

	    }; 
	    
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			switch(v.getId()){
			case R.id.IvClass_rb:
				finish();
				break;
			case R.id.IvClassified_search:
				intent.setClass(ClassifiedGoods.this, Search.class);
				startActivity(intent);
				break;
			}
			
		}
}
