package model;

import java.awt.Color;

import physics.Angle;
import physics.Vect;

/**
 * TODO
 * Class designed for Gizmos that can actively move around on the Board. 
 * For these Gizmos, PreciseX & PreciseY methods can be used instead if their position on the board need 
 * be precise (i.e. move about rapidly). Keep in mind that preciseX/Y replaces the normal X/Y's role 
 * in this class.
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

	private Vect velocity;

	private boolean stopped;

	public AMovingGizmo(String name, double x, double y, Color color, Angle theta, double velo) {
		super(name, (int) x, (int) y, color);

		preciseXPos = x; // Centre coordinates
		preciseYPos = y;
		preciseXStartingPos = x;
		preciseYStartingPos = y;
		velocity = new Vect(theta, velo);
	}
	
	/** 
	 * Same function as setPreciseX(), but rounding the parameter to whole number.
	 * This method replaces the version from AGizmoComponent.
	 * @see model.AGizmoComponent#setY(int)
	 */
	@Override
	public boolean setX(int x) {
		return setPreciseX(x);
	}

	/** 
	 * Same function as setPreciseY(), but rounding the parameter to whole number.
	 * This method replaces the version from AGizmoComponent.
	 * @see model.AGizmoComponent#setY(int)
	 */
	@Override
	public boolean setY(int y) {
		return setPreciseY(y);
	}
	
	/** 
	 * Same function as getPreciseX(), but rounding to whole number first.
	 * This method replaces the version from AGizmoComponent.
	 * @see model.AGizmoComponent#getY(int)
	 */
	@Override
	public int getX() {
		return (int) preciseXPos;
	}

	/** 
	 * Same function as getPreciseY(), but rounding to whole number first.
	 * This method replaces the version from AGizmoComponent.
	 * @see model.AGizmoComponent#getY(int)
	 */
	@Override
	public int getY() {
		return (int) preciseYPos;
	}

	public boolean setPreciseX(double x) {
		// TODO Validation
		preciseXPos = x;
		
		return true;
	}

	public boolean setPreciseY(double y) {
		// TODO Validation
		preciseYPos = y;
		
		return true;
	}

	public double getPreciseX() {
		return preciseXPos;
	}

	public double getPreciseY() {
		return preciseYPos;
	}

	public boolean setPreciseStartingX(double x) {
		// TODO Validation
		preciseXStartingPos = x;
		
		return true;
	}

	public boolean setPreciseStartingY(double y) {
		// TODO Validation
		preciseYStartingPos = y;
		
		return true;
	}

	public double getPreciseStartingX() {
		return preciseXStartingPos;
	}

	public double getPreciseStartingY() {
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

}
