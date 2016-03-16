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

	/** The horizontal-coordinate of the component (in pixel). Along with the preciseYPos,
	 * This determines the position of a Gizmo component on the board, during gameplay. 
	 * Where this is exactly depends on the type of Gizmo. **/
	private double preciseXPos;
	
	/** The vertical-coordinate of the component (in pixel). Along with the preciseXPos,
	 * This determines the position of a Gizmo component on the board, during gameplay. 
	 * Where this is exactly depends on the type of Gizmo. **/
	private double preciseYPos;
	
	/** Same as preciseXPos, but this value is used to determine the Gizmo's position 
	 * before the game starts (i.e. during Build Mode) **/
	private double preciseXStartingPos;
	
	/** Same as preciseYPos, but this value is used to determine the Gizmo's position 
	 * before the game starts (i.e. during Build Mode) **/
	private double preciseYStartingPos;

	private Vect velocity;

	private boolean stopped;

	
	public AMovingGizmo(String name, double x, double y, Color color, Angle theta, double velo) {
		super(name, color);

		preciseXPos = x; // Centre coordinates
		preciseYPos = y;
		preciseXStartingPos = x;
		preciseYStartingPos = y;
		velocity = new Vect(theta, velo);
	}


/* Exclusive Methods to this type of Gizmo */
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
	
	
/* Abstract methods that can be implemented already */
	@Override
	public String toString() {
		
		// TODO Adapt to new class
		// return getGizmoID() + " " + (xpos / MainEngine.L) + " " + (ypos / MainEngine.L);
		return null;
	}
	
/* Shared methods across AStatueGizmo & AMovingGizmo, but with different parameters */
	
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
	
	/**
	 * Method called when the user decides to move a Gizmo component.
	 * 
	 * @param newX
	 *            - new X coordinate
	 * @param newY
	 *            - new Y coordinate
	 * @modify this
	 * @effect Circle Set is updated; IF gizmo uses Line Segments, THEN the
	 *         collection of Line Segment is updated; IF gizmo is a supertype of
	 *         AStatueGizmo, THEN drawingShape is updated.
	 */
	abstract public boolean move(double newX, double newY);


}
