package com.example.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.util.Log;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ChatMsg extends BmobObject{
	private List<String> msg_get ,msg_get_time;
	private List<String> msg_send ,msg_send_time;
	private MyUser user_send,user_get;
	private String goodsObject;
	public ChatMsg(MyUser send,MyUser get){
		this.user_get = get;
		this.user_send = send;
		msg_get = new ArrayList<String>();
		msg_get.add("亲爱的呆宝用户，您好！");
		msg_send = new ArrayList<String>();
		msg_get_time = new ArrayList<String>();
		msg_send_time = new ArrayList<String>();
		msg_get_time.add(getDate());
	}
	public ChatMsg(){}
	public void SendMsg(String msg,String time,MyUser send){
		if(send.getObjectId().equals(user_get.getObjectId())){
			msg_get.add(msg);
			msg_get_time.add(time);
		}else{
			msg_send.add(msg);
			msg_send_time.add(time);
		}
		this.update(this.getObjectId(), new UpdateListener() {
		    @Override
		    public void done(BmobException e) {
		        if(e==null){
		            Log.i("ChatMsg","更新成功");
		        }else{
		            Log.i("ChatMsg","更新失败："+e.getMessage()+","+e.getErrorCode());
		        }
		    }
		});
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
	public String getGoodsObject() {
		return goodsObject;
	}
	public void setGoodsObject(String goodsObject) {
		this.goodsObject = goodsObject;
	}   
	
}
