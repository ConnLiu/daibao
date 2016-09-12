package com.example.easyshop;

import java.io.File;

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
	private int[] resId = new int[9];
	private ImageView[] mImageViews = null;
	private ImageView[] mTips = null;
	private int index = 0; 
	MyUser user = UserSingleton.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Bmob.initialize(this, "79f7c1d79f0db04370bf7b20720440db");
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
        //=====================================================================
        if(GoodsSingleton.getInstance()==null) 	//判断是否已经保存了数据
        {
        	getNewGoods();
        	getAllGoods();
        }else{
        	GoodslistAdapter goodslistadapter = new GoodslistAdapter(Home.this,GoodsSingleton.getTypeGoods("13"));
    		LvHome_goods.setAdapter(goodslistadapter);
        }
        
        //=================================================
		initResources();
		mTips = new ImageView[resId.length];
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
	    mImageViews = new ImageView[resId.length];
	    for(index = 0; index < mImageViews.length; index++)
	    {
	      ImageView iv = new ImageView(this);
	      mImageViews[index] = iv;
	      int reqWidth = getWindowManager().getDefaultDisplay().getWidth();
	      int reqHeight = getWindowManager().getDefaultDisplay().getHeight();
	      iv.setImageBitmap(decodeSampledBitmapFromResource(getResources(), resId[index], reqWidth, reqHeight));
	    }
	    VpHome_hotgoods.setAdapter(new MyPagerAdapter());
	    VpHome_hotgoods.setOnPageChangeListener(this);
	    onPageSelected(0);
	    Intent intent = getIntent();
	    if(intent.getStringExtra("from")!=null&&intent.getStringExtra("from").equals("login"))
	    	download();
	    else{
		    SharedPreferences  sharedPreferences = getSharedPreferences("login", 0);
		    String valid = sharedPreferences.getString("validate","0");
		    if(valid.equals("1")){
		    	String account = sharedPreferences.getString("account","0");
		    	String passwd = sharedPreferences.getString("passwd","0");
		    	login(account,passwd);
		    }
	    }
    }
    public void login(String account,String passwd){
		MyUser user = new MyUser();
		user.setUsername(account);
		user.setPassword(passwd);
		user.login(new SaveListener<MyUser>() {
			public void done(MyUser s, cn.bmob.v3.exception.BmobException e) {
				if(e==null){
					toast("登陆成功！用户："+s.getNick());
					//通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
		            //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
					UserSingleton.setInstance(BmobUser.getCurrentUser(MyUser.class));
				}else{
					toast("登陆失败！"+e.getMessage());
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
		c.findObjects(new FindListener<Goods>() {
			@Override
			public void done(List<Goods> object, BmobException e) {
				if(e==null){
					GoodslistAdapter goodslistadapter = new GoodslistAdapter(Home.this,object);
		    		LvHome_goods.setAdapter(goodslistadapter);
		    		mSwipeLayout.setRefreshing(false);
		    		toast("数据刷新完毕");
				}else{
					Log.i("bmob----getGoods", "failed"+e.getMessage()+","+e.getErrorCode());
				}
			}
		});
    	getAllGoods();
    }  
    public void getNewGoods(){
		BmobQuery<Goods> c = new BmobQuery<Goods>();
		c.setLimit(20);
		c.order("-createdAt");
		c.findObjects(new FindListener<Goods>() {
			@Override
			public void done(List<Goods> object, BmobException e) {
				if(e==null){
					GoodslistAdapter goodslistadapter = new GoodslistAdapter(Home.this,object);
		    		LvHome_goods.setAdapter(goodslistadapter);
		    		
				}else{
					Log.i("bmob----getGoods", "failed"+e.getMessage()+","+e.getErrorCode());
				}
			}
		});		
	}
    public void getAllGoods(){
    	BmobQuery<Goods> c = new BmobQuery<Goods>();
		c.setLimit(10000);
		c.order("-createdAt");
		c.findObjects(new FindListener<Goods>() {
			@Override
			public void done(List<Goods> object, BmobException e) {
				if(e==null){
					GoodsSingleton.setInstance(object);   //将得到数据保存到单例对象中，后面数据量大可以考虑保存到本地数据库
				}else{
					Log.i("bmob----getAllGoods", "failed"+e.getMessage()+","+e.getErrorCode());
				}
			}
		});	
    }
    OnItemClickListener itemListener = new OnItemClickListener() {     
        public void onItemClick(AdapterView<?> parent, View view, int position,  
                long id) {  
            // 这里的view是我们在list.xml中定义的LinearLayout对象.  
            // 所以可以通过findViewById方法可以找到list.xml中定义的它的子对象,如下: 
        	Intent intent = new Intent();
        	intent.setClass(Home.this, GoodsDetail.class);
        	intent.putExtra("position", position);
        	Log.d("fenlei","form:"+position);
        	startActivity(intent);
        	overridePendingTransition(R.anim.fade, R.anim.hold);
        }

    };  
    void download(){
		if(user.getHead_path()==null||user.getHead_path().equals("")){
			return;
		}
		Log.i("path:",user.getHead_path());
		BmobFile file =new BmobFile(user.getObjectId()+"_temphead.png","",
				user.getHead_path());
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
	
    class MyPagerAdapter extends PagerAdapter
    {
      @Override
      public int getCount()
      {
        return resId.length;
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
	        index = resId.length - 1;
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
    
    private static Bitmap decodeSampledBitmapFromResource(Resources res,int resId,int reqWidth,int reqHeight)
    {
      BitmapFactory.Options opts = new BitmapFactory.Options();
      opts.inJustDecodeBounds = true;
      BitmapFactory.decodeResource(res, resId);
      int inSampleSize = cacluateInSampledSize(opts, reqWidth, reqHeight);
      opts.inSampleSize = inSampleSize;
      opts.inJustDecodeBounds = false;
      return BitmapFactory.decodeResource(res,resId,opts);
    }
    
    private static int cacluateInSampledSize(BitmapFactory.Options opts,int width,int height)
    {
      if(opts == null)
      {
        return 1;
      }
      int inSampleSize = 1;
      int realWidth = opts.outWidth;
      int realHeight = opts.outHeight;
      
      if(realWidth > width || realHeight > height)
      {
        int heightRatio = realHeight/height;
        int widthRatio = realWidth/width;
        
        inSampleSize = (widthRatio > heightRatio) ? heightRatio : widthRatio;
      }
      return inSampleSize;
    }
    
	  public void initResources() {
		    resId[0] = R.drawable.news4;
		    resId[1] = R.drawable.news2;
		    resId[2] = R.drawable.news3;
		    resId[3] = R.drawable.news5;
		    resId[4] = R.drawable.news1;
		    resId[5] = R.drawable.news7;
		    resId[6] = R.drawable.news2;
		    resId[7] = R.drawable.news8;
		    resId[8] = R.drawable.news6;
		  }
	
	
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
    void toast(String s){
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}
}
