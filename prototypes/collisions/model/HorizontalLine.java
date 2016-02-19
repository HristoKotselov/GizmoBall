package model;

import physics.LineSegment;
import physics.Circle;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class HorizontalLine {

	private int xpos;
	private int ypos;
	private int width;
	private LineSegment ls;
	private Circle c1,c2;

	public HorizontalLine(int x, int y, int w) {
		xpos = x;
		ypos = y;
		width = w;
		ls = new LineSegment(x, y, x + w, y);
		c1 = new Circle(x,y,0);
		c2 = new Circle(x+w,y,0);
	}

	public LineSegment getLineSeg() {
		return ls;
	}
	
	public Circle getLeftCircle() {
		return c1;
	}
	
	public Circle getRightCircle() {
		return c2;
	}

	public int getX() {
		return xpos;
	}

	public int getY() {
		return ypos;
	}

	public int getWidth() {
		return width;
	}

}
