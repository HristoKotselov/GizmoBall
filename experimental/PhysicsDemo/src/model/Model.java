package PhysicsDemo.src.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Model extends Observable {

	private ArrayList<VerticalLine> lines;
	private Ball ball;
	private Walls gws;
	PrintWriter writer;

	final int L = 20;

	public Model() {
		// Ball position (5, 5), moving horizontally right at 20 units
		ball = new Ball(5, 5, Angle.ZERO, 20);

		// Wall size 400 x 400 pixels
		gws = new Walls(0, 0, 20 * L, 20 * L);

		// Lines added in Main
		lines = new ArrayList<VerticalLine>();
	}

	public void moveBall() {

		double moveTime = 1.0/60.0; // 0.05 = 20 times per second as per
										// Gizmoball

		if (ball != null && !ball.stopped()) {


//			System.out.println("1 - " + ball.getVelo().toString());

			// Friction
			double mu1 = 0.025;
			double mu2 = 0.025;
			double scale = 1 - mu1 * moveTime - ball.getVelo().length() * mu2 * moveTime;
			ball.setVelo(ball.getVelo().times(scale));

//			System.out.println("2 - " + ball.getVelo().toString());

			// Gravity
			int gravity = 25;
			ball.setVelo(ball.getVelo().plus(new Vect(Angle.DEG_90, gravity * moveTime)));

//			System.out.println(ball.getVelo().toString());
//			writer.println(ball.getVelo().toString());

			CollisionDetails cd = timeUntilCollision();
			double tuc = cd.getTuc();
			if (tuc > moveTime) {
				// No collision ...
				ball = movelBallForTime(ball, moveTime);
			} else {
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

		// Time to collide with 4 walls
		ArrayList<LineSegment> lss = gws.getLineSegments();
		for (LineSegment line : lss) {
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
			}
		}

		// Time to collide with any vertical lines
		for (VerticalLine line : lines) {
			LineSegment ls = line.getLineSeg();
			time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(ls, ball.getVelo(), 1.0);
			}
		}
		return new CollisionDetails(shortestTime, newVelo);
	}

	public Ball getBall() {
		return ball;
	}

	public ArrayList<VerticalLine> getLines() {
		return lines;
	}

	public void addLine(VerticalLine l) {
		lines.add(l);
	}

	public void setBallSpeed(int x, int y) {
		ball.setVelo(new Vect(x, y));
	}
}
