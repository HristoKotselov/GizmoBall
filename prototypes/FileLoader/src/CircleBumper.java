import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class CircleBumper extends Gizmo {

	public CircleBumper(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public void rotate() {
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public Shape getShape() {
		int[] xpoints = { 0, Const.L, 0 };
		int[] ypoints = { 0, 0, Const.L };

		return new Ellipse2D.Double(0, 0, Const.L, Const.L);
	}
}