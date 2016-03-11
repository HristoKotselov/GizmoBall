package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import physics.Circle;

public class CircularBumper extends AStatueGizmo {

	private int radius;

	public CircularBumper(String name, int grid_tile_x, int grid_tile_y, Color color) {
		super(name, grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, color);
		radius = 10;
		Circle circle = new Circle(grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, radius);
		circleSet.add(circle);
	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setupDrawingShape() {
		drawingShape = new Ellipse2D.Double(0, 0, MainEngine.L, MainEngine.L);
	}

	@Override
	protected void setupCircles() {
		Circle circle = new Circle(getX() + 10, getY() + 10, 10);
		circleSet.add(circle);
	}

	@Override
	public void updateCollections() {
		setupDrawingShape();
		setupCircles();
	}

	@Override
	public boolean rotate(int degree) {

		updateCollections();

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean move(int newX, int newY) {

		updateCollections();

		// TODO Auto-generated method stub
		return false;
	}

	/* CircularBumper exclusive methods */
	public double getRadius() {
		return radius;
	}

}
