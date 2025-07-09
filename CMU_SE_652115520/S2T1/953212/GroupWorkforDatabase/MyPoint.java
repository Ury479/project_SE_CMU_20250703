package cn.lab5_2_myPoint;

/** YIHAO WANG 652115520 */
public class MyPoint {
	private double a;
	private double b;

	MyPoint() {
		super();
		// TODO Auto-generated constructor stub
	}

	 MyPoint(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public double distance(MyPoint myPoint) {
		return Math.sqrt(Math.pow(myPoint.getA() - a, 2) + Math.pow(myPoint.getB() - b, 2));
	}

	public double distance(double x, double y) {
		return distance(new MyPoint(x,y));
	}
}
