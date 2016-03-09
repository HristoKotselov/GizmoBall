package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Set;

import physics.Angle;
import physics.Circle;

public class Ball extends AMovingGizmo {

	private double radius;

	// x, y coordinates and x,y velocity
	public Ball(String name, Color color, double x, double y, Angle theta, double yv) {
		super(name, y, x, color, theta, yv);
		start();

		radius = 5;
		System.out.println(drawingShape);
	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public Shape getDrawingShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Circle> getCircles() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Ball specific methods */
	public double getRadius() {
		return radius;
	}
	
	public Circle getCircle() {
		return new Circle(this.getPreciseX(), this.getPreciseY(), radius);

	}
	
	@Override
	protected void setupDrawingShape() {
		System.out.println("drawing shape being setup");
		drawingShape = new Ellipse2D.Double(0-this.radius, 0-this.radius, this.radius*2, this.radius*2);
	}

}
