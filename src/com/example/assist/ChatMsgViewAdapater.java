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
	
	//ListView视图的内容由IMsgViewType决定
	public static interface IMsgViewType
	{
		//对方发来的信息
		int IMVT_COM_MSG = 0;
		//自己发出的信息
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

    //获取ListView的项个数
    public int getCount() {
        return data.size();
    }

    //获取项
    public Object getItem(int position) {
        return data.get(position);
    }

    //获取项的ID
    public long getItemId(int position) {
        return position;
    }

    //获取项的类型
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

	//获取项的类型数
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	//获取View
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	ChatMsgEntity entity = data.get(position);
    	boolean isComMsg = entity.getMsgType();
    		
    	ViewHolder viewHolder = null;	
	    if (convertView == null)
	    {
	    	  if (isComMsg)
			  {
	    		  //如果是对方发来的消息，则显示的是左气泡
				  convertView = mInflater.inflate(R.layout.chat_list_item_right, null);
			  }else{
				  //如果是自己发出的消息，则显示的是右气泡
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
    
    //通过ViewHolder显示项的内容
    static class ViewHolder { 
        public TextView tvSendTime;
        public TextView tvContent;
        public ImageView chat_head;
        public boolean isComMsg = true;
    }
    
}