package Absorber;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class Absorber extends AGizmoComponent {
	
	/** The currently captured Ball within the Absorber. If there is no ball within the absorber, then this object becomes null. */
	private Ball capturedBall;
	
	public Absorber(String name, int grid_tile_x, int grid_tile_y, int width, int height, Color color) {
		super(name, grid_tile_x * Model.L, grid_tile_y * Model.L, color);
		setWidth(width * Model.L);
		setHeight(height * Model.L);
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
	
	/* Absorber exclusive methods */
	public void setBall(Ball b) {
		// TODO Auto-generated method stub

	}
	
	public Ball getCapturedBall() {
		// TODO Auto-generated method stub
		return capturedBall;
	}

}
