package com.lu.wang.E74;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class bak7RepairE74 {
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
	static StringBuffer steps = new StringBuffer();
	
	public static void main(String[] args) {
		
//		Scanner in = new Scanner(System.in);
//		String n = in.next();
		
		String n = "0FGA45DEHCB6KQWMJPV2LRNI18XSTOU793YZ";
		String corStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		toCube(n);
//		toCorrectCube(corStr);
//		getCorMap();
		
//		getOprMap();//操作地图
//		for(String key : oprMap.keySet()) {
//			if(0 != oprMap.get(key)[0] || 0 != oprMap.get(key)[1]) {
//				System.out.println(key + "  " + oprMap.get(key)[0] +"  "+ oprMap.get(key)[1]);
//			}
//		}
		
		printCube(cube);//原始Cube
		toLeft(2, 2);toDown(1, 2);toRight(2, 2);toUp(1, 2);
		toLeft(4, 1);toUp(1, 2);toRight(4, 1);toDown(1, 2);
		toLeft(1, 1);toDown(1, 1);toRight(1, 1);toUp(1, 1);
		toLeft(2, 1);toDown(1, 2);toRight(2, 1);toUp(1, 2);
		toLeft(3, 3);toDown(1, 3);toRight(3, 3);toUp(1, 3);
		toUp(2, 1);toLeft(2, 2);toUp(2, 3);toRight(2, 2);toUp(2, 2);
		toLeft(3, 1);toUp(2, 3);toRight(3, 1);toDown(2, 3);
		toRight(3, 1);toDown(2, 3);toLeft(3, 1);toUp(2, 3);
		toDown(3, 2);toRight(0, 1);toUp(3, 2);toLeft(0, 1);
		toLeft(2, 1);toDown(0, 1);toRight(2, 1);toUp(0, 1);
		toLeft(3, 2);toDown(0, 2);toRight(3, 2);toUp(0, 2);
		toDown(1, 2);toRight(1, 1);toUp(1, 2);toLeft(1, 1);
		toRight(2, 1);toDown(0, 1);toLeft(2, 1);toUp(0, 2);
		toLeft(1, 3);toDown(0, 1);toRight(1, 3);
		toLeft(4, 1);toDown(0, 3);toRight(4, 1);toUp(0, 3);
		toRight(5, 1);toUp(3, 2);toLeft(5, 1);toUp(3, 2);
		toRight(1, 3);toUp(3, 2);toLeft(1, 3);
		toLeft(5, 1);toRight(0, 1);toDown(2, 1);toLeft(0, 1);toUp(2, 1);toRight(5, 1);
		toDown(5, 2);toLeft(3, 1);toUp(5, 2);toRight(3, 1);
		toDown(3, 1);toRight(1, 3);toDown(3, 1);toLeft(1, 3);toUp(3, 2);
		toLeft(1, 1);toUp(5, 2);toRight(1, 1);toDown(5, 2);
		toRight(4, 1);toDown(5, 3);toLeft(4, 1);toUp(5, 3);
		toUp(4, 3);toLeft(1, 1);toDown(4, 3);toRight(1, 1);
		toLeft(4, 1);toUp(3, 1);toLeft(4, 2);toDown(3, 1);toRight(4, 3);
		
		System.out.println();
//		mainOperator();
//		mainOperator();
		printCube(cube);//final Cube
		
	}
	
	public static String findThreeAndMove() {
		String[] three = new String[3];
		
		Iterator<Map.Entry<String, int[]>> it = wrongPosMap.entrySet().iterator();
		while(it.hasNext()) {
			
			Map.Entry<String, int[]> entry = it.next();
			three[0] = entry.getKey();
			String nextStr = occupiedBy(correctPosMap.get(entry.getKey()));//正确坐标当前值
			if(null != nextStr) {
				three[1] = nextStr;
				String nextNextStr = occupiedBy(correctPosMap.get(nextStr));//
				three[2] = nextNextStr;
				if(null != occupiedBy(correctPosMap.get(nextNextStr))) {
					//非三元素圈
					moveAndRemove(three, false);
				}
				moveAndRemove(three, true);
				findThreeAndMove();
				break;
			} else {
				it.remove();
			}
		}
		return null;
	}
	
	public static void moveAndRemove(String[] strArray, boolean ifcircle) {
		move();
		if(ifcircle) {
			//一个圈
			for(int i=0; i<3; i++) {
				wrongPosMap.remove(strArray[i]);
				correctPosMap.remove(strArray[i]);
			}
		} else {
			wrongPosMap.get(strArray[2])[0] = wrongPosMap.get(strArray[0])[0];
			wrongPosMap.get(strArray[2])[1] = wrongPosMap.get(strArray[0])[1];
			
			wrongPosMap.remove(strArray[0]);
			wrongPosMap.remove(strArray[1]);
			
			correctPosMap.remove(strArray[0]);
			correctPosMap.remove(strArray[1]);
		}
	}
	
	public static void move() {
		String u = "U";
		String l = "L";
		String d = "D";
		String r = "R";
		
		//same row
		//same col
		//直角
		//非直角
		
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
				String key = correctCube[i][j].toString();
				int[] tmp = new int[2];
				int[] tmpWrong = wrongPosMap.get(key);
				if(tmpWrong[0] == i && tmpWrong[1] == j) {
					//去除正确位置元素
					wrongPosMap.remove(key);
					continue;
				}
				tmp[0] = i;
				tmp[1] = j;
				correctPosMap.put(key, tmp);
				
			}
		}
	}
	
	public static void toLeft(int rowNo, int times) {
		System.out.println("L"+rowNo);
//		System.out.print(times);
		while((times--) > 0) {
			//移动多次
			String tmp = cube[rowNo][0].toString();
			for(int i=0; i<order-1; i++) {
				cube[rowNo][i] = cube[rowNo][i+1];//更新Cube
			}
			cube[rowNo][order-1] = tmp;
		}
	}
	
	public static void toRight(int rowNo, int times) {
		System.out.println("R"+rowNo);
//		System.out.print(times);
		while((times--) > 0) {
			//移动多次
			String tmp = cube[rowNo][order-1].toString();
			for(int i=order-1; i>0; i--) {
				cube[rowNo][i] = cube[rowNo][i-1];//更新Cube
			}
			cube[rowNo][0] = tmp;
		}
	}
	
	public static void toUp(int colNo, int times) {
		System.out.println("U"+colNo);
//		System.out.print(times);
		while((times--) > 0) {
			//移动多次
			String tmp = cube[0][colNo].toString();
			for(int i=0; i<order-1; i++) {
				cube[i][colNo] = cube[i+1][colNo];//更新Cube
			}
			cube[order-1][colNo] = tmp;
		}
	}
	
	public static void toDown(int colNo, int times) {
		System.out.println("D"+colNo);
//		System.out.print(times);
		while((times--) > 0) {
			//移动多次
			String tmp = cube[order-1][colNo].toString();
			for(int i=order-1; i>0; i--) {
				cube[i][colNo] = cube[i-1][colNo];//更新Cube
			}
			cube[0][colNo] = tmp;
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
