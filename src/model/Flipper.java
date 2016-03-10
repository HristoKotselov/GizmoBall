package model;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.RoundRectangle2D;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javafx.geometry.Point2D;
import physics.Angle;
import physics.Circle;
import physics.LineSegment;

public class Flipper extends AMovableGizmo implements ILineSegmentCollider {
	public double rotation;
	public boolean flippingForward;
	public boolean leftFlipper;
	private int flipSpeed;

	private long flipTime;
	private long startedFlipping;

	// private Set<Circle> circles;
	// private Set<LineSegment> lines;

	public Flipper(String name, int grid_tile_x, int grid_tile_y, Color color, boolean leftFlipper) {
		super(name, grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, color, new Angle(0.0), 0);
		//System.out.println(leftFlipper);

		this.rotation = 0;
		this.flippingForward = false;
		this.leftFlipper = leftFlipper;
		if (!leftFlipper) {
			this.setX( (this.getX() + 10));
		}
		
		//circles = new HashSet<Circle>();
		Circle c = new physics.Circle(this.getX()+10, this.getY()+10, MainEngine.L*0.5);
	//	circles.add(c);

	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub
		flippingForward = true;
		startedFlipping = System.nanoTime();
	}

	@Override
	public Shape getDrawingShape() {
	//	rotation += 15;
		RoundRectangle2D.Double r = new RoundRectangle2D.Double(0, 0, 0.5 * MainEngine.L, 2 * MainEngine.L,
				0.5 * MainEngine.L, 0.5 * MainEngine.L);
		AffineTransform transform = new AffineTransform();

		transform.rotate(Math.toRadians(rotation), r.getX() + 5, r.getY() + 5);
		if (leftFlipper) {
			try {
			//	System.out.println("inverting");
				transform.invert();
			} catch (NoninvertibleTransformException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Shape transformedR = transform.createTransformedShape(r);
		return transformedR;
		// return new Rectangle(20, 20);
	}

	@Override
	public Set<Circle> getCircles() {
		// TODO
		return null;
	}

	@Override
	public Set<LineSegment> getLineSeg() {
		// TODO
		return null;
	}
	

	@Override
	public boolean rotate(int degree) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean move(int newX, int newY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		if (flippingForward) {
			rotation += flipSpeed;
		} else {
			rotation -= flipSpeed;
		}

		if (rotation > 90) {
			rotation = 90;
		} else if (rotation < 0) {
			rotation = 0;
		}

		if (System.nanoTime() - startedFlipping > flipTime) {
			flippingForward = false;
		}
		// Circle circle = new physics.Circle(new physics.Vect(this.getX(),
		// this.getY()), 10);

	}
}
