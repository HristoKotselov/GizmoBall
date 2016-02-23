package Absorber;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Set;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Model extends Observable {

	private ArrayList<Absorber> absorbers;
	private Ball ball;
	private Walls gws;
	
	private CollisionDetails cd;
	
	private boolean isPlaying;		// used to tell Keyboard ActionListeners when they should be active (only when the game is running)

	final static int L = 20;

	public Model() {
		// Ball position (5, 5), moving horizontally right at 20L
		ball = new Ball("Ball1", Color.YELLOW, 5, 5, Angle.ZERO, 20 * L);

		// Wall size 400 x 400 pixels
		gws = new Walls(0, 0, 20 * L, 20 * L);

		absorbers = new ArrayList<Absorber>();
		
		cd = null;
		isPlaying = false;
	}

	public void moveBall() {
		double moveTime = 1.0/60.0;		// 60 fps

		if (ball != null && !ball.stopped()) {
			// Friction		- from 	6.170 Final Project  Gizmoball
			double mu1 = 0.025;		// 0.025 per second
			double mu2 = 0.025/L;	// 0.025 per L (convert to unit of mu1)
			double scale = 1 - mu1 * moveTime - ball.getVelo().length() * mu2 * moveTime;
			ball.setVelo(ball.getVelo().times(scale));

			// Gravity		- from 	6.170 Final Project  Gizmoball
			int gravity = 25 * L;		// 25L per second, converted to pixels per second
			ball.setVelo(ball.getVelo().plus(new Vect(Angle.DEG_90, gravity * moveTime)));

			cd = timeUntilCollision();
			double tuc = cd.getTuc();		// i.e. what is the time to the nearest future collision...?
			if (tuc > moveTime) {
				// No collision ...
				ball = movelBallForTime(ball, moveTime);
			} else {
				// We've got a collision in tuc
				
				if(cd.getCollider() instanceof Absorber){		// special collision (with Absorber)
					// if ball is inside an absorber while moving (i.e. shooting straight up for launching), then the ball is moved outside
					if(!SpecialCollisionHandler.handleAbsorberColi(cd)){		
						ball = movelBallForTime(ball, moveTime);
					}
				}
				else{		// procedures for a normal collision
					ball = movelBallForTime(ball, tuc);
					// Post collision velocity ...
					ball.setVelo(cd.getVelo());
				}
			}

			// Notify observers ... redraw updated view
			this.setChanged();
			this.notifyObservers();
		}
	}

	private Ball movelBallForTime(Ball ball, double time) {

		double newX = 0.0;
		double newY = 0.0;
		double xVel = ball.getVelo().x();
		double yVel = ball.getVelo().y();
		newX = ball.getExactX() + (xVel * time);
		newY = ball.getExactY() + (yVel * time);
		ball.setExactX(newX);
		ball.setExactY(newY);
		return ball;
	}

	private CollisionDetails timeUntilCollision() {
		// Find Time Until Collision and also, if there is a collision, the new
		// speed vector.
		// Create a physics.Circle from Ball
		Circle ballCircle = ball.getCircle();
		Vect ballVelocity = ball.getVelo();
		Vect newVelo = new Vect(0, 0);

		// Now find shortest time to hit a vertical line or a wall line
		double shortestTime = Double.MAX_VALUE;
		double time = 0.0;
		
		AGizmoComponent collider = null;		// the Gizmo component that the ball will collide with in a collision prediction

		// Time to collide with 4 walls
		ArrayList<LineSegment> lss = gws.getLineSegments();
		for (LineSegment line : lss) {
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
			}
		}

		// Time to collide with any absorbers
		Set<LineSegment> curAbsorberLSS;
		Set<Circle> curAbsorberCS;
		
		for (Absorber abs : absorbers) {
			// handle all Lines from Absorber
			curAbsorberLSS = abs.getLineSeg();
			
			for (LineSegment ls : curAbsorberLSS) {
				time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);		
				if (time < shortestTime) {
					shortestTime = time;
					collider = abs;
				}
			}
			
			// handle all Circles from Absorber
			curAbsorberCS = abs.getCircles();
			
			for (Circle circle : curAbsorberCS) {
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);		
				if (time < shortestTime) {
					shortestTime = time;
					collider = abs;
				}
			}
		}
				
		return new CollisionDetails(shortestTime, newVelo, ball, collider);
	}

	public Ball getBall() {
		return ball;
	}
	
	public ArrayList<Absorber> getAbsorbers() {
		return absorbers;
	}

	public void addAbsorber(Absorber abs) {
		absorbers.add(abs);
	}
	
	public void setBallSpeed(int x, int y) {
		ball.setVelo(new Vect(x, y));
	}
	
	public CollisionDetails getCollisionDetails(){
		return cd;
	}
	
	public boolean isPlaying(){
		return isPlaying;
	}
	
	public void start(){
		isPlaying = true;
	}
	
	public void stop(){
		isPlaying = false;
	}
}
