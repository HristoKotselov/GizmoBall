package model;

import java.awt.Color;
import java.awt.Shape;
import java.util.Set;
import physics.Circle;

/**
 * Parent class of ALL Gizmo Component. Contain key attribute that all Gizmo should process.
 */
abstract public class AGizmoComponent {

	/* The following attributes have to be decided before the Gizmo is created **/
	/** A String given to a Gizmo to identity it **/
	private String gizmoID;

	/**
	 * The horizontal-coordinate of the component (in pixel). Along with x-coordinate, this determines the position of a Gizmo component on
	 * the board. For stationary Gizmos, this point should always be the left corner of a grid square. For moving Gizmos, this determines
	 * their starting position when the game starts.
	 **/
	private int xpos;
	/**
	 * The vertical-coordinate of the component (in pixel). Along with y-coordinate, this determines the position of a Gizmo component on
	 * the board. For stationary Gizmos, this point should always be the left corner of a grid square. For moving Gizmos, this determines
	 * their starting position when the game starts.
	 **/
	private int ypos;

	/**
	 * Initial Colour of Gizmo
	 **/
	private Color initialColour;

	/**
	 * Colour of the Gizmo, used by the view to determine what colour to paint
	 **/
	private Color colour;

	/* The following attributes have default values for each Gizmo components (will be defined automatically in sub-classes)
	 * or will be modified in the constructor of sub-classes */
	/** Whether the Gizmo is visible on the board or not */
	private boolean visibility;

	/**
	 * The angle of rotation of a Gizmo. In this project, the default position of a Gizmo component is treated as 0.
	 **/
	protected int rotationAngle;
	
	protected double timeTilRevert;


	public AGizmoComponent(String name, int x, int y, Color color) {
		gizmoID = name;
		xpos = x;
		ypos = y;

		// clone Color to initialColor
		this.colour = color;
		float[] compArray = colour.getColorComponents(null);
		initialColour = new Color(colour.getColorSpace(), compArray, 1.0f);


		// Default settings
		visibility = true;
		rotationAngle = 0;
	}


	/* Concrete methods */
	public String getGizmoID() {
		return gizmoID;
	}

	public boolean setX(int x) {
		xpos = x;
		return true;
	}

	public int getX() {
		return xpos;
	}

	public boolean setY(int y) {
		ypos = y;
		return true;
	}

	public int getY() {
		return ypos;
	}

	public boolean setColour(Color color) {
		this.colour = color;
		return true;
	}

	public Color getColour() {
		return colour;
	}

	public Color getInitialColour() {
		return initialColour;
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


	/* Concrete methods that might be overwritten */
	/**
	 * Method called when the user decides to move this Gizmo component.
	 * 
	 * @param newX
	 *            new X coordinate (in pixels)
	 * @param newY
	 *            new Y coordinate (in pixels)
	 * @modify this
	 * @effect xpos & ypos is updated with the new values.
	 */
	public void move(int newX, int newY) {
		setX(newX);
		setY(newY);
	}

	public void update(double moveTime) {
		// By default, do nothing
	}

	/* Collection-sped-up-able abstract methods */
	abstract public Shape getDrawingShape();

	abstract public Set<Circle> getCircles();


	/* Other abstract methods */

	/**
	 * Procedure that is executed when a Ball has a collision with this component. Might execute the action() sequence.
	 * 
	 * @param cd
	 *            - the collection of all objects related to the collision
	 * @modify maybe this, maybe cd
	 * @effect varies with each Gizmo component; see individual class
	 */
	abstract public void ballTriggered(CollisionDetails cd);


	/**
	 * Action that might be executed when a Ball collide with Gizmo (depends on the Gizmo), or from a connection of triggers. Keep in mind
	 * there are no graphical procedures here.
	 * 
	 * @modify this
	 * @effect varies with each Gizmo component; see individual class
	 */
	abstract public void action();

	/**
	 * Method called when the user decides to rotate a Gizmo component.
	 * 
	 * @param degree
	 *            the angle of rotation to add
	 * @modify this
	 * @effect rotationAngle = degree;
	 * @return TRUE if the rotate operation succeeded (even if a Gizmo cannot be rotated usually, like CircularBumper for example), FALSE
	 *         only if rotating a Gizmo causes it to overlap another Gizmo (at least one of the Gizmo in that case will need to be a Moving
	 *         Gizmo)
	 */
	abstract public boolean rotate(int degree);

	/**
	 * Method called when the game is reset in Play Mode. For most Gizmos, this method will just change one or two fields that can be
	 * changed during gameplay (e.g. Color).
	 * 
	 * @modify this
	 * @effect varies with each Gizmo component; see individual class;
	 */
	abstract public void reset();

	abstract public String toString();

}