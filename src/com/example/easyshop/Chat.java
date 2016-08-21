package com.example.easyshop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.assist.ChatMsgViewAdapater;
import com.example.assist.MessagelistAdapter;
import com.example.assist.ZanslistAdapter;
import com.example.customview.ListViewForScrollView;
import com.example.entity.ChatMsgEntity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Chat extends Activity implements OnClickListener{
	private Button mBtnSend;  
	private Button mBtnBack;  
	private EditText mEditTextContent;  
	    //聊天内容的适配器  
	private ChatMsgViewAdapater mAdapter;  
	private ListView LvChat_list;  
	    //聊天的内容  
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>(); 
	private ImageView IvSet_rb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
 
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		LvChat_list = (ListViewForScrollView)findViewById(R.id.LvChat_list);
		
	    mBtnSend = (Button) findViewById(R.id.btn_send);  
	    mBtnSend.setOnClickListener(this);  
	    mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
		IvSet_rb = (ImageView)findViewById(R.id.IvSet_rb);
		IvSet_rb.setOnClickListener(this);
		initData();
		
	}
    private String[] msgArray = new String[]{"  孩子们，要好好学习，天天向上！要好好听课，不要翘课！不要挂科，多拿奖学金！三等奖学金的争取拿二等，二等的争取拿一等，一等的争取拿励志！",   
            "姚妈妈还有什么吩咐...",   
            "还有，明天早上记得跑操啊，不来的就扣德育分！",   
            "德育分是什么？扣了会怎么样？",   
            "德育分会影响奖学金评比，严重的话，会影响毕业",   
            "哇！学院那么不人道？",  
            "你要是你不听话，我当场让你不能毕业！",   
            "姚妈妈，我知错了(- -我错在哪了...)"};  
  
    private String[]dataArray = new String[]{"2012-09-01 18:00", "2012-09-01 18:10",   
            "2012-09-01 18:11", "2012-09-01 18:20",   
            "2012-09-01 18:30", "2012-09-01 18:35",   
            "2012-09-01 18:40", "2012-09-01 18:50"};  
    private final static int COUNT = 8;  
      
    //初始化要显示的数据   
    private void initData() {  
        for(int i = 0; i < COUNT; i++) {  
            ChatMsgEntity entity = new ChatMsgEntity();  
            entity.setDate(dataArray[i]);  
            if (i % 2 == 0)  
            {  
                entity.setName("姚妈妈");  
                entity.setMsgType(true);  
            }else{  
                entity.setName("Shamoo");  
                entity.setMsgType(false);  
            }  
              
            entity.setText(msgArray[i]);  
            mDataArrays.add(entity);  
        }  
        mAdapter = new ChatMsgViewAdapater(this, mDataArrays);  
        LvChat_list.setAdapter(mAdapter);  
    }  
    private void send()  
    {  
        String contString = mEditTextContent.getText().toString();  
        if (contString.length() > 0)  
        {  
            ChatMsgEntity entity = new ChatMsgEntity();  
            entity.setDate(getDate());  
            entity.setName("");  
            entity.setMsgType(false);  
            entity.setText(contString);  
            mDataArrays.add(entity);  
            mAdapter.notifyDataSetChanged();  
            mEditTextContent.setText("");  
            LvChat_list.setSelection(LvChat_list.getCount() - 1);  
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
		switch (v.getId()) {
		case R.id.IvSet_rb:
			finish();
			break;
		case R.id.btn_send:
			toast("发送信息");
			send();
			break;
		default:
			break;
		}
	}
	  void toast(String s){
			Toast.makeText(this, s, Toast.LENGTH_LONG).show();
		}
}
