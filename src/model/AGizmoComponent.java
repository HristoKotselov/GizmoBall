package model;

import java.awt.Color;
import java.awt.Shape;

import physics.Angle;

abstract public class AGizmoComponent {

	/* The following attributes can be defined before the Gizmo is created **/
	/** A String given to a Gizmo to identity it    **/
	private String gizmoID;
	/** The horizontal-coordinate of the component. Along with y-coordinate, this determines the position of a Gizmo component
	 * on the board. Where exactly it is on the component depends on the type of Gizmo.    **/
	private int xpos;
	/** The vertical-coordinate of the component. Along with y-coordinate, this determines the position of a Gizmo component
	 * on the board. Where exactly it is on the component depends on the type of Gizmo.    **/
	private int ypos;
	/** Colour of the Gizmo, used by the view to determine what colour to paint    **/
	private Color colour;

	/* The following attributes have default values for each Gizmo components (will be defined automatically in sub-classes */
	/** Colour of the Gizmo after it has been triggered (e.g. a ball hits the component)    **/
	protected Color triggeredColour;
	/** The angle of rotation of a Gizmo. In this project, the default position of a Gizmo component is treated as 0; with
	 * the exception of Line, which will automatically be calculated properly upon construction **/
	protected Angle rotationAngle;

	public AGizmoComponent(String name, int x, int y, Color color) {
		gizmoID = name;
		xpos = x;
		ypos = y;
		this.colour = color;
	}

	/**
	 * Method called when the user decides to rotate a Gizmo component.
	 * @param degree - how many degree to rotate by 
	 * @modify this
	 * @effect varies with each Gizmo component; see individual class
	 */
	public abstract void rotate(double radians);

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

	public int getY() {
		return ypos;
	}

	public Color getColour() {
		return colour;
	}

	public Color getTriggeredColour() {
		return triggeredColour;
	}

	public double getRotation() {
		return rotationAngle.radians();
	}

}
