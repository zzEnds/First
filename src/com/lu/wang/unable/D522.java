package com.lu.wang.unable;
	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
	
/**
 * 
 * @author lu.wang
 * D. Closest Equals
 *
 */
public class D522 {
	
//		public static Long timea = System.currentTimeMillis();//time for
//		if(n > 10000) {
//			System.out.println(System.currentTimeMillis()-timea);//time for
//		}
	public static void main(String[] args) {
		
		theMain();
	}
	
	public static void theMain() {
		
//			List<int[]> valuePos = new ArrayList<int[]>();
		
		StringBuffer re = new StringBuffer("");
		Scanner in=new Scanner(System.in);
		int i, j, x, y, shortest, tmp;
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		List<Integer> valuePosX = new ArrayList<Integer>();
		List<Integer> valuePosY = new ArrayList<Integer>();
		List<Integer> valuePosY_X = new ArrayList<Integer>();
		List<Integer> queryListX = new ArrayList<Integer>();
		List<Integer> queryListY = new ArrayList<Integer>();
		Map<Integer, Integer> lastPos = new HashMap<Integer, Integer>();
		int chkPos = 0;
		for(i=0; i<n; i++) {
			int tmpoo = in.nextInt();
			if(lastPos.containsKey(tmpoo)) {
				//if need this pos
				tmp = lastPos.get(tmpoo);
				if(chkPos <= tmp) {
//						int[] arrayX = {tmp, i};
//						valuePos.add(arrayX);
					valuePosX.add(tmp);
					valuePosY.add(i);
					valuePosY_X.add(i-tmp);
					chkPos = tmp;
					lastPos.put(tmpoo, i);
				}
			} else {
				lastPos.put(tmpoo, i);
			}
		}
		
		for(i=0; i<m; i++) {
			queryListX.add(in.nextInt());
			queryListY.add(in.nextInt());
		}
		
		for(i=0; i<m; i++) {
			x = queryListX.get(i)-1;
			y = queryListY.get(i)-1;
//				x = in.nextInt()-1;
//				y = in.nextInt()-1;
//				if(n > 10000) {
//					System.out.println("x:"+x);//time for
//					System.out.println("y:"+y);//time for
//				}
			//big and small
			shortest = -1;

			//where to begin x->valuePos.get(?)[0]
//				int sizeV = valuePos.size()-1;
			int sizeV = valuePosX.size()-1;
			if(sizeV >= 0) {
				j = theDichotomy(x, valuePosX, sizeV);
				for(; j<valuePosX.size(); j++) {
					int posX = valuePosX.get(j);
					int posY = valuePosY.get(j);
					if(posX > y) {
						break;
					}
					if(posX == x && posY == y) {
						shortest = valuePosY_X.get(j);
						break;
					} else if(posX>=x && posY<=y && (shortest == -1 || shortest>(posY-posX))) {
						shortest = valuePosY_X.get(j);
						if(shortest == 1) {
							break;
						}
					}
				}
			}
			re.append(shortest + "\n");
		}
		System.out.println(re.toString());
	}
	
	public static int theDichotomy(int x, List<Integer> valuePosX, int high) {
		int low = 0;
		do{
			int v_l = valuePosX.get(low);
			int v_h = valuePosX.get(high);
			int v_half = valuePosX.get((high-low)/2);
			
			if(high == low + 1) {
				return low;
			} else if(x == v_l) {
				return low;
			} else if(x == v_h) {
				return high;
			} else if(x == v_half) {
				return (high-low)/2;
			} else if(x>v_half) {
				low = (high-low)/2;
			} else if(x<v_half) {
				high = (high-low)/2;
			}
		}while(high >= low);
//			}
		return 0;
	}
	
}
