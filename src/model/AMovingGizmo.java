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

	/** The horizontal-coordinate of the component (in pixel). Along with the movingYPos,
	 * This determines the position of a Gizmo component on the board, during gameplay. 
	 * Where this is exactly depends on the type of Gizmo. **/
	private double movingXPos;
	
	/** The vertical-coordinate of the component (in pixel). Along with the movingXPos,
	 * This determines the position of a Gizmo component on the board, during gameplay. 
	 * Where this is exactly depends on the type of Gizmo. **/
	private double movingYPos;

	private boolean stopped;

	
	public AMovingGizmo(String name, double x, double y, Color color, Angle theta, double velo) {
		super(name, (int) x, (int) y, color);

		movingXPos = x;
		movingYPos = y;
	}


/* Exclusive Methods to this type of Gizmo */
	public void stop() {
		stopped = true;
	}

	public void start() {
		stopped = false;
	}

	public boolean stopped() {
		return stopped;
	}
	
	public boolean setMovingX(double x) {
		// TODO Validation
		movingXPos = x;
		
		return true;
	}

	public boolean setMovingY(double y) {
		// TODO Validation
		movingYPos = y;
		
		return true;
	}

	public double getMovingX() {
		return movingXPos;
	}

	public double getMovingY() {
		return movingYPos;
	}


	
/* Abstract methods that can be implemented already */
	@Override
	public String toString() {
		
		return getGizmoID() + " " + (getX() / MainEngine.L) + " " + (getY() / MainEngine.L);
	}
	
}
