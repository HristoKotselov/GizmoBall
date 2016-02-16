package model;

import java.awt.Color;
import java.awt.Shape;

public class Line extends AGizmoComponent {
	/** The horizontal-coordinate of the line. Along with ypos2, this determines where the line will end. 	**/
	private int xpos2;
	/**  The vertical-coordinate of the line. Along with xpos2, this determines where the line will end. 	**/
	private int ypos2;

	public Line(String name, int x1, int y1, int x2, int y2) {
		super(name, x1, y2, new Color(0, 0, 0, 0)); // " new Color(0, 0, 0, 0) " is transparent
		setTriggeredColour(new Color(0, 0, 0, 0));

		// TODO need to make LineSegments first		this.rotationAngle = ;

		xpos2 = x2;
		ypos2 = y2;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
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
