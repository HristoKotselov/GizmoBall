package model;

import java.awt.Color;

public class Line extends AGizmoComponent {
	/** The horizontal-coordinate of the component. Along with y-coordinate, this determines the position of a Gizmo component
	 * on the board. Where exactly it is on the component depends on the type of Gizmo.    **/
	private int xpos2;
	/** The vertical-coordinate of the component. Along with y-coordinate, this determines the position of a Gizmo component
	 * on the board. Where exactly it is on the component depends on the type of Gizmo.    **/
	private int ypos2;

	public Line(int x1, int y1, int x2, int y2, Color color, Color triggeredColor) {
		super(x1, y2, color, triggeredColor);
		xpos2 = x2;
		ypos2 = y2;
	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}

/* Root class methods */
	public int getX2() {
		return xpos2;
	}

	public int getY2() {
		return ypos2;
	}

}
