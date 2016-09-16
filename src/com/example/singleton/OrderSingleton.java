package com.example.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.example.entity.OrderAll;

public class OrderSingleton {
	 public OrderSingleton(){
	    }
	 private static class SingletonHolder{//设置默认的单例对象
		 private static List<OrderAll> instance = null;
		 private static Map<String,ArrayList<Integer>> user=new HashMap<String,ArrayList<Integer>>();
		 //用arraylist保存某一个用户的所有order的序号
	 }
	 public static List<OrderAll> getInstance(){
	    return SingletonHolder.instance;
	 }
	 public static void setInstance(final List<OrderAll> m){//设置自定义单例对象
		 SingletonHolder.instance = m;
		 Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<m.size();i++){
					OrderAll order = m.get(i); 
					ArrayList<Integer> temp=SingletonHolder.user.get(String.valueOf(order.getBuyer().getObjectId()));
					if(temp==null){
						temp=new ArrayList<Integer>();
					}
					temp.add(i);
					SingletonHolder.user.put(String.valueOf(order.getBuyer()),temp);
					
					temp=SingletonHolder.user.get(String.valueOf(order.getSeller().getObjectId()));
					if(temp==null){
						temp=new ArrayList<Integer>();
					}
					temp.add(i);
					SingletonHolder.user.put(String.valueOf(order.getSeller()),temp);
					Log.d("fenlei","type:"+order.getBuyer());
				}
				Log.d("fenlei",""+SingletonHolder.user.get("0"));
				//m.get(SingletonHolder.type.get(0).get(0)).getName()
			}
		});
		t.start();
	}
	 
	public static List<OrderAll> getOrder(String userId){
		
		List<OrderAll> list = new ArrayList<OrderAll>();
		for(int i:SingletonHolder.user.get(userId)){
			list.add(SingletonHolder.instance.get(i));
		}
		return list;
	}
	public static int getCentGood(String user,int position){
		
		Log.d("fenlei","xuhao:"+SingletonHolder.user.get(user).get(position));
		return SingletonHolder.user.get(user).get(position);
	}
	
}
