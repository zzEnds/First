package com.lu.wang.E74;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class bak6E74 {
//	/**
	static int order = 6;
//	static List<List<String>> listRowCube = new LinkedList<List<String>>();
//	static List<List<String>> listRowCube = new ArrayList<List<String>>();

	static Map<String, int[]> wrongPosMap = new HashMap<String, int[]>();//WrongRow WrongCol
	static Map<int[], String> wrongPosMapReverse = new HashMap<int[], String>();//WrongRow WrongCol
	static Map<String, int[]> correctPosMap = new HashMap<String, int[]>();//CorrectRow CorrectCol
	
//	static Map<String, int[]> oprMap = new HashMap<String, int[]>();
	static Map<String, List<String>> keyToKey = new HashMap<String, List<String>>();
	static final String BLANK = "    ";
	static Object[][] cube = new Object[order][order];
	static Object[][] correctCube = new Object[order][order];
	
	public static void main(String[] args) {
		
//		Scanner in = new Scanner(System.in);
//		String n = in.next();
		
		String n = "0FGA45DEHCB6KQWMJPV2LRNI18XSTOU793YZ";
		String corStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		toCube(n);
		toCorrectCube(corStr);
		getCorMap();
		
//		getOprMap();//操作地图
//		for(String key : oprMap.keySet()) {
//			if(0 != oprMap.get(key)[0] || 0 != oprMap.get(key)[1]) {
//				System.out.println(key + "  " + oprMap.get(key)[0] +"  "+ oprMap.get(key)[1]);
//			}
//		}
		
//		printCube(cube);//原始Cube
//		toUp(5);
//		mainOperator();
//		mainOperator();
//		printCube(cube);//final Cube
		
	}
	
	public static void createKeytoKey() {
		List<List<String>> strPosList = new ArrayList<List<String>>();
		
		Iterator<Map.Entry<String, int[]>> it = wrongPosMap.entrySet().iterator();
		while(it.hasNext()) {
			List<String> strList = new ArrayList<String>();
			Map.Entry<String, int[]> entry = it.next();
			strList.add(entry.getKey());
			String nextStr = occupiedBy(correctPosMap.get(entry.getKey()));//正确坐标当前值
//			if(null != nextStr && ) {
//				
//			}
			it.remove();
			strList.add(nextStr);
		}
		
	}
	
	public static String occupiedBy(int[] pos) {
		Iterator<Map.Entry<String, int[]>> it = wrongPosMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, int[]> entry = it.next();
			if(entry.getValue()[0] == pos[0] && entry.getValue()[1] == pos[1]) {
				return entry.getKey();
			}
		}
		return null;
	}
	
