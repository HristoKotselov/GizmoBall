package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.RoundRectangle2D;
import java.util.HashSet;
import java.util.Set;
import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class Flipper extends AStationaryGizmo implements ILineSegmentCollider {
	public static final int LEFT = 0;
	public static final int RIGHT = 1;

	/**
	 * Angle of rotation of flipper relative to start point, used during
	 * gameplay. Separate from rotationAngle, which defines the rotation of the
	 * flipper within bounding box.
	 **/
	private double gameplayRotation;
	private boolean flippingForward;
	private int orientation;
	private int flipSpeed;

	private long flipTime;
	private long startedFlipping;

	/**
	 * A set of Circles belonging to this Gizmo. They act as collision detectors
	 * with a ball, often at the edges of a shape.
	 **/
	protected Set<Circle> circleSet;
	/**
	 * A set of Line Segments around the edge of the absorber, which will act as
	 * the collision detector with a ball
	 **/
	private Set<LineSegment> ls;

	public Flipper(String name, int grid_tile_x, int grid_tile_y, Color color, int orientation) {
		super(name, grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, color);

		bmWidth = 2;
		bmHeight = 2;

		circleSet = new HashSet<Circle>();
		ls = new HashSet<LineSegment>();

		this.gameplayRotation = 0;
		this.flippingForward = false;
		this.orientation = orientation;

		updateCollections();
	}


	/* Flipper's non-sped up get methods */
	@Override
	public Shape getDrawingShape() {
		int x = getX();
		int y = getY();

		// rotation += 15;
		RoundRectangle2D.Double r = new RoundRectangle2D.Double(x, y, 0.5 * MainEngine.L, 2 * MainEngine.L,
				0.5 * MainEngine.L, 0.5 * MainEngine.L);
		AffineTransform transform = new AffineTransform();

		// Apply flipper rotation when triggered
		transform.rotate(Math.toRadians(gameplayRotation), r.getX() + 5, r.getY() + 5);
		if (orientation == LEFT) {
			try {
				// System.out.println("inverting");
				transform.invert();
			} catch (NoninvertibleTransformException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Rotate to proper orientation within the bounding box
		transform.rotate(Math.toRadians(rotationAngle), x + MainEngine.L, y + MainEngine.L);

		// Position right flipper at RHS of bounding box
		if (orientation == RIGHT) {
			transform.translate(30, 0);
		}

		return transform.createTransformedShape(r);
	}

	@Override
	public Set<Circle> getCircles() {
		return circleSet;
	}

	private void setupCircles() {
		int lCorner_X = getX();
		int rCorner_X = getX() + MainEngine.L * 2;
		int tCorner_Y = getY();
		int bCorner_Y = getY() + MainEngine.L * 2;
		
		circleSet.clear();
		
		Circle topleft = new Circle(lCorner_X + 5, tCorner_Y + 5, 5);
		Circle topright = new Circle(rCorner_X - 5, tCorner_Y + 5, 5);
		Circle bottomleft = new Circle(lCorner_X + 5, bCorner_Y - 5, 5);
		Circle bottomright = new Circle(rCorner_X - 5, bCorner_Y - 5, 5);
		
		if(orientation == LEFT){
			if(rotationAngle == 0){
				circleSet.add(topleft);
				circleSet.add(bottomleft);
			}
			else if(rotationAngle == 90){
				circleSet.add(topleft);
				circleSet.add(topright);
			}
			else if(rotationAngle == 180){
				circleSet.add(topright);
				circleSet.add(bottomright);
			}
			else if(rotationAngle == 270){
				circleSet.add(bottomright);
				circleSet.add(bottomleft);
			}
		}
		else {
			if(rotationAngle == 0){
				circleSet.add(topright);
				circleSet.add(bottomright);
			}
			else if(rotationAngle == 90){
				circleSet.add(bottomright);
				circleSet.add(bottomleft);
			}
			else if(rotationAngle == 180){
				circleSet.add(bottomleft);
				circleSet.add(topleft);
			}
			else if(rotationAngle == 270){
				circleSet.add(topleft);
				circleSet.add(topright);
			}
		}
	}

	@Override
	public Set<LineSegment> getLineSeg() {
		return ls;
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
		int rCorner_X = getX() + MainEngine.L * 2;
		int tCorner_Y = getY();
		int bCorner_Y = getY() + MainEngine.L * 2;
		
		ls.clear();
		
		LineSegment leftleft = new LineSegment(lCorner_X, tCorner_Y + 5, lCorner_X, bCorner_Y - 5);
		LineSegment leftright = new LineSegment(lCorner_X + 10, tCorner_Y + 5, lCorner_X + 10, bCorner_Y - 5);
		
		LineSegment toptop = new LineSegment(lCorner_X + 5, tCorner_Y, rCorner_X - 5, tCorner_Y);
		LineSegment topbottom = new LineSegment(lCorner_X + 5, tCorner_Y + 10, rCorner_X - 5, tCorner_Y + 10);
		
		LineSegment rightright = new LineSegment(rCorner_X, tCorner_Y + 5, rCorner_X, bCorner_Y - 5);
		LineSegment rightleft = new LineSegment(rCorner_X - 10, tCorner_Y + 5, rCorner_X - 10, bCorner_Y - 5);
		
		LineSegment bottombottom = new LineSegment(lCorner_X + 5, bCorner_Y, rCorner_X - 5, bCorner_Y);
		LineSegment bottomtop = new LineSegment(lCorner_X + 5, bCorner_Y - 10, rCorner_X - 5, bCorner_Y - 10);
		

		if(orientation == LEFT){
			if(rotationAngle == 0){
				ls.add(leftleft);
				ls.add(leftright);
			}
			else if(rotationAngle == 90){
				ls.add(toptop);
				ls.add(topbottom);
			}
			else if(rotationAngle == 180){
				ls.add(rightright);
				ls.add(rightleft);
			}
			else if(rotationAngle == 270){
				ls.add(bottombottom);
				ls.add(bottomtop);
			}
		}
		else {
			if(rotationAngle == 0){
				ls.add(rightright);
				ls.add(rightleft);
			}
			else if(rotationAngle == 90){
				ls.add(bottombottom);
				ls.add(bottomtop);
			}
			else if(rotationAngle == 180){
				ls.add(leftleft);
				ls.add(leftright);
			}
			else if(rotationAngle == 270){
				ls.add(toptop);
				ls.add(topbottom);
			}
		}
	}

	public void updateCollections() {
		setupLineSeg();
		setupCircles();
	}

	/* Regular methods implementation */
	/* (non-Javadoc)
	 * @see model.AGizmoComponent#triggerAction()
	 */
	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub
		flippingForward = true;
		startedFlipping = System.nanoTime();
	}

	/* (non-Javadoc)
	 * @see model.AGizmoComponent#rotate(int)
	 */
	@Override
	public boolean rotate(int degree) {
		// TODO Validation
		rotationAngle = (rotationAngle + degree) % 360;

		updateCollections();
		
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

	@Override
	public String toString() {
		String s;

		if (orientation == LEFT) {
			s = "LeftFlipper " + super.toString();
		} else {
			s = "RightFlipper " + super.toString();
		}

		for (int i = rotationAngle; i > 0; i -= 90) {
			s += "\nRotate " + getGizmoID();
		}

		return s;
	}

	/* (non-Javadoc)
	 * @see model.AGizmoComponent#reset()
	 */
	@Override
	public void reset() {
		// TODO get Flipper to default position
	}


	/* Flipper exclusive methods */
	public void update() {
		if (flippingForward) {
			gameplayRotation += flipSpeed;
		} else {
			gameplayRotation -= flipSpeed;
		}

		if (gameplayRotation > 90) {
			gameplayRotation = 90;
		} else if (gameplayRotation < 0) {
			gameplayRotation = 0;
		}

		if (System.nanoTime() - startedFlipping > flipTime) {
			flippingForward = false;
		}
		// Circle circle = new physics.Circle(new physics.Vect(this.getX(),
		// this.getY()), 10);

	}

	// use this method TWICE for each flipper for a full 90-degree rotation
	public void rotate45degrees() {
		Vect rotationPoint;
		double SQRT = Math.sqrt(0.5); // calculation for 45-degree angle
		if (flippingForward) {
			if (orientation == LEFT) {
				rotationPoint = new Vect(getX() + 5, getY() + 5);
				for (Circle each : circleSet) {
					Geometry.rotateAround(each, rotationPoint, new Angle(SQRT, SQRT)); // rotate
																						// 45
																						// degrees
				}
				for (LineSegment each : ls) {
					Geometry.rotateAround(each, rotationPoint, new Angle(SQRT, SQRT)); // rotate
																						// 45
																						// degrees
				}
			} else {
				rotationPoint = new Vect(getX() + MainEngine.L * 2 - 5, getY() + 5);
				for (Circle each : circleSet) {
					Geometry.rotateAround(each, rotationPoint, new Angle(SQRT, -SQRT)); // rotate
																						// 315
																						// degrees
				}
				for (LineSegment each : ls) {
					Geometry.rotateAround(each, rotationPoint, new Angle(SQRT, -SQRT)); // rotate
																						// 315
																						// degrees
				}
			}
		}
	}

	// use this method TWICE for each flipper for a full 90-degree rotation
	// BACKWARDS
	public void rotateback45degrees() {
		Vect rotationPoint;
		double SQRT = Math.sqrt(0.5); // calculation for 45-degree angle
		if (!flippingForward) {
			if (orientation == LEFT) {
				rotationPoint = new Vect(getX() + 5, getY() + 5);
				for (Circle each : circleSet) {
					Geometry.rotateAround(each, rotationPoint, new Angle(SQRT, -SQRT)); // rotate
																						// 315
																						// degrees
				}
				for (LineSegment each : ls) {
					Geometry.rotateAround(each, rotationPoint, new Angle(SQRT, -SQRT)); // rotate
																						// 315
																						// degrees
				}
			} else {
				rotationPoint = new Vect(getX() + MainEngine.L * 2 - 5, getY() + 5);
				for (Circle each : circleSet) {
					Geometry.rotateAround(each, rotationPoint, new Angle(SQRT, SQRT)); // rotate
																						// 45
																						// degrees
				}
				for (LineSegment each : ls) {
					Geometry.rotateAround(each, rotationPoint, new Angle(SQRT, SQRT)); // rotate
																						// 45
																						// degrees
				}
			}
		}
	}

	public Vect getRotationPoint() {
		if (orientation == LEFT) {
			return new Vect(getX() + 5, getY() + 5);
		} else
			return new Vect(getX() + MainEngine.L * 2 - 5, getY() + 5);
	}

	public int getOrientation() {
		return orientation;
	}
}
