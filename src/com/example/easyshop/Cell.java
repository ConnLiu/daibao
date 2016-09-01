package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

import com.example.assist.CelllistAdapter;
import com.example.entity.CellInfo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;

public class Cell extends Activity implements OnClickListener{

	private ImageButton IbCell_message,IbCell_add,IbCell_home,IbCell_mine,IbCell_search;
    private ListView LvCell_cell;
	private TextView TvCell_topfind,TvCell_topmine;
	private String objectId[] = {"9VvS4445"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cell);
        Bmob.initialize(this, "79f7c1d79f0db04370bf7b20720440db");
		
		LvCell_cell = (ListView)findViewById(R.id.LvCell_cell);
		TvCell_topfind = (TextView)findViewById(R.id.TvCell_topfind);
		TvCell_topmine = (TextView)findViewById(R.id.TvCell_topmine);
	    IbCell_add =(ImageButton) findViewById(R.id.IbCell_add);
	    IbCell_home =(ImageButton) findViewById(R.id.IbCell_home);
	    IbCell_mine =(ImageButton) findViewById(R.id.IbCell_mine);
        IbCell_message =(ImageButton) findViewById(R.id.IbCell_message);
        IbCell_search =(ImageButton) findViewById(R.id.IbCell_search);

        IbCell_add.setOnClickListener(this);
        IbCell_home.setOnClickListener(this);
        IbCell_mine.setOnClickListener(this);
        IbCell_message.setOnClickListener(this);
		TvCell_topfind.setOnClickListener(this);
		TvCell_topmine.setOnClickListener(this);
		IbCell_search.setOnClickListener(this);
		getDataFind();
		
	}
	
    OnItemClickListener itemListener = new OnItemClickListener() {  
        
        public void onItemClick(AdapterView<?> parent, View view, int position,  
                long id) {  
            // 这里的view是我们在list.xml中定义的LinearLayout对象.  
            // 所以可以通过findViewById方法可以找到list.xml中定义的它的子对象,如下: 
        	Intent intent = new Intent();
        	intent.setClass(Cell.this, CellDetail.class);
        	intent.putExtra("position", position);
        	startActivity(intent);
        }
    };  
    final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private void getDataFind() {
		
		
		BmobQuery<CellInfo> c = new BmobQuery<CellInfo>();
		c.setLimit(20);
		c.findObjects(new FindListener<CellInfo>() {
			
			@Override
			public void done(List<CellInfo> object, BmobException e) {
				if(e==null){
					for(CellInfo ci : object){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("cellname", ci.getName().toString());
						map.put("publishnum", ci.getPublish().toString());
						map.put("peoplenum", ci.getPeople().toString());
						map.put("cellimage", R.drawable.tip_selected);
						list.add(map);
					}
					CelllistAdapter celllistadapter = new CelllistAdapter(Cell.this,list);
					LvCell_cell.setAdapter(celllistadapter);
					LvCell_cell.setDividerHeight(0);
					LvCell_cell.setOnItemClickListener(itemListener);
				}else{
					Log.i("bmob", "failed");
				}
			}
		});
		
	}
	private List<Map<String,Object>> getDataMine() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i = 0 ; i <4; i ++ )
		{
			Map<String, Object> map = new HashMap<String, Object>();
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
		CelllistAdapter celllistadapter = new CelllistAdapter(this, list);
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
    	case R.id.IbCell_search:
    		intent.setClass(Cell.this, SearchCell.class);
    		startActivity(intent);
    		break;
    	default:
    		break;
		}
		
	}
}
