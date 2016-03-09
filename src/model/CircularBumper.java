package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Set;

import physics.Circle;
import physics.LineSegment;

public class CircularBumper extends AStatueGizmo {
	
	private int radius;

	public CircularBumper(String name, int grid_tile_x, int grid_tile_y, Color color) {
		super(name, grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, color);
		radius = 10;
		Circle circle = new Circle(grid_tile_x * MainEngine.L,grid_tile_y * MainEngine.L, radius);
		circleSet.add(circle);
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
	public void triggerAction() {
		// TODO Auto-generated method stub

	}
	
	public double getRadius() {
		return radius;
	}

	//@Override
	public Set<LineSegment> getLines() {
		// TODO Auto-generated method stub
		return null;
	}

}
