package Absorber;

public class SpecialCollisionHandler {

	
	// Return TRUE if outside absorber, return FALSE if inside absorber
	public static boolean handleAbsorberColi(CollisionDetails cd){
		Ball ball = cd.getBall();
		Absorber absorber = (Absorber) cd.getCollider();
		
		if(absorber.getCapturedBall() != ball && (ball.getExactY() < absorber.getY() || ball.getExactY() > absorber.getY() + absorber.getHeight()) ){		// i.e. first collision BEFORE ball enter Absorber
			ball.stop();
			ball.setExactX(absorber.getX() + absorber.getWidth() - (0.25 * Model.L));
			ball.setExactY(absorber.getY() + absorber.getHeight() - (0.25 * Model.L));
			absorber.setBall(ball);
			return true;
		}
		else{		// i.e. second collision AFTER ball enter Absorber
			absorber.setBall(null);
			return false;
		}

	}
}
