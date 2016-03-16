package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Set;

import physics.Circle;

public class CircularBumper extends AStatueGizmo {

	private int radius;

	public CircularBumper(String name, int grid_tile_x, int grid_tile_y, Color color) {
		/* NOTE -	The following methods are called by the superclass's constructor:
			setupDrawingShape();
			setupCircles();
		*/
		super(name, grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, color);
		
		radius = 10;
		

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
		super.move(newX, newY);
		
		updateCollections();

		// TODO Auto-generated method stub
		return false;
	}

	/* CircularBumper exclusive methods */
	public double getRadius() {
		return radius;
	}

	@Override
	public String toString(){
		return "Circle " + super.toString();
	}
}
