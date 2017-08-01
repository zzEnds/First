package com.lu.wang;

import java.util.ArrayList;
import java.util.List;

public class TextOnly {

	public static void main(String[] args) {
		test();
	}
	
	public static void test() {
		
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		list.add("eee");
		
		for(String tmp : list) {
			tmp = "111";
		}
		
		System.out.println(list.get(0));
	}
}
