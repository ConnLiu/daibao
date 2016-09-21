package com.example.easyshop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobRealTimeData;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.ValueEventListener;

import com.example.assist.ChatMsgViewAdapater;
import com.example.assist.MessagelistAdapter;
import com.example.assist.ZanslistAdapter;
import com.example.customview.ListViewForScrollView;
import com.example.entity.ChatMsg;
import com.example.entity.ChatMsgEntity;
import com.example.entity.Goods;
import com.example.entity.MyUser;
import com.example.singleton.GoodsSingleton;
import com.example.singleton.UserSingleton;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Chat extends Activity implements OnClickListener{
	private Button mBtnSend;  
	private EditText mEditTextContent;  
	    //聊天内容的适配器  
	private ChatMsgViewAdapater mAdapter;  
	private ListView LvChat_list;  
	    //聊天的内容  
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>(); 
	private ImageView IvSet_rb,img_good;
	private TextView tv_confrmBuy,tv_goodName,tv_price;
	private Goods good;
	private ChatMsg chatMsg;
	private MyUser user= UserSingleton.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);
		Intent intent =getIntent();
		int position = getIntent().getIntExtra("position",0);
		good = GoodsSingleton.getInstance().get(position);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		LvChat_list = (ListViewForScrollView)findViewById(R.id.LvChat_list);
		LvChat_list.setDividerHeight(0);
		tv_confrmBuy = (TextView)findViewById(R.id.tv_confrmBuy);
		tv_goodName = (TextView)findViewById(R.id.tv_goodName);
		tv_goodName.setText(intent.getStringExtra("goodname"));
		tv_price = (TextView)findViewById(R.id.tv_price);
		tv_price.setText(intent.getStringExtra("price"));
	    mBtnSend = (Button) findViewById(R.id.btn_send);  
	    mBtnSend.setOnClickListener(this);  
	    mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
		IvSet_rb = (ImageView)findViewById(R.id.IvSet_rb);
		img_good = (ImageView)findViewById(R.id.img_good);
		IvSet_rb.setOnClickListener(this);
		tv_confrmBuy.setOnClickListener(this);
		initData();
		
	}
    private String[] msgArray = new String[]{"亲爱的呆宝用户，您好！",   
            };  
    private String[]dataArray = new String[]{" 系统默认信息 "};  
    private final static int COUNT = 1;  
      
    private void SetView(ImageView img,String file_name){
    	FileInputStream localstream = null;
		try {
			localstream = openFileInput(file_name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bitmap bm = BitmapFactory.decodeStream(localstream);
		if(bm != null){
			img.setImageBitmap(bm);
		}else{
			img.setImageResource(R.drawable.tip_selected);
		}
    }
    //初始化要显示的数据   
    private void initData() {
    	SetView(img_good,good.getObjectId()+"_0image.png");
    	BmobQuery<ChatMsg> query = new BmobQuery<ChatMsg>();
    	//查询playerName叫“比目”的数据
    	query.addWhereEqualTo("goodsObject", good.getObjectId());
    	//返回50条数据，如果不加上这条语句，默认返回10条数据
    	query.setLimit(1);
    	//执行查询方法
    	query.findObjects(new FindListener<ChatMsg>() {
    	    @Override
    	    public void done(List<ChatMsg> object, BmobException e) {
    	        if(e==null){
    	           // toast("查询成功：共"+object.size()+"条数据。");
    	            for (ChatMsg gameScore : object) {
    	            	{
    	            		chatMsg = gameScore;
    	            		
    	            		return;
    	            	}
    	            }
    	        }
    	            Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
    	            chatMsg = new ChatMsg(user,good.getAuthor());
    	            chatMsg.setGoodsObject(good.getObjectId());
    	            chatMsg.save(new SaveListener<String>() {
    	    			@Override
    	    			public void done(String objectId, BmobException e) {
    	    				// TODO Auto-generated method stub
    	    				 if(e==null){
    	    	                    //toast("创建数据成功：" + objectId);
    	    	                    Log.i("bmob","objectId："+objectId+"   "+chatMsg.getObjectId());
    	    	                }else{
    	    	                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
    	    	                }
    	    			}
    	            });
    	        }
    	});
    	
        for(int i = 0; i < COUNT; i++) {  
            ChatMsgEntity entity = new ChatMsgEntity();  
            entity.setDate(dataArray[i]);  //
            if (i == 0)  
            {  
                entity.setPath(good.getAuthor().getObjectId()+"_temphead.png");  
                entity.setMsgType(true);  
            }else{  
                entity.setPath("Shamoo");  
                entity.setMsgType(false);  
            }  
            entity.setText(msgArray[i]);  //
            mDataArrays.add(entity);  
        }  
        mAdapter = new ChatMsgViewAdapater(this, mDataArrays);  
        LvChat_list.setAdapter(mAdapter);  
        
        data.start(new ValueEventListener() { 	//有时候没用
			@Override
			public void onDataChange(JSONObject data) {
				// TODO Auto-generated method stub
				//if(BmobRealTimeData.ACTION_UPDATETABLE.equals(arg0.optString("action"))){
					
					//chatMsg.add(new ChatMsg(data.optString("name"), data.optString("content")));
					Log.d("onDataChange", "("+data+")"
					+"数据："+data.opt("msg_send")+" "+data.optString("msg_send"));
				//}
			}
			@Override
			public void onConnectCompleted(Exception e) {
				// TODO Auto-generated method stub
				if(e==null){
					if(data.isConnected()){
						Log.i("onDataChange","成功：");
						data.subRowUpdate("ChatMsg", chatMsg.getObjectId());
					}
					Log.i("onDataChange","成功：");
				}else{
					Log.i("onDataChange","失败："+e.getMessage());
				}
				
			}
		});
        
    } 
    BmobRealTimeData data = new BmobRealTimeData();
    private void send()  
    {  
        String contString = mEditTextContent.getText().toString();  
        if (contString.length() > 0)  
        {  
            ChatMsgEntity entity = new ChatMsgEntity();  
            entity.setDate(getDate()); 
            entity.setPath(user.getObjectId()+"_temphead.png");
            entity.setMsgType(false);  
            entity.setText(contString);  
            mDataArrays.add(entity);
            mAdapter.notifyDataSetChanged();
            mEditTextContent.setText("");  
            LvChat_list.setSelection(LvChat_list.getCount() - 1);
            // 上面是显示数据，下面是传送数据到数据库
            chatMsg.SendMsg(contString,entity.getDate(),user);
        }  
    }  
      
    //获取日期  
    private String getDate() {  
        Calendar c = Calendar.getInstance();  
        String year = String.valueOf(c.get(Calendar.YEAR));  
        String month = String.valueOf(c.get(Calendar.MONTH));  
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));  
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        String mins = String.valueOf(c.get(Calendar.MINUTE));  
        StringBuffer sbBuffer = new StringBuffer();  
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins);   
        return sbBuffer.toString();  
    }   
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.IvSet_rb:
			finish();
			break;
		case R.id.btn_send:
			toast("发送信息");
			send();
			break;
		case R.id.tv_confrmBuy:
			intent.setClass(Chat.this, Buy.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("goodname",tv_goodName.getText().toString());
			intent.putExtra("goodprice",tv_price.getText().toString());
			intent.putExtra("position",getIntent().getIntExtra("position",0));
    		startActivity(intent);
    		finish();
			break;
		default:
			break;
		}
	}
	  void toast(String s){
			Toast.makeText(this, s, Toast.LENGTH_LONG).show();
		}
}
