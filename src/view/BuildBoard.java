package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.AGizmoComponent;
import model.IMainEngine;
import physics.Circle;
import model.Ball;

public class BuildBoard extends JPanel implements Observer {
	private IMainEngine model;

	public BuildBoard(IMainEngine m) {
		// TODO change to (400, 400)
		setPreferredSize(new Dimension(400, 400));
		setBackground(Color.BLACK);

		model = m;
		model.addObserver(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		Map<String, AGizmoComponent> gizmos = model.getGizmosMap();

		// Turn on antialiasing
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Save the default transformation
		AffineTransform old = g2d.getTransform();

		// Iterate over all gizmos
		for (AGizmoComponent giz : gizmos.values()) {
			// Get the colour and shape of the gizmo
			// System.out.println(giz.getGizmoID());
			g2d.setColor(giz.getColour());
			Shape s = giz.getDrawingShape();

			// Translate the gizmo to the appropriate position on the board
			g2d.translate(giz.getX(), giz.getY());
			// System.out.println(giz.getX() + ":" + giz.getY());
			// Rotate gizmo around its center. Currently only works with 1x1
			// gizmos because of hardcoded 10, but can be easily changed
			// g2d.rotate(Math.toRadians(giz.getRotation()), giz.getWidth() / 2,
			// giz.getHeight() / 2);

			// Draw the shape
			g2d.fill(s);

			// Reset transformation before drawing the next object
			g2d.setTransform(old);

		}

/** TODO START of Temporarily Block of code, REMOVE\CHANGE before final release **/
		Ball ball = model.getBall();
		g2d.setColor(ball.getColour());
		g2d.translate(ball.getPreciseX(), ball.getPreciseY());
		// g2d.fill(ball.getDrawingShape());
		//Shape s = new Circle(ball.getPreciseX(), ball.getPreciseY(), ball.getRadius());
	//	Shape s = (Shape) new javafx.scene.shape.Circle(ball.getPreciseX(), ball.getPreciseY(), ball.getRadius());
	//g2d.fill(s);	
		g2d.fillRect(-(int) ball.getRadius(),-(int) ball.getRadius(),2*(int) ball.getRadius(),2*(int) ball.getRadius());
		
		
		g2d.setTransform(old);
/** TODO END of Temporarily Block of code **/
		
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