package com.example.easyshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;
import com.example.assist.CommentlistAdapter;
import com.example.customview.ListViewForScrollView;
import com.example.entity.Goods;
import com.example.entity.MyUser;
import com.example.singleton.GoodsSingleton;
import com.example.singleton.UserSingleton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GoodsDetail extends Activity implements OnClickListener{

	private ListViewForScrollView LvGoodsDetail;
	private ScrollView SvGoodsDetail;
	private ImageView IvGoodsDetail_rb,IvGoodsDetail_head,IvGoodsDetail_iv1,IvGoodsDetail_iv2,IvGoodsDetail_iv3,IvGoodsDetail_iv4;
	private TextView TvGoodsDetail_newp,TvGoodsDetail_ownername,TvGoodsDetail_rank;
	private TextView TvGoods_name;
	private TextView TvGoodsDetail_oldp,TvGoodsDetail_want;
	private Goods good;
	private TextView tv_goodsintro;	
	MyUser user = UserSingleton.getInstance();
	MyUser owner = null ;
	String AUTHOR_IMAGE_NAME;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_detail);
		int position = getIntent().getIntExtra("position",0);
		//Log.d("gooddetail","come:"+position);
		good = GoodsSingleton.getInstance().get(position);
		owner = good.getAuthor();
		
		// init the author pic
		AUTHOR_IMAGE_NAME = owner.getObjectId()+"_temphead.png";
		FileInputStream localstream = null;
		try {
			localstream = openFileInput(AUTHOR_IMAGE_NAME);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IvGoodsDetail_head = (ImageView)findViewById(R.id.IvGoodsDetail_head);
		Bitmap bm = BitmapFactory.decodeStream(localstream);
		if(bm != null){
			IvGoodsDetail_head.setImageBitmap(bm);
		}
		else download_pic();
		//Log.d("gooddetail","goods_name"+good.getName()+"  owner:"+owner+"  owner:"+owner.getGender());
		init_pic();  //init the good's four pic
		TvGoodsDetail_newp = (TextView) findViewById(R.id.TvGoodsDetail_newp);
		TvGoodsDetail_newp.setText(String.valueOf(good.getPrice()));
		TvGoodsDetail_oldp = (TextView) findViewById(R.id.TvGoodsDetail_oldp);
		TvGoodsDetail_ownername = (TextView) findViewById(R.id.TvGoodsDetail_ownername);
		TvGoodsDetail_rank = (TextView) findViewById(R.id.TvGoodsDetail_rank);
		TvGoodsDetail_ownername.setText(owner.getNick());
		TvGoodsDetail_rank.setText("V"+owner.getGrade());
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
	void init_pic(){
		IvGoodsDetail_iv1 = (ImageView)findViewById(R.id.IvGoodsDetail_iv1);
		IvGoodsDetail_iv2 = (ImageView)findViewById(R.id.IvGoodsDetail_iv2);
		IvGoodsDetail_iv3 = (ImageView)findViewById(R.id.IvGoodsDetail_iv3);
		IvGoodsDetail_iv4 = (ImageView)findViewById(R.id.IvGoodsDetail_iv4);
		int num_pic;
		String path[]=null;
		if(good.getHead_path()==null)
			num_pic=0;
		else{
			path = good.getHead_path().split(",");
			num_pic = path.length;
		}
			
		
		for(int i=0;i<num_pic;i++){
			switch(i){
			case 0:
				download(path[i],good.getObjectId()+"_0image.png",IvGoodsDetail_iv1);
				break;
			case 1:
				download(path[i],good.getObjectId()+"_1image.png",IvGoodsDetail_iv2);
				break;
			case 2:
				download(path[i],good.getObjectId()+"_2image.png",IvGoodsDetail_iv3);
				break;
			case 3:
				download(path[i],good.getObjectId()+"_3image.png",IvGoodsDetail_iv4);
				break;
			}
		}
		
		for(int i=num_pic;i<4;i++){
			switch(i){
			case 0:
				IvGoodsDetail_iv1.setVisibility(View.GONE);
				break;
			case 1:
				IvGoodsDetail_iv2.setVisibility(View.GONE);
				break;
			case 2:
				IvGoodsDetail_iv3.setVisibility(View.GONE);
				break;
			case 3:
				IvGoodsDetail_iv4.setVisibility(View.GONE);
				break;
			}
		}
	}
	void download(String path,String img_name,final ImageView img){
		final String file_name = img_name;
		FileInputStream localstream = null;
			try {
				localstream = openFileInput(file_name);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    final Bitmap bm = BitmapFactory.decodeStream(localstream);
	    if(bm != null){
	    	img.setImageBitmap(bm);
			return;
		}
		BmobFile file =new BmobFile(file_name,"",
				path);
		 //允许设置下载文件的存储路径，默认下载文件的目录为：context.getApplicationContext().getCacheDir()+"/bmob/"
	    File saveFile = new File(getFilesDir(), file.getFilename());
	    file.download(saveFile, new DownloadFileListener() {
	        @Override
	        public void onStart() {
//	            toast("开始下载...");
	        }
	        @Override
	        public void done(String savePath,cn.bmob.v3.exception.BmobException e) {
	            if(e==null){
	            	Log.i("download:",savePath);
	            	FileInputStream localstream = null;
	        		try {
	        			localstream = openFileInput(file_name);
	        		} catch (FileNotFoundException e1) {
	        			// TODO Auto-generated catch block
	        			e1.printStackTrace();
	        		} 
	                final Bitmap bm = BitmapFactory.decodeStream(localstream);
	                img.setImageBitmap(bm);
	                //toast("下载成功");
	            }else{
	//                toast("下载失败："+e.getErrorCode()+","+e.getMessage());
	            }
	        }
	        @Override
	        public void onProgress(Integer value, long newworkSpeed) {
	//            Log.i("bmob","下载进度："+value+","+newworkSpeed);
	        }
	    });
	}
	void download_pic(){
		if(owner.getHead_path()==null||owner.getHead_path().equals("")){
			return;
		}
		Log.i("path:",owner.getHead_path());
		BmobFile file =new BmobFile(owner.getObjectId()+"_temphead.png","",
				owner.getHead_path());
		 //允许设置下载文件的存储路径，默认下载文件的目录为：context.getApplicationContext().getCacheDir()+"/bmob/"
	    File saveFile = new File(getFilesDir(), file.getFilename());
	    file.download(saveFile, new DownloadFileListener() {
	        @Override
	        public void onStart() {
//	            toast("开始下载...");
	        }
	        @Override
	        public void done(String savePath,cn.bmob.v3.exception.BmobException e) {
	            if(e==null){
	            	Log.i("download:",savePath);
	            	FileInputStream localstream = null;
	        		try {
	        			localstream = openFileInput(AUTHOR_IMAGE_NAME);
	        		} catch (FileNotFoundException e1) {
	        			// TODO Auto-generated catch block
	        			e1.printStackTrace();
	        		}
	        		Bitmap bm = BitmapFactory.decodeStream(localstream);
	        		IvGoodsDetail_head.setImageBitmap(bm);
	        		
	                //toast("下载成功");
	            }else{
	//                toast("下载失败："+e.getErrorCode()+","+e.getMessage());
	            }
	        }
	        @Override
	        public void onProgress(Integer value, long newworkSpeed) {
	//            Log.i("bmob","下载进度："+value+","+newworkSpeed);
	        }
	    });
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
			break;
		case R.id.TvGoodsDetail_want:
			intent.setClass(GoodsDetail.this, Chat.class);
			intent.putExtra("goodname",TvGoods_name.getText().toString());
			intent.putExtra("price",TvGoodsDetail_newp.getText().toString());
			intent.putExtra("position",getIntent().getIntExtra("position",0));
			startActivity(intent);
			break;
		}
		
	}
}
