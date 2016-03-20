package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import model.AGizmoComponent;
import model.AMovingGizmo;
import model.AStationaryGizmo;
import model.Absorber;
import model.Ball;
import model.CircularBumper;
import model.Flipper;
import model.IMainEngine;
import model.SquareBumper;
import model.TriangularBumper;
import physics.Angle;
import view.BuildMenu;
import view.IGameWindow;

public class BuildModeMouseListener implements MouseInputListener {
	private IGameWindow gw;
	private BuildMenu bm;
	private IMainEngine m;

	private int x, y;
	/**
	 * Memory values used when adding a Gizmo can have variable width\height
	 * (e.g. Absorber)
	 * 
	 * -1 is the default value that indicates the 1st click is required to start
	 * the size-selection procedures.
	 **/
	private int x2 = -1, y2 = -1;

	/**
	 * Memory value used when moving a Gizmo to a different position.
	 * 
	 * null is the default value that indicates the 1st click is required to
	 * start the moving procedure.
	 */
	private AGizmoComponent moveG;

	public BuildModeMouseListener(IGameWindow gw, IMainEngine m, BuildMenu bm) {
		this.gw = gw;
		this.m = m;
		this.bm = bm;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		int L = m.getLInPixels();
		int grid_tile_x = x / L;
		int grid_tile_y = y / L;

		String selectedFunction = bm.getSelectedFunction();

		AGizmoComponent g = null;

		switch (selectedFunction) {
			case "Remove Gizmo":
				// TODO add method for to check for AMovingGizmo first, i.e.
				// Ball
				g = m.getStationaryGizmoAt(grid_tile_x, grid_tile_y);

				if (g != null) {
					m.removeGizmo(g);
				}

				break;

			case "Rotate Gizmo":
				// TODO add method for to check for AMovingGizmo first, i.e.
				// Ball
				g = m.getStationaryGizmoAt(grid_tile_x, grid_tile_y);

				if (g != null) {
					m.rotateGizmo(g, 90);
				}

				break;

			case "Move Gizmo":
				if (moveG == null) {
					// TODO add method for to check for AMovingGizmo first, i.e.
					// Ball
					moveG = m.getStationaryGizmoAt(grid_tile_x, grid_tile_y);
					// TODO change ActionTip to remind user of the currently
					// selected Gizmo
				} else {
					if (moveG instanceof AStationaryGizmo) {
						m.moveGizmoByL(moveG, grid_tile_x, grid_tile_y);
					}
					if (moveG instanceof AMovingGizmo) {
						// TODO moveGizmoByPixels()
					}
					moveG = null;
				}

				break;

			case "Add Ball":
				try {
					double angle = bm.getBallDirection();
					double speed = bm.getBallSpeed() * m.getLInPixels();

					g = new Ball("Ball(" + x + "," + y + ")", Color.BLUE, x, y, new Angle(Math.toRadians(angle)), speed);
					m.addGizmo(g);
				} catch (NumberFormatException ex) {
				}

				break;

			case "Add Gizmo":
				String selectedGizmo = bm.getSelectedGizmo();

				switch (selectedGizmo) {
					case "Square":
						g = new SquareBumper("s(" + grid_tile_x + "," + grid_tile_y + ")", grid_tile_x, grid_tile_y, Color.RED);
						m.addGizmo(g);
						break;

					case "Triangle":
						g = new TriangularBumper("t(" + grid_tile_x + "," + grid_tile_y + ")", grid_tile_x, grid_tile_y, Color.BLUE);
						m.addGizmo(g);
						break;

					case "Circle":
						g = new CircularBumper("c(" + grid_tile_x + "," + grid_tile_y + ")", grid_tile_x, grid_tile_y, Color.GREEN);
						m.addGizmo(g);
						break;

					case "Left Flipper":
						g = new Flipper("lf(" + grid_tile_x + "," + grid_tile_y + ")", grid_tile_x, grid_tile_y, Color.ORANGE, Flipper.LEFT);
						m.addGizmo(g);
						break;

					case "Right Flipper":
						g = new Flipper("rf(" + grid_tile_x + "," + grid_tile_y + ")", grid_tile_x, grid_tile_y, Color.ORANGE, Flipper.RIGHT);
						m.addGizmo(g);
						break;

					case "Absorber":
						if (x2 == -1 && y2 == -1) {
							x2 = grid_tile_x;
							y2 = grid_tile_y;
							// TODO change ActionTip to remind user of the
							// currently selected Absorber
						} else {
							// Top left corner
							int x1 = Math.min(grid_tile_x, x2);
							int y1 = Math.min(grid_tile_y, y2);

							// Bottom right corner. + 1 because coords start at
							// 0, 0 so width calculation doesn't work otherwise
							x2 = Math.max(grid_tile_x, x2) + 1;
							y2 = Math.max(grid_tile_y, y2) + 1;

							g = new Absorber("a(" + x1 + "," + y1 + ")", x1, y1, x2 - x1, y2 - y1, Color.MAGENTA);
							m.addGizmo(g);

							x2 = -1;
							y2 = -1;
						}

						break;
				}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		gw.setCoords(e.getX(), e.getY());
	}
}