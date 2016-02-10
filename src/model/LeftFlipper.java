package model;

import java.awt.Color;
import java.awt.Shape;

import physics.Angle;

public class LeftFlipper extends AGizmoComponent {

	public LeftFlipper(String name, int x, int y, Color color) {
		super(name, x, y, color);
		this.triggeredColour = color;
		this.rotationAngle = new Angle(0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rotate(double degree) {
		// TODO Auto-generated method stub

	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}

}
