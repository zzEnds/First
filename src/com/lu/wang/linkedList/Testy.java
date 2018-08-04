package com.lu.wang.linkedList;


public class Testy {

	public static void main(String[] args) {
		
		basicTest();
	}
	
	
	
	public static void basicTest() {
		
		LinkedOB linkedOB = new LinkedOB();
		
		linkedOB.addNode(new NodeOB(1, "A"));
		linkedOB.addNode(new NodeOB(2, "AA"));
		linkedOB.addNode(new NodeOB(3, "AAA"));

		linkedOB.displayAllNodes(linkedOB.getHead());
		linkedOB.reverse();
		linkedOB.displayAllNodes(linkedOB.getHead());
	}
	

}
