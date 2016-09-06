package com.example.easyshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import com.example.customview.EditDialog;
import com.example.entity.Goods;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Add extends Activity implements OnClickListener{

	private final String IMAGE_FILE_NAME = "image.png";
	private int IMAGECODE=0;
	private final int REQUESTCODE_PICK=0,REQUESTCODE_TAKE=1,REQUESTCODE_CUTTING=2,REQUESTCODE_CLASS=3;
	private String classname[] = {"书籍","首饰","玩具","鞋包","服装","化妆品","卡券","工艺品","数码","运动用品","小电器","生活用品"};
    private ImageView IvAdd_rb,IvAdd_image1,IvAdd_image2,IvAdd_image3,IvAdd_image4;
	private RadioButton RbAdd_price,RbAdd_auction,RbAdd_noprice;
	private Button Btn_cfmbuy;
	private EditText EtAdd_title,EtAdd_content;
	private TextView TvAdd_oldprice,TvAdd_price,TvAdd_class;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		
		IvAdd_rb =(ImageView) findViewById(R.id.IvAdd_rb);
		EtAdd_title = (EditText)findViewById(R.id.EtAdd_title);
		EtAdd_content = (EditText)findViewById(R.id.EtAdd_content);
		IvAdd_image1 =(ImageView) findViewById(R.id.IvAdd_image1);
		IvAdd_image2 =(ImageView) findViewById(R.id.IvAdd_image2);
		IvAdd_image3 =(ImageView) findViewById(R.id.IvAdd_image3);
		IvAdd_image4 =(ImageView) findViewById(R.id.IvAdd_image4);
		Btn_cfmbuy = (Button) findViewById(R.id.Btn_cfmbuy);
		RbAdd_price =(RadioButton) findViewById(R.id.RbAdd_price);
		RbAdd_auction =(RadioButton) findViewById(R.id.RbAdd_auction);
		RbAdd_noprice =(RadioButton) findViewById(R.id.RbAdd_noprice);
		TvAdd_oldprice =(TextView) findViewById(R.id.TvAdd_oldprice);
		TvAdd_price =(TextView) findViewById(R.id.TvAdd_price);
		TvAdd_class =(TextView) findViewById(R.id.TvAdd_class);
		
		IvAdd_rb.setOnClickListener(this);
		Btn_cfmbuy.setOnClickListener(this);
		RbAdd_price.setOnClickListener(this);
		RbAdd_auction.setOnClickListener(this);
		RbAdd_noprice.setOnClickListener(this);
		TvAdd_oldprice.setOnClickListener(this);
		TvAdd_price.setOnClickListener(this);
		TvAdd_class.setOnClickListener(this);
		IvAdd_image1.setOnClickListener(this);
		IvAdd_image2.setOnClickListener(this);
		IvAdd_image3.setOnClickListener(this);
		IvAdd_image4.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(Add.this, SelectClass.class);
		Resources resource = (Resources) getBaseContext().getResources(); 
		ColorStateList greycsl = (ColorStateList) resource.getColorStateList(R.color.lightgrey);
		ColorStateList backcsl = (ColorStateList) resource.getColorStateList(R.color.home); 
		switch(v.getId()){
		case R.id.Btn_cfmbuy:
			Goods good = new Goods();
			good.setName(EtAdd_title.getText().toString());
			good.setIntro(EtAdd_content.getText().toString());
			good.setPrice(Float.valueOf(TvAdd_oldprice.getText().toString()));
			good.setPrice(Float.valueOf(TvAdd_price.getText().toString()));
			good.save(new SaveListener<String>(){
				@Override
				public void done(String objectId, cn.bmob.v3.exception.BmobException e) {
					// TODO Auto-generated method stub
					if(e==null){
			            toast("发布成功！");
			            finish();
			        }else{
			            Log.i("bmob","发布失败："+e.getMessage()+","+e.getErrorCode());
			        }
				}
			});
			break;
		case R.id.IvAdd_rb:
			finish();
			break;
		case R.id.RbAdd_price:
			RbAdd_price.setChecked(true);
			RbAdd_price.setTextColor(backcsl);
			RbAdd_auction.setChecked(false);
			RbAdd_auction.setTextColor(greycsl);
			RbAdd_noprice.setChecked(false);
			RbAdd_noprice.setTextColor(greycsl);
			break;
		case R.id.RbAdd_auction:
			RbAdd_price.setChecked(false);
			RbAdd_price.setTextColor(greycsl);
			RbAdd_auction.setChecked(true);
			RbAdd_auction.setTextColor(backcsl);
			RbAdd_noprice.setChecked(false);
			RbAdd_noprice.setTextColor(greycsl);
			break;
		case R.id.RbAdd_noprice:
			RbAdd_price.setChecked(false);
			RbAdd_price.setTextColor(greycsl);
			RbAdd_auction.setChecked(false);
			RbAdd_auction.setTextColor(greycsl);
			RbAdd_noprice.setChecked(true);
			RbAdd_noprice.setTextColor(backcsl);
			break;
		case R.id.TvAdd_oldprice:
            final EditDialog.Builder builder1=new EditDialog.Builder(Add.this);
            builder1.setNegativeButton("取消",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvAdd_oldprice.setText("0");
            		Toast.makeText(getApplicationContext(), "未保存",Toast.LENGTH_SHORT).show();
            	}
            });
            builder1.setPositiveButton("确定",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvAdd_oldprice.setText(builder1.getEdit());
            		Toast.makeText(getApplicationContext(), builder1.getEdit()+"保存成功",Toast.LENGTH_SHORT).show();
            	}
            }); 
            builder1.create().show();
			break;
		case R.id.TvAdd_price:
            final EditDialog.Builder builder2=new EditDialog.Builder(Add.this);
            builder2.setNegativeButton("取消",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvAdd_oldprice.setText("0");
            		Toast.makeText(getApplicationContext(), "未保存",Toast.LENGTH_SHORT).show();
            	}
            });
            builder2.setPositiveButton("确定",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvAdd_price.setText(builder2.getEdit());
            		Toast.makeText(getApplicationContext(), builder2.getEdit()+"保存成功",Toast.LENGTH_SHORT).show();
            	}
            }); 
            builder2.create().show();
			break;
		case R.id.TvAdd_class:
			startActivityForResult(intent, REQUESTCODE_CLASS);
			break;
		case R.id.IvAdd_image1:
			if(IMAGECODE == 0){
				showPopupWindow(v);
			}
			if(IMAGECODE > 0){
				myshowDialog(v,0);
			}
			
			break;
		case R.id.IvAdd_image2:
			if(IMAGECODE == 1){
				showPopupWindow(v);
			}
			if(IMAGECODE > 1){
				myshowDialog(v,1);
			}
			break;
		case R.id.IvAdd_image3:
			if(IMAGECODE == 2){
				showPopupWindow(v);
			}
			if(IMAGECODE > 2){
				myshowDialog(v,2);
			}
			break;
		case R.id.IvAdd_image4:
			if(IMAGECODE == 3){
				showPopupWindow(v);
			}
			if(IMAGECODE > 3){
				myshowDialog(v,3);
			}
			break;
		}
	}
	private void myshowDialog(final View v,final int position){
//	    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(Add.this);
        //    设置Title的图标
        builder.setIcon(R.drawable.login);
        //    设置Title的内容
        builder.setTitle("提示");
        //    设置Content来显示一个信息
        builder.setMessage("确认删除该张图片？");
        //    设置一个PositiveButton
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            	Log.d("DialogInterface","position "+position+" IMAGECODE "+IMAGECODE);
            	for(int i=position+1;i<IMAGECODE;i++){
            		FileInputStream localstream = null;
            		try {
            			localstream = openFileInput(i+IMAGE_FILE_NAME);
            		} catch (FileNotFoundException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		} 
                    final Bitmap bm = BitmapFactory.decodeStream(localstream);
                    switch(i-1){
                    case 0:
                    	IvAdd_image1.setImageBitmap(bm);
                    	break;
                    case 1:
                    	IvAdd_image2.setImageBitmap(bm);
                    	break;
                    case 2:
                    	IvAdd_image3.setImageBitmap(bm);
                    	break;
                    
                    }
            	}
            	IMAGECODE--;
            	switch(IMAGECODE){
                case 0:
                	IvAdd_image1.setImageResource(R.drawable.add);
                	break;
                case 1:
                	IvAdd_image2.setImageResource(R.drawable.add);
                	break;
                case 2:
                	IvAdd_image3.setImageResource(R.drawable.add);
                	break;
                case 3:
                	IvAdd_image4.setImageResource(R.drawable.add);
                	break;
                }
            	switch(IMAGECODE+1){
                case 1:
                	IvAdd_image2.setVisibility(View.GONE);
                	break;
                case 2:
                	IvAdd_image3.setVisibility(View.GONE);
                	break;
                case 3:
                	IvAdd_image4.setVisibility(View.GONE);
                	break;
                }
            	
            }
        });
        //    设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                
            }
        });
       
        //    显示出该对话框
        builder.show();
	}
	private PopupWindow popupWindow;
	private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(Add.this).inflate(
                R.layout.headimage_popwindow, null);
        // 设置按钮的点击事件
        ImageView imageview1 = (ImageView) contentView.findViewById(R.id.imageView1);
        ImageView imageview2 = (ImageView) contentView.findViewById(R.id.imageView2);
        imageview1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //下面这句指定调用相机拍照后的照片存储的路径
                takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, 
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(),IMAGECODE+IMAGE_FILE_NAME)));
                startActivityForResult(takeIntent, REQUESTCODE_TAKE);
                popupWindow.dismiss();
            }
        });
        imageview2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                // 如果朋友们要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(pickIntent, REQUESTCODE_PICK);
                popupWindow.dismiss();
            }
        });
        popupWindow = new PopupWindow(contentView,
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.transparent));
        // 设置好参数之后再show
        popupWindow.showAsDropDown(view,-20,0);
        
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
	    case REQUESTCODE_PICK:// 直接从相册获取
	        try {
	            startPhotoZoom(data.getData());
	        } catch (NullPointerException e) {
	            //e.printStackTrace();// 用户点击取消操作
	        }
	        break;
	    case REQUESTCODE_TAKE:// 调用相机拍照
	        File temp = new File(Environment.getExternalStorageDirectory() , IMAGECODE+IMAGE_FILE_NAME);
	        startPhotoZoom(Uri.fromFile(temp));
	        break;
	    case REQUESTCODE_CUTTING:// 取得裁剪后的图片
	        if (data != null) {
	        	IMAGECODE++;
	            setPicToView(data);
	        }
	        break;
	    case REQUESTCODE_CLASS:
	    	TvAdd_class.setText(classname[resultCode-1]);
	    	break;
	    }
	    super.onActivityResult(requestCode, resultCode, data);
	}/**
	 * 裁剪图片方法实现
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
	    Intent intent = new Intent("com.android.camera.action.CROP");
	    intent.setDataAndType(uri, "image/*");
	    // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
	    intent.putExtra("crop", true);
	    // aspectX aspectY 是宽高的比例
	    intent.putExtra("aspectX", 1);
	    intent.putExtra("aspectY", 1);
	    // outputX outputY 是裁剪图片宽高
	    intent.putExtra("outputX", 300);
	    intent.putExtra("outputY", 300);
	    intent.putExtra("return-data", true);
	    startActivityForResult(intent, REQUESTCODE_CUTTING);
	}
	 /*    
	*//**
	 * 保存裁剪之后的图片数据
	 * @param picdata
	 */
	private void setPicToView(Intent picdata) {
	    Bundle extras = picdata.getExtras();
	    if (extras != null) {
	        // 取得SDCard图片路径做显示
	        Bitmap photo = extras.getParcelable("data");
	        Drawable drawable = new BitmapDrawable(null, photo);
	        FileOutputStream stream = null;
			try {
				stream = openFileOutput(IMAGECODE+IMAGE_FILE_NAME, 0);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
	        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
			switch(IMAGECODE){
			case 1:
				
				IvAdd_image1.setImageDrawable(drawable);
				IvAdd_image2.setVisibility(View.VISIBLE);
				IvAdd_image2.setImageResource(R.drawable.add);
	        break;
			case 2:
		        IvAdd_image2.setImageDrawable(drawable);
		        IvAdd_image3.setVisibility(View.VISIBLE);
		        IvAdd_image3.setImageResource(R.drawable.add);
		        break;
			case 3:
		        IvAdd_image3.setImageDrawable(drawable);
		        IvAdd_image4.setVisibility(View.VISIBLE);
		        IvAdd_image4.setImageResource(R.drawable.add);
		        break;
			case 4:
		        IvAdd_image4.setImageDrawable(drawable);
		        break;
			}
	    }
	}  
	void toast(String s){
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}
}
