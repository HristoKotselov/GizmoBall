package Absorber;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

import FileLoader.src.Const;

public class Absorber extends AGizmoComponent {

	public Absorber(String name, int x, int y, Color color) {
		super(name, x, y, color);
		setTriggeredColour(color);
		// no need for this in the prototype:		setHeight( );
		// no need for this in the prototype:		 setWidth( );
		// TODO Auto-generated constructor stub
	}

	@Override
	public Shape getShape() {
		return new Rectangle(Const.L * getWidth(), Const.L * getHeight());
	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}

}
