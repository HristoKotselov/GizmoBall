package model;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.HashSet;
import java.util.Set;
import physics.Circle;
import physics.LineSegment;

public class TriangularBumper extends AStatueGizmo implements ILineSegmentCollider {
	/** A set of Line Segments around the edge of the triangular bumper, which will act as the collision detector with a ball **/
	private Set<LineSegment> ls;
	
	public TriangularBumper(String name, int grid_tile_x, int grid_tile_y, Color color) {
		super(name, grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, color);
		
		ls = new HashSet<LineSegment>();
		setupLineSeg();
		setupCircles();
	}
	

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * The setup of the Line Segment collection. Gizmo component that rely on Line Segments for collision detection should
	 * set up their Line Segment objects here.
	 * @modify this
	 * @effect Fill the collection which hold all the Line Segments in this class with appropriate objects
	 */
	private void setupLineSeg() {
		int lCorner_X = getX();
		int rCorner_X = getX() + MainEngine.L;
		int tCorner_Y = getY();
		int bCorner_Y = getY() + MainEngine.L;

		LineSegment top = new LineSegment(lCorner_X, tCorner_Y, rCorner_X, tCorner_Y);
		LineSegment middle = new LineSegment(rCorner_X, tCorner_Y, lCorner_X, bCorner_Y);
		LineSegment left = new LineSegment(lCorner_X, bCorner_Y, lCorner_X, tCorner_Y);

		ls.add(top);
		ls.add(middle);
		ls.add(left);
	}

	@Override
	protected void setupDrawingShape() {
		int[] xpoints = { 0, MainEngine.L, 0 };
		int[] ypoints = { 0, 0, MainEngine.L };

		drawingShape = new Polygon(xpoints, ypoints, xpoints.length);
	}

	@Override
	protected void setupCircles() {
		int lCorner_X = getX();
		int rCorner_X = getX() + MainEngine.L;
		int tCorner_Y = getY();
		int bCorner_Y = getY() + MainEngine.L;

		Circle topleft = new Circle(lCorner_X, tCorner_Y, 0);
		Circle topright = new Circle(rCorner_X, tCorner_Y, 0);
		Circle bottomleft = new Circle(lCorner_X, bCorner_Y, 0);

		circleSet.add(topleft);
		circleSet.add(topright);
		circleSet.add(bottomleft);
	}
	
	@Override
	public void updateCollections(){
		setupDrawingShape();
		setupLineSeg();
		setupCircles();
	}
	
	@Override
	public boolean rotate(int degree) {
		
		
		
		updateCollections();
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean move(int newX, int newY) {
		
		
		
		updateCollections();
		
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Set<LineSegment> getLineSeg() {
		return ls;
	}

}
