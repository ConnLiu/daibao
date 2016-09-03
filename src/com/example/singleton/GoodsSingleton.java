package com.example.singleton;

import java.util.List;
import com.example.entity.Goods;


public class GoodsSingleton {
	 private GoodsSingleton(){
	    }
	 private static class SingletonHolder{//设置默认的单例对象
		 private static List<Goods> instance = null;
	 }
	 public static List<Goods> getInstance(){
	    return SingletonHolder.instance;
	 }
	 public static void setInstance(List<Goods> m){//设置自定义单例对象
		 SingletonHolder.instance = m;
	}
}
