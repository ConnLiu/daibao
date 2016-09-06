package com.example.easyshop;

import java.io.File;

import com.example.customview.GenderDialog;
import com.example.entity.MyUser;
import com.example.singleton.UserSingleton;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

public class Set extends Activity implements OnClickListener{

	private ImageView IvSet_rb;
	private Button Btlogout;
	private TextView TvSet_info,TvSet_dark,TvSet_quality,TvSet_alarm,TvSet_about,TvSet_rule,TvSet_cache;
	MyUser user = UserSingleton.getInstance();
	private final String IMAGE_FILE_NAME = user.getObjectId()+"_temphead.png";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);
		
		IvSet_rb =(ImageView) findViewById(R.id.IvSet_rb);
		TvSet_info =(TextView) findViewById(R.id.TvSet_info);
		TvSet_dark =(TextView) findViewById(R.id.TvSet_dark);
		TvSet_quality =(TextView) findViewById(R.id.TvSet_quality);
		TvSet_alarm =(TextView) findViewById(R.id.TvSet_alarm);
		TvSet_about =(TextView) findViewById(R.id.TvSet_about);
		TvSet_rule =(TextView) findViewById(R.id.TvSet_rule);
		TvSet_cache =(TextView) findViewById(R.id.TvSet_cache);
		Btlogout =(Button) findViewById(R.id.Btlogout);
		
		IvSet_rb.setOnClickListener(this);
		TvSet_info.setOnClickListener(this);
		TvSet_dark.setOnClickListener(this);
		TvSet_quality.setOnClickListener(this);
		TvSet_alarm.setOnClickListener(this);
		TvSet_about.setOnClickListener(this);
		TvSet_rule.setOnClickListener(this);
		TvSet_cache.setOnClickListener(this);
		Btlogout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch(v.getId()){
		case R.id.IvSet_rb:
			intent.setClass(Set.this, Mine.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		case R.id.TvSet_info:
			intent.setClass(Set.this, Myinfo.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		case R.id.TvSet_dark:
			intent.setClass(Set.this, BlackName.class);
			startActivity(intent);
			break;
		case R.id.TvSet_quality:
			GenderDialog.Builder builder1=new GenderDialog.Builder(Set.this);
            builder1.setNegativeButton("低",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
        			Toast.makeText(getApplicationContext(), "低", Toast.LENGTH_SHORT).show();
            	}
            });
            builder1.setPositiveButton("高",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
        			Toast.makeText(getApplicationContext(), "高", Toast.LENGTH_SHORT).show();
            	}
            }); 
            builder1.create().show();
			break;
		case R.id.TvSet_alarm:
			GenderDialog.Builder builder=new GenderDialog.Builder(Set.this);
            builder.setNegativeButton("不提醒",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
        			Toast.makeText(getApplicationContext(), "不提醒", Toast.LENGTH_SHORT).show();
            	}
            });
            builder.setPositiveButton("提醒",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
        			Toast.makeText(getApplicationContext(), "提醒", Toast.LENGTH_SHORT).show();
            	}
            }); 
            builder.create().show();
            
			break;
		case R.id.TvSet_about:
			intent.setClass(Set.this, About.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		case R.id.TvSet_rule:
			Toast.makeText(getApplicationContext(), "请补充", Toast.LENGTH_SHORT).show();
			break;
		case R.id.TvSet_cache:
			Toast.makeText(getApplicationContext(), "清除缓存成功", Toast.LENGTH_SHORT).show();
			break;
		case R.id.Btlogout:
			Toast.makeText(getApplicationContext(), "退出登录", Toast.LENGTH_SHORT).show();
			File file = new File(IMAGE_FILE_NAME);
			file.delete();
			SharedPreferences sharedPreferences = getSharedPreferences("login", 0);
			SharedPreferences.Editor  editor  =  sharedPreferences.edit();
			editor.putString("validate","0");
			editor.commit();
			UserSingleton.setInstance(null);
			intent.setClass(Set.this, Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		}
	}
}
