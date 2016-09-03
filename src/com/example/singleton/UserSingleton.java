package com.example.singleton;

import com.example.entity.MyUser;

public class UserSingleton {  //����ʽ�ĵ���ģʽ��ÿ�ε���ʵ��ʱ�Ŵ�����ã���ʱ�任�ռ�
	 private UserSingleton(){
	    }
	 private static class SingletonHolder{//����Ĭ�ϵĵ�������
		 private static MyUser instance = new MyUser("��ǰ���ο͵�½","������ͷ���½");
	 }
	 public static MyUser getInstance(){
	    return SingletonHolder.instance;
	 }
	 public static void setInstance(MyUser m){//�����Զ��嵥������
		 SingletonHolder.instance = m;
	//	return SingletonHolder.instance;
	}
}
