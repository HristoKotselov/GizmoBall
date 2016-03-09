package model;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.HashSet;
import java.util.Set;
import physics.Circle;
import physics.LineSegment;

public class SquareBumper extends AStatueGizmo implements ILineSegmentCollider {
	/**
	 * A set of Line Segments around the edge of the square bumper, which will
	 * act as the collision detector with a ball
	 **/
	private Set<LineSegment> ls;

	public SquareBumper(String name, int grid_tile_x, int grid_tile_y, Color color) {
		super(name, grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, color);
		ls = new HashSet<LineSegment>();
		setupLineSeg();
		setupCircles();
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
		int rCorner_X = getX() + 20;
		int tCorner_Y = getY();
		int bCorner_Y = getY() + 20;

		LineSegment top = new LineSegment(lCorner_X, tCorner_Y, rCorner_X, tCorner_Y);
		LineSegment right = new LineSegment(rCorner_X, tCorner_Y, rCorner_X, bCorner_Y);
		LineSegment bottom = new LineSegment(rCorner_X, bCorner_Y, lCorner_X, bCorner_Y);
		LineSegment left = new LineSegment(lCorner_X, bCorner_Y, lCorner_X, tCorner_Y);

		ls.add(top);
		ls.add(right);
		ls.add(bottom);
		ls.add(left);
	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public Shape getDrawingShape() {
		return new Rectangle(20, 20);
	}

	@Override
	protected void setupCircles() {
		int lCorner_X = getX();
		int rCorner_X = getX() + 20;
		int tCorner_Y = getY();
		int bCorner_Y = getY() + 20;

		Circle topleft = new Circle(lCorner_X, tCorner_Y, 0);
		Circle topright = new Circle(rCorner_X, tCorner_Y, 0);
		Circle bottomright = new Circle(rCorner_X, bCorner_Y, 0);
		Circle bottomleft = new Circle(lCorner_X, bCorner_Y, 0);

		circleSet.add(topleft);
		circleSet.add(topright);
		circleSet.add(bottomright);
		circleSet.add(bottomleft);
	}

	@Override
	public Set<LineSegment> getLineSeg() {
		return ls;
	}

}
