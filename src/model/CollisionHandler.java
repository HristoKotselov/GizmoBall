package model;

public class CollisionHandler {

	private MainEngine model;

	public CollisionHandler(MainEngine mainEngine) {
		model = mainEngine;
	}

	// Use CollisionDetails to determine which handle__Coli() method to call
	public void handleCollision(CollisionDetails cd) {
		// TODO
	}

	// Return TRUE if outside absorber, return FALSE if inside absorber
	public boolean handleAbsorberColi(CollisionDetails cd) {
		// TODO Need to improve in order to incoperate MainEngine code

		Ball ball = cd.getBall();
		Absorber absorber = (Absorber) cd.getCollider();

		if (absorber.getCapturedBall() != ball && (ball.getPreciseY() < absorber.getY()
				|| ball.getPreciseY() > absorber.getY() + absorber.getHeight())) { // i.e. first collision BEFORE ball enter Absorber
			ball.stop();
			ball.setPreciseX(absorber.getX() + absorber.getWidth() - (0.25 * MainEngine.L));
			ball.setPreciseY(absorber.getY() + absorber.getHeight() - (0.25 * MainEngine.L));
			absorber.setBall(ball);
			return true;
		} else { // i.e. second collision AFTER ball enter Absorber
			absorber.setBall(null);
			return false;
		}

	}
}
