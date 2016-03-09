package model;

import java.awt.Color;
import java.awt.Shape;

import physics.Angle;
import physics.Vect;

/**
 * TODO
 * Class designed for Gizmos that can actively move around on the Board. For these Gizmos, PreciseX & PreciseY methods should be used
 * instead of the standard position methods in AGizmoComponent DURING gameplay.
 * Examples:	Ball
 */
abstract public class AMovingGizmo extends AGizmoComponent {

	/** X-coordinate (pixel) of the Gizmo at any moment during gameplay **/
	private double preciseXPos;
	/** Y-coordinate (pixel) of the Gizmo at any moment during gameplay **/
	private double preciseYPos;
	/** X-coordinate (pixel) of the Gizmo before the game starts (i.e. during Build Mode) **/
	private double preciseXStartingPos;
	/** Y-coordinate (pixel) of the Gizmo before the game starts (i.e. during Build Mode) **/
	private double preciseYStartingPos;
	
	
	protected Shape drawingShape;

	private Vect velocity;

	private boolean stopped;

	public AMovingGizmo(String name, double x, double y, Color color, Angle theta, double velo) {
		super(name, (int) x, (int) y, color);
		setupDrawingShape();
		preciseXPos = x; // Centre coordinates
		preciseYPos = y;
		preciseXStartingPos = x;
		preciseYStartingPos = y;
		velocity = new Vect(theta, velo);
	}

	public void setPreciseX(double x) {
		// TODO Validation
		preciseXPos = x;
	}

	public void setPreciseY(double y) {
		// TODO Validation
		preciseYPos = y;
	}

	public double getPreciseX() {
		return preciseXPos;
	}

	public double getPreciseY() {
		return preciseYPos;
	}

	public void setExactStartingX(double x) {
		// TODO Validation
		preciseXStartingPos = x;
	}

	public void setExactStartingY(double y) {
		// TODO Validation
		preciseYStartingPos = y;
	}

	public double getExactStartingX() {
		return preciseXStartingPos;
	}

	public double getExactStartingY() {
		return preciseYStartingPos;
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

	public void setVelo(Vect v) {
		// TODO Validation
		velocity = v;
	}

	public Vect getVelo() {
		return velocity;
	}
	
	public void update(){
		
	}
	
	abstract protected void setupDrawingShape();

}
