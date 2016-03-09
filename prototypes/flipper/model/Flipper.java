package flipper.model;

public class Flipper {
	private int x, y;
	private double currentAngle;
	private boolean currentlyFlipping, leftFlipper;

	public Flipper(int x, int y, boolean leftFlipper) {
		super();
		this.x = x;
		this.y = y;
		this.leftFlipper = leftFlipper;
		this.currentAngle = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		System.out.println(x);
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean getOrientation() {
		return leftFlipper;
	}

	public void setOrientation(boolean orientation) {
		this.leftFlipper = orientation;
	}

	public double getCurrentAngle() {
		return currentAngle;
	}

	public void setCurrentAngle(double currentAngle) {
		this.currentAngle = currentAngle;
	}

	public boolean isCurrentlyFlipping() {
		return currentlyFlipping;
	}

	public void setCurrentlyFlipping(boolean currentlyFlipping) {
		this.currentlyFlipping = currentlyFlipping;
	}

}
