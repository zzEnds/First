package com.lu.wang.E74;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class bak1E74 {
	
	static int order = 6;
//	static List<List<String>> listRowCube = new LinkedList<List<String>>();
	static List<List<String>> listRowCube = new ArrayList<List<String>>();
	static Map<String, Integer> colMap = new HashMap<String, Integer>();
	static Map<String, Integer> rowMap = new HashMap<String, Integer>();
	static Map<String, Integer> corColMap = new HashMap<String, Integer>();
	static Map<String, Integer> corRowMap = new HashMap<String, Integer>();
	static int[] pos = new int[2];//up & left
	static Map<String, int[]> oprMap = new HashMap<String, int[]>();
	static final String BLANK = "    ";
	
	public static void main(String[] args) {
		
//		Scanner in = new Scanner(System.in);
//		String n = in.next();
		Object[][] cube = new Object[order][order];
		Object[][] correctCube = new Object[order][order];
		
		String n = "0FGA45DEHCB6KQWMJPV2LRNI18XSTOU793YZ";
		String corStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		cube = toCube(n);
		correctCube = toCorrectCube(corStr);
		getCorMap(correctCube);
		getOprMap();//操作地图
		
		printCube(cube);//原始Cube
		toUp(2);toUp(2);toUp(2);toUp(2);
		toLeft(3);toLeft(3);
		toUp(2);toUp(2);
		toLeft(3);toLeft(3);toLeft(3);toLeft(3);
		
		cube = refreshFromRow(cube);
		printCube(cube);//final Cube
		
	}
	
	public static void getOprMap() {
		for(Map.Entry<String, Integer> entry : colMap.entrySet()) {
			int[] opr = new int[2];
			String keyValue = entry.getKey();
			int wrongRow = rowMap.get(keyValue);
			int wrongCol = colMap.get(keyValue);
			int corRow = corRowMap.get(keyValue);
			int corCol = corColMap.get(keyValue);
			//纵向操作
			if(wrongCol >= corCol) {
				opr[0] = wrongCol - corCol;
			} else {
				opr[0] = order + wrongCol - corCol;
			}
			//横向操作
			if(wrongRow >= corRow) {
				opr[1] = wrongRow - corRow;
			} else {
				opr[1] = order + wrongRow - corRow;
			}
			oprMap.put(keyValue, opr);
		}
	}
	
	public static void getCorMap(Object[][] correctCube) {
		for(int i=0; i<correctCube.length; i++) {
			for(int j=0; j<correctCube.length; j++) {
				corRowMap.put(correctCube[i][j].toString(), i);
				corColMap.put(correctCube[i][j].toString(), j);
			}
		}
	}
	
	public static void toLeft(int rowNo) {
		rowNo--;
		String tmp = listRowCube.get(rowNo).get(0);
		listRowCube.get(rowNo).remove(0);
		listRowCube.get(rowNo).add(tmp);
	}
	
	public static void toUp(int colNo) {
		colNo--;
		String tmp = listRowCube.get(0).get(colNo);
		for(int i=0; i<order-1; i++) {
			listRowCube.get(i).set(colNo, listRowCube.get(i+1).get(colNo));
		}
		listRowCube.get(order-1).set(colNo, tmp);
	}
	
	public static Object[][] toCube(String str) {
		Object[][] cube = new Object[order][order];
		char[] chars = str.toCharArray();
		int cnt = 0;
		for(int i=0; i<order; i++) {
			List<String> rowCube = new LinkedList<String>();
			for(int j=0; j<order; j++) {
				cube[i][j] = chars[cnt]+"";
				rowMap.put(chars[cnt]+"", i);
				colMap.put(chars[cnt]+"", j);
				rowCube.add(chars[cnt++]+"");
			}
			listRowCube.add(rowCube);
		}
		return cube;
	}
	
	public static Object[][] toCorrectCube(String str) {
		char[] chars = str.toCharArray();
		Object[][] cube = new Object[order][order];
		int cnt = 0;
		
		for(int i=0; i<order; i++) {
			for(int j=0; j<order; j++) {
				cube[i][j] = chars[cnt++]+"";
			}
		}
		return cube;
	}
	
	public static Object[][] refreshFromRow(Object[][] cube) {
		int i=0;
		for(List<String> element : listRowCube) {
			cube[i++] = (Object[]) element.toArray();
//			cube[i++] = (String[]) element.toArray();
		}
		return cube;
	}
	
	public static void printCube(Object[][] cube) {
		for(int i=0; i<order; i++) {
			for(int j=0; j<order; j++) {
				System.out.print(cube[i][j]);//用数组打印
//				System.out.print(listRowCube.get(i).get(j));//用RowList打印
				if((order-1) != j) {
					System.out.print(BLANK);
				} else {
					System.out.println("\n");
				}
			}
		}
		System.out.println();
		System.out.println("(๑•̀ㅂ•́)و✧");
		System.out.println();
	}
	
	public static void randomRoll() {
		Random r = new Random();
		int x = 0;
		for(int i=0; i<6; i++) {
			//随意重置
			while(0 == x){x = r.nextInt(6);}
			toUp(x);
			x = r.nextInt(6);
			toLeft(x);
		}
	}
	
	public static void toRight(int rowNo) {
		rowNo--;
		String tmp = listRowCube.get(rowNo).get(order-1);
		listRowCube.get(rowNo).remove(order-1);
		listRowCube.get(rowNo).add(0, tmp);
	}
	
	public static void toDown(int colNo) {
		colNo--;
		String tmp = listRowCube.get(order-1).get(colNo);
		for(int i=order-1; i>0; i--) {
			listRowCube.get(i).set(colNo, listRowCube.get(i-1).get(colNo));
		}
		listRowCube.get(0).set(colNo, tmp);
	}
}
