package com.example.singleton;

import com.example.entity.MyUser;

public class UserSingleton {
	 private static MyUser singleton;
	 private UserSingleton(){
	    }
	 public static MyUser getInstance(){
	    if(singleton==null){
	         singleton=new MyUser();
	         singleton.setNick("��ǰ���ο͵�½");
	         singleton.setMotto("������ͷ���½");
	    }
	    return singleton;
	 }
	 public static MyUser setInstance(MyUser m){
		 singleton = m;
		return singleton;
	}
}
