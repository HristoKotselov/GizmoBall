package model;

public class PhysicsConfig {

	private double moveTime;

	// Friction
	private double mu1;
	private double mu2;

	// Gravity
	private double gravity;

	/* Default settings */
	public PhysicsConfig() {
		moveTime = 1.0 / 60.0; // 60 FPS

		// Friction
		mu1 = 0.025;
		mu2 = 0.025 / MainEngine.L;

		// Gravity
		gravity = 25.0 * MainEngine.L;
	}

	/* Custom Settings */
	public PhysicsConfig(double mu, double mu2, double g) {
		// TODO some validation
	}

	public boolean setMoveTime(double newMoveTime) {
		// TODO some validation
		moveTime = newMoveTime;
		return true;
	}

	public double getMoveTime() {
		return moveTime;
	}

	public boolean setGravity(double newGravity) {
		// TODO some validation
		gravity = newGravity;
		return true;
	}

	public double getGravity() {
		return gravity;
	}

	public boolean setFrictionCoef1(double mu) {
		// TODO some validation
		this.mu1 = mu;
		return true;
	}

	public double getFrictionCoef1() {
		return mu1;
	}

	public boolean setFrictionCoef2(double mu2) {
		// TODO some validation
		this.mu2 = mu2;
		return true;
	}

	public double getFrictionCoef2() {
		return mu2;
	}
}
