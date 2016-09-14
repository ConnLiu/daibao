package com.example.easyshop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.example.entity.MyUser;
import com.example.singleton.UserSingleton;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Mine extends Activity implements OnClickListener{

	private ImageButton IbMine_message,IbMine_add,IbMine_home,IbMine_cell;
	private ImageView IvMine_head;
	private TextView TvMine_motto,TvMine_name,TvMine_gold,TvMine_soldnum,TvMine_addnum,TvMine_boughtnum,TvMine_likenum;
	private TextView TvMine_sold,TvMine_add,TvMine_bought,TvMine_like,TvMine_auction,TvMine_help,TvMine_order,TvMine_set;
	private String minegoods[] = {"我发布的","我卖出的","我买到的","我赞过的","我的订单"};
	MyUser user = UserSingleton.getInstance();
	private final String IMAGE_FILE_NAME = user.getObjectId()+"_temphead.png";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine);
        FileInputStream localstream = null;
		try {
			localstream = openFileInput(IMAGE_FILE_NAME);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        final Bitmap bm = BitmapFactory.decodeStream(localstream);

	    IbMine_add =(ImageButton) findViewById(R.id.IbMine_add);
	    IbMine_home =(ImageButton) findViewById(R.id.IbMine_home);
	    IbMine_cell =(ImageButton) findViewById(R.id.IbMine_cell);
	    IbMine_message =(ImageButton) findViewById(R.id.IbMine_message);
	    TvMine_gold =(TextView) findViewById(R.id.TvMine_gold);
	    TvMine_soldnum =(TextView) findViewById(R.id.TvMine_soldnum);
	    TvMine_addnum =(TextView) findViewById(R.id.TvMine_addnum);
	    TvMine_boughtnum =(TextView) findViewById(R.id.TvMine_boughtnum);
	    TvMine_likenum =(TextView) findViewById(R.id.TvMine_likenum);
	    TvMine_sold =(TextView) findViewById(R.id.TvMine_sold);
	    TvMine_add =(TextView) findViewById(R.id.TvMine_add);
	    TvMine_bought =(TextView) findViewById(R.id.TvMine_bought);
	    TvMine_like =(TextView) findViewById(R.id.TvMine_like);
	    TvMine_auction =(TextView) findViewById(R.id.TvMine_auction);
	    TvMine_help =(TextView) findViewById(R.id.TvMine_help);
	    TvMine_order =(TextView) findViewById(R.id.TvMine_order);
	    TvMine_set =(TextView) findViewById(R.id.TvMine_set);
	    IvMine_head =(ImageView) findViewById(R.id.IvMine_head);
	    TvMine_motto =(TextView) findViewById(R.id.TvMine_motto);
	    TvMine_name =(TextView) findViewById(R.id.TvMine_name);

	    
	    TvMine_name.setText(user.getNick());
	    TvMine_motto.setText(user.getMotto());
        IbMine_add.setOnClickListener(this);
        IbMine_home.setOnClickListener(this);
        IbMine_cell.setOnClickListener(this);
        IbMine_message.setOnClickListener(this);
        TvMine_gold.setOnClickListener(this);
        TvMine_soldnum.setOnClickListener(this);
        TvMine_addnum.setOnClickListener(this);
        TvMine_boughtnum.setOnClickListener(this);
        TvMine_likenum.setOnClickListener(this);
        TvMine_sold.setOnClickListener(this);
        TvMine_add.setOnClickListener(this);
        TvMine_bought.setOnClickListener(this);
        TvMine_like.setOnClickListener(this);
        TvMine_auction.setOnClickListener(this);
        TvMine_help.setOnClickListener(this);
        TvMine_order.setOnClickListener(this);
        TvMine_set.setOnClickListener(this);
        IvMine_head.setOnClickListener(this);
        TvMine_name.setOnClickListener(this);
        TvMine_motto.setOnClickListener(this);

		if(bm != null){
			IvMine_head.setImageBitmap(bm);
		}
		else IvMine_head.setImageResource(R.drawable.tip_selected);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
    	Intent intent = new Intent();
    	Bundle bundle = new Bundle();
		switch(v.getId()){
    	case R.id.IbMine_message:
    		intent.setClass(Mine.this, Message.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		overridePendingTransition(R.anim.fast_fade, R.anim.hold);
    		break;
    	case R.id.IbMine_add:
    		if(!check_user())
    			return;
    		intent.setClass(Mine.this, Add.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbMine_home:
    		intent.setClass(Mine.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		intent.putExtra("from",	"other");
    		startActivity(intent);
    		overridePendingTransition(R.anim.fast_fade, R.anim.hold);
    		break;
    	case R.id.IbMine_cell:
    		intent.setClass(Mine.this, Cell.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		overridePendingTransition(R.anim.fast_fade, R.anim.hold);
    		break;
    	case R.id.TvMine_sold:
    		if(!check_user())
    			return;
    		intent.setClass(Mine.this, Mine_goods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		bundle.putString("minegoods", minegoods[1]);
    		intent.putExtras(bundle);
    		startActivity(intent);
    		break;
    	case R.id.TvMine_bought:
    		if(!check_user())
    			return;
    		intent.setClass(Mine.this, Mine_goods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		bundle.putString("minegoods", minegoods[2]);
    		intent.putExtras(bundle);
    		startActivity(intent);
    		break;
    	case R.id.TvMine_add:
    		if(!check_user())
    			return;
    		intent.setClass(Mine.this, Mine_goods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		bundle.putString("minegoods", minegoods[0]);
    		intent.putExtras(bundle);
    		startActivity(intent);
    		break;
    	case R.id.TvMine_like:
    		if(!check_user())
    			return;
    		intent.setClass(Mine.this, Mine_goods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		bundle.putString("minegoods", minegoods[3]);
    		intent.putExtras(bundle);
    		startActivity(intent);
    		break;
    	case R.id.TvMine_help:
    		intent.setClass(Mine.this, Help.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.TvMine_order:
    		intent.setClass(Mine.this, Mine_goods.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		bundle.putString("minegoods", minegoods[4]);
    		intent.putExtras(bundle);
            startActivity(intent);
    		break;
    	case R.id.TvMine_auction:
    		if(!check_user())
    			return;
    		intent.setClass(Mine.this, Auction.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.TvMine_set:
    		if(!check_user())
    			return;
    		intent.setClass(Mine.this, Set.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IvMine_head:
    	case R.id.TvMine_name:
    	case R.id.TvMine_motto:
    		if(user.getNick().equals("当前是游客登陆"))
    		{
    			intent.setClass(Mine.this, Login.class);
        		startActivity(intent);
        		finish();
    		}else{
	    		intent.setClass(Mine.this, Me.class);
	    		startActivity(intent);
    		}
    		break;
    	default:
    		break;
		}
		
	}
	public boolean check_user(){
		
		if(user.getNick().equals("当前是游客登陆")){
			  new AlertDialog.Builder(Mine.this).setTitle("系统提示")//设置对话框标题  		  
			     .setMessage("当前未登录，是否跳转到登陆页面？")//设置显示的内容  			  
			     .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮  		  
			         @Override  			  
			         public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件  			  
			             // TODO Auto-generated method stub  	
			        	 Intent intent = new Intent();
			        	 intent.setClass(Mine.this, Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			        	 startActivity(intent);	
			        	 finish();
			         }  		  
			     }).setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加返回按钮    
			         @Override  
			         public void onClick(DialogInterface dialog, int which) {//响应事件  
			             // TODO Auto-generated method stub  		  
			         }  
			     }).show();//在按键响应事件中显示此对话框 
			return false;
		}
		return true;
	}

}
