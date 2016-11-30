package com.lu.wang.working;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author lu.wang
 * D. Plus and xor
 * http://codeforces.com/problemset/problem/76/D
 */
public class D76 {
	
	public static void main(String[] args) {
		String a = "/12/34/";
		System.out.println(a.split("/")[1]);
//		calculate();
		
	}
	
	public static void calculate() {
		
//		Scanner in = new Scanner(System.in);
//		//同1异0
//		BigInteger A = in.nextBigInteger();
//		BigInteger B = in.nextBigInteger();
//		BigInteger X = new BigInteger("0");
//		BigInteger Y = new BigInteger("0");
		int X = 10;
		int Y = 12;
		String binX = Integer.toBinaryString(X);
		String binY = Integer.toBinaryString(Y);
		
		System.out.println(binX + " " + binY);
		int xor = Integer.valueOf("1001",2);
		System.out.println("X: " + X + " Y: " + Y + " X+Y:" + (X+Y) + "  X xor Y: " + xor);
		
		System.out.println(Integer.valueOf("1111",2));
		System.out.println(Integer.valueOf("10000",2));
		
		System.out.println(Integer.toBinaryString(120));
//		System.out.println(X + " " + Y);
	}

}
