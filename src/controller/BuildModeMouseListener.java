package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import model.AGizmoComponent;
import model.CircularBumper;
import model.Flipper;
import model.IMainEngine;
import model.SquareBumper;
import model.TriangularBumper;
import view.BuildBoard;
import view.BuildMenu;

public class BuildModeMouseListener implements MouseListener {
	private BuildBoard b;
	private BuildMenu bm;
	private IMainEngine m;

	public BuildModeMouseListener(BuildBoard b, IMainEngine m, BuildMenu bm) {
		this.b = b;
		this.m = m;
		this.bm = bm;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/**
		 * On commit 44fcf5421045dec4c6cb0d70e4dbaf40a38bc192 [44fcf54] ; 09
		 * March 2016 16:43:16 Sam wrote:
		 * " if you clicked, moved the mouse, then released, nothing happened. "
		 * 
		 * But isn't that a good thing though? This means user can change their
		 * mind at the last second
		 * 
		 **/

		// System.out.println(e.getX() / 20 + ", " + e.getY() / 20);
		/*
				int x = e.getX() / 20;
				int y = e.getY() / 20;
				String selectedType = "";
				for (Enumeration<AbstractButton> buttons = bm.getGizmoSelector().getElements(); buttons.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();
		
					if (button.isSelected()) {
						selectedType = button.getText();
					}
				}
				AGizmoComponent g = null;
		
				switch (selectedType) {
				case "square":
					g = new SquareBumper("s" + x + y, x, y, Color.GREEN);
		
					break;
				case "triangle":
					g = new TriangularBumper("t" + x + y, x, y, Color.red);
		
					break;
				case "circle":
					g = new CircularBumper("c" + x + y, x, y, Color.blue);
		
					break;
				case "left flipper":
					g = new Flipper("f" + x + y, x, y, Color.ORANGE, true);
		
					break;
				case "right flipper":
					g = new Flipper("f" + x + y, x, y, Color.ORANGE, false);
		
					break;
				default:
					System.out.println(
							"Not recognised or not Implimented, make sure the gizmo is in the BuildModeMouseListener.mouseClicked() case statement.");
					return;
				}
		
				m.addGizmo(g);*/
	}

	@Override
	public void mousePressed(MouseEvent e) {

		// Please look at mouseClicked() for a suggestion for moving this block
		// of code back to mouseClick()
		/**
		 * TODO Due to Hristo's GUI changes, this block of code is now broken
		 * and need changing
		 **/

		int x = e.getX() / 20;
		int y = e.getY() / 20;

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

			case "Add Gizmo":
				String selectedGizmo = bm.getSelectedGizmo();
				
				switch (selectedGizmo) {
					case "Square":
						g = new SquareBumper("s" + x + "," + y, x, y, Color.GREEN);
						m.addGizmo(g);
						break;

					case "Triangle":
						g = new TriangularBumper("t" + x + "," + y, x, y, Color.RED);
						m.addGizmo(g);
						break;

					case "Circle":
						g = new CircularBumper("c" + x + "," + y, x, y, Color.BLUE);
						m.addGizmo(g);
						break;

					case "Left Flipper":
						g = new Flipper("f" + x + "," + y, x, y, Color.ORANGE, true);
						m.addGizmo(g);
						break;

					case "Right Flipper":
						g = new Flipper("f" + x + "," + y, x, y, Color.ORANGE, false);
						m.addGizmo(g);
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