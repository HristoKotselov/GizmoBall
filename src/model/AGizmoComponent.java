package model;

import java.awt.Color;
import java.awt.Shape;
import java.util.HashSet;
import java.util.Set;
import physics.Circle;
import physics.LineSegment;

/**
 * TODO Parent class of ALL Gizmo Component. Contain key attribute that all
 * Gizmo should process.
 */
abstract public class AGizmoComponent {

	/* The following attributes can be defined before the Gizmo is created **/
	/** A String given to a Gizmo to identity it **/
	private String gizmoID;
	/**
	 * The horizontal-coordinate of the component (in pixel). Along with
	 * x-coordinate, this determines the position of a Gizmo component on the
	 * board. Where this is exactly depends on the type of Gizmo
	 **/
	private int xpos;
	/**
	 * The vertical-coordinate of the component (in pixel). Along with
	 * y-coordinate, this determines the position of a Gizmo component on the
	 * board. Where this is exactly depends on the type of Gizmo
	 **/
	private int ypos;
	/**
	 * Colour of the Gizmo, used by the view to determine what colour to paint
	 **/
	private Color colour;

	/* The following attributes have default values for each Gizmo components (will be defined automatically in sub-classes)
	 * or will be modified in the constructor of sub-classes */
	/** Whether the Gizmo is visible on the board or not */
	private boolean visibility;
	/**
	 * The angle of rotation of a Gizmo. In this project, the default position
	 * of a Gizmo component is treated as 0; with the exception of Line, which
	 * will automatically be calculated properly upon construction
	 **/
	protected int rotationAngle;
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
	 * The number of vertical grid taken up by this Gizmo (+y). Whenever a user
	 * click on a pixel that is within the space of (xpos + xpos*bmWidth) &
	 * (ypos + ypos*bmHeight), the selection will be directed to this Gizmo.
	 * 
	 * Can be used by Gizmo with variable Height/Width (such as Absorber) to
	 * store their dimensions.
	 */
	protected int bmHeight;

	public AGizmoComponent(String name, int x, int y, Color color) {
		gizmoID = name;
		xpos = x;
		ypos = y;
		this.colour = color;

		// Default settings
		visibility = true;
		rotationAngle = 0;
		bmWidth = 1;
		bmHeight = 1;
	}

	/**
	 * Action that is executed when a Ball has a collision with this component,
	 * or from a connection of triggers. Keep in mind there are no graphical
	 * procedures here.
	 * 
	 * @modify this
	 * @effect varies with each Gizmo component; see individual class
	 */
	abstract public void triggerAction();

	abstract public Shape getDrawingShape();

	abstract public Set<Circle> getCircles();



	public String getGizmoID() {
		return gizmoID;
	}

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

	public int getBMWidth() {
		return bmWidth;
	}

	public int getBMHeight() {
		return bmHeight;
	}

	public boolean setColour(Color color) {
		// TODO some validation
		this.colour = color;
		return true;
	}

	public Color getColour() {
		return colour;
	}

	/**
	 * Method called when the user decides to rotate a Gizmo component.
	 * 
	 * @param degree
	 *            - the angle of rotation to add
	 * @modify this
	 * @effect rotationAngle = new angle; Circle Set is updated; IF gizmo uses
	 *         Line Segments, THEN the collection of Line Segment is updated; IF
	 *         gizmo is a supertype of AStatueGizmo, THEN drawingShape is
	 *         updated. Can have additional effects, check individual Gizmo for
	 *         them.
	 */
	abstract public boolean rotate(int degree);

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
	public boolean move(int newX, int newY) {
		setX(newX * MainEngine.L);
		setY(newY * MainEngine.L);
		// TODO Validation
		return false;
	}

	public int getRotation() {
		return rotationAngle;
	}

	public void setVisibility(boolean visible) {
		this.visibility = visible;
	}

	public boolean getVisibility() {
		return visibility;
	}

	@Override
	public String toString() {
		return gizmoID + " " + (xpos / MainEngine.L) + " " + (ypos / MainEngine.L);
	}
}
