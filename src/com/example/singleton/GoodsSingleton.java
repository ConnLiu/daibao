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
	 private static class SingletonHolder{//����Ĭ�ϵĵ�������
		 private static List<Goods> instance = null;
		 private static Map<String,ArrayList<Integer>> type=new HashMap<String,ArrayList<Integer>>();
			// 0�鼮��1���Σ�2 ��ߣ�3Ь����4��װ ��5��ױƷ ��6���� ��7 ����Ʒ��
			//8 ���룻9 �˶���Ʒ��10 С���� ��11������Ʒ ��12���ȣ�13 ����
		 //��arraylist����ĳһ����Ʒ������good�����
	 }
	 public static List<Goods> getInstance(){
	    return SingletonHolder.instance;
	 }
	 public static void setInstance(final List<Goods> m){//�����Զ��嵥������
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
