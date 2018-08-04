package com.lu.wang.javabase;


public class AboutClassLoader {
	
	public static void main(String[] args) {
		
		ClassLoader loader = String.class.getClassLoader();//JavaNio.class.getClassLoader();
		
		while(loader != null) {
			System.out.println(loader);
			loader = loader.getParent();
		}
		System.out.println(loader);
	}

}
