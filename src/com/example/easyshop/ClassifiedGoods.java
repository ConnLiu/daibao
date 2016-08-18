package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.GoodslistAdapter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class ClassifiedGoods extends Activity implements OnClickListener{

	private ListView LvClassified;
	private TextView TvClassfied_class;
	private ImageView IvClass_rb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classified_goods);
		//Intent intent = new Intent();
		
		LvClassified = (ListView)findViewById(R.id.LvClassified);
		TvClassfied_class =(TextView) findViewById(R.id.TvClassfied_class);
		IvClass_rb =(ImageView) findViewById(R.id.IvClass_rb);
		
		IvClass_rb.setOnClickListener(this);
		
		GoodslistAdapter goodslistadapter = new GoodslistAdapter(this, getData());
		LvClassified.setAdapter(goodslistadapter);
		TvClassfied_class.setText(getIntent().getExtras().getString("classname"));
	}

		private List<Map<String,Object>> getData() {
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			for(int i = 0 ; i <4; i ++ )
			{
				Map<String, Object> map = new HashMap<String, Object>();
				/*try {
					map.put("newstext",java.net.URLDecoder.decode(news_Info.get(i).getNewsTitle().toString(),"utf-8"));
					
					map.put("newsimage", java.net.URLDecoder.decode(news_Info.get(i).getPictureURL().toString(),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				map.put("goodsname", "ÉÌÆ·Ãû³Æ");
				map.put("goodsmoney", "0.00");
				map.put("goodslikenum", "88");
				map.put("goodsimage", R.drawable.tip_selected);
				map.put("goodslike", R.drawable.zan);
				list.add(map);
			}
			return list;
		}
		 
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.IvClass_rb:
				finish();
				break;
			}
			
		}
}
