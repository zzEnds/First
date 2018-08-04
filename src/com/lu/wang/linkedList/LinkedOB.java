package com.lu.wang.linkedList;

import static java.lang.System.out;

public class LinkedOB {
	
	private int size;
	private Node head;
	
	
	public LinkedOB() {
		
		size = 0;
		head = null;
		
	}
	
	public void addNode(NodeOB nodeOB) {
		
		Node newHead = new Node(nodeOB);
		if(head == null) {
			head = newHead;
		}
		else {
			newHead.setNext(head);
			newHead.setNodeOB(nodeOB);
			head = newHead;
		}
		
		size++;
	}
	
	public void displayAllNodes(Node head) {
		
		if(head == null) {
			out.print("");
		}
		else {
			out.print(head.getNodeOB().getValue());
			if(head.getNext() != null) {
				out.print("--->");
			}
			displayAllNodes(head.getNext());
		}
		
	}
	
	public void reverse() {
		
		reverse(head, null);
		
	}
	
	public void reverse(Node subhead, Node newHead) {
		
		if(subhead != null) {
			
			Node newSubHead = subhead.getNext();
			if(newSubHead == null) {
				head = subhead;
			}
			
			subhead.setNext(newHead);
			newHead = subhead;
			
			reverse(newSubHead, newHead);
			
		}
		
	}

	public int getSize() {
		return size;
	}

	public Node getHead() {
		return head;
	}
	
	
	
	

}
