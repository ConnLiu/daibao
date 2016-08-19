package com.example.easyshop;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;

import com.example.assist.GoodslistAdapter;
import com.example.customview.ListViewForScrollView;
import com.example.entity.MyUser;
import com.example.singleton.UserSingleton;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class Home extends Activity implements OnPageChangeListener,OnClickListener{

	private ImageButton IbHome_cell,IbHome_class,IbHome_add,IbHome_message,IbHome_mine;
	private ScrollView SvHome;
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

        SvHome = (ScrollView) findViewById(R.id.SvHome);
        IbHome_cell =(ImageButton) findViewById(R.id.IbHome_cell);
        IbHome_class =(ImageButton) findViewById(R.id.IbHome_class);
	    viewGroup = (LinearLayout) findViewById(R.id.viewGroup);
	    VpHome_hotgoods = (ViewPager) findViewById(R.id.VpHome_hotgoods);
	    LvHome_goods = (ListViewForScrollView)findViewById(R.id.LvHome_goods);
	    IbHome_add =(ImageButton) findViewById(R.id.IbHome_add);
	    IbHome_message =(ImageButton) findViewById(R.id.IbHome_message);
	    IbHome_mine =(ImageButton) findViewById(R.id.IbHome_mine);

        SvHome.smoothScrollTo(0, 0);
        IbHome_cell.setOnClickListener(this);
        IbHome_class.setOnClickListener(this);
        IbHome_add.setOnClickListener(this);
        IbHome_message.setOnClickListener(this);
        IbHome_mine.setOnClickListener(this);

		GoodslistAdapter goodslistadapter = new GoodslistAdapter(this, getData());
		LvHome_goods.setAdapter(goodslistadapter);
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
	    System.out.println(intent.getStringExtra("from"));
	    if(intent.getStringExtra("from")!=null&&intent.getStringExtra("from").equals("login"))
	    	download();
    }
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
	                toast("下载成功");
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
      		   //intent.putExtra("url", "http://www.baidu.com");
     		   //intent.putExtra("Title","baidu");
     		   //intent.setClass(Home.this,Web_News.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
     		  //startActivity(intent);
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
	  
	private List<Map<String,Object>> getData() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i = 0 ; i <4; i ++ )
		{
			Map<String, Object> map = new HashMap<String, Object>();
			/*try {
				map.put("newstext",java.net.URLDecoder.decode(news_Info.get(i).getNewsTitle().toString(),"utf-8"));
				
				map.put("newsimage", java.net.URLDecoder.decode(news_Info.get(i).getPictureURL().toString(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			map.put("goodsname", "商品名称");
			map.put("goodsmoney", "0.00");
			map.put("goodslikenum", "88");
			map.put("goodsimage", R.drawable.tip_selected);
			map.put("goodslike", R.drawable.zan);
			list.add(map);
		}
		return list;
	}
	
    @Override
    public void onClick(View v) {
    	// TODO Auto-generated method stub
    	Intent intent = new Intent();
    	switch(v.getId()){
    	case R.id.IbHome_cell:
    		intent.setClass(Home.this, Cell.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbHome_class:
    		intent.setClass(Home.this, Category.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbHome_add:
    		intent.setClass(Home.this, Add.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbHome_message:
    		intent.setClass(Home.this, Message.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	case R.id.IbHome_mine:
    		intent.setClass(Home.this, Mine.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		break;
    	default:
    		break;
    	}
    	
    }
    void toast(String s){
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}
}
