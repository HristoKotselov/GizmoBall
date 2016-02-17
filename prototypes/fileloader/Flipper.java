package fileloader;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

public class Flipper extends Gizmo {
	public static final int LEFT = 0;
	public static final int RIGHT = 1;

	private int orientation;

	public Flipper(String name, int x, int y, int o) {
		super(name, x, y);
		orientation = o;
	}

	@Override
	public Color getColor() {
		return Color.YELLOW;
	}

	@Override
	public Shape getShape() {
		if (orientation == LEFT) {
			return new RoundRectangle2D.Double(0, 0, Const.L * 0.5, Const.L * 2, Const.L, 10);
		} else {
			return new RoundRectangle2D.Double(Const.L * 1.5, 0, Const.L * 0.5, Const.L * 2, Const.L, 10);
		}
	}

	@Override
	public int getWidth() {
		return 2 * Const.L;
	}

	@Override
	public int getHeight() {
		return 2 * Const.L;
	}
}