package controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import model.AGizmoComponent;
import model.AMovingGizmo;
import model.AStationaryGizmo;
import model.ActionTipDialogue;
import model.IMainEngine;
import model.gizmos.Absorber;
import model.gizmos.Ball;
import model.gizmos.CircularBumper;
import model.gizmos.Flipper;
import model.gizmos.SquareBumper;
import model.gizmos.TriangularBumper;
import physics.Angle;
import view.IBoard;
import view.IBuildMenu;
import view.IGameWindow;
import view.IPlayMenu;

public class BuildModeMouseListener implements MouseInputListener {
	private IBoard board;
	private IBuildMenu bm;
	/** Need to change ActionTips that involves clicking on the Board **/
	private IGameWindow gameWindow;
	private IPlayMenu pm;
	private IMainEngine m;

	private int x, y;
	/**
	 * Memory values used when adding a Gizmo can have variable width\height (e.g. Absorber)
	 * 
	 * -1 is the default value that indicates the 1st click is required to start the size-selection procedures.
	 **/
	private int x2 = -1, y2 = -1;

	/**
	 * Memory value used when moving a Gizmo to a different position.
	 * 
	 * null is the default value that indicates the 1st click is required to start the moving procedure.
	 */
	private AGizmoComponent moveG;

	/**
	 * Memory value used when connecting two Gizmos.
	 * 
	 * null is the default value that indicates the 1st click is required to start the connection procedure.
	 */
	private AGizmoComponent connG;


