package escape.model;

import physics.Circle;

public class CircleBumper {

	private int xpos;
	private int ypos;
	private int radius;
	private Circle circle;
	
	public CircleBumper(int x, int y, int r){
		xpos = x;
		ypos = y;
		radius = r;
		circle = new Circle(x,y,r);
	}
	
	public Circle getCircle() {
		return circle;
	}
	
	public int getX() {
		return xpos;
	}

	public int getY() {
		return ypos;
	}

	public int getRadius() {
		return radius;
	}

}
