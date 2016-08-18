package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.GoodslistAdapter;
import com.example.assist.MessagelistAdapter;
import com.example.customview.ListViewForScrollView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ScrollView;

public class Message extends Activity implements OnClickListener{

	private ImageButton IbMessage_cell,IbMessage_add,IbMessage_home,IbMessage_mine;
    
	private ScrollView SvMessage;
	private ListViewForScrollView LvMessage_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);

		SvMessage = (ScrollView) findViewById(R.id.SvMessage);
		LvMessage_list = (ListViewForScrollView)findViewById(R.id.LvMessage_list);
	    IbMessage_add =(ImageButton) findViewById(R.id.IbMessage_add);
	    IbMessage_home =(ImageButton) findViewById(R.id.IbMessage_home);
	    IbMessage_mine =(ImageButton) findViewById(R.id.IbMessage_mine);
        IbMessage_cell =(ImageButton) findViewById(R.id.IbMessage_cell);

        SvMessage.smoothScrollTo(0, 0);
        IbMessage_add.setOnClickListener(this);
        IbMessage_home.setOnClickListener(this);
        IbMessage_mine.setOnClickListener(this);
        IbMessage_cell.setOnClickListener(this);
        
		MessagelistAdapter goodslistadapter = new MessagelistAdapter(this, getData());
		LvMessage_list.setAdapter(goodslistadapter);
		LvMessage_list.setDividerHeight(0);
	}


	private List<Map<String,Object>> getData() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i = 0 ; i <7; i ++ )
		{
			Map<String, Object> map = new HashMap<String, Object>();
			/*try {
				map.put("newstext",java.net.URLDecoder.decode(news_Info.get(i).getNewsTitle().toString(),"utf-8"));
				
				map.put("newsimage", java.net.URLDecoder.decode(news_Info.get(i).getPictureURL().toString(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			map.put("name", "Davi");
			map.put("time", "12:00");
			map.put("rank", "V4");
			map.put("comment", "ÄãºÃ");
			map.put("head", R.drawable.zan);
			list.add(map);
		}
		return list;
	}
	

	@Override
    public void onClick(View v) {
    	Intent intent = new Intent();
    	switch(v.getId()){
    	case R.id.IbMessage_cell:
    		intent.setClass(Message.this, Cell.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbMessage_add:
    		intent.setClass(Message.this, Add.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbMessage_home:
    		intent.setClass(Message.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbMessage_mine:
    		intent.setClass(Message.this, Mine.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	default:
    		break;
    	}
	}

}
