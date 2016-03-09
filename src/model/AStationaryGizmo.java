package model;

import java.awt.Color;

/**
 * TODO
 * Class designed for Gizmos that do not actively move around on the board, but might change their shape at any given time (e.g. rotation). 
 * Thus, their Shape & Circles are not guaranteed to remain constant and should be generated on the spot.
 * Examples:	Flipper
 */
abstract public class AStationaryGizmo extends AGizmoComponent {

	private boolean stopped;

	public AStationaryGizmo(String name, int grid_tile_x, int grid_tile_y, Color color) {
		super(name, grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, color);

	}

	public void stop() {
		stopped = true;
	}

	public void start() {
		stopped = false;
	}

	public boolean stopped() {
		return stopped;
	}
}
