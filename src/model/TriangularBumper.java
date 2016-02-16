package model;

import java.awt.Color;
import java.awt.Shape;

import physics.Angle;

public class TriangularBumper extends AGizmoComponent {

	public TriangularBumper(String name, int x, int y, Color color, Color triggeredColor) {
		super(name, x, y, color);
		setTriggeredColour(triggeredColor);
		// TODO Auto-generated constructor stub
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

}
