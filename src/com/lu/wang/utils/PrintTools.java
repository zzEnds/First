package com.lu.wang.utils;

import java.util.List;

public class PrintTools {
	
	
	public static void asString(List<Integer> list) {
		
		System.out.println("==============Start================");
		for(int i=0; i<list.size(); i++) {
			System.out.println("No." + i + " -- " + list.get(i));
		}
		System.out.println("===============End===============");
		
	}

}
