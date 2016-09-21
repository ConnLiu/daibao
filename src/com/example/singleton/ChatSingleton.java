package com.example.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.ChatMsgEntity;

public class ChatSingleton {
	 public ChatSingleton(){
	    }
	 private static class SingletonHolder{//设置默认的单例对象
		 private static List<ChatMsgEntity> instance = null;
		 private static Map<String,ArrayList<Integer>> buy_user= new HashMap<String, ArrayList<Integer>>();
		 private static Map<String,ArrayList<Integer>> sell_user= new HashMap<String, ArrayList<Integer>>();
		 //private static String test = null;
		 //用arraylist保存某一个买家的所有order的序号
	 }
}
