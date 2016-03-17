package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;

import physics.Angle;
import physics.Circle;
import physics.Vect;

public class Ball extends AMovingGizmo {
	
	private Vect velocity;
	
	private double radius;

	// x, y coordinates and x,y velocity
	public Ball(String name, Color color, double x, double y, Angle theta, double velo) {
		super(name, x, y, color, theta, velo);
		start();

		velocity = new Vect(theta, velo);
		radius = 5;
	}

/* Ball's non-sped up get methods */
	@Override
	public Shape getDrawingShape() {
		return new Ellipse2D.Double(0-this.radius, 0-this.radius, this.radius*2, this.radius*2);
	}

	@Override
	public Set<Circle> getCircles() {
		Set<Circle> circleSet = new HashSet<Circle>();
		
		circleSet.add(new Circle(getMovingX(), getMovingY(), radius));
		
		return circleSet;
	}
	

/* Regular methods implementation */
	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

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
	

	public void setVelo(Vect v) {
		// TODO Validation
		velocity = v;
	}

	public Vect getVelo() {
		return velocity;
	}
	

	/**
	 * TODO
	 * Shortcut method to retrieve the only Circle class within circleSets
	 * @return
	 */
	public Circle getCircle() {
		return new Circle(getMovingX(), getMovingY(), radius);
	}

}
