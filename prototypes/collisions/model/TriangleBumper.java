package model;

import physics.LineSegment;
import physics.Vect;
import physics.Circle;
import java.util.ArrayList;

public class TriangleBumper {
	
	private int x1,x2,x3,y1,y2,y3,size;
	private LineSegment a,b,c;
	private Circle ab,ac,bc; //named to show which circle is situated between which 2 sides of the triangle
	
	public TriangleBumper(int x, int y, int size){
		this.x1 = x;
		this.x2 = x + size;
		this.y1 = y;
		this.y2 = y + size;
		this.size = size;
		a = new LineSegment(x, y, x, y + size);
		b = new LineSegment(x, y, x + size, y);
		c = new LineSegment(x + size, y + size, x + size, y);
		ab = new Circle(x,y,0);
		bc = new Circle(x + size,y,0);
		ac = new Circle(x,y + size,0);

	}
	
	public Vect getTopLeftCorner(){
		return (new Vect(x1,y1));
	}
	
	public Vect getTopRightCorner(){
		return (new Vect(x2,y1));
	}
	
	public Vect getBottomRightCorner(){
		return (new Vect(x2,y2));
	}
	
	public Vect getBottomLeftCorner(){
		return (new Vect(x1,y2));
	}
	
	public int getSize(){
		return size;
	}

}
