package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import com.example.assist.CelllistAdapter;
import com.example.assist.GoodslistAdapter;
import com.example.entity.CellInfo;
import com.example.singleton.CellSingleton;
import com.example.singleton.GoodsSingleton;
import com.example.singleton.MyCellSingleton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;

public class Cell extends Activity implements OnClickListener{

	private ImageButton IbCell_message,IbCell_add,IbCell_home,IbCell_mine,IbCell_search;
    private ListView LvCell_cell;
	private TextView TvCell_topfind,TvCell_topmine;
	
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
		LvCell_cell.setDividerHeight(0);
		LvCell_cell.setOnItemClickListener(itemListener);
        //=====================================================================
        if(CellSingleton.getInstance()!=null) 	//判断是否已经保存了数据
        {
			CelllistAdapter celllistadapter = new CelllistAdapter(Cell.this,CellSingleton.getInstance());
			LvCell_cell.setAdapter(celllistadapter);
        }else{
        	getDataFind();
			Log.i("cellsingleton", "null");
        }
        //=================================================
	}
	
    OnItemClickListener itemListener = new OnItemClickListener() {  
        
        public void onItemClick(AdapterView<?> parent, View view, int position,  
                long id) {  
            // 这里的view是我们在list.xml中定义的LinearLayout对象.  
            // 所以可以通过findViewById方法可以找到list.xml中定义的它的子对象,如下: 
        	Intent intent = new Intent();
        	intent.setClass(Cell.this, CellDetail.class);
        	intent.putExtra("id",list.get(position).get("objectId").toString());
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
					CelllistAdapter celllistadapter = new CelllistAdapter(Cell.this,object);
					LvCell_cell.setAdapter(celllistadapter);
					CellSingleton.setInstance(object);
				}else{
					Log.i("bmob", "failed");
				}
			}
		});
	}

	private void getDataMine() {
		list.clear();
		BmobQuery<CellInfo> c = new BmobQuery<CellInfo>();
		c.setLimit(20);
		
		c.findObjects(new FindListener<CellInfo>() {
			
			@Override
			public void done(List<CellInfo> object, BmobException e) {
				if(e==null){
					CelllistAdapter celllistadapter = new CelllistAdapter(Cell.this,object);
					LvCell_cell.setAdapter(celllistadapter);
					MyCellSingleton.setInstance(object);
				}else{
					Log.i("bmob", "failed");
				}
			}
		});
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
		TvCell_topmine.setBackgroundResource(R.drawable.rightred_bg);
		TvCell_topfind.setBackgroundResource(R.drawable.leftwhite_bg);
        if(CellSingleton.getInstance()!=null) 	//判断是否已经保存了数据
        {
			CelllistAdapter celllistadapter = new CelllistAdapter(Cell.this,CellSingleton.getInstance());
			LvCell_cell.setAdapter(celllistadapter);
        }else{
        	getDataFind();
			Log.i("cellsingleton", "null");
        }
		break;
		
		case R.id.TvCell_topmine: 
		if (whitecsl != null && backcsl != null) {  
			TvCell_topfind.setTextColor(whitecsl); 
			TvCell_topmine.setTextColor(backcsl);   
		}  
		TvCell_topfind.setBackgroundResource(R.drawable.leftred_bg);
		TvCell_topmine.setBackgroundResource(R.drawable.rightwhite_bg);
        if(MyCellSingleton.getInstance()!=null) 	//判断是否已经保存了数据
        {
			CelllistAdapter listadapter = new CelllistAdapter(Cell.this,MyCellSingleton.getInstance());
			LvCell_cell.setAdapter(listadapter);
        }else{
        	getDataMine();
			Log.i("cellsingleton", "null");
        }
		break;
    	case R.id.IbCell_message:
    		intent.setClass(Cell.this, Message.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		overridePendingTransition(R.anim.fast_fade, R.anim.hold);
    		break;
    	case R.id.IbCell_add:
    		intent.setClass(Cell.this, Add.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbCell_home:
    		intent.setClass(Cell.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		overridePendingTransition(R.anim.fast_fade, R.anim.hold);
    		break;
    	case R.id.IbCell_mine:
    		intent.setClass(Cell.this, Mine.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		overridePendingTransition(R.anim.fast_fade, R.anim.hold);
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
