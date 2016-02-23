package model;

import physics.LineSegment;
import physics.Circle;
import java.util.ArrayList;

public class TriangleBumper {
	
	private int x1,x2,y1,y2,size;
	private LineSegment a,b,c;
	private Circle ab,ac,bc; //named to show which circle is situated between which 2 sides of the triangle
	ArrayList<Circle> circles;
	ArrayList<LineSegment> lines;
	
	public TriangleBumper(int x, int y, int size){
		this.x1 = x;
		this.x2 = x + size;
		this.y1 = y;
		this.y2 = y + size;
		this.size = size;
		a = new LineSegment(x, y, x, y + size);
		b = new LineSegment(x, y, x + size, y);
		c = new LineSegment(x, y + size, x + size, y);
		lines = new ArrayList<LineSegment>();
		lines.add(a);
		lines.add(b);
		lines.add(c);
		ab = new Circle(x,y,0);
		bc = new Circle(x + size,y,0);
		ac = new Circle(x,y + size,0);
		circles = new ArrayList<Circle>();
		circles.add(ab);
		circles.add(bc);
		circles.add(ac);
		
	}
	
	public ArrayList<LineSegment> getSides(){
		return lines;
	}
	
	public ArrayList<Circle> getEdges(){
		return circles;
	}
	
	public int getX() {
		return x1;
	}
	
	public int getX2() {
		return x2;
	}

	public int getY() {
		return y1;
	}

	public int getY2() {
		return y2;
	}
	
	public int getSize(){
		return size;
	}

}
