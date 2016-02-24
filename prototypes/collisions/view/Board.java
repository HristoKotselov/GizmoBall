package collisions.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import collisions.model.Ball;
import collisions.model.CircleBumper;
import collisions.model.Model;
import collisions.model.SquareBumper;
import collisions.model.TriangleBumper;
import collisions.model.HorizontalLine;

/**
 * part of the code is from Murray's Demonstration of MVC and MIT Physics Collisions 2014
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

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.PINK);
		for (int i = 0; i < 21; i++) {
			g2.drawLine(i * 20, 0, i * 20, 400);
			g2.drawLine(0, i * 20, 400, i * 20);
		}

		g2.setColor(Color.BLACK);
		// Draw all the vertical lines
		for (HorizontalLine vl : gm.getLines()) {
			g2.fillRect(vl.getX(), vl.getY(), vl.getWidth(), 1);
		}
		for (CircleBumper circle : gm.getCircleBumpers()) {
			int x = (int) (circle.getX() - circle.getRadius());
			int y = (int) (circle.getY() - circle.getRadius());
			int width = (int) (2 * circle.getRadius() + 1);
			g2.fillOval(x, y, width, width);
		}
		
		for (SquareBumper square : gm.getSquareBumpers()) {
			g2.fillRect(square.getX(), square.getY(), square.getSize() + 1, square.getSize() + 1);
		}
		
		for (TriangleBumper triangle : gm.getTriangleBumpers()) {
			g2.fillRect(triangle.getX(), triangle.getY(), triangle.getSize(), 1);
			g2.fillRect(triangle.getX(), triangle.getY(), 1, triangle.getSize());
			g2.drawLine(triangle.getX(), triangle.getY2(), triangle.getX2(), triangle.getY());
		}
		

		Ball b = gm.getBall();
		if (b != null) {
			g2.setColor(b.getColour());
			int x = (int) (b.getExactX() - b.getRadius());
			int y = (int) (b.getExactY() - b.getRadius());
			int width = (int) (2 * b.getRadius());
			g2.fillOval(x, y, width, width);

		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();

	}

}
