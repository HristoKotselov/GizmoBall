package flipper.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.RoundRectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import flipper.model.Flipper;
import flipper.model.Model;
import flipper.model.VerticalLine;

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
	}

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		// Draw all the vertical lines
		for (VerticalLine vl : gm.getLines()) {
			g2.fillRect(vl.getX(), vl.getY(), vl.getWidth(), 1);
		}

		for (Flipper f : gm.getFlippers()) {
			g2.setColor(Color.BLACK);
			//Rectangle r = new Rectangle(f.getX(), f.getY(), 25, 50);
			RoundRectangle2D.Double r = new RoundRectangle2D.Double(f.getX(),f.getY(), 20, 80, 20, 20);
					
			if (f.isCurrentlyFlipping()) {
				g2.setColor(Color.orange);
				f.setCurrentAngle(f.getCurrentAngle() + 20);
				if (f.getCurrentAngle() > 90) {
					f.setCurrentAngle(90);
				}
			} else {
				f.setCurrentAngle(f.getCurrentAngle() - 20);
				if (f.getCurrentAngle() < 0) {
					f.setCurrentAngle(0);
				}
			}
			AffineTransform transform = new AffineTransform();
			
			transform.rotate(Math.toRadians(f.getCurrentAngle()), r.getX() +10,
					r.getY() + 10 );
			if(f.getOrientation()){
				try {
					transform.invert();
				} catch (NoninvertibleTransformException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Shape transformedR = transform.createTransformedShape(r);
			g2.fill(transformedR);
			g2.setColor(Color.BLACK);

			
	
		}

		/*Ball b = gm.getBall();
		
		if (b != null) {
			g2.setColor(b.getColour());
			int x = (int) (b.getExactX() - b.getRadius());
			int y = (int) (b.getExactY() - b.getRadius());
			int width = (int) (2 * b.getRadius());
			g2.fillOval(x, y, width, width);
		}*/
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}
