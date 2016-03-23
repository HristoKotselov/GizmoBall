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
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.AGizmoComponent;
import model.AMovingGizmo;
import model.AStationaryGizmo;
import model.IMainEngine;
import model.gizmos.Absorber;
import model.gizmos.Ball;
import model.gizmos.CircularBumper;
import model.gizmos.Flipper;
import model.gizmos.SquareBumper;
import model.gizmos.TriangularBumper;
import physics.Angle;

public class GameBoard extends JPanel implements IBoard, Observer {
	private IMainEngine model;
	private GameWindow gameWindow;
	private BuildMenu buildMenu;
	private PlayMenu playMenu;

	private int x1, y1;
	private int x2 = -1, y2 = -1;

	public GameBoard(IMainEngine m, BuildMenu bm, PlayMenu pm, GameWindow gw) {
		setPreferredSize(new Dimension(400, 400));
		setBackground(Color.BLACK);
		setFocusable(true);

		model = m;
		gameWindow = gw;
		buildMenu = bm;
		playMenu = pm;
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

		if (gameWindow.isBuildMode() || playMenu.isDynamicEditEnabled()) {
			// Draw shadow of gizmo being placed, if appropriate
			if (buildMenu.getSelectedFunction().equals("Add Gizmo") || buildMenu.getSelectedFunction().equals("Add Ball")) {
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
				AGizmoComponent giz;

				if (buildMenu.getSelectedFunction().equals("Add Gizmo")) {
					giz = getShape(buildMenu.getSelectedGizmo());
				} else {
					giz = new Ball("temp", Color.BLUE, x1, y1, Angle.ZERO, 0);
				}

				g2d.setColor(giz.getColour());
				g2d.fill(giz.getDrawingShape());

				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
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
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

	@Override
	public void setP1(int x, int y) {
		this.x1 = x;
		this.y1 = y;

		repaint();

		gameWindow.updateCoordsLabel(x, y);
	}

	@Override
	public void setP2(int x, int y) {
		this.x2 = x;
		this.y2 = y;

		repaint();

		gameWindow.updateCoordsLabel(x, y);
	}

	public AGizmoComponent getShape(String s) {
		// TODO make this code pretty
		// TODO make all previews white, but change to red if placement will
		// override another component?
		AGizmoComponent giz = null;
		int x = x1 / model.getLInPixels();
		int y = y1 / model.getLInPixels();

		switch (s) {
			case "Square":
				giz = new SquareBumper("temp", x, y, Color.RED);
				break;

			case "Circle":
				giz = new CircularBumper("temp", x, y, Color.GREEN);
				break;

			case "Triangle":
				giz = new TriangularBumper("temp", x, y, Color.BLUE);
				break;

			case "Left Flipper":
				giz = new Flipper("temp", x, y, Color.ORANGE, Flipper.LEFT);
				break;

			case "Right Flipper":
				giz = new Flipper("temp", x, y, Color.ORANGE, Flipper.RIGHT);
				break;

			case "Absorber":
				int x1, y1, x2, y2;

				if (this.x2 != -1) {
					x1 = Math.min(x, this.x2 / model.getLInPixels());
					y1 = Math.min(y, this.y2 / model.getLInPixels());

					x2 = Math.max(x, this.x2 / model.getLInPixels()) + 1;
					y2 = Math.max(y, this.y2 / model.getLInPixels()) + 1;
				} else {
					x1 = x;
					y1 = y;
					x2 = x + 1;
					y2 = y + 1;
				}

				giz = new Absorber("temp", x1, y1, x2 - x1, y2 - y1, Color.MAGENTA);
				break;
		}

		return giz;
	}
}