package Absorber;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

import FileLoader.src.Const;

public class Absorber extends AGizmoComponent {

	public Absorber(String name, int grid_tile_x, int grid_tile_y, int width, int height, Color color) {
		super(name, grid_tile_x * Const.L, grid_tile_y * Const.L, color);
		setWidth(width * Const.L);
		setHeight(height * Const.L);
		setTriggeredColour(color);
		// no need for this in the prototype:		setHeight( );
		// no need for this in the prototype:		 setWidth( );
		// TODO Auto-generated constructor stub
	}

	@Override
	public Shape getShape() {
		return new Rectangle(getWidth(), getHeight());
	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}

}
