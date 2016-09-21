package com.example.entity;

import android.R.integer;
import cn.bmob.v3.BmobObject;

public class OrderAll extends BmobObject{
	private String goodname;
	private String intro;
	private Goods good;
	private float price;
	private String head_path;
	private MyUser seller;
	private MyUser buyer;
	private String phone;
	private integer time;
	private int state;  //0=待发货 1=待收货  2=已结束

	public Goods getGood(){
		return good;
	}
	
	public void setGood(Goods good){
		this.good = good;
	}

	public String getGoodname(){
		return goodname;
	}
	
	public void setGoodname(String goodname){
		this.goodname = goodname;
	}

	public String getIntro(){
		return intro;
	}
	
	public void setIntro(String intro){
		this.intro = intro;
	}

	public float getPrice(){
		return price;
	}
	
	public void setPrice(float price){
		this.price = price;
	}

	public String getHead_path(){
		return head_path;
	}
	
	public void setHead_path(String head_path){
		this.head_path = head_path;
	}

	public MyUser getSeller(){
		return seller;
	}
	
	public void setSeller(MyUser seller){
		this.seller = seller;
	}

	public MyUser getBuyer(){
		return buyer;
	}
	
	public void setBuyer(MyUser buyer){
		this.buyer = buyer;
	}
	
	public integer getTime(){
		return time;
	}
	
	public void setTime(integer time){
		this.time = time;
	}

	public int getState(){
		return state;
	}
	
	public void setState(int state){
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
