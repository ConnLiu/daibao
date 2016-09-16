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
	private String classname[] = {"�鼮","��Ʒ","���","Ь��","��װ","��ױƷ","��ȯ","����Ʒ","����","�˶���Ʒ","С����","������Ʒ"};
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
	            // �����view��������list.xml�ж����LinearLayout����.  
	            // ���Կ���ͨ��findViewById���������ҵ�list.xml�ж���������Ӷ���,����: 
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
