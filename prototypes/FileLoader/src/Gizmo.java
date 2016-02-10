import java.awt.Color;
import java.awt.Shape;

public abstract class Gizmo {
	protected String name;
	public int x;
	public int y;

	public abstract void rotate();

	public abstract Color getColor();
	
	public abstract Shape getShape();
	
	public int getRotation(){
		return 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}