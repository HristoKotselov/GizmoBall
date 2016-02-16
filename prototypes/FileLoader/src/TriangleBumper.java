import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;

public class TriangleBumper extends Gizmo {
	public TriangleBumper(String name, int x, int y) {
		super(name, x, y);
	}

	public void rotate(int a) {
		super.rotate(a);

		System.out.println("Rotating triangle bumper: \"" + name + "\"");
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

	@Override
	public Shape getShape() {
		int[] xpoints = { 0, Const.L, 0 };
		int[] ypoints = { 0, 0, Const.L };

		return new Polygon(xpoints, ypoints, xpoints.length);
	}
}