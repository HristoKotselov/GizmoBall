package fileloader;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class CircleBumper extends Gizmo {
	public CircleBumper(String name, int x, int y) {
		super(name, x, y);
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(0, 0, Const.L, Const.L);
	}
}