package com.lu.wang.cube;

import java.util.LinkedList;
import java.util.List;

import com.lu.wang.cube.Contant.*;

public class Layer {
	
	private List<Box> boxesOutter = new LinkedList<Box>();//clockwise
	private List<Box> boxesInner = new LinkedList<Box>();//clockwise
	private String direction;
	
	public String rotation(RotatedDi rotateDi, Angle angle) {
		
		return "";
	}
	
	public String baseMove() {
		Coordinate tmpCoo = null;
		tmpCoo = boxesOutter.get(0).getCoo();
		for(int i=0; i<3; i++) {
			boxesOutter.get(i+1).setCoo(boxesOutter.get(i).getCoo());
		}
		boxesOutter.get(0).setCoo(tmpCoo);
		
		tmpCoo = boxesInner.get(0).getCoo();
		for(int i=0; i<3; i++) {
			boxesInner.get(i+1).setCoo(boxesInner.get(i).getCoo());
		}
		boxesInner.get(0).setCoo(tmpCoo);
		
		return "";
	}

	

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	
	
}
