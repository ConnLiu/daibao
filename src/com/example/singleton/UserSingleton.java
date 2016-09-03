package com.example.singleton;

import com.example.entity.MyUser;

public class UserSingleton {  //懒汉式的单例模式，每次调用实例时才创建获得，以时间换空间
	 private UserSingleton(){
	    }
	 private static class SingletonHolder{//设置默认的单例对象
		 private static MyUser instance = new MyUser("当前是游客登陆","点击左侧头像登陆");
	 }
	 public static MyUser getInstance(){
	    return SingletonHolder.instance;
	 }
	 public static void setInstance(MyUser m){//设置自定义单例对象
		 SingletonHolder.instance = m;
	//	return SingletonHolder.instance;
	}
}
