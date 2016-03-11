package model;

import java.awt.Color;

import physics.Angle;
import physics.Vect;

/**
 * TODO
 * Class designed for Gizmos that can move around on the Board (even just certain parts moving). For these Gizmos, 
 * PreciseX & PreciseY methods can be used instead if their position on the board need be precise DURING gameplay 
 * (i.e. move about rapidly).
 * Examples:	Ball, Flipper
 */
abstract public class AMovableGizmo extends AGizmoComponent {

	/** X-coordinate (pixel) of the Gizmo at any moment during gameplay **/
	private double preciseXPos;
	/** Y-coordinate (pixel) of the Gizmo at any moment during gameplay **/
	private double preciseYPos;
	/** X-coordinate (pixel) of the Gizmo before the game starts (i.e. during Build Mode) **/
	private double preciseXStartingPos;
	/** Y-coordinate (pixel) of the Gizmo before the game starts (i.e. during Build Mode) **/
	private double preciseYStartingPos;

	private Vect velocity;

	private boolean stopped;

	public AMovableGizmo(String name, double x, double y, Color color, Angle theta, double velo) {
		super(name, (int) x, (int) y, color);

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

}
