package com.example.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

public class Goods extends BmobObject{
	private int type;
	private float price;
	private float old_price;
	private String name;
	private String intro;
	private int like_num;
	private String head_path;
	private int flag;    // 1-unchangeable 2-noPrice 3-auction
	private MyUser author;    //who published the goods
	private BmobRelation likes;  //users like that goods

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getLike_num() {
		return like_num;
	}

	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}

	public String getHead_path() {
		return head_path;
	}

	public void setHead_path(String head_path) {
		this.head_path = head_path;
	}

	public float getOld_price() {
		return old_price;
	}

	public void setOld_price(float old_price) {
		this.old_price = old_price;
	}
	
	public MyUser getAuthor() {
		return author;
	}

	public void setAuthor(MyUser author) {
		this.author = author;
	}
	
	public BmobRelation getLikes() {
		return likes;
	}

	public void setLikes(BmobRelation likes) {
		this.likes = likes;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
