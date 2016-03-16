package model;

import physics.Angle;
import physics.Vect;

public class CollisionHandler {

	private MainEngine model;

	public CollisionHandler(MainEngine mainEngine) {
		model = mainEngine;
	}

	// Use CollisionDetails to determine whether a handle_Coli() method need to be called
	public void handleCollision(CollisionDetails cd, AGizmoComponent gizmo) {
		
		double tuc = cd.getTuc();
		Ball ball = cd.getBall();
		
		// gizmo == null means its the wall
		if(gizmo != null){
		
			switch(gizmo.getClass().getSimpleName()){
				case "Absorber": 
					/* If ball is outside an absorber while moving (i.e. as it touches the edge of the Absorber), then the ball is captured.
					 * If ball is inside an absorber while moving (i.e. shooting straight up for launching), then the ball is moved outside; ignoring that top Line Segment
					 * "tuc == 0" prolong execution of the handleAbsorberColi() procedure until next cycle by exploiting the fact that in a && expression; if the 1st condition isn't true, the 2nd condition is not evaluated	*/	
					if(tuc == 0 && !handleAbsorberColi(cd, gizmo)){		// tuc == 0 occur when the Ball is directly in contact the Line Segment
						ball = model.moveBallAtCurrentVelo(ball, model.getMoveTime());
					}
					
					break;
	
			// TODO add Flipper stuff here	
					
				default:		// procedures for a normal collision
					ball.setVelo(cd.getVelo());		// Post collision velocity ...
					break;
			}
		}
		else{
		
			ball.setVelo(cd.getVelo());		// Post collision velocity ...
		}

	}

	// Return TRUE if outside absorber, return FALSE if inside absorber
	private boolean handleAbsorberColi(CollisionDetails cd, AGizmoComponent gizmo) {

		Ball ball = cd.getBall();
		Absorber absorber = (Absorber) gizmo;
		int width_in_pixels = absorber.bmWidth * MainEngine.L;
		int height_in_pixels = absorber.bmHeight * MainEngine.L;
		
		// i.e. first collision BEFORE ball enter Absorber
		if (absorber.getCapturedBall() != ball && 
				(ball.getPreciseY() < absorber.getY()    ||    ball.getPreciseY() > absorber.getY() + height_in_pixels)) { 			// check if Ball is at Top or Bottom of Absorber
			ball.setVelo(new Vect(Angle.DEG_270, 0));		// make the velocity 0
			ball.stop();
			ball.setPreciseX(absorber.getX() + width_in_pixels - (0.25 * MainEngine.L));
			ball.setPreciseY(absorber.getY() + height_in_pixels - (0.25 * MainEngine.L));
			absorber.setBall(ball);
			return true;
		} else { 		// i.e. second collision AFTER ball enter Absorber
			return false;		// nothing happens
		}
	}
}
