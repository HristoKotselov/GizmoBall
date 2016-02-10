package model;

import java.awt.Color;
import java.awt.Shape;

import physics.Angle;

public class CircularBumper extends AGizmoComponent {

	public CircularBumper(String name, int x, int y, Color color, Color triggeredColor) {
		super(name, x, y, color);
		this.triggeredColour = triggeredColor;
		this.rotationAngle = new Angle(0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rotate(double radians) {
		// TODO Auto-generated method stub

	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}

}
