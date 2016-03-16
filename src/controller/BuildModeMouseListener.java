package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.AGizmoComponent;
import model.Absorber;
import model.CircularBumper;
import model.Flipper;
import model.IMainEngine;
import model.SquareBumper;
import model.TriangularBumper;
import view.BuildMenu;

public class BuildModeMouseListener implements MouseListener {
	private BuildMenu bm;
	private IMainEngine m;

	private int x, y;
	private int x2 = -1, y2 = -1;

	private AGizmoComponent moveG;

	public BuildModeMouseListener(IMainEngine m, BuildMenu bm) {
		this.m = m;
		this.bm = bm;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX() / 20;
		y = e.getY() / 20;

		String selectedFunction = bm.getSelectedFunction();

		AGizmoComponent g = null;

		switch (selectedFunction) {
			case "Remove Gizmo":
				g = m.getGizmoAt(x, y);

				if (g != null) {
					m.removeGizmo(g);
				}

				break;

			case "Rotate Gizmo":
				g = m.getGizmoAt(x, y);

				if (g != null) {
					m.rotateGizmo(g, 90);
				}

				break;

			case "Move Gizmo":
				if (moveG == null) {
					moveG = m.getGizmoAt(x, y);
				} else {
					m.moveGizmo(moveG, x, y);
					moveG = null;
				}

				break;

			case "Add Gizmo":
				String selectedGizmo = bm.getSelectedGizmo();

				switch (selectedGizmo) {
					case "Square":
						g = new SquareBumper("s(" + x + "," + y + ")", x, y, Color.GREEN);
						m.addGizmo(g);
						break;

					case "Triangle":
						g = new TriangularBumper("t(" + x + "," + y + ")", x, y, Color.RED);
						m.addGizmo(g);
						break;

					case "Circle":
						g = new CircularBumper("c(" + x + "," + y + ")", x, y, Color.BLUE);
						m.addGizmo(g);
						break;

					case "Left Flipper":
						g = new Flipper("lf(" + x + "," + y + ")", x, y, Color.ORANGE, true);
						m.addGizmo(g);
						break;

					case "Right Flipper":
						g = new Flipper("rf(" + x + "," + y + ")", x, y, Color.ORANGE, false);
						m.addGizmo(g);
						break;

					case "Absorber":
						if (x2 == -1 && y2 == -1) {
							x2 = x;
							y2 = y;

						} else {
							// Top left corner
							int x1 = Math.min(x, x2);
							int y1 = Math.min(y, y2);

							// Bottom right corner. + 1 because coords start at
							// 0, 0 so width calculation doesn't work otherwise
							x2 = Math.max(x, x2) + 1;
							y2 = Math.max(y, y2) + 1;

							g = new Absorber("a(" + x1 + "," + y1 + ")", x1, y1, x2 - x1, y2 - y1, Color.MAGENTA);
							m.addGizmo(g);

							x2 = -1;
							y2 = -1;
						}

						break;

					default:
						System.out.println(
								"Not recognised or not Implemented, make sure the gizmo is in the BuildModeMouseListener.mouseClicked() case statement.");
						return;
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
}