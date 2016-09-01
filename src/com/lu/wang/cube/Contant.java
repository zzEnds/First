package com.lu.wang.cube;

public class Contant {
	
	public static final int ClassType = 3;//
	
	public enum Colors {
		WHITE, YELLOW, GREEN, BLUE, RED, ORANGE
	}
	
	public enum Directions {
		UP("Z"), DOWN("Z"), LEFT("Y"), RIGHT("Y"), FRONT("X"), BACK("X");
		private String axis;
		private Directions(String axis) {
			this.axis = axis;
		}
	}
	
	//cw:clockwise; ccw:anticlockwise
	public enum RotatedDi {
		CW, CCW
	}
	
	public enum Angle {
		_90, _180
	}
	
//	public static final String WHITE = "white";//up
//	public static final String YELLOW = "white";//down
//	public static final String GREEN = "green";//left
//	public static final String BLUE = "blue";//right
//	public static final String RED = "red";//front
//	public static final String ORANGE = "orange";//back
	
//	public static final String UP = "up";
//	public static final String DOWN = "down";
//	public static final String LEFT = "left";
//	public static final String RIGHT = "right";
//	public static final String FRONT = "front";
//	public static final String BACK = "back";
	
	
}
