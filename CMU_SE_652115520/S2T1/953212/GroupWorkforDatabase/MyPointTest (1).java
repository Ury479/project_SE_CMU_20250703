package cn.lab5_2_myPoint;

import cn.timej.myPoint;

public class MyPointTest {

	public static void main(String[] args) {
		MyPoint point1 = new MyPoint();
		MyPoint point2 = new MyPoint(3.5,6.0);
		
		System.out.println("The distance between (" 
				+ point1.getA() + ", " + point1.getB() + ") and ("
				+ point2.getA() + ", " + point2.getB() + ") is: " + 
				point1.distance(point2));
		
		
		
		
		
		
	}

}
