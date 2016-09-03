package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.GoodslistAdapter;
import com.example.customview.ListViewForScrollView;
import com.example.singleton.GoodsSingleton;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;

public class Search extends Activity implements OnClickListener{

	private ImageView Ivsearch_return,IvDeleteText;
	private EditText EtSearch_content;
	private TextView TvSearch;
	private ListViewForScrollView LvSearch;
	private String content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		
		Ivsearch_return =(ImageView) findViewById(R.id.Ivsearch_return);
		IvDeleteText =(ImageView) findViewById(R.id.IvDeleteText);
		EtSearch_content =(EditText) findViewById(R.id.EtSearch_content);
		TvSearch =(TextView) findViewById(R.id.TvSearch);
		LvSearch =(ListViewForScrollView) findViewById(R.id.LvSearch);
		
		Ivsearch_return.setOnClickListener(this);
		
		EtSearch_content.addTextChangedListener(new TextWatcher() {  
            
          public void onTextChanged(CharSequence s, int start, int before, int count) {  
              // TODO Auto-generated method stub  
                
          }  
            
          public void beforeTextChanged(CharSequence s, int start, int count,  
                  int after) {  
              // TODO Auto-generated method stub  
                
          }  
            
          public void afterTextChanged(Editable s) {  
      		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
      		
      		Map<String, Object> map = new HashMap<String, Object>();
  			if (s.length() == 0) {  
  				IvDeleteText.setVisibility(View.GONE);  
              } 
  			else{
  				IvDeleteText.setVisibility(View.VISIBLE);
  				content = EtSearch_content.getText().toString();
  				int j = 0;
          		for(int i = 0 ; i < 10; i ++ ) //
          		{
          			
          			if(true){
	            			map = new HashMap<String, Object>();
	            			map.put("goodsname", "ÉÌÆ·Ãû³Æ");
	            			map.put("goodsmoney", "0.00");
	            			map.put("goodslikenum", "88");
	            			map.put("goodsimage", R.drawable.tip_selected);
	            			map.put("goodslike", R.drawable.zan);
	            			list.add(map);
          			}
          		}
          		GoodslistAdapter listviewadapter = new GoodslistAdapter(Search.this, GoodsSingleton.getInstance());
      			LvSearch.setAdapter(listviewadapter);
  			}
          }  
      });
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.Ivsearch_return:
			finish();
		case R.id.IvDeleteText:
			EtSearch_content.setText("");
			break;
		}
	}
}
