package com.example.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.example.entity.LeaveWord;

public class LeavewordSingleton {
	public LeavewordSingleton(){}
	private static class SingletonHolder{
		private static List<LeaveWord> instance = null;
		private static Map<String,ArrayList<Integer>> goodId=new HashMap<String,ArrayList<Integer>>();
	    //以goodId作为关键词，记录留言序号
	}
	public static List<LeaveWord> getInstance(){
		return SingletonHolder.instance;
	}
	 public static void setInstance(final List<LeaveWord> m){//设置自定义单例对象
		 SingletonHolder.instance = m;
	     Log.i("GoodId","GoodId:begin");
		 Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<m.size();i++){
					LeaveWord lword = m.get(i); 
					ArrayList<Integer> temp=SingletonHolder.goodId.get(String.valueOf(lword.getGoodId()));
					Log.i("GoodId","GoodId:"+lword.getGoodId()+"tmp"+temp);
					if(temp==null){//如果没找着关键字？申请空间
						temp=new ArrayList<Integer>();
					}
					temp.add(i);     //将序号加入list
					SingletonHolder.goodId.put(String.valueOf(lword.getGoodId()),temp); //添加list
					Log.i("GoodId","GoodId:"+lword.getGoodId());
					Log.i("GoodId","GoodId:"+lword.getGoodId()+"tmp2"+temp.get(0).toString());
				}
			}
		});
		t.start();
	}
	 
	public static List<LeaveWord> getGoodsLWord(String goodId){//获取goodId的list

		List<LeaveWord> list = new ArrayList<LeaveWord>();
		if(SingletonHolder.goodId.get(goodId) == null){
			return null;
		}
		for(int i:SingletonHolder.goodId.get(goodId)){
			list.add(SingletonHolder.instance.get(i));
		}
		return list;
	}
	public static int getCentGood(String goodId,int position){

		Log.d("GoodId","getcentgood");
		Log.d("fenlei","xuhao:"+SingletonHolder.goodId.get(goodId).get(position));
		return SingletonHolder.goodId.get(goodId).get(position);
	}
}
