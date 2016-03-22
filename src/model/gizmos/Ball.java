package model.gizmos;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;
import model.AMovingGizmo;
import physics.Angle;
import physics.Circle;

public class Ball extends AMovingGizmo {

	private double radius;

	// x, y coordinates and x,y velocity
	public Ball(String name, Color color, int starting_x, int starting_y, Angle theta, double velo) {
		super(name, starting_x, starting_y, color, theta, velo);

		radius = 5;
	}

	/* Ball's non-sped up get methods */
	@Override
	public Shape getDrawingShape() {
		return new Ellipse2D.Double(getMovingX() - this.radius, getMovingY() - this.radius, this.radius * 2, this.radius * 2);
	}

	@Override
	public Set<Circle> getCircles() {
		Set<Circle> circleSet = new HashSet<Circle>();

		circleSet.add(new Circle(getMovingX(), getMovingY(), radius));

		return circleSet;
	}


	/* Regular methods implementation */
	/* (non-Javadoc)
	 * @see model.AGizmoComponent#triggerAction()
	 */
	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub
		// Do nothing
	}

	/* (non-Javadoc)
	 * @see model.AGizmoComponent#rotate(int)
	 */
	@Override
	public boolean rotate(int degree) {
		// Ball shouldn't be rotatable so this method doesn't need to do
		// anything
		return true;
	}

	/* Overwritten methods */
	/* (non-Javadoc)
	 * @see model.AGizmoComponent#move(int, int)
	 */
	@Override
	public void move(int x, int y) {
		// TODO Validation

		super.move(x, y);
	}


	/* (non-Javadoc)
	 * @see model.AGizmoComponent#reset()
	 */
	@Override
	public void reset() {
		setMovingX(getX());
		setMovingY(getY());
		setVelo(getInitialVelo());
		start();
	}


	/* Ball specific methods */
	public double getRadius() {
		return radius;
	}

	/**
	 * TODO Shortcut method to retrieve the only Circle class within circleSets
	 * 
	 * @return
	 */
	public Circle getCircle() {
		return new Circle(getMovingX(), getMovingY(), radius);
	}

	@Override
	public String toString() {
		return "Ball " + super.toString();
	}
}
