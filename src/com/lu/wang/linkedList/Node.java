package com.lu.wang.linkedList;

public class Node {

	private NodeOB nodeOB;
	private Node next;

	public Node(NodeOB nodeOB) {
		this.nodeOB = nodeOB;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public NodeOB getNodeOB() {
		return nodeOB;
	}

	public void setNodeOB(NodeOB nodeOB) {
		this.nodeOB = nodeOB;
	}

}