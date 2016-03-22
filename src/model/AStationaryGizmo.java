package model;

import java.awt.Color;

/**
 * TODO
 * Class designed for Gizmos that do not actively move around on the board, but might change their shape 
 * at any given time (e.g. rotation).
 * Examples:	Square Bumper, Circular Bumper, Flipper
 */
abstract public class AStationaryGizmo extends AGizmoComponent {
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
		super(name, x, y, color);
		
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
		return getGizmoID() + " " + (getX() / MainEngine.L) + " " + (getY() / MainEngine.L);
	}

}
