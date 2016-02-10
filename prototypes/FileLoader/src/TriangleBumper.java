import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;

public class TriangleBumper extends Gizmo {
	int angle = 0;

	public TriangleBumper(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public void rotate() {
		System.out.println("Rotating triangle bumper: \"" + name + "\"");
		angle = (angle + 90) % 360;
	}

	public int getRotation() {
		return angle;
	}

	@Override
	public Color getColor() {
		return Color.CYAN;
	}

	@Override
	public Shape getShape() {
		int[] xpoints = { 0, Const.L, 0 };
		int[] ypoints = { 0, 0, Const.L };

		return new Polygon(xpoints, ypoints, xpoints.length);
	}
}