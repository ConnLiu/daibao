package com.example.singleton;
import java.util.List;
import com.example.entity.CellInfo;
public class CellSingleton {
	private CellSingleton(){
	}
	private static class SingletonHolder{
		private static List<CellInfo> instance = null;
	}
	public static List<CellInfo> getInstance(){
		return SingletonHolder.instance;
	}
	public static void setInstance(List<CellInfo> m){
		SingletonHolder.instance = m;
	}
}
