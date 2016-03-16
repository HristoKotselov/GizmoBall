package model;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.HashSet;
import java.util.Set;
import physics.Angle;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class Absorber extends AStatueGizmo implements ILineSegmentCollider {

	/**
	 * A set of Line Segments around the edge of the absorber, which will act as
	 * the collision detector with a ball
	 **/
	private Set<LineSegment> ls;
	/**
	 * The currently captured Ball within the Absorber. If there is no ball
	 * within the absorber, then this object becomes null.
	 */
	private Ball capturedBall;

	public Absorber(String name, int grid_tile_x, int grid_tile_y, int grid_tile_width, int grid_tile_height, Color color) {
		/* NOTE -	The following methods are called by the superclass's constructor:
		setupDrawingShape();
		setupCircles();
		 */
		super(name, grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, color);

		bmWidth = grid_tile_width;
		bmHeight = grid_tile_height;
		capturedBall = null;

		ls = new HashSet<LineSegment>();
		/* Needs to be called again because when called by super() width/height
		   haven't been set yet. But this time, Line Segments are also registered.
		 */
		// TODO Find better workaround
		updateCollections();
	}

	@Override
	public void triggerAction() {
		if (capturedBall != null) { // no ball in absorber = nothing happens
			setBall(null);		// Release the ball
			// In this physics package, ANGLE.ZERO is RHS of x-axis; degree
			// increasing clock-wise. 50L is the length, thus it is converted to
			// pixels here
			capturedBall.setVelo(new Vect(Angle.DEG_270, 50 * MainEngine.L));
			capturedBall.start();
		}

	}

	@Override
	protected void setupDrawingShape() {
		drawingShape = new Rectangle(bmWidth * MainEngine.L, bmHeight * MainEngine.L);
	}

	/* Absorber's collision detector methods */
	@Override
	protected void setupCircles() {
		int lCorner_X = getX();
		int rCorner_X = getX() + bmWidth * MainEngine.L;
		int tCorner_Y = getY();
		int bCorner_Y = getY() + bmHeight * MainEngine.L;

		Circle tlCorner = new Circle(lCorner_X, tCorner_Y, 0.0);
		Circle trCorner = new Circle(rCorner_X, tCorner_Y, 0.0);
		Circle brCorner = new Circle(rCorner_X, bCorner_Y, 0.0);
		Circle blCorner = new Circle(lCorner_X, bCorner_Y, 0.0);

		circleSet.add(tlCorner);
		circleSet.add(trCorner);
		circleSet.add(brCorner);
		circleSet.add(blCorner);
	}

	/**
	 * The setup of the Line Segment collection. Gizmo component that rely on
	 * Line Segments for collision detection should set up their Line Segment
	 * objects here.
	 * 
	 * @modify this
	 * @effect Fill the collection which hold all the Line Segments in this
	 *         class with appropriate objects
	 */
	private void setupLineSeg() {
		int lCorner_X = getX();
		int rCorner_X = getX() + bmWidth * MainEngine.L;
		int tCorner_Y = getY();
		int bCorner_Y = getY() + bmHeight * MainEngine.L;

		LineSegment tlCorner_trCorner = new LineSegment(lCorner_X, tCorner_Y, rCorner_X, tCorner_Y);
		LineSegment trCorner_brCorner = new LineSegment(rCorner_X, tCorner_Y, rCorner_X, bCorner_Y);
		LineSegment brCorner_blCorner = new LineSegment(rCorner_X, bCorner_Y, lCorner_X, bCorner_Y);
		LineSegment blCorner_tlCorner = new LineSegment(lCorner_X, bCorner_Y, lCorner_X, tCorner_Y);

		ls.add(tlCorner_trCorner);
		ls.add(trCorner_brCorner);
		ls.add(brCorner_blCorner);
		ls.add(blCorner_tlCorner);
	}

	@Override
	public Set<LineSegment> getLineSeg() {
		return ls;
	}

	@Override
	public void updateCollections() {
		setupDrawingShape();
		setupLineSeg();
		setupCircles();
	}

	@Override
	public boolean rotate(int degree) {
		// Absorber shouldn't be rotatable so this method doesn't need to do anything
		return true;
	}

	@Override
	public boolean move(int newX, int newY) {

		updateCollections();

		// TODO Auto-generated method stub
		return false;
	}

	/* Absorber exclusive methods */
	public void setBall(Ball b) {
		capturedBall = b;
	}

	public Ball getCapturedBall() {
		return capturedBall;
	}

	@Override
	public String toString() {
		int x2 = (getX() / MainEngine.L) + bmWidth;
		int y2 = (getY() / MainEngine.L) + bmHeight;

		return "Absorber " + super.toString() + " " + x2 + " " + y2;
	}
}
