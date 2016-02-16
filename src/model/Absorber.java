package model;

import java.awt.Color;
import java.awt.Shape;

import physics.Angle;

public class Absorber extends AGizmoComponent {

	public Absorber(String name, int x, int y, Color color) {
		super(name, x, y, color);
		setTriggeredColour(color);
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
