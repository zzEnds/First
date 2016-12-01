package com.lu.wang.solved;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author lu.wang
 * A. Boy or Girl
 * http://codeforces.com/problemset/problem/236/A
 */
public class A236 {
	
	public static void main(String[] args) {
		calculate();
		
	}
	
	public static void calculate() {
		Scanner in = new Scanner(System.in);
		Set<String> set = new HashSet<>();
		String wordS = in.next();
		String[] wordsArray = wordS.split("");
		for (int i = 0; i < wordsArray.length; i++) {
			if((!"".equals(wordsArray[i].trim())) && (!set.contains(wordsArray[i]))) {
				set.add(wordsArray[i]);
			}
		}
		
		String result = "CHAT WITH HER!";
		if(set.size() %2 != 0) {
			result = "IGNORE HIM!";
		}
		System.out.println(result);
		
	}
	
}
