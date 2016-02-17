package Absorber;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Board extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected Model gm;

	public Board(int w, int h, Model m) {
		// Observe changes in Model
		m.addObserver(this);
		width = w;
		height = h;
		gm = m;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setDoubleBuffered(true);
	}

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;
		
		// Set the Board black
		g2D.setColor(Color.BLACK);
		g2D.fillRect(0, 0, Model.L * Model.L, Model.L * Model.L);
		
		// Turn on antialiasing
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		
		/*	Grid removed
		g2D.setColor(Color.PINK);
		for (int i = 0; i < 21; i++) {
			g2D.drawLine(i * 20, 0, i * 20, 400);
			g2D.drawLine(0, i * 20, 400, i * 20);
		}
		*/

		g2D.setColor(Color.BLACK);
		// Draw all the vertical lines
		for (Line vl : gm.getLines()) {
			g2D.fillRect(vl.getX(), vl.getY(), vl.getWidth(), 1);
		}
		
		g2D.setColor(gm.getAbsorbers().get(0).getColour());
		// Draw all the absorbers
		for (Absorber abs : gm.getAbsorbers()) {
			g2D.fillRect(abs.getX(), abs.getY(), abs.getWidth(), abs.getHeight());
		}

		Ball b = gm.getBall();
		if (b != null) {
			g2D.setColor(b.getColour());
			int x = (int) (b.getExactX() - b.getRadius());
			int y = (int) (b.getExactY() - b.getRadius());
			int width = (int) (2 * b.getRadius());
			g2D.fillOval(x, y, width, width);

		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();

	}

}
