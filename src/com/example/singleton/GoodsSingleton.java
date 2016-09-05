package com.example.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.R.integer;
import android.util.Log;
import com.example.entity.Goods;
public class GoodsSingleton {
	 public GoodsSingleton(){
	    }
	 private static class SingletonHolder{//设置默认的单例对象
		 private static List<Goods> instance = null;
		 private static Map<String,ArrayList<Integer>> type=new HashMap<String,ArrayList<Integer>>();
			// 0书籍；1首饰；2 玩具；3鞋包；4服装 ；5化妆品 ；6卡卷 ；7 工艺品；
			//8 数码；9 运动用品；10 小电器 ；11生活用品 ；12火热；13 最新
		 //用arraylist保存某一类商品的所有good的序号
	 }
	 public static List<Goods> getInstance(){
	    return SingletonHolder.instance;
	 }
	 public static void setInstance(final List<Goods> m){//设置自定义单例对象
		 SingletonHolder.instance = m;
		 Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<m.size();i++){
					Goods good = m.get(i); 
					ArrayList<Integer> temp=SingletonHolder.type.get(String.valueOf(good.getType()));
					if(temp==null){
						temp=new ArrayList<Integer>();
					}
					temp.add(i);
					SingletonHolder.type.put(String.valueOf(good.getType()),temp);
					Log.d("fenlei","type:"+good.getType());
				}
				Log.d("fenlei",""+SingletonHolder.type.get("0"));
				//m.get(SingletonHolder.type.get(0).get(0)).getName()
			}
		});
		t.start();
	}
	 
	public static List<Goods> getTypeGoods(String type){
		List<Goods> list = new ArrayList<Goods>();
		
		for(int i:SingletonHolder.type.get(type)){
			list.add(SingletonHolder.instance.get(i));
		}
		return list;
	}
	public static int getCentGood(String type,int position){
		
		Log.d("fenlei","xuhao:"+SingletonHolder.type.get(type).get(position));
		return SingletonHolder.type.get(type).get(position);
	}
	
}
