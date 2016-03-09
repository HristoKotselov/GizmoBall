package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.AGizmoComponent;
import model.IMainEngine;
import model.SquareBumper;
import view.BuildBoard;

public class BuildModeMouseListener implements MouseListener {
	private BuildBoard b;
	private IMainEngine m;


	public BuildModeMouseListener(BuildBoard b, IMainEngine m) {
		this.b = b;
		this.m = m;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX() / 20 + ", " + e.getY() / 20);

		int x = e.getX() / 20;
		int y = e.getY() / 20;

		AGizmoComponent g = new SquareBumper("s"+x+y, x, y, Color.GREEN);
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