package model;

import java.awt.Color;
import java.awt.Shape;
import java.util.Set;

import physics.Angle;
import physics.Circle;

public class Ball extends AMovableGizmo {

	private double radius;

	// x, y coordinates and x,y velocity
	public Ball(String name, Color color, double x, double y, Angle theta, double yv) {
		super(name, y, x, color, theta, yv);
		start();

		radius = 5;
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

	/* Ball specific methods */
	public double getRadius() {
		return radius;
	}

	/**
	 * TODO
	 * Shortcut method to retrieve the only Circle class within circleSets
	 * @return
	 */
	public Circle getCircle() {
		return new Circle(getPreciseX(), getPreciseY(), radius);
	}

}
