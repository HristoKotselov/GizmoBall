package model;

import java.awt.Color;
import java.awt.Shape;

public class Flipper extends AGizmoComponent{

	public Flipper(String name, int x, int y, Color color) {
		super(name, x, y, color);
		setTriggeredColour(color);
		// finish this line:		setHeight( );
		// finish this line:		 setWidth( );
		// TODO Auto-generated constructor stub
	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}

}
