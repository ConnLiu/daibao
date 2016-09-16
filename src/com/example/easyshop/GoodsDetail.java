package com.example.easyshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.CelllistAdapter;
import com.example.assist.CommentlistAdapter;
import com.example.customview.ListViewForScrollView;
import com.example.entity.Goods;
import com.example.singleton.GoodsSingleton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class GoodsDetail extends Activity implements OnClickListener{

	private ListViewForScrollView LvGoodsDetail;
	private ScrollView SvGoodsDetail;
	private ImageView IvGoodsDetail_rb;
	private TextView TvGoodsDetail_newp;
	private TextView TvGoods_name;
	private TextView TvGoodsDetail_oldp,TvGoodsDetail_want;
	private Goods good;
	private TextView tv_goodsintro;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_detail);
		int position = getIntent().getIntExtra("position",0);
		Log.d("fenlei","come:"+position);
		good = GoodsSingleton.getInstance().get(position);
		Log.d("fenlei",""+good.getName());
		TvGoodsDetail_newp = (TextView) findViewById(R.id.TvGoodsDetail_newp);
		TvGoodsDetail_newp.setText(String.valueOf(good.getPrice()));
		TvGoodsDetail_oldp = (TextView) findViewById(R.id.TvGoodsDetail_oldp);
		TvGoodsDetail_oldp.setText(String.valueOf(good.getOld_price()));
		tv_goodsintro = (TextView) findViewById(R.id.tv_goodsintro);
		tv_goodsintro.setText(good.getIntro());
		TvGoods_name = (TextView) findViewById(R.id.TvGoods_name);
		TvGoods_name.setText(good.getName());
		LvGoodsDetail =(ListViewForScrollView) findViewById(R.id.LvGoodsDetail);
		SvGoodsDetail =(ScrollView) findViewById(R.id.SvGoodsDetail);
		IvGoodsDetail_rb =(ImageView) findViewById(R.id.IvGoodsDetail_rb);
		TvGoodsDetail_want =(TextView) findViewById(R.id.TvGoodsDetail_want);

		SvGoodsDetail.smoothScrollTo(0, 0);
		CommentlistAdapter listadapter = new CommentlistAdapter(this, getData());
		LvGoodsDetail.setDividerHeight(0);
		LvGoodsDetail.setAdapter(listadapter);
		
		IvGoodsDetail_rb.setOnClickListener(this);
		TvGoodsDetail_want.setOnClickListener(this);
	}

	private List<Map<String,Object>> getData() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i = 0 ; i <4; i ++ )
		{
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("username", "username");
			map.put("comment", "comment");
			map.put("time", "time");
			map.put("headimage", R.drawable.tip_selected);
			list.add(map);
		}
		return list;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch(v.getId()){
		case R.id.IvGoodsDetail_rb:
			finish();
		case R.id.TvGoodsDetail_want:
			intent.setClass(GoodsDetail.this, Chat.class);
			intent.putExtra("goodname",TvGoods_name.getText().toString());
			intent.putExtra("price",TvGoodsDetail_newp.getText().toString());
			intent.putExtra("position",getIntent().getIntExtra("position",0));
			startActivity(intent);
		}
		
	}
}
