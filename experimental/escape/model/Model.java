package escape.model;

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
 * part of the code is from Murray's Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Model extends Observable {

	private ArrayList<HorizontalLine> lines;
	private ArrayList<CircleBumper> circles;
	private ArrayList<SquareBumper> squares;
	private ArrayList<TriangleBumper> triangles;
	private Ball ball;
	private Walls gws;
	PrintWriter writer;

	final int L = 20;

	public Model() {

		try {
			writer = new PrintWriter(new File("out.txt"));
		} catch (FileNotFoundException e) {
		}

		// Ball position (5, 5), moving horizontally right at 20 units
		ball = new Ball(5, 5, Angle.ZERO, 300);

		// Wall size 400 x 400 pixels
		gws = new Walls(0, 0, 20 * L, 5 * L);

		// Lines added in Main
		lines = new ArrayList<HorizontalLine>();
		circles = new ArrayList<CircleBumper>();
		squares = new ArrayList<SquareBumper>();
		triangles = new ArrayList<TriangleBumper>();
	}

	public void moveBall() {

		double moveTime = 1.0/60.0; // 0.05 = 20 times per second as per
										// Gizmoball

		if (ball != null && !ball.stopped()) {


//			System.out.println("1 - " + ball.getVelo().toString());

			// Friction
//			double mu1 = 0.025*L;
//			double mu2 = 0.025*L;
//			double scale = 1 - mu1 * moveTime - ball.getVelo().length() * mu2 * moveTime;
//			ball.setVelo(ball.getVelo().times(scale));

//			System.out.println("2 - " + ball.getVelo().toString());

			// Gravity
			//int gravity = 25*L;
			//ball.setVelo(ball.getVelo().plus(new Vect(Angle.DEG_90, gravity * moveTime)));

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
//		for (HorizontalLine line : lines) {
//			LineSegment ls = line.getLineSeg();
//			Circle c1 = line.getLeftCircle();
//			Circle c2 = line.getRightCircle();
//			time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);
//			if (time < shortestTime) {
//				shortestTime = time;
//				newVelo = Geometry.reflectWall(ls, ball.getVelo(), 1.0);
//			}
//			time = Geometry.timeUntilCircleCollision(c1, ballCircle, ballVelocity);
//			if (time < shortestTime) {
//				shortestTime = time;
//				newVelo = Geometry.reflectCircle(c1.getCenter(), new Vect(ball.getExactX(), ball.getExactY()), ball.getVelo(), 1.0);
//			}
//			time = Geometry.timeUntilCircleCollision(c2, ballCircle, ballVelocity);
//			if (time < shortestTime) {
//				shortestTime = time;
//				newVelo = Geometry.reflectCircle(c2.getCenter(), new Vect(ball.getExactX(), ball.getExactY()), ball.getVelo(), 1.0);
//			}
//			
//		}
		
		for (CircleBumper circle : circles) {
			Circle c3 = circle.getCircle();
			time = Geometry.timeUntilCircleCollision(c3, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectCircle(new Vect(circle.getX(), circle.getY()), new Vect(ball.getExactX(), ball.getExactY()), ball.getVelo(), 1.0);
			}
		}
		
		for (SquareBumper square : squares) {
				for (Circle each : square.getEdges()){
					time = Geometry.timeUntilCircleCollision(each, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectCircle(each.getCenter(), new Vect(ball.getExactX(), ball.getExactY()), ball.getVelo(), 1.0);
					}
				}
				for (LineSegment each : square.getSides()){
					time = Geometry.timeUntilWallCollision(each, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectWall(each, ball.getVelo(), 1.0);
					}
				}
		}
		
		for (TriangleBumper triangle : triangles) {
			for (Circle each : triangle.getEdges()){
				time = Geometry.timeUntilCircleCollision(each, ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectCircle(each.getCenter(), new Vect(ball.getExactX(), ball.getExactY()), ball.getVelo(), 1.0);
				}
			}
			for (LineSegment each : triangle.getSides()){
				time = Geometry.timeUntilWallCollision(each, ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectWall(each, ball.getVelo(), 1.0);
				}
			}
	}
		
		
		return new CollisionDetails(shortestTime, newVelo);
	}

	public Ball getBall() {
		return ball;
	}

	public ArrayList<HorizontalLine> getLines() {
		return lines;
	}

	public void addLine(HorizontalLine l) {
		lines.add(l);
	}
	
	public ArrayList<CircleBumper> getCircleBumpers() {
		return circles;
	}
	
	public void addCircleBumper(CircleBumper c) {
		circles.add(c);
	}
	
	public ArrayList<SquareBumper> getSquareBumpers() {
		return squares;
	}
	
	public void addSquareBumper(SquareBumper s) {
		squares.add(s);
	}
	
	public ArrayList<TriangleBumper> getTriangleBumpers() {
		return triangles;
	}
	
	public void addTriangleBumper(TriangleBumper t) {
		triangles.add(t);
	}

	public void setBallSpeed(int x, int y) {
		ball.setVelo(new Vect(x, y));
	}
}
