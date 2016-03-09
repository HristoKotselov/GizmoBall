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
		//System.out.println(e.getX() / 20 + ", " + e.getY() / 20);

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

		m.addGizmo(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {

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