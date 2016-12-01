package com.lu.wang.solved;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author lu.wang
 * A. Chat room
 * http://codeforces.com/problemset/problem/58/A
 */
public class A58 {
	
	public static void main(String[] args) {
		calculate();
		
	}
	
	public static void calculate() {
		Scanner in = new Scanner(System.in);
		String wordS = in.next();
		wordS = wordS.toUpperCase();
		String result = "NO";
		String H = "H", E = "E", L = "L", O = "O";
		
		int pos = wordS.indexOf(H);
		if(-1 != pos && wordS.length()>pos+1) {
			wordS = wordS.substring(wordS.indexOf(H)+1);
			
			pos = wordS.indexOf(E);
			if(-1 != pos && wordS.length()>pos+1) {
				wordS = wordS.substring(wordS.indexOf(E)+1);
				
				pos = wordS.indexOf(L);
				if(-1 != pos && wordS.length()>pos+1) {
					wordS = wordS.substring(wordS.indexOf(L)+1);
					
					pos = wordS.indexOf(L);
					if(-1 != pos && wordS.length()>pos+1) {
						wordS = wordS.substring(wordS.indexOf(L)+1);
						
						pos = wordS.indexOf(O);
						if(-1 != pos) {
							result = "YES";
						}
					}
				}
			}
		}
//		String sub1 = wordS.contains(H);
		
		System.out.println(result);
		
	}
	
}