//	public static void mainOperator() {
//		while(ifNotCorrect()) {
//			//优先整行或列大于零的坐标调整
//			//Cobe's rows
//			ifRowCorrect();
//			ifColCorrect();
//			printCube(cube);
//		}
//	}
//	
//	public static void ifRowCorrect() {
//		for(int i=0; i<order; i++) {
//			int oprTimes = 0;
//			for(int j=0; j<order; j++) {
//				if(j == 0) {
//					oprTimes = oprMap.get(cube[i][j])[0];
//				} else {
//					oprTimes = (oprMap.get(cube[i][j])[0] < oprTimes) ? oprMap.get(cube[i][j])[0] : oprTimes;
//				}
//			}
//			if(oprTimes > 0) {
//				toLeft(i, oprTimes);
//			}
//		}
//	}
//	
//	public static void ifColCorrect() {
//		for(int i=0; i<order; i++) {
//			int oprTimes = 0;
//			for(int j=0; j<order; j++) {
//				if(j == 0) {
//					oprTimes = oprMap.get(cube[j][i])[1];
//				} else {
//					oprTimes = oprMap.get(cube[j][i])[1] < oprTimes ? oprMap.get(cube[j][i])[1] : oprTimes;
//				}
//			}
//			if(oprTimes > 0) {
//				toUp(i, oprTimes);
//			}
//		}
//	}
//	
//	public static boolean ifNotCorrect() {
//		for(int[] value : oprMap.values()) {
//			if(value[0] > 0 || value[1] > 0) return true;
//		}
//		return false;
//	}
//	
//	public static void getOprMap() {
//		
//		Iterator<Map.Entry<String, int[]>> entries = positionMap.entrySet().iterator();
//		while(entries.hasNext()) {
//			Map.Entry<String, int[]> entry = entries.next();
//			String key = entry.getKey();
//			int[] opr = new int[2];
//			int[] tmp = positionMap.get(key);
//			
//			int wrongRow = tmp[0];
//			int wrongCol = tmp[1];
//			int corRow = tmp[2];
//			int corCol = tmp[3];
//			//纵向操作
//			if(wrongCol >= corCol) {
//				opr[0] = wrongCol - corCol;
//			} else {
//				opr[0] = order + wrongCol - corCol;
//			}
//			//横向操作
//			if(wrongRow >= corRow) {
//				opr[1] = wrongRow - corRow;
//			} else {
//				opr[1] = order + wrongRow - corRow;
//			}
//			oprMap.put(key, opr);
//		}
//	}
	
	public static void getCorMap() {
		for(int i=0; i<correctCube.length; i++) {
			for(int j=0; j<correctCube.length; j++) {
				int[] tmp = new int[2];
				tmp[0] = i;
				tmp[1] = j;
				correctPosMap.put(correctCube[i][j].toString(), tmp);
			}
		}
	}
	
	public static void toLeft(int rowNo, int times) {
//		rowNo--;
		while((times--) > 0) {
			//移动多次
			String tmp = cube[rowNo][0].toString();
			for(int i=0; i<order-1; i++) {
//				String currentKey = cube[rowNo][i].toString();
//				positionMap.get(currentKey)[0] -= 1;
//				oprMap.get(currentKey)[0]--;
				
				cube[rowNo][i] = cube[rowNo][i+1];//更新Cube
			}
//			positionMap.get(cube[rowNo][order-1])[0] -= 1;
			cube[rowNo][order-1] = tmp;
		}
	}
	
	public static void toUp(int colNo, int times) {
//		colNo--;
		while((times--) > 0) {
			//移动多次
			String tmp = cube[0][colNo].toString();
			for(int i=0; i<order-1; i++) {
//				String currentKey = cube[i][colNo].toString();
//				positionMap.get(currentKey)[1] -= 1;
//				oprMap.get(currentKey)[1]--;
				
				cube[i][colNo] = cube[i+1][colNo];//更新Cube
			}
//			positionMap.get(cube[order-1][colNo])[1] -= 1;
			cube[order-1][colNo] = tmp;
		}
	}
	
	public static Object[][] toCube(String str) {
		char[] chars = str.toCharArray();
		int cnt = 0;
		for(int i=0; i<order; i++) {
			for(int j=0; j<order; j++) {
				int[] tmp = new int[2];
				cube[i][j] = chars[cnt]+"";
				tmp[0] = i;
				tmp[1] = j;
				wrongPosMap.put(chars[cnt]+"", tmp);
				wrongPosMapReverse.put(tmp, chars[cnt++]+"");
			}
		}
		return cube;
	}
	
	public static Object[][] toCorrectCube(String str) {
		char[] chars = str.toCharArray();
		int cnt = 0;
		
		for(int i=0; i<order; i++) {
			for(int j=0; j<order; j++) {
				correctCube[i][j] = chars[cnt++]+"";
			}
		}
		return correctCube;
	}
	
	public static void printCube(Object[][] cube) {
		for(int i=0; i<order; i++) {
			for(int j=0; j<order; j++) {
				System.out.print(cube[i][j]);//用数组打印
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
	
}
