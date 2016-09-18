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
	 private static class SingletonHolder{//����Ĭ�ϵĵ�������
		 private static List<OrderAll> instance = null;
		 private static Map<String,ArrayList<Integer>> buy_user=new HashMap<String,ArrayList<Integer>>();
		 private static Map<String,ArrayList<Integer>> sell_user=new HashMap<String,ArrayList<Integer>>();
		 private static Map<String,ArrayList<Integer>> undone=new HashMap<String,ArrayList<Integer>>();
		 //private static String test = null;
		 //��arraylist����ĳһ����ҵ�����order�����
	 }
	 public static List<OrderAll> getInstance(){
	    return SingletonHolder.instance;
	 }
	 public static void setInstance(final List<OrderAll> m){//�����Զ��嵥������
		 SingletonHolder.instance = m;
				// TODO Auto-generated method stub
				for(int i=0;i<m.size();i++){
					OrderAll order = m.get(i); 
					Log.d("OrderSingleton","getSeller:"+order.getSeller().getObjectId()+" nick:"+order.getBuyer().getNick());
					
					
					
					
					
					if(order.getState()!=3){
						ArrayList<Integer> undone=SingletonHolder.undone.get(order.getSeller().getObjectId());
						if(undone==null){
							undone=new ArrayList<Integer>();
						}
						undone.add(i);
						SingletonHolder.undone.put(order.getBuyer().getObjectId(),undone);
					}else{
						ArrayList<Integer> temp=SingletonHolder.buy_user.get(order.getBuyer().getObjectId());
						ArrayList<Integer> temp1=SingletonHolder.sell_user.get(order.getSeller().getObjectId());
						Log.d("OrderSingleton","big big:"+temp1);
						//SingletonHolder.test = order.getSeller().getObjectId();
						if(temp==null){
							temp=new ArrayList<Integer>();
						}
						if(temp1==null){
							temp1=new ArrayList<Integer>();
						}
						temp1.add(i);
						temp.add(i);
						SingletonHolder.sell_user.put(order.getSeller().getObjectId(),temp1);
						SingletonHolder.buy_user.put(order.getBuyer().getObjectId(),temp);
					}
				}
				Log.d("OrderSingleton","buy_user"+SingletonHolder.buy_user+"sell_user"+SingletonHolder.sell_user);
				
	}
	 
	public static List<OrderAll> getOrder_Buyer(String userId){
		List<OrderAll> list = new ArrayList<OrderAll>();
		Log.d("OrderSingleton","SingletonHolder.buyuser:"+SingletonHolder.buy_user +" userId:"+userId);
		if(SingletonHolder.buy_user.get(userId)==null){
			return null;
		}
		for(int i:SingletonHolder.buy_user.get(userId)){
			list.add(SingletonHolder.instance.get(i));
		}
		return list;
	}
	public static List<OrderAll> getOrder_Seller(String userId){
		List<OrderAll> list = new ArrayList<OrderAll>();
		ArrayList<Integer> temp=SingletonHolder.sell_user.get(userId);
//		Log.d("OrderSingleton","SingletonHolder.selluser:"+SingletonHolder.sell_user+" temp:"+temp
//				+" test:"+SingletonHolder.test+" selluser:"+SingletonHolder.sell_user.get(SingletonHolder.test)
//				+" equal:"+SingletonHolder.test.equals(userId));
		if(temp==null){
			Log.d("OrderSingleton","userId:"+userId+" sell_user"+temp);
			return null;
		}
		for(int i:temp){
			list.add(SingletonHolder.instance.get(i));
		}
		Log.d("OrderSingleton","return list:"+list);
		return list;
	}
	
	public static List<OrderAll> getOrder_Undone(String userId){
		List<OrderAll> list = new ArrayList<OrderAll>();
		ArrayList<Integer> temp=SingletonHolder.undone.get(userId);
		if(temp==null){
			Log.d("OrderSingleton","userId:"+userId+" sell_user"+temp);
			return null;
		}
		for(int i:temp){
			list.add(SingletonHolder.instance.get(i));
		}
		Log.d("OrderSingleton","return list:"+list);
		return list;
	}
	public static int getCentGood(String user,int position){
		Log.d("fenlei","xuhao:"+SingletonHolder.buy_user.get(user).get(position));
		return SingletonHolder.buy_user.get(user).get(position);
	}
	
}
