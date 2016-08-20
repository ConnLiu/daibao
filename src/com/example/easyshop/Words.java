package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.WordslistAdapter;
import com.example.assist.ZanslistAdapter;
import com.example.customview.ListViewForScrollView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Words extends Activity{
	private ListViewForScrollView LvWords_list;
	private ImageView IvSet_rb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.words);
		LvWords_list = (ListViewForScrollView)findViewById(R.id.LvWord_list);
		IvSet_rb = (ImageView)findViewById(R.id.IvSet_rb);
		IvSet_rb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		WordslistAdapter wordslistadapter = new WordslistAdapter(this, getData());
		LvWords_list.setAdapter(wordslistadapter);
		LvWords_list.setDividerHeight(0);
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
			
			map.put("userName", "Davi"+" 给我留言");
			map.put("content", "你的这个宝贝真不错啊！真不错啊！真不错啊！真不错啊！真不错啊！真不错啊！真不错啊！真不错啊！真不错啊！");
			map.put("time", "2016-10-10-12:00");
			map.put("goodsname", "特别特别大的书");
			map.put("goodsmoney", "20");
			map.put("goodslikenum", "99");
			list.add(map);
		}
		return list;
	}
}
