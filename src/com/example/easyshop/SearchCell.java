package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.CelllistAdapter;
import com.example.assist.GoodslistAdapter;
import com.example.customview.ListViewForScrollView;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchCell extends Activity implements OnClickListener{

	private ImageView IvSearchCell_return,IvSearchCell_DeleteText;
	private EditText EtSearchCell_content;
	private TextView TvSearchCell;
	private ListViewForScrollView LvSearchCell;
	private String content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_cell);
		
		IvSearchCell_return =(ImageView) findViewById(R.id.IvSearchCell_return);
		IvSearchCell_DeleteText =(ImageView) findViewById(R.id.IvSearchCell_DeleteText);
		EtSearchCell_content =(EditText) findViewById(R.id.EtSearchCell_content);
		TvSearchCell =(TextView) findViewById(R.id.TvSearchCell);
		LvSearchCell =(ListViewForScrollView) findViewById(R.id.LvSearchCell);
		
		IvSearchCell_return.setOnClickListener(this);
		
		EtSearchCell_content.addTextChangedListener(new TextWatcher() {  
            
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
  				IvSearchCell_DeleteText.setVisibility(View.GONE);  
              } 
  			else{
  				IvSearchCell_DeleteText.setVisibility(View.VISIBLE);
  				content = EtSearchCell_content.getText().toString();
  				int j = 0;
          		for(int i = 0 ; i < 10; i ++ ) //
          		{
          			
          			if(true){
	            			map = new HashMap<String, Object>();
	            			map.put("cellname", "lalala");
	            			map.put("publishnum", "234");
	            			map.put("peoplenum", "88");
	            			map.put("cellimage", R.drawable.tip_selected);
	            			list.add(map);
          			}
          		}
          		CelllistAdapter listviewadapter = new CelllistAdapter(SearchCell.this, list);
      			LvSearchCell.setAdapter(listviewadapter);
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
			EtSearchCell_content.setText("");
			break;
		}
	}
}
