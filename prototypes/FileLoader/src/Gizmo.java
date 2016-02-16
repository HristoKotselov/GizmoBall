package FileLoader.src;

import java.awt.Color;
import java.awt.Shape;

public abstract class Gizmo {
	protected String name;
	public int x;
	public int y;
	private int angle;

	public Gizmo(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public abstract Color getColor();

	public abstract Shape getShape();

	public void rotate(int a) {
		angle = (angle + a) % 360;
	}

	public int getRotation() {
		return angle;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return Const.L;
	}

	public int getHeight() {
		return Const.L;
	}
}