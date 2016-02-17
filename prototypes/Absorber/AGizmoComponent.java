package Absorber;

import java.awt.Color;
import java.awt.Shape;

abstract public class AGizmoComponent {

	/* The following attributes can be defined before the Gizmo is created **/
	/** A String given to a Gizmo to identity it    **/
	private String gizmoID;
	/** The horizontal-coordinate of the component (in pixel). Along with y-coordinate, this determines the position of a Gizmo 
	 * component on the board. Where exactly it is on the component depends on the type of Gizmo.    **/
	private int xpos;
	/** The vertical-coordinate of the component (in pixel). Along with y-coordinate, this determines the position of a Gizmo 
	 * component on the board. Where exactly it is on the component depends on the type of Gizmo.    **/
	private int ypos;
	/** The amount of width this component takes up in the board (in pixel). A component will always take up at least one 
	 * grid tile worth of space (except the ball).    **/
	private int width;
	/** The amount of height this component takes up in the board (in pixel). A component will always take up at least one 
	 * grid tile worth of space (except the ball).    **/
	private int height;
	/** Colour of the Gizmo, used by the view to determine what colour to paint    **/
	private Color colour;
	/** Whether the Gizmo is visible on the board or not */
	private boolean visibility;

	/* The following attributes have default values for each Gizmo components (will be defined automatically in sub-classes)
	 * or will be modified in the constructor of sub-classes */
	/** Colour of the Gizmo after it has been triggered (e.g. a ball hits the component)    **/
	private Color triggeredColour;
	/** The angle of rotation of a Gizmo. In this project, the default position of a Gizmo component is treated as 0; with
	 * the exception of Line, which will automatically be calculated properly upon construction **/
	private int rotationAngle;

	public AGizmoComponent(String name, int x, int y, Color color) {
		gizmoID = name;
		xpos = x;
		ypos = y;
		this.colour = color;

		// Default settings
		visibility = true;
		rotationAngle = 0;
	}

	/**
	 * Action that is executed when a Ball has a collision with this component, or from a connection of triggers. 
	 * Keep in mind there are no graphical procedures here.
	 * @modify this
	 * @effect varies with each Gizmo component; see individual class
	 */
	public abstract void triggerAction();

	/**
	 * TODO
	 * @return
	 */
	public abstract Shape getShape();

	public String getGizmoID() {
		return gizmoID;
	}

	public int getX() {
		return xpos;
	}

	public boolean setX(int x) {
		// TODO some validation
		xpos = x;
		return true;
	}

	public int getY() {
		return ypos;
	}

	public boolean setY(int y) {
		// TODO some validation
		ypos = y;
		return true;
	}

	public Color getColour() {
		return colour;
	}

	public boolean setColour(Color color) {
		// TODO some validation
		this.colour = color;
		return true;
	}

	public Color getTriggeredColour() {
		return triggeredColour;
	}

	public boolean setTriggeredColour(Color color) {
		// TODO some validation
		this.triggeredColour = color;
		return true;
	}

	public int getRotation() {
		return rotationAngle;
	}
	
	public boolean setWidth(int newWidth) {
		this.width = newWidth;
		return true;
	}
	
	/**
	 * NOTE: This method description might get overwritten by sub-classes
	 */
	public int getWidth() {
		return width;
	}
	
	public boolean setHeight(int newHeight) {
		this.height = newHeight;
		return true;
	}

	/**
	 * NOTE: This method description might get overwritten by sub-classes
	 */
	public int getHeight() {
		return height;
	}
	
	public void setVisibility(boolean visible) {
		this.visibility = visible;
	}

	public boolean getVisibility() {
		return visibility;
	}

	/**
	 * Method called when the user decides to rotate a Gizmo component.
	 * @param degree - the angle of rotation to add
	 * @modify this
	 * @effect varies with each Gizmo component; see individual class
	 */
	public boolean rotate(int degree) {
		// no need for this in the prototype!
		return true;
	}

}
