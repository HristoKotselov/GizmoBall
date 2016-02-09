package model;

import java.awt.Color;

abstract public class AGizmoComponent {
	/** The horizontal-coordinate of the component. Along with y-coordinate, this determines the position of a Gizmo component
	 * on the board. Where exactly it is on the component depends on the type of Gizmo.    **/
	private int xpos;
	/** The vertical-coordinate of the component. Along with y-coordinate, this determines the position of a Gizmo component
	 * on the board. Where exactly it is on the component depends on the type of Gizmo.    **/
	private int ypos;
	/** Colour of the Gizmo, used by the view to determine what colour to paint    **/
	private Color colour;
	/** Colour of the triggerable Gizmo, after it has been triggered (a ball hits the component)    **/
	private Color triggeredColour;
	
	public AGizmoComponent(int x, int y, Color color, Color triggeredColor) {
		xpos = x;
		ypos = y;
		this.colour = color;
		this.triggeredColour = triggeredColor;
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
	
	/**
	 * Action that is executed when a Ball has a collision with this component, or from a connection of triggers. 
	 * Keep in mind there are no graphical procedures here.
	 * @modify this
	 * @effect varies with each Gizmo component
	 */
	abstract public void triggerAction();

}
