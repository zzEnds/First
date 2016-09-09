package com.lu.wang;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author lu.wang
 * D. Closest Equals
 *
 */
public class D522test9Thread {
	
	public static Long timea = System.currentTimeMillis();//time for
	public static void main(String[] args) {
		
		theMain();
	}
	
	public static void theMain() {
		
		StringBuffer re = new StringBuffer("");
		Scanner in=new Scanner(System.in);
		List<Integer> aList = new ArrayList<Integer>();
		List<Integer> queryListX = new ArrayList<Integer>();
		List<Integer> queryListY = new ArrayList<Integer>();
		Map<Integer, Integer> noRepeat = new HashMap<Integer, Integer>();
		int i, j, repeatNo, tmp;
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		for(i=0; i<n; i++) {
			int tmpoo = in.nextInt();
			aList.add(tmpoo);
			if(noRepeat.containsKey(tmpoo)) {
				noRepeat.put(tmpoo, 1);
			} else {
				noRepeat.put(tmpoo, 0);
			}
		}
		
		for(i=0; i<m; i++) {
			queryListX.add(in.nextInt());
			queryListY.add(in.nextInt());
		}

		
		ExecutorService threadPool = Executors.newCachedThreadPool();
		CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
		for(i=0; i<m; i++) {
			
			final int taskID = i;
			cs.submit(new Callable<Integer>() {
				public Integer call() throws Exception {

					int x = queryListX.get(taskID);
					int y = queryListY.get(taskID);
					int shortest = -1;
					Map<Integer, Integer> repeatMap = new HashMap<Integer, Integer>();
					shortest = -1;
					for(j=x-1; j<y; j++) {
						
						repeatNo = aList.get(j);
						if(noRepeat.get(repeatNo) == 0) {
							continue;
						}
						if(!repeatMap.containsKey(repeatNo)) {
							repeatMap.put(repeatNo, j);
						} else {
							tmp = j - repeatMap.get(repeatNo);
							if(tmp == 1) { shortest = 1;break; }
							repeatMap.put(repeatNo, j);
							if(shortest > tmp || shortest < 0) {
								shortest = tmp;
							}
						}
					}
//					re.append(shortest + "\n");
				
				
					return shortest;
				}
			});
		
			// 可能做一些事情
			for (int i = 1; i < 5; i++) {
				try {
					System.out.println(cs.take().get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		
		}
		System.out.println(re.toString());
	}
	
}