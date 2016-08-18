package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.CelllistAdapter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;

public class Cell extends Activity implements OnClickListener{

	private ImageButton IbCell_message,IbCell_add,IbCell_home,IbCell_mine;
    private ListView LvCell_cell;
	private TextView TvCell_topfind,TvCell_topmine;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cell);
		
		LvCell_cell = (ListView)findViewById(R.id.LvCell_cell);
		TvCell_topfind = (TextView)findViewById(R.id.TvCell_topfind);
		TvCell_topmine = (TextView)findViewById(R.id.TvCell_topmine);
	    IbCell_add =(ImageButton) findViewById(R.id.IbCell_add);
	    IbCell_home =(ImageButton) findViewById(R.id.IbCell_home);
	    IbCell_mine =(ImageButton) findViewById(R.id.IbCell_mine);
        IbCell_message =(ImageButton) findViewById(R.id.IbCell_message);

        IbCell_add.setOnClickListener(this);
        IbCell_home.setOnClickListener(this);
        IbCell_mine.setOnClickListener(this);
        IbCell_message.setOnClickListener(this);
		TvCell_topfind.setOnClickListener(this);
		TvCell_topmine.setOnClickListener(this);
		CelllistAdapter celllistadapter = new CelllistAdapter(this, getDataFind());
		LvCell_cell.setAdapter(celllistadapter);
	}

	private List<Map<String,Object>> getDataFind() {
		
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
			
			map.put("cellname", "lalala");
			map.put("publishnum", "234");
			map.put("peoplenum", "88");
			map.put("cellimage", R.drawable.tip_selected);
			list.add(map);
		}
		return list;
	}
	private List<Map<String,Object>> getDataMine() {
		
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
			
			map.put("cellname", "lololo");
			map.put("publishnum", "555");
			map.put("peoplenum", "99");
			map.put("cellimage", R.drawable.tip_normal);
			list.add(map);
		}
		return list;
	}
	  

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
    	Intent intent = new Intent();
		Resources resource = (Resources) getBaseContext().getResources(); 
		ColorStateList whitecsl = (ColorStateList) resource.getColorStateList(R.color.white);
		ColorStateList backcsl = (ColorStateList) resource.getColorStateList(R.color.home); 
		switch(v.getId()){
		case R.id.TvCell_topfind:  
		if (whitecsl != null && backcsl != null) {  
			TvCell_topmine.setTextColor(whitecsl);  
			TvCell_topfind.setTextColor(backcsl); 
		}  
		TvCell_topmine.setBackgroundResource(R.drawable.hometop_bg);
		TvCell_topfind.setBackgroundResource(R.color.white);
		CelllistAdapter celllistadapter = new CelllistAdapter(this, getDataFind());
		LvCell_cell.setAdapter(celllistadapter);
		break;
		case R.id.TvCell_topmine: 
		if (whitecsl != null && backcsl != null) {  
			TvCell_topfind.setTextColor(whitecsl); 
			TvCell_topmine.setTextColor(backcsl);   
		}  
		TvCell_topfind.setBackgroundResource(R.drawable.hometop_bg);
		TvCell_topmine.setBackgroundResource(R.color.white);
		celllistadapter = new CelllistAdapter(this, getDataMine());
		LvCell_cell.setAdapter(celllistadapter);
		break;
    	case R.id.IbCell_message:
    		intent.setClass(Cell.this, Message.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbCell_add:
    		intent.setClass(Cell.this, Add.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbCell_home:
    		intent.setClass(Cell.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbCell_mine:
    		intent.setClass(Cell.this, Mine.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	default:
    		break;
		}
		
	}
}
