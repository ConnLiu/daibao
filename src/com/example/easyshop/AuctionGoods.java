package com.example.easyshop;

import com.example.customview.AddPriceDialog;
import com.example.customview.ConfirmDialog;
import com.example.customview.DomitoryDialog;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

public class AuctionGoods extends Activity implements OnClickListener{

	private int addprice = 0;
	private ImageView IvAuctionGoods_head,IvAuctionGoods_rb;
	private TextView TvAuctionGoods_name,TvAuctionGoods_rank,TvAuctionGoods_oldp,TvAuctionGoods_newp,TvAuctionGoods_title,tv_AuctionGoodsintro,TvAuctionGoods_wantuser,TvAuctionGoods_want;
	private ImageView IvAuctionGoods_iv1,IvAuctionGoods_iv2,IvAuctionGoods_iv3,IvAuctionGoods_iv4,IvAuctionGoods_wantuser,IvAuctionGoods_zan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auction_goods);
		Intent intent = getIntent();
		
		IvAuctionGoods_rb =(ImageView) findViewById(R.id.IvAuctionGoods_rb);
		IvAuctionGoods_head =(ImageView) findViewById(R.id.IvAuctionGoods_head);
		IvAuctionGoods_iv1 =(ImageView) findViewById(R.id.IvAuctionGoods_iv1);
		IvAuctionGoods_iv2 =(ImageView) findViewById(R.id.IvAuctionGoods_iv2);
		IvAuctionGoods_iv3 =(ImageView) findViewById(R.id.IvAuctionGoods_iv3);
		IvAuctionGoods_iv4 =(ImageView) findViewById(R.id.IvAuctionGoods_iv4);
		IvAuctionGoods_wantuser =(ImageView) findViewById(R.id.IvAuctionGoods_wantuser);
		IvAuctionGoods_zan =(ImageView) findViewById(R.id.IvAuctionGoods_zan);
		TvAuctionGoods_name =(TextView) findViewById(R.id.TvAuctionGoods_name);
		TvAuctionGoods_rank =(TextView) findViewById(R.id.TvAuctionGoods_rank);
		TvAuctionGoods_oldp =(TextView) findViewById(R.id.TvAuctionGoods_oldp);
		TvAuctionGoods_newp =(TextView) findViewById(R.id.TvAuctionGoods_newp);
		TvAuctionGoods_title =(TextView) findViewById(R.id.TvAuctionGoods_title);
		tv_AuctionGoodsintro =(TextView) findViewById(R.id.tv_AuctionGoodsintro);
		TvAuctionGoods_wantuser =(TextView) findViewById(R.id.TvAuctionGoods_wantuser);
		TvAuctionGoods_want =(TextView) findViewById(R.id.TvAuctionGoods_want);
		
		IvAuctionGoods_rb.setOnClickListener(this);
		IvAuctionGoods_head.setOnClickListener(this);
		IvAuctionGoods_iv1.setOnClickListener(this);
		IvAuctionGoods_iv2.setOnClickListener(this);
		IvAuctionGoods_iv3.setOnClickListener(this);
		IvAuctionGoods_iv4.setOnClickListener(this);
		IvAuctionGoods_wantuser.setOnClickListener(this);
		IvAuctionGoods_zan.setOnClickListener(this);
		if(intent.getIntExtra("state", 0)==2){
			TvAuctionGoods_want.setBackgroundResource(R.drawable.back_bg);
			TvAuctionGoods_want.setText("已结束");
		}
		else if(intent.getIntExtra("state", 0)==1){
			TvAuctionGoods_want.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.IvAuctionGoods_rb:
			finish();
		case R.id.IvAuctionGoods_head:
			break;
		case R.id.IvAuctionGoods_iv1:
			break;
		case R.id.IvAuctionGoods_iv2:
			break;
		case R.id.IvAuctionGoods_iv3:
			break;
		case R.id.IvAuctionGoods_iv4:
			break;
		case R.id.IvAuctionGoods_wantuser:
			break;
		case R.id.IvAuctionGoods_zan:
			IvAuctionGoods_zan.setImageResource(R.drawable.zan_red);
			break;
		case R.id.TvAuctionGoods_want:
			final ConfirmDialog.Builder cdialogbuilder = new ConfirmDialog.Builder(AuctionGoods.this);
			cdialogbuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
            		dialog.dismiss();
					addprice = 0;
				}
			});
			cdialogbuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
            		dialog.dismiss();
            		Toast.makeText(AuctionGoods.this, "此处添加信息提交"+addprice, Toast.LENGTH_SHORT).show();
				}
			});
			AddPriceDialog.Builder apbuilder=new AddPriceDialog.Builder(AuctionGoods.this);
			apbuilder.setPositiveButton(new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		addprice = 1;
        			cdialogbuilder.setTitle("确认加价"+addprice+"元？");
    				cdialogbuilder.create().show();
            	}
            });
			apbuilder.setNegativeButton(new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		addprice = 5;
        			cdialogbuilder.setTitle("确认加价"+addprice+"元？");
    				cdialogbuilder.create().show();
            	}
            });
			apbuilder.setButton10(new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		addprice = 10;
        			cdialogbuilder.setTitle("确认加价"+addprice+"元？");
    				cdialogbuilder.create().show();
            	}
            });
			apbuilder.setButton20(new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		addprice = 20;
        			cdialogbuilder.setTitle("确认加价"+addprice+"元？");
    				cdialogbuilder.create().show();
            	}
            });
			apbuilder.create().show();

			break;
		}
		
	}
}
