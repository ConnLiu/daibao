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
	    //�������ݵ�������  
	private ChatMsgViewAdapater mAdapter;  
	private ListView LvChat_list;  
	    //���������  
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
    private String[] msgArray = new String[]{"  �����ǣ�Ҫ�ú�ѧϰ���������ϣ�Ҫ�ú����Σ���Ҫ�̿Σ���Ҫ�ҿƣ����ý�ѧ�����Ƚ�ѧ�����ȡ�ö��ȣ����ȵ���ȡ��һ�ȣ�һ�ȵ���ȡ����־��",   
            "Ҧ���軹��ʲô�Ը�...",   
            "���У��������ϼǵ��ٰܲ��������ľͿ۵����֣�",   
            "��������ʲô�����˻���ô����",   
            "�����ֻ�Ӱ�콱ѧ�����ȣ����صĻ�����Ӱ���ҵ",   
            "�ۣ�ѧԺ��ô���˵���",  
            "��Ҫ���㲻�������ҵ������㲻�ܱ�ҵ��",   
            "Ҧ���裬��֪����(- -�Ҵ�������...)"};  
  
    private String[]dataArray = new String[]{"2012-09-01 18:00", "2012-09-01 18:10",   
            "2012-09-01 18:11", "2012-09-01 18:20",   
            "2012-09-01 18:30", "2012-09-01 18:35",   
            "2012-09-01 18:40", "2012-09-01 18:50"};  
    private final static int COUNT = 8;  
      
    //��ʼ��Ҫ��ʾ������   
    private void initData() {  
        for(int i = 0; i < COUNT; i++) {  
            ChatMsgEntity entity = new ChatMsgEntity();  
            entity.setDate(dataArray[i]);  
            if (i % 2 == 0)  
            {  
                entity.setName("Ҧ����");  
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
      
    //��ȡ����  
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
			toast("������Ϣ");
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
