package com.example.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.R.integer;
import android.util.Log;
import com.example.entity.Goods;
import com.example.entity.MyUser;
public class GoodsSingleton {
	 public GoodsSingleton(){
	    }
	 private static class SingletonHolder{//设置默认的单例对象
		 private static List<Goods> instance = null;
		 private static Map<String,ArrayList<Integer>> type=new HashMap<String,ArrayList<Integer>>();
			// 0书籍；1首饰；2 玩具；3鞋包；4服装 ；5化妆品 ；6卡卷 ；7 工艺品；
			//8 数码；9 运动用品；10 小电器 ；11生活用品 ；12火热；13 用户发布
		 //用arraylist保存某一类商品的所有good的序号
	 }
	 public static List<Goods> getInstance(){
	    return SingletonHolder.instance;
	 }
	 public static void setInstance(final List<Goods> m){//设置自定义单例对象
		 SingletonHolder.instance = m;
		 final MyUser user = UserSingleton.getInstance();
		 SingletonHolder.type.clear();
		 SingletonHolder.type = new HashMap<String,ArrayList<Integer>>();
//		 Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<m.size();i++){
					Goods good = m.get(i); 
					
					ArrayList<Integer> temp=SingletonHolder.type.get(String.valueOf(good.getType()));					
					if(temp==null){
						temp=new ArrayList<Integer>();
					}
					temp.add(i);
					SingletonHolder.type.put(String.valueOf(good.getType()),temp);
					if(user!=null){
						ArrayList<Integer> user_arr=SingletonHolder.type.get("13");
						if(user_arr==null){
							user_arr = new ArrayList<Integer>();
						}
						if(user.getObjectId()!=null&&user.getObjectId().equals(good.getAuthor().getObjectId())){
							user_arr.add(i);
							SingletonHolder.type.put("13",user_arr);
						}
					}
//					Log.d("fenlei","3temp:"+temp+"   SingletonHolder.type.get(String.valueOf(good.getType())):"+SingletonHolder.type);
//					Log.d("fenlei","i:"+i+"  type:"+good.getType()+" good.getAuthor().getObjectId():"+good.getAuthor().getObjectId());
				}
				Log.d("fenlei",""+SingletonHolder.type);
				//m.get(SingletonHolder.type.get(0).get(0)).getName()
//			}
//		});
//		t.start();
	}
	 
	public static List<Goods> getTypeGoods(String type){
		List<Goods> list = new ArrayList<Goods>();
		if(type.equals("12")){
			if(SingletonHolder.instance.size()<=20)
				return SingletonHolder.instance;
			else{
				list=SingletonHolder.instance.subList(0,20);
				return list;
			}
		}
		for(int i:SingletonHolder.type.get(type)){
			list.add(SingletonHolder.instance.get(i));
		}
		return list;
	}
	public static int getCentGood(String type,int position){
		
		Log.d("fenlei","xuhao:"+SingletonHolder.type.get(type).get(position));
		return SingletonHolder.type.get(type).get(position);
	}
	public static void delCentGood(int position){
		Log.d("delCentGood","type :"+SingletonHolder.type+"type");
		Goods temp = SingletonHolder.instance.get(SingletonHolder.instance.size()-1); //get the last
		Goods goal = SingletonHolder.instance.get(position);
		SingletonHolder.instance.remove(SingletonHolder.instance.size()-1);
		SingletonHolder.instance.remove(position);
		SingletonHolder.instance.add(position,temp);
		ArrayList<Integer> list=SingletonHolder.type.get(String.valueOf(goal.getType()));	
		if(list!=null){
			for(int i=0;i<list.size();i++){
				if(position==list.get(i)){
					list.remove(i);
					break;
				}
			}
		}
		
		int position_temp = SingletonHolder.instance.size()-1;
		for(int j=0;j<12;j++)
		{
			list=SingletonHolder.type.get(String.valueOf(j));
			if(list!=null){
				for(int i=0;i<list.size();i++){
					if(position_temp==list.get(i)){
						list.remove(i);
						list.add(position);
						break;
					}
				}
			}
		}
		list=SingletonHolder.type.get("13");
		if(list!=null){
			for(int i=0;i<list.size();i++){
				if(position_temp==list.get(i)){
					list.remove(i);
					list.add(position);
					break;
				}
			}
		}
		Log.d("delCentGood","type :"+SingletonHolder.type);
		
	}
	public static int getPosition(String type,int positon){
		return SingletonHolder.type.get(type).get(positon);
	}
}
