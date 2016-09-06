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
	private String classname[] = {"�鼮","����","���","Ь��","��װ","��ױƷ","��ȯ","����Ʒ","����","�˶���Ʒ","С����","������Ʒ"};
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
			            toast("�����ɹ���");
			            finish();
			        }else{
			            Log.i("bmob","����ʧ�ܣ�"+e.getMessage()+","+e.getErrorCode());
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
            builder1.setNegativeButton("ȡ��",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvAdd_oldprice.setText("0");
            		Toast.makeText(getApplicationContext(), "δ����",Toast.LENGTH_SHORT).show();
            	}
            });
            builder1.setPositiveButton("ȷ��",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvAdd_oldprice.setText(builder1.getEdit());
            		Toast.makeText(getApplicationContext(), builder1.getEdit()+"����ɹ�",Toast.LENGTH_SHORT).show();
            	}
            }); 
            builder1.create().show();
			break;
		case R.id.TvAdd_price:
            final EditDialog.Builder builder2=new EditDialog.Builder(Add.this);
            builder2.setNegativeButton("ȡ��",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvAdd_oldprice.setText("0");
            		Toast.makeText(getApplicationContext(), "δ����",Toast.LENGTH_SHORT).show();
            	}
            });
            builder2.setPositiveButton("ȷ��",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvAdd_price.setText(builder2.getEdit());
            		Toast.makeText(getApplicationContext(), builder2.getEdit()+"����ɹ�",Toast.LENGTH_SHORT).show();
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
//	    ͨ��AlertDialog.Builder�������ʵ�������ǵ�һ��AlertDialog�Ķ���
        AlertDialog.Builder builder = new AlertDialog.Builder(Add.this);
        //    ����Title��ͼ��
        builder.setIcon(R.drawable.login);
        //    ����Title������
        builder.setTitle("��ʾ");
        //    ����Content����ʾһ����Ϣ
        builder.setMessage("ȷ��ɾ������ͼƬ��");
        //    ����һ��PositiveButton
        builder.setPositiveButton("ɾ��", new DialogInterface.OnClickListener()
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
        //    ����һ��NegativeButton
        builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                
            }
        });
       
        //    ��ʾ���öԻ���
        builder.show();
	}
	private PopupWindow popupWindow;
	private void showPopupWindow(View view) {

        // һ���Զ���Ĳ��֣���Ϊ��ʾ������
        View contentView = LayoutInflater.from(Add.this).inflate(
                R.layout.headimage_popwindow, null);
        // ���ð�ť�ĵ���¼�
        ImageView imageview1 = (ImageView) contentView.findViewById(R.id.imageView1);
        ImageView imageview2 = (ImageView) contentView.findViewById(R.id.imageView2);
        imageview1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //�������ָ������������պ����Ƭ�洢��·��
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
                // ���������Ҫ�����ϴ�����������ͼƬ����ʱ����ֱ��д�磺image/jpeg �� image/png�ȵ�����
                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(pickIntent, REQUESTCODE_PICK);
                popupWindow.dismiss();
            }
        });
        popupWindow = new PopupWindow(contentView,
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);

        // ���������PopupWindow�ı����������ǵ���ⲿ������Back�����޷�dismiss����
        // �Ҿ���������API��һ��bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.transparent));
        // ���úò���֮����show
        popupWindow.showAsDropDown(view,-20,0);
        
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
	    case REQUESTCODE_PICK:// ֱ�Ӵ�����ȡ
	        try {
	            startPhotoZoom(data.getData());
	        } catch (NullPointerException e) {
	            //e.printStackTrace();// �û����ȡ������
	        }
	        break;
	    case REQUESTCODE_TAKE:// �����������
	        File temp = new File(Environment.getExternalStorageDirectory() , IMAGECODE+IMAGE_FILE_NAME);
	        startPhotoZoom(Uri.fromFile(temp));
	        break;
	    case REQUESTCODE_CUTTING:// ȡ�òü����ͼƬ
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
	 * �ü�ͼƬ����ʵ��
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
	    Intent intent = new Intent("com.android.camera.action.CROP");
	    intent.setDataAndType(uri, "image/*");
	    // crop=true�������ڿ�����Intent��������ʾ��VIEW�ɲü�
	    intent.putExtra("crop", true);
	    // aspectX aspectY �ǿ�ߵı���
	    intent.putExtra("aspectX", 1);
	    intent.putExtra("aspectY", 1);
	    // outputX outputY �ǲü�ͼƬ���
	    intent.putExtra("outputX", 300);
	    intent.putExtra("outputY", 300);
	    intent.putExtra("return-data", true);
	    startActivityForResult(intent, REQUESTCODE_CUTTING);
	}
	 /*    
	*//**
	 * ����ü�֮���ͼƬ����
	 * @param picdata
	 */
	private void setPicToView(Intent picdata) {
	    Bundle extras = picdata.getExtras();
	    if (extras != null) {
	        // ȡ��SDCardͼƬ·������ʾ
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
