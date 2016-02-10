package model;

import java.awt.Color;

import physics.Angle;
import physics.Circle;
import physics.Vect;

public class Ball extends AGizmoComponent {
	
	private Vect velocity;
	private double radius;
	private double xpos; // most likely from abstract class and will be removed from here
	private double ypos; // same as above
	private Color colour;

	private boolean stopped;

	// x, y coordinates and x,y velocity
	public Ball(double x, double y, Angle theta, double yv) {
		xpos = x; // Centre coordinates
		ypos = y;
		colour = Color.BLUE;
		velocity = new Vect(theta, yv);
		radius = 5;
		stopped = false;
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
		return new Circle(xpos, ypos, radius);

	}

	// Ball specific methods that deal with double precision.
	public double getExactX() {
		return xpos;
	}

	public double getExactY() {
		return ypos;
	}

	public void setExactX(double x) {
		xpos = x;
	}

	public void setExactY(double y) {
		ypos = y;
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

	public Color getColour() {
		return colour;
	}
}