	public BuildModeMouseListener(IBoard board, IMainEngine m, IBuildMenu bm, IPlayMenu pm, IGameWindow gw) {
		this.board = board;
		this.m = m;
		this.bm = bm;
		this.pm = pm;
		this.gameWindow = gw;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (gameWindow.isBuildMode() || pm.isDynamicEditEnabled()) {
			x = e.getX();
			y = e.getY();
			int L = IMainEngine.L;
			int grid_tile_x = x / L;
			int grid_tile_y = y / L;

			String selectedFunction = bm.getSelectedFunction();

			AGizmoComponent g = null;

			switch (selectedFunction) {
				case "Remove Gizmo":
<<<<<<< HEAD
<<<<<<< HEAD
					// TODO add method for to check for AMovingGizmo first, i.e. Ball
=======
>>>>>>> 84931fe3ceaa7b9f66afcccdfc818bd402232cbf
=======
>>>>>>> 24d1d8980855bc42d310646b4924367d62afb755
					g = m.getStationaryGizmoAt(grid_tile_x, grid_tile_y);

					if (g != null) {
						m.removeGizmo(g);
					}

					break;

				case "Rotate Gizmo":
<<<<<<< HEAD
<<<<<<< HEAD
					// TODO add method for to check for AMovingGizmo first, i.e. Ball
=======
>>>>>>> 84931fe3ceaa7b9f66afcccdfc818bd402232cbf
=======
>>>>>>> 24d1d8980855bc42d310646b4924367d62afb755
					g = m.getStationaryGizmoAt(grid_tile_x, grid_tile_y);

					if (g != null) {
						m.rotateGizmo(g, 90);
					}

					break;

				case "Move Gizmo":
					if (moveG == null) {
						moveG = m.getMovingGizmoAt(x, y);
						if (moveG == null) { // i.e. no moving Gizmo found
							moveG = m.getStationaryGizmoAt(grid_tile_x, grid_tile_y);
						}
<<<<<<< HEAD
<<<<<<< HEAD
						// TODO change ActionTip to remind user of the currently selected Gizmo
=======
>>>>>>> 84931fe3ceaa7b9f66afcccdfc818bd402232cbf
=======
>>>>>>> 24d1d8980855bc42d310646b4924367d62afb755
					} else {
						if (moveG instanceof AStationaryGizmo) {
							m.moveGizmoByL(moveG, grid_tile_x, grid_tile_y);
						}
						if (moveG instanceof AMovingGizmo) {
							m.moveGizmoByPixels((AMovingGizmo) moveG, x, y);
						}
						moveG = null;
					}

					break;

				case "Edit Ball Settings":
					try {
						double angle = bm.getBallDirectionFromGUI();
						double speed = bm.getBallSpeedFromGUI() * IMainEngine.L;

						g = new Ball("Ball(" + x + "," + y + ")", Color.BLUE, x, y, new Angle(Math.toRadians(angle)), speed);

						m.addGizmo(g);

						// Op successful!
						gameWindow.setActionTipsTextArea(ActionTipDialogue.addBallActionTip());
					} catch (NumberFormatException ex) {
						gameWindow.setActionTipsTextArea(ActionTipDialogue.addBallSettingsError());
						gameWindow.setActionTipsTextAreaColour(Color.RED);
					}

					break;

<<<<<<< HEAD
<<<<<<< HEAD
				// TODO Add way to remove individual connection
=======
>>>>>>> 84931fe3ceaa7b9f66afcccdfc818bd402232cbf
=======
>>>>>>> 24d1d8980855bc42d310646b4924367d62afb755
				case "Connect Gizmos":
					if (bm.getConnectFunction().equals("addconn")) {
						if (connG == null) {
							connG = m.getStationaryGizmoAt(grid_tile_x, grid_tile_y);
<<<<<<< HEAD
<<<<<<< HEAD
							// TODO change ActionTip to remind user of the currently selected Gizmo
=======
>>>>>>> 84931fe3ceaa7b9f66afcccdfc818bd402232cbf
=======
>>>>>>> 24d1d8980855bc42d310646b4924367d62afb755
						} else {
							g = m.getStationaryGizmoAt(grid_tile_x, grid_tile_y);

							if (g != null) {
								m.addConnection(connG, g);
								connG = null;
							}
						}
					} else if (bm.getConnectFunction().equals("remconn")) {
						if (connG == null) {
							connG = m.getStationaryGizmoAt(grid_tile_x, grid_tile_y);
<<<<<<< HEAD
<<<<<<< HEAD
							// TODO change ActionTip to remind user of the currently selected Gizmo
=======
>>>>>>> 84931fe3ceaa7b9f66afcccdfc818bd402232cbf
=======
>>>>>>> 24d1d8980855bc42d310646b4924367d62afb755
						} else {
							g = m.getStationaryGizmoAt(grid_tile_x, grid_tile_y);

							if (g != null) {
								m.removeConnection(connG, g);
								connG = null;
							}
						}
						
					} else {
						connG = null;

						g = m.getStationaryGizmoAt(grid_tile_x, grid_tile_y);

						if (g != null) {
							m.addConnection(g, null);
						}
					}

					break;

<<<<<<< HEAD
<<<<<<< HEAD
				// TODO Add way to remove individual binding
=======
>>>>>>> 84931fe3ceaa7b9f66afcccdfc818bd402232cbf
=======
>>>>>>> 24d1d8980855bc42d310646b4924367d62afb755
				case "Bind Key":
					g = m.getStationaryGizmoAt(grid_tile_x, grid_tile_y);

					if (g != null) {
						if (bm.getKeyEventType().equals("keypress")) {
							new KeyBindPopupListener(m, g, KeyEvent.KEY_PRESSED);

						} else if (bm.getKeyEventType().equals("keyrelease")) {
							new KeyBindPopupListener(m, g, KeyEvent.KEY_RELEASED);

						} else if (bm.getKeyEventType().equals("keyremove")) {
							new KeyBindPopupListener(m, g, -1);

						} else {
							m.bindKey(g, -1, -1);
						}
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
<<<<<<< HEAD
<<<<<<< HEAD
								// TODO change ActionTip to remind user of the currently selected Absorber
=======
>>>>>>> 84931fe3ceaa7b9f66afcccdfc818bd402232cbf
=======
>>>>>>> 24d1d8980855bc42d310646b4924367d62afb755
							} else {
								// Top left corner
								int x1 = Math.min(grid_tile_x, x2);
								int y1 = Math.min(grid_tile_y, y2);

								// Bottom right corner. + 1 because coords start at 0, 0 so width calculation doesn't work otherwise
								x2 = Math.max(grid_tile_x, x2) + 1;
								y2 = Math.max(grid_tile_y, y2) + 1;

								g = new Absorber("a(" + x1 + "," + y1 + ")", x1, y1, x2 - x1, y2 - y1, Color.MAGENTA);
								m.addGizmo(g);

								x2 = -1;
								y2 = -1;
								board.setP2(-1, -1);
							}

							break;
					}
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		board.requestFocusInWindow();
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (x2 == -1) {
			board.setP1(e.getX(), e.getY());
		} else {
			board.setP2(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (x2 == -1) {
			board.setP1(e.getX(), e.getY());
		} else {
			board.setP2(e.getX(), e.getY());
		}
	}
}