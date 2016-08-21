package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.BlacknameAdapter;
import com.example.assist.CommentlistAdapter;
import com.example.customview.AnswerDialog;
import com.example.customview.ConfirmDialog;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.DialogInterface;

public class BlackName extends Activity implements OnClickListener{

	private ImageView IvBlack_rb;
	private ListView LvBlack_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.black_name);
		
		LvBlack_list =(ListView) findViewById(R.id.LvBlack_list);
		IvBlack_rb =(ImageView) findViewById(R.id.IvBlack_rb);

		IvBlack_rb.setOnClickListener(this);
		LvBlack_list.setOnItemClickListener(listener);
		
		BlacknameAdapter listadapter = new BlacknameAdapter(this, getData());
		LvBlack_list.setDividerHeight(0);
		LvBlack_list.setAdapter(listadapter);
	}

	private List<Map<String,Object>> getData() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i = 0 ; i <4; i ++ )
		{
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("username", "username");
			map.put("time", "time");
			map.put("headimage", R.drawable.tip_selected);
			list.add(map);
		}
		return list;
	}

	OnItemClickListener listener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,  
                long id) {
			ConfirmDialog.Builder builder = new ConfirmDialog.Builder(BlackName.this);
			builder.setTitle("确定将此人移出黑名单？");
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		Toast.makeText(getApplicationContext(), "补充操作", Toast.LENGTH_SHORT).show();
            	}
            });
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            	}
            });
            builder.create().show();
		}
	};
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.IvBlack_rb:
			finish();
		}
	}
}
