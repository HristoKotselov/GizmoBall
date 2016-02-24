package model;

import java.awt.Color;
import java.awt.Shape;
import java.util.Set;

import physics.Angle;
import physics.LineSegment;

public class TriangularBumper extends AGizmoComponent implements ILineSegmentCollider {

	public TriangularBumper(String name, int grid_tile_x, int grid_tile_y, Color color) {
		super(name, grid_tile_x, grid_tile_y, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setupDrawingShape() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setupCircles() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<LineSegment> getLineSeg() {
		// TODO Auto-generated method stub
		return null;
	}

}
