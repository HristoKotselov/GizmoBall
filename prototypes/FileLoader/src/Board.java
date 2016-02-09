import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

public class Board extends JPanel {
//	private static final int L = 20;

	Map<String, Gizmo> gizmos;

	public Board() {
		gizmos = new HashMap<String, Gizmo>();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// Turn on antialiasing because it looks better
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Save the default transformation
		AffineTransform old = g2d.getTransform();

		// Iterate over all gizmos
		for (Gizmo giz : gizmos.values()) {

			// Get the colour and shape of the gizmo
			g2d.setColor(giz.getColor());
			Shape s = giz.getShape();

			// Translate the gizmo to the appropriate position on the board
			g2d.translate(giz.getX() * Const.L, giz.getY() * Const.L);

			// Rotate gizmo around its center. Currently only works with 1x1
			// gizmos because of hardcoded 10, but can be easily changed
			g2d.rotate(Math.toRadians(giz.getRotation()), 10, 10);

			// Draw the shape
			g2d.fill(s);

			// Reset transformation before drawing the next object
			g2d.setTransform(old);
		}

		// Draw grid
		g2d.setColor(Color.BLACK);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		for (int i = 0; i <= Const.L; i++) {
			g2d.drawLine(0, i * Const.L, Const.L * Const.L, i * Const.L);
			g2d.drawLine(i * Const.L, 0, i * Const.L, Const.L * Const.L);
		}
	}

	public void addGizmo(String name, Gizmo g) {
		gizmos.put(name, g);
	}

	public Gizmo getGizmo(String name) {
		return gizmos.get(name);
	}
}