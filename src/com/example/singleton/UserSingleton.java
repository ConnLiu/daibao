package com.example.singleton;

import com.example.entity.MyUser;

public class UserSingleton {
	 private static MyUser singleton;
	 private UserSingleton(){
	    }
	 public static MyUser getInstance(){
	    if(singleton==null){
	         singleton=new MyUser();
	         singleton.setNick("当前是游客登陆");
	         singleton.setMotto("点击左侧头像登陆");
	    }
	    return singleton;
	 }
	 public static MyUser setInstance(MyUser m){
		 singleton = m;
		return singleton;
	}
}
