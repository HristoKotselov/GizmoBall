package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Collection;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.AGizmoComponent;
import model.AMovingGizmo;
import model.AStationaryGizmo;
import model.IMainEngine;

public class GameBoard extends JPanel implements IBoard, Observer {
	private IMainEngine model;

	public GameBoard(IMainEngine m) {
		setPreferredSize(new Dimension(400, 400));
		setBackground(Color.BLACK);

		model = m;
		model.setWallDimensions(getPreferredSize().width, getPreferredSize().height);
		model.addObserver(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// Turn on antialiasing
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Save the default transformation
		AffineTransform old = g2d.getTransform();


		// Iterate over all stationary gizmos
		Collection<AStationaryGizmo> sGizmos = model.getAllStationaryGizmos();

		for (AStationaryGizmo giz : sGizmos) {
			// Get the colour and shape of the gizmo
			// System.out.println(giz.getGizmoID());
			g2d.setColor(giz.getColour());
			Shape s = giz.getDrawingShape();

			// Draw the shape
			g2d.fill(s);

			// Reset transformation before drawing the next object
			g2d.setTransform(old);
		}


		// Iterate over all moving gizmos
		Collection<AMovingGizmo> mGizmos = model.getAllMovingGizmos();

		for (AMovingGizmo giz : mGizmos) {
			// Get the colour and shape of the gizmo
			// System.out.println(giz.getGizmoID());
			g2d.setColor(giz.getColour());
			Shape s = giz.getDrawingShape();

			// Draw the shape
			g2d.fill(s);

			// Reset transformation before drawing the next object
			g2d.setTransform(old);
		}


		// Draw grid
		g2d.setColor(Color.WHITE);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		for (int i = 0; i <= 20; i++) {
			g2d.drawLine(0, i * 20, 400, i * 20);
			g2d.drawLine(i * 20, 0, i * 20, 400);
		}
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}