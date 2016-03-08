package escape.model;

import physics.LineSegment;
import physics.Vect;
import physics.Circle;
import java.util.ArrayList;

public class SquareBumper {

	private int x1,x2,y1,y2,size; // x1 & y1 -> top left, x2 & y2 -> top right, x3 & y3 -> bottom right, x4 & y4 -> bottom left, 
	private LineSegment a,b,c,d;
	private Circle ab,bc,cd,da; //named to show which circle is situated between which 2 sides of the square
	ArrayList<Circle> circles;
	ArrayList<LineSegment> lines;
	
	public SquareBumper(int x, int y, int size){
		this.x1 = x;
		this.x2 = x + size;
		this.y1 = y;
		this.y2 = y + size;
		this.size = size;
		a = new LineSegment(x, y, x + size, y);
		b = new LineSegment(x + size, y, x + size, y + size);
		c = new LineSegment(x + size, y + size, x, y + size);
		d = new LineSegment(x, y + size, x, y);
		lines = new ArrayList<LineSegment>();
		lines.add(a);
		lines.add(b);
		lines.add(c);
		lines.add(d);
		ab = new Circle(x,y,0);
		bc = new Circle(x + size,y,0);
		cd = new Circle(x + size,y + size,0);
		da = new Circle(x,y + size,0);
		circles = new ArrayList<Circle>();
		circles.add(ab);
		circles.add(bc);
		circles.add(cd);
		circles.add(da);

	}
	
	public ArrayList<LineSegment> getSides(){
		return lines;
	}
	
	public ArrayList<Circle> getEdges(){
		return circles;
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
	
	public int getX() {
		return x1;
	}

	public int getY() {
		return y1;
	}
	
	public int getSize(){
		return size;
	}
	
}
