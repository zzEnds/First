package com.lu.wang.E74;

import java.util.HashMap;
import java.util.Map;

public class bak4CalculateE74 {
//	/**
	static int order = 6;
//	static List<List<String>> listRowCube = new LinkedList<List<String>>();
//	static List<List<String>> listRowCube = new ArrayList<List<String>>();
	
	
	static int[] pos = new int[2];//up & left
	static Map<String, int[]> oprMap = new HashMap<String, int[]>();
	static final String BLANK = "\t";
	static Object[][] cube = new Object[order][order];
//	static Object[][] correctCube = new Object[order][order];
	
	public static void main(String[] args) {
		
//		Scanner in = new Scanner(System.in);
//		String n = in.next();
//		String n = "0FGA45DEHCB6KQWMJPV2LRNI18XSTOU793YZ";
		
		String corStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		toCube(corStr);
		
		printCube(cube);//原始Cube
		toLeft(1, 1);
		toUp(1, 1);
		toLeft(1, 6-1);
		toUp(1, 6-1);
//		toLeft(0, 6-1);
//		toUp(0, 1);
		printCube(cube);//final Cube
		
	}
	
	
	public static void toLeft(int rowNo, int times) {
		rowNo--;
		while((times--) > 0) {
			//移动多次
			String tmp = cube[rowNo][0].toString();
			for(int i=0; i<order-1; i++) {
				cube[rowNo][i] = cube[rowNo][i+1];//更新Cube
			}
			cube[rowNo][order-1] = tmp;
		}
	}
	
	public static void toUp(int colNo, int times) {
		colNo--;
		while((times--) > 0) {
			//移动多次
			String tmp = cube[0][colNo].toString();
			for(int i=0; i<order-1; i++) {
				cube[i][colNo] = cube[i+1][colNo];//更新Cube
			}
			cube[order-1][colNo] = tmp;
		}
	}
	
	public static Object[][] toCube(String str) {
		char[] chars = str.toCharArray();
		int cnt = 0;
		
		for(int i=0; i<order; i++) {
			for(int j=0; j<order; j++) {
				cube[i][j] = chars[cnt++]+"";
			}
		}
		return cube;
	}
	
	public static void printCube(Object[][] cube) {
		for(int i=0; i<order; i++) {
			for(int j=0; j<order; j++) {
				System.out.print(cube[i][j]);//用数组打印
				if((order-1) != j) {
					System.out.print(BLANK);
				} else {
					System.out.println();
				}
			}
		}
		System.out.println();
		System.out.println("(๑•̀ㅂ•́)و✧");
		System.out.println();
	}
	
}
