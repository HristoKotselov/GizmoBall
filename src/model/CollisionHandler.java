package model;

import java.awt.geom.Point2D;
import java.util.Set;
import model.gizmos.Absorber;
import model.gizmos.Ball;

public class CollisionHandler {

	private MainEngine model;

	public CollisionHandler(MainEngine mainEngine) {
		model = mainEngine;
	}

	// Use CollisionDetails to determine whether a handle_Coli() method need to be called
	public void handleCollision(CollisionDetails cd, AGizmoComponent gizmo, Connections con) {

		double tuc = cd.getTuc();
		Ball ball = cd.getBall();


		// gizmo == null means its the wall
		if (gizmo != null) {

			switch (gizmo.getClass().getSimpleName()) {
				case "Absorber":
					/* If ball is outside an absorber while moving (i.e. as it touches the edge of the Absorber), then the ball is captured.
					 * If ball is inside an absorber while moving (i.e. shooting straight up for launching), then the ball is moved outside; ignoring that top Line Segment
					 * "tuc == 0" prolong execution of the handleAbsorberColi() procedure until next cycle by exploiting the fact that in a && expression; if the 1st condition isn't true, the 2nd condition is not evaluated	*/
					if (tuc == 0 && !handleAbsorberColi(cd, gizmo)) { // tuc == 0 occur when the Ball is directly in contact the Line
																		// Segment
						model.moveBallAtCurrentVelo(ball, model.getMoveTime());
					}

					break;

				// TODO add Flipper stuff here

				default: // procedures for a normal collision (that is not wall collision)
					ball.setVelo(cd.getVelo()); // Post collision velocity ...
					break;
			}

			gizmo.collided();
			Set<AGizmoComponent> s = con.getGizmoTriggerConnections(gizmo);

			if (s != null) {
				for (AGizmoComponent g : s) {
					g.triggerAction();
				}
			}

		} else {
			// procedures for a wall collision
			ball.setVelo(cd.getVelo()); // Post collision velocity ...
		}

	}

	// TODO could possibly be moved to Absorber class in the collided() method?
	// Return TRUE if outside absorber, return FALSE if inside absorber
	private boolean handleAbsorberColi(CollisionDetails cd, AGizmoComponent gizmo) {

		Ball ball = cd.getBall();
		Absorber absorber = (Absorber) gizmo;
		int width_in_pixels = absorber.bmWidth * MainEngine.L;
		int height_in_pixels = absorber.bmHeight * MainEngine.L;

		// i.e. first collision BEFORE ball enter Absorber
		Point2D ballCentre = new Point2D.Double(ball.getMovingX(), ball.getMovingY());

		if (!absorber.getCapturedBalls().contains(ball) &&
				!absorber.getDrawingShape().contains(ballCentre) // check if Ball is NOT inside Absorber
		) {
			ball.stop();
			ball.setMovingX(absorber.getX() + width_in_pixels - (0.25 * MainEngine.L));
			ball.setMovingY(absorber.getY() + height_in_pixels - (0.25 * MainEngine.L));
			absorber.addCapturedBall(ball);
			return true;
		} else { // i.e. second collision AFTER ball enter Absorber
			return false; // nothing happens
		}
	}
}
