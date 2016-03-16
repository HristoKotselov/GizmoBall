package model;

import java.awt.Color;

/**
 * TODO
 * Class designed for Gizmos that do not actively move around on the board, but might change their shape 
 * at any given time (e.g. rotation).
 * Examples:	Square Bumper, Circular Bumper, Flipper
 */
abstract public class AStationaryGizmo extends AGizmoComponent {
	
/* The following attributes have to be decided before the Gizmo is created **/
	/**
	 * The horizontal-coordinate of the component (in pixel). Along with
	 * ypos, this determines the position of a Gizmo component on the
	 * board. Where this is exactly depends on the type of Gizmo
	 **/
	private int xpos;
	
	/**
	 * The vertical-coordinate of the component (in pixel). Along with
	 * xpos, this determines the position of a Gizmo component on the
	 * board. Where this is exactly depends on the type of Gizmo
	 **/
	private int ypos;

/* The following attributes have default values for each Gizmo components (will be defined automatically in sub-classes)
 * or will be modified in the constructor of sub-classes */
	/**
	 * The number of horizontal grid taken up by this Gizmo (+x). Whenever a
	 * user click on a pixel that is within the space of (xpos + xpos*bmWidth) &
	 * (ypos + ypos*bmHeight), the selection will be directed to this Gizmo.
	 * 
	 * Can be used by Gizmo with variable Height/Width (such as Absorber) to 
	 * store their dimensions.
	 */
	protected int bmWidth;
	
	/**
	 * The number of vertical grid taken up by this Gizmo (+y). Whenever a
	 * user click on a pixel that is within the space of (xpos + xpos*bmWidth) &
	 * (ypos + ypos*bmHeight), the selection will be directed to this Gizmo.
	 * 
	 * Can be used by Gizmo with variable Height/Width (such as Absorber) to 
	 * store their dimensions.
	 */
	protected int bmHeight;

	
	public AStationaryGizmo(String name, int x, int y, Color color) {
		super(name, color);
		
		xpos = x;
		ypos = y;

		bmWidth = 1;
		bmHeight = 1;
	}


/* Exclusive Methods to this type of Gizmo */
	public int getBMWidth() {
		return bmWidth;
	}

	public int getBMHeight() {
		return bmHeight;
	}
	
	
/* Abstract methods that can be implemented already */
	@Override
	public String toString() {
		return getGizmoID() + " " + (xpos / MainEngine.L) + " " + (ypos / MainEngine.L);
	}
	
	
/* Shared methods across AStatueGizmo & AMovingGizmo, but with different parameters */
	
	public boolean setX(int x) {
		// TODO some validation
		xpos = x;
		return true;
	}

	public int getX() {
		return xpos;
	}

	public boolean setY(int y) {
		// TODO some validation
		ypos = y;
		return true;
	}

	public int getY() {
		return ypos;
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
	abstract public boolean move(int newX, int newY);

}
