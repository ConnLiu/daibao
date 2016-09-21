package com.example.easyshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.List;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.example.assist.CelllistAdapter;
import com.example.assist.GoodslistAdapter;
import com.example.customview.ListViewForScrollView;
import com.example.entity.CellInfo;
import com.example.entity.Goods;
import com.example.entity.MyUser;
import com.example.manager.GoodsManager;
import com.example.singleton.GoodsSingleton;
import com.example.singleton.UserSingleton;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class Home extends Activity implements OnPageChangeListener,OnClickListener, OnRefreshListener{

	private ImageButton IbHome_cell,IbHome_class,IbHome_add,IbHome_message,IbHome_mine;
	private ScrollView SvHome;
	private SwipeRefreshLayout mSwipeLayout; 
	private ListViewForScrollView LvHome_goods;
	private ViewPager VpHome_hotgoods = null; 
	private LinearLayout viewGroup = null;
	private String pathName[] = new String[5];
	private ImageView[] mImageViews = null;
	private ImageView[] mTips = null;
	private int index = 0; 
	MyUser user = UserSingleton.getInstance();
	//�����ǹ���ͼƬ�������ַ
	private String path_notic[] = new String[]{"http://a3.qpic.cn/psb?/V13GUzi82nFFur/OdyprfjByHy2aZjXeIrbJE6Zb8vZSBeZWSIMDGIPSuE!/b/dAoBAAAAAAAA&bo=aAHIAAAAAAADB4M!&rf=viewer_4",
			"http://a2.qpic.cn/psb?/V13GUzi82nFFur/glN4dE8BbI0qMHvsZswdCPaJeqSW7n.IAH*FTSBYad4!/b/dAwBAAAAAAAA&bo=aAHIAAAAAAADAIQ!&rf=viewer_4",
			"http://a3.qpic.cn/psb?/V13GUzi82nFFur/BuiCpv2Z4KVRIFvnaHe*GgVaYtA.j*f5XOp2XUR9sJg!/b/dI8AAAAAAAAA&bo=aAHIAAAAAAADAIQ!&rf=viewer_4",
			"http://a3.qpic.cn/psb?/V13GUzi82nFFur/QHamHhi9QO9bh0kd4a6JY91Mj4jpyBT96Cv7f83gE2E!/b/dI8AAAAAAAAA&bo=aAHIAAAAAAADAIQ!&rf=viewer_4",
			"http://a2.qpic.cn/psb?/V13GUzi82nFFur/fEssTp0b1XazZTTxNvh9bsKAryEDB67U3etbRnR1jfw!/b/dAkBAAAAAAAA&bo=aAHIAAAAAAADAIQ!&rf=viewer_4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Bmob.initialize(this, "79f7c1d79f0db04370bf7b20720440db");
        //=================================================
	    Intent intent = getIntent();
	    if(intent.getStringExtra("from")!=null&&intent.getStringExtra("from").equals("login"))
	    	download();
	    else if(intent.getStringExtra("from")!=null&&intent.getStringExtra("from").equals("other")){
	    }
	    else{
		    SharedPreferences  sharedPreferences = getSharedPreferences("login", 0);
		    String valid = sharedPreferences.getString("validate","0");
		    if(valid.equals("1")){   //if has valid login info
		    	String account = sharedPreferences.getString("account","0");
		    	String passwd = sharedPreferences.getString("passwd","0");
		    	login(account,passwd);
		    }
	    }
	    
        SvHome = (ScrollView) findViewById(R.id.SvHome);
        IbHome_cell =(ImageButton) findViewById(R.id.IbHome_cell);
        IbHome_class =(ImageButton) findViewById(R.id.IbHome_class);
	    viewGroup = (LinearLayout) findViewById(R.id.viewGroup);
	    VpHome_hotgoods = (ViewPager) findViewById(R.id.VpHome_hotgoods);
	    LvHome_goods = (ListViewForScrollView)findViewById(R.id.LvHome_goods);
	    IbHome_add =(ImageButton) findViewById(R.id.IbHome_add);
	    IbHome_message =(ImageButton) findViewById(R.id.IbHome_message);
	    IbHome_mine =(ImageButton) findViewById(R.id.IbHome_mine);
	    mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly); 
	    mSwipeLayout.setOnRefreshListener(this);  
        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,  
                android.R.color.holo_orange_light, android.R.color.holo_red_light); 
   
        SvHome.smoothScrollTo(0, 0);
        IbHome_cell.setOnClickListener(this);
        IbHome_class.setOnClickListener(this);
        IbHome_add.setOnClickListener(this);
        IbHome_message.setOnClickListener(this);
        IbHome_mine.setOnClickListener(this);
        LvHome_goods.setOnItemClickListener(itemListener);
        mTips = new ImageView[pathName.length];
	    for(int i = 0; i < mTips.length; i++)
	    {
	      ImageView iv = new ImageView(this);
	      iv.setLayoutParams(new LayoutParams(10,10));
	      mTips[i] = iv;
	      
	      if(i == 0)
	      {
	        iv.setBackgroundResource(R.drawable.back_bg);
	      }else
	      {
	        iv.setBackgroundResource(R.drawable.back_bg);
	      }
	      LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	      lp.leftMargin = 5;
	      lp.rightMargin = 5;
	      viewGroup.addView(iv,lp);
	    }
	    mImageViews = new ImageView[pathName.length];
	    VpHome_hotgoods.setAdapter(new MyPagerAdapter());
	    VpHome_hotgoods.setOnPageChangeListener(this);
	    onPageSelected(0);
	    initResources(); 
        //=====================================================================
        if(GoodsSingleton.getInstance()==null) 	//�ж��Ƿ��Ѿ�����������
        {
        	Log.d("getPopGoods", "GoodsSingleton.getInstance()==null");        	
        	getPopGoods();
        	getAllGoods();
        }else{
        	GoodslistAdapter goodslistadapter = new GoodslistAdapter(Home.this,GoodsSingleton.getTypeGoods("12"));
        	Log.d("getPopGoods", "GoodsSingleton.getInstance()!=null");
    		LvHome_goods.setAdapter(goodslistadapter);
        }
        
       
    }
    public void login(String account,String passwd){
		MyUser user = new MyUser();
		user.setUsername(account);
		user.setPassword(passwd);
		user.login(new SaveListener<MyUser>() {
			public void done(MyUser s, cn.bmob.v3.exception.BmobException e) {
				if(e==null){
					toast("��½�ɹ����û���"+s.getNick());
					//ͨ��BmobUser user = BmobUser.getCurrentUser()��ȡ��¼�ɹ���ı����û���Ϣ
		            //������Զ����û�����MyUser����ͨ��MyUser user = BmobUser.getCurrentUser(MyUser.class)��ȡ�Զ����û���Ϣ
					UserSingleton.setInstance(BmobUser.getCurrentUser(MyUser.class));
				}else{
					toast("��½ʧ�ܣ�"+e.getMessage());
				}
			};
		});
	}
    public void onRefresh()  
    {  
        // Log.e("xxx", Thread.currentThread().getName());  
        // UI Thread  
    	BmobQuery<Goods> c = new BmobQuery<Goods>();
		c.setLimit(20);
		c.order("-createdAt");
		c.addWhereLessThan("type", 12);
		c.findObjects(new FindListener<Goods>() {
			@Override
			public void done(List<Goods> object, BmobException e) {
				if(e==null){
					GoodslistAdapter goodslistadapter = new GoodslistAdapter(Home.this,object);
		    		LvHome_goods.setAdapter(goodslistadapter);
		    		mSwipeLayout.setRefreshing(false);
		    		toast("����ˢ�����");
				}else{
					Log.i("bmob----getGoods", "failed"+e.getMessage()+","+e.getErrorCode());
				}
			}
		});
    	getAllGoods();
    }  
    public void getPopGoods(){
		BmobQuery<Goods> c = new BmobQuery<Goods>();
		c.setLimit(20);
		c.order("-createdAt");
		c.addWhereLessThan("type", 12);
		c.findObjects(new FindListener<Goods>() {
			@Override
			public void done(List<Goods> object, BmobException e) {
				if(e==null){
					Log.d("getPopGoods", "findObjects success");
					GoodslistAdapter goodslistadapter = new GoodslistAdapter(Home.this,object);
		    		LvHome_goods.setAdapter(goodslistadapter);
				}else{
					Log.i("bmob----getGoods", "failed"+e.getMessage()+","+e.getErrorCode());
				}
			}
		});		
	}
    
    public void initResources() {
    	Log.d("getPopGoods", "pathName.length"+pathName.length);
    	int i;
		  for(i=0,index=0;i<pathName.length;i++){	
			final String file_name = "notice"+i+"_image.png";
			FileInputStream localstream = null;
			try {
					localstream = openFileInput(file_name);
				}catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			final Bitmap bm = BitmapFactory.decodeStream(localstream);
			if(bm != null){
			    pathName[i] = getFilesDir()+"/"+file_name;
			    Log.d("getPopGoods", "file exist   "+i+"pathName"+pathName[i]);
			    ImageView iv = new ImageView(Home.this);
	   		    mImageViews[index] = iv;
	   		    int reqWidth = getWindowManager().getDefaultDisplay().getWidth();
	   		    int reqHeight = getWindowManager().getDefaultDisplay().getHeight();
	   		    iv.setImageBitmap(decodeSampledBitmapFromFile(pathName[index], reqWidth, reqHeight));
	   		    index++;
				continue;
			}			
			BmobFile file =new BmobFile(file_name,"",
					path_notic[i]);
			 //�������������ļ��Ĵ洢·����Ĭ�������ļ���Ŀ¼Ϊ��context.getApplicationContext().getCacheDir()+"/bmob/"
		    File saveFile = new File(getFilesDir(), file.getFilename());
		    file.download(saveFile, new DownloadFileListener() {
		        @Override
		        public void onStart() {
//		            toast("��ʼ����...");
		        }
		        @Override
		        public void done(String savePath,cn.bmob.v3.exception.BmobException e) {
		            if(e==null){
		            	Log.i("getNotice:",savePath);
		            	 ImageView iv = new ImageView(Home.this);
		   		      mImageViews[index] = iv;
		   		      int reqWidth = getWindowManager().getDefaultDisplay().getWidth();
		   		      int reqHeight = getWindowManager().getDefaultDisplay().getHeight();
		   		      iv.setImageBitmap(decodeSampledBitmapFromFile(pathName[index], reqWidth, reqHeight));
		   		      index++;
		                //toast("���سɹ�");
		            }else{
		//                toast("����ʧ�ܣ�"+e.getErrorCode()+","+e.getMessage());
		            	Log.i("getNotice:","����ʧ�ܣ�"+e.getErrorCode()+","+e.getMessage());
		            }
		        }
		        @Override
		        public void onProgress(Integer value, long newworkSpeed) {
		//            Log.i("bmob","���ؽ��ȣ�"+value+","+newworkSpeed);
		        }
		    });
			
		    pathName[i] = getFilesDir()+"/"+file_name;
			
		    Log.d("getPopGoods", "file not exist   "+i+"pathName"+pathName[i]);
		  }
		  		
		 
	}
    public void getAllGoods(){
    	BmobQuery<Goods> c = new BmobQuery<Goods>();
		c.setLimit(10000);
		c.order("-createdAt");
		c.addWhereLessThan("type", 12);
		c.include("author");	//get point "author" info when query 
		c.findObjects(new FindListener<Goods>() {
			@Override
			public void done(List<Goods> object, BmobException e) {
				if(e==null){
					GoodsSingleton.setInstance(object);   //���õ����ݱ��浽���������У���������������Կ��Ǳ��浽�������ݿ�
				}else{
					Log.i("bmob----getAllGoods", "failed"+e.getMessage()+","+e.getErrorCode());
				}
			}
		});	
    }
    OnItemClickListener itemListener = new OnItemClickListener() {     
        public void onItemClick(AdapterView<?> parent, View view, int position,  
                long id) {  
            // �����view��������list.xml�ж����LinearLayout����.  
            // ���Կ���ͨ��findViewById���������ҵ�list.xml�ж���������Ӷ���,����: 
        	Intent intent = new Intent();
        	intent.setClass(Home.this, GoodsDetail.class);
        	intent.putExtra("position", position);
        	Log.d("fenlei","form:"+position);
        	startActivity(intent);
        	overridePendingTransition(R.anim.fade, R.anim.hold);
        }

    };  
    void download(){  //�����û�ͷ��
		if(user.getHead_path()==null||user.getHead_path().equals("")){
			return;
		}
		Log.i("path:",user.getHead_path());
		BmobFile file =new BmobFile(user.getObjectId()+"_temphead.png","",
				user.getHead_path());
		 //�������������ļ��Ĵ洢·����Ĭ�������ļ���Ŀ¼Ϊ��context.getApplicationContext().getCacheDir()+"/bmob/"
	    File saveFile = new File(getFilesDir(), file.getFilename());
	    file.download(saveFile, new DownloadFileListener() {
	        @Override
	        public void onStart() {
//	            toast("��ʼ����...");
	        }
	        @Override
	        public void done(String savePath,cn.bmob.v3.exception.BmobException e) {
	            if(e==null){
	            	Log.i("download:",savePath);
	                //toast("���سɹ�");
	            }else{
	//                toast("����ʧ�ܣ�"+e.getErrorCode()+","+e.getMessage());
	            	Log.i("download:",+e.getErrorCode()+" "+e.getMessage());
	            }
	        }
	        @Override
	        public void onProgress(Integer value, long newworkSpeed) {
	//            Log.i("bmob","���ؽ��ȣ�"+value+","+newworkSpeed);
	        }
	    });
	}
    class MyPagerAdapter extends PagerAdapter
    {
      @Override
      public int getCount()
      {
        return pathName.length;
      }
      @Override
      public boolean isViewFromObject(View arg0, Object arg1)
      {
        return arg0 == arg1;
      }
      @Override
      public Object instantiateItem(ViewGroup container, int position)
      {
	      if (index < 0) {
	        index = pathName.length - 1;
	      }
        try
        {
          View view = (View)mImageViews[position];
      	  view.setOnClickListener(new OnClickListener() {
      	   
      	   @Override
      	   public void onClick(View v) {
      		   Intent intent = new Intent();
      	   }
      	  });
      	  ViewPager viewPager = (ViewPager) container;
      	  viewPager.addView(view);
        }catch(Exception e){
      	  e.printStackTrace();
        }
        return mImageViews[position];
      }
      @Override
      public void destroyItem(ViewGroup container, int position, Object object)
      { 
      }
    }
    
    
    public void onPageSelected(int arg0)
    {
      for(int i = 0; i < mTips.length; i++)
      {
        if(arg0 == i)
        {
          mTips[i].setBackgroundResource(R.drawable.tip_selected);
        }else
        {
          mTips[i].setBackgroundResource(R.drawable.tip_normal);
        }
      }	
    }
    
    
    public void onPageScrollStateChanged(int arg0)
    {
    }
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2)
    {
    }
    
    public static Bitmap decodeSampledBitmapFromFile(String pathName, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        Log.i("decodeSampledBitmapFromFile:",pathName);
        options = getBestOptions(options, reqWidth, reqHeight);
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
        return createScaleBitmap(src, mDesiredWidth, mDesiredHeight);
    }
    /**
     * @description ����Ŀ���ȣ�Ŀ��߶ȣ�inSampleSize
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return BitmapFactory.Options����
     */
    private static BitmapFactory.Options getBestOptions(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // ��ȡͼƬ����
        int actualWidth = options.outWidth;
        int actualHeight = options.outHeight;
        // Then compute the dimensions we would ideally like to decode to.
        mDesiredWidth = getResizedDimension(reqWidth, reqHeight, actualWidth, actualHeight);
        mDesiredHeight = getResizedDimension(reqHeight, reqWidth, actualHeight, actualWidth);
        // �������ڵõ�����inSampleSize
        options.inSampleSize = calculateBestInSampleSize(actualWidth, actualHeight, mDesiredWidth, mDesiredHeight);
        // ʹ�û�ȡ����inSampleSizeֵ�ٴν���ͼƬ
        options.inJustDecodeBounds = false;
        return options;
    }
    private static int mDesiredWidth;
    private static int mDesiredHeight;
    private static int getResizedDimension(int maxPrimary, int maxSecondary, int actualPrimary, int actualSecondary) {
        double ratio = (double) actualSecondary / (double) actualPrimary;
        int resized = maxPrimary;
        if (resized * ratio > maxSecondary) {
            resized = (int) (maxSecondary / ratio);
        }
        return resized;
    }
    private static int calculateBestInSampleSize(int actualWidth, int actualHeight, int desiredWidth, int desiredHeight) {
        double wr = (double) actualWidth / desiredWidth;
        double hr = (double) actualHeight / desiredHeight;
        double ratio = Math.min(wr, hr);
        float inSampleSize = 1.0f;
        while ((inSampleSize * 2) <= ratio) {
            inSampleSize *= 2;
        }

        return (int) inSampleSize;
    }
    private static Bitmap createScaleBitmap(Bitmap tempBitmap, int desiredWidth, int desiredHeight) {
        // If necessary, scale down to the maximal acceptable size.
        if (tempBitmap != null && (tempBitmap.getWidth() > desiredWidth || tempBitmap.getHeight() > desiredHeight)) {
            // ����ǷŴ�ͼƬ��filter�����Ƿ�ƽ�����������СͼƬ��filter��Ӱ��
            Bitmap bitmap = Bitmap.createScaledBitmap(tempBitmap, desiredWidth, desiredHeight, true);
            tempBitmap.recycle(); // �ͷ�Bitmap��native��������
            return bitmap;
        } else {
            return tempBitmap; // ���û�����ţ���ô������
        }
    }
    //before before before
    @Override
    public void onClick(View v) {
    	// TODO Auto-generated method stub
    	Intent intent = new Intent();
    	switch(v.getId()){
    	case R.id.IbHome_cell:
    		intent.setClass(Home.this, Cell.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		overridePendingTransition(R.anim.fast_fade, R.anim.hold);
    		break;
    	case R.id.IbHome_class:
    		intent.setClass(Home.this, Category.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		overridePendingTransition(R.anim.fast_fade, R.anim.hold);
    		break;
    	case R.id.IbHome_add:
    		if(!check_user())
    			return;
    		intent.setClass(Home.this, Add.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbHome_message:
    		intent.setClass(Home.this, Message.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		overridePendingTransition(R.anim.fast_fade, R.anim.hold);
    		break;
    	case R.id.IbHome_mine:
    		intent.setClass(Home.this, Mine.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		overridePendingTransition(R.anim.fast_fade,R.anim.hold);
    		break;
    	default:
    		break;
    	}
    }
    public boolean check_user(){
    	user = UserSingleton.getInstance();
		if(user.getNick().equals("��ǰ���ο͵�½")){
			  new AlertDialog.Builder(Home.this).setTitle("ϵͳ��ʾ")//���öԻ������  		  
			     .setMessage("��ǰδ��¼���Ƿ���ת����½ҳ�棿")//������ʾ������  			  
			     .setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {//���ȷ����ť  		  
			         @Override  			  
			         public void onClick(DialogInterface dialog, int which) {//ȷ����ť����Ӧ�¼�  			  
			             // TODO Auto-generated method stub  	
			        	 Intent intent = new Intent();
			        	 intent.setClass(Home.this, Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			        	 startActivity(intent);	
			        	 finish();
			         }  		  
			     }).setNegativeButton("ȡ��",new DialogInterface.OnClickListener() {//��ӷ��ذ�ť    
			         @Override  
			         public void onClick(DialogInterface dialog, int which) {//��Ӧ�¼�  
			             // TODO Auto-generated method stub  		  
			         }  
			     }).show();//�ڰ�����Ӧ�¼�����ʾ�˶Ի��� 
			return false;
		}
		return true;
	}
    void toast(String s){
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
}
