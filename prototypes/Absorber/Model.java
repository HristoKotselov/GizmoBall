package Absorber;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Set;

import Absorber.CollisionDetails.CollisionType;
import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Model extends Observable {

	private ArrayList<Line> lines;
	private ArrayList<Absorber> absorbers;
	private Ball ball;
	private Walls gws;
	PrintWriter writer;

	final static int L = 20;

	public Model() {
		// Ball position (5, 5), moving horizontally right at 20 units
		ball = new Ball("Ball1", Color.YELLOW, 5, 5, Angle.ZERO, 20);

		// Wall size 400 x 400 pixels
		gws = new Walls(0, 0, 20 * L, 20 * L);

		// Lines added in Main
		lines = new ArrayList<Line>();
		
		// Absorbers added in Main
		absorbers = new ArrayList<Absorber>();
	}

	public void moveBall() {
		double moveTime = 1.0/60.0; // 0.05 = 20 times per second as per
										// Gizmoball

		if (ball != null && !ball.stopped()) {
			// Friction
			double mu1 = 0.025;
			double mu2 = 0.025;
			double scale = 1 - mu1 * moveTime - ball.getVelo().length() * mu2 * moveTime;
			ball.setVelo(ball.getVelo().times(scale));

			// Gravity
			int gravity = 25;
			ball.setVelo(ball.getVelo().plus(new Vect(Angle.DEG_90, gravity * moveTime)));

			CollisionDetails cd = timeUntilCollision();
			double tuc = cd.getTuc();		// i.e. what is the time to the nearest future collision...?
			if (tuc > moveTime) {
				// No collision ...
				ball = movelBallForTime(ball, moveTime);
			} else {
				// TODO change this bit to incoperate different collision type
				
				// We've got a collision in tuc
				ball = movelBallForTime(ball, tuc);
				// Post collision velocity ...
				ball.setVelo(cd.getVelo());
			}

			// Notify observers ... redraw updated view
			this.setChanged();
			this.notifyObservers();
		}
	}

	private Ball movelBallForTime(Ball ball, double time) {

		double newX = 0.0;
		double newY = 0.0;
		double xVel = ball.getVelo().x() * L;
		double yVel = ball.getVelo().y() * L;
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
		Vect ballVelocity = ball.getVelo().times(20);
		Vect newVelo = new Vect(0, 0);

		// Now find shortest time to hit a vertical line or a wall line
		double shortestTime = Double.MAX_VALUE;
		double time = 0.0;
		
		CollisionType collisionType = CollisionDetails.CollisionType.REGULAR;

		// Time to collide with 4 walls
		ArrayList<LineSegment> lss = gws.getLineSegments();
		for (LineSegment line : lss) {
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
				collisionType = CollisionDetails.CollisionType.REGULAR;
			}
		}

		// Time to collide with any lines
		for (Line line : lines) {
			LineSegment ls = line.getLineSeg();
			time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(ls, ball.getVelo(), 1.0);
				collisionType = CollisionDetails.CollisionType.REGULAR;
			}
		}
		
		// Time to collide with any absorbers
		Set<LineSegment> curAbsorberLSS;
		
		for (Absorber abs : absorbers) {
			curAbsorberLSS = abs.getLineSeg();
			
			for (LineSegment ls : curAbsorberLSS) {
					time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);		
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = new Vect(0, 0);		// stop the ball's velocity
						collisionType = CollisionDetails.CollisionType.ABSORBER;
					}
			}
		}
				
		return new CollisionDetails(shortestTime, newVelo, collisionType);
	}

	public Ball getBall() {
		return ball;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}

	public void addLine(Line l) {
		lines.add(l);
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
}
