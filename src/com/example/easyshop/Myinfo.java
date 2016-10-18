package com.example.easyshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.example.customview.DomitoryDialog;
import com.example.customview.EditDialog;
import com.example.customview.GenderDialog;
import com.example.entity.MyUser;
import com.example.singleton.UserSingleton;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class Myinfo extends Activity implements OnClickListener{
	private final int REQUESTCODE_PICK=0,REQUESTCODE_TAKE=1,REQUESTCODE_CUTTING=2;
    private Context mContext = null;
	private TextView TvMyinfo_gender,TvMyinfo_birth,TvMyinfo_domi,TvMyinfo_id,Tv_realname,TvMyinfo_qianming,TvMyinfo_phone;
	private ImageView IvMyinfo_head,IbMyinfo_rb;
	private DatePickerDialog datedialog;
	private int year,monthOfYear,dayOfMonth;
	MyUser user = UserSingleton.getInstance();
	private final String IMAGE_FILE_NAME = user.getObjectId()+"_temphead.png";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo);
        mContext = this;   
        FileInputStream localstream = null;
		try {
			localstream = openFileInput(IMAGE_FILE_NAME);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        final Bitmap bm = BitmapFactory.decodeStream(localstream);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR) - 20;
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		
		IvMyinfo_head =(ImageView) findViewById(R.id.IvMyinfo_head);
		IbMyinfo_rb =(ImageView) findViewById(R.id.IbMyinfo_rb);
		TvMyinfo_gender =(TextView) findViewById(R.id.TvMyinfo_gender);
		TvMyinfo_birth =(TextView) findViewById(R.id.TvMyinfo_birth);
		TvMyinfo_domi =(TextView) findViewById(R.id.TvMyinfo_domi);
		TvMyinfo_id =(TextView) findViewById(R.id.TvMyinfo_id);
		Tv_realname =(TextView) findViewById(R.id.TvMyinfo_name);
		TvMyinfo_qianming =(TextView) findViewById(R.id.TvMyinfo_qianming);
		TvMyinfo_phone =(TextView) findViewById(R.id.TvMyinfo_phone);
		TvMyinfo_gender.setText(user.getGender());
		TvMyinfo_birth.setText(user.getBirthday());
		TvMyinfo_domi.setText(user.getDorm());
		Tv_realname.setText(user.getRealname());
		TvMyinfo_id.setText(user.getNumberID());
		TvMyinfo_phone.setText(user.getPhone());
		
		IvMyinfo_head.setOnClickListener(this);
		IbMyinfo_rb.setOnClickListener(this);
		TvMyinfo_gender.setOnClickListener(this);
		TvMyinfo_birth.setOnClickListener(this);
		TvMyinfo_domi.setOnClickListener(this);
		TvMyinfo_qianming.setOnClickListener(this);
		TvMyinfo_phone.setOnClickListener(this);

		if(bm != null){
			IvMyinfo_head.setImageBitmap(bm);
		}
		else IvMyinfo_head.setImageResource(R.drawable.tip_selected);
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		user.setGender(TvMyinfo_gender.getText().toString());
        user.setValue("gender", TvMyinfo_gender.getText().toString());
		user.setDorm(TvMyinfo_domi.getText().toString());
		user.setValue("dorm", TvMyinfo_domi.getText().toString());
		user.setPhone(TvMyinfo_phone.getText().toString());
		Log.d("MyInfo",user.getGender());
		user.update(user.getObjectId(),new UpdateListener() {
			@Override
			public void done(BmobException e) {
				// TODO Auto-generated method stub
				if(e==null){
					Log.i("Myinfo","onstop,���³ɹ�");
				}else{
					Log.i("Myinfo","onstop,����ʧ��"+e.getMessage()+e.getErrorCode());
				}
			}
		});
		super.onStop();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch(v.getId()){
		case R.id.IbMyinfo_rb:
			finish();
			break;
		case R.id.IvMyinfo_head:
			 showPopupWindow(v);
			break;
		case R.id.TvMyinfo_gender:
			GenderDialog.Builder builder=new GenderDialog.Builder(Myinfo.this);
            builder.setNegativeButton("Ů",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvMyinfo_gender.setText("Ů");
            	}
            });
            builder.setPositiveButton("��",new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvMyinfo_gender.setText("��");
            	}
            }); 
            builder.create().show();
            
			break;
		case R.id.TvMyinfo_birth:
			datedialog = new DatePickerDialog(this, new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
					
					TvMyinfo_birth.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
					user.setBirthday(TvMyinfo_birth.getText().toString());
					user.setValue("birthday", TvMyinfo_birth.getText().toString());
				}
			}, year, monthOfYear, dayOfMonth);
			datedialog.show();
			
			break;
		case R.id.TvMyinfo_domi:
			DomitoryDialog.Builder dombuilder=new DomitoryDialog.Builder(Myinfo.this);
			dombuilder.setHuiButton(new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvMyinfo_domi.setText("��԰");
            	}
            });
			dombuilder.setBoButton(new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvMyinfo_domi.setText("��԰");
            	}
            });
			dombuilder.setYiButton(new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvMyinfo_domi.setText("��԰");
            	}
            });
			dombuilder.setSuiButton(new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvMyinfo_domi.setText("����");
            	}
            });
			dombuilder.setHeButton(new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvMyinfo_domi.setText("��԰");
            	}
            });
			dombuilder.create().show();
			
			break;
		case R.id.TvMyinfo_qianming:
			intent.setClass(Myinfo.this, SelfIntroduction.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		case R.id.TvMyinfo_phone:
			final EditDialog.Builder editbuilder = new EditDialog.Builder(Myinfo.this);
			editbuilder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            		TvMyinfo_phone.setText(editbuilder.getEdit().toString());
				}
			});
			editbuilder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
				public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
				}
			});
			editbuilder.create().show();
		}
		
	}

    private void showPopupWindow(View view) {

        // һ���Զ���Ĳ��֣���Ϊ��ʾ������
        View contentView = LayoutInflater.from(mContext).inflate(
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
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                startActivityForResult(takeIntent, REQUESTCODE_TAKE);
            }
        });
        imageview2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                // ���������Ҫ�����ϴ�����������ͼƬ����ʱ����ֱ��д�磺image/jpeg �� image/png�ȵ�����
                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(pickIntent, REQUESTCODE_PICK);
            }
        });
        PopupWindow popupWindow = new PopupWindow(contentView,
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
	            e.printStackTrace();// �û����ȡ������
	        }
	        break;
	    case REQUESTCODE_TAKE:// �����������
	        File temp = new File(Environment.getExternalStorageDirectory() , IMAGE_FILE_NAME);
	        startPhotoZoom(Uri.fromFile(temp));
	        break;
	    case REQUESTCODE_CUTTING:// ȡ�òü����ͼƬ
	        if (data != null) {
	            setPicToView(data);
	        }
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
            photo = toRoundBitmap(photo);
	        Drawable drawable = new BitmapDrawable(null, photo);
	        FileOutputStream stream = null;
			try {
				stream = openFileOutput(IMAGE_FILE_NAME, 0);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
	        IvMyinfo_head.setImageDrawable(drawable);
	        //=====================================================================
	        if(user.getHead_path()!=null&&!user.getHead_path().equals("")){
	        	BmobFile file = new BmobFile();
	        	file.setUrl(user.getHead_path());//��url���ϴ��ļ��ɹ�֮��ͨ��bmobFile.getUrl()������ȡ�ġ�
	        	file.delete(new UpdateListener() {
	        	    @Override
	        	    public void done(BmobException e) {
	        	        if(e==null){
	        	        	System.out.println("�ļ�ɾ���ɹ�");	        	        
	        	        }else{
	        	        	System.out.println("�ļ�ɾ��ʧ�ܣ�"+e.getErrorCode()+","+e.getMessage());	  
	        	            
	        	        }
	        	    }
	        	});
	        }
	        String picPath = getFilesDir()+"/"+IMAGE_FILE_NAME;
	        final BmobFile bmobFile = new BmobFile(new File(picPath));
	        bmobFile.uploadblock(new UploadFileListener() {
	            @Override
	            public void done(BmobException e) {
	                if(e==null){
	                    //bmobFile.getFileUrl()--���ص��ϴ��ļ���������ַ
	                	user.setHead_path(bmobFile.getFileUrl());
	                	user.setValue("head_path", bmobFile.getFileUrl());
	                    toast("����ͷ��ɹ�" );
	                }else{
	                    toast("����ͷ��ʧ��,δ�ϴ�����������" );
	                }
	            }

	            @Override
	            public void onProgress(Integer value) {
	                // ���ص��ϴ����ȣ��ٷֱȣ�
	            }
	        });
	    }
	}   /**
     * ��bitmapת��Բ��
     * */
    public Bitmap toRoundBitmap(Bitmap bitmap){
            int width=bitmap.getWidth();
            int height=bitmap.getHeight();
            int r=0;
            //ȡ��̱����߳�
            if(width<height){
                    r=width;
            }else{
                    r=height;
            }
            //����һ��bitmap
            Bitmap backgroundBm=Bitmap.createBitmap(width,height,Config.ARGB_8888);
            //newһ��Canvas����backgroundBmp�ϻ�ͼ 
            Canvas canvas=new Canvas(backgroundBm);
            Paint p=new Paint();
            //���ñ�Ե�⻬��ȥ����� 
            p.setAntiAlias(true);
            RectF rect=new RectF(0, 0, r, r);
            //ͨ���ƶ���rect��һ��Բ�Ǿ��Σ���Բ��X�᷽��İ뾶����Y�᷽��İ뾶ʱ��  
            //�Ҷ�����r/2ʱ����������Բ�Ǿ��ξ���Բ�� 
            canvas.drawRoundRect(rect, r/2, r/2, p);
            //���õ�����ͼ���ཻʱ��ģʽ��SRC_INΪȡSRCͼ���ཻ�Ĳ��֣�����Ľ���ȥ��
            p.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            //canvas��bitmap����backgroundBmp��
            canvas.drawBitmap(bitmap, null, rect, p);
            return backgroundBm;
    }
    void toast(String s){
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}
}
