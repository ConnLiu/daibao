package com.example.assist;

import android.R.integer;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.example.easyshop.R;
import com.example.entity.ChatMsgEntity;

public class ChatMsgViewAdapater extends BaseAdapter {
	
	//ListView��ͼ��������IMsgViewType����
	public static interface IMsgViewType
	{
		//�Է���������Ϣ
		int IMVT_COM_MSG = 0;
		//�Լ���������Ϣ
		int IMVT_TO_MSG = 1;
	}
	
    private static final String TAG = ChatMsgViewAdapater.class.getSimpleName();
    private List<ChatMsgEntity> data;
    private Context context;  
    private LayoutInflater mInflater;

    public ChatMsgViewAdapater(Context context, List<ChatMsgEntity> data) {
        this.context = context;
        this.data = data;
        mInflater = LayoutInflater.from(context);
    }

    //��ȡListView�������
    public int getCount() {
        return data.size();
    }

    //��ȡ��
    public Object getItem(int position) {
        return data.get(position);
    }

    //��ȡ���ID
    public long getItemId(int position) {
        return position;
    }

    //��ȡ�������
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
	 	ChatMsgEntity entity = data.get(position);
	 	
	 	if (entity.getMsgType())
	 	{
	 		return IMsgViewType.IMVT_COM_MSG;
	 	}else{
	 		return IMsgViewType.IMVT_TO_MSG;
	 	}
	 	
	}

	//��ȡ���������
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	//��ȡView
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	ChatMsgEntity entity = data.get(position);
    	boolean isComMsg = entity.getMsgType();
    		
    	ViewHolder viewHolder = null;	
	    if (convertView == null)
	    {
	    	  if (isComMsg)
			  {
	    		  //����ǶԷ���������Ϣ������ʾ����������
				  convertView = mInflater.inflate(R.layout.chat_list_item_right, null);
			  }else{
				  //������Լ���������Ϣ������ʾ����������
				  convertView = mInflater.inflate(R.layout.chat_list_item_left, null);
			  }

	    	  viewHolder = new ViewHolder();
			  viewHolder.tvSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
			  viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
			  viewHolder.chat_head = (ImageView) convertView.findViewById(R.id.chat_head);
			  viewHolder.isComMsg = isComMsg;
			  
			  convertView.setTag(viewHolder);
	    }else{
	        viewHolder = (ViewHolder) convertView.getTag();
	    }
	    viewHolder.tvSendTime.setText(entity.getDate());
	    viewHolder.tvContent.setText(entity.getText());
	    
	    FileInputStream localstream = null;
		try {
			localstream = context.openFileInput(entity.getPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bitmap bm = BitmapFactory.decodeStream(localstream);
		if(bm != null){
			viewHolder.chat_head.setImageBitmap(bm);
		}else{
			viewHolder.chat_head.setImageResource(R.drawable.tip_selected);
		}
	   
	    return convertView;
    }
    
    //ͨ��ViewHolder��ʾ�������
    static class ViewHolder { 
        public TextView tvSendTime;
        public TextView tvContent;
        public ImageView chat_head;
        public boolean isComMsg = true;
    }
    
}