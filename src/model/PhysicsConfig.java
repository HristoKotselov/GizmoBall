package model;

import java.util.Observable;

public class PhysicsConfig extends Observable implements IPhysicsConfig {
	public static final double DEFAULT_GRAVITY = 25.0 * MainEngine.L;
	public static double DEFAULT_MU1 = 0.025;
	public static double DEFAULT_MU2 = 0.025 / MainEngine.L;

	// Friction
	private double mu1;
	private double mu2;

	// Gravity
	private double gravity;

	/* Custom Settings */
	public PhysicsConfig() {
		gravity = DEFAULT_GRAVITY;
		mu1 = DEFAULT_MU1;
		mu2 = DEFAULT_MU2;
	}

	public PhysicsConfig(double gravity, double mu1, double mu2) {
		this.gravity = gravity;
		this.mu1 = mu1;
		this.mu2 = mu2;
	}

	public boolean setGravity(double newGravity) {
		gravity = newGravity;

		update();
		return true;
	}

	public double getGravity() {
		return gravity;
	}

	public boolean setFrictionCoef1(double mu) {
		this.mu1 = mu;

		update();
		return true;
	}

	public double getFrictionCoef1() {
		return mu1;
	}

	public boolean setFrictionCoef2(double mu2) {
		this.mu2 = mu2;

		update();
		return true;
	}

	public double getFrictionCoef2() {
		return mu2;
	}

	private void update() {
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		return "Gravity " + gravity / MainEngine.L + "\n"
				+ "Friction " + mu1 + " " + mu2 * MainEngine.L;
	}
}
