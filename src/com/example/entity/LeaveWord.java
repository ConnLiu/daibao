package com.example.entity;

import cn.bmob.v3.BmobObject;

public class LeaveWord extends BmobObject{
	private MyUser user;     //who write the message
	private String content;
	private String goodId;
	
	public MyUser getUser(){
		return user;
	}
	
	public void setUser(MyUser user){
		this.user = user;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getGoodId(){
		return goodId;
	}
	
	public void setGoodId(String goodId){
		this.goodId = goodId;
	}
}
