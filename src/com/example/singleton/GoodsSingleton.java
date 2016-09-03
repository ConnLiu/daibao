package com.example.singleton;

import java.util.List;
import com.example.entity.Goods;


public class GoodsSingleton {
	 private GoodsSingleton(){
	    }
	 private static class SingletonHolder{//����Ĭ�ϵĵ�������
		 private static List<Goods> instance = null;
	 }
	 public static List<Goods> getInstance(){
	    return SingletonHolder.instance;
	 }
	 public static void setInstance(List<Goods> m){//�����Զ��嵥������
		 SingletonHolder.instance = m;
	}
}
