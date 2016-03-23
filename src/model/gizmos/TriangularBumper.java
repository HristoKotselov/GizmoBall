package model.gizmos;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.HashSet;
import java.util.Set;
import model.AStationaryGizmo;
import model.CollisionDetails;
import model.ILineSegmentCollider;
import model.MainEngine;
import physics.Circle;
import physics.LineSegment;

public class TriangularBumper extends AStationaryGizmo implements ILineSegmentCollider {

	/* Collections used to optimise up game performance */
	/**
	 * The visual representation of the Gizmo. Used by drawing code to determine what a Gizmo will look like on screen.
	 **/
	private Shape drawingShape;
	/**
	 * A set of Circles belonging to this Gizmo. They act as collision detectors with a ball, often at the edges of a shape.
	 **/
	protected Set<Circle> circleSet = new HashSet<Circle>();
	/**
	 * A set of Line Segments around the edge of the absorber, which will act as the collision detector with a ball
	 **/
	private Set<LineSegment> ls;


	public TriangularBumper(String name, int grid_tile_x, int grid_tile_y, Color color) {
		super(name, grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, color);


		// Collection-speed up initialisation
		circleSet = new HashSet<Circle>();
		ls = new HashSet<LineSegment>();
		updateCollections();
	}



	/* Triangle's Collection-sped up methods (Circle, DrawingShape, LineSegment) */
	@Override
	public Shape getDrawingShape() {
		return drawingShape;
	}

	private void setupDrawingShape() {
		int x = getX();
		int y = getY();

		int[] xpoints = { x, x + MainEngine.L, x };
		int[] ypoints = { y, y, y + MainEngine.L };

		// Create triangle
		drawingShape = new Polygon(xpoints, ypoints, xpoints.length);

		// Rotate to correct orientation
		AffineTransform t = new AffineTransform();
		t.rotate(Math.toRadians(rotationAngle), x + (MainEngine.L / 2), y + (MainEngine.L / 2));
		drawingShape = t.createTransformedShape(drawingShape);
	}

	@Override
	public Set<Circle> getCircles() {
		return circleSet;
	}

	private void setupCircles() {
		int lCorner_X = getX();
		int rCorner_X = getX() + MainEngine.L;
		int tCorner_Y = getY();
		int bCorner_Y = getY() + MainEngine.L;

		circleSet.clear();

		Circle topleft = new Circle(lCorner_X, tCorner_Y, 0);
		Circle topright = new Circle(rCorner_X, tCorner_Y, 0);
		Circle bottomleft = new Circle(lCorner_X, bCorner_Y, 0);
		Circle bottomright = new Circle(rCorner_X, bCorner_Y, 0);

		// not the best way to do this, but good enough for the spec!
		if (rotationAngle == 0) {
			circleSet.add(topleft);
			circleSet.add(topright);
			circleSet.add(bottomleft);
		} else if (rotationAngle == 90) {
			circleSet.add(topleft);
			circleSet.add(topright);
			circleSet.add(bottomright);
		} else if (rotationAngle == 180) {
			circleSet.add(topright);
			circleSet.add(bottomleft);
			circleSet.add(bottomright);
		} else if (rotationAngle == 270) {
			circleSet.add(topleft);
			circleSet.add(bottomleft);
			circleSet.add(bottomright);
		}
	}

	@Override
	public Set<LineSegment> getLineSeg() {
		return ls;
	}

	/**
	 * The setup of the Line Segment collection. Gizmo component that rely on Line Segments for collision detection should set up their Line
	 * Segment objects here.
	 * 
	 * @modify this
	 * @effect Fill the collection which hold all the Line Segments in this class with appropriate objects
	 */
	private void setupLineSeg() {
		int lCorner_X = getX();
		int rCorner_X = getX() + MainEngine.L;
		int tCorner_Y = getY();
		int bCorner_Y = getY() + MainEngine.L;

		ls.clear();

		LineSegment top = new LineSegment(lCorner_X, tCorner_Y, rCorner_X, tCorner_Y);
		LineSegment right = new LineSegment(rCorner_X, tCorner_Y, rCorner_X, bCorner_Y);
		LineSegment bottom = new LineSegment(lCorner_X, bCorner_Y, rCorner_X, bCorner_Y);
		LineSegment left = new LineSegment(lCorner_X, tCorner_Y, lCorner_X, bCorner_Y);
		LineSegment tlCorner_brCorner = new LineSegment(lCorner_X, tCorner_Y, rCorner_X, bCorner_Y);
		LineSegment trCorner_blCorner = new LineSegment(rCorner_X, tCorner_Y, lCorner_X, bCorner_Y);

		// not the best way to do this, but good enough for the spec!
		if (rotationAngle == 0) {
			ls.add(top);
			ls.add(left);
			ls.add(trCorner_blCorner);
		} else if (rotationAngle == 90) {
			ls.add(top);
			ls.add(right);
			ls.add(tlCorner_brCorner);
		} else if (rotationAngle == 180) {
			ls.add(right);
			ls.add(bottom);
			ls.add(trCorner_blCorner);
		} else if (rotationAngle == 270) {
			ls.add(bottom);
			ls.add(left);
			ls.add(tlCorner_brCorner);
		}
	}

	private void updateCollections() {
		setupDrawingShape();
		setupLineSeg();
		setupCircles();
	}



	/* Regular methods implementation */

	/* (non-Javadoc)
	 * @see model.AGizmoComponent#triggered()
	 */
	@Override
	public void ballTriggered(CollisionDetails cd) {
		action();
	}

	/* (non-Javadoc)
	 * @see model.AGizmoComponent#triggerAction()
	 */
	@Override
	public void action() {
		timeTilRevert = 3;
		setColour(Color.CYAN);
	}

	@Override
	public void update(double moveTime) {
		timeTilRevert -= moveTime;

		if (timeTilRevert <= 0) {
			timeTilRevert = 0;
			reset();
		}
	};

	/* (non-Javadoc)
	 * @see model.AGizmoComponent#rotate(int)
	 */
	@Override
	public boolean rotate(int degree) {
		rotationAngle = (rotationAngle + degree) % 360;

		updateCollections();
		// TODO Also requires validation
		return true;
	}

	/* Overwritten methods */
	/* (non-Javadoc)
	 * @see model.AGizmoComponent#move(int, int)
	 */
	@Override
	public void move(int grid_tile_x, int grid_tile_y) {
		// TODO Validation

		super.move(grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L);

		updateCollections();
	}

	/* (non-Javadoc)
	 * @see model.AGizmoComponent#reset()
	 */
	@Override
	public void reset() {
		setColour(getInitialColour());
	}

	@Override
	public String toString() {
		String s = "Triangle " + super.toString();

		for (int i = rotationAngle; i > 0; i -= 90) {
			s += "\nRotate " + getGizmoID();
		}

		return s;
	}
}