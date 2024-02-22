//lampros vlahopoulos 2948
public class Point {

	private double x;
	private double y;
	
	
	
	public Point(double x, double y) {
		this.x=x;
		this.y=y;
		
		
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}


	public String toString() {
		
		String point =x+" "+y;
		return point;
	}
	
}
