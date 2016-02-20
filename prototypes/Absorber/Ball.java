package Absorber;

import java.awt.Color;
import java.awt.Shape;

import physics.Angle;
import physics.Circle;
import physics.Vect;

public class Ball extends AGizmoComponent{

	private double radius;
	private double preciseXPos;
	private double preciseYPos;
	private Vect velocity;

	private boolean stopped;

	// x, y coordinates and x,y velocity
	public Ball(String name, Color color, double x, double y, Angle theta, double yv) {
		super(name, (int) x, (int) y, color);
		
		radius = 5;
		preciseXPos = x; // Centre coordinates
		preciseYPos = y;
		velocity = new Vect(theta, yv);
		stopped = false;
	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/* Ball specific methods that deal with double precision. */
	public double getExactX() {
		return preciseXPos;
	}

	public double getExactY() {
		return preciseYPos;
	}

	public void setExactX(double x) {
		preciseXPos = x;
	}

	public void setExactY(double y) {
		preciseYPos = y;
	}

	public void stop() {
		stopped = true;
	}

	public void start() {
		stopped = false;
	}

	public boolean stopped() {
		return stopped;
	}
	
	public Vect getVelo() {
		return velocity;
	}

	public void setVelo(Vect v) {
		velocity = v;
	}

	public double getRadius() {
		return radius;
	}

	public Circle getCircle() {
		return new Circle(preciseXPos, preciseYPos, radius);

	}
}
