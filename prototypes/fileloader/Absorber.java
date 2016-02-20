package fileloader;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class Absorber extends Gizmo {
	private int width;
	private int height;

	public Absorber(String name, int x, int y, int w, int h) {
		super(name, x, y);
		width = w;
		height = h;
	}

	@Override
	public Color getColor() {
		return Color.MAGENTA;
	}

	@Override
	public Shape getShape() {
		return new Rectangle(Const.L * width, Const.L * height);
	}
}