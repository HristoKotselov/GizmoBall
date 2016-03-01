package model;

import java.awt.Color;
import java.awt.Shape;
import java.util.Set;

import physics.Angle;
import physics.Circle;
import physics.LineSegment;

public class Flipper extends AStationaryGizmo implements ILineSegmentCollider {

	public Flipper(String name, int grid_tile_x, int grid_tile_y, Color color) {
		super(name, grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, color);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public Shape getDrawingShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Circle> getCircles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<LineSegment> getLineSeg() {
		// TODO Auto-generated method stub
		return null;
	}
}
