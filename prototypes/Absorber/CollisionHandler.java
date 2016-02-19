package Absorber;

public class CollisionHandler {

	public static void handleAbsorber(CollisionDetails cd){
		if(cd.getCollider() instanceof Absorber){
			Ball ball = cd.getBall();
			Absorber absorber = (Absorber) cd.getCollider();
			
			if(absorber.getCapturedBall() != ball){		// i.e. first collision BEFORE ball enter Absorber
				ball.stop();
				ball.setExactX(absorber.getX() + absorber.getWidth() - (0.25 * Model.L));
				ball.setExactY(absorber.getY() + absorber.getHeight() - (0.25 * Model.L));
				absorber.setBall(ball);
			}
			else{		// i.e. second collision AFTER ball entered Absorber, so it is launching up right now
// TODO finish this part (ball launch up)
				absorber.setBall(null);
			}
		}
		else
			System.out.println("Something went wrong with Model [moveBall() ] ");
	}
}
