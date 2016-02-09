package model;

import java.awt.Color;

abstract public class AGizmoComponent {
	/** The x-coordinate of the component. Along with y-coordinate, this determines the position of a Gizmo component
	 * on the board. Exact  **/
	private double xpos;
	private double ypos;
	private Color colour;
	
	public AGizmoComponent(double x, double y, Color color) {
		xpos = x; // Centre coordinates
		ypos = y;
		this.colour = color;
	}
}
