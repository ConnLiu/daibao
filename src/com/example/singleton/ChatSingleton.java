package com.example.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.ChatMsgEntity;

public class ChatSingleton {
	 public ChatSingleton(){
	    }
	 private static class SingletonHolder{//����Ĭ�ϵĵ�������
		 private static List<ChatMsgEntity> instance = null;
		 private static Map<String,ArrayList<Integer>> buy_user= new HashMap<String, ArrayList<Integer>>();
		 private static Map<String,ArrayList<Integer>> sell_user= new HashMap<String, ArrayList<Integer>>();
		 //private static String test = null;
		 //��arraylist����ĳһ����ҵ�����order�����
	 }
}
