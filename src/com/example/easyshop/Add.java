package com.example.easyshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.example.customview.EditDialog;
import com.example.entity.Goods;
import com.example.entity.MyUser;
import com.example.singleton.UserSingleton;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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
	private String classname[] = {"�鼮","��Ʒ","���","Ь��","��װ","��ױƷ","��ȯ","����Ʒ","����","�˶���Ʒ","С����","������Ʒ"};
    private ImageView IvAdd_rb,IvAdd_image1,IvAdd_image2,IvAdd_image3,IvAdd_image4;
	private RadioButton RbAdd_price,RbAdd_auction,RbAdd_noprice;
	private Button Btn_cfmbuy;
	private EditText EtAdd_title,EtAdd_content;
	private TextView TvAdd_oldprice,TvAdd_price,TvAdd_class;
	private int type;
	MyUser user = UserSingleton.getInstance();
	private Goods good = new Goods();
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
	private Handler handler = new Handler(){
		public void handleMessage(final android.os.Message msg) {
			if(msg.what==0){
				if(IMAGECODE==0){
					
					toast("�����ɹ���");
					finish();
				}
				final String[] filePaths_before = new String[IMAGECODE];
				final String[] filePaths_after = new String[IMAGECODE];
				final String objid = (String) msg.obj;
				for(int i=0;i<IMAGECODE;i++){
					filePaths_before[i] = getFilesDir()+"/"+i+IMAGE_FILE_NAME;
					filePaths_after[i] = getFilesDir()+"/"+msg.obj.toString()+"_"+i+IMAGE_FILE_NAME;
					File file = new File(filePaths_before[i]),file_after = new File(filePaths_after[i]);
					Log.i("sned","file before:"+file.exists()+"  file after��"+file_after.exists());
					file.renameTo(file_after);
					Log.i("sned","file before:"+file.exists()+"  file after��"+file_after.exists());
					Log.i("sned","file before:"+(String)msg.obj+"   temo:"+msg.obj);
				}
				
				
				BmobFile.uploadBatch(filePaths_after, new UploadBatchListener() {

				    @Override
				    public void onSuccess(List<BmobFile> files,List<String> urls) {
				        //1��files-�ϴ���ɺ��BmobFile���ϣ���Ϊ�˷����Ҷ����ϴ�������ݽ��в�������������Խ����ļ����浽����
				        //2��urls-�ϴ��ļ�������url��ַ
				        if(urls.size()==filePaths_after.length){//���������ȣ�������ļ�ȫ���ϴ����
				            //do something
				        	String str="";
				        	for(int i=0;i<urls.size();i++)
				        		str = str+urls.get(i)+",";
				        	android.os.Message msg1 = new android.os.Message();
				        	String temp[] = new String[2];
				        	temp[0] = str;
				        	temp[1] = objid;
				        	msg1.obj = temp;
				        	
				        	msg1.what =1 ;
				        	Log.i("sned","temp0 :"+temp[0]+"   temp1:"+temp[1]);
				        	handler.sendMessage(msg1);
				        }
				    }

				    @Override
				    public void onError(int statuscode, String errormsg) {
				        Log.d("������"+statuscode,"����������"+errormsg);
				    }

				    @Override
				    public void onProgress(int curIndex, int curPercent, int total,int totalPercent) {
				        //1��curIndex--��ʾ��ǰ�ڼ����ļ������ϴ�
				        //2��curPercent--��ʾ��ǰ�ϴ��ļ��Ľ���ֵ���ٷֱȣ�
				        //3��total--��ʾ�ܵ��ϴ��ļ���
				        //4��totalPercent--��ʾ�ܵ��ϴ����ȣ��ٷֱȣ�
				    }
				});
			}else if(msg.what==1){
				String temp[] = new String[2];
				temp=(String[]) msg.obj;
				good.setValue("head_path", temp[0]);
				Log.i("bmob","temp[0] "+temp[0]+" temp[1]:"+temp[1] );
				good.update(temp[1], new UpdateListener() {

				    @Override
				    public void done(BmobException e) {
				        if(e==null){
				            Log.i("bmob","���³ɹ�");
				            toast("�����ɹ�");
				            finish();
				        }else{
				            Log.i("bmob","����ʧ�ܣ�"+e.getMessage()+","+e.getErrorCode());
				        }
				    }
				});
			}
		};
	};
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(Add.this, SelectClass.class);
		Resources resource = (Resources) getBaseContext().getResources(); 
		ColorStateList greycsl = (ColorStateList) resource.getColorStateList(R.color.lightgrey);
		ColorStateList backcsl = (ColorStateList) resource.getColorStateList(R.color.home); 
		switch(v.getId()){
		case R.id.Btn_cfmbuy:
			
			good.setName(EtAdd_title.getText().toString());
			good.setIntro(EtAdd_content.getText().toString());
			good.setType(type);
			good.setAuthor(user);
			good.setOld_price(Float.valueOf(TvAdd_oldprice.getText().toString()));
			good.setPrice(Float.valueOf(TvAdd_price.getText().toString()));
			good.save(new SaveListener<String>(){
				@Override
				public void done(String objectId, cn.bmob.v3.exception.BmobException e) {
					// TODO Auto-generated method stub
					if(e==null){
						android.os.Message msg = new android.os.Message();
			        	msg.obj = objectId;
			        	msg.what=0;
						handler.sendMessage(msg);
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
            	
            	for(int i=position+1;i<IMAGECODE;i++){
            		FileInputStream localstream = null;
//            		try {
//            			localstream = openFileInput("0"+IMAGE_FILE_NAME);
//            		} catch (FileNotFoundException e) {
//            			// TODO Auto-generated catch block
//            			e.printStackTrace();
//            			Log.d("DialogInterface","FileNotFoundException+ IMAGECODE "+IMAGECODE+" i: "+i);
//            		}
//                    Bitmap bm = BitmapFactory.decodeStream(localstream);
//                    IvAdd_image3.setImageBitmap(bm);
                    
//                    
//                    try {
//            			localstream = openFileInput("2"+IMAGE_FILE_NAME);
//            		} catch (FileNotFoundException e) {
//            			// TODO Auto-generated catch block
//            			e.printStackTrace();
//            			Log.d("DialogInterface","FileNotFoundException+ IMAGECODE "+IMAGECODE+" i: "+i);
//            		}
//                     bm = BitmapFactory.decodeStream(localstream);
//                    IvAdd_image4.setImageBitmap(bm);
                    
            		int temp = i-1;
            		deleteFile(temp+IMAGE_FILE_NAME);
            		File file = new File(getFilesDir()+"/"+i+IMAGE_FILE_NAME);
                    File file1 = new File(getFilesDir()+"/"+temp+IMAGE_FILE_NAME);
                    Log.d("DialogInterface","  file1 "+file1.exists()+"file ex "+file.exists());
                    boolean suc = file.renameTo(file1);
                    Log.d("DialogInterface","suc:"+suc+" file name "+file.getName()+" IMAGECODE "+IMAGECODE+"file1 name "+file1.getName());			
                    Log.d("DialogInterface","after rename  file1 "+file1.exists()+"file ex "+file.exists());
                    
                    
            		
            		try {
            			localstream = openFileInput(temp+IMAGE_FILE_NAME);
            		} catch (FileNotFoundException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            			Log.d("DialogInterface","FileNotFoundException+ IMAGECODE "+IMAGECODE+" i: "+i);
            		}
            		Bitmap bm = BitmapFactory.decodeStream(localstream);
                    Log.d("DialogInterface","after local  file1 "+file1.exists()+"file ex "+file.exists());
                    switch(temp){
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
	private int pic[] = new int[4]; 
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
	        	
	            setPicToView(data);
	        }
	        break;
	    case REQUESTCODE_CLASS:
	    	TvAdd_class.setText(classname[resultCode-1]);
	    	type=resultCode-1;
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
				e.printStackTrace(); 
			}
	        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
	        
	        IMAGECODE++;
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
